<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
  <title>My JSP 'index.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">	
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script>
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
  </script>
  </head>
  <body>
  <a href="javascript:void(0);" onclick="sendAjaxReq();">����</a>
  <div id="div1"></div>
  </body>
</html>