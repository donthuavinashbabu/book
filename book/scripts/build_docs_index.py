#!/usr/bin/env python3
"""Generate MkDocs-ready content tree from repository files."""

from __future__ import annotations

import re
import shutil
import stat
from pathlib import Path
from urllib.parse import unquote

ROOT = Path(__file__).resolve().parents[1]
OUTPUT_DIR = ROOT / "site-src"

SKIP_DIRS = {
    ".git",
    ".idea",
    ".gradle",
    ".venv",
    "venv",
    "__pycache__",
    "node_modules",
    "target",
    "build",
    "out",
    "site",
    "site-src",
    "taskArtifacts",
    ".mvn",
}

MARKDOWN_EXT = {".md"}
TEXTUAL_EXT = {".txt"}
ASSET_EXT = {".png", ".jpg", ".jpeg", ".gif", ".svg", ".webp", ".pdf"}

PROJECT_MARKERS = (
    "pom.xml",
    "build.gradle",
    "build.gradle.kts",
    "settings.gradle",
    "pyproject.toml",
)

CODE_FILE_EXT = {
    ".java",
    ".kt",
    ".kts",
    ".gradle",
    ".groovy",
    ".xml",
    ".properties",
    ".yml",
    ".yaml",
    ".json",
    ".sql",
    ".sh",
    ".bat",
    ".ps1",
    ".py",
    ".js",
    ".ts",
    ".go",
    ".avsc",
    ".proto",
    ".cfg",
    ".conf",
    ".ini",
    ".env",
    ".csv",
}

CODE_FILENAMES = {
    "Dockerfile",
    "docker-compose.yml",
    "docker-compose.yaml",
    "pom.xml",
    "build.gradle",
    "build.gradle.kts",
    "settings.gradle",
}

NON_TOPIC_DIRS = SKIP_DIRS | {"img", "scripts"}

MAX_TEXT_FILE_BYTES = 250_000
LINK_PATTERN = re.compile(r"(!?\[[^\]]*\]\()([^)]+)(\))")

HIDE_NAV_FRONTMATTER = "---\nhide:\n  - navigation\n---\n\n"
CODE_PROJECTS_START = "<!-- docs-gen:code-projects-start -->"
CODE_PROJECTS_END = "<!-- docs-gen:code-projects-end -->"


def should_skip(path: Path) -> bool:
    return any(part in SKIP_DIRS for part in path.parts)


def get_topic_modules() -> list[Path]:
    modules: list[Path] = []
    for item in ROOT.iterdir():
        if not item.is_dir():
            continue
        if item.name in NON_TOPIC_DIRS or item.name.startswith("."):
            continue
        modules.append(item)
    return sorted(modules)


def discover_files() -> tuple[list[Path], list[Path], list[Path]]:
    markdown_files: list[Path] = []
    text_files: list[Path] = []
    asset_files: list[Path] = []

    for path in ROOT.rglob("*"):
        if not path.is_file():
            continue
        rel = path.relative_to(ROOT)
        if should_skip(rel):
            continue
        if "_code" in rel.parts:
            continue

        ext = path.suffix.lower()
        if ext in MARKDOWN_EXT:
            markdown_files.append(path)
        elif ext in TEXTUAL_EXT:
            if path.stat().st_size <= MAX_TEXT_FILE_BYTES:
                text_files.append(path)
        elif ext in ASSET_EXT:
            asset_files.append(path)

    return markdown_files, text_files, asset_files


def is_code_file(path: Path) -> bool:
    return path.suffix.lower() in CODE_FILE_EXT or path.name in CODE_FILENAMES


def find_code_projects(module_dir: Path) -> list[Path]:
    projects: set[Path] = set()
    for marker in PROJECT_MARKERS:
        for marker_path in module_dir.rglob(marker):
            rel = marker_path.relative_to(ROOT)
            if should_skip(rel):
                continue
            projects.add(marker_path.parent.resolve())
    return sorted(projects, key=lambda p: (len(p.parts), p.as_posix()))


def find_containing_project(projects: list[Path], file_path: Path) -> Path | None:
    containing = [project for project in projects if file_path.is_relative_to(project)]
    if not containing:
        return None
    return max(containing, key=lambda p: len(p.parts))


def discover_code_files(project_root: Path) -> list[Path]:
    files: list[Path] = []
    for path in project_root.rglob("*"):
        if not path.is_file():
            continue
        rel = path.relative_to(ROOT)
        if should_skip(rel):
            continue
        if is_code_file(path):
            files.append(path)
    return sorted(files, key=lambda p: p.as_posix())


def project_slug(module_dir: Path, project_root: Path) -> str:
    rel = project_root.relative_to(module_dir)
    return rel.as_posix() if rel.parts else project_root.name


def code_file_page_path(module_dir: Path, project_root: Path, source_file: Path) -> Path:
    slug = project_slug(module_dir, project_root)
    rel_file = source_file.relative_to(project_root).as_posix()
    module_rel = module_dir.relative_to(ROOT)
    return (OUTPUT_DIR / module_rel / "_code" / slug / "files" / f"{rel_file}.md").resolve()


def code_project_index_path(module_dir: Path, project_root: Path) -> Path:
    slug = project_slug(module_dir, project_root)
    module_rel = module_dir.relative_to(ROOT)
    return (OUTPUT_DIR / module_rel / "_code" / slug / "index.md").resolve()


def top_level_module(path: Path) -> Path | None:
    rel = path.relative_to(ROOT)
    if not rel.parts:
        return None
    module = ROOT / rel.parts[0]
    return module if module.is_dir() else None


def destination_for(source: Path) -> Path:
    rel = source.relative_to(ROOT)
    ext = source.suffix.lower()

    if ext in MARKDOWN_EXT:
        if source.name.lower() == "readme.md":
            return (OUTPUT_DIR / rel.parent / "index.md").resolve()
        return (OUTPUT_DIR / rel).resolve()
    if ext in TEXTUAL_EXT:
        return (OUTPUT_DIR / rel).with_suffix(rel.suffix + ".md").resolve()
    if ext in ASSET_EXT:
        return (OUTPUT_DIR / rel).resolve()
    return (OUTPUT_DIR / rel).resolve()


def destination_for_link(source_file: Path, target_file: Path) -> Path:
    if is_code_file(target_file):
        module = top_level_module(target_file)
        if module is not None:
            projects = find_code_projects(module)
            project = find_containing_project(projects, target_file)
            if project is not None:
                return code_file_page_path(module, project, target_file)
    return destination_for(target_file)


def ensure_clean_output() -> None:
    def handle_remove_readonly(func, path, excinfo):
        del excinfo
        try:
            Path(path).chmod(stat.S_IWRITE)
            func(path)
        except OSError:
            pass

    if OUTPUT_DIR.exists():
        try:
            shutil.rmtree(OUTPUT_DIR, onexc=handle_remove_readonly)
        except PermissionError:
            for item in sorted(OUTPUT_DIR.rglob("*"), reverse=True):
                try:
                    if item.is_file() or item.is_symlink():
                        item.chmod(stat.S_IWRITE)
                        item.unlink(missing_ok=True)
                    elif item.is_dir():
                        item.rmdir()
                except OSError:
                    continue
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)


def title_from_path(path: Path) -> str:
    return path.name.replace("-", " ").replace("_", " ")


def relative_posix_path(from_dir: Path, to_path: Path) -> str:
    return Path(shutil.os.path.relpath(to_path, start=from_dir)).as_posix()


def resolve_link_target(source_file: Path, raw_target: str) -> Path | None:
    decoded_target = unquote(raw_target)
    path_part = decoded_target.split("#", 1)[0].strip()
    if not path_part:
        return source_file

    absolute = (source_file.parent / path_part).resolve()
    try:
        absolute.relative_to(ROOT)
    except ValueError:
        return None

    if absolute.exists() and absolute.is_file():
        return absolute

    candidate_exts = (".md", ".txt", *CODE_FILE_EXT)
    for ext in candidate_exts:
        candidate = absolute.with_suffix(ext)
        if candidate.exists() and candidate.is_file():
            return candidate

    if absolute.exists() and absolute.is_dir():
        readme = absolute / "README.md"
        if readme.exists():
            return readme
        return None
    return None


def rewrite_markdown_links(content: str, source_file: Path, missing_links: list[str]) -> str:
    def replace(match: re.Match[str]) -> str:
        prefix, target, suffix = match.groups()

        if target.startswith(("http://", "https://", "mailto:", "#")):
            return match.group(0)

        target_path, anchor = (target.split("#", 1) + [""])[:2]
        anchor_part = f"#{anchor}" if anchor else ""

        resolved = resolve_link_target(source_file, target_path)
        if resolved is None:
            missing_links.append(f"{source_file.relative_to(ROOT)} -> {target}")
            return match.group(0)

        src_dest = destination_for(source_file)
        target_dest = destination_for_link(source_file, resolved)
        relative_link = relative_posix_path(src_dest.parent, target_dest)
        return f"{prefix}{relative_link}{anchor_part}{suffix}"

    return LINK_PATTERN.sub(replace, content)


def copy_markdown_files(markdown_files: list[Path], missing_links: list[str]) -> None:
    for source in markdown_files:
        dest = destination_for(source)
        dest.parent.mkdir(parents=True, exist_ok=True)
        raw = source.read_text(encoding="utf-8", errors="replace")
        rewritten = rewrite_markdown_links(raw, source, missing_links)
        dest.write_text(rewritten, encoding="utf-8")


def convert_text_files(text_files: list[Path]) -> None:
    for source in text_files:
        dest = destination_for(source)
        dest.parent.mkdir(parents=True, exist_ok=True)
        raw = source.read_text(encoding="utf-8", errors="replace")
        wrapped = (
            f"# {title_from_path(source.relative_to(ROOT))}\n\n"
            f"Source: `{source.relative_to(ROOT).as_posix()}`\n\n"
            f"```text\n{raw}\n```\n"
        )
        dest.write_text(wrapped, encoding="utf-8")


def copy_assets(asset_files: list[Path]) -> None:
    for source in asset_files:
        dest = destination_for(source)
        dest.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(source, dest)


def language_for_file(path: Path) -> str:
    ext = path.suffix.lower().lstrip(".")
    mapping = {
        "java": "java",
        "kt": "kotlin",
        "kts": "kotlin",
        "gradle": "gradle",
        "groovy": "groovy",
        "xml": "xml",
        "yml": "yaml",
        "yaml": "yaml",
        "json": "json",
        "sql": "sql",
        "sh": "bash",
        "bat": "bat",
        "ps1": "powershell",
        "py": "python",
        "js": "javascript",
        "ts": "typescript",
        "go": "go",
        "properties": "properties",
        "avsc": "json",
        "proto": "protobuf",
    }
    return mapping.get(ext, "text")


def write_code_file_page(
    module_dir: Path,
    project_root: Path,
    source_file: Path,
) -> Path:
    dest = code_file_page_path(module_dir, project_root, source_file)
    dest.parent.mkdir(parents=True, exist_ok=True)
    lang = language_for_file(source_file)
    rel_source = source_file.relative_to(ROOT).as_posix()
    content = source_file.read_text(encoding="utf-8", errors="replace")
    page = (
        f"{HIDE_NAV_FRONTMATTER}"
        f"# {source_file.name}\n\n"
        f"Source: `{rel_source}`\n\n"
        f"```{lang}\n{content}\n```\n"
    )
    dest.write_text(page, encoding="utf-8")
    return dest


def write_code_project_index(
    module_dir: Path,
    project_root: Path,
    code_files: list[Path],
    generated_pages: dict[Path, Path],
) -> Path:
    dest = code_project_index_path(module_dir, project_root)
    dest.parent.mkdir(parents=True, exist_ok=True)

    slug = project_slug(module_dir, project_root)
    rel_project = project_root.relative_to(ROOT).as_posix()
    module_name = module_dir.name

    lines = [
        HIDE_NAV_FRONTMATTER.rstrip(),
        f"# Code Project: {slug}",
        "",
        f"Module: **{module_name}**",
        f"Path: `{rel_project}`",
        "",
        "Use the search box below to filter code files. All files are also indexed by the site search.",
        "",
        '<input type="search" id="code-file-search" placeholder="Search code files..." '
        'style="width:100%;padding:0.5rem;margin:0.5rem 0;" />',
        "",
        '<ul id="code-file-list">',
    ]

    for source in code_files:
        rel = source.relative_to(project_root).as_posix()
        page = generated_pages[source]
        link = relative_posix_path(dest.parent, page)
        lines.append(f'<li data-filter="{rel.lower()}"><a href="{link}">{rel}</a></li>')

    lines.extend(
        [
            "</ul>",
            "",
            "<script>",
            "const searchInput = document.getElementById('code-file-search');",
            "const items = Array.from(document.querySelectorAll('#code-file-list li'));",
            "searchInput.addEventListener('input', () => {",
            "  const q = searchInput.value.trim().toLowerCase();",
            "  items.forEach((item) => {",
            "    const text = item.getAttribute('data-filter') || '';",
            "    item.style.display = !q || text.includes(q) ? '' : 'none';",
            "  });",
            "});",
            "</script>",
            "",
        ]
    )

    dest.write_text("\n".join(lines), encoding="utf-8")
    return dest


def inject_code_projects_section(module_index: Path, project_links: list[tuple[str, Path]]) -> None:
    if not module_index.exists():
        return

    content = module_index.read_text(encoding="utf-8")
    if CODE_PROJECTS_START in content:
        before = content.split(CODE_PROJECTS_START, 1)[0].rstrip()
        content = before

    section_lines = [
        "",
        CODE_PROJECTS_START,
        "## Code Projects",
        "",
        "Browse source files for each project (searchable file list):",
        "",
    ]
    for label, index_page in project_links:
        link = relative_posix_path(module_index.parent, index_page)
        section_lines.append(f"- [{label}]({link})")
    section_lines.extend(["", CODE_PROJECTS_END, ""])

    module_index.write_text(content + "\n".join(section_lines), encoding="utf-8")


def generate_code_modules() -> tuple[int, int, int]:
    project_count = 0
    code_file_count = 0

    for module_dir in get_topic_modules():
        projects = find_code_projects(module_dir)
        if not projects:
            continue

        project_links: list[tuple[str, Path]] = []

        for project_root in projects:
            code_files = discover_code_files(project_root)
            if not code_files:
                continue

            generated_pages: dict[Path, Path] = {}
            for source in code_files:
                generated_pages[source] = write_code_file_page(module_dir, project_root, source)
                code_file_count += 1

            index_page = write_code_project_index(
                module_dir,
                project_root,
                code_files,
                generated_pages,
            )
            project_links.append((project_slug(module_dir, project_root), index_page))
            project_count += 1

        if not project_links:
            continue

        module_rel = module_dir.relative_to(ROOT)
        module_index = OUTPUT_DIR / module_rel / "index.md"
        inject_code_projects_section(module_index, project_links)

    return project_count, code_file_count, len(get_topic_modules())


def write_build_report(
    markdown_files: list[Path],
    text_files: list[Path],
    asset_files: list[Path],
    missing_links: list[str],
    project_count: int,
    code_file_count: int,
) -> None:
    report = OUTPUT_DIR / "_build_report.md"
    lines = [
        HIDE_NAV_FRONTMATTER.rstrip(),
        "# Build Report",
        "",
        f"- Markdown pages: {len(markdown_files)}",
        f"- Text pages (.txt): {len(text_files)}",
        f"- Copied assets: {len(asset_files)}",
        f"- Code projects: {project_count}",
        f"- Code file pages: {code_file_count}",
        "",
        "Code pages are hidden from the left navigation and linked from each module index.",
        "",
        "## Missing Relative Links",
    ]
    if missing_links:
        lines.extend(f"- {item}" for item in sorted(set(missing_links)))
    else:
        lines.append("- None")
    lines.append("")
    report.write_text("\n".join(lines), encoding="utf-8")


def main() -> None:
    ensure_clean_output()
    markdown_files, text_files, asset_files = discover_files()
    missing_links: list[str] = []

    copy_markdown_files(markdown_files, missing_links)
    convert_text_files(text_files)
    copy_assets(asset_files)

    project_count, code_file_count, _ = generate_code_modules()
    write_build_report(
        markdown_files,
        text_files,
        asset_files,
        missing_links,
        project_count,
        code_file_count,
    )

    print("Generated docs source at:", OUTPUT_DIR)
    print("Markdown:", len(markdown_files))
    print("Text:", len(text_files))
    print("Assets:", len(asset_files))
    print("Code projects:", project_count)
    print("Code file pages:", code_file_count)
    print("Missing links:", len(set(missing_links)))


if __name__ == "__main__":
    main()
