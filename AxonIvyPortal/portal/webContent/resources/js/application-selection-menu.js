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
 * Updates href attributes for menu items and logos with base64 encoded URLs
 * 
 * This function solves a limitation of PrimeFaces menu components which support
 * either URL navigation OR AJAX actions, but not both simultaneously.
 * 
 * Solution:
 * 1. Java side: Build menu items with AJAX actions for normal left-click behavior
 * 2. Java side: Store the target URLs as base64-encoded values in CSS class names
 * 3. JavaScript side: Decode these URLs and set them as href attributes
 * 
 * This hybrid approach provides:
 * - Left click: AJAX action (normal behavior)
 * - Right click: "Open in new tab" works correctly with the href URL
 * 
 * The function dynamically updates href attributes for:
 * 1. Menu items with js-menu-url-* classes
 * 2. Parent dashboard menu items with js-parent-dashboard-url-* classes  
 * 3. Logo links with js-logo-url-* classes
 * 
 * URLs are base64 URL-encoded in CSS class names to avoid conflicts with 
 * special characters that could break HTML attributes.
 * 
 * Story ID: IVYPORTAL-19031
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