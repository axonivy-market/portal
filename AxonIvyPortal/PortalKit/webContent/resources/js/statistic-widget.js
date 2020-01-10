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
    onClick : taskByExpiryChartClickEvent,
    
    plugins: {
      datalabels: {
        color: function(context) {
          let legendColor = context.chart.legend.options.labels.fontColor;
          var index = context.dataIndex;
          var value = context.dataset.data[index];
          return value <= 0 ? 'transparent' : legendColor;
          },
      }
    }
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
          let legendColor = context.chart.legend.options.labels.fontColor;
          var index = context.dataIndex;
          var value = context.dataset.data[index];
          return value <= 0 ? 'transparent' : legendColor;
          },
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

function donutExtender() {
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    hover : {
      onHover : function(event, activeElement) {
        event.target.style.cursor = activeElement[0] ? 'pointer' : 'default';
      }
    },

    plugins: {
        datalabels: {
          color: function(context) {
            let legendColor = context.chart.legend.options.labels.fontColor;
            var index = context.dataIndex;
            var value = context.dataset.data[index];
            return value <= 0 ? 'transparent' : legendColor;
            },

            formatter: function(value, context) {
              let sum = 0;
              let dataArr = context.dataset.data;
              for (var index = 0;index < dataArr.length; index++) {
                  sum += dataArr[index];
              }

              let percentage = (value*100 / sum).toFixed(2)+"%";
              return percentage;
            }
        }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
}

var toggleColumn = document.getElementsByClassName("elapsed-time-detail-toggle-column");
var hiddenColumn =  document.getElementsByClassName("js-elapsed-case");
var caseTable = document.getElementsByClassName("case-table");

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
		if($(hiddenColumn).hasClass("ui-hidden")) {
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
		$(column).addClass("ui-hidden");
	},
	
	displayColumnWhenResizeScreen: function(column) {
		$(column).removeClass("ui-hidden");
	}
}

