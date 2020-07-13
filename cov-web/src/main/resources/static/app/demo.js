function make_map(mapdata) {
    try {
        achart = echarts.init(document.getElementById("美国"));
        console.log("mapdata==========================>\n" + mapdata);
        var data = JSON.parse(mapdata);

        for (var i = 0; i < data.length; i++) {
            data[i].value = data[i].confirmedNum;
        }
        console.log("data==========================>\n" + JSON.stringify(data[0]));
        var option = {
            tooltip: {
                trigger: "item",
                backgroundColor: "rgba(64,76,76,0.5)",

                formatter: function (params) {
                    return params["data"].name + "<br/>确诊人数：" + params["data"].confirmedNum + "<br/>治愈人数：" + params["data"].curesNum
                        + "<br/>死亡人数：" + params["data"].deathsNum + "<br/>治愈率：" + params["data"].curesRatio + "<br/>死亡率：" + params["data"].deathsRatio
                        + "<br/>在治人数：" + params["data"].treatingNum + "<br/>确诊增量：" + params["data"].confirmedIncr + "<br/>无症状人数：" + params["data"].asymptomaticNum
                        + "<br/>无症状增量：" + params["data"].asymptomaticIncr
                }
            },
            title: [{
                textStyle: {
                    color: "#000",
                    fontSize: 18
                },
                subtext: "",
                text: "",
                top: "auto",
                subtextStyle: {
                    color: "#aaa",
                    fontSize: 14
                },
                left: "auto"
            }
            ],
            backgroundColor: "#fff",
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            visualMap: {
                min: 1000,
                max: 500000,
                text: ['High', 'Low'],
                realtime: false,
                calculable: false,
                show: false,
                inRange: {
                    color: ["#0ff", "#ff0", "#f00"]
                }
            },
            "series": [{
                "mapType": "美国",
                "data": data,
                "symbol": "circle",
                "roam": false,
                "type": 'map',

            },
            ],
        };
        achart.setOption(option);
    } catch (e) {
        console.log(e);
    }
}

