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
    <title>保密门户角色设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
 	<link rel="stylesheet" type="text/css" media="all" href="css/pickList.css" />
	<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

	<script type="text/javascript">
	$(function(){
		/**取得部门数据*/
		setDepts();
		$("#dept").change(function(){
			alert("------");
		});
		/**保存按钮被点击后触发，提交保存数据*/	
		$("#submit").click(function(){
			submitData();
		});
	
		
	});
		/**为部门选择框设置部门数据*/
		function setDepts(){
			/**取得部门数据*/
			$.post("safe/getOrgs.do",function(deptdata){
				$("#pickList").pickList({
					data: deptdata
				});
				$("#pickData option").remove();
				$("#pickListResult option").remove();
				var htm="";
				for(var i=0;i<deptdata.length;i++){
					htm+="<option value='"+deptdata[i].id+"'>"+deptdata[i].orgName+"</option>";
				}
				$("#dept").append(htm);
			});
			
		}
		/**数组测试*/
		/**获取选择的人员*/
		function setUsers(){
			var usersIds=new Array();
			usersIds=$("#users").val().split("|");
			if($("#users").val()==""||$("#users").val()==null){
				return ;
			}
			var userIds=new Array();
			for(var i=0;i<usersIds.length;i++){
				userIds.push(Number(usersIds[i]));
			}
			$.ajax({//根据用户ID查询出用户视图
				url: 'safe/usersid.do', //提交地址
				type: 'POST',//方式
				traditional: true,
				data: {"uids":userIds} ,//+usersId2,//提交的数据
				async: true,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					
					alert("保存失败");
				},
				success: function(data) {
					
					alert(data);
				}
			});
		}
		/**提交数据*/
		function submitData(){
			//setUsers();
			var dat="ID="+$("#id").val()+"&userId=";
			$("#pickListResult option").each(function(){
				dat+=$(this).val()+"|";
			});
			
			alert(dat+"---"+$("#pickListResult option").length);
			/* $.ajax({
				url: 'safe/savenewcreditdetail.do', //提交地址
				type: 'POST',//方式
				data: datas,//提交的数据
				async: true,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					
					alert("保存失败");
				},
				success: function(data) {
					
					alert(data);
				}
			}); */
		}
		
	</script>

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	 <div class="container"> 
	 <!-- 视图名称 bean -->
		<div class="jumbotron" style="margin-left: 100px;">
			<input type="hidden" value="${modle.id }" id="id">
			<label for="roleType">用户角色</label>
			<select id="roleType" disabled="disabled">
				<option value="${webRole.roleType }">${webRole.role } </option>
			</select>
			
			<label for="dept">选择部门</label>
			<select id="dept" >
				<option value="0">--请选择--</option>
			</select>
						
			<%-- <label for="roleType" >管理角色</label>
			<select id="roleType" disabled="disabled">
				<option value="${modle.roleType }">${modle.typeName }</option>
			</select> --%>
			<!-- 部门左右移动块 -->
			<label for="selectuser" >人员筛选：</label><input id="selectuser" type="text" > <button id="check">查询</button>
			<input type="hidden" value="${modle.typeName }" id="users">
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
