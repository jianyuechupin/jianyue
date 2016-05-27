$(function() {
	/**取得用户列表*/
	$.post("safe/userPowerManageViews.do", function(data) {
		$("#table").bootstrapTable('load', data);
	});
});

/**拼接修改列*/
function actionFormatter(value, row, index) {
	return [
		'<a class="edit ml10" href="javascript:void(0)" title="Edit">',
		'<i class="glyphicon glyphicon-edit"> 修改</i>', /* class="glyphicon glyphicon-edit" */
		'</a>'
	].join('');
}
/**绑定事件*/
window.actionEvents = {
	'click .edit': function(e, value, row, index) {
		window.location = 'safe/userPowerDetails.do?uid=' + row.uid;
	},
};