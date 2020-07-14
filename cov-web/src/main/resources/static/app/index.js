MyWebSocket("get_map_data", make_map);
MyWebSocket("get_bar1_data", echarts_1);
// MyWebSocket("get_line6_data", echarts_6);
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
        fun(JSON.parse(evt.data));
    };

    ws.onclose = function () {
        console.log("连接已关闭...");
    };
    ws.onerror = function (event) {
        console.log("Error...\n" + event);
    };
}


