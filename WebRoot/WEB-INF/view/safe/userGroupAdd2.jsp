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
    <title>页面标题</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../include/base.jsp" %> 
	<script type="text/javascript">
		 /* JS函数位置 */
		 <%-- alert("<%=basePath%>"); --%>
	</script>

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
			<div>
			 	<h5 class="col-sm-2">用户组名称：</h5>
				<input class="form-control required col-sm-4" style="width: 30%;" id="placeholderinput" placeholder="权限组名称" type="text"/>
				<h5 class="col-sm-2">用户组名称：</h5>
				<input class="form-control required" style="width: 30%;"  placeholder="权限组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${list }" var="bean"  varStatus="status">
					<li ><a href="#${bean.id }" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">
				<table id="table" >
				
				<thead>
				<tr>
					<th data-field="id">查询</th>
					<th data-field="id">修改</th>
					<th data-field="id">增加</th>
					<th data-field="id">删除</th>
				</tr>
				</thead>
				</table>
			</div>
		</div>			

		</div>
		
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			//遍历li标签下的a被激活
		$('#myTab li:eq(0) a').tab('show');		
		/* $('#myTab li a').click(function(){ 
				ajaxs($(this));
			});
		}); */
		var flist=${list };
		alert("一级菜单:"+flist);
		
		function postt(obj){
			alert($(this ).val());
			/* var arrChk=$("input[name='chk_list'][checked]"); */
			var l=$(this).parent().find("input").length;
			alert(l);
		}
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			var i=obj.find("input[name='operId']").val();
			 alert(i);
			/*if(i!=0){
				return;
			}  */
			var id=obj.attr("href").toString().replace("#","");
			/*取得子菜单以及操作功能*/
			
			$.post("sonMenu.do",{"pid":id} ,function(data){
				$("#table").bootstrapTable('load', date);
				/* 
				var htm="<div class=\"tab-pane fade\" id=\""+id+"\">";
				
				for(var i=0;i<data.length;i++){
				
					htm+=data[i].tableName+"<br/>";
					var $list=data[i].tableOperViews;
					for(var j=0;j<$list.length;j++){
						htm+="<input type=\"checkbox\" name='id' value="+$list[j].id+">"+$list[j].operTypeName;
					}
					htm+="<br/>"; */
				/* }
				htm+="<button onclick='postt(this)' id=\"submit\" class=\"btn btn-primary\" type=\"button\">保  存</button></div>";
				$("#myTabContent").append(htm);
				obj.find("input[name='operId']").val(++i); */
				
			});
			
		}	

	</script>
  </body>
</html>
