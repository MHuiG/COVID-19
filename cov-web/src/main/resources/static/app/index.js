MyWebSocket("get_map_data", make_map);
echarts_1();
echarts_2();
echarts_3();
echarts_4();
echarts_5();
echarts_6();
echarts_7();
echarts_8();

function MyWebSocket(url, fun) {
    var ws = new WebSocket("ws://localhost:8080/cov/" + url);
    ws.onopen = function () {
        ws.send("发送数据");
    };

    ws.onmessage = function (evt) {
        // console.log(evt.data);
        fun(evt.data);
    };

    ws.onclose = function () {
        console.log("连接已关闭...");
    };
    ws.onerror = function (event) {
        console.log("Error...\n" + event);
    };
}

