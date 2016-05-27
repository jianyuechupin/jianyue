$(function() {
	/**绑定双击事件*/
	$('#table').bootstrapTable({
		onDblClickRow: function(row, $element) {
			window.location = 'safe/creatUserGroup.do?id=' + row.id;
		}
	});
	/**取得用户组列表*/
	$.post("safe/userGroupAjax.do", function(data) {
		$("#table").bootstrapTable('load', data);
	});
	$("#add").click(function() {
		window.location = "safe/creatUserGroup.do?id=";
	});

});

/**拼接表格*/
function actionFormatter(value, row, index) {
	return [

		'<a class="edit ml10" href="javascript:void(0)" title="Edit">',
		'<i class="glyphicon glyphicon-edit"></i>',
		'</a>',
		'<a class="remove ml10" href="javascript:void(0)" title="Remove">',
		'<i class="glyphicon glyphicon-remove"></i>',
		'</a>'
	].join('');
}

/**绑定事件*/
window.actionEvents = {
	'click .edit': function(e, value, row, index) {
		window.location = 'safe/creatUserGroup.do?id=' + row.id;

	},
	'click .remove': function(e, value, row, index) {
		//window.location = 'safe/deleteusergroup.do?id=' + row.id;
		$.ajax({
			url:'safe/deleteusergroup.do',
			type:'POST',
			data:"id="+row.id,
			async: false,
			error: function(xMLHttpRequest, textStatus, errorThrown) {
				alert("服务器发生错误，请检查要删除的数据是否正确后重试");
			},
			success: function(data) {
				if("删除成功"==data){
					/**刷新保密管理员列表*/
					$("#table").bootstrapTable('removeAll');
					alert("删除成功");
					$.post("safe/userGroupAjax.do", function(data) {
						$("#table").bootstrapTable('load', data);
					});
					
				}
			}
		}); 
		
		
		/* var datas="id="+row.id+"&userInfoId="+row.userInfoId+"&roleType="+row.roleType;
				$.ajax({
					url:'safe/creditmanagerdelete.do',
					type:'POST',
					data:datas,
					async: true,
					error: function(xMLHttpRequest, textStatus, errorThrown) {
						alert("服务器发生错误，请检查要删除的数据是否正确后重试");
					},
					success: function(data) {
						alert(data);
						if("删除成功"==data){
							/**刷新保密管理员列表*/
		/*	$.post("safe/getmanagers.do",function(data){
				 		 		$("#table").bootstrapTable('removeAll');
								$("#table").bootstrapTable('load', data);
							}); 
						}
					}
				}); */
	}
};