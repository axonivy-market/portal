function CaseWidget(outerPanelId) {
  this.setUpScrollbar = function() {
    var childElements = $('.js-case-item');
    if (childElements.length > 0) {
      var container = $('.js-case-list > .ui-datascroller-content');
      var mainAreaPanel = $('#' + outerPanelId);
      var caseWidgetHeaderContainer = $('.widget-header');
      var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
      var caseWidgetAdvancedFilterContainer = $('.js-additional-filter-container');
      var error = 5;

      var availableHeight = mainAreaPanel.outerHeight() - caseWidgetHeaderContainer.outerHeight(true)
          - caseWidgetSortMenuContainer.outerHeight(true) - caseWidgetAdvancedFilterContainer.outerHeight(true) - error;

      if (!!availableHeight) {
        container.height(availableHeight);
      }
    }
  };

  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }
}

function CaseListToolKit() {
  
  return {
    setupHeader : function() {
      var caseSortMenu = $('.js-case-widget-column-header');
      var caseEntry = $('.js-case-item-header').first();
      var noEntry = caseEntry.length == 0;
      if (noEntry) {
        $(caseSortMenu).hide();
      } else {
        $(caseSortMenu).show();
      }
      $.each(caseSortMenu.children(), function(i, header) {
        var cell = $(caseEntry).children().get(i);
        $(header).outerWidth($(cell).outerWidth());
      });
    },
    
    responsiveInLargeScreen : function(){
      this.setupHeader();
    },
    
    responsiveInMediumScreen : function(){
      this.setupHeader();
    },
  
    responsiveInSmallScreen : function() {
      this.setupHeader();
      var caseWidget = new CaseWidget("main-area-panel");
      caseWidget.setUpScrollbar();
    },
    
    responsive : function() {
      if (viewPort.isMediumScreen()) {
        this.responsiveInMediumScreen();
      } else if (viewPort.isSmallScreen()) {
        this.responsiveInSmallScreen();
      } else {
        this.setupHeader();
      }
    }
  }
}