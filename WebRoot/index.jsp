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



<link rel="stylesheet" href="css/alertstyle.css"/>

<link rel="stylesheet" href="js/assets/css/clockstyle.css"/>

<script type="text/javascript" src="js/hyst.js"></script>

<script type="text/javascript">
	function tanchuang (){
		hyst.msg.alert("哈哈");
	}
</script>
</head>
<body>
	
	<!-- 引入导航头 -->
	<%@include file="/include/top.jsp"%>
	<!-- 引入左侧导航 -->
	<%@include file="/include/left.jsp"%>
	
	<br/><br/><br/><br/><br/><br/>
	<div class="content ">
		<div id="clock" class="light">
			<div class="display">
				<div class="weekdays"></div>
				<div class="ampm"></div>
				<div class="alarm"></div>
				<div class="digits"></div>
			</div>
		</div>

		<div class="button-holder">
			<a id="switch-theme" class="button">切换主题</a>
			<a class="alarm-button"></a>
		</div>

		<!-- The dialog is hidden with css -->
		<div class="overlay">
			<div id="alarm-dialog">
				<h2>设置定时提醒</h2>
				<label class="hours">
					时
					<input type="number" value="0" min="0" />
				</label>
				<label class="minutes">
					分
					<input type="number" value="0" min="0" />
				</label>
				<label class="seconds">
					秒
					<input type="number" value="0" min="0" />
				</label>
				<div class="button-holder">
					<a id="alarm-set" class="button blue">开始</a>
					<a id="alarm-clear" class="button red">重置</a>
				</div>
				<a class="close"></a>
			</div>
		</div>

		<div class="overlay">
			<div id="time-is-up">
				<h2>时间到~~</h2>
				<div class="button-holder">
					<a class="button blue">关闭</a>
				</div>
			</div>

		</div>

		<audio id="alarm-ring" preload>
			<source src="js/assets/audio/ticktac.mp3" type="audio/mpeg" />
			<source src="js/assets/audio/ticktac.ogg" type="audio/ogg" />
		</audio>

        <button onclick="tanchuang()">
			弹窗
		</button>
        
		<!-- JavaScript Includes -->
		<script src="js/assets/js/moment.min.js"></script>
		<script src="js/assets/js/script.js"></script>
		
		
	</div>
	
</body>
</html>
