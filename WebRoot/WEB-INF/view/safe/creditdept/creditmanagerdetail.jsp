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
    <title>新增保密管理员</title>
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
			/**装载左右select数据*/
			typeChange(daptDate);
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
			
			userChange(daptDate);
		});
		/**-保密员类型改变时触发*/
		$("#roleType").change(function(){
			typeChange(daptDate);
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
		
		/**保密员类型被改变的时候*/
		function typeChange(daptDate){
			if($("#roleType").val()==2){//如果是保密处人员，左右选择框清空
				$("#pickData option").remove();
				$("#pickListResult option").remove();
				return;
			}else{
				$("#pickList div[class='row']").remove();
				$("#pickList").pickList({
					data: daptDate
				});
				$("#pickListResult option").remove();
				var userid=$("#userInfo").val();
				if(userid!=0){
					
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
			
		}
		
		/**选择用户时改变已选择的部门的内容*/
		function userChange(daptDate){
			typeChange(daptDate);
		}
		
		/**选择部门后触发此方法，改变人员*/
		function deptChange(deptid){
			$.post("user/getusersbydept.do",{deptId:deptid},function(data){
				var useroption="<option value=\"0\">选择人员</option>";
				for(var i=0;i<data.length;i++){
					useroption+="<option value=\""+data[i].id+"\">"+data[i].userName+"</option>";
				}
				$("#userInfo option").remove();
				$("#userInfo").append(useroption);
			});
		}
		/**保存数据*/
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
		<div class="jumbotron" style="margin-left: 100px;">
			<label for="dept">部门</label>
			<select id="dept" >
				<option value="0">请选择部门 </option>
			</select>
			
			<label for="userInfo">人员</label>
			<select id="userInfo">
				<option value="0">选择人员</option>
			</select>
						
			<label for="roleType">管理角色</label>
			<select id="roleType">
				<option value="1">兼职保密员</option>
				<option value="2">保密处人员</option>
			</select>
			<!-- 部门左右移动块 -->
			
				
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
