if ("WebSocket" in window) {
    console.log("您的浏览器支持 WebSocket!");

    // 打开一个 web socket
    var ws = new WebSocket("ws://localhost:8080/cov/get_map_data");

    ws.onopen = function () {
        // Web Socket 已连接上，使用 send() 方法发送数据
        ws.send("发送数据");
        console.log("数据发送中...");
    };

    ws.onmessage = function (evt) {
        var received_msg = evt.data;
        // console.log(evt.data);
        window.mapdata=evt.data;
        make_map();
        console.log("数据已接收...")
    };

    ws.onclose = function () {
        // 关闭 websocket
        console.log("连接已关闭...");
    };
} else {
    // 浏览器不支持 WebSocket
    console.log("您的浏览器不支持 WebSocket!");
}