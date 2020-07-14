function echarts_1(data) {
    var myChart = echarts.init(document.getElementById('echart1'));
    var values = [];
    console.log(data);
    for (var i = 0; i < 5; i++) {
        values[i] = data[i].value;
    }
    console.log(values);

    option = {
        //  backgroundColor: '#00265f',
        tooltip: {
            trigger: 'item',
            axisPointer: {
                type: 'shadow'
            },
            formatter: function (params) {
                return params["data"].name + "<br/>" + params["data"].value
            }
        },
        grid: {
            left: '0%',
            top: '10px',
            right: '0%',
            bottom: '4%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            // data: data['x_name'],
            data: data,
            // data: ['NewYork', 'California', 'Texas', 'Florida', 'Illinois'],
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1,
                    type: "solid"
                },
            },

            axisTick: {
                show: false
            },
            axisLabel: {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                //formatter: '{value} %'
                show: true,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
            axisTick: {
                show: false,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1	)",
                    width: 1,
                    type: "solid"
                },
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                }
            }
        }],
        series: [
            {
                type: 'bar',
                data: data,
                // data: [
                //     ('湖北', 300),
                //     ('广东', 250),
                //     ('浙江', 200),
                //     ('河南', 150),
                //     ('湖南', 100)
                // ],
                barWidth: '35%', //柱子宽度
                // barGap: 1, //柱子之间间距
                itemStyle: {
                    normal: {
                        color: '#d5110d',
                        opacity: 1,
                        barBorderRadius: 5,
                    }
                }
            }

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}