(function () {
  const GH = "https://github.com/donthuavinashbabu/book/blob/main/book/";

  const NAV = [
    {
      id: "overview",
      label: "Overview",
      children: [
        { href: "index.html", label: "Introduction", id: "intro" },
        { href: "overview/using-this-site.html", label: "Using this site", id: "using" },
        { href: "overview/topics.html", label: "Topics", id: "topics" },
      ],
    },
    {
      id: "guides",
      label: "Guides & notes",
      children: [
        { href: "guides/index.html", label: "Browse all guides", id: "guides-home" },
        { href: "guides/java-errors.html", label: "Java errors & solutions", id: "guides-java" },
        {
          href: GH + "module-does-not-open.md",
          label: "module does not open (Unsafe)",
          id: "g-module",
        },
        { href: "guides/preparation.html", label: "Preparation", id: "guides-prep" },
        { href: GH + "architect-preparation.md", label: "Architect preparation plan", id: "g-arch" },
        {
          href: "https://drive.google.com/file/d/10Uh-f_IAgnWNexeKjPFOfTZtwraXHBHj/view?usp=sharing",
          label: "SDE road map",
          id: "g-sde",
        },
        { href: "guides/misc.html", label: "Misc", id: "guides-misc" },
        {
          href: GH + "encoding-encryption-tokenization.md",
          label: "Encoding vs encryption vs tokenization",
          id: "g-enc",
        },
        {
          href: GH + "env-variables-vm-variables-program-arguments.md",
          label: "Env vars vs VM args vs program args",
          id: "g-env",
        },
        { href: GH + "model-vs-entity.md", label: "Model vs entity", id: "g-model" },
        { href: GH + "types-of-sql-queries.md", label: "Types of SQL queries", id: "g-sql" },
        { href: GH + "softwares-list.md", label: "Software list for Java engineer", id: "g-sw" },
        { href: GH + "java-architect-tech-list.md", label: "Java architect tech list", id: "g-arch-tech" },
        { href: GH + "learning/README.md", label: "Learnings and certifications", id: "g-learn" },
        { href: GH + "email-skills.jpeg", label: "Email skills", id: "g-email" },
        { href: GH + "code-review.jpeg", label: "Code review", id: "g-review" },
        {
          href: "https://drive.google.com/file/d/1F9k7t9pu9Toj48l3aXcmiKA7Q90qP7nl/view?usp=sharing",
          label: "SDE road map (alt)",
          id: "g-sde2",
        },
        { href: GH + "java-essentials.txt", label: "Java developer essential skills", id: "g-ess" },
        {
          href: GH + "product-development-points.txt",
          label: "Product development essentials",
          id: "g-prod",
        },
        { href: GH + "edge-vs-corner-case.md", label: "Edge case vs corner case", id: "g-edge" },
        { href: "guides/new-projects.html", label: "New projects", id: "guides-nfr" },
        { href: GH + "nfrs.md", label: "NFRs for new projects", id: "g-nfr" },
      ],
    },
    {
      id: "git",
      label: "Git",
      children: [
        { href: "git/index.html", label: "Introduction", id: "git-intro" },
        { href: GH + "git/notes.md", label: "Notes (theory)", id: "git-notes" },
        { href: GH + "git/commands.md", label: "Git commands", id: "git-commands" },
        { href: GH + "git/branching.md", label: "Branching strategy", id: "git-branch" },
        { href: GH + "git/squash-commints.md", label: "Rebase and squash commits", id: "git-squash" },
        { href: GH + "git/cherry-pick.md", label: "Cherry pick", id: "git-cherry" },
        { href: GH + "git/stash.md", label: "Stash", id: "git-stash" },
        { href: GH + "git/ssh.md", label: "SSH key setup", id: "git-ssh" },
        {
          href: GH + "git/two-github-accounts-in-same-machine-with-2-dffirent-ssh-keys.md",
          label: "Two GitHub accounts / SSH keys",
          id: "git-two-ssh",
        },
        {
          href: GH + "git/push-local-repo-to-new-remote-repo.md",
          label: "Push local repo to new remote",
          id: "git-remote",
        },
        { href: GH + "git/app-passwords.md", label: "Bitbucket app passwords", id: "git-app-pw" },
        { href: GH + "git/unable-to-access-url-403.md", label: "Unable to access URL (403)", id: "git-403" },
        { href: GH + "git/.gitignore", label: "Git ignore file", id: "git-ignore" },
        {
          href: "https://www.conventionalcommits.org/en/v1.0.0/",
          label: "Conventional Commits",
          id: "git-conventional",
        },
        { href: "git/index.html#references", label: "Materials & references", id: "git-refs" },
      ],
    },
    {
      id: "cucumber",
      label: "Cucumber",
      children: [
        { href: "cucumber/index.html", label: "Introduction", id: "cuc-intro" },
        { href: "cucumber/project-structure.html", label: "Project structure", id: "cuc-structure" },
        { href: "cucumber/setup.html", label: "Maven setup", id: "cuc-setup" },
        { href: "cucumber/feature.html", label: "Feature: calculator", id: "cuc-feature" },
        {
          href: "cucumber/step-defs.html",
          label: "CalculatorStepDef",
          id: "cuc-steps",
          children: [
            { href: "cucumber/step-defs.html#given", label: "given()", id: "cuc-given" },
            { href: "cucumber/step-defs.html#when", label: "when()", id: "cuc-when" },
            { href: "cucumber/step-defs.html#then", label: "then()", id: "cuc-then" },
          ],
        },
        {
          href: "cucumber/calculator.html",
          label: "Calculator",
          id: "cuc-calc",
          children: [{ href: "cucumber/calculator.html#sum", label: "sum()", id: "cuc-sum" }],
        },
        { href: "cucumber/runner.html", label: "RunnerTest", id: "cuc-runner" },
      ],
    },
    {
      id: "apache-commons-lang3",
      label: "Apache Commons Lang3",
      children: [
        { href: "apache-commons-lang3/index.html", label: "Introduction", id: "acl-intro" },
        { href: "apache-commons-lang3/project-structure.html", label: "Project structure", id: "acl-structure" },
        { href: "apache-commons-lang3/setup.html", label: "Maven setup", id: "acl-setup" },
        {
          href: "apache-commons-lang3/api/RandomStringUtilsTest.html",
          label: "API: RandomStringUtilsTest",
          id: "acl-api",
          children: [
            {
              href: "apache-commons-lang3/api/RandomStringUtilsTest.html#randomString",
              label: "randomString()",
              id: "acl-api-randomString",
            },
          ],
        },
        { href: "apache-commons-lang3/examples/random-string-utils.html", label: "Examples: RandomStringUtils", id: "acl-ex" },
        { href: "apache-commons-lang3/examples/random.html", label: "random()", id: "acl-ex-random" },
        { href: "apache-commons-lang3/examples/random-alphabetic.html", label: "randomAlphabetic()", id: "acl-ex-alpha" },
        {
          href: "apache-commons-lang3/examples/random-alphanumeric.html",
          label: "randomAlphanumeric()",
          id: "acl-ex-alnum",
        },
      ],
    },
  ];

  function basePath() {
    const body = document.body;
    return body && body.dataset.base != null ? body.dataset.base : "";
  }

  function withBase(href) {
    if (/^https?:\/\//i.test(href) || href.startsWith("#")) {
      return href;
    }
    return basePath() + href;
  }

  function linkAttrs(href) {
    if (/^https?:\/\//i.test(href)) {
      return ' target="_blank" rel="noopener"';
    }
    return "";
  }

  function allNavPagePaths() {
    const paths = [];
    NAV.forEach((section) => {
      section.children.forEach((item) => {
        paths.push(item.href.split("#")[0]);
        (item.children || []).forEach((child) => paths.push(child.href.split("#")[0]));
      });
    });
    return [...new Set(paths.filter((p) => p && !/^https?:\/\//i.test(p)))];
  }

  function currentPageKey() {
    let path = window.location.pathname.replace(/\\/g, "/");
    if (path.endsWith("/")) {
      path += "index.html";
    }

    // Prefer longest known nav path so topic index.html does not match Overview index.html.
    const known = allNavPagePaths().sort((a, b) => b.length - a.length);
    for (const href of known) {
      if (path === href || path.endsWith("/" + href)) {
        return href;
      }
    }

    const marker = "/docs/";
    const idx = path.lastIndexOf(marker);
    if (idx >= 0) {
      const rest = path.slice(idx + marker.length);
      return rest || "index.html";
    }

    const parts = path.split("/").filter(Boolean);
    return parts[parts.length - 1] || "index.html";
  }

  const EXPANDED_KEY = "docs-nav-expanded";

  function isActive(href) {
    const page = currentPageKey();
    const cleanHref = href.split("#")[0];
    if (page !== cleanHref) {
      return false;
    }
    if (href.includes("#")) {
      return window.location.hash === href.slice(href.indexOf("#"));
    }
    return true;
  }

  function isActiveSection(section, activePage) {
    return (
      section.children.some(
        (item) => isActive(item.href) || (item.children || []).some((c) => isActive(c.href))
      ) || activePage.startsWith(section.id + "/")
    );
  }

  function getExpandedIds() {
    try {
      const raw = sessionStorage.getItem(EXPANDED_KEY);
      // Default: all sections collapsed (empty set).
      return new Set(raw ? JSON.parse(raw) : []);
    } catch (e) {
      return new Set();
    }
  }

  function setExpandedIds(ids) {
    sessionStorage.setItem(EXPANDED_KEY, JSON.stringify([...ids]));
  }

  function renderNav() {
    const host = document.getElementById("sidebar-nav");
    if (!host) return;

    const activePage = currentPageKey();
    const expandedIds = getExpandedIds();
    // Drop legacy collapsed-storage key if present.
    sessionStorage.removeItem("docs-nav-collapsed");

    const html = NAV.map((section) => {
      const childHtml = section.children
        .map((item) => {
          const active = isActive(item.href) ? " active" : "";
          let sub = "";
          if (item.children && item.children.length) {
            sub =
              '<ul class="nav-sub">' +
              item.children
                .map((child) => {
                  const childActive = isActive(child.href) ? " active" : "";
                  return `<li><a class="${childActive.trim()}" href="${withBase(child.href)}"${linkAttrs(child.href)} data-label="${child.label.toLowerCase()}">${child.label}</a></li>`;
                })
                .join("") +
              "</ul>";
          }
          return `<li><a class="${active.trim()}" href="${withBase(item.href)}"${linkAttrs(item.href)} data-label="${item.label.toLowerCase()}">${item.label}</a>${sub}</li>`;
        })
        .join("");

      // Default collapsed. Only the active section (and user-expanded ones) stay open.
      if (isActiveSection(section, activePage)) {
        expandedIds.add(section.id);
      }
      const shouldExpand = expandedIds.has(section.id);

      return `
        <div class="nav-section${shouldExpand ? "" : " collapsed"}" data-section="${section.id}">
          <button type="button" class="nav-toggle" aria-expanded="${shouldExpand ? "true" : "false"}">
            <span>${section.label}</span>
            <span class="chevron" aria-hidden="true">▼</span>
          </button>
          <ul class="nav-children">${childHtml}</ul>
        </div>`;
    }).join("");

    setExpandedIds(expandedIds);
    host.innerHTML = html;

    host.querySelectorAll(".nav-toggle").forEach((btn) => {
      btn.addEventListener("click", (event) => {
        event.preventDefault();
        event.stopPropagation();
        const section = btn.closest(".nav-section");
        const collapsed = section.classList.toggle("collapsed");
        btn.setAttribute("aria-expanded", collapsed ? "false" : "true");
        const ids = getExpandedIds();
        const id = section.dataset.section;
        if (collapsed) ids.delete(id);
        else ids.add(id);
        setExpandedIds(ids);
      });
    });

    // Submenu links only navigate; never toggle section collapse.
    host.querySelectorAll(".nav-children a").forEach((link) => {
      link.addEventListener("click", (event) => {
        event.stopPropagation();
      });
    });
  }

  function wireSearch() {
    const input = document.getElementById("nav-search");
    if (!input) return;
    input.addEventListener("input", () => {
      const q = input.value.trim().toLowerCase();
      document.querySelectorAll(".nav-section").forEach((section) => {
        let anyVisible = false;
        section.querySelectorAll(".nav-children > li").forEach((li) => {
          const links = li.querySelectorAll("a");
          let match = false;
          links.forEach((a) => {
            const label = (a.dataset.label || a.textContent || "").toLowerCase();
            const ok = !q || label.includes(q);
            a.classList.toggle("hidden-by-search", !ok && a.parentElement.tagName === "LI" && a.closest(".nav-sub"));
            if (ok) match = true;
          });
          const primary = li.querySelector(":scope > a");
          const primaryMatch = !q || (primary && (primary.dataset.label || "").includes(q));
          const subMatch = Array.from(li.querySelectorAll(".nav-sub a")).some((a) =>
            (a.dataset.label || "").includes(q)
          );
          const show = !q || primaryMatch || subMatch || match;
          li.classList.toggle("hidden-by-search", !show);
          if (show) anyVisible = true;
        });
        section.classList.toggle("hidden-by-search", q && !anyVisible);
        if (q && anyVisible) section.classList.remove("collapsed");
      });
    });
  }

  function wireMobileMenu() {
    const toggle = document.getElementById("menu-toggle");
    const sidebar = document.getElementById("sidebar");
    if (!toggle || !sidebar) return;
    toggle.addEventListener("click", () => {
      sidebar.classList.toggle("open");
    });
  }

  document.addEventListener("DOMContentLoaded", () => {
    renderNav();
    wireSearch();
    wireMobileMenu();
  });
})();
