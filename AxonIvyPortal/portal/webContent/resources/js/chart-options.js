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

const intervalObjects = [];

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

$(document).ready(function () {
    $('.chart-options').each(async (index, chart) => {
        let chartId = chart.getAttribute('data-chart-id');
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});

        let data = await response.data;
        let result = data.result.aggs[0].buckets;
        let chartType = data.chartType;
        let chartObject;
        if ('number' === chartType) {
            $(chart).find('.card-number').text(result.map(bucket => bucket.count));
        }
        if ('bar' === chartType) {
            let canvasObject = $(chart).find('canvas');
            result.map(bucket => console.log((" " + bucket.key + " " + isNumeric((new Date(bucket.key)).getTime()))));

            chartObject = new Chart(canvasObject, {
                type: chartType,
                label: data.label,
                data: {
                    labels: result.map(bucket => {
                        if (isNumeric((new Date(bucket.key)).getTime())) {
                            return bucket.key.substring(0, 10);
                        }
                        return bucket.key
                    }),
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
        if (chartObject !== undefined) {
            intervalObjects.push({'chartObject': chartObject, 'chartType': chartType, 'chartId': chartId})
        }

    });

    setInterval(() => {
        for (let i = 0; i < intervalObjects.length; i++) {
            let intervalObject = intervalObjects[i];
            if (typeof intervalObject !== "undefined") {
                userActionChartNew(intervalObject.chartObject, intervalObject.chartId);
            }
        }
    }, 10000);

    const userActionChartNew = async (chartObject, chartId) => {
        const response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": chartId});
        const result = response.data.result.aggs[0].buckets;
        chartObject.data.labels = result.map(bucket => bucket.key);
        chartObject.data.datasets.forEach(dataset => {
            dataset.data = result.map(bucket => bucket.count);
        });
        chartObject.update();
    }
});