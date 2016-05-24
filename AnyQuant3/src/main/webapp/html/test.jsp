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
  <script type="text/javascript" src="/AnyQuant/html/data/stockList.js"></script>
  </head>
  <body>
  <script>
    var result = getStockList();
    alert(result);
  //getStock(getMsg(msg));
  </script>
  <a href="javascript:void(0);" onclick="getStockList();">≤‚ ‘</a>
  <div id="div1"></div>
  </body>
</html>