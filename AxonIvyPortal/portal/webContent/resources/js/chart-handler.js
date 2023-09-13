const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const instance = axios.create({
    baseURL: baseURL,
    timeout: 60000,
    headers: {'X-Requested-By': 'ivy'}
});

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function formatISODate(dt) {
    let year = dt.getFullYear();
    let month = dt.getMonth() < 10 ? '0' + dt.getMonth() : dt.getMonth();
    let date = dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();
    return `${year}-${month}-${date}`;
}

$(document).ready(function () {
    initStatistics();
});

function initStatistics() {
    var chartColors = [];
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-1-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-2-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-3-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-4-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-5-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-6-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-7-color'));
    chartColors.push(getComputedStyle(document.body).getPropertyValue('--statistics-8-color'));

    let refreshInfos = [];
    $('.chart-options').each(async (index, chart) => {
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/api/statistic-data-service/Data', {"chartId": chartId});
        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        let config = data.chartConfig;
        let chartType = config.chartType;
        let refreshInterval = config.refreshInterval;
        let chartObject;
        let chartData;
        $('.dashboard__widget').each(function() {
            if ($(this).find("div[data-chart-id='"+ chartId + "']").length) {
                if ('number' === chartType) {
                    $(this).find('.widget__header-title').text("");
                } else {
                    $(this).find('.widget__header-title').text(config.name);
                }
                return;
            }
        });
        if ('number' === chartType) {
            let html = renderNumberChart(config.name);
            $(chart).html(html);
            let cardNumber = result.map(bucket => bucket.count) + `${config.numberChartConfig.suffixSymbol}`;
            $(chart).find('.card-number').html(cardNumber);
            chartData = $(chart);
            resizeChartWidget();

        }
        if ('bar' === chartType || 'line' === chartType) {
            let html = renderBarChart(chartId);
            $(chart).html(html);
            let canvasObject = $(chart).find('canvas');
            chartData = new Chart(canvasObject, {
                type: chartType,
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
                                text: config.barChartConfig.yTitle,
                                display: true
                            }
                        },
                        x: {
                            title: {
                                text: config.barChartConfig.xTitle,
                                display: true
                            }
                        }
                    }
                }
            });

        }
        if ('pie' === chartType || 'doughnut' === chartType) {
            let html = renderPieChart(chartId);
            $(chart).html(html);
            let canvasObject = $(chart).find('canvas');
            chartData = new Chart(canvasObject, {
                type: chartType,
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

        chartObject = {
            'chartData': chartData,
            'chartType': chartType,
            'chartId': chartId,
            'refreshInterval': refreshInterval
        }

        if (chartData !== undefined) {
            refreshInfos.push(chartObject);
        }
        if ($('.chart-options').length === refreshInfos.length) {
            initRefresh(refreshInfos);
        }
    });

    function formatChartLabel(label) {
        if (isNumeric((new Date(label)).getTime())) {
            return formatISODate(new Date(label));
        }
        return label
    }

    function initRefresh(refreshInfos) {
        for (let i = 0; i < refreshInfos.length; i++) {
            let refreshInfo = refreshInfos[i];
            if (refreshInfo.refreshInterval && refreshInfo.refreshInterval > 0) {
                 // when init statistic again, e.g., AJAX update statistic, clear exising interval
                if (typeof refreshIntervalId !== 'undefined') {
                    clearInterval(refreshIntervalId);
                }
                refreshIntervalId = setInterval(() => {
                    refreshChart(refreshInfo);
                }, refreshInfo.refreshInterval * 1000);
            }
        }
    }

    async function refreshChart(chartInfo) {
        let chartId = chartInfo.chartId;
        const response = await instance.post('/api/statistic-data-service/Data', {"chartId": chartId});
        const result = response.data.result.aggs[0].buckets;
        let chartData = chartInfo.chartData;
        if (chartInfo.chartType !== 'number') {
            chartData.data.labels = result.map(bucket => formatChartLabel(bucket.key));
            chartData.data.datasets.forEach(dataset => {
                dataset.data = result.map(bucket => bucket.count);
            });
            chartData.update();
        } else {
            let config = response.data.chartConfig;
            let cardNumber = result.map(bucket => bucket.count) + `${config.numberChartConfig.suffixSymbol}`;
            chartData.find('.card-number').html(cardNumber);
        }
    }

    const renderBarChart = (chartId) => {
        let html = `<canvas id="${chartId}"></canvas>`;
        return html;
    };

    const renderPieChart = (chartId) => {
        let html = `<canvas id="${chartId}" ></canvas>`;
        return html;
    };

    const renderNumberChart = (label) => {
        let html = `
                <div class="u-text-align-center chart-content-card">
                    <div class="chart-icon-font-size">
                        <i class="fa-solid fa-chart-line"></i>
                    </div>
                    <div>
                        <span class="card-number chart-number-font-size"></span>
                    </div>
                    <div>
                        <span class="card-name chart-name-font-size">${label}</span>
                    </div>
                </div>
            `;
        return html;
    };
}