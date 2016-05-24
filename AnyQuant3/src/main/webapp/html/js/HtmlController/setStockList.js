function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}
function fillTable(datas){
	//alert(datas);
	//var datas = [
	    //["19995","大名清单",2.4,5.2,10.23,10.44,10.99,10.0,1000,1000,1000,10],
	    //["19993","大名清单",-2.4,-5.2,10.23,10.44,10.99,10.0,1000,1000,1000,10],
	    //["19992","大名清单",0,0,10.23,10.44,10.99,10.0,1000,1000,1000,10]
	//];
   // alert("data");
	//alert(datas);
	
	    $('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
	    $('#example').dataTable( {
	        "aaData": datas,
	        "aoColumns": [
	            { "sTitle": "股票代码" ,
	              "render":function(data,type,row,meta){
	              	return "<a href='../Individual.html'>"+data+"</a>";
	              }
	            },
	            { "sTitle": "股票简称" },
	            { "sTitle": "涨跌额" ,
	           	  "render":function(data,type,row,meta){
	              	if	(data>0)
	              		return "<span style='color:red'>"+data+"</span>";
	              	else if (data<0)
	              		return "<span style='color:green'>"+data+"</span>";
	              	else
	              		return data;
	              }
	            },
	            { "sTitle": "涨跌幅"  ,
	        	  "render":function(data,type,row,meta){
	              	if	(data>0)
	              		return "<span style='color:red'>"+data+"</span>";
	              	else if (data<0)
	              		return "<span style='color:green'>"+data+"</span>";
	              	else
	              		return data;
	              }
	},
	            { "sTitle": "昨收" },
	            { "sTitle": "今开" },
	            { "sTitle": "最高" },
	            { "sTitle": "最低" },
	            { "sTitle": "资金流入/万" },
	            { "sTitle": "成交量/手" },
	            { "sTitle": "成交额" },
	           {
	                "sTitle": "换手率",
	                "sClass": "center",
	                "fnRender": function(obj) {
	                    var sReturn = obj.aData[ obj.iDataColumn ];
	                    if ( sReturn == "A" ) {
	                        sReturn = "<b>A</b>";
	                    }
	                    return sReturn;
	                }
	            }
	        ]
	    } );    
	
}


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
