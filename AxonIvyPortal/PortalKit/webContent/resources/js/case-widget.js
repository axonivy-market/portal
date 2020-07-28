function CaseWidget() {
  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }
  
  this.toggleCaseFilter = function(toggler) {
    $('.js-advanced-filter-component').toggleClass('u-hidden-md-down');
    $('.js-filter-add-action').toggleClass('u-hidden-md-down');
    $('.js-filter-reset-action').toggleClass('u-hidden-md-down');
    $('.js-filter-save-action').toggleClass('u-hidden-md-down');
  }
  
  this.setUpScrollbar = function() {
    var childElements = $('.js-case-item');
    if (childElements.length > 0) {
      var container = $('.js-case-list > .ui-datascroller-content');
      var caseWidgetHeaderContainer = $('.widget-header');

      // temporary hide mobile title to calculate
      var mobileTitle = caseWidgetHeaderContainer.find(".js-widget-title-mobile");
      if (mobileTitle.length > 0) {
        mobileTitle.addClass("u-hidden");
      }

      var announcementMessageContainer = $('.js-announcement-message');
      var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
      var caseWidgetFilterContainer = $('.js-filter-container');
      var error = 5;
      var globalSearchInput = $('.js-global-search');
      var globalSearchTabHeader = $('.ui-tabs-nav');
      if (globalSearchTabHeader.length > 0) {
        error = 55; // included margin, padding in search page
      }

      var layoutContentTopDistance = ($('.js-layout-content').outerHeight(true) - $('.js-layout-content').height())||0;
      var mainScreenHeight = ($('.js-layout-content').outerHeight(true)||0);
      var availableHeight = mainScreenHeight - (caseWidgetHeaderContainer.outerHeight(true)||0) - (caseWidgetSortMenuContainer.outerHeight(true)||0)
          - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0) - (globalSearchTabHeader.outerHeight(true)||0)
          - (announcementMessageContainer.outerHeight(true)||0) - error - layoutContentTopDistance;

      if (!!availableHeight) {
        container.height(availableHeight);
      }

      // show mobile title after calculate
      if (mobileTitle.length > 0) {
        mobileTitle.removeClass("u-hidden");
      }
    }
  };

  this.updateCaseCountToBreadcrumb = function() {
      var $breadCrumbTaskElem = $("[id $= ':breadcrumb'] li").last().find(".ui-menuitem-link");
      if ($breadCrumbTaskElem.length == 0) {
        return;
      }

      if ($breadCrumbTaskElem.find(".js-count").length == 0) {
        $breadCrumbTaskElem.find("span").addClass("has-count");
        $breadCrumbTaskElem.append('<span class="js-count has-count"> (' + $(".js-hidden-case-count").get(0).innerHTML + ')</span>');
      } else {
        $breadCrumbTaskElem.find(".js-count").get(0).innerHTML = " (" + $(".js-hidden-case-count").get(0).innerHTML + ")";
      }
      if ($(".js-case-count-mobile").length > 0) {
        $(".js-case-count-mobile").get(0).innerHTML = " (" + $(".js-hidden-case-count").get(0).innerHTML + ")";
      }
    }
}

function CaseListToolKit() {
  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("u-hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("u-hidden");
  }
  
  return {
    setupHeader : function() {
      var caseSortMenu = $('.js-case-widget-column-header');
      var caseEntry = $('.js-case-start-link').first();
      var noEntry = caseEntry.length == 0;
      this.showHideColumnWhenMenuToggle();
      if (noEntry) {
        $(caseSortMenu).hide();
      } else {
        $(caseSortMenu).show();
      }
      
      $.each(caseSortMenu.children('a'), function(i, header) {
        var cell = $(caseEntry).children().get(i);
        $(header).outerWidth($(cell).outerWidth());
      });
     
    },
    
    setupScrollbar : function() {
      var caseWidget = new CaseWidget();
      caseWidget.setUpScrollbar();
    },
    
    showHideColumnWhenMenuToggle: function() {
      var $layout = $('.js-layout-wrapper');
      var $mainMenu = $('.js-left-sidebar');
      var remainingWidth = $('body').width() - $mainMenu.outerWidth() - 75;//exclude padding and scroll bar
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $layout.hasClass('layout-wrapper-static')) {
        hideColumnWhenExpandMenu($hiddenColumns);
      } else {
        displayColumnWhenCollapseMenu($hiddenColumns);
      }
    },
    
    responsive : function() {
        this.setupScrollbar();
        this.setupHeader();
    }
  }
}