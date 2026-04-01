var SidebarClickMode = (function() {
  var CLS_LAYOUT_STATIC  = 'layout-static';
  var CLS_STATIC_RESTORE = 'layout-static-restore';
  var CLS_SIDEBAR_ACTIVE = 'layout-sidebar-active';

  var SEL_WRAPPER      = '.js-layout-wrapper';
  var SEL_MENU_WRAPPER = '.menu-wrapper.js-left-sidebar';
  var SEL_TOGGLE_LABEL = '.sidebar-toggle-label';
  var SEL_TOGGLE_BTN   = '.js-sidebar-toggle-btn';

  var FREYA_TRANSITION_MS = 250;
  var COOKIE_MENU_STATIC  = 'freya_menu_static';

  return {
    mode: 'HOVER',
    _isExpanded: false,
    _toggling: false,
    _observer: null,
    _initialized: false,

    init: function(sidebarBehaviour) {
      this.mode = sidebarBehaviour || 'HOVER';
      if (this.mode === 'CLICK') {
        this._toggling = false;
        this.observeAndBlockHover();
        if ($.cookie(COOKIE_MENU_STATIC)) {
          this._isExpanded = true;
          $(SEL_TOGGLE_LABEL).show();
          $(SEL_TOGGLE_BTN).attr('aria-expanded', 'true');
        } else {
          this._isExpanded = false;
          this.ensureCollapsed();
        }
        if (!this._initialized) {
          this.bindSubmenuAutoExpand();
          this._initialized = true;
        }
      }
    },

    observeAndBlockHover: function() {
      if (this._observer) {
        this._observer.disconnect();
      }
      var self = this;
      var $wrapper = $(SEL_WRAPPER);
      if ($wrapper.length === 0) return;
      this._observer = new MutationObserver(function () {
        if (self._toggling) return;
        if ($wrapper.hasClass(CLS_LAYOUT_STATIC) && !self._isExpanded) {
          $wrapper.removeClass(CLS_LAYOUT_STATIC + ' ' + CLS_STATIC_RESTORE);
        }
      });
      this._observer.observe($wrapper[0], { attributes: true, attributeFilter: ['class'] });
    },

    ensureCollapsed: function() {
      $(SEL_WRAPPER).removeClass(CLS_LAYOUT_STATIC + ' ' + CLS_STATIC_RESTORE);
      $(SEL_MENU_WRAPPER).removeClass(CLS_SIDEBAR_ACTIVE);
    },

    toggle: function() {
      if (this.mode !== 'CLICK') return;
      this._toggling = true;
      this._isExpanded = !this._isExpanded;
      var $layoutWrapper = $(SEL_WRAPPER);
      if (this._isExpanded) {
        $layoutWrapper.addClass(CLS_LAYOUT_STATIC);
        $(SEL_TOGGLE_LABEL).show();
        $(SEL_TOGGLE_BTN).attr('aria-expanded', 'true');
      } else {
        $layoutWrapper.removeClass(CLS_LAYOUT_STATIC);
        $(SEL_TOGGLE_LABEL).hide();
        $(SEL_TOGGLE_BTN).attr('aria-expanded', 'false');
      }
      var self = this;
      setTimeout(function() {
        try {
          Portal.updateBreadcrumb();
          Portal.updateLayoutContent();
        } finally {
          self._toggling = false;
        }
      }, FREYA_TRANSITION_MS);
    },

    bindSubmenuAutoExpand: function() {
      var self = this;
      $(document).off('click.sidebarSubmenu').on('click.sidebarSubmenu', '.layout-menu li > a', function () {
        if (self.mode !== 'CLICK' || self._isExpanded) return;
        var $li = $(this).closest('li');
        if ($li.find('> ul').length > 0) {
          self.toggle();
        }
      });
    }
  };
})();
