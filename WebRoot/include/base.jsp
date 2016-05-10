<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
    //本地 的配置
	String path2 = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path2 + "/";
%>

	
<!-- Bootstrap core CSS -->
<link href="/hyst/css/bootstrap/bootstrap.css" rel="stylesheet">
<!-- 表单页单独引用 -->
<!-- <link href="/hyst/css/bootstrap/bootstrap-table.min.css" rel="stylesheet"> -->
<!-- <script src="/hyst/js/bootstrap/bootstrap-table.js" type="text/javascript"></script> -->

<!-- http://localhost:8080/hyst/js/jquery.json.js -->
<link href="/hyst/css/bootstrap/navbar-fixed-top.css" rel="stylesheet">

<!-- <link rel="stylesheet" type="text/css" href="/hyst/css/lib/font-awesome.css" />
 -->
<link rel="stylesheet" type="text/css" href="/hyst/css/layout.css" />

<script type="text/javascript" src="/hyst/js/jquery.js"></script>

<script src="/hyst/js/bootstrap/bootstrap.js" type="text/javascript"></script>



<script type="text/javascript">
	<%-- alert("<%=basePath%>"); --%>
</script>
 	
 	
 	
 	