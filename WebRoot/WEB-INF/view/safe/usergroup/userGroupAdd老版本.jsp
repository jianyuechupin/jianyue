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
    <title>用户组管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/pickList.css" />
	<script type="text/javascript">
		 /* JS函数位置 */
		 <%-- alert("<%=basePath%>"); --%>
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
			<div>
				<h4>${usergroup.id }</h4>
				<input type="hidden" value="${usergroup.id }" id="powergroupid">			
			
			 	<h5 class="col-sm-2">用户组名称：</h5>
				<input class="form-control required col-sm-4" style="width: 30%;" value="${usergroup.userGroupName }" placeholder="用户组名称" type="text"/>
				<h5 class="col-sm-2">用户组描述：</h5> 
				<input class="form-control required" style="width: 30%;"  value="${usergroup.description }" placeholder="用户组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${list }" var="bean"  varStatus="status">
					<li ><a href="#tal${bean.id }" id="manuTree" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
				<li ><a href="#users"  data-toggle="tab">
					用户列表<input name="operId" type="hidden" value="0"></a>
				</li>
			</ul>
			<div id="myTabContent" class="tab-content">

			</div>
		</div>			

		</div>
		
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			//遍历li标签下的a被激活
			$("body").find("#myTab li a[id='manuTree']").each(function(i,ele){
				ajaxs($(ele));
			});
		
			$('#myTab li:eq(0) a').tab('show');		

		});
		var val="";

		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			//var n=obj.find("input[name='operId']").val();
			
			var id=obj.attr("href").toString().replace("#","");
			/**获取部门列表*/
			$.post("getOrgs.do",function(data){
				val=data; 
			}); 
			
			/*取得子菜单以及操作功能*/
			var pid=id.replace("tal","");
			$.post("sonMenu.do",{"pid":pid} ,function(data){
				var htm="<div class=\"tab-pane fade\" id=\""+id+"\">";
				
				for(var i=0;i<data.length;i++){
					var hasOrg=data[i].hasOrg;/* 0不需要做部门限制，1需要部门限制 */
					htm+=data[i].tableName+"<br/>";
					var $list=data[i].tableOperViews;
					if(data[i].hasOrg==0){
						for(var j=0;j<$list.length;j++){
							htm+="<input type=\"checkbox\" name='id' value="+$list[j].id+">"+$list[j].operTypeName;
						}
					}else{
						for(var j=0;j<$list.length;j++){
						//htm+="</br>-----------需要做部门级别的选择------------"
							var c="<div class='panel-heading'><h5 class='panel-title'>"+
							$list[j].operTypeName+"</h5></div>"
							+"<div id=\"pickList\"><input type=\"hidden\" value="+$list[j].id+"></div>";
							//alert(c);
							htm+=c;
						}
						//htm+="</div>";
					}
					htm+="<br/>";
				}
				htm+="<button onclick='postt(this,"+pid+")' id=\"submit\" class=\"btn btn-primary\" type=\"button\">保  存</button></div>";
				//alert(htm);
				$("#myTabContent").append(htm);
				zj(val);
				//obj.find("input[name='operId']").val(++n);
			});
			
		}	
		/**为部门列表添加数据函数  */
		function zj(val){
			$("body").find("div [id='pickList']").each(function(i,ele){
			   	var pick = $(ele).pickList({
					data: val
				});
			 });

		}
		/**ajax提交保存的数据*/
		 function postt(obj,pid){
		 	/**************************采集页面选择数据阶段**********************************************/
		 	var ids= "pid="+pid;
		 	var idz=new Array();
		 	var j=$(obj).prevAll("input:checkbox[name=id]:checked").length;
		
			$(obj).prevAll("input:checkbox[name=id]:checked").each(function(i){  
	           ids+="&details["+i+"].tableOperID="+$(this).val()+"&details["+i+"].userGroupId="+3;
	        });
	        /**上面是获取已选择的多选框的数据，下面获取已选择的部门列表数据  */
	       	$(obj).parent().find("div [class=row]").each(function(i,ele){
	       	 	var tableOperId=$(ele).parent().find("input:hidden").val();//功能菜单ID
	       	 	var depts=new Array();
	       	 	var deptList="";
			   	$(ele).children("div [name='sele']").find("#pickListResult option").each(function () {
			   		depts.push(this.id);
			   		deptList+=this.id+"|";
			    });
			    if(depts.length>0){
				    ids+="&details["+j+"].tableOperID="+tableOperId+"&details["+j+"].userGroupId="+3+
				    	"&details["+j+"].deptList="+deptList;
				    j++;	
			    
			    }
			 }); 
			 /*********************************提交数据阶段****************************************/
	        $.post("addUserGroupDetails.do",ids,function(data){
	        	//alert(data);
	        });
	        
		} 
		
		/*  */
	</script>
  </body>
</html>
