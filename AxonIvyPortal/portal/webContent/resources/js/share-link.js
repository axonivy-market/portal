/**
 * Shared behaviour for the ShareLinkDialog component.
 * Every lookup is scoped to one widget, so several share links can live on the same page.
 */
var PortalShareLink = (function () {

  var lastAnchorId = null;

  function widget(widgetVar) {
    return (window.PrimeFaces && PrimeFaces.widgets[widgetVar]) || null;
  }

  function copyButton(shareWidget) {
    return shareWidget && shareWidget.jq ? shareWidget.jq.find("button[id$='share-link-copy-button']") : $();
  }

  function isClipboardSupported() {
    return !!(navigator.clipboard && navigator.clipboard.writeText);
  }

  document.documentElement.classList.toggle('js-clipboard-supported', isClipboardSupported());

  return {

    /**
     * Runs synchronously inside PF().show(), so it must never throw: an exception here would
     * escape the caller's onclick handler and let a trigger link follow its href.
     */
    onShow: function (widgetVar, focusCopyButton) {
      try {
        if (focusCopyButton) {
          copyButton(widget(widgetVar)).trigger('focus');
        }
      } catch (e) {
        // never break the component that is opening
      }
    },

    /**
     * Must not return false: p:commandButton cancels its ajax request on a false onclick,
     * which would swallow the "Link copied" growl.
     */
    copy: function (widgetVar, url) {
      if (!isClipboardSupported()) {
        return;
      }
      navigator.clipboard.writeText(url);
      var shareWidget = widget(widgetVar);
      if (shareWidget) {
        shareWidget.hide();
      }
    },

    /**
     * An overlay menu hides itself as soon as one of its items is clicked, so a menu item cannot
     * anchor the panel. Remember the menu's own trigger instead, while it is still on screen.
     */
    rememberAnchor: function (element) {
      lastAnchorId = element && element.id ? element.id : null;
    },

    showAtAnchor: function (widgetVar) {
      var shareWidget = widget(widgetVar);
      if (shareWidget && lastAnchorId && document.getElementById(lastAnchorId)) {
        shareWidget.show(lastAnchorId);
      }
    },

    toggle: function (widgetVar, source) {
      var shareWidget = widget(widgetVar);
      if (shareWidget) {
        shareWidget.isVisible() ? shareWidget.hide() : shareWidget.show(source);
      }
      return false;
    }
  };
})();
