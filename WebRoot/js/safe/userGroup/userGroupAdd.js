var val = "";
$(function() {
	getDepts();

	//创建页面内容
	getManus();

	/**选择部门触发   ---改变用户选择框的值 <下拉框的> */
	$("#orgIds").change(function() {
		deptChange($(this).val());
	});
	//查询按钮被点击
	$("#check").click(function() {
		query();
	});
	/**增删改查被选择后*/
	$("input:checkbox[name='tableOperID']").change(function() {
		crudChange(this);
	});
	/**二级菜单被改变*/
	$("input:checkbox[name='tabeInfo']").change(function() {
		treeChecked(this);
	});
});
/**名字模糊查询*/
function query() {
	var name = $("#selectuser").val();
	$("#usersList #pickData option").each(function() {
		var optName = $(this).text();
		if (optName.toLowerCase().indexOf(name.toLowerCase()) > -1) {
			$(this).attr("selected", true);
		}
	});
}
/**选择部门后触发此方法，改变人员*/
function deptChange(deptid) {
	$.post("user/getusersbydept.do", {
		deptId: deptid
	}, function(data) {
		var useroption = "";
		for (var i = 0; i < data.length; i++) {
			useroption += "<option value=\"" + data[i].id + "\">" + data[i].userName + "</option>";
		}
		$("#usersList #pickData option").remove();
		$("#usersList #pickData").append(useroption);
		moveRepeatUsers();
	});
}
/**去除已选重复的人员*/
function moveRepeatUsers() {

	$("#usersList #pickListResult option").each(function() {
		var uid = $(this).val();
		var op = "#pickData option[value='" + uid + "']";
		$(op).remove();
	});
}
/***/
function setUsers() {

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
/**初始化用户列表*/
function initUsers() {
	$("#usersList").pickList({
		data: val
	});
	$("#usersList #pickData option").remove();
	//添加在下面已选择的用户
}

function setOrgs() {
	var htm = "";
	for (var i = 0; i < val.length; i++) {
		htm += "<option value=\"" + val[i].id + "\">" + val[i].orgName + "</option>";
	}
	$("#orgIds").append(htm);
}
/**取得部门列表*/
function getDepts() {
	/**获取部门列表*/
	$.post("safe/getOrgs.do", function(data) {
		val = data;
	});
}

/**取得一级菜单及其子菜单功能*/
function getManus() {
	var val = "";
	//遍历li标签下的a被激活
	$("body").find("#myTab li a[id='manuTree']").each(function(i, ele) {
		//异步请求二级菜单
		ajaxs($(ele));
	});
	//为页面所有的左右选择框添加数据
	addDept(val);
	initUsers();
	//展示第一个Tab页
	$('#myTab li:eq(0) a').tab('show');
	doChecked();
	setOrgs();
}

/*ajax 异步获取子菜单列表*/
function ajaxs(obj) {
	var id = obj.attr("href").toString().replace("#", "");
	if (id == "tal-1") {
		return;
	}
	/*取得子菜单以及操作功能*/
	var pid = id.replace("tal", "");

	$.ajax({
		url: "safe/sonMenu.do", //提交地址
		type: 'POST', //方式
		data: "pid=" + pid, //提交的数据
		async: false, //是否同步提交
		error: function(xMLHttpRequest, textStatus, errorThrown) {
			alert("服务器异常");
		},
		success: function(data) {
			setHtml(data, id, pid);
		}
	});

}
/**设置页面，拼接选择页面*/
function setHtml(data, id, pid) {
	var htm = "<div class=\"tab-pane fade\" id=\"" + id + "\"><ul>";

	for (var i = 0; i < data.length; i++) {
		htm += "<li class=\"sidebar_nav col-sm-12\">";

		var $list = data[i].tableOperViews;
		if (data[i].hasOrg == 0) { /* 0不需要做部门限制，1需要部门限制 */
			htm += "<input type='checkbox' class='chk_1' name='tabeInfo' id='tree" + data[i].id + "'><label style='width: 26%;' for='tree" + data[i].id + "'>" +
				data[i].tableName + "</label><ul>";
			for (var j = 0; j < $list.length; j++) {
				htm += "<li class='col-sm-3'><input type=\"checkbox\" name='tableOperID' id=table" + $list[j].id +
					" value=" + $list[j].id + "><label for='table" + $list[j].id + "'>" + $list[j].operTypeName +
					"</label></li>";
			}
		} else {
			htm += "<label style='width: 26%;' id='tree" + data[i].id + "'>" + data[i].tableName + "</label><ul>";
			for (var j = 0; j < $list.length; j++) {
				//htm+="</br>-----------需要做部门级别的选择------------"
				var c = "<div class='panel-heading'><label class='fontstyle'>" +
					$list[j].operTypeName + "</label></div>" + "<div id=\"pickList\"><input type=\"hidden\" value=" + $list[j].id + "></div>";
				htm += c;
			}
		}
		htm += "</ul>";
	}
	htm += "</ul><button onclick='postt(this," + pid + ")' id=\"submit\" class=\"btn btn-primary\" type=\"button\">保  存</button></div>";
	$("#myTabContent").append(htm);

}
/**为部门列表添加数据函数  */
function addDept() {
	$("body").find("div [id='pickList']").each(function(i, ele) {
		var pick = $(ele).pickList({
			data: val
		});
	});

}
/**保存按钮点击后，数据异步提交函数*/
function postt(obj, pid) {
	var ids = "pid=" + pid + "&userGroup.id=" + $("#usergroupid").val() + "&userGroup.userGroupName=" + $("#usergroupname").val() + "&userGroup.description=" + $("#usergroupremark").val();
	var j = $(obj).parent().find("input:checkbox[name=tableOperID]:checked").length;
	/**获取所有复选框的值*/
	$(obj).parent().find("input:checkbox[name=tableOperID]:checked").each(function(i) {
		ids += "&details[" + i + "].tableOperID=" + $(this).val() + "&details[" + i + "].userGroupId=" + $("#usergroupid").val();
	});
	/**遍历select已经选择的*/
	$(obj).parent().find("div [name='sele']").find("select").each(function() {
		if ($(this).find("option").length > 0) {
			var deptList = "";
			var tableOperId = $(this).parent().parent().parent().find("input:hidden").val();
			$(this).find("option").each(function() {
				deptList += this.value + "|";
			});
			ids += "&details[" + j + "].tableOperID=" + tableOperId + "&details[" + j + "].userGroupId=" + $("#usergroupid").val() +
				"&details[" + j + "].deptList=" + deptList.substring(0, deptList.length - 1);
			j++;
		}
	});
	/**保存数据*/
	$.post("safe/addUserGroupDetails.do", ids, function(data) {});
}

/**************************根据权限组ID将对应已选择的按钮选中***********************/
function doChecked() {
	/**权限组ID，0表示个性化权限*/
	var userGroupId = $("#usergroupid").val();
	//根据用户组ID取得用户组已选细则
	$.post("safe/getusergroupchecked.do", {
		id: userGroupId
	}, function(data) {
		//获取已选数据，将对应的选择框选中
		checkedIt(data);
	});
}

function checkedIt(data) {

	for (var i = 0; i < data.length; i++) {

		var tableOper = data[i].tableOperID;
		var deptIds = data[i].deptIds;
		//如果部门集合不为空或者部门集合长度不等于0
		if (deptIds != null && deptIds.length != 0) {
			for (j = 0; j < deptIds.length; j++) {
				//移到所选的
				moveSelect(tableOper, deptIds[j]);
			}
		} else {
			//没有部门权限要求的，设置选择情况
			var checkid = "#table" + tableOper;
			$(checkid).attr('checked', 'true');
		}
	}

}
/**把已选的部门添加到选择*/
function moveSelect(tableOper, value) {
	var p = $("input:hidden[value='" + tableOper + "']").next("div [class='row']").find("div:first").find("#pickData option[value='" + value + "']");
	var h = $("input:hidden[value='" + tableOper + "']").next("div:first").find("div:last").children("#pickListResult");
	p.clone().appendTo(h);
	p.remove();
}

/**增删改查选择框选择状态改变后*/
function crudChange(obj) {
	var total = $(obj).parent().parent().find("li").length;
	var checkNum = $(obj).parent().parent().find("input:checkbox:checked").length;
	var parcheckbox = $(obj).parent().parent().parent().find("input:checkbox[name='tabeInfo']");
	if (total == checkNum) {
		$(parcheckbox).prop("checked", true);
	} else {
		$(parcheckbox).prop("checked", false);
	}
	//alert(total+"---"+checkNum);
}
/**二级菜单勾选后*/
function treeChecked(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().find("input:checkbox[name='tableOperID']").each(function() {
			$(this).prop("checked", true);
		});
	} else {
		$(obj).parent().find("input:checkbox[name='tableOperID']").each(function() {
			$(this).prop("checked", false);
		});
	}
}