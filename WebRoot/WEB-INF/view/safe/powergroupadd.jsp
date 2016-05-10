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
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/pickList.css" />


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
				<h4>${id }</h4>
				<input type="hidden" value="${id }" id="powergroupid">
			 	<h5 class="col-sm-2">权限组名称：</h5>
				<input id="powergroupname" class="form-control required col-sm-4" style="width: 30%;" id="placeholderinput" placeholder="权限组名称" type="text"/>
				<h5 class="col-sm-2">权限组描述：</h5>
				<input id="powergroupremark" class="form-control required" style="width: 30%;"  placeholder="权限组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${list }" var="bean"  varStatus="status">
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
			//创建页面内容
			getManus();
			//展示第一个Tab页
			$('#myTab li:eq(0) a').tab('show');	
	
		});
		var val="";
		/**取得一级菜单及其子菜单功能*/
		function getManus(){
			var flg=false; 
			//遍历li标签下的a被激活
			$("body").find("#myTab li a[id='manuTree']").each(function(i,ele){
				var num=$("body").find("#myTab li a[id='manuTree']").length;
			//异步请求二级菜单
				ajaxs($(ele));
				flg=i==($("body").find("#myTab li a[id='manuTree']").length);
				
			});
		
			/**将本组下应选中的选择框选中*/	
			//doChecked();
		}
		
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			//var n=obj.find("input[name='operId']").val();
			
			var id=obj.attr("href").toString().replace("#","");
			/**获取部门列表*/
			$.post("getOrgs.do",function(data){
				val=data; 
			}); 
			/**$("input:checkbox[value='1']").attr('checked','true');  */
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
							htm+="<input type=\"checkbox\" name='id' id=table"+$list[j].id+" value="+$list[j].id+">"+$list[j].operTypeName;
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
				doChecked();
			
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
		/**保存按钮点击后，数据异步提交函数*/
		 function postt(obj,pid){
			//提交之前检查权限组名称是否正确
		 	/**权限组ID*/
		 	var groupid=$("#powergroupid").val();
		 	var groupname=$("#powergroupname").val();
		 	
		 	var ids="pid="+pid+"&powerGroup.id="+groupid+"&powerGroup.powerGroup="+$("#powergroupname").val()+"&powerGroup.remark="+$("#powergroupremark").val();
		 	//取得已选复选框的个数
		 	var j=$(obj).prevAll("input:checkbox[name=id]:checked").length;
		 	//遍历所有复选框
			$(obj).prevAll("input:checkbox[name=id]:checked").each(function(i){  
	           ids+="&details["+i+"].tableOperId="+$(this).val()+"&details["+i+"].powerGroupId="+groupid;
	        	
	        });
	        /**上面是获取已选择的多选框的数据，下面获取已选择的部门列表数据  */
	       	$(obj).parent().find("div [class=row]").each(function(i,ele){
	       	 	var tableOperId=$(ele).parent().find("input:hidden").val();//功能菜单ID
	       	 	var deptList="";
	       	 	var depts=new Array();
			   	$(ele).children("div [name='sele']").find("#pickListResult option").each(function () {
			   		deptList+=this.value+"|";
			   		depts.push(this.id);
			    });
			    
			    if(depts.length>0){
				    ids+="&details["+j+"].tableOperId="+tableOperId+"&details["+j+"].powerGroupId="+groupid+
				    	"&details["+j+"].deptList="+deptList;
				    j++;	
			    
			    }
			 }); 
			 /*****************************提交数据阶段***********************************************************/
	       	$.post("addpower.do",ids,function(data){
	       		alert(data);
	        });
		} 
		
		/****************************权限组名称改变时判断新名称是否可用******************************************/
		$("#powergroupname").change(function(){
			checkName();
		});
		function checkName(){
			var name=$("#powergroupname").val();
			if(name.length==""){
				//alert("权限组名称不能为空");
				return false;
			}
			//alert("名字不为空");
			$.post("checkPowerGroupName.do",{"powerGroup":name,"id":$("#powergroupid").val()},function(data){
				//返回检查结果，true可用，false不可用；
				return data;
			});
		}
		/**************************根据权限组ID将对应已选择的按钮选中***********************/
		function doChecked(){
			var groupId=$("#powergroupid").val(); 
			$.post("getChecked.do",{powerGroupId:groupId},function (data){
				//获取已选数据，将对应的选择框选中
				checkedIt(data);
			});
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
		function moveSelect(tableOper,value){
			var p=$("input:hidden[value='"+tableOper+"']").next("div:first").find("div:first").find("#pickData option[value='"+value+"']");
			var h=$("input:hidden[value='"+tableOper+"']").next("div:first").find("div:last").children("#pickListResult");
			p.clone().appendTo(h);
			p.remove();	
		}
	</script>
  </body>
</html>
