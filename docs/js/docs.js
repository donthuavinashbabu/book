(function () {
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

  function allNavPagePaths() {
    const paths = [];
    NAV.forEach((section) => {
      section.children.forEach((item) => {
        paths.push(item.href.split("#")[0]);
        (item.children || []).forEach((child) => paths.push(child.href.split("#")[0]));
      });
    });
    return [...new Set(paths.filter(Boolean))];
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

  const COLLAPSED_KEY = "docs-nav-collapsed";

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

  function getCollapsedIds() {
    try {
      const raw = sessionStorage.getItem(COLLAPSED_KEY);
      return new Set(raw ? JSON.parse(raw) : []);
    } catch (e) {
      return new Set();
    }
  }

  function setCollapsedIds(ids) {
    sessionStorage.setItem(COLLAPSED_KEY, JSON.stringify([...ids]));
  }

  function renderNav() {
    const host = document.getElementById("sidebar-nav");
    if (!host) return;

    const activePage = currentPageKey();
    const collapsedIds = getCollapsedIds();
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
                  return `<li><a class="${childActive.trim()}" href="${withBase(child.href)}" data-label="${child.label.toLowerCase()}">${child.label}</a></li>`;
                })
                .join("") +
              "</ul>";
          }
          return `<li><a class="${active.trim()}" href="${withBase(item.href)}" data-label="${item.label.toLowerCase()}">${item.label}</a>${sub}</li>`;
        })
        .join("");

      // Expand/collapse only from the section header. Submenu navigation must not
      // auto-collapse other sections. Active section stays open so the current page is visible.
      if (isActiveSection(section, activePage)) {
        collapsedIds.delete(section.id);
      }
      const shouldExpand = !collapsedIds.has(section.id);

      return `
        <div class="nav-section${shouldExpand ? "" : " collapsed"}" data-section="${section.id}">
          <button type="button" class="nav-toggle" aria-expanded="${shouldExpand ? "true" : "false"}">
            <span>${section.label}</span>
            <span class="chevron" aria-hidden="true">▼</span>
          </button>
          <ul class="nav-children">${childHtml}</ul>
        </div>`;
    }).join("");

    setCollapsedIds(collapsedIds);
    host.innerHTML = html;

    host.querySelectorAll(".nav-toggle").forEach((btn) => {
      btn.addEventListener("click", (event) => {
        event.preventDefault();
        event.stopPropagation();
        const section = btn.closest(".nav-section");
        const collapsed = section.classList.toggle("collapsed");
        btn.setAttribute("aria-expanded", collapsed ? "false" : "true");
        const ids = getCollapsedIds();
        const id = section.dataset.section;
        if (collapsed) ids.add(id);
        else ids.delete(id);
        setCollapsedIds(ids);
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
