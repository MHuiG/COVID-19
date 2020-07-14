// $(function () {
//     $.ajax({
//         type: 'get',
//         url: '/get_ncov_totalcount',
//         dataType: 'json',
//         success: function (res) {
//             document.getElementById("confirm").innerHTML = res['confirmedCount'];
//             document.getElementById("suspect").innerHTML = res['suspectedCount']
//         }
//     })
// });

$(document).ready(function () {
    // $("button").click(function () {
    $.get("http://api.tianapi.com/txapi/ncovabroad/index?key=82611c2b6ffe5174234c44eb5e592ff1",
        function (data, status) {
            // console.log(data["newslist"][0]["confirmedCount"]);
            $('#result').append(JSON.stringify(data)); //返回内容绑定到ID为result的标签
            document.getElementById("confirm").innerHTML = data["newslist"][0]["confirmedCount"];
            document.getElementById("suspect").innerHTML = data["newslist"][0]['deadCount']

            // alert("状态码：" + data.code + "\n消息：" + data.msg);
        });
    // });
});
