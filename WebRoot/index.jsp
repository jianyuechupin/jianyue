<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>保密平台首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%@include file="/include/base.jsp"%>



<link rel="stylesheet" href="css/style.css">
<style>
	
	.toTop{
		background-image:-webkit-linear-gradient(to top, #F8FFED,rgba(116, 145, 150, 0.63));
		background-image:linear-gradient(to top,#F8FFED,rgba(116, 145, 150, 0.63));
		
	}
	/* .toTop:before {
		content: "";
		left: 0;
  		right: 0;
  		top:0;
		width: 1440px;
  		height: 900px;
		background-image: url("img/1 (14).jpg");
		background-repeat: repeat;
		z-index: -1;
		position: absolute;
		-webkit-filter: blur(5px);
	} */
</style>


<script type="text/javascript" src="js/hyst.js"/>
<script type="text/javascript">
	function tanchuang (){
		hyst.msg.alert("哈哈");
	}
</script>
</head>
<body class="toTop">
	
	<!-- 引入导航头 -->
	<%@include file="/include/top.jsp"%>
	<!-- 引入左侧导航 -->
	<%@include file="/include/left.jsp"%>
	
	
	<div class="content ">
		<div align="center">
			<h1>
				<span id="time" style="color: red;"></span>
			</h1>
		</div>
		<br>
		<button id="post">Ajax请求POST</button>
		<br>
		<button id="get">Ajax请求GET</button>
		<br>
		
		<button onclick="tanchuang()">
			弹窗
		</button>
	</div>
		

	
</body>
</html>
