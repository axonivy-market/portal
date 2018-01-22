function CaseWidget(outerPanelId) {
  this.setUpScrollbar = function() {

    var container = $('.js-case-list > .ui-datascroller-content');
    var mainAreaPanel = $("#" + outerPanelId);
    var widgetHeaderContainer = $('.js-widget-header');
    var caseWidgetAdvancedFilterContainer = $('.js-additional-filter-container');
    var availableHeight = mainAreaPanel.outerHeight() -
    caseWidgetAdvancedFilterContainer.outerHeight(true) - widgetHeaderContainer.outerHeight(true) - 80;

    if (container.height() > availableHeight) {
      container.height(availableHeight);
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
      var caseWidget = new CaseWidget("");
      caseWidget.setupHeader();
    },
    
    responsiveInMediumScreen : function(){
      var caseWidget = new CaseWidget("");
      caseWidget.setupHeader();
    },
  
    responsiveInSmallScreen : function() {
      var caseWidget = new CaseWidget("");
      caseWidget.setupHeader();
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