function getUrlParam(key){
	var url = decodeURI( window.location.toString() );
	var arr = url.split("?");
	if(arr.length>1){
		var params = arr[1].split("&");
		for(var i=0; i<params.length; i++){
			var param = params[i];  //"pid=101"
			if(param.split("=")[0] == key ){
				return param.split("=")[1];
			}
		}
	}
	return null;
}


// function timeChange(time) {
// 	var date = time.substr(0, 10); //年月日
// 	var hours = time.substring(11, 13);
// 	var minutes = time.substring(14, 16);
// 	var seconds = time.substring(17, 19);
// 	var timeFlag = date + ' ' + hours + ':' + minutes + ':' + seconds;
// 	timeFlag = timeFlag.replace(/-/g, "/");
// 	timeFlag = new Date(timeFlag);
// 	timeFlag = new Date(timeFlag.getTime() + 8 * 3600 * 1000);
// 	timeFlag = timeFlag.getFullYear() + '-' + ((timeFlag.getMonth() + 1) < 10 ? "0" + (timeFlag.getMonth() + 1) : (timeFlag.getMonth() + 1)) + '-' + (timeFlag.getDate() < 10 ? "0" + timeFlag.getDate() : timeFlag.getDate()) + ' ' + timeFlag.getHours() + ':' + timeFlag.getMinutes() + ':' + (timeFlag.getSeconds() < 10 ? "0" + timeFlag.getSeconds() : timeFlag.getSeconds());
// 	// console.log('asdf:' + timeFlag);
// 	return timeFlag;
// };

function timeChange(time) {
	let date = new Date(time)
	let Str = date.getFullYear() + '-' +
		(date.getMonth() + 1) + '-' +
		date.getDate() + ' ' +
		((date.getHours() + '').length == 1 ? '0' + date.getHours() : date.getHours())+ ':' +
		((date.getMinutes() + '').length == 1 ? '0' + date.getMinutes() : date.getMinutes())+ ':' +
		((date.getSeconds() + '').length == 1 ? '0' + date.getSeconds() : date.getSeconds())
	return Str
}

