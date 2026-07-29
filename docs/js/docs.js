(function () {
  const NAV = [
    {
      id: "overview",
      label: "Overview",
      children: [
        { href: "index.html", label: "Introduction", id: "intro" },
        { href: "overview/project-structure.html", label: "Project structure", id: "structure" },
        { href: "overview/setup.html", label: "Maven setup", id: "setup" },
      ],
    },
    {
      id: "api",
      label: "API (JavaDoc style)",
      children: [
        {
          href: "api/RandomStringUtilsTest.html",
          label: "RandomStringUtilsTest",
          id: "api-class",
          children: [
            { href: "api/RandomStringUtilsTest.html#randomString", label: "randomString()", id: "api-randomString" },
          ],
        },
      ],
    },
    {
      id: "examples",
      label: "Code examples",
      children: [
        { href: "examples/random-string-utils.html", label: "RandomStringUtils", id: "ex-overview" },
        { href: "examples/random.html", label: "random()", id: "ex-random" },
        { href: "examples/random-alphabetic.html", label: "randomAlphabetic()", id: "ex-alpha" },
        { href: "examples/random-alphanumeric.html", label: "randomAlphanumeric()", id: "ex-alnum" },
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

  function currentPageKey() {
    const path = window.location.pathname.replace(/\\/g, "/");
    const marker = "/docs/";
    const idx = path.lastIndexOf(marker);
    if (idx >= 0) {
      return path.slice(idx + marker.length);
    }
    const parts = path.split("/");
    const docsIdx = parts.lastIndexOf("docs");
    if (docsIdx >= 0) {
      return parts.slice(docsIdx + 1).join("/");
    }
    return parts[parts.length - 1] || "index.html";
  }

  function isActive(href) {
    const page = currentPageKey();
    const cleanHref = href.split("#")[0];
    return page === cleanHref || page.endsWith("/" + cleanHref) || (page === "" && cleanHref === "index.html");
  }

  function renderNav() {
    const host = document.getElementById("sidebar-nav");
    if (!host) return;

    const activePage = currentPageKey();
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

      const shouldExpand =
        section.children.some((item) => isActive(item.href) || (item.children || []).some((c) => isActive(c.href))) ||
        activePage.startsWith(section.id + "/");

      return `
        <div class="nav-section${shouldExpand ? "" : " collapsed"}" data-section="${section.id}">
          <button type="button" class="nav-toggle" aria-expanded="${shouldExpand ? "true" : "false"}">
            <span>${section.label}</span>
            <span class="chevron" aria-hidden="true">▼</span>
          </button>
          <ul class="nav-children">${childHtml}</ul>
        </div>`;
    }).join("");

    host.innerHTML = html;

    host.querySelectorAll(".nav-toggle").forEach((btn) => {
      btn.addEventListener("click", () => {
        const section = btn.closest(".nav-section");
        const collapsed = section.classList.toggle("collapsed");
        btn.setAttribute("aria-expanded", collapsed ? "false" : "true");
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
