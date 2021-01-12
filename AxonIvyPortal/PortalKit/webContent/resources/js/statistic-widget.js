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

var taskByExpiryLabelsColor = "#FFFFFF";

function taskByExpiryChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    hover : {
      onHover : function(event, activeElement) {
        event.target.style.cursor = activeElement[0] ? 'pointer' : 'default';
      }
    },
    onClick : taskByExpiryChartClickEvent,
    
    plugins: {
      datalabels: {
        color: function(context) {
          var index = context.dataIndex;
          var value = context.dataset.data[index];
          return value <= 0 ? 'transparent' : taskByExpiryLabelsColor;
          },
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

function taskByExpiryChartClickEvent(event, activeElement) {
  var $expiryChartDrillDown = $('.js-expiry-chart-drill-down');
  if (activeElement[0]) {
    if (activeElement[0]._index === 0) {
      $expiryChartDrillDown.hide();
    } else {
      $expiryChartDrillDown.show();
    }
    var chartId = '';
    if (event.path) {
      chartId = $(event.path[0]).attr('id');
    } else {
      chartId = $(event.target.parentNode).attr('id');
    }
    var indexOfChart = chartId.lastIndexOf(":");
    var widgetVar = 'context-menu-' + chartId.substring(indexOfChart - 1, indexOfChart);
    PF(widgetVar).show();
    topValue = event.offsetY + 50;
    leftValue = event.offsetX;
  }
}

var elapsedTimeLabelsColor = "#FFFFFF";

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
        color: function(context) {
          var index = context.dataIndex;
          var value = context.dataset.data[index];
          return value <= 0 ? 'transparent' : elapsedTimeLabelsColor;
          },
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

// EXCEPTION, HIGH, NORMAL, LOW: colors order/length must be the same as returned data order/length of generateDataForTaskByPriorityChart in StatisticService.java
var taskByPriorityLabelsColor = ["#FFFFFF", "#FFFFFF", "#FFFFFF", "#565656"];

function taskByPriorityChartDataLabelsColor(context) {
  var index = context.dataIndex;
  var value = context.dataset.data[index];
  return value <= 0 ? 'transparent' : taskByPriorityLabelsColor[index];
}

function taskByPriorityChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
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
          color: taskByPriorityChartDataLabelsColor,

          formatter: donutChartDataLabelsFormatter
        }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateLabelsColor = ["#565656", "#FFFFFF", "#FFFFFF", "#FFFFFF"];

function caseByStateChartDataLabelsColor(context) {
  var index = context.dataIndex;
  var value = context.dataset.data[index];
  return value <= 0 ? 'transparent' : caseByStateLabelsColor[index];
}

function caseByStateChartExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
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
          color: caseByStateChartDataLabelsColor,

          formatter: donutChartDataLabelsFormatter
        }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
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