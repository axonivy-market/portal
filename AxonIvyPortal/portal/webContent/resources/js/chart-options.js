const instance = axios.create({
    baseURL: '/',
    timeout: 60000,
    headers: {'X-Requested-By': 'ivy'}
});
$(document).ready(function () {
    const chart1 = document.getElementById('chart1');
    const userActionChart1 = async () => {
        var basePath = window.location.pathname;
        basePath = basePath.substring(0, basePath.indexOf('/faces/'));

        // Calls the Workflow Task Statistic REST API to aggregate states
        const response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": "3"});
        const result = await response.data.aggs[0].buckets;
        new Chart(chart1, {
            type: 'bar',
            data: {
                labels: result.map(bucket => bucket.key),
                datasets: [{
                    label: 'Priority',
                    data: result.map(bucket => bucket.count),
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(255, 159, 64, 0.5)',
                        'rgba(255, 205, 86, 0.5)'
                    ]
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
                            text: 'Count',
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: 'Priority',
                            display: true
                        }
                    }
                }
            }
        });
    }

    const chart2 = document.getElementById('chart2');
    const userActionChart2 = async () => {
        var basePath = window.location.pathname;
        basePath = basePath.substring(0, basePath.indexOf('/faces/'));

        // Calls the Workflow Task Statistic REST API to aggregate startTimeStamps by hour and state
        const response = await fetch('/api/workflow/tasks/stats?agg=startTimestamp:bucket:hour,businessState');
        const result = await response.json();
        new Chart(chart2, {
            type: 'bar',
            data: {
                labels: result.map(bucket => bucket.key),
                datasets: [{
                    label: 'OPEN',
                    borderWidth: 1,
                    backgroundColor: 'gray',
                    data: getStateCount(result, 'OPEN')
                },
                    {
                        label: 'IN_PROGRESS',
                        borderWidth: 1,
                        backgroundColor: 'blue',
                        data: getStateCount(result, 'IN_PROGRESS')
                    },
                    {
                        label: 'DONE',
                        borderWidth: 1,
                        backgroundColor: 'green',
                        data: getStateCount(result, 'DONE')
                    },
                    {
                        label: 'ERROR',
                        borderWidth: 1,
                        backgroundColor: 'red',
                        data: getStateCount(result, 'ERROR')
                    }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: 'Tasks',
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: 'Hour',
                            display: true
                        }
                    }
                }
            }
        });
    }

    const chart3 = document.getElementById('chart3');
    const userActionChart3 = async () => {
        let response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": "5"});
        let result = await response.data.aggs[0].buckets;
        new Chart(chart3, {
            type: 'bar',
            data: {
                labels: ['OPEN'],
                datasets: [{
                    label: 'OPEN',
                    borderWidth: 1,
                    backgroundColor: 'gray',
                    data: result.map(bucket => bucket.count)
                }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: 'Tasks',
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: 'Hour',
                            display: true
                        }
                    }
                }
            }
        });
    }

    const chart4 = document.getElementById('chart4');
    const userActionChart4 = async () => {

        const response = await instance.post('/designer/api/statistic-data-service/Data', {"chartId": "6"});
        const result = await response.data.aggs[0].buckets;
        new Chart(chart4, {
            type: 'bar',
            data: {
                labels: result.map(bucket => bucket.key),
                datasets: [{
                    label: 'DONE',
                    borderWidth: 1,
                    backgroundColor: 'gray',
                    data: result.map(bucket => bucket.count)
                }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            text: 'Tasks',
                            display: true
                        }
                    },
                    x: {
                        title: {
                            text: 'Hour',
                            display: true
                        }
                    }
                }
            }
        });
    }

    function getStateCount(result, state) {
        let aggs = result.map(bucket => bucket.count > 0 ? bucket.aggs.find(a => a.key === state) : undefined);
        let stateCount = aggs.map(a => a === undefined ? 0 : a.count)
        return stateCount;
    }

    function toLabel(utc) {
        const date = new Date(utc);
        return date.toLocaleString(undefined, {year: 'numeric', month: 'short', day: 'numeric', hour: 'numeric'});
    }

    userActionChart1();
//userActionChart2();
    userActionChart3();
    userActionChart4();
});