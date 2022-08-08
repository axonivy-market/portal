var topValue;
var leftValue;

function getColor(colorName) {
  return getComputedStyle(document.documentElement).getPropertyValue(colorName);
}

var chartLabelDefaultColor = getColor('--ivy-primary-color-white');

// Statistic chart Task by priority
var chartLabelDefaultExceptionPriorityColor = getColor('--chart-label-default-exception-priority-color');
var chartLabelDefaultHighPriorityColor = getColor('--chart-label-default-high-priority-color');
var chartLabelDefaultNormalPriorityColor = getColor('--chart-label-default-normal-priority-color');
var chartLabelDefaultlowPriorityColor = getColor('--chart-label-default-low-priority-color');

var chartDefaultExceptionPriorityColor = getColor('--chart-default-exception-priority-color');
var chartDefaultHighPriorityColor = getColor('--chart-default-high-priority-color');
var chartDefaultNormalPriorityColor = getColor('--chart-default-normal-priority-color');
var chartDefaultlowPriorityColor = getColor('--chart-default-low-priority-color');

// Statistic chart Case by state
var chartLabelDefaultCreatedCaseColor = getColor('--chart-label-default-created-case-color');
var chartLabelDefaultRunningCaseColor = getColor('--chart-label-default-running-case-color');
var chartLabelDefaultDoneCaseColor = getColor('--chart-label-default-done-case-color');
var chartLabelDefaultFailedCaseColor = getColor('--chart-label-default-failed-case-color');

var chartDefaultCreatedCaseColor = getColor('--chart-default-created-case-color');
var chartDefaultRunningCaseColor = getColor('--chart-default-running-case-color');
var chartDefaultDoneCaseColor = getColor('--chart-default-done-case-color');
var chartDefaultFailedCaseColor = getColor('--chart-default-failed-case-color');

// Statistic chart Case by state finish task
var chartLabelDefaultCreatedCaseFinishTaskColor = getColor('--chart-label-default-created-case-finish-task-color');
var chartLabelDefaultRunningCaseFinishTaskColor = getColor('--chart-label-default-running-case-finish-task-color');
var chartLabelDefaultDoneCaseFinishTaskColor = getColor('--chart-label-default-done-case-finish-task-color');
var chartLabelDefaultFailedCaseFinishTaskColor = getColor('--chart-label-default-failed-case-finish-task-color');

var chartDefaultCreatedCaseFinishTaskColor = getColor('--chart-default-created-case-finish-task-color');
var chartDefaultRunningCaseFinishTaskColor = getColor('--chart-default-running-case-finish-task-color');
var chartDefaultDoneCaseFinishTaskColor = getColor('--chart-default-done-case-finish-task-color');
var chartDefaultFailedCaseFinishTaskColor = getColor('--chart-default-failed-case-finish-task-color');

// Statistic chart Case by state finish time
var chartLabelDefaultCreatedCaseFinishTimeColor = getColor('--chart-label-default-created-case-finish-time-color');
var chartLabelDefaultRunningCaseFinishTimeColor = getColor('--chart-label-default-running-case-finish-time-color');
var chartLabelDefaultDoneCaseFinishTimeColor = getColor('--chart-label-default-done-case-finish-time-color');
var chartLabelDefaultFailedCaseFinishTimeColor = getColor('--chart-label-default-failed-case-finish-time-color');

var chartDefaultCreatedCaseFinishTimeColor = getColor('--chart-default-created-case-finish-time-color');
var chartDefaultRunningCaseFinishTimeColor = getColor('--chart-default-running-case-finish-time-color');
var chartDefaultDoneCaseFinishTimeColor = getColor('--chart-default-done-case-finish-time-color');
var chartDefaultFailedCaseFinishTimeColor = getColor('--chart-default-failed-case-finish-time-color');

// Statistic chart Elapsed time of done case
var chartLabelDefaultElapsedTimeColor = getColor('--chart-label-default-elapsed-time-color');

var chartDefaultElapsedTimeColor = getColor('--chart-default-elapsed-time-color');

// Statistic chart Task by expiry
var chartLabelDefaultTaskExpiriedColor = getColor('--chart-label-default-task-expiried-color');
var chartLabelDefaultTaskTodayColor = getColor('--chart-label-default-task-today-color');
var chartLabelDefaultTaskWeekColor = getColor('--chart-label-default-task-week-color');
var chartLabelDefaultTaskMonthColor = getColor('--chart-label-default-task-month-color');
var chartLabelDefaultTaskYearColor = getColor('--chart-label-default-task-year-color');

var chartDefaultTaskExpiriedColor = getColor('--chart-default-task-expiried-color');
var chartDefaultTaskTodayColor = getColor('--chart-default-task-today-color');
var chartDefaultTaskWeekColor = getColor('--chart-default-task-week-color');
var chartDefaultTaskMonthColor = getColor('--chart-default-task-month-color');
var chartDefaultTaskYearColor = getColor('--chart-default-task-year-color');

// For Task's expire in day
var chartDefaultTaskTodayBefore8Color = getColor('--chart-default-task-expiried-today-before-8-color');
var chartDefaultTaskToday8Color = getColor('--chart-default-task-expiried-today-8-color');
var chartDefaultTaskToday9Color = getColor('--chart-default-task-expiried-today-9-color');
var chartDefaultTaskToday10Color = getColor('--chart-default-task-expiried-today-10-color');
var chartDefaultTaskToday11Color = getColor('--chart-default-task-expiried-today-11-color');
var chartDefaultTaskToday12Color = getColor('--chart-default-task-expiried-today-12-color');
var chartDefaultTaskToday13Color = getColor('--chart-default-task-expiried-today-13-color');
var chartDefaultTaskToday14Color = getColor('--chart-default-task-expiried-today-14-color');
var chartDefaultTaskToday15Color = getColor('--chart-default-task-expiried-today-15-color');
var chartDefaultTaskToday16Color = getColor('--chart-default-task-expiried-today-16-color');
var chartDefaultTaskToday17Color = getColor('--chart-default-task-expiried-today-17-color');
var chartDefaultTaskToday18Color = getColor('--chart-default-task-expiried-today-18-color');
var chartDefaultTaskTodayAfter18Color = getColor('--chart-default-task-expiried-today-after-18-color');

// For Task's expire in week
var chartDefaultTaskMonColor = getColor('--chart-default-task-expiried-mon-color');
var chartDefaultTaskTueColor = getColor('--chart-default-task-expiried-tue-color');
var chartDefaultTaskWedColor = getColor('--chart-default-task-expiried-wed-color');
var chartDefaultTaskThuColor = getColor('--chart-default-task-expiried-thu-color');
var chartDefaultTaskFriColor = getColor('--chart-default-task-expiried-fri-color');
var chartDefaultTaskSatColor = getColor('--chart-default-task-expiried-sat-color');
var chartDefaultTaskSunColor = getColor('--chart-default-task-expiried-sun-color');

// For Task's expire in month
var chartDefaultTaskFirstWeekColor = getColor('--chart-default-task-expiried-first-week-color');
var chartDefaultTaskSecondWeekColor = getColor('--chart-default-task-expiried-second-week-color');
var chartDefaultTaskThirdWeekColor = getColor('--chart-default-task-expiried-third-week-color');
var chartDefaultTaskFourthWeekColor = getColor('--chart-default-task-expiried-fourth-week-color');
var chartDefaultTaskFifthWeekColor = getColor('--chart-default-task-expiried-fifth-week-color');

// For Task's expire in year
var chartDefaultTaskJanColor = getColor('--chart-default-task-expiried-jan-color');
var chartDefaultTaskFebColor = getColor('--chart-default-task-expiried-feb-color');
var chartDefaultTaskMarColor = getColor('--chart-default-task-expiried-mar-color');
var chartDefaultTaskAprColor = getColor('--chart-default-task-expiried-apr-color');
var chartDefaultTaskMayColor = getColor('--chart-default-task-expiried-may-color');
var chartDefaultTaskJuneColor = getColor('--chart-default-task-expiried-june-color');
var chartDefaultTaskJulyColor = getColor('--chart-default-task-expiried-july-color');
var chartDefaultTaskAugColor = getColor('--chart-default-task-expiried-aug-color');
var chartDefaultTaskSeptColor = getColor('--chart-default-task-expiried-sept-color');
var chartDefaultTaskOctColor = getColor('--chart-default-task-expiried-oct-color');
var chartDefaultTaskNovColor = getColor('--chart-default-task-expiried-nov-color');
var chartDefaultTaskDecColor = getColor('--chart-default-task-expiried-dec-color');

// Statistic chart Cases by Category
var chartLabelDefaultCasesByCategoryColor = getColor('--chart-label-default-cases-by-category-color');

var chartDefaultCasesByCategoryColor = getColor('--chart-default-cases-by-category-color');

// Default legend color for Doughnut Chart (Task by priority, Case by state, Case by state finish task, Case by state finish time)
var chartDefaultLegendColor = getColor('--chart-default-legend-color');

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

function taskByExpiryChartBaseExtender(context, datalabelsColor, dataColor) {
  //Register plugin datalabels
  jQuery.extend(true, context.cfg.config, {plugins: [ChartDataLabels]});
  // copy the config options into a variable
  var delayed;
  let options = jQuery.extend(true, {}, context.cfg.config.options);
  options = {
    maintainAspectRatio : false,
    scales : {
      x : {
        offset: true
      }
    },
    onHover : function(event, activeElements) {
      event.native.target.style.cursor = activeElements[0] ? 'pointer' : 'default';
    },
    animation: {
      onComplete: () => {
        delayed = true;
      },
      delay: (context) => {
        let delay = 0;
        if (context.type === 'data' && context.mode === 'default' && !delayed) {
          delay = context.dataIndex * 50 + context.datasetIndex * 10;
        }
        return delay;
      },
    },
    onClick : taskByExpiryChartClickEvent,
    
    plugins: {
      legend: {
          display: false
      },
      datalabels: {
        color: datalabelsColor
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, context.cfg.config.options, options);
  
  //copy config data into a variable
  let data = {
       datasets: [
       {
         borderColor: dataColor,
         backgroundColor: dataColor,
       }
    ]
  }
  
   // merge data into the main chart data
  jQuery.extend(true, context.cfg.config.data, data);
}

// EXPIRED_KEY, TODAY_EXPIRY_KEY, THIS_WEEK_EXPIRY_KEY, THIS_MONTH_EXPIRY_KEY, THIS_YEAR_EXPIRY_KEY: colors order/length must be the same as returned data order/length of generateDefaultExpiryModel in StatisticService.java
var taskByExpiryDefaultLabelsColors = [chartLabelDefaultTaskExpiriedColor, chartLabelDefaultTaskTodayColor, chartLabelDefaultTaskWeekColor, chartLabelDefaultTaskMonthColor, chartLabelDefaultTaskYearColor];

// EXPIRED_KEY, TODAY_EXPIRY_KEY, THIS_WEEK_EXPIRY_KEY, THIS_MONTH_EXPIRY_KEY, THIS_YEAR_EXPIRY_KEY: colors order/length must be the same as returned data order/length of generateDefaultExpiryModel in StatisticService.java
var taskByExpiryDefaultColors = [chartDefaultTaskExpiriedColor, chartDefaultTaskTodayColor, chartDefaultTaskWeekColor, chartDefaultTaskMonthColor, chartDefaultTaskYearColor];

function taskByExpiryChartDefaultDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, taskByExpiryDefaultLabelsColors);
}

function taskByExpiryChartDefaultExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartDefaultDataLabelsColor, taskByExpiryDefaultColors);
}

// For Task's expire in day
var chartLabelDefaultTaskTodayColors = [chartDefaultTaskTodayBefore8Color,chartDefaultTaskToday8Color, chartDefaultTaskToday9Color, chartDefaultTaskToday10Color
, chartDefaultTaskToday11Color, chartDefaultTaskToday12Color, chartDefaultTaskToday13Color, chartDefaultTaskToday14Color, chartDefaultTaskToday15Color
, chartDefaultTaskToday16Color, chartDefaultTaskToday17Color, chartDefaultTaskToday18Color, chartDefaultTaskTodayAfter18Color];

function taskByExpiryChartTodayDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, chartLabelDefaultTaskTodayColor);
}

function taskByExpiryChartTodayExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartTodayDataLabelsColor, chartLabelDefaultTaskTodayColors);
}

function taskByExpiryChartThisWeekDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskWeekColor);
}

// For Task's expire in week
var chartDefaultTaskWeekColors = [chartDefaultTaskMonColor, chartDefaultTaskTueColor, chartDefaultTaskWedColor, chartDefaultTaskThuColor
, chartDefaultTaskFriColor, chartDefaultTaskSatColor, chartDefaultTaskSunColor];

function taskByExpiryChartThisWeekExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisWeekDataLabelsColor, chartDefaultTaskWeekColors);
}

function taskByExpiryChartThisMonthDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskMonthColor);
}

// For Task's expire in month
var chartDefaultTaskMonthColors = [chartDefaultTaskFirstWeekColor, chartDefaultTaskSecondWeekColor, chartDefaultTaskThirdWeekColor
, chartDefaultTaskFourthWeekColor, chartDefaultTaskFifthWeekColor];

function taskByExpiryChartThisMonthExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisMonthDataLabelsColor, chartDefaultTaskMonthColors);
}

function taskByExpiryChartThisYearDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultTaskYearColor);
}

// For Task's expire in year
var chartDefaultTaskYearColors = [chartDefaultTaskJanColor, chartDefaultTaskFebColor, chartDefaultTaskMarColor, chartDefaultTaskAprColor
, chartDefaultTaskMayColor, chartDefaultTaskJuneColor, chartDefaultTaskJulyColor, chartDefaultTaskAugColor, chartDefaultTaskSeptColor
, chartDefaultTaskOctColor, chartDefaultTaskNovColor, chartDefaultTaskDecColor];

function taskByExpiryChartThisYearExtender() {
  taskByExpiryChartBaseExtender(this, taskByExpiryChartThisYearDataLabelsColor, chartDefaultTaskYearColors);
}

function taskByExpiryChartClickEvent(event, activeElement) {
  var $expiryChartDrillDown = $('.js-expiry-chart-drill-down');
  if ($expiryChartDrillDown.length == 0) {
    return;
  }
  if (activeElement[0]) {
    if (activeElement[0].index === 0) {
      $expiryChartDrillDown.hide();
    } else {
      $expiryChartDrillDown.show();
    }
    var chartId = event.chart.canvas.id;
    
    var indexOfChart = chartId.lastIndexOf(":");
    var widgetVar = 'context-menu-' + chartId.substring(indexOfChart - 1, indexOfChart);
    PF(widgetVar).show();
    topValue = event.native.offsetY + 50;
    leftValue = event.native.offsetX - 60;
  }
}

function elapsedTimeChartDataLabelsColor(context) {
  return chartDataLabelsSingleColor(context, chartLabelDefaultElapsedTimeColor);
}

function elapsedTimeChartExtender() {
  //Register plugin datalabels
  jQuery.extend(true, this.cfg.config, {plugins: [ChartDataLabels]});
  // copy the config options into a variable
  var delayed;
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    maintainAspectRatio : false,
    scales : {
      x : {
        ticks: {
          // For a category axis
          callback: function(val, index, vals) {
            if(vals.length > 5 && this.getLabelForValue(val).length > 15){
              return this.getLabelForValue(val).substr(0, 15) + '...';
            } else {
              return this.getLabelForValue(val);
            }
          }
        },
        tickWidth: 50,
        offset: true
      }
    },
     onHover : function(event, activeElements) {
      event.native.target.style.cursor = activeElements[0] ? 'pointer' : 'default';
    }
    ,
    animation: {
      onComplete: () => {
        delayed = true;
      },
      delay: (context) => {
        let delay = 0;
        if (context.type === 'data' && context.mode === 'default' && !delayed) {
          delay = context.dataIndex * 50 + context.datasetIndex * 10;
        }
        return delay;
      },
    },
    plugins: {
      legend: {
          display: false
      },
      datalabels: {
        color: elapsedTimeChartDataLabelsColor
      }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
  
   //copy config data into a variable
  let data = {
       datasets: [
       {
         borderColor: chartDefaultElapsedTimeColor,
         backgroundColor: chartDefaultElapsedTimeColor,
       }
    ]
  }
  
   // merge data into the main chart data
  jQuery.extend(true, this.cfg.config.data, data);
}

function donutChartLegendLabelsFilter(legendItem, data) {
  return data.datasets[0].data[legendItem.index] != 0;
}

function donutChartHoverEvent(event, activeElements) {
  event.native.target.style.cursor = activeElements[0] ? 'pointer' : 'default';
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

function donutChartExtender(context, datalabelsColor, dataColor) {
  //Register plugin datalabels
  jQuery.extend(true, context.cfg.config, {plugins: [ChartDataLabels]});
  // copy the config options into a variable
  let options = jQuery.extend(true, {}, context.cfg.config.options);
  options = {
    maintainAspectRatio : false,
    onHover : donutChartHoverEvent
    ,
    plugins: {
        legend: {
          display: true,
          labels: {
            filter: donutChartLegendLabelsFilter,
            color: chartDefaultLegendColor
          }
        },
        datalabels: {
          color: datalabelsColor,

          formatter: donutChartDataLabelsFormatter
        }
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, context.cfg.config.options, options);
  
  //copy config data into a variable
  let data = {
       datasets: [
       {
         borderColor: dataColor,
         backgroundColor: dataColor,
       }
    ]
  }
  
   // merge data into the main chart data
  jQuery.extend(true, context.cfg.config.data, data);
}

// EXCEPTION, HIGH, NORMAL, LOW: colors order/length must be the same as returned data order/length of generateDataForTaskByPriorityChart in StatisticService.java
var taskByPriorityLabelsColors = [chartLabelDefaultExceptionPriorityColor, chartLabelDefaultHighPriorityColor, chartLabelDefaultNormalPriorityColor, 
chartLabelDefaultlowPriorityColor];

function taskByPriorityChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, taskByPriorityLabelsColors);
}

// EXCEPTION, HIGH, NORMAL, LOW: colors order/length must be the same as returned data order/length of generateDataForTaskByPriorityChart in StatisticService.java
var taskByPriorityColors = [chartDefaultExceptionPriorityColor, chartDefaultHighPriorityColor, chartDefaultNormalPriorityColor, chartDefaultlowPriorityColor];

function taskByPriorityChartExtender() {
  donutChartExtender(this, taskByPriorityChartDataLabelsColor, taskByPriorityColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateLabelsColors = [chartLabelDefaultCreatedCaseColor, chartLabelDefaultRunningCaseColor, chartLabelDefaultDoneCaseColor, chartLabelDefaultFailedCaseColor];

function caseByStateChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, caseByStateLabelsColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateColors = [chartDefaultCreatedCaseColor, chartDefaultRunningCaseColor, chartDefaultDoneCaseColor, chartDefaultFailedCaseColor];

function caseByStateChartExtender() {
  donutChartExtender(this, caseByStateChartDataLabelsColor, caseByStateColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateFinishTaskLabelsColors = [chartLabelDefaultCreatedCaseFinishTaskColor, chartLabelDefaultRunningCaseFinishTaskColor, 
chartLabelDefaultDoneCaseFinishTaskColor, chartLabelDefaultFailedCaseFinishTaskColor];

function caseByStateFinishTaskChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, caseByStateFinishTaskLabelsColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateFinishTaskColors = [chartDefaultCreatedCaseFinishTaskColor, chartDefaultRunningCaseFinishTaskColor, 
chartDefaultDoneCaseFinishTaskColor, chartDefaultFailedCaseFinishTaskColor];

function caseByStateFinishTaskChartExtender() {
  donutChartExtender(this, caseByStateFinishTaskChartDataLabelsColor, caseByStateFinishTaskColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateFinishTimeLabelsColors = [chartLabelDefaultCreatedCaseFinishTimeColor, chartLabelDefaultRunningCaseFinishTimeColor, 
chartLabelDefaultDoneCaseFinishTimeColor, chartLabelDefaultFailedCaseFinishTimeColor];

function caseByStateFinishTimeChartDataLabelsColor(context) {
  return chartDataLabelsMultipleColor(context, caseByStateFinishTimeLabelsColors);
}

// CREATED, RUNNING, DONE, FAILED: colors order/length must be the same as returned data order/length of generateDataForCaseStateChart in StatisticService.java
var caseByStateFinishTimeColors = [chartDefaultCreatedCaseFinishTimeColor, chartDefaultRunningCaseFinishTimeColor, 
chartDefaultDoneCaseFinishTimeColor, chartDefaultFailedCaseFinishTimeColor];

function caseByStateFinishTimeChartExtender() {
  donutChartExtender(this, caseByStateFinishTimeChartDataLabelsColor, caseByStateFinishTimeColors);
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

function casesByCategoryChartExtender() {
  //Register plugin datalabels
  jQuery.extend(true, this.cfg.config, {plugins: [ChartDataLabels]});
  // copy the config options into a variable
  var delayed;
  let options = jQuery.extend(true, {}, this.cfg.config.options);
  options = {
    responsive: true,
    maintainAspectRatio : false,
   	scales : {
      x : {
        offset: true,
        ticks : {
          callback : function(val, index, vals) {
              if(vals.length > 5){
                return getShortValueCasesByCategoryChart(this.getLabelForValue(val));
              } else {
                return getValueCasesByCategoryChart(this.getLabelForValue(val));
              }
          },
        }
      }
    },
    onHover : function(event, activeElements) {
      event.native.target.style.cursor = activeElements[0] ? 'pointer' : 'default';
    }
    ,
    animation: {
      onComplete: () => {
        delayed = true;
      },
      delay: (context) => {
        let delay = 0;
        if (context.type === 'data' && context.mode === 'default' && !delayed) {
          delay = context.dataIndex * 50 + context.datasetIndex * 10;
        }
        return delay;
      },
    },
    onClick : casesByCategoryChartClickEvent,
    plugins: {
      legend: {
          display: false
      },
      datalabels: {
        color: chartLabelDefaultCasesByCategoryColor
      },
      tooltip: {
          enabled: true,
          mode: 'index',
          intersect: false,
          callbacks: {
             title: function(tooltipItems) {
               return getValueCasesByCategoryChart(tooltipItems[0].label);
             }      
          }
    	},
    }
  };

  // merge all options into the main chart options
  jQuery.extend(true, this.cfg.config.options, options);
  
  //copy config data into a variable
  let data = {
       datasets: [
       {
         borderColor: chartDefaultCasesByCategoryColor,
         backgroundColor: chartDefaultCasesByCategoryColor,
       }
    ]
  }
  
   // merge data into the main chart data
  jQuery.extend(true, this.cfg.config.data, data);
}

function getShortValueCasesByCategoryChart(value){
    if(value.includes("\\\\")){
      value = value.split("\\\\")[0];
    }
    if(value.includes("\\")){
      value = value.split("\\")[0];
    }
    if(value.length > 15){
       return value.substr(0, 15) + '...';
    } else {
       return value;
    }
}

function getValueCasesByCategoryChart(value){
    if(value.includes("\\\\")){
      return value.split("\\\\")[0];
    }
    if(value.includes("\\")){
     return value.split("\\")[0];
    }
    return value;
}

function casesByCategoryChartClickEvent(event, activeElement) {
  var $casesByCategoryChartDrillDown = $('.js-cases-by-category-chart-drill-down');
  if ($casesByCategoryChartDrillDown.length == 0) {
    return;
  }
  if (activeElement[0]) {
    const points = event.chart.getElementsAtEventForMode(event, 'nearest', { intersect: true }, true);
    if (points.length) {
        const firstPoint = points[0];
        var label = event.chart.data.labels[firstPoint.index];
        if(label.includes("\\\\")){
          $casesByCategoryChartDrillDown.show();
        } else {
          $casesByCategoryChartDrillDown.hide();
        }
    } else {
     $casesByCategoryChartDrillDown.show();
    }
    
    var chartId = event.chart.canvas.id;
    
    var indexOfChart = chartId.lastIndexOf(":");
    var widgetVar = 'context-menu-' + chartId.substring(indexOfChart - 1, indexOfChart);
    PF(widgetVar).show();
    topValue = event.native.offsetY + 50;
    leftValue = event.native.offsetX - 60;
  }
}