const instance = axios.create({
    baseURL: '/',
    timeout: 60000,
    headers: {'X-Requested-By': 'ivy'}
});
const CHART_COLORS = [
    'hsl(192, 63%, 70%)',
    'hsl(192, 63%, 60%)',
    'hsl(192, 63%, 50%)',
    'hsl(192, 63%, 40%)',
    'hsl(192, 63%, 30%)',
    'hsl(192, 63%, 20%)',
    'hsl(192, 63%, 10%)',
];

$(document).ready(function () {
    const userActionChart1 = async () => {
        const chart = document.getElementById('chart1');
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});
        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        chart1Object = new Chart(chart, {
            type: data.chartType,
            label: data.label,
            data: {
                labels: result.map(bucket => bucket.key),
                datasets: [{
                    label: data.label,
                    data: result.map(bucket => bucket.count),
                    backgroundColor: CHART_COLORS
                }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: data.barChartConfig.yTitle,
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: data.barChartConfig.xTitle,
                            display: true
                        }
                    }
                }
            }
        });
    }

    setInterval(() => {
        if (typeof chart1Object != "undefined") {
            userActionChartNew(chart1Object);
        }
    }, 10000);

    const userActionChartNew = async (chartObject) => {
        const response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": "3"});
        const result = await response.data.aggs[0].buckets;
        chartObject.data.labels = result.map(bucket => bucket.key);
        chartObject.data.datasets.forEach(dataset => {
            dataset.data = result.map(bucket => bucket.count);
          });
        chartObject.update();
    }

    const userActionChart2 = async () => {
        const chart = document.getElementById('chart2');
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});
        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        new Chart(chart, {
            type: data.chartType,
            data: {
                labels: result.map(bucket => bucket.key.substring(0, 10)),
                datasets: [{
                    label: data.label,
                    data: result.map(bucket => bucket.count),
                    backgroundColor: CHART_COLORS
                }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: data.barChartConfig.yTitle,
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: data.barChartConfig.xTitle,
                            display: true
                        }
                    }
                }
            }
        });
    }

    const userActionChart3 = async () => {
        const chart = document.getElementById('chart3');
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});
        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        $('#card-number').text(result.map(bucket => bucket.count));
    }

    const userActionChart4 = async () => {
        const chart = document.getElementById('chart4');
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});
        let data = await response.data;
        let result = data.result.aggs[0].buckets;

        new Chart(chart, {
            label: data.label,
            type: data.chartType,
            data: {
                labels: result.map(bucket => bucket.key.substring(0, 10)),
                datasets: [{
                    label: 'DONE',
                    borderWidth: 1,
                    backgroundColor: CHART_COLORS,
                    data: result.map(bucket => bucket.count)
                }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: data.barChartConfig.yTitle,
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: data.barChartConfig.xTitle,
                            display: true
                        }
                    }
                }
            }
        });
    }

    userActionChart1();
    userActionChart2();
    userActionChart3();
    userActionChart4();
});