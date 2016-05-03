<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>页面标题</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="/include/base.jsp" %> 
	<script type="text/javascript">
		 /* JS函数位置 */
		 <%-- alert("<%=basePath%>"); --%>
	</script>

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="/include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="/include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
			
			<div class="container">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${list }" var="bean"  varStatus="status">
					<c:choose>
						<c:when test="${status.index ==0}">
							<li class="active"><a id="afrist" href="#${bean.id }" data-toggle="tab">
					      		${bean.tableNameChs }<input type="hidden" value=1></a>
							</li>						
						</c:when>
						<c:otherwise>
							<li ><a href="#${bean.id }" data-toggle="tab">
					      		${bean.tableNameChs }<input type="hidden" value=0></a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">

				<c:forEach items="${list }" var="bean"  varStatus="status">
					<c:choose>
						<c:when test="${status.index ==0}">
							<div class="tab-pane fade in active" id="${bean.id }">
								<p>${bean.tableNameChs }</p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="tab-pane fade" id="${bean.id }">
								<p>${bean.tableNameChs }</p>
							</div>							
						</c:otherwise>
					</c:choose>
				</c:forEach>	
			</div>
		</div>			

		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			//遍历li标签下的a被激活
			$('#myTab li:eq(0) a').tab('show');	
			$('#myTab li a').click(function(){ 
/* 				var i=$(this).find("input").val();
				$(this).find("input").val(++i);*/
				ajaxs($(this));
			});
		});
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			var i=obj.find("input").val();
			alert("i=="+i);
			if(i!=0){
				return;
			}
			var id=$("#afrist").attr("href").toString().replace("#","");
			
			$.post("sonMenu.do",{"pid":id} ,function(data){
				alert(data);
				obj.find("input").val(++i);
			});
			
		}		
	</script>
	<script type="text/javascript">

	</script>
  </body>
</html>
