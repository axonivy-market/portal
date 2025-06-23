const DATA_CHART_ID = 'data-chart-id';
const WIDGET_HEADER_TITLE = '.widget__header-title';
const AVERAGE_BUSINESS_RUNTIME = "avg-businessRuntime";


// Additional configs
const EMPTY_CHART_MESSAGE =  'emptyChartDataMessage';
const MANIPULATE_BY = 'manipulateValueBy';
const CHART_TEXT_COLOR = '#808080';
const CHART_GRID_COLOR = 'rgba(192, 192, 192, 0.5)';
const MIN_REFRESH_INTERVAL = 60;
const SUCCESS_STATUS_CODE = 200;

let locale;
let contentLocale;
let datePattern;
var statisticApiURL = '';
var refreshInfos = [];

const chartColors = () => {
  return [getCssVariable('--statistics-1-color'),
  getCssVariable('--statistics-2-color'),
  getCssVariable('--statistics-3-color'),
  getCssVariable('--statistics-4-color'),
  getCssVariable('--statistics-5-color'),
  getCssVariable('--statistics-6-color'),
  getCssVariable('--statistics-7-color'),
  getCssVariable('--statistics-8-color')];
}

const getCssVariable = variableName => {
  return getComputedStyle(document.body).getPropertyValue(variableName);
}

async function postFetchApi(uri, content) {
  const response = await fetch(uri, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'X-Requested-By': 'ivy',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST',
    },
    body: content
  });
  return response;
}

function filterOptionsForDateTimeFormatter(pattern) {
  let options = { day: 'numeric', month: 'long', year: 'numeric' };
  let patternArr = pattern.includes('.') ? pattern.split('.') : pattern.split(' ');
  patternArr.forEach((element, index) => {
    switch (element) {
      case 'd':
      case 'dd':
      case 'ddd':
      case 'dddd': options.day = '2-digit'; break;

      case 'M':
      case 'MM': options.month = '2-digit'; break;
      case 'MMM': options.month = 'short'; break;
      case 'MMMM': options.month = 'long'; break;

      case 'y':
      case 'yy':
      case 'yyy':
      case 'yyyy': options.year = 'numeric'; break;
    }
  })
  return options;
}

function formatDateFollowLocale(dt) {
  const options = filterOptionsForDateTimeFormatter(datePattern);
  // Format locale
  let friendlyLocale = locale.replace('_', '-');
  const formatter = new Intl.DateTimeFormat(friendlyLocale, options);
  return formatter.format(dt);
}

function formatISODate(dt) {
  const correctMonth = dt.getMonth() + 1;
  let year = dt.getFullYear();
  let month = correctMonth < 10 ? '0' + correctMonth : correctMonth;
  let date = dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();
  return year + '-' + month + '-' + date;
}
const convertYValue = (value, config) => {
  if (!value || !config) {
    return value;
  }
  
  let valueNumber = 0;
  try {
     valueNumber = Number(value);

     config.find(function (item) {
      if (Object.keys(item || {})[0] === MANIPULATE_BY) {
        let itemVal = Object.values(item)[0];
        let operator = itemVal.charAt(0);
        let manipulateValueBy = Number(itemVal.substring(1));
        switch (operator) {
          case '/': 
             value = (valueNumber / manipulateValueBy);
             break;
          case '*':
             value =  valueNumber * manipulateValueBy;
             break;
          default:
             break;
        };
      }
    });
  } catch(error) {
    return value;
  }
  
  return value;
}

async function fetchChartData(chart, chartId) {
  let data;
  let cloneResponse;

  try {
    const response = await postFetchApi(statisticApiURL, JSON.stringify({ "chartId": chartId }));
    cloneResponse = response.clone();
    data = await response.json();
    data['statusCode'] = response.status;
    return await data;
  } catch (error) {
    (new ClientChart()).renderNoPermissionStatistics(chart, await cloneResponse.text());
    return;
  }
}

async function refreshChart(chartInfo) {
  data = await fetchChartData(chartInfo.chart.chart, chartInfo.chartId);
  chartInfo.chart.update(data);
}

function initRefresh() {
  for (let i = 0; i < refreshInfos.length; i++) {
    let refreshInfo = refreshInfos[i];
    if (refreshInfo.refreshInterval && refreshInfo.refreshInterval > 0) {
      // when init statistic again, e.g., AJAX update statistic, clear exising interval
      if (typeof refreshInfo.refreshIntervalId !== 'undefined') {
        clearInterval(refreshInfo.refreshIntervalId);
      }
      if (refreshInfo.refreshInterval !== 0) {
        if (refreshInfo.refreshInterval < MIN_REFRESH_INTERVAL) {
          refreshInfo.refreshInterval = MIN_REFRESH_INTERVAL;
        }
        refreshInfo.refreshIntervalId = setInterval(() => {
          refreshChart(refreshInfo);
        }, refreshInfo.refreshInterval * 1000);
      }
    }
  }
}

function initClientCharts(statisticEndpoint, defaultLocale, datePatternConfig, defaultContentLocale) {
  initConfig(defaultLocale, defaultContentLocale, datePatternConfig);

  // Find HTML elements of client charts widget
  const charts = Array.from(document.getElementsByClassName('js-statistic-chart'));
  if (!charts || charts.length == 0) {
    return;
  }
 
  statisticApiURL = window.location.origin + statisticEndpoint;

  // Use AJAX to call REST API to fetch data for each chart elements
  charts.forEach(async chart => {
    let chartId = chart.getAttribute(DATA_CHART_ID);
    let data = await fetchChartData(chart, chartId);

    if (data.statusCode != SUCCESS_STATUS_CODE) {
      renderNotFoundData(chart, data.errorMessage);
      return;
    }

    if (!data) {
      renderNotFoundData(chart, 'No data found');
      return;
    }

    // proceed chart data
    let chartData = generateChart(chart, data);
    const config = data.chartConfig;

    // If chart data is fetched succesfully:
    // Render chart
    // Prepare info for refresh routine of each chart
    if (chartData) {
      chartData.render();

      const chartObject = {
        chart: chartData,
        chartType: config.chartType,
        chartId: chartId,
        refreshInterval: config.refreshInterval
      }

      refreshInfos.push(chartObject);
    }

    // Init refresh routine for charts
    initRefresh();
  });
}

function previewChart(data, defaultLocale, datePatternConfig, defaultContentLocale) {
  const charts = document.getElementsByClassName('js-statistic-chart');
  if (!charts || charts.length == 0) {
    return;
  }
  initConfig(defaultLocale, defaultContentLocale, datePatternConfig);
  
  try {
    let chartData = generateChart(charts[0], data);
    if (chartData) {
      chartData.render();
    }
  } catch (error) {
    console.error("Error in previewChart:", error);
    PF('previewButton').enable();
    renderFailToRenderChart(charts[0], data.chartConfig.additionalConfigs);
  }

}

function clearChartInterval() {
  for (let i = 0; i < refreshInfos.length; i++) {
    let refreshInfo = refreshInfos[i];
    if (typeof refreshInfo.refreshIntervalId !== 'undefined') {
      clearInterval(refreshInfo.refreshIntervalId);
    }
  }
}

function removeCharacterFromLastIndex(str, n) {
  if (!str || str.length <= n) {
    return ""; // Handle empty or short strings
  }
  return str.slice(0, str.length - (n + 1)) + str.slice(str.length - n);
}
// Function to generate chart data by chart type
const generateChart = (chart, data) => {
  data.chartConfig.filter = data.chartConfig.filter ? data.chartConfig.filter : '';
  
  switch (data.chartConfig.chartType) {
    case 'number': return new ClientNumberChart(chart, data);
    case 'bar': return new ClientBarChart(chart, data);
    case 'line': return new ClientLineChart(chart, data);
    case 'pie': return new ClientPieChart(chart, data);
    case 'doughnut': return new ClientPieChart(chart, data);
  }
  return undefined;
}

function renderNotFoundData(chart, errorMessage) {
  let noChartDataHtml =
    `<div class="process-dashboard-widget__empty-process empty-message-container">` +
    `    <span class="empty-message-text">${errorMessage}</span>` +
    `</div>`;
  $(chart).html(noChartDataHtml);
}

// Method to render empty preview chart
function renderFailToRenderChart(chart, additionalConfig) {
  let failToRenderChartMessage;
  additionalConfig.find(function (item) {
    if (Object.keys(item || {})[0] === 'failToRenderChartMessage') {
      failToRenderChartMessage = item.failToRenderChartMessage;
    }
  });
  // HTML element for the empty chart
  let failToRenderChartHtml =
    '<div class="empty-message-container">' +
    '    <i class="si si-analytics-pie-2 empty-message-icon"></i>' +
    '    <p class="empty-message-text">' + failToRenderChartMessage + '</p>' +
    '</div>';
  $(chart).html(failToRenderChartHtml);
}

function initConfig(defaultLocale, defaultContentLocale, datePatternConfig) {
    // If locale didn't initialized, set the default locale to it.
    if (!locale) {
      locale = defaultLocale;
    }
  
    if (!contentLocale) {
      contentLocale = defaultContentLocale;
    }
    datePattern = datePatternConfig;
}

function getFormatedTitle(titles) { 
  const matchingItem = titles.find(item => item.locale === contentLocale);
  if (matchingItem) {
    return matchingItem.value;
  }
  return '';
}

// Generic class for Client charts
class ClientChart {
  constructor(chart, data) {
    this.chart = chart;
    this.data = data;
    this.clientChartConfig = null;
  }

  // Abstract method to render client chart
  render() { }

  // Abstract method to format chart label
  formatChartLabel() { }

  // Update new data to an existing chart
  update(newData) {
    this.data = newData;
    this.dataResult = newData.result.aggs?.[0]?.buckets ?? [];
    this.updateClientChart();
  }

  updateClientChart() { }

  // Method to render empty chart
  renderEmptyChart(chart, additionalConfig) {
    let emptyChartDataMessage;
    additionalConfig.find(function (item) {
      if (Object.keys(item || {})[0] === 'emptyChartDataMessage') {
        emptyChartDataMessage = item.emptyChartDataMessage;
      }
    });
    // HTML element for the empty chart
    let emptyChartHtml =
      '<div class="empty-message-container">' +
      '    <i class="si si-analytics-pie-2 empty-message-icon"></i>' +
      '    <p class="empty-message-text">' + emptyChartDataMessage + '</p>' +
      '</div>';
    $(chart).html(emptyChartHtml);
  }

  // Method to render no permission error to see chart
  renderNoPermissionStatistics(chart, noPermissionChartMessage) {

    // HTML element for the empty chart with no permission message
    let noPermissionChartHtml =
      '<div class="process-dashboard-widget__empty-process empty-message-container">' +
      '    <i class="si si-lock-1 empty-message-icon"></i>' +
      '    <br><span class="empty-message-text">' + noPermissionChartMessage + '</span>' +
      '</div>';

    $(chart).html(noPermissionChartHtml);
  }
}

// Generic class for canvas charts which handled and generated by Chart.js 
class ClientCanvasChart extends ClientChart {
  constructor(chart, data) {
    super(chart, data);
  }

  // Method to render canvas
  renderChartCanvas(chartId) {
    return '<canvas id="' + chartId + '" />';
  };

  // Method to format chart label
  formatChartLabel(label) {
    if (typeof label === 'number') {
      return formatDateFollowLocale(new Date(label));
    }

    const aggregationField = this.data.chartConfig.statisticAggregation?.field;
    
    if (aggregationField !== 'state') {
      return label;
    }

    return label.toLowerCase()
      .split('_')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }

  // Method to init the dashboard statistic widget title
  initWidgetTitle() {
    $(this.chart).parents('.dashboard__widget').find('.widget__header > .widget__header-title')
      .text(getFormatedTitle(this.data.chartConfig.names));
  }

  updateClientChart() {
    this.initWidgetTitle();

    let result = this.data.result.aggs?.[0]?.buckets ?? [];
    let config = this.data.chartConfig;
    let chart = this.chart;

    // Render empty chart when result empty 
    if (result.length == 0) {
      return this.renderEmptyChart(chart, config.additionalConfigs);
    }

    // If there is no chart from the beginning, init chart config
    if ($(this.chart).find('.empty-message-container').length > 0) {
      this.render();
      return;
    }

    // Update client chart config by new data
    this.clientChartConfig.data.labels = result.map(bucket => this.formatChartLabel(bucket.key));
    if (this.clientChartConfig.data.datasets[0]) {
      this.clientChartConfig.data.datasets[0].data = result.map(bucket => bucket.count)
      this.clientChartConfig.data.datasets[0].label = config.name;
    }
    this.clientChartConfig.update("none");
  }
}

// Class for pie charts
class ClientPieChart extends ClientCanvasChart {
  render() {
    this.initWidgetTitle();

    let result = this.data.result.aggs?.[0]?.buckets ?? [];
    let config = this.data.chartConfig;
    let chart = this.chart;

    if (result.length == 0) {
      return this.renderEmptyChart(chart, config.additionalConfigs);
    } else {
      let html = this.renderChartCanvas(chart.getAttribute(DATA_CHART_ID));
      $(chart).html(html);
      let canvasObject = $(chart).find('canvas');
      this.clientChartConfig = new Chart(canvasObject, {
        type: config.chartType,
        label: config.name,
        data: {
          labels: result.map(bucket => this.formatChartLabel(bucket.key)),
          datasets: [{
            label: config.name,
            data: result.map(bucket => bucket.count),
            backgroundColor: this.getBackgoundColors()?.length ? this.getBackgoundColors() : chartColors
          }],
          hoverOffset: 4
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              labels: {
                color: CHART_TEXT_COLOR
              }
            }
          }
        }
      });
    }
  }

  getBackgoundColors() {
    return this.data.chartConfig.pieChartConfig.backgroundColors;
  }
}

// Class for cartersian charts (bar, line)
class ClientCartesianChart extends ClientCanvasChart {
  constructor(chart, data) {
    super(chart, data);
    this.dataResult = data.result.aggs?.[0]?.buckets ?? [];
  }

  render() {
    this.initWidgetTitle();

    let result = this.dataResult;
    let config = this.data.chartConfig;
    let chart = this.chart;

    if (result.length == 0) {
      return this.renderEmptyChart(chart, config.additionalConfigs);
    } else {
      //If the target type for the Y axis is 'time', get average time from sub aggregate of the result.
      const chartTypeConfig = this.getChartTypeConfig();
      let data = chartTypeConfig?.yValue ? this.processYValue(result, chartTypeConfig?.yValue) : result;

      // Because processYValue removes bucket which has empty key, if the returned result is empty, render empty chart
      if (data.length == 0) {
        return this.renderEmptyChart(chart, config.additionalConfigs);
      }

      let stepSize = chartTypeConfig?.yValue === 'time' ? 200 : 2;
      let html = this.renderChartCanvas(chart.getAttribute(DATA_CHART_ID));

      $(chart).html(html);
      let canvasObject = $(chart).find('canvas');
      this.clientChartConfig = new Chart(canvasObject, {
        type: config.chartType,
        data: {
          labels: data.map(bucket => this.formatChartLabel(bucket.key)),
          datasets: [{
            label: config.name,
            data: data.map(bucket => bucket.count),
            backgroundColor: this.getBackgoundColors()?.length ? this.getBackgoundColors() : chartColors,
            pointBorderColor: this.getBackgoundColors()?.length ? this.getBackgoundColors() : chartColors,
            pointRadius: 4,
            borderColor: getCssVariable("--ivy-primary-color-grey-medium"),
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: false
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              title: {
                text: getFormatedTitle(chartTypeConfig.yTitles),
                display: chartTypeConfig.yTitles.length > 0,
                color: CHART_TEXT_COLOR
              },
              ticks: {
                stepSize: stepSize,
                color: CHART_TEXT_COLOR
              },
              grid: {
                color: CHART_GRID_COLOR
              }
            },
            x: {
              title: {
                text: getFormatedTitle(chartTypeConfig.xTitles),
                display: chartTypeConfig.xTitles.length > 0,
                color: CHART_TEXT_COLOR
              },
              ticks: {
                color: CHART_TEXT_COLOR
              },
              grid: {
                color: CHART_GRID_COLOR
              }
            }
          }
        }
      });
    }
  }

  // abstract methods
  getChartTitleConfig() { }

  getBackgoundColors() { }

  processYValue(result, yValue) {
    switch (yValue) {
      case 'time': {
        const values = [];
        result.forEach((bucket) => {
          if (bucket.key.trim().length !== 0) {
            bucket.aggs.forEach((item) => {
              if (item['name'] === AVERAGE_BUSINESS_RUNTIME) {
                values.push({
                  key: bucket.key,
                  count: convertYValue(item.value, this.data.chartConfig.additionalConfigs)
                });
              }
            });
          }
        });
        return values;
      };
      default:
        return result;
    }
  }
}

// Class for bar chart
class ClientBarChart extends ClientCartesianChart {
  getChartTypeConfig() {
    return this.data.chartConfig.barChartConfig;
  }

  updateClientChart() {
    let result = this.data.result.aggs?.[0]?.buckets ?? [];
    let config = this.data.chartConfig;
    let chart = this.chart;

    // Render empty chart when result empty 
    if (result.length == 0) {
      return this.renderEmptyChart(chart, config.additionalConfigs);
    } 
    else if (result.length > 0) {
      // Update y value in case y value is time
      if (config.barChartConfig?.yValue === 'time') {
        result = this.processYValue(result, config.barChartConfig.yValue);

        // Because processYValue removes bucket which has empty key, if the returned result is empty, render empty chart
        if (result.length == 0) {
          return this.renderEmptyChart(chart, config.additionalConfigs);
        }
      }
      let data = result;
      this.clientChartConfig.data.labels = result.map(bucket => this.formatChartLabel(bucket.key));
      this.clientChartConfig.data.datasets = [{
        label: config.name,
        data: data.map(bucket => bucket.count),
        backgroundColor: config.backgroundColors ? config.backgroundColors : chartColors
      }]
    }

    // If there is no chart from the beginning, init chart config
    if ($(this.chart).find('.empty-message-container').length > 0) {
      this.render();
      return;
    }

    this.clientChartConfig.update("none");
  }

  getBackgoundColors() {
    return this.data.chartConfig.barChartConfig.backgroundColors;
  }
}


// Class for line chart
class ClientLineChart extends ClientCartesianChart {
  getChartTypeConfig() {
    return this.data.chartConfig.lineChartConfig;
  }

  getBackgoundColors() {
    return this.data.chartConfig.lineChartConfig.backgroundColors;
  }
}

// Class for number chart
class ClientNumberChart extends ClientChart {
  constructor(chartElem, data) {
    super(chartElem, data);
    this.filters = data.chartConfig.filter?.split(',');
    this.dataResult = data.result.aggs?.[0]?.buckets ?? [];
    this.items = this.getItemFromFilters();
  }

  fillResult() {
    let result = this.dataResult;
    if (result?.length == 0) {
      result = this.fillEmptyResult();
    }

    let dataResultKeys = result.map(item => item.key);
    this.items.forEach((item) => {
      if (!dataResultKeys.includes(item)) {
        result.push({
          key: item,
          count: 0,
          aggs: []
        })
      }
    });

    return result;
  }

  fillEmptyResult() {
    // If items of chart is recognizable, render result for each item
    if (this?.items?.length > 0) {
      let result = [];
      this.items.forEach(item => {
        result.push({
          key: item,
          count: 0,
          aggs: []
        });
      });
      return result;
    }

    // If cannot recognize result, render only 1 result with empty key
    return [{
      key: '',
      count: 0,
      aggs: []
    }];
  }

  formatChartLabel(label) {
    return label;
  }

  render() {
    let config = this.data.chartConfig;
    this.initWidgetHeaderName(this.chart, getFormatedTitle(config.names));
    let result = this.fillResult();

    if (!result || result.length == 0) {
      result = this.fillEmptyResult();
    }

    $(this.chart).parents('.statistic-chart-widget__chart').addClass('client-number-chart');
    let multipleKPI = this.renderMultipleNumberChartInHTML(result, config.numberChartConfig.suffixSymbol);
    return $(this.chart).html(multipleKPI);
  }

  initWidgetHeaderName(chart, widgetName) {
    let widgetHeader = $(chart).parents(".card-widget-panel")
      .find(".widget__header")
      .find(".widget__header-title").get(0);
    if (widgetHeader) {
      widgetHeader.textContent = widgetName;
    }
  }

  renderMultipleNumberChartInHTML(result, suffixSymbold) {
    let multipleNumberChartInHTML = '';
    if (result?.length > 0) {
        result.forEach((item, index) => {
          let htmlString = this.generateItemHtml(item.key, item.count, suffixSymbold, index);
          multipleNumberChartInHTML += htmlString;
        })

    } else {
      multipleNumberChartInHTML = this.generateItemHtml('', '0', suffixSymbold, 0);
    }
    return multipleNumberChartInHTML;
  }

  generateItemHtml(label, number, suffixSymbol, index) {
    let border = '<div class="chart-border">' + '</div>';
    label = this.data.chartConfig.numberChartConfig?.hideLabel === true ? '' : this.formatChartLabel(label) ;
    let html =
      '<div class="text-center chart-content-card">' +
      '    <div class="chart-number-container">' +
      '        <span class="card-number chart-number-font-size chart-number-animation">' + number + '</span>' +
      '        <i class="card-number chart-number-font-size chart-number-animation ' + suffixSymbol + '"></i>' +
      '    </div>' +
      '    <div class="chart-label-container">' +
      '        <span class="card-name chart-name-font-size chart-number-animation">' + label + '</span>' +
      '    </div>' +
      '</div>';
    return index > 0 ? border + html : html;
  };

  // Method to format chart label.
  formatChartLabel(label) {
    // Format date
    if (typeof label === 'number') {
      return formatDateFollowLocale(new Date(label));
    }

    // Format enum. Example: IN_PROGRESS -> In Progess
    return label.toLowerCase()
      .split('_')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }

  getItemFromFilters() {
    const aggregateFilter = this.data.chartConfig.aggregates + ':';
    const aggregateFilterRegex = new RegExp('^' + this.data.chartConfig.aggregates + ':');
    let result = [];
    this.filters.filter(filter => filter.startsWith(aggregateFilter))
    .forEach(filter => {
      // Get states from filter. Expected result: ['OPEN','DONE']
      filter.replace(aggregateFilterRegex, "").split(' ')
      .filter(item => item !== "").forEach((state) => result.push(state));
    });
    // Remove duplicated states if any. ['OPEN','DONE', 'OPEN'] => ['OPEN','DONE']
    // result = result.filter(item => item !== "" && item !== null);
    return result.filter((item, index) => result.indexOf(item) == index);
  }

  updateClientChart() {
    this.render();
  }
}