const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const statisticApiURL = '/api/statistics/data';
const instance = axios.create({
    baseURL: baseURL,
    timeout: 60000,
    headers: { 'X-Requested-By': 'ivy' }
});
const DATA_CHART_ID = 'data-chart-id';
const WIDGET_HEADER_TITLE = '.widget__header-title';
const AVERAGE_BUSINESS_RUNTIME = "avg-businessRuntime";
// Default value of locale is 'de'  
let locale = 'de';
let datePattern = 'dd.MM.yyyy';

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

const isNumeric = number => {
    return !isNaN(parseFloat(number)) && isFinite(number);
}

function filterOptionsForDateTimeFormatter(pattern) {
    let options = {day: 'numeric',month: 'long',year: 'numeric'};
    let patternArr = pattern.includes('.') ? pattern.split('.') : pattern.split(' ');
    patternArr.forEach((element,index) => {
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
    locale = locale.replace('_', '-');
    const formatter = new Intl.DateTimeFormat(locale,options);
    return formatter.format(dt);
}

function formatISODate(dt) {
    const correctMonth = dt.getMonth() + 1;
    let year = dt.getFullYear();
    let month = correctMonth < 10 ? '0' + correctMonth : correctMonth;
    let date = dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();
    return year + '-' + month + '-' + date;
}

$(document).ready(function () {
    initStatistics();
});

function initRefresh(refreshInfos) {
    for (let i = 0; i < refreshInfos.length; i++) {
        let refreshInfo = refreshInfos[i];
        if (refreshInfo.refreshInterval && refreshInfo.refreshInterval > 0) {
            // when init statistic again, e.g., AJAX update statistic, clear exising interval
            if (typeof refreshInfo.refreshIntervalId !== 'undefined') {
                clearInterval(refreshInfo.refreshIntervalId);
            }
            refreshInfo.refreshIntervalId = setInterval(() => {
                refreshChart(refreshInfo);
            }, refreshInfo.refreshInterval * 1000);
        }
    }
}

async function refreshChart(chartInfo) {
    let chartId = chartInfo.chartId;    
    const response = await instance.post(statisticApiURL, { "chartId": chartId });
    chartInfo.chart.update(await response.data);
}

function initStatistics() {
    let refreshInfos = [];
    $('.chart-options').each(async (index, chart) => {
        let chartId = chart.getAttribute(DATA_CHART_ID);
        let response;
        try {
            response = await instance.post(statisticApiURL, {"chartId": chartId});
        } catch (error) {
            (new ClientChart()).renderNoPermissionStatistics(chart, error.response.data);
            return;
        }
		let data = await response.data;
		let chartData = generateChart(chart, data);
		const config = data.chartConfig;
		locale = config.locale;
		datePattern = config.datePattern;

		chartData.render();
        let chartObject = {
            'chart': chartData,
            'chartType': config.chartType,
            'chartId': chartId,
            'refreshInterval': config.refreshInterval
        }

        if (chartData !== undefined) {
            refreshInfos.push(chartObject);
        }

        initRefresh(refreshInfos);

    });
}

const generateChart = (chart, data) => {
	switch(data.chartConfig.chartType) {
		case 'number': return data.chartConfig.aggregates === 'businessState' ? 
			new BusinessStateNumberChart(chart, data) : 
			new ClientNumberChart(chart, data);
		case 'bar': return new ClientBarChart(chart, data);
		case 'line': return new ClientLineChart(chart, data);
		case 'pie': return new ClientPieChart(chart, data);
		case 'doughnut': return new ClientPieChart(chart, data);
	}
	return undefined;
}

class ClientChart {
	constructor(chart, data) {
		this.chart = chart;
		this.data = data;
	}

	// abstract methods
	render() {}
	formatChartLabel() {}
	update(newData) {
		this.data = newData;
		this.render();
		this.dataResult = newData.result.aggs?.[0]?.buckets ?? [];
	}

	// Other methods

	
	renderEmptyStatistics(chart, additionalConfig) {
		let emptyChartDataMessage;
		additionalConfig.find(function (item) {
			if (Object.keys(item)[0] === "emptyChartDataMessage") {
				emptyChartDataMessage = item.emptyChartDataMessage;
			}
		});
		let emptyChartHtml =
			'<div class="empty-message-container">' +
			'    <i class="si si-analytics-pie-2 empty-message-icon"></i>' +
			'    <p class="empty-message-text">' + emptyChartDataMessage + '</p>' +
			'</div>';
		$(chart).html(emptyChartHtml);
	}
	
	renderNoPermissionStatistics(chart, noPermissionChartMessage) {
		let noPermissionChartHtml =
			'<div class="process-dashboard-widget__empty-process empty-message-container">' +
			'    <i class="si si-lock-1 empty-message-icon"></i>' +
			'    <br><span class="empty-message-text">' + noPermissionChartMessage + '</span>' +
			'</div>';
		$(chart).html(noPermissionChartHtml);
	}
}

class ClientCanvasChart extends ClientChart {
	constructor(chart, data) {
		super(chart, data);
	}

	renderChartCanvas(chartId) {
		return '<canvas id="' + chartId + '" />';
	};

	formatChartLabel(label) {
		if (isNumeric((new Date(label)).getTime())) {
			return formatDateFollowLocale(new Date(label));
		}
		return label;
	}

	initWidgetTitle() {
		$(this.chart).parents('.dashboard__widget').find('.widget__header > .widget__header-title')
		    .text(this.data.chartConfig.name);
	}
}

class ClientPieChart extends ClientCanvasChart {
	render() {
		this.initWidgetTitle();

		let result = this.data.result.aggs?.[0]?.buckets ?? [];
		let config = this.data.chartConfig;
		let chart = this.chart;

		if (result.length == 0) {
			return this.renderEmptyStatistics(chart, config.additionalConfig);
		} else {
			let html = this.renderChartCanvas(chart.getAttribute(DATA_CHART_ID));
			$(chart).html(html);
			let canvasObject = $(chart).find('canvas');
			return new Chart(canvasObject, {
				type: config.chartType,
				label: config.name,
				data: {
					labels: result.map(bucket => this.formatChartLabel(bucket.key)),
					datasets: [{
						label: config.name,
						data: result.map(bucket => bucket.count),
						backgroundColor: chartColors
					}],
					hoverOffset: 4
				},
				options: {
					responsive: true,
					maintainAspectRatio: false
				}
			});
		}
	}
}

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
			return this.renderEmptyStatistics(chart, config.additionalConfig);
		} else {
			//If the target type for the Y axis is 'time', get average time from sub aggregate of the result.
			const chartTypeConfig = this.getChartTypeConfig();
			let data = config?.yValue ? this.processBarChartYValue(result, config?.yValue ) : result;
			let stepSize = chartTypeConfig?.yValue === 'time' ? 200 : 2;
			let html = this.renderChartCanvas(chart.getAttribute(DATA_CHART_ID));

			$(chart).html(html);
			let canvasObject = $(chart).find('canvas');
			return new Chart(canvasObject, {
				type: config.chartType,
				data: {
					labels: data.map(bucket => this.formatChartLabel(bucket.key)),
					datasets: [{
						label: config.name,
						data: data.map(bucket => bucket.count),
						backgroundColor: chartColors
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
								text: chartTypeConfig.yTitle,
								display: true
							},
							ticks: {
								stepSize: stepSize
							}
						},
						x: {
							title: {
								text: chartTypeConfig.xTitle,
								display: true
							}
						}
					}
				}
			});
		}
	}

	// abstract methods
	getChartTitleConfig() {}
	
	processBarChartYValue(result, yValue) {
		switch (yValue) {
			case 'time': {
				const values = [];
				result.forEach((bucket) => {
					if (bucket.key.trim().length !== 0) {
						bucket.aggs.forEach((item) => {
							if(item['name'] === AVERAGE_BUSINESS_RUNTIME){
								values.push({
									key: bucket.key,
									count: item.value
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

class ClientBarChart extends ClientCartesianChart {
	getChartTypeConfig() {
		return this.data.chartConfig.barChartConfig;
	}
}

class ClientLineChart extends ClientCartesianChart {
	getChartTypeConfig() {
		return this.data.chartConfig.lineChartConfig;
	}
}

class ClientNumberChart extends ClientChart {
	constructor(chartElem, data) {
		super(chartElem, data);
		this.filters = data.chartConfig.filter?.split(',');
		this.dataResult = data.result.aggs?.[0]?.buckets ?? [];
	}

	fillResult() {}
	fillEmptyResult() {}

	formatChartLabel(label) {
		return label;
	}

	render() {
		let config = this.data.chartConfig;
		this.initWidgetHeaderName(this.chart, config.name);
		let result = this.fillResult();

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
	
		if (result.length > 0) {
			result.forEach((item) => {
				let htmlString = this.generateItemHtml(item.key, item.count, suffixSymbold);
				multipleNumberChartInHTML += htmlString;
			});
		} else {
			multipleNumberChartInHTML = this.generateItemHtml('', '0', suffixSymbold);
		}
		return multipleNumberChartInHTML;
	}

	generateItemHtml(label, number, suffixSymbol) {
		label = this.formatChartLabel(label);
		let html =
			'<div class="u-text-align-center chart-content-card">' +
			'    <div class="chart-icon-font-size chart-number-animation">' +
			'        <i class="fa-solid fa-chart-line"></i>' +
			'    </div>' +
			'    <div>' +
			'        <span class="card-number chart-number-font-size chart-number-animation">' + number + '</span>' +
			'        <i class="card-number chart-number-font-size chart-number-animation ' + suffixSymbol + '"></i>' +
			'    </div>' +
			'    <div>' +
			'        <span class="card-name chart-name-font-size chart-number-animation">' + this.formatChartLabel(label) + '</span>' +
			'    </div>' +
			'</div>';
		return html;
	};
}

class BusinessStateNumberChart extends ClientNumberChart {
	constructor(chartElem, data) {
		super(chartElem, data);
		this.states = this.getStatesFromFilters();
	}

	fillResult() {
		let result = this.dataResult;
		if (result.length == 0) {
			result = this.fillEmptyResult();
		}

		let dataResultKeys = result.map(item => item.key);
		this.states.forEach((state) => {
			if (!dataResultKeys.includes(state)) {
				result.push({
					key: state,
					count: 0,
					aggs: []
				})
			}
		});

		return result;
	}

	fillEmptyResult() {
		let result = [];
		this.states.forEach(state => {
			result.push({
				key: state,
				count: 0,
				aggs: []
			});
		});
		return result;
	}

	getStatesFromFilters() {
		const BUSINESS_STATE_FILTER = 'businessState:';
		const BUSINESS_STATE_FILTER_REGEX = /^businessState:/;
		let result = [];
		let x = this.filters.filter(filter => filter.startsWith(BUSINESS_STATE_FILTER));
		this.filters.filter(filter => filter.startsWith(BUSINESS_STATE_FILTER))
			.forEach(filter => {
				// Get states from filter. Expected result: ['OPEN','DONE']
				filter.replace(BUSINESS_STATE_FILTER_REGEX, "").split(' ')
				    .forEach((state) => result.push(state));
			});
		
		// Remove duplicated states if any. ['OPEN','DONE', 'OPEN'] => ['OPEN','DONE']
		return result.filter((item, index) =>  result.indexOf(item) == index);
	}

	formatChartLabel(label) {
		if (label === 'IN_PROGRESS') {
			return 'In Progress';
		}

		return label.charAt(0) + label.slice(1).toLowerCase();
	}
}