var ws = new WebSocket("ws://localhost:8080/cov/get_map_data");

ws.onopen = function () {
    // Web Socket 已连接上，使用 send() 方法发送数据
    ws.send("发送数据");
    console.log("数据发送中...");
};

ws.onmessage = function (evt) {
    // console.log(evt.data);
    make_map(evt.data);
    console.log("数据已接收...")
};

ws.onclose = function () {
    // 关闭 websocket
    console.log("连接已关闭...");
};
