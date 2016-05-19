$(document).ready(function(){
	$("body").append("<div class='alerts'  style='display:none;''><h2>消息</h2><div class='alert_con'><p id='ts'></p><div style='line-height:70px'><a class='btn alertYes'>确定</a></div></div></div>");
	$(".alertYes").click(function() {
		alert_hide();
	})
});


var hyst = {
	author : "rpj",
	mail : "renpengju@515cn.com"
};
hyst.msg = {
	alert : function(msg) {
		$("#ts").html(msg);
		alert_show();
	}
}
function alert_hide() {
	$(".alerts").animate({
		"top" : "-40%"
	}, 300);
}
function alert_show() {
	$(".alerts").show().animate({
		"top" : "45%"
	}, 300);
}
