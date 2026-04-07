var SidebarClickMode = (function () {
  const CLS_LAYOUT_STATIC = 'layout-static';
  const CLS_STATIC_RESTORE = 'layout-static-restore';
  const CLS_SIDEBAR_ACTIVE = 'layout-sidebar-active';

  const SEL_WRAPPER = '.js-layout-wrapper';
  const SEL_MENU_WRAPPER = '.menu-wrapper.js-left-sidebar';
  const SEL_TOGGLE_BTN = '.js-sidebar-toggle-btn';
  const SEL_TOGGLE_ICON = '.js-sidebar-toggle-icon';
  const CLS_ICON_EXPAND = 'si-arrow-right-1';
  const CLS_ICON_COLLAPSE = 'si-arrow-left-1';

  const FREYA_TRANSITION_MS = 250;
  const COOKIE_MENU_STATIC = 'freya_menu_static';

  return {
    mode: 'HOVER',
    _isExpanded: false,
    _toggling: false,
    _observer: null,
    _initialized: false,

    init: function (sidebarMode) {
      this.mode = sidebarMode || 'HOVER';
      if (this.mode === 'CLICK') {
        this._toggling = false;
        this.observeAndBlockHover();
        if ($.cookie(COOKIE_MENU_STATIC)) {
          this._isExpanded = true;
          $(SEL_TOGGLE_BTN).attr('aria-expanded', 'true');
          $(SEL_TOGGLE_ICON).removeClass(CLS_ICON_EXPAND).addClass(CLS_ICON_COLLAPSE);
        } else {
          this._isExpanded = false;
          this.ensureCollapsed();
          $(SEL_TOGGLE_ICON).removeClass(CLS_ICON_COLLAPSE).addClass(CLS_ICON_EXPAND);
        }
        if (!this._initialized) {
          this.bindSidebarClick();
          this._initialized = true;
        }
      }
    },

    observeAndBlockHover: function () {
      if (this._observer) {
        this._observer.disconnect();
      }
      var self = this;
      var $wrapper = $(SEL_WRAPPER);
      if ($wrapper.length === 0) {
        return;
      }
      this._observer = new MutationObserver(function () {
        if (self._toggling) {
          return;
        }
        if ($wrapper.hasClass(CLS_LAYOUT_STATIC) && !self._isExpanded) {
          $wrapper.removeClass(CLS_LAYOUT_STATIC + ' ' + CLS_STATIC_RESTORE);
        }
      });
      this._observer.observe($wrapper[0], { attributes: true, attributeFilter: ['class'] });
    },

    ensureCollapsed: function () {
      $(SEL_WRAPPER).removeClass(CLS_LAYOUT_STATIC + ' ' + CLS_STATIC_RESTORE);
      $(SEL_MENU_WRAPPER).removeClass(CLS_SIDEBAR_ACTIVE);
    },

    toggle: function () {
      if (this.mode !== 'CLICK') {
        return;
      }
      this._toggling = true;
      this._isExpanded = !this._isExpanded;
      var $layoutWrapper = $(SEL_WRAPPER);
      if (this._isExpanded) {
        $layoutWrapper.addClass(CLS_LAYOUT_STATIC);
        $(SEL_TOGGLE_BTN).attr('aria-expanded', 'true');
        $(SEL_TOGGLE_ICON).removeClass(CLS_ICON_EXPAND).addClass(CLS_ICON_COLLAPSE);
      } else {
        $layoutWrapper.removeClass(CLS_LAYOUT_STATIC);
        $(SEL_TOGGLE_BTN).attr('aria-expanded', 'false');
        $(SEL_TOGGLE_ICON).removeClass(CLS_ICON_COLLAPSE).addClass(CLS_ICON_EXPAND);
      }
      var self = this;
      setTimeout(function () {
        try {
          Portal.updateBreadcrumb();
          Portal.updateLayoutContent();
        } finally {
          self._toggling = false;
        }
      }, FREYA_TRANSITION_MS);
    },

    bindSidebarClick: function () {
      var self = this;
      $(document).on('click.sidebarExpand', SEL_MENU_WRAPPER, function (e) {
        if (self.mode !== 'CLICK' || self._isExpanded) {
          return;
        }
        var $target = $(e.target).closest('a');
        if ($target.hasClass('js-sidebar-toggle-btn')) {
          return;
        }
        if ($target.length > 0 && $target.closest('li').find('> ul').length === 0) {
          return;
        }
        self.toggle();
      });
      $(document).on('click.sidebarToggleBtn', '.js-sidebar-toggle-btn', function () {
        if (self.mode !== 'CLICK') {
          return;
        }
        self.toggle();
      });
    }
  };
})();
