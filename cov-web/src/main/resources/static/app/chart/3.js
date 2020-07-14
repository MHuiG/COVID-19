function echarts_3(data) {
    var myChart = echarts.init(document.getElementById('fb1'));
    option = {
        title: [{
            text: '确诊与死亡',
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
            data: ['确诊', '死亡'],
            textStyle: {
                color: 'rgba(255,255,255,.5)',
                fontSize: '12',
            }
        },
        series: [
            {
                name: '确诊与死亡',
                type: 'pie',
                center: ['50%', '42%'],
                radius: ['40%', '60%'],
                color: ['#065aab', '#066eab'],
                label: {show: false},
                labelLine: {show: false},
                // data: data
                data: [
                    {value: 1, name: '确诊'},
                    {value: 4, name: '死亡'},
                ]
            }
        ]
    };
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}