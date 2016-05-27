$(function() {
	/**取得部门数据*/
	$.post("safe/getOrgs.do", function(deptData) {
		
		setDepts(deptData);
		
	});

	/**保存按钮被点击后触发，提交保存数据*/
	$("#submit").click(function() {
		submitData();
	});

});
/**为部门选择框设置部门数据*/
function setDepts(daptDate) {
	$("#pickList").pickList({
		data: daptDate
	});
	if ($("#roleType").val() == 2) { //如果是保密处人员，左右选择框清空
		$("#pickData option").remove();
		$("#pickListResult option").remove();
		return;
	} else {
		var userid = $("#userInfo").val();
		$.post("safe/getorgsbyuserid.do", {
			userId: userid
		}, function(data) {
			if (data == null) {
				alert("数据为空");
				return;
			}
			for (var i = 0; i < data.length; i++) {
				var p = $("#pickData option[value='" + data[i].orgId + "']");
				p.clone().appendTo($("#pickListResult"));
				p.remove();
			}
		});
	}
}

/**提交数据*/
function submitData() {
	var userid = $("#userInfo").val();
	var role = $("#roleType").val();
	var creditmanager = "";
	var datas = "userInfoId=" + userid + "&userName=" + $("#userInfo option:selected").text() +
		"&roleType=" + role + "&orgsName=";
	$("#pickListResult option").each(function(i) {
		datas += $(this).text() + ";";
		creditmanager += "&creditManagerTbls[" + i + "].orgId=" + $(this).val() + "&creditManagerTbls[" + i + "].userInfoId=" +
			userid + "&creditManagerTbls[" + i + "].roleType=" + role + "&creditManagerTbls[" + i + "].isValid=" + 1;
	});
	datas += creditmanager;
	//alert(datas);
	$.ajax({
		url: 'safe/savenewcreditdetail.do', //提交地址
		type: 'POST', //方式
		data: datas, //提交的数据
		async: true, //是否同步提交
		error: function(xMLHttpRequest, textStatus, errorThrown) {

			alert("保存失败");
		},
		success: function(data) {

			alert(data);
		}
	});
}