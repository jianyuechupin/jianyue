<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
	/demo2/WebRoot/js/jquery.js-->
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.json.js"></script>
	<script type="text/javascript">
		alert(${list });
	</script>
  </head>
  
  <body>
  	<table width="80%" align="center">
  		<tr>
			<tr>
				<td ><input type="checkbox"></td>
				<td name="">单位</td>
				<td name="">责任人</td>
				<td name="">名称型号</td>
				<td name="">密级</td>
				<td name="">安装位置</td>
				<td name="">管理地址</td>
				<td name="">启用时间</td>
				<td name="">序列号</td>
				<td name="">IOS版本</td>
				<td name="">主要用途</td>
				<td name="">使用情况</td>
				<td name="">备注</td>
			</tr>
  		</tr>
  		<c:forEach items="${list }" var="bean"  varStatus="status">
  			<tr>
  				<td><label><input type="checkbox" id="select">选择 ${status.index}</label></td>
  				
				<td name="1">${bean.orgName }</td>
				<td name="2">${bean.staffName }</td>
				<td name="3">${bean.sbXH }</td>
				<td name="4">${bean.secretLevelName }</td>
				<td name="5">${bean.placement }</td>
				<td name="6">${bean.manageAddr }</td>
				<td name="7">${bean.startUsingTime }</td>
				<td name="8">${bean.serialNum }</td>
				<td name="9">${bean.isOVersionNum }</td>
				
				<td name="10">${bean.useage }</td>
				<td name="11">${bean.useStateName }</td>
				<td name="12">${bean.infoMemo }</td>
  			</tr>
  		</c:forEach>
  		
  	</table>

  	

  </body>
</html>
