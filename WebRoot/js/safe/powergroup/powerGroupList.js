$(function() {
	/**绑定双击事件*/
	$('#table').bootstrapTable({
		onDblClickRow: function(row, $element) {
			window.location = 'safe/addpowergroup.do?id=' + row.id;
		}
	});
	/**取得权限组列表*/
	$.post("safe/getPowerGroups.do", function(data) {
		$("#table").bootstrapTable('load', data);
	});
	/**添加按钮*/
	$("#add").click(function() {
		window.location = "safe/addpowergroup.do?id=";
	});
});

/**添加操作列数据*/
function actionFormatter(value, row, index) {
	return [
		/* glyphicon glyphicon-edit  */
		'<a class="edit ml10" href="javascript:void(0)" title="修改">',
		'<i class="glyphicon glyphicon-pencil"></i>',
		'</a>',
		'<a class="remove ml10" href="javascript:void(0)" title="删除">',
		'<i class="glyphicon glyphicon-remove"></i>',
		'</a>'
	].join('');
}
/**绑定事件*/
window.actionEvents = {
	'click .edit': function(e, value, row, index) {
		window.location = 'safe/addpowergroup.do?id=' + row.id;

	},
	'click .remove': function(e, value, row, index) {
		//alert('safe/deletepowergroup.do?id='+row.id);
		window.location = 'safe/deletepowergroup.do?id=' + row.id;
	}

};