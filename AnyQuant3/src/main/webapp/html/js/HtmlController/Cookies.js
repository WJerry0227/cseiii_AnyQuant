function writeCookie(id){
	if(isExist(id))
		return;
	var expires = "";
	var date = new Date();
	date.setTime(date.getTime()+(1000*24*60*60*1000));//默认1000天
	expires ="; expires=" +date.toGMTString();
	document.cookie = getCount() + "=" +id + expires + "; path=/";
}

function getCookie(){ 
	var data = new Array;
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 
	
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		data[i] = arr[1];
	} 
	return data; 
} 

function getCount(){
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; ");
	return arrCookie.length;
}

function isExist(id){
	var data = new Array;
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 

	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(id == arr[i])
			return true;
	} 
	return false; 
}

function eraseCookie(id){
	writeCookie(id,"",-1);
}
