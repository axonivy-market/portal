function switchProcessWidgetToCompactMode() {
  var processWidget = $('.js-process-widget', '.js-widget-container');
  switchMode(processWidget, 'COMPACT');
}

function switchProcessWidgetToExpandedMode() {
  var processWidget = $('.js-process-widget', '.js-widget-container');
  switchMode(processWidget, 'EXPANDED');
}

function switchTaskWidgetToCompactMode() {
  var taskWidget = $('.js-task-widget', '.js-widget-container');
  switchMode(taskWidget, 'COMPACT');
}

function switchTaskWidgetToExpandedMode() {
  var taskWidget = $('.js-task-widget', '.js-widget-container');
  switchMode(taskWidget, 'EXPANDED');
}

function switchStatisticWidgetToExpandedMode() {
  var statisticWidget = $('.js-statistic-widget', '.js-widget-container');
  switchMode(statisticWidget, 'EXPANDED');
}

function switchStatisticWidgetToCompactMode() {
  var statisticWidget = $('.js-statistic-widget', '.js-widget-container');
  switchMode(statisticWidget, 'COMPACT');
}

function switchMode(widget, mode) {
  var otherWidgets = $(widget).siblings();
  if ('COMPACT' == mode) {
    $(otherWidgets).show();
    $('.js-compact-mode-element', widget).show();
    $('.js-process-widget-live-search-input').hide();

  } else {
    $(otherWidgets).hide();
    $('.js-compact-mode-element', widget).hide();
    $('.js-process-widget-live-search-input').show();
  }
}
