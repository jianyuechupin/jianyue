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
 	<link rel="stylesheet" type="text/css" media="all" href="css/pickList.css" />
	<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

	<script type="text/javascript">
	$(function(){
		/**取得部门数据*/
		$.post("safe/getOrgs.do",function(deptData){
			
			setDepts(deptData);
			
		});

		/**保存按钮被点击后触发，提交保存数据*/	
		$("#submit").click(function(){
			submitData();
		});
	
		
	});
		/**为部门选择框设置部门数据*/
		function setDepts(daptDate){
			$("#pickList").pickList({
				data: daptDate
			});
			if($("#roleType").val()==2){//如果是保密处人员，左右选择框清空
				$("#pickData option").remove();
				$("#pickListResult option").remove();
				return;
			}else{
				var userid=$("#userInfo").val();
				$.post("safe/getorgsbyuserid.do",{userId:userid},function(data){
					if(data==null){
						alert("数据为空");
						return;
					}
					for(var i=0;i<data.length;i++){
						var p = $("#pickData option[value='"+data[i].orgId+"']");
						p.clone().appendTo($("#pickListResult"));
						p.remove();	
					}
				});
			}
		}
	
	
		/**提交数据*/
		function submitData(){
			var userid=$("#userInfo").val();
			var role=$("#roleType").val();
			var creditmanager="";
			var datas="userInfoId="+userid+"&userName="+$("#userInfo option:selected").text()+
				"&roleType="+role+"&orgsName=";
			$("#pickListResult option").each(function(i){
				datas+=$(this).text()+";";
				creditmanager+="&creditManagerTbls["+i+"].orgId="+$(this).val()+"&creditManagerTbls["+i+"].userInfoId="+
				userid+"&creditManagerTbls["+i+"].roleType="+role+"&creditManagerTbls["+i+"].isValid="+1;
			});
			datas+=creditmanager;
			//alert(datas);
			$.ajax({
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
			<label for="dept">部门</label>
			<select id="dept" disabled="disabled">
				<option value="${modle.deptId }">${modle.orgName } </option>
			</select>
			
			<label for="userInfo">人员</label>
			<select id="userInfo" disabled="disabled">
				<option value="${modle.userInfoId }">${modle.userName }</option>
			</select>
						
			<label for="roleType" >管理角色</label>
			<select id="roleType" disabled="disabled">
				<option value="${modle.roleType }">${modle.typeName }</option>
			</select>
			<!-- 部门左右移动块 -->
			
				
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
