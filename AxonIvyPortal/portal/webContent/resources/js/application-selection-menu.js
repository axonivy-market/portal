function hideBreadcrumb() {
  if ($(".layout-static").length == 0) {
    $("#top-menu").find("> li.breadcrumb-container").css("display", "none");
  }
}

/**
 * This function handles the reverse process of Java's URL encoding of base64 strings.
 * Java side: URL.encode(Base64.encode(originalUrl)) 
 * JavaScript side: base64UrlDecode(encodedString) -> originalUrl
 * 
 * This is used for decoding URLs that are encoded in Java code to avoid special character conflicts in HTML attributes.
 */
function base64UrlDecode(str) {
  // Converts URL-safe characters back to standard base64 characters (- to +, _ to /)
  str = str.replace(/-/g, '+').replace(/_/g, '/');
  try {
    return atob(str);
  } catch (e) {
    console.warn("Failed to decode base64:", str, e);
    return null;
  }
}

/**
 * Patch href for menu items and logos using base64-encoded URLs.
 *
 * PrimeFaces <p:menuitem> supports either URL or AJAX, not both, 
 * and does not allow custom attributes (e.g. data-url).
 *
 * Approach:
 * 1. Java: encode target URL as Base64 in CSS class
 * 2. JS: decode class and set href dynamically
 *
 * Covers:
 * - Menu items (js-menu-url-*)
 * - Parent dashboard (js-parent-dashboard-url-*)
 * - Logos (js-logo-url-*)
 *
 * Story: IVYPORTAL-19031
 */
function updateMenuHref() {
  // Handle regular menu items with encoded URLs in class names
  $('[id^="user-menu-required-login:main-navigator:main-menu"]').find('li a[class*="js-menu-url-"]').each(function () {
    const $a = $(this);
    const urlClass = $a.attr('class').match(/js-menu-url-([^ ]+)/)[1];
    if (urlClass) {
      const menuUrl = base64UrlDecode(urlClass);
      if ($a.attr('href') !== menuUrl) {
        $a.attr('href', menuUrl);
      }
    }
  });

  // Handle parent dashboard menu items with special URL handling
  $('[id^="user-menu-required-login:main-navigator:main-menu"]').find('li[class*="js-parent-dashboard-url-"] > a').each(function () {
    const $submenuLink = $(this);
    const $parentLi = $submenuLink.closest('li[class*="js-parent-dashboard-url-"]');
    const urlClass = $parentLi.attr('class').match(/js-parent-dashboard-url-([^ ]+)/);

    if (urlClass && urlClass[1]) {
      const menuUrl = base64UrlDecode(urlClass[1]);
      if ($submenuLink.attr('href') !== menuUrl) {
        $submenuLink.attr('href', menuUrl);
      }
    }
  });

  // Handle logo links with encoded URLs
  $('a[class*="js-logo-url-"]').each(function () {
    const $a = $(this);
    const urlClass = $a.attr('class').match(/js-logo-url-([^ ]+)/);
    if (urlClass && urlClass[1]) {
      const logoUrl = base64UrlDecode(urlClass[1]);
      if ($a.attr('href') !== logoUrl) {
        $a.attr('href', logoUrl);
      }
    }
  });
}

// Initial update when page loads
updateMenuHref();
updateMainMenuAriaLabel();

// Reapply update after each PrimeFaces AJAX update to ensure URLs remain correct
// This is necessary because PrimeFaces AJAX updates may re-render menu components
// which would lose the client-side href updates
$(document).on("pfAjaxComplete", updateMenuHref);