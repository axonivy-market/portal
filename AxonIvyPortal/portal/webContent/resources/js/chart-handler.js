const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const instance = axios.create({
    baseURL: baseURL,
    timeout: 60000,
    headers: {'X-Requested-By': 'ivy'}
});
const CHART_COLORS = [
    'hsl(192, 63%, 80%)',
    'hsl(192, 63%, 60%)',
    'hsl(192, 63%, 40%)',
    'hsl(192, 63%, 30%)',
    'hsl(192, 63%, 20%)',
];

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
    let refreshInfos = [];
    $('.chart-options').each(async (index, chart) => {
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/api/statistic-data-service/Data', {"chartId": chartId});
        let refreshInterval = chart.getAttribute('refresh-interval');

        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        let config = data.chartConfig;
        let chartType = config.chartType;
        let chartObject;
        let chartData;

        if ('number' === chartType) {
            let html = renderNumberChart(config.label);
            $(chart).html(html);
            let cardNumber = result.map(bucket => bucket.count) + `${config.numberChartConfig.suffixSymbol}`;
            $(chart).find('.card-number').html(cardNumber);
            chartData = $(chart);
        }
        if ('bar' === chartType) {
            let html = renderBarChart(chartId);
            $(chart).html(html);
            let canvasObject = $(chart).find('canvas');
            chartData = new Chart(canvasObject, {
                type: chartType,
                label: config.label,
                data: {
                    labels: result.map(bucket => formatChartLabel(bucket.key)),
                    datasets: [{
                        label: config.label,
                        data: result.map(bucket => bucket.count),
                        backgroundColor: CHART_COLORS
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: 'top',
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
                label: config.label,
                data: {
                    labels: result.map(bucket => formatChartLabel(bucket.key)),
                    datasets: [{
                        label: config.label,
                        data: result.map(bucket => bucket.count),
                        backgroundColor: CHART_COLORS
                    }],
                    hoverOffset: 4
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false
                }
            });

        }
        if ('line' === chartType) {
            let html = renderPieChart(chartId);
            $(chart).html(html);
            let canvasObject = $(chart).find('canvas');
            chartData = new Chart(canvasObject, {
                type: chartType,
                label: config.label,
                data: {
                    labels: result.map(bucket => formatChartLabel(bucket.key)),
                    datasets: [{
                        label: config.label,
                        data: result.map(bucket => bucket.count),
                        backgroundColor: CHART_COLORS
                    }],
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
                setInterval(() => {
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

});