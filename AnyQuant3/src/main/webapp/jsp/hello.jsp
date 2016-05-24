<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="./jquery/js/jquery-2.1.1.js"></script>  
<script type="text/javascript">  
$(function(){
    $("#btn").click(function(){
        $.post("mvc/getPerson",{name:$("#name").val()},function(data){
            alert(data);
        });
    });
});
</script>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello
</body>
</html>