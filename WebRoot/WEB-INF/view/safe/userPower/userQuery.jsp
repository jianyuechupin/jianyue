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
    <title>用户查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />
	
	<script type="text/javascript">
		$.post("safe/getOrgs.do",function(data){
			htm="";
			for(var i=0;i<data.length;i++){
				htm+="<option value='"+data[i].id+"'>"+data[i].orgName+"</option>";
			}
			$("#orgIds").append(htm);	
		}); 
		
	</script>

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	 <div class="container"> 
		<div class="jumbotron" style="margin-left: 100px;">
		<form class="niceform" action="safe/query.do" method="post">
		<fieldset>
          <dl>
            <dt><label for="orgId">单位：</label></dt>
            <dd>
             <select size="1" name="orgId" id="orgIds">
              <option value="0">所有单位</option>
             </select>
            </dd>
          </dl>		
		<dl>
			<dt><label for="loginType">登录方式：</label></dt>
			<dd>
				<input type="radio" id="all" name="loginType" id="" value="-1" /><label for="all" class="check_label">全部方式</label>
				<input type="radio" id="zone" name="loginType" id="" value="0" /><label for="zone" class="check_label">密码登陆</label>
				<input type="radio" id="password" name="loginType" id="" value="1" /><label for="password" class="check_label">域登录</label>
			</dd>
		</dl>		
		
        <dl>
            <dt><label for="userName">姓名:</label></dt>
            <dd><input type="text" name="userName" id="" size="54" /></dd>
        </dl>			
        <dl>
            <dt><label for="account">系统账号:</label></dt>
            <dd><input type="text" name="account" id="" size="54" /></dd>
        </dl>			
		<dl class="submit">
			<input type="submit" name="submit" id="submit" value="Submit" />
		</dl>
		</fieldset>
		</form>	
	</div></div>				
  </body>
</html>
