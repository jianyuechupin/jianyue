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
				<input id="powergroupname" class="form-control required col-sm-4" style="width: 30%;"  value="${usergroup.userGroupName }" placeholder="用户组名称" type="text"/>
				<h5 class="col-sm-2">用户组描述：</h5>
				<input id="powergroupremark" class="form-control required" style="width: 30%;" value="${usergroup.description }" placeholder="用户组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${tableList }" var="bean"  varStatus="status">
					<li ><a href="#tal${bean.id }" id="manuTree" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
					<li ><a href="#tal-1" id="manuTree" data-toggle="tab">
					      用户列表<input name="operId" type="hidden" value="0"></a>
					</li>				
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade" id="tal-1" >
					<h5 class="col-sm-1">单位：</h5>
					<select name="select" id="selectGroup" style="width: 20%" class="form-control input-sm col-sm-3">
						<option value="0">个性化权限</option>
						<c:forEach items="${powerGroups }" var="bean">
						<option value="${bean.id }">${bean.powerGroup }</option>
						</c:forEach>
					</select>
					
					<h5 class="col-sm-2">用户筛选：</h5>
					<input id="powergroupname" class="form-control required col-sm-3" style="width: 30%;"  value="${usergroup.userGroupName }" placeholder="用户组名称" type="text"/>
					<input class="btn btn-success  type="button" value="查询">
					<div class="col-sm-3 dy_sele">

						<select class="form-control selectpicker" multiple="multiple">
                            <option> 请选择</option >
                            <option> 游记</option >
                            <option> 景点</option >
                        </select>
					</div>
				</div>
			</div>
		</div>			
		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			//创建页面内容
			getManus();
			//展示第一个Tab页
			$('#myTab li:eq(0) a').tab('show');	
	
		});
		
		/**取得一级菜单及其子菜单功能*/
		function getManus(){
			var val="";
			//遍历li标签下的a被激活
			$("body").find("#myTab li a[id='manuTree']").each(function(i,ele){
				//异步请求二级菜单
				ajaxs($(ele));
			});	
		}
		
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			//var n=obj.find("input[name='operId']").val();
			var id=obj.attr("href").toString().replace("#","");
			if(id=="tal-1"){
				return;
			}
 			/**获取部门列表*/
			$.post("safe/getOrgs.do",function(data){
				val=data; 
			}); 
			/**$("input:checkbox[value='1']").attr('checked','true');  */
			/*取得子菜单以及操作功能*/
			var pid=id.replace("tal","");
			$.post("safe/sonMenu.do",{"pid":pid} ,function(data){
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
						//htm+="</br>-----------需要做部门级别的选择------------"
							var c="<div class='panel-heading'><label class='fontstyle'>"+
							$list[j].operTypeName+"</label></div>"
							+"<div id=\"pickList\"><input type=\"hidden\" value="+$list[j].id+"></div>";
							//alert(c);
							htm+=c;
						}
						//htm+="</div>";
					}
					htm+="</ul>";
				}
				htm+="<button onclick='postt(this,"+pid+")' id=\"submit\" class=\"btn btn-primary\" type=\"button\">保  存</button></ul></div>";
				//alert(htm);
				$("#myTabContent").append(htm);
				
				addDept(val);
				doChecked();
			
			});
		}	
		/**为部门列表添加数据函数  */
		
		
		function addDept(val){

			$("body").find("div [id='pickList']").each(function(i,ele){
			   	var pick = $(ele).pickList({
					data: val
				});
			 });

		}
		//**************************保存按钮点击触发*********************************************/
		/**保存按钮点击后，数据异步提交函数*/
 		 function postt(obj,pid){ 
		 	var ids= "pid="+pid;
		 	var idz=new Array();
		 	var j=$(obj).prevAll("input:checkbox[name=tableOperID]:checked").length;
		
			$(obj).prevAll("input:checkbox[name=tableOperID]:checked").each(function(i){  
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
 	        $.post("safe/addUserGroupDetails.do",ids,function(data){
	        	//alert(data);
	        });
	        
		} 
		 

		/**************************根据权限组ID将对应已选择的按钮选中***********************/
		function doChecked(){
			/**权限组ID，0表示个性化权限*/
			var groupId=$("#selectGroup").val();
			//若非个性化权限组
			if(groupId!=0){
				$.post("safe/getChecked.do",{userGroupId:groupId},function (data){
					//获取已选数据，将对应的选择框选中
					checkedIt(data);
				});
			}else{
				//去用户权限表取数据
				$.post("safe/getUserPowerDetails.do",{},function (data){
					//获得了已选数据
				});
			}
		}
		function checkedIt(data){
			for(var i=0;i<data.length;i++){
				var tableOper=data[i].tableOperId;
				var deptSet=data[i].deptSet;
				//如果部门集合不为空或者部门集合长度不等于0
				if(deptSet!=null&&deptSet.length!=0){
					for(j=0;j<deptSet.length;j++){
						moveSelect(tableOper,deptSet[j]);
					}
				}else{
					//没有部门权限要求的，设置选择情况
					var checkid="#table"+tableOper;	
					$(checkid).attr('checked', 'true');
				}
			}
			
		}
		/**把已选的部门添加到选择*/
		function moveSelect(tableOper,value){
			var p=$("input:hidden[value='"+tableOper+"']").next("div:first").find("div:first").find("#pickData option[value='"+value+"']");
			var h=$("input:hidden[value='"+tableOper+"']").next("div:first").find("div:last").children("#pickListResult");
			p.clone().appendTo(h);
			p.remove();	
		}
	</script>
  </body>
</html>
