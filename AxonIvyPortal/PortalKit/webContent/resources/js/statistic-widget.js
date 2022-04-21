var topValue;
var leftValue;

function getColor(colorName) {
  return getComputedStyle(document.documentElement).getPropertyValue(colorName);
}

var chartLabelDefaultColor = getColor('--ivy-primary-color-white');

var chartLabelDefaultExceptionPriorityColor = getColor('--chart-label-default-exception-priority-color');
var chartLabelDefaultHighPriorityColor = getColor('--chart-label-default-high-priority-color');
var chartLabelDefaultNormalPriorityColor = getColor('--chart-label-default-normal-priority-color');
var chartLabelDefaultlowPriorityColor = getColor('--chart-label-default-low-priority-color');

var chartLabelDefaultCreatedCaseColor = getColor('--chart-label-default-created-case-color');
var chartLabelDefaultRunningCaseColor = getColor('--chart-label-default-running-case-color');
var chartLabelDefaultDoneCaseColor = getColor('--chart-label-default-done-case-color');
var chartLabelDefaultFailedCaseColor = getColor('--chart-label-default-failed-case-color');

var chartLabelDefaultElapsedTimeColor = getColor('--chart-label-default-elapsed-time-color');

var chartLabelDefaultTaskExpiriedColor = getColor('--chart-label-default-task-expiried-color');
var chartLabelDefaultTaskTodayColor = getColor('--chart-label-default-task-today-color');
var chartLabelDefaultTaskWeekColor = getColor('--chart-label-default-task-week-color');
var chartLabelDefaultTaskMonthColor = getColor('--chart-label-default-task-month-color');
var chartLabelDefaultTaskYearColor = getColor('--chart-label-default-task-year-color');

function chartDataLabelsSingleColor(context, color) {
  var index = context.dataIndex;
  var value = context.dataset.data[index];
  return value <= 0 ? 'transparent' : color;
}

function chartDataLabelsMultipleColor(context, colors) {
  var index = context.dataIndex;
  var value = context.dataset.data[index];
  return value <= 0 ? 'transparent' : (index <= colors.length - 1 ? colors[index] : chartLabelDefaultColor);
}

function updateDrillDownPanelPosition(panel) {
  var widgetVar = panel.widgetVar;
  $('.' + widgetVar).css({
    'left' : leftValue,
    'top' : topValue,
    'position' : 'absolute'
  });
}

function taskByExpiryChartBaseExtender(context, datalabelsColor) {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, context.cfg.config.options);
  options = {
    hover : {
      onHover : function(event, activeElement) {
        event.target.style.cursor = activeElement[0] ? 'pointer' : 'default';
      }
    },
    onClick : taskByExpiryChartClickEvent,
    
    plugins: {
      datalabels: {
        color: datalabelsColor
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, context.cfg.config.options, options);
}

// EXPIRED_KEY, TODAY_EXPIRY_KEY, THIS_WEEK_EXPIRY_KEY, THIS_MONTH_EXPIRY_KEY, THIS_YEAR_EXPIRY_KEY: colors order/length must be the same as returned data order/length of generateDefaultExpiryModel in StatisticService.java
var taskByExpiryDefaultLabelsColors = [chartLabelDefaultTaskExpiriedColor, chartLabelDefaultTaskTodayColor, chartLabelDefaultTaskWeekColor, chartLabelDefaultTaskMonthColor, chartLabelDefaultTaskYearColor];

function taskByExpiryChartDefaultDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, taskByExpiryDefaultLabelsColors);
}

function taskByExpiryChartDefaultExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartDefaultDataLabelsColor);
}

function taskByExpiryChartTodayDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskTodayColor);
}

function taskByExpiryChartTodayExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartTodayDataLabelsColor);
}

function taskByExpiryChartThisWeekDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskWeekColor);
}

function taskByExpiryChartThisWeekExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisWeekDataLabelsColor);
}

function taskByExpiryChartThisMonthDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskMonthColor);
}

function taskByExpiryChartThisMonthExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisMonthDataLabelsColor);
}

function taskByExpiryChartThisYearDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskYearColor);
}

function taskByExpiryChartThisYearExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisYearDataLabelsColor);
}

function taskByExpiryChartClickEvent(event, activeElement) {
  var $expiryChartDrillDown = $('.js-expiry-chart-drill-down');
  if (activeElement[0]) {
    if (activeElement[0]._index === 0) {
      $expiryChartDrillDown.hide();
    } else {
      $expiryChartDrillDown.show();
    }
    var chartId = event.chart.canvas.id;
    
    var indexOfChart = chartId.lastIndexOf(":");
    var widgetVar = 'context-menu-' + chartId.substring(indexOfChart - 1, indexOfChart);
    PF(widgetVar).show();
    topValue = event.offsetY + 50;
    leftValue = event.offsetX;
  }
}

function elapsedTimeChartDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultElapsedTimeColor);
}

function elapsedTimeChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    scales : {
      xAxes : {
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
      },
      yAxes : {}
    },
    tooltips: {
      enabled: true,
      mode: 'index',
      intersect: false,
      callbacks: {
        title: function(tooltipItems, data) {
          var idx = tooltipItems[0].index;
          return data.labels[idx];
        }
      }
    },
    hover: {
      mode: 'index',
      intersect: false
   },
    plugins: {
      datalabels: {
        color: elapsedTimeChartDataLabelsColor
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

function donutChartLegendLabelsFilter(legendItem, data) {
  return data.datasets[0].data[legendItem.index] != 0;
}

function donutChartHoverEvent(event, activeElement) {
  event.target.style.cursor = activeElement[0] ? 'pointer' : 'default';
}

function donutChartDataLabelsFormatter(value, context) {
  let sum = 0;
  let dataArr = context.dataset.data;
  for (var index = 0;index < dataArr.length; index++) {
      sum += dataArr[index];
  }

  let percentage = (value*100 / sum).toFixed(2)+"%";
  return percentage;
}

function donutChartExtender(context, datalabelsColor) {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, context.cfg.config.options);
  options = {
    legend: {
      labels: {
        filter: donutChartLegendLabelsFilter
      }
    },

    hover : {
      onHover : donutChartHoverEvent
    },

    plugins: {
        datalabels: {
          color: datalabelsColor,

          formatter: donutChartDataLabelsFormatter
        }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, context.cfg.config.options, options);
}

// EXCEPTION, HIGH, NORMAL, LOW: colors order/length must be the same as returned data order/length of generateDataForTaskByPriorityChart in StatisticService.java
var taskByPriorityLabelsColors = [chartLabelDefaultExceptionPriorityColor, chartLabelDefaultHighPriorityColor, chartLabelDefaultNormalPriorityColor, chartLabelDefaultlowPriorityColor];

function taskByPriorityChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, taskByPriorityLabelsColors);
}

function taskByPriorityChartExtender() {
  donutChartExtender(this, taskByPriorityChartDataLabelsColor);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateLabelsColors = [chartLabelDefaultCreatedCaseColor, chartLabelDefaultRunningCaseColor, chartLabelDefaultDoneCaseColor, chartLabelDefaultFailedCaseColor];

function caseByStateChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, caseByStateLabelsColors);
}

function caseByStateChartExtender() {
  donutChartExtender(this, caseByStateChartDataLabelsColor);
}

var toggleColumn = document.getElementsByClassName("js-elapsed-time-detail-toggle-column");
var hiddenColumn =  document.getElementsByClassName("js-elapsed-case");
var caseTable = document.getElementsByClassName("js-case-table");

var elapsedTimeChartDetail = {

	setupHeader: function() {
		if(window.innerWidth < 1024) {
			this.hideColumnWhenResizeScreen(hiddenColumn);
			this.toggleIcon();
		} else {
			this.displayColumnWhenResizeScreen(hiddenColumn);
			this.toggleIcon();
		}
	},
	
	toggleColumn: function() {
		if($(hiddenColumn).hasClass("u-hidden")) {
			this.displayColumnWhenResizeScreen(hiddenColumn);
			this.toggleIcon();
			$(caseTable).css("min-width", "900px");
		} else {
			this.hideColumnWhenResizeScreen(hiddenColumn);
			this.toggleIcon();
			$(caseTable).css("min-width", "0");
		}
	},
	
	toggleIcon: function() {
		if($(toggleColumn).hasClass("fa fa-plus")) {
			$(toggleColumn).removeClass("fa fa-plus");
			$(toggleColumn).addClass("fa fa-minus");
		} else {
			$(toggleColumn).removeClass("fa fa-minus");
			$(toggleColumn).addClass("fa fa-plus");
		}
	},
	
	displayColumnWhenSort: function() {
		if(window.innerWidth < 1024) {
			this.displayColumnWhenResizeScreen(hiddenColumn);
			$(toggleColumn).removeClass("fa fa-plus");
			$(toggleColumn).addClass("fa fa-minus");
			$(caseTable).css("min-width", "900px");
		}
	},

	hideColumnWhenResizeScreen: function(column) {
		$(column).addClass("u-hidden");
	},
	
	displayColumnWhenResizeScreen: function(column) {
		$(column).removeClass("u-hidden");
	}
}