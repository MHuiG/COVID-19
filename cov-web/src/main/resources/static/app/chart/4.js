function echarts_4(data) {
    var myChart = echarts.init(document.getElementById('fb2'));
    mydata = data;
    len = JSON.parse(mydata.a).list.length
    data = {};
    data.a = JSON.parse(mydata.a).list[len - 1];
    data.b = JSON.parse(mydata.b).list[len - 1];

    option = {
        title: [{
            text: '确诊与治愈',
            left: 'center',
            textStyle: {
                color: '#fff',
                fontSize: '16'
            }
        }],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)",
            position: function (p) {   //其中p为当前鼠标的位置
                return [p[0] + 10, p[1] - 10];
            }
        },
        legend: {

            top: '70%',
            itemWidth: 10,
            itemHeight: 10,
            data: ['确诊', '治愈'],
            textStyle: {
                color: 'rgba(255,255,255,.5)',
                fontSize: '12',
            }
        },
        series: [
            {
                name: '确诊与治愈',
                type: 'pie',
                center: ['50%', '42%'],
                radius: ['40%', '60%'],
                color: ['#065aab', '#066eab'],
                label: {show: false},
                labelLine: {show: false},
                // data: data
                data: [
                    {value: data.a, name: '确诊'},
                    {value: data.b, name: '治愈'},
                ]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}