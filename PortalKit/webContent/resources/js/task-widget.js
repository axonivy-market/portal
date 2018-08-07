function TaskWidget(outerPanelId) {
  var items = $('#' + outerPanelId + ' .js-widget-header-menu li');
  $.each(items, function() {
    $(this).click(function() {
      if (!$(this).hasClass('js-ignore-selected-state')) {
        $(items).removeClass('is-selected');
        $(this).addClass('is-selected');
      }
    });
  });

  return {

    setupScrollbar : function() {

      var container = $('.js-task-start-list > .ui-datascroller-content');
      var childElements = $('.js-task-start-list-item');
      var scrollUpButton = $('.js-task-start-list-scroll-up');
      var scrollDownButton = $('.js-task-start-list-scroll-down');

      var taskListHeight = 0;
      var taskElementHeight = childElements.outerHeight(true);
      childElements.each(function() {
        taskListHeight += $(this).outerHeight(true);
      });

      var mainAreaPanel = $('#'+outerPanelId);
      var taskWidgetHeaderContainer = $('.js-task-widget-header-container');
      var widgetScrollButton = $('#' + outerPanelId + ' .js-widget-content-scroll-button');

      var availableHeight = mainAreaPanel.outerHeight() - taskWidgetHeaderContainer.outerHeight(true) - 2
          * widgetScrollButton.outerHeight(true) - 40;

      if (taskListHeight <= Math.max(availableHeight, 0)) {
        scrollUpButton.hide();
        scrollDownButton.hide();
        return;
      }

      scrollUpButton.show();
      scrollDownButton.show();

      container.height(availableHeight);

      container.perfectScrollbar({
        suppressScrollX : true
      });

      scrollUpButton.click(function() {
        container.scrollTop(container.scrollTop() - taskElementHeight);
      });
      scrollUpButton.show();

      scrollDownButton.click(function() {
        container.scrollTop(container.scrollTop() + taskElementHeight);
      });
      scrollDownButton.show();
    },

    hideScrollbar : function() {
      $('.js-task-start-list-scroll-up').hide();
      $('.js-task-start-list-scroll-down').hide();
    }

  }

}