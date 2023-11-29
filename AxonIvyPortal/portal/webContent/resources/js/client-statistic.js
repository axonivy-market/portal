const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const statisticApiURL = '/api/statistics/data';
const instance = axios.create({
    baseURL: baseURL,
    timeout: 60000,
    headers: {'X-Requested-By': 'ivy'}
});
const DATA_CHART_ID = 'data-chart-id';
const WIDGET_HEADER_TITLE = '.widget__header-title';

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

function formatISODate(dt) {
    let year = dt.getFullYear();
    let month = dt.getMonth() < 10 ? '0' + dt.getMonth() : dt.getMonth();
    let date = dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();
    return year + '-' + month + '-' + date;
}

$(document).ready(function () {
    initStatistics();
});

function renderNumberChart(chart, data) {
    let dataResult = data.result.aggs?.[0]?.buckets ?? [];
    let config = data.chartConfig;
    initWidgetHeaderName(chart, config.name);
    let filters = getChartFilters(data);
    let result = fillUpResultBasedOnFilter(dataResult, filters);

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

function renderBarLineChart(result, chart, config) {
    if (result.length == 0) {
        return renderEmptyStatistics(chart, config.additionalConfig);
    } else {
        let html = renderChartCanvas(chart.getAttribute(DATA_CHART_ID));
        $(chart).html(html);
        let canvasObject = $(chart).find('canvas');
        return new Chart(canvasObject, {
            type: config.chartType,
            data: {
                labels: result.map(bucket => formatChartLabel(bucket.key)),
                datasets: [{
                    label: config.name,
                    data: result.map(bucket => bucket.count),
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
                            text: (config.chartType === "bar" ? config.barChartConfig : config.lineChartConfig).yTitle,
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: (config.chartType === "bar" ? config.barChartConfig : config.lineChartConfig).xTitle,
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
    const response = await instance.post(statisticApiURL, {"chartId": chartId});
    let data = await response.data;
    let result = data.result.aggs?.[0]?.buckets ?? [];
    let chartData = chartInfo.chartData;
    if (chartInfo.chartType !== 'number') {
        chartData.data.labels = result.map(bucket => formatChartLabel(bucket.key));
        chartData.data.datasets.forEach(dataset => dataset.data = result.map(bucket => bucket.count));
        chartData.update();
    } else {
        let config = response.data.chartConfig;
        let filters = getChartFilters(data);
        result = fillUpResultBasedOnFilter(result, filters);

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
        return formatISODate(new Date(label));
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
    const EMPTY_SPACE = '';
    const WHITE_SPACE = ' ';
    let filters = data.chartConfig.filter?.replace(data.chartConfig.aggregates + ':', EMPTY_SPACE);
    filters = filters.split(WHITE_SPACE);

    return filters;
}

function fillUpResultBasedOnFilter(result, filters) {
    if (result.length == filters.length) {
        return result;
    }

    if (result.length == 0) {
        filters.forEach(filter => {
            result.push({
                key: filter,
                count: 0,
                aggs: []
            })
        });
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
