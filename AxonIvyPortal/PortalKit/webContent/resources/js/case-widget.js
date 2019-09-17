function CaseWidget() {
  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }
  
  this.toggleTaskFilter = function(toggler) {
    $('.js-filter-container').toggleClass('u-hidden-md-down');
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

      var mainScreenHeight = $('body').outerHeight() - $('.layout-topbar').outerHeight() - 20; //exclude margin
      var availableHeight = mainScreenHeight - caseWidgetHeaderContainer.outerHeight(true)
          - caseWidgetSortMenuContainer.outerHeight(true) - caseWidgetFilterContainer.outerHeight(true)
          - globalSearchInput.outerHeight(true) - globalSearchTabHeader.outerHeight(true)
          - announcementMessageContainer.outerHeight(true) - error;

      if (!!availableHeight) {
        container.height(availableHeight);
      }
    }
  };
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
      var caseEntry = $('.js-case-item-header').first();
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
      var $mainMenu = $('.js-left-sidebar');
      var remainingWidth = $('body').width() - $mainMenu.outerWidth() - 75;//exclude padding and scroll bar
      var $hiddenColumns = $('.js-hidden-when-expand-menu');
      if (remainingWidth < 1024 && $mainMenu.hasClass('in')) {
        hideColumnWhenExpandMenu($hiddenColumns);
      } else {
        displayColumnWhenCollapseMenu($hiddenColumns);
      }
    },
    
    responsive : function() {
        this.setupHeader();
        this.setupScrollbar();
    }
  }
}