var baseUrl = "http://localhost:8080/AnyQuant/data.do?";
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
            // alert(req.responseText);
            eval("var result="+req.responseText);
            return result;
        }
    };
    req.send(null);
}
function dData(){
    var req = createAjaxObj();
    req.open("get",baseUrl+"method=dData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status==200) {
            //alert(req.responseText);
            eval("var result="+req.responseText);
            return result;
        }
    };
    req.send(null);
}
function jData() {
    //code
    var req = createAjaxObj();
    req.open("get",baseUrl+"method=jData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status == 200) {
            //alert(req.responseText);
            eval("var result="+req.responseText);
            return result;
        }
    };
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
            //最新价暂无数据，以后更改或者删去；
            baseData.push(2323);
            //
            baseData.push(result.increase);
            baseData.push(result.increaseValue);
            baseData.push(result.open);
            baseData.push(result.close);
            baseData.push(result.high);
            baseData.push(result.low);
            baseData.push(result.volume);
            baseData.push(result.volumeValue);
            //baseData.push(result[]);
	    //todo
	    //最新，涨幅，涨跌，今开，昨收，最高，最低，成交量，成交额
	       
            //var data = [3020.18, 5.0, -71, 3022.2, 3045.2, 3079.2, 3011.3, 150000, 5600020];
            //alert(baseData[0]);
            dataDeal(baseData);
            
        }
    };
    req.send(null);
}
function kLineData(){
	//alert("test");
	var req = createAjaxObj();
    req.open("get",baseUrl+"method=kLineData",false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
    	//alert(req.readyState);
    	//alert(req.status);
        if (req.readyState==4 && req.status == 200) {
        	//alert(req.responseText);
        	// 开盘，收盘，最低，最高  
        	eval("var result="+req.responseText);
        	//alert(result[0].open);
        	var backResult = [];
        	for(var i=0;i<result.length;i++){
        		var singleArray = [];
        		singleArray.push(result[i].open);
        		singleArray.push(result[i].close);
        		singleArray.push(result[i].low);
        		singleArray.push(result[i].high);
        		backResult.push(singleArray);
        		
        		//alert(singleArray);
        	}
        	//alert(backResult);
        	return backResult;
        }
    };
    req.send(null);
}
function volumeData(){
	//alert("get");
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=volumeData",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		//alert(req.readyState);
		//alert(req.status);
		if(req.readyState==4&&req.status==200){
		//alert(req.responseText);
		eval("var result="+req.responseText);
		///alert(result[0]);
		//alert(result[1]);
		return result;
		}
	};
	req.send(null);
}

function swing(){
	//alert("get");
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=swing",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		//alert(req.readyState);
		//alert(req.status);
		if(req.readyState==4&&req.status==200){
			eval("var result="+req.responseText);
			alert(result);
			return result;
		}
	};
	req.send(null);
}
//macdData
function macdData(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=macdData",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4&&req.status==200){
			// alert(req.responseText);
			eval("var result="+req.responseText);
			return result;
			
		}
	};
	req.send(null);
}
//ema12Data
function ema12Data(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=ema12Data",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4&&req.status==200){
			eval("var result="+req.responseText);
			alert(result);
			return result;
		}
		
	};
	req.send(null);	
}
//ema26Data
function ema26Data(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=ema26Data",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4&&req.status==200){
			eval("var result="+req.responseText);
			return result;
		}
	};
	req.send(null);
	
}
//diffData
function diffData(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=diffData",false);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange=function(){
		if(req.readyState==4&&req.status==200){
			eval("var result="+req.responseText);
			return result;
		}
	};
	req.send(null);
}
//barData
function barData(){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=barData",false);
	req.setRequestHeader("accept","application/json",false);
	req.onreadystatechange = function(){
		if(req.readyState==4&&req.status==200){
			eval("var result="+req.responseText);
			return result;
		}
	};
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
      };
      req.send(null);
	  
	  return backResult;
	  
}