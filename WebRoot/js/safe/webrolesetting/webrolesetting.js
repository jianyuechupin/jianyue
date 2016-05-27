$(function() {
	/**绑定双击事件*/
	$('#table').bootstrapTable({
		onDblClickRow: function(row, $element) {
			var ur = "safe/" + row.id + "/webroleupdatepage.do";
			window.location = ur;
		}
	});
	/**取得角色列表*/
	$.post("safe/webrolelist.do", function(data) {
		$("#table").bootstrapTable('load', data);
	});
	/**修改按钮被点击*/
	$("#update").click(function() {
		var selects = $("#table").bootstrapTable('getSelections');
		if (selects.length == 0) {
			alert("请选择要修改的数据");
			return;
		} else if (selects.length > 1) {
			alert("请正确选择要修改的数据，每次只能选择一个");
			return;
		}
		var id = $("#table").bootstrapTable('getSelections')[0].id;
		var ur = "safe/" + id + "/webroleupdatepage.do";
		window.location = ur; //'safe/creditmanagerupdate.do?id='+id;
	});
});

/**拼接修改列*/
function actionFormatter(value, row, index) {
	return [
		'<a class="edit ml10" href="javascript:void(0)" title="Edit">',
		'<i class="glyphicon glyphicon-edit"></i>',
		'修改</a>'
	].join('');
}
/**绑定事件*/
window.actionEvents = {
	'click .edit': function(e, value, row, index) {
		window.location = "safe/" + row.id + "/webroleupdatepage.do";
	},
};