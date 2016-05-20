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
    <title>用户权限细则</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/pickList.css" />
	<link rel="stylesheet" type="text/css" href="css/powerDetailStyle.css" />
	<!-- <script type="text/javascript" src="js/icheck.min.js"></script>
	<link href="css/checkboxcss/green.css" rel="stylesheet" type="text/css"> -->
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
				<input type="hidden" id="uid" value=${userview.uid }>
			 	<h5 class="col-sm-2">用户名：${userview.userName }</h5>
				<h5 class="col-sm-2">所属单位：${userview.orgName }</h5>
				<h5 class="col-sm-2">登录账户：${userview.account }</h5>
				<h5 for="select2" class="col-sm-2">权限组：</h5>
				<select name="select" id="selectGroup" style="width: 30%" class="form-control input-sm col-sm-3">
					<option value="0">个性化权限</option>
					<c:forEach items="${powerGroups }" var="bean">
					<option value="${bean.id }">${bean.powerGroup }</option>
					</c:forEach>
				</select>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${tableList }" var="bean"  varStatus="status">
					<li ><a href="#tal${bean.id }" id="manuTree" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">
				
			</div>
		</div>			
		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			//alert($('#myTab li:eq(0) a').attr("href"));
			//创建页面内容
			getManus();
			//展示第一个Tab页
			$('#myTab li:eq(0) a').tab('show');	
			//为用户重新选择权限组
			$("#selectGroup").change(function(){
				//清空所有选项
				unCheckedAll();
				//按照权限组为页面选择框赋选中值
				setCheckedValue();
			});
		});
		/**清空所有选项*/
		function unCheckedAll(){
			//将所有的复选框状态变为未选择
				$("body").find("input:checkbox:checked").each(function(){
					$(this).attr("checked",false);
				});
				//移除所有已选择的部门
				$("body").find("div[class='row']").each(function(){
					$(this).remove();
				});
				//重新加载部门数据
				addDept(val);
		}
		/**取得选择的内容*/
		function getCheckedValue(){
			var powerGroupId=$("#selectGroup").val();
			var ur="safe/getUserPowerDetails.do";
			var para="id="+$("#uid").val();
			var datas=null;
			if(powerGroupId!=0){
				ur="safe/getChecked.do";
				para="powerGroupId="+powerGroupId;
			}
			
			$.ajax({
				url: ur, //提交地址
				type: 'POST',//方式
				data: para,//提交的数据
				async: false,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					alert("服务器异常");
				},
				success: function(data) {
					datas= data;
				}
			});
			return datas;
		}
		/**设置权限组已选择的数据 --*/
		function setCheckedValue(){
			var data =getCheckedValue();
			 for(var i=0;i<data.length;i++){
				var tableOper=data[i].tableOperId;
				var deptSet=data[i].deptSet; 
				//如果部门集合不为空或者部门集合长度不等于0
				 if(deptSet!=null&&deptSet.length!=0){
					for(var j=0;j<deptSet.length;j++){
						if(deptSet[j]!=null){
							var value=deptSet[j];
							var p=$("input:hidden[value='"+tableOper+"']").next("div [class='row']").find("div:first").find("#pickData option[value='"+value+"']");
							var h=$("input:hidden[value='"+tableOper+"']").next("div:first").find("div:last").children("#pickListResult");
							p.clone().appendTo(h);
							p.remove();	
						}
					} 
				}else{
					//没有部门权限要求的，设置选择情况
					var checkid="#table"+tableOper;	
					$(checkid).prop("checked", true);
				}
			}
		}
		
		
		/**取得一级菜单及其子菜单功能*/
		function getManus(){
			var val="";
			//遍历li标签下的a被激活
			$("body").find("#myTab li a[id='manuTree']").each(function(i,ele){
				//异步请求二级菜单
				ajaxs($(ele));
			});
			
			addDept(val);
			setCheckedValue();
		}
		
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			var id=obj.attr("href").toString().replace("#","");
 			
			/**$("input:checkbox[value='1']").attr('checked','true');  */
			/*取得子菜单以及操作功能*/
			var pid=id.replace("tal","");
			$.ajax({
				url: "safe/sonMenu.do", //提交地址
				type: 'POST',//方式
				data: "pid="+pid,//提交的数据
				async: false,//是否同步提交
				error: function(xMLHttpRequest, textStatus, errorThrown) {
					alert("服务器异常");
				},
				success: function(data) {
					appendHtml(data,id,pid);
				}
			});	
		}	
		
		/**根据json数据拼接页面*/
		function appendHtml(data,id,pid){
			var htm="<div class=\"tab-pane fade\" id=\""+id+"\"><ul>";
			for(var i=0;i<data.length;i++){
				htm+="<li class=\"sidebar_nav col-sm-12\"><input type='checkbox' class='chk_1' name='tabeInfo' id='123'><label style='width: 26%;' for='123'>"+
				data[i].tableName+"</label><ul>";
				
				var $list=data[i].tableOperViews;
				if(data[i].hasOrg==0){/* 0不需要做部门限制，1需要部门限制 */
					for(var j=0;j<$list.length;j++){
						htm+="<li class='col-sm-3'><input type=\"checkbox\" name='tableOperID' id=table"+$list[j].id+
						" value="+$list[j].id+"><label for='table"+$list[j].id+"'>"+$list[j].operTypeName+
						"</label></li>";
					}
				}else{
					for(var j=0;j<$list.length;j++){
					//-----------需要做部门级别的选择------------"
						var c="<div class='panel-heading'><label class='fontstyle'>"+
						$list[j].operTypeName+"</label></div>"
						+"<div id=\"pickList\"><input type=\"hidden\" value="+$list[j].id+"></div>";
						htm+=c;
					}
				}
				htm+="</ul>";
			}
			htm+="</ul><button onclick='postt(this,"+pid+")' id=\"submit\" class=\"btn btn-primary\" type=\"button\">保  存</button></div>";
			$("#myTabContent").append(htm);
			
		}
		/**为部门列表添加数据函数  */
		function addDept(val){
			if(val==null||val.length==0){
				$.ajax({
					url: "safe/getOrgs.do", //提交地址
					type: 'POST',//方式
					async: false,//是否同步提交
					error: function(xMLHttpRequest, textStatus, errorThrown) {
						alert("获取部门列表失败，页面加载失败");
					},
					success: function(data) {
						val=data; 
					}
				});
			}
			$("body").find("div [id='pickList']").each(function(i,ele){
			   	var pick = $(ele).pickList({
					data: val
				});
			 });

		}
		//**************************保存按钮点击触发*********************************************/
		/**保存按钮点击后，数据异步提交函数*/
		function postt(obj,pid){
			//提交之前检查权限组名称是否正确
		 	/**用户ID*/
		 	var uid=$("#uid").val();
			//获得一级菜单的ID
		 	var ids="pid="+pid+"&userId="+uid;
		 	//取得已选复选框的个数
		 	var j=$(obj).parent().find("input:checkbox[name=tableOperID]:checked").length;
			//遍历复选框选择的值
			$(obj).parent().find("input:checkbox[name=tableOperID]:checked").each(function(i){  
	           ids+="&userPowers["+i+"].tableOperId="+$(this).val()+"&userPowers["+i+"].userId="+uid;
	        });
		 	//遍历select选择的值
		 	 /**遍历select已经选择的*/
			 $(obj).parent().find("div [name='sele']").find("select").each(function(){
				 if($(this).find("option").length>0){
					 var deptList="";
					 var tableOperId=$(this).parent().parent().parent().find("input:hidden").val();
					 $(this).find("option").each(function(){
					 	deptList+=this.value+"|";
					 });
					 ids+="&userPowers["+j+"].tableOperId="+tableOperId+"&userPowers["+j+"].userId="+uid+
				    	"&userPowers["+j+"].deptList="+deptList.substring(0,deptList.length-1);
					 j++;
				 }
			 });
		 	//alert(ids);
		 	//提交数据
		    $.post("safe/saveUserPowers.do",ids,function(data){
		       		alert(data);
		        });
		} 
		

	</script>
  </body>
</html>
