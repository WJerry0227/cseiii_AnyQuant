$(function () {
    $('#myTab li:eq(1) a').tab('show');
});

function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}

function getGrailToday(var requestUrl){
	requestUrl = "http://localhost:8080/AnyQuant/data.do?method=grailList&a="+Math.random();
	var req = createAjaxObj();
	req.open("get",requestUrl,false);
      req.setRequestHeader("accept","application/json"); 
      req.onreadystatechange  = function(){
		  //alert(req.readyState);
		  //alert(req.status);
    	if(req.readyState==4 && req.status==200){  
    		 eval("var result="+req.responseText);
    		// var result = req.responseText;  
			//alert(req.responseText);	
		     
			 var data = [];
			 data.push(result.[]);
			 data.push(result.[increase]);
			 data.push(result.[swing]);
			 data.push(result.[open]);
			 data.push(result.[close]);
			 data.push(result.[high]);
			 data.push(result.[low]);
			 data.push(result.[volume]);
			 data.push(result.[volumeValue]);
			//msgfunction(data);
		    //backResult = data;
			//alert(data);
			 return data;
		   // fillTable(data);
			//show();
    	}
        //eval("var result="+req.responseText);
        //document.getElementById("div1").innerHTML=result[0].uname;
      }
      req.send(null);
}

function getDateList(var requestUrl)
{
	requestUrl = "http://localhost:8080/AnyQuant/data.do?method=getDate&a="+Math.random();
	var req = createAjaxObj();
	req.open("get",requestUrl,false);
      req.setRequestHeader("accept","application/json"); 
      req.onreadystatechange  = function(){
		  //alert(req.readyState);
		  //alert(req.status);
    	if(req.readyState==4 && req.status==200){  
    		 eval("var result="+req.responseText);
    		// var result = req.responseText;  
			//alert(req.responseText);	
		     
			 var data = [];
			 data.push(result.[]);
			 data.push(result.[increase]);
			 data.push(result.[swing]);
			 data.push(result.[open]);
			 data.push(result.[close]);
			 data.push(result.[high]);
			 data.push(result.[low]);
			 data.push(result.[volume]);
			 data.push(result.[volumeValue]);
			//msgfunction(data);
		    //backResult = data;
			//alert(data);
			 return data;
		   // fillTable(data);
			//show();
    	}
        //eval("var result="+req.responseText);
        //document.getElementById("div1").innerHTML=result[0].uname;
      }
      req.send(null);
}

function getKlineData(var requestUrl)
{
	requestUrl = "http://localhost:8080/AnyQuant/data.do?method=getDate&a="+Math.random();
	var req = createAjaxObj();
	req.open("get",requestUrl,false);
      req.setRequestHeader("accept","application/json"); 
      req.onreadystatechange  = function(){
		  //alert(req.readyState);
		  //alert(req.status);
    	if(req.readyState==4 && req.status==200){  
    		 eval("var result="+req.responseText);
    		// var result = req.responseText;  
			//alert(req.responseText);	
		     
			 var data = [];
			 data.push(result.[]);
			 data.push(result.[increase]);
			 data.push(result.[swing]);
			 data.push(result.[open]);
			 data.push(result.[close]);
			 data.push(result.[high]);
			 data.push(result.[low]);
			 data.push(result.[volume]);
			 data.push(result.[volumeValue]);
			//msgfunction(data);
		    //backResult = data;
			//alert(data);
			 return data;
		   // fillTable(data);
			//show();
    	}
        //eval("var result="+req.responseText);
        //document.getElementById("div1").innerHTML=result[0].uname;
      }
      req.send(null);
}
