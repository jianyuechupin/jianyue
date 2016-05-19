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
		var daptDate=null;
		/**取得部门数据*/
		$.post("safe/getOrgs.do",function(depts){
			daptDate=depts;
			setDeptsForSelect(daptDate);
		});
		/**保存按钮被点击后触发，提交保存数据*/	
		$("#submit").click(function(){
			submitData();
		});
		/**选择部门触发   ---改变用户选择框的值 <下拉框的> */
		$("#dept").change(function(){
			deptChange($(this).val());
		});
		/**选择用户后触发*/
		$("#userInfo").change(function(){
			alert("人员改变了");
			userChange($(this).val(),daptDate);
		});
		
	});
		/**为部门的select添加option项*/
		function setDeptsForSelect(daptDate){
			var opts="";
			for(var i=0;i<daptDate.length;i++){
				opts+="<option value=\""+daptDate[i].id+"\">"+daptDate[i].orgName+" </option>";
			}
			$("#dept").append(opts);
			$("#pickList").pickList({
				data: daptDate
			});
		}
		
		/**选择用户时改变已选择的部门的内容*/
		function userChange(userid,daptDate){
			if(userid==0){//如果没选用户，把所有的已移动到被选择的选择移回去；
				$("#pickList").pickList({
					data: daptDate
				});
				$("#pickListResult option").remove();
				
			}
			$.post("获取某用户下的所有数据的Do",{userId:userid},function(data){
				for(var i=0;i<data.length;i++){
					var p = $("#pickData option[value='"+data[i].orgId+"']");
					p.clone().appendTo($("#pickListResult"));
					p.remove();	
				}
			});
		}
		
		/**选择部门后触发此方法，改变人员*/
		function deptChange(deptid){
			$.post("getusersbyid",{deptId:deptid},function(data){
				var useroption="<option value=\"0\">选择人员</option>";
				for(var i=0;i<data.lengrh;i++){
					useroption+="<option value=\""+data[i].id+"\">"+data[i].userName+"</option>";
					useroption+="<option value=\""+(data[i].id+1)+"\">"+data[i].userName+"</option>";
				}
				$("#userInfo option").remove();
				$("#userInfo").append(useroption);
			});
		}
		/**保存数据*/
		function submitData(){
			//var userid=$("#userInfo").val();
			var userid=1;
			//var username=$("#userInfo option:selected").text();
			var role=$("#roleType").val();
			var creditmanager="";
			var datas="userInfoId="+userid+"&userName="+$("#userInfo option:selected").text()+
				"&roleType="+role+"&orgsName=";
			$("#pickListResult option").each(function(i){
				datas+=$(this).text()+";";
				creditmanager+="&creditManagerTbls["+i+"].orgId="+$(this).val()+"&creditManagerTbls["+i+"].userInfoId="+
				userid+"&creditManagerTbls["+i+"].roleType="+role;
			});
			datas+=creditmanager;
			//alert(datas);
			$.ajax({
				url: 'safe/savenewcreditdetail.do', //提交地址
				type: 'POST',//方式
				data: datas,//提交的数据
				async: true,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					
					alert("服务端验证失败");
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
		<div class="jumbotron" style="margin-left: 100px;">
			<label for="dept">部门</label>
			<select id="dept">
				<option value="0">请选择部门 </option>
			</select>
			
			<label for="userInfo">人员</label>
			<select id="userInfo">
				<option value="0">选择人员</option>
			</select>
						
			<label for="roleType">管理角色</label>
			<select id="roleType">
				<option value="0">兼职保密员</option>
				<option value="1">保密处人员</option>
			</select>
			<!-- 部门左右移动块 -->
			
				
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
