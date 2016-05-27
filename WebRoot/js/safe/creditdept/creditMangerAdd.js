$(function() {
	var daptDate = null;
	/**取得部门数据*/
	$.post("safe/getOrgs.do", function(depts) {
		daptDate = depts;
		setDeptsForSelect(daptDate);
		/**装载左右select数据*/
		typeChange(daptDate);
	});

	/**保存按钮被点击后触发，提交保存数据*/
	$("#submit").click(function() {
		submitData();
	});
	/**选择部门触发   ---改变用户选择框的值 <下拉框的> */
	$("#dept").change(function() {
		deptChange($(this).val());
	});
	/**选择用户后触发*/
	$("#userInfo").change(function() {

		userChange(daptDate);
	});
	/**-保密员类型改变时触发*/
	$("#roleType").change(function() {
		typeChange(daptDate);
	});

});
/**为部门的select添加option项*/
function setDeptsForSelect(daptDate) {
	var opts = "";
	for (var i = 0; i < daptDate.length; i++) {
		opts += "<option value=\"" + daptDate[i].id + "\">" + daptDate[i].orgName + " </option>";
	}
	$("#dept").append(opts);
	$("#pickList").pickList({
		data: daptDate
	});
}

/**保密员类型被改变的时候*/
function typeChange(daptDate) {
	if ($("#roleType").val() == 2) { //如果是保密处人员，左右选择框清空
		$("#pickData option").remove();
		$("#pickListResult option").remove();
		return;
	} else {
		$("#pickList div[class='row']").remove();
		$("#pickList").pickList({
			data: daptDate
		});
		$("#pickListResult option").remove();
		var userid = $("#userInfo").val();
		if (userid != 0) {

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

}

/**选择用户时改变已选择的部门的内容*/
function userChange(daptDate) {
	typeChange(daptDate);
}

/**选择部门后触发此方法，改变人员*/
function deptChange(deptid) {
	$.post("user/getusersbydept.do", {
		deptId: deptid
	}, function(data) {
		var useroption = "<option value=\"0\">选择人员</option>";
		for (var i = 0; i < data.length; i++) {
			useroption += "<option value=\"" + data[i].id + "\">" + data[i].userName + "</option>";
		}
		$("#userInfo option").remove();
		$("#userInfo").append(useroption);
	});
}
/**保存数据*/
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