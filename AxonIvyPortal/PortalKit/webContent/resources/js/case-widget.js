function CaseWidget() {
  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }
  
  this.toggleTaskFilter = function(toggler) {
	  $('.advanced-filter-component').toggleClass('u-hidden-md-down');
  }
  
  this.setUpScrollbar = function() {
    var childElements = $('.js-case-item');
    if (childElements.length > 0) {
      var container = $('.js-case-list > .ui-datascroller-content');
      var caseWidgetHeaderContainer = $('.widget-header');
      var announcementMessageContainer = $('.js-announcement-message');
      var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
      var caseWidgetFilterContainer = $('.js-filter-container');
      var error = 5;
      var globalSearchInput = $('.js-global-search');
      var globalSearchTabHeader = $('.ui-tabs-nav');
      if (globalSearchTabHeader.length > 0) {
        error = 55; // included margin, padding in search page
      }

      var headerHeight = $('#portal-template-header').outerHeight()||0;
      var footerHeight = $('#portal-template-footer').outerHeight()||0;
      var headerFooterHeight = headerHeight + footerHeight;
      var envHeight = $('.js-layout-wrapper').hasClass('u-invisibility') ? $('#portal-environment').outerHeight()||0 : 0;

      var layoutContentTopDistance = ($('.layout-content').outerHeight(true) - $('.layout-content').height())||0;
      var mainScreenHeight = ($('.js-layout-content').outerHeight(true)||0);
      var availableHeight = mainScreenHeight - (caseWidgetHeaderContainer.outerHeight(true)||0) - (caseWidgetSortMenuContainer.outerHeight(true)||0)
          - (globalSearchInput.is(":visible") ? globalSearchInput.outerHeight(true) : 0) - (globalSearchTabHeader.outerHeight(true)||0)
          - (announcementMessageContainer.outerHeight(true)||0) - error - layoutContentTopDistance - headerFooterHeight - envHeight;

      if (!!availableHeight) {
        container.height(availableHeight);
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
        $breadCrumbTaskElem.append('<span class="js-count has-count"> (' + $(".js-case-count-hidden").get(0).innerHTML + ')</span>');
      } else {
        $breadCrumbTaskElem.find(".js-count").get(0).innerHTML = " (" + $(".js-case-count-hidden").get(0).innerHTML + ")";
      }
      $(".js-case-count-mobile").get(0).innerHTML = " (" + $(".js-case-count-hidden").get(0).innerHTML + ")";
    }
}

function CaseListToolKit() {
  function hideColumnWhenExpandMenu($columns) {
    $columns.addClass("ui-hidden");
  }

  function displayColumnWhenCollapseMenu($columns) {
    $columns.removeClass("ui-hidden");
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