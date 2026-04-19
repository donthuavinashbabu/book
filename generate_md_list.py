import os
from pathlib import Path

# The root directory
root_dir = r"c:\MyPC\personal\github\book\book"

# The categories
categories = {
    "Phase 1: Core Fundamentals & Utilities": ["core-java", "data-structures-algorithms", "guava", "apache-commons-lang3", "json-jackson", "map-struct", "log4j", "jwt", "env-variables-vm-variables-program-arguments.md", "module-does-not-open.md", "java-essentials.txt"],
    "Phase 2: Testing Practices": ["junit-4", "junit-5", "mockito", "cucumber", "java-faker"],
    "Phase 3: Frameworks (The Spring Ecosystem)": ["spring-boot", "spring-security", "spring-batch", "rest-api"],
    "Phase 4: Databases & Data Management": ["sql", "mysql", "postgre-sql", "oracle", "sql-server", "mongo-db", "redis", "hikari-cp", "types-of-sql-queries.md"],
    "Phase 5: Architecture & System Design": ["system-design", "microservices", "architect-preparation.md", "java-architect-tech-list.md", "model-vs-entity.md", "nfrs.md", "encoding-encryption-tokenization.md", "kafka"],
    "Phase 6: DevOps, Cloud, & Infrastructure": ["docker", "kubernetes", "aws", "gitops-argocd", "git", "linux", "windows", "splunk", "solr"],
    "Phase 7: Tooling & Emerging Technologies": ["intellij", "maven", "postman", "uv", "softwares-list.md", "ai", "ollama"],
    "Phase 8: Professional Engineering Practices & Learning": ["learning"],
    "Other / Uncategorized": [] # Fallback
}

md_files = []
for root, dirs, files in os.walk(root_dir):
    for f in files:
        if f.endswith('.md'):
            md_files.append(os.path.join(root, f))

categorized_files = {k: [] for k in categories.keys()}

for file_path in md_files:
    rel_path = os.path.relpath(file_path, root_dir)
    parts = rel_path.split(os.sep)
    
    placed = False
    for cat_name, cat_keywords in categories.items():
        if cat_name == "Other / Uncategorized": continue
        
        # Check if any keyword matches top level dir or the file itself
        for kw in cat_keywords:
            if kw in parts[0] or kw in rel_path:
                categorized_files[cat_name].append(file_path)
                placed = True
                break
        if placed:
            break
            
    if not placed:
        categorized_files["Other / Uncategorized"].append(file_path)

# Output generation
output_file = r"c:\MyPC\personal\github\book\chronological-md-files.md"
with open(output_file, 'w', encoding='utf-8') as f:
    f.write("# Chronological List of MD Files for Revision\n\n")
    
    for cat_name, files in categorized_files.items():
        if not files: continue
        f.write(f"## {cat_name}\n")
        # sort files alphabetically within category
        files.sort()
        for file in files:
            f.write(f"- {file}\n")
        f.write("\n")

print(f"Generated successfully at {output_file}")
