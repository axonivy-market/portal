function CaseWidget(outerPanelId) {
  this.setUpScrollbar = function() {

    var container = $('.js-case-list > .ui-datascroller-content');
    var mainAreaPanel = $("#" + outerPanelId);
    var widgetHeaderContainer = $('.js-widget-header');
    var availableHeight = mainAreaPanel.outerHeight() - widgetHeaderContainer.outerHeight(true) - 70;

    if (container.height() > availableHeight) {
      container.height(availableHeight);
      container.perfectScrollbar({
        suppressScrollX : true
      });
    }
  };
}