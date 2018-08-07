function CaseWidget(outerPanelId) {
  this.setUpScrollbar = function() {

    var container = $('.js-case-list > .ui-datascroller-content');
    var mainAreaPanel = $("#" + outerPanelId);
    var widgetHeaderContainer = $('.js-widget-header');
    var availableHeight = mainAreaPanel.outerHeight() - widgetHeaderContainer.outerHeight(true) - 70;

    if (container.height() > availableHeight) {
      container.height(availableHeight);
    }
  };

  this.setupHeader = function() {
    var taskSortMenu = $('.js-case-widget-column-header');
    var taskEntry = $('.js-case-item-header').first();
    var noEntry = taskEntry.length == 0;
    if (noEntry) {
      $(taskSortMenu).hide();
    } else {
      $(taskSortMenu).show();
    }
    $.each(taskSortMenu.children(), function(i, header) {
      var cell = $(taskEntry).children().get(i);
      $(header).outerWidth($(cell).outerWidth());
    });
  }
}

function CaseListToolKit() {
  
  return {
    responsiveInMediumScreen : function(){
      var caseWidget = new CaseWidget("");
      caseWidget.setupHeader();
    },
  
    responsiveInSmallScreen : function() {
      var caseWidget = new CaseWidget("");
      caseWidget.setupHeader();
    }
  }
}