var datas = [];
var names = [];
var values = [];
$.get("http://api.tianapi.com/txapi/ncovabroad/index?key=82611c2b6ffe5174234c44eb5e592ff1",
    function (data, status) {
        datas.push(data["newslist"][1]);
        datas.push(data["newslist"][2]);
        datas.push(data["newslist"][3]);
        datas.push(data["newslist"][4]);
        datas.push(data["newslist"][5]);
        for (var i = 0; i < 5; i++) {
            names.push(datas[i]["provinceName"])
            values.push(datas[i]["confirmedCount"])
        }
        console.log(datas)
        console.log(names)
        echarts_8(names, values)
        // alert("状态码：" + data.code + "\n消息：" + data.msg);
    });

function echarts_8(names, values) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart5_1'));


    option = {
        //  backgroundColor: '#00265f',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        grid: {
            left: '0%',
            top: '10px',
            right: '0%',
            bottom: '2%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            // data: ['浙江', '上海', '江苏', '广东', '北京', '深圳', '安徽', '四川'],
            // data: data['x_name'],
            data: names,
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1,
                    type: "solid"
                },
            },

            axisTick: {
                show: false,
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
        series: [{
            type: 'bar',
            // data: [2, 3, 3, 9, 15, 12, 6, 4, 6, 7, 4, 10],
            // data: data['data'],
            data: values,
            barWidth: '35%', //柱子宽度
            // barGap: 1, //柱子之间间距
            itemStyle: {
                normal: {
                    color: '#d86a11',
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