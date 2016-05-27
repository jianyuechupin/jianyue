		var val="";
		$(function() {
			getDepts();
			/*//创建页面内容*/
			getManus();
			/**增删改查被选择后*/
			$("input:checkbox[name='tableOperID']").change(function(){
				crudChange(this);
			});
			/**二级菜单被改变*/
			$("input:checkbox[name='tabeInfo']").change(function(){
				treeChecked(this);
			});
		});
		/**增删改查选择框选择状态改变后*/
		function crudChange(obj){
			var total=$(obj).parent().parent().find("li").length;
			var checkNum=$(obj).parent().parent().find("input:checkbox:checked").length;
			var parcheckbox=$(obj).parent().parent().parent().find("input:checkbox[name='tabeInfo']");
			if(total==checkNum){
				$(parcheckbox).prop("checked",true);
			}else{
				$(parcheckbox).prop("checked",false);
			}
		}
		/**二级菜单勾选后*/
		function treeChecked(obj){
			if($(obj).is(':checked')){
				$(obj).parent().find("input:checkbox[name='tableOperID']").each(function(){
					$(this).prop("checked",true);
				});
			}else{
				$(obj).parent().find("input:checkbox[name='tableOperID']").each(function(){
					$(this).prop("checked",false);
				});
			}
		 }
		
		/**取得部门列表*/
		function getDepts(){
			/**获取部门列表*/
			$.post("safe/getOrgs.do",function(data){
				val=data; 
			}); 
		}
		
		/**取得一级菜单及其子菜单功能*/
		function getManus(){
			/*//遍历li标签下的a被激活*/
			$("body").find("#myTab li a[id='manuTree']").each(function(i,ele){
			/*//异步请求二级菜单*/
				ajaxs($(ele));
			});
			/*//为页面所有的左右选择框添加数据*/
			addDept(val);
			/*//选中改组已被选择的数据*/
			setCheckedValue();
			/*//展示第一个Tab页*/
			$('#myTab li:eq(0) a').tab('show');	
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
		/**取得选择的内容*/
		function getCheckedValue(){
			var powerGroupId=$("#powergroupid").val();
			var datas=null;
			var ur="safe/getChecked.do";
			var para="powerGroupId="+powerGroupId;
			
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
		/*ajax 异步获取子菜单列表*/
		function ajaxs(obj){
			
			var id=obj.attr("href").toString().replace("#","");
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
		/**页面细则追加*/
		function appendHtml(data,id,pid){
			var htm="<div class=\"tab-pane fade\" id=\""+id+"\"><ul>";
			for(var i=0;i<data.length;i++){
				var hasOrg=data[i].hasOrg;/* 0不需要做部门限制，1需要部门限制 */
				htm+="<li class=\"sidebar_nav col-sm-12\">";
				var $list=data[i].tableOperViews;
				if(data[i].hasOrg==0){
					htm+="<input type='checkbox' class='chk_1' name='tabeInfo' id='tree"+data[i].id+"'><label style='width: 26%;' for='tree"+data[i].id+"'>"+
					data[i].tableName+"</label><ul>";						
					for(var j=0;j<$list.length;j++){
						htm+="<li class='col-sm-3'><input type=\"checkbox\" name='tableOperID' id=table"+$list[j].id+
						" value="+$list[j].id+"><label for='table"+$list[j].id+"'>"+$list[j].operTypeName+
						"</label></li>";				
					}
				}else{
					htm+="<label style='width: 26%;' id='tree"+data[i].id+"'>"+data[i].tableName+"</label><ul>";
					for(var j=0;j<$list.length;j++){
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
		};
		/**为部门列表添加数据函数  */
		function addDept(val){
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
		 	var j=$(obj).parent().find("input:checkbox[name=tableOperID]:checked").length;
		 	//遍历所有复选框
			$(obj).parent().find("input:checkbox[name=tableOperID]:checked").each(function(i){  
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
	       	$.post("safe/addpower.do",ids,function(data){
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
			$.post("safe/checkPowerGroupName.do",{"powerGroup":name,"id":$("#powergroupid").val()},function(data){
				return data;
			});
		}
		
