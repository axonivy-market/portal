var topValue;
var leftValue;

function updateDrillDownPanelPosition(panel) {
  var widgetVar = panel.widgetVar;
  $('.' + widgetVar).css({
    'left' : leftValue,
    'top' : topValue,
    'position' : 'absolute'
  });
}

function taskByExpiryChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    hover : {
      onHover : function(event, activeElement) {
        event.target.style.cursor = activeElement[0] ? 'pointer' : 'default';
      }
    },
    onClick : taskByExpiryChartClickEvent
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

function taskByExpiryChartClickEvent(event, activeElement) {
  var $expiryChartDrillDown = $('.js-expiry-chart-drill-down');
  var $expiryChartTaskList = $('.js-expiry-chart-task-list');
  if (activeElement[0]) {
    if (activeElement[0]._index === 0) {
      $expiryChartDrillDown.hide();
    } else {
      $expiryChartDrillDown.show();
    }
    var chartId = $(event.path[0]).attr('id');
    var indexOfChart = chartId.lastIndexOf(":");
    var widgetVar = 'context-menu-' + chartId.substring(indexOfChart - 1, indexOfChart);
    PF(widgetVar).show();
    topValue = event.offsetY + 50;
    leftValue = event.offsetX;
  }
}

function elapsedTimeChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    scales : {
      xAxes : [{
        ticks : {
          maxTicksLimit: 5,
          callback : function(value) {
            if (value.length > 15) {
              return value.substr(0, 15) + '...';
            } else {
              return value;
            }
          },
        }
      }],
      yAxes : [{}]
    },
    tooltips: {
      enabled: true,
      mode: 'label',
      callbacks: {
        title: function(tooltipItems, data) {
          var idx = tooltipItems[0].index;
          return data.labels[idx];
        }
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}
