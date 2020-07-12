function make_map(cityname, dom_id){
  achart = echarts.init(document.getElementById(dom_id));
  var data = [
{name:"Wisconsin", "curesNum":29101,"deathsNum":15553,
  "curesRatio":0.16328879,"deathsRatio":0.08726952,"treatingNum":133564,
  "confirmedIncr":593,"asymptomaticNum":0,"asymptomaticIncr":0},
{name:"Wyoming", "curesNum":29101,"deathsNum":15553,
  "curesRatio":0.16328879,"deathsRatio":0.08726952,"treatingNum":133564,
  "confirmedIncr":593,"asymptomaticNum":0,"asymptomaticIncr":0},
{name:"Puerto Rico", "curesNum":29101,"deathsNum":15553,
  "curesRatio":0.16328879,"deathsRatio":0.08726952,"treatingNum":133564,
  "confirmedIncr":593,"asymptomaticNum":0,"asymptomaticIncr":0},
{"name":"Oregon","curesNum":29101,"deathsNum":15553,
  "curesRatio":0.16328879,"deathsRatio":0.08726952,"treatingNum":133564,
  "confirmedIncr":593,"asymptomaticNum":0,"asymptomaticIncr":0},
];

	var socket;
	$("#connect").click(function(event){
		socket = new WebSocket("ws://127.0.0.1:8000/get_map_data");
		socket.onopen = function(){
			alert("Socket has been opened");
		}
		socket.onmessage = function(msg){
			alert(msg.data);
		}
		socket.onclose = function() {
			alert("Socket has been closed");
		}
	});
	$("#send").click(function(event){
		socket.send("send from client");
	});
	$("#close").click(function(event){
		socket.close();
	})

for(var i=0;i<data.length;i++){
    data[i].value=data[i].curesNum;
}

  var option =  {
	tooltip: {
		trigger:"item",
		backgroundColor: "rgba(64,76,76,0.5)",
		
		formatter:function(params) {
		return params["data"].name+"<br/>确诊人数："+params["data"].confirmedNum+"<br/>治愈人数："+params["data"].curesNum
		+"<br/>死亡人数："+params["data"].deathsNum+"<br/>治愈率："+params["data"].curesRatio+"<br/>死亡率："+params["data"].deathsRatio
		+"<br/>在治人数："+params["data"].treatingNum+"<br/>确诊增量："+params["data"].confirmedIncr+"<br/>无症状人数："+params["data"].asymptomaticNum
		+"<br/>无症状增量："+params["data"].asymptomaticIncr
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
			color: ["#0ff","#ff0","#f00"]
		}
	},
    "series": [{
		"mapType": cityname,
		"data": data,
		"symbol": "circle",
		"roam": false,
		"type": 'map',
		
    }, 
	],}

    achart.setOption(option);
}

