var baseUrl = "http://localhost:8080/AnyQuant/data.do?"
function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}

function kData() {
    //code
    var req = createAjaxObj();
    req.open("get",baseUrl+"method=kData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function (){
        if (req.readyState==4&&req.status == 200) {
            alert(req.responseText);
        }
    }
    req.send(null);
}
function dData(){
    var req = createAjaxObj();
    req.open("get",baseUrl+"method=dData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status==200) {
            alert(req.responseText);
        }
    }
    req.send(null);
}
function jData() {
    //code
    var req = createAjaxObj();
    req.open("get",baseUrl+"method=jData",false);
    req.setRequestHeader("accept","application/json")
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status == 200) {
            alert(req.responseText);
        }
    }
    req.send(null);
}

function statistics(dataDeal){
	var req = createAjaxObj();
    req.open("get",baseUrl+"method=statistics",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status == 200) {
        	//alert(req.responseText);
        	eval("var result="+req.responseText);
            //alert(result);
        	//alert(result["date"]);
        	//alert(result[date]);
            baseData = [];
            baseData.push(187);
            //baseData.push(result[]);
	    //todo
            var data = [3020.18, 5.0, -71, 3022.2, 3045.2, 3079.2, 3011.3, 150000, 5600020];
            //alert(baseData[0]);
            dataDeal(data);
            
        }
    }
    req.send(null);
}
function kLineData(){
	alert("test");
	var req = createAjaxObj();
    req.open("get",baseUrl+"method=kLineData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
    	alert(req.readyState);
    	alert(req.status);
        if (req.readyState==4 && req.status == 200) {
        	//alert(req.responseText);
        	eval("var result="+req.responseText);
        	for(var i=0;i<result.length;i++){
        		alert(result[i].increase);
        	}
        }
    }
    req.send(null);
}
function volumeData(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=volumeData",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4&&req.statue==200){
		alert(req.responseText);
		
		}
	}
	req.send(null);
}

//********************************************************************************************************************
function getStockList()
{
	//var result;
	//alert("gettttt");
	var backResult;
	var req = createAjaxObj();
	  req.open("get","http://localhost:8080/AnyQuant/data.do?method=stockList&a="+Math.random(),false);
      req.setRequestHeader("accept","application/json"); 
      req.onreadystatechange  = function(){
		  //alert(req.readyState);
		  //alert(req.status);
    	if(req.readyState==4 && req.status==200){  
    		 eval("var result="+req.responseText);
    		// var result = req.responseText;  
			//alert(req.responseText);	
		     
			 var data = [];
			 for(var i=0;i<result.length;i++){
				 var subdata  = [];
				 subdata.push(result[i].id);
				 subdata.push(result[i].name);
				 subdata.push(result[i].increaseValue);
				 subdata.push(result[i].increase);
				 subdata.push(result[i].swing);
				 subdata.push(result[i].close);
				 subdata.push(result[i].open);
				 subdata.push(result[i].high);
				 subdata.push(result[i].low);
				 subdata.push( result[i].flowin);
				 subdata.push(result[i].volume);
				 subdata.push(result[i].volumeValue);
				 subdata.push(result[i].handchange);
				 data.push(subdata);
			 }
			//msgfunction(data);
		    //backResult = data;
			//alert(data);
		    fillTable(data);
			//show();
    	}
        //eval("var result="+req.responseText);
        //document.getElementById("div1").innerHTML=result[0].uname;
      }
      req.send(null);
	  
	  return backResult;
	  
}