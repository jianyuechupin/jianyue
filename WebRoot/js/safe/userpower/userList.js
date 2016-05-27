
		 $(function(){
		 	/**绑定双击事件*/
		 	$('#table').bootstrapTable({
				onDblClickRow:function(row, $element){
					window.location='safe/userPowerDetails.do?uid='+row.uid;
				}
	        });
		 	/**修改按钮被点击*/
		 	$("#add").click(function(){
		 		update();
		 	});
		 	/**修改登录方式被点击*/
 			$("#setlogintype").click(function(){
 				showModal();
 			});
		 	/**控制密码框的显隐*/
 			$('input:radio[name="loginType"]').change(function(){
 				changeLoginType();
 			});
 			/**修改登录保存按钮被点击*/
 			$("#changeType").click(function(){
 				submitType();
 			});
 			
		 }); 
		 
		 
		 //**************************方法区***********************************
		 function update(){
			 var checkedRow=$("#table").bootstrapTable('getSelections');
				if(checkedRow.length==0){
					alert("请选择被修改的人员");
					return;
				}else if(checkedRow.length > 1){
					alert("请正确选择被修改的人员，每次只能选择一个");
					return;
				}
			window.location='safe/userPowerDetails.do?uid='+checkedRow[0].uid;
		 }
		 
		 /**展示修改登录方式遮罩层*/
		 function showModal(){
			var checkedRow=$("#table").bootstrapTable('getSelections');
			if(checkedRow.length==0){
				alert("请选择被修改的人员");
				return;
			}else if(checkedRow.length > 1){
				alert("请正确选择被修改的人员，每次只能选择一个");
				return;
			}
			$("#sh").modal('show');
			var loginType=checkedRow[0].loginType;
			var id="input[name='loginType'][value="+loginType+"]";
			$("input[name='loginType']").removeAttr("checked");
			$(id).prop("checked",true);
			changeLoginType();
			$("#updateUserId").val(checkedRow[0].uid);
		 }
		 /**未完成的方法  修改后的登录方式保存*/
		 function submitType(){
			 var logintype=$("input[name='loginType']:checked").val();
			 var datas="loginType="+logintype+"&uid="+$("#updateUserId").val();
			 if(logintype==1){
				 datas+="&passWord="+$("#myPassWord").val();
				 if($("#myPassWord").val()==null||$("#myPassWord").val()==""){
					 alert("请输入登录密码后再进行保存");
					 return ;
				 }
			 }
			 alert(datas);
			 //$("#closeModal").click();
			 $.ajax({
				 url:"",
				 type:'POST',
				 data:"",
				 error:function(){
					 alert("服务器异常，数据保存失败");
				 },
				 success:function(data){
					 //完成后取消模态框
					 $("#closeModal").click();
				 }
			 });
		 }
		 /**弹出修改用户登录方式的遮罩*/
		 function butto(){
			    $("#sh").modal('show');
			    alert($("input[name='loginType']:checked").val());
			    if($("input[name='loginType']:checked").val()==0){
			    	$("#passdl").hide();
			    }else{
			    	$("#passdl").show();
			    }
		}
		/**登录方式redio的改变事件*/ 
		 function changeLoginType(){
			 if($("input[name='loginType']:checked").val()==0){
			    	$("#passdl").hide();
			    }else{
			    	$("#passdl").show();
			    }
		}
