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
</head>

<body>
	<!-- 引入导航头 -->
	<%@include file="/include/top.jsp"%>
	<!-- 引入左侧导航 -->
	<%@include file="/include/left.jsp"%>
	<div class="content">
		This is my JSP page. <br>

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
				<td>
					<button id="ge">Ajax请求GET</button>
				</td>
				<td id="name">123</td>
			</tr>
		</table>
	</div>


	<!-- scripts -->
	<script type="text/javascript" src="js/jquery.js"></script>

</body>
</html>
