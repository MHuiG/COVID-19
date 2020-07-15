MyWebSocket("get_map_data", make_map);
MyWebSocket("get_bar1_data", echarts_1);
MyWebSocket("get_bar2_data", echarts_2);
MyWebSocket("get_bar7_data", echarts_7);
OurWebSocket();

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

function OurWebSocket() {
    var ws = new WebSocket("ws://localhost:8080/cov/get_line6_data");
    ws.onopen = function () {
        ws.send("发送数据");
    };

    ws.onmessage = function (evt) {
        echarts_6(JSON.parse(evt.data));
        echarts_5(JSON.parse(evt.data));
        echarts_4(JSON.parse(evt.data));
        echarts_3(JSON.parse(evt.data));
    };

    ws.onclose = function () {
        console.log("连接已关闭...");
    };

}
