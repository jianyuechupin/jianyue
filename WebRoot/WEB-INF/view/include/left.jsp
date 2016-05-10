<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div id="sidebar-nav" class="navbar-default">
	<ul id="dashboard-menu">
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div> <a href="javascript:;"> <i class="glyphicon glyphicon-home"></i>
				<span>首页</span>
		</a>
		</li>



		<li class="active">
			 <a id="asa1" href="javascript:;"
				 class="dropdown-toggle">
				 <i class="glyphicon glyphicon-apple"></i>
				  <span >安全管理</span> <i class="caret"></i>
			 </a>
			<ul class="submenu">
				<li>123</li>
			</ul> 
		</li>
	</ul>
</div>

<script type="text/javascript">
	function getTree(v) {
				
	}

	$(function() {
	//把所有的二级菜单隐藏
		$("ul .submenu").css("display", "none");
	});
	//a标签被点击
 	$("#sidebar-nav .dropdown-toggle").click(function() {
 		
 		var lis="";
 		$.post("ajax.do",{"id":this.id},function (data){
			lis=data;
			//map.put(this.id,data);
		});
		//lis=eval(lis);
		alert(lis);
		//二级菜单缓慢收起来
		$('.submenu').slideUp("slow");
			if ($(this).next().css("display") == "none") {
				$(this).next().append(lis);
				$(this).next().slideDown("slow");
			}
			//样式的阴影
			$(this).css("box-shadow", "");

	});

	$(".nav .dropdown-toggle").click(function() {
		$('.dropdown-menu').slideUp("slow");
		if ($(this).next().css("display") == "none") {

			$(this).next().slideDown("slow");
		} else {
			$(this).next().slideUp("slow");
		}
	});
	
	function go(obj){
	var $table=$("#table");
	//var da=null;
	
	 	$.post(obj.id,function(date){
			$table.bootstrapTable('load', date);
		}); 
		
	}
	
	//获取用户组函数
	function getGroup(){
		$("#head").empty();
		//var $table=$("#table");
		var $head="	<th data-field=\"id\" data-formatter=\"idFormatter\">序号</th>\n" +
"							<th data-field=\"userGroupName\">用户组名称</th>\n" +
"							<th data-field=\"NO\">用户数</th>\n" +
"							<th data-field=\"description\">描述</th>\n";
		$("#head").append($head);
		$.post("userGroupAjax.do",function(data){
		//加载用户组数据
			$("#table").bootstrapTable('load', data);
		});
	}
	
	
	$("#userGroupAjax").click(function(){
		//清空表头
		$("#head").empty();
		alert("ok");
	});
</script>
