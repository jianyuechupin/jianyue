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
		/**部门数据改变的时候改变人员列表*/
		$("#dept").change(function(){
			cheangeUsers($(this).val());
		});
		setUsers();
		/**保存按钮被点击后触发，提交保存数据*/	
		$("#submit").click(function(){
			submitData();
		});
		//查询按钮被点击
		$("#check").click(function(){
			 query();
		});
	});
		/**名字模糊查询*/
		function query(){
			var name=$("#selectuser").val();
			$("#pickData option").each(function(){
				var optName=$(this).text();
				if(optName.toLowerCase().indexOf(name.toLowerCase())>-1){
					$(this).attr("selected", true);
				}
			});
		}
		/**改变人员列表*/
		function cheangeUsers(deptid){
			$("#pickData option").remove();
			$.ajax({
				url:"user/getusersbydept.do",
				type:"POST",
				data:{"deptId":deptid},
				async: true,
				error:function(xMLHttpRequest, textStatus, errorThrown){
					alert("获取人员列表失败");
				},
				success: function(data) {
					htm="";
					var daptName="["+$("#dept option:selected").text()+"]";
					for(var i=0;i<data.length;i++){
						htm+="<option value='"+data[i].id+"'>"+daptName+data[i].userName+"</option>";
					}
					
					$("#pickData").append(htm);
					moveRepeatUsers();
				}
			});
		}
		/**去除已选重复的人员*/
		function moveRepeatUsers(){
			
			$("#pickListResult option").each(function(){
				var uid=$(this).val();
				var op="#pickData option[value='"+uid+"']";
				$(op).remove();
			});
		}
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
		/**获取选择的人员*/
		function setUsers(){
			var userids=$("#userids").val();
			$.ajax({//根据用户ID查询出用户视图
				url: 'safe/usersid.do', //提交地址
				type: 'POST',//方式
				traditional: true, 
				data: {"userIds":userids} ,//+usersId2,//提交的数据
				async: true,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					
					alert("保存失败");
				},
				success: function(data) {
					var htm="";
					for(var i=0;i<data.length;i++){
						htm+="<option value='"+data[i].id+"'>["+data[i].orgName+"]"+data[i].userName+"</option>";
					}
					$("#pickListResult").append(htm);
				}
			});
		}
		/**提交数据*/
		function submitData(){
			//setUsers();
			var dat="id="+$("#id").val();
			var userIds="&userId=";
			var users="&users=";
			$("#pickListResult option").each(function(i){
				userIds+=+$(this).val()+"|";
				users+=$(this).text()+";"
			});
			dat+=userIds+users;
			$.ajax({
				url: 'safe/savewebrole.do', //提交地址
				type: 'POST',//方式
				data: dat,//提交的数据
				async: true,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					
					alert("保存失败");
				},
				success: function(data) {
					
					alert(data);
				}
			}); 
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
			<input type="hidden" id="userids" value="${model.userId }">
			<input type="hidden" value="${model.id }" id="id">
			<label for="roleType">用户角色</label>
			<select id="roleType" disabled="disabled">
				<option value="${model.roleType }">${model.role } </option>
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
			<%-- <input type="hidden" value="${model.typeName }" id="users"> --%>
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
