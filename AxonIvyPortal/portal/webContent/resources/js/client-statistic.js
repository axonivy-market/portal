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

const chartColors = () => {
    let chartColors = [];
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-1-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-2-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-3-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-4-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-5-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-6-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-7-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-8-color'));
    return chartColors;
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function formatDateFollowLocale(dt) {
    const options = {day: 'numeric',month: 'long',year: 'numeric'};
    // Format locale
    locale = locale.replace('_', '-');
    const formatter = new Intl.DateTimeFormat(locale,options);
    return formatter.format(dt);
}

$(document).ready(function () {
    initStatistics();
});

function renderNumberChart(chart, data) {
    let dataResult = data.result.aggs?.[0]?.buckets ?? [];
    let config = data.chartConfig;
    initWidgetHeaderName(chart, config.name);
    let filters = getChartFilters(data);
    let result = fillUpResultBasedOnFilter(config.aggregates, dataResult, filters);

    let multipleKPI = renderMultipleNumberChartInHTML(result, config.numberChartConfig.suffixSymbol);
    return $(chart).html(multipleKPI);
}

function renderPieChart(chart, data) {
    let result = data.result.aggs?.[0]?.buckets ?? [];
    let config = data.chartConfig;
    if (result.length == 0) {
        return renderEmptyStatistics(chart, config.additionalConfig);
    } else {
        let html = renderChartCanvas(chart.getAttribute(DATA_CHART_ID));
        $(chart).html(html);
        let canvasObject = $(chart).find('canvas');
        return new Chart(canvasObject, {
            type: config.chartType,
            label: config.name,
            data: {
                labels: result.map(bucket => formatChartLabel(bucket.key)),
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

function renderBarChart(chart, data) {
    let result = data.result.aggs?.[0]?.buckets ?? [];
    return renderBarLineChart(result, chart, data.chartConfig);
}

function initWidgetHeaderName(chart, widgetName) {
    let widgetHeader = $(chart).parents(".card-widget-panel")
        .find(".widget__header")
        .find(".widget__header-title").get(0);
    widgetHeader.textContent = widgetName;
}

function processBarChartYValue(result, yValue) {
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

function renderBarLineChart(result, chart, config) {
    if (result.length == 0) {
        return renderEmptyStatistics(chart, config.additionalConfig);
    } else {
        //If the target type for the Y axis is 'time', get average time from sub aggregate of the result.
        let data = config.barChartConfig?.yValue ? processBarChartYValue(result, config.barChartConfig?.yValue) : result;
        let stepSize = config.barChartConfig?.yValue === 'time' ? 200 : 2;
        let html = renderChartCanvas(chart.getAttribute(DATA_CHART_ID));

        const chartTitleConfig = config.chartType === 'bar' ? config.barChartConfig : config.lineChartConfig;

        $(chart).html(html);
        let canvasObject = $(chart).find('canvas');
        return new Chart(canvasObject, {
            type: config.chartType,
            data: {
                labels: data.map(bucket => formatChartLabel(bucket.key)),
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
                            text: chartTitleConfig.yTitle,
                            display: true
                        },
                        ticks: {
                            stepSize: stepSize
                        }
                    },
                    x: {
                        title: {
                            text: chartTitleConfig.xTitle,
                            display: true
                        }
                    }
                }
            }
        });
    }
}

function updateTitle(chartId, chartType, title) {
    $('.dashboard__widget').each(function () {
        if ($(this).find('div[' + DATA_CHART_ID + '=' + chartId + ']').length
            && 'number' !== chartType) {
            $(this).find(WIDGET_HEADER_TITLE).text(title);
            return;
        }
    });
}

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
    let data = await response.data.then(data => chartInfo?.barChartConfig?.yValue ? getDataFromResult(data) : data);
    let result = data.result.aggs?.[0]?.buckets ?? [];
    let chartData = chartInfo.chartData;
    if (chartInfo.chartType !== 'number') {
        chartData.data.labels = result.map(bucket => formatChartLabel(bucket.key));
        chartData.data.datasets.forEach(dataset => dataset.data = result.map(bucket => bucket.count));
        chartData.update();
    } else {
        let config = response.data.chartConfig;
        let filters = getChartFilters(data);
        result = fillUpResultBasedOnFilter(config.aggregates, result, filters);

        let multipleKPI = renderMultipleNumberChartInHTML(result, config.numberChartConfig.suffixSymbol);
        $(chartData).html(multipleKPI);
    }
}

function initStatistics() {
    let refreshInfos = [];
    $('.chart-options').each(async (index, chart) => {
        let chartId = chart.getAttribute(DATA_CHART_ID);
        let response;
        try {
            response = await instance.post(statisticApiURL, {"chartId": chartId});
        } catch (error) {
            renderNoPermissionStatistics(chart, error.response.data);
            return;
        }
        let data = await response.data;
        let result = data.result.aggs?.[0]?.buckets ?? [];
        let config = data.chartConfig;
        let chartType = config.chartType;
        let refreshInterval = config.refreshInterval;
        let chartData;
        locale = config.locale;
        updateTitle(chartId, chartType, config.name);
        if ('number' === chartType) {
            chartData = renderNumberChart(chart, data);
        }
        if ('bar' === chartType || 'line' === chartType) {
            chartData = renderBarChart(chart, data);
        }
        if ('pie' === chartType || 'doughnut' === chartType) {
            chartData = renderPieChart(chart, data);
        }

        let chartObject = {
            'chartData': chartData,
            'chartType': chartType,
            'chartId': chartId,
            'refreshInterval': refreshInterval
        }

        if (chartData !== undefined) {
            refreshInfos.push(chartObject);
        }

        initRefresh(refreshInfos);

    });
}

function formatChartLabel(label) {
    if (isNumeric((new Date(label)).getTime())) {
        return formatDateFollowLocale(new Date(label));
    }
    return label
}

function renderEmptyStatistics(chart, additionalConfig) {
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

function renderNoPermissionStatistics(chart, noPermissionChartMessage) {
    let noPermissionChartHtml =
        '<div class="process-dashboard-widget__empty-process empty-message-container">' +
        '    <i class="si si-lock-1 empty-message-icon"></i>' +
        '    <br><span class="empty-message-text">' + noPermissionChartMessage + '</span>' +
        '</div>';
    $(chart).html(noPermissionChartHtml);
}

function renderChartCanvas(chartId) {
    let html = '<canvas id="' + chartId + '" />';
    return html;
};

function renderNumberChartHtml(label, number, suffixSymbol) {
    label = formatChartLabel(label);
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
        '        <span class="card-name chart-name-font-size chart-number-animation">' + normalizeLabel(label) + '</span>' +
        '    </div>' +
        '</div>';
    return html;
};

function normalizeLabel(label) {
    if (label === 'IN_PROGRESS') {
        return 'In Progress';
    }

    return label.charAt(0) + label.slice(1).toLowerCase();
}

function renderMultipleNumberChartInHTML(result, suffixSymbold) {
    let multipleNumberChartInHTML = '';

    if (result.length > 0) {
        for (item of result) {
            let htmlString = renderNumberChartHtml(item.key, item.count, suffixSymbold);
            multipleNumberChartInHTML += htmlString;
        }
    } else {
        multipleNumberChartInHTML = renderNumberChartHtml('', '0', suffixSymbold);
    }
    return multipleNumberChartInHTML;
}

function getChartFilters(data) {
    return data.chartConfig.filter?.split(',');
}

function fillUpResultBasedOnFilter(aggregateType, result, filters) {
    if (result.length == filters.length) {
        return result;
    }

    if (result.length == 0) {
        // Handle empty result in case of aggregate type is 'businessState'
        if (aggregateType === "businessState") {
            return fillEmptyResultForBusinessStateAggregate(filters);
        }

        // For generic aggregate type, just show 0 when the result is empty.
        result.push({
                key: '',
                count: 0,
                aggs: []
            })
    } else if (result.length < filters.length) {
        let currentResult = result.map(item => item.key);
        let missingPieces = filters.filter(item => !currentResult.includes(item));
        missingPieces.forEach(piece => {
            result.push({
                key: piece,
                count: 0,
                aggs: []
            })
        });
    }

    return result;
}

function fillEmptyResultForBusinessStateAggregate(filters) {
    const BUSINESS_STATE_FILTER = 'businessState:';
    let result = [];

    filters.filter(filter => filter.startsWith(BUSINESS_STATE_FILTER))
        .forEach(filter => {
            // Get states from filter. Expected result: ['OPEN','DONE']
            const states = filter.replace(/^businessState:/, "").split(' ');

            // Put states to an array of results.
            states.forEach(state => {

                // Only add state to the result array when it's not existed in the result.
                const isExisted = result.find(item => item.key === state);

                if (!isExisted) {
                    result.push({
                        key: state,
                        count: 0,
                        aggs: []
                    });
                }
            });
        });
    
    return result;
}
