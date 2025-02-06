function CaseWidget() {

  return {
    setupHeader: function () {
      var caseListToolKit = CaseListToolKit();
      caseListToolKit.setupHeader();
    },

    toggleCaseFilter: function () {
      $('.js-advanced-filter-component').toggleClass('u-hidden-md-down');
      $('.js-filter-add-action').toggleClass('u-hidden-md-down');
      $('.js-filter-reset-action').toggleClass('u-hidden-md-down');
      $('.js-filter-save-action').toggleClass('u-hidden-md-down');
    },

    showCaseFilter: function () {
      $('.js-advanced-filter-component').removeClass('u-hidden-md-down');
      $('.js-filter-add-action').removeClass('u-hidden-md-down');
      $('.js-filter-reset-action').removeClass('u-hidden-md-down');
      $('.js-filter-save-action').removeClass('u-hidden-md-down');
    },

    setUpScrollbar: function () {
      let needShowScrollbar = false;
      var childElements = $('.js-case-item');
      if (childElements.length > 0) {
        var container = $('.js-case-list > .ui-datascroller-content');
        var caseWidgetHeaderContainer = $('.js-case-widget-header');

        // temporary hide mobile title to calculate
        var mobileTitle = caseWidgetHeaderContainer.find(".js-widget-title-mobile");
        if (mobileTitle.length > 0) {
          $(".js-layout-content").css("overflow-y", "hidden");
          mobileTitle.addClass("hidden");
        }

        var announcementMessageContainer = $('.js-announcement-message');
        var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
        var layoutContentMarginPadding = ($('.js-layout-content').outerHeight(true) - $('.js-layout-content').height()) || 0;
        var mainScreenHeight = PortalLayout.getAvailableHeight();

        // When open task list inside case information dialog, back link is displayed. So we should subtract its height also
        var backlinkHeight = $('.js-back-link').outerHeight(true) || 0;

        var availableHeight = mainScreenHeight - (caseWidgetHeaderContainer.outerHeight(true) || 0)
                              - (caseWidgetSortMenuContainer.outerHeight(true) || 0)
                              - (announcementMessageContainer.outerHeight(true) || 0) - layoutContentMarginPadding - backlinkHeight;

        if (PortalGlobalSearch.isSearchPageOpened()) {
          availableHeight = availableHeight - PortalGlobalSearch.getAvailableHeight(':task-tab');
        } else {
          needShowScrollbar = (childElements.length * $(childElements[0]).outerHeight(true) || 1) > availableHeight;
          if (needShowScrollbar && !isMobileDevices()) {
            PortalLayout.removeLayoutContentPaddingBottom();
            $('.js-case-default-widget-container').css('margin-right', '-' + PortalLayout.getPaddingRightLayoutContent());
          }
        }

        if (!!availableHeight) {
          container.outerHeight(availableHeight);
        }

        // show mobile title after calculate
        if (mobileTitle.length > 0) {
          mobileTitle.removeClass("hidden");
        }
      }
      if (!needShowScrollbar) {
        PortalLayout.removeJsStyleOnLayoutContent();
        // $('.js-case-list').removeAttr("style");
      }
    },

    updateCaseCountToBreadcrumb: function () {
      var $hiddenCaseCountElem = $(".js-hidden-case-count");
      // $hiddenCaseCountElem is not rendered if case count is disabled. So that do not update case count to breadcrumb.
      if ($hiddenCaseCountElem.length === 0) {
        return;
      }

      var $breadCrumbTaskElem = $("[id$=':breadcrumb'] li").last().find(".ui-menuitem-link");
      if ($breadCrumbTaskElem.length === 0) {
        return;
      }

      if ($breadCrumbTaskElem.find(".js-count").length === 0) {
        $breadCrumbTaskElem.find("span").addClass("has-count");
        $breadCrumbTaskElem.append('<span class="js-count has-count"> (' + $(".js-hidden-case-count").get(0).innerHTML + ')</span>');
      } else {
        $breadCrumbTaskElem.find(".js-count").get(0).innerHTML = " (" + $(".js-hidden-case-count").get(0).innerHTML + ")";
      }
      if ($(".js-case-count-mobile").length > 0) {
        $(".js-case-count-mobile").get(0).innerHTML = " (" + $(".js-hidden-case-count").get(0).innerHTML + ")";
      }
    }
  };
}

function CaseListToolKit() {
  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("hidden");
  }

  return {
    setupHeader: function () {
      var caseSortMenu = $('.js-case-widget-column-header');
      var caseEntry = $('.js-case-start-link').first();
      var noEntry = caseEntry.length === 0;
      this.showHideColumnWhenMenuToggle();
      if (noEntry) {
        $(caseSortMenu).hide();
      } else {
        $(caseSortMenu).show();
      }

      $.each(caseSortMenu.children('a, .js-unsortable-header-cell'), function (i, header) {
        var cell = $(caseEntry).children().get(i);
        $(header).outerWidth($(cell).outerWidth());
      });

    },

    setupScrollbar: function () {
      var caseWidget = new CaseWidget();
      caseWidget.setUpScrollbar();
    },

    showHideColumnWhenMenuToggle: function () {
      var $layout = $('.js-layout-wrapper');
      var remainingWidth = $('.js-layout-content').outerWidth(true);
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $layout.hasClass('layout-static')) {
        hideColumnWhenExpandMenu($hiddenColumns);
      } else {
        displayColumnWhenCollapseMenu($hiddenColumns);
      }
    },

    responsive: function () {
      this.setupScrollbar();
      this.setupHeader();
    }
  };
}