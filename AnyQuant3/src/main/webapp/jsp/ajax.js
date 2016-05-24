function createAjaxObj(){
      var req;
      if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
      }else{
        req = new ActiveXObject("Msxml2.XMLHTTP");  //ie
      }
      return req;
    }
    function sendAjaxReq(){
      var req = createAjaxObj();
      req.open("get","myajax.do?method=test1&a="+Math.random());
      req.setRequestHeader("accept","application/json"); 
      req.onreadystatechange  = function(){
    	if(req.readyState==4 && req.status==200){  
    		 eval("var result="+req.responseText);
    		// var result = req.responseText;    		 
             for(var i=0;i<result.length;i++){
            	 alert(result[i].userName);
             }
    	
    	}
        //eval("var result="+req.responseText);
        //document.getElementById("div1").innerHTML=result[0].uname;
      }
      req.send(null);
    }