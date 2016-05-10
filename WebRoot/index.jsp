<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	/hyst/WebRoot/JS/jquery.js-->
	<script type="text/javascript" src="JS/jquery.js"></script>
	<script type="text/javascript">
	
	setInterval("timer()",1000);//1000为1秒钟
		var t=0; var c=0; 
		function timer(){
		var f=" : ";
			if(t<10)
				f=" : 0";
			$("#time").html( c+f+t++);
			if(t==60){
				c++;
				t=0;
			}
		}
	
	$(function(){

		$("#post").click(function(){
		alert("ajax");
			$.post("user/ajax.do",{"name":"张三丰"},function(data){
				var htm="";
				for(var i=0;i<data.length;i++){
					htm+="<tr><td>"+data[i].id+"</td><td>"+
						data[i].name+"</td><td>"+data[i].pwd+
						+"</td></tr>";
				}
				$('#list').append(htm);
			});
		});
		$("#get").click(function(){
		
			$.get("user/ajax.do",{"name":"张三丰"},function(data){
				
			});
		});
		
		$("#ge").click(function(){
			$.get("user/getf.do",{"name":"张三丰"},function(data){
				$("#name").text(data);
			});
		});
	});
	
		
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
      	
  	<div align="center">
  		<h1 ><span id="time" style="color: red;"></span></h1>
  	</div>
  	<br>
    <button id="post">Ajax请求POST</button><br>
    <button id="get">Ajax请求GET</button>
    <br>
    <table id="list" width="100%">
    	<tr id="lisy">
  			<td>编号</td>
  			<td>姓名</td>
  			<td>密码</td>
  		</tr>
    </table>
    <table width="80%">
    <tr>
    	<td><button id="pos">Ajax请求POST</button></td>
   		<td> <button id="ge">Ajax请求GET</button></td>
   		<td id="name">123</td>
    </tr></table>
  </body>
</html>
