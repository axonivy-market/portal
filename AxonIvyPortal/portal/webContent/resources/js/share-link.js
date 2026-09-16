/**
 * Shared behaviour for the ShareLinkDialog component, in both its dialog and its overlay panel mode.
 * Every lookup is scoped to one widget, so several share links can live on the same page.
 */
var PortalShareLink = (function () {

  function widget(widgetVar) {
    return (window.PrimeFaces && PrimeFaces.widgets[widgetVar]) || null;
  }

  function copyButton(shareWidget) {
    return shareWidget && shareWidget.jq ? shareWidget.jq.find("button[id$='share-link-copy-button']") : $();
  }

  function isClipboardSupported() {
    return !!(navigator.clipboard && navigator.clipboard.writeText);
  }

  return {

    /**
     * Runs synchronously inside PF().show(), so it must never throw: an exception here would
     * escape the caller's onclick handler and let a trigger link follow its href.
     */
    onShow: function (widgetVar, focusCopyButton) {
      try {
        var button = copyButton(widget(widgetVar));
        if (!isClipboardSupported()) {
          button.hide();
          return;
        }
        button.show();
        if (focusCopyButton) {
          button.trigger('focus');
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

    show: function (widgetVar) {
      var shareWidget = widget(widgetVar);
      if (shareWidget) {
        shareWidget.show();
      }
      return false;
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
