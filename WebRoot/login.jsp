<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh">

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>保密平台登录</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/bootstrap/signin.css" rel="stylesheet">

<!-- form表单样式 -->
<link rel="stylesheet" type="text/css" href="css/form.css" />

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 8]>
		  <script type="text/javascript">
			alert("对不起，您的浏览器不支持此网站的浏览!\n 请使用chrome浏览器!")
		  </script>
	    <![endif]-->

</head>
<body>

	<div class="container">
		<form id="formsubmit" class="form-signin" action="user/login.do"
			method="post">
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<h2 class="form-signin-heading">保密综合管理平台</h2>
			<br /> <label for="inputEmail" class="sr-only">用户名</label> <input
				type="text" id="inputName" name="account"
				class="form-control required" maxlength="10" minlength="2"
				placeholder="域账号" autofocus> <label for="inputPassword"
				class="sr-only">密码</label> <input type="password" id="inputPassword"
				name="password" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label><br />

			</div>
			<div class="error">
				<label> ${msg} </label>
			</div>
			<button class="btn btn-lg btn-success btn-block" type="submit">登录</button>

		</form>
		<div class="connect">
			<p>广东航宇卫星科技有限公司授权.</p>
		</div>
	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->


	<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>

	<script src="js/bootstrap/bootstrap.js" type="text/javascript"
		charset="utf-8"></script>

	<!--验证-->
	<script src="js/jquery.validation/jquery.validate.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.validation/messages_zh.js"
		type="text/javascript" charset="utf-8"></script>

	<script src="js/md5.js" type="text/javascript" charset="utf-8"></script>



	<script type="text/javascript">
		$.validator.setDefaults({
			submitHandler : function() {
				$.ajax({
					url:"user/getsalt.do",
					type:"post",
					async:false,
					success:function(salt){
						//console.log(data);
						var inputPassword = hex_md5(hex_md5($("#inputPassword").val()) +salt);
				        $("#inputPassword").val(inputPassword);
					},
					error:function(error){
						alert(error);
					}
				});
				return true;
			}
		});
		$().ready(function() {
			/* 密码验证规则 */
			jQuery.validator.addMethod(
							"isPassword",
							function(value, element) {
								var hh = /([0-9].*([a-zA-Z].*[!$@^&*?#%]|[!$@^&*?#%].*[a-zA-Z])|[a-zA-Z].*([0-9].*[!$@^&*?#%]|[!$@^&*?#%].*[0-9])|[!$@^&*?#%].*([0-9].*[a-zA-Z]|[a-zA-Z].*[0-9]))/;
								return (hh.exec(value)) ? true
										: false;
							},
							"密码由字母与数字组合并包含特殊字符（除字母、数字、空格外的字符）并且8位以上！");

			/**
			 * 1、如果这个参数为true，那么表单不会提交，只进行检查，调试时十分方便。像这样：$("#formsubmit").validate({debug:true});
			 * 2、如果在class中指定required、或有required属性、maxlength属性的时候，在调用validate方法的时候就不用指定rules设置验证规则了
			 * 		否则就要指定验证规则：
			 * $("#formsubmit").validate({
			    	rules:{
			    		required:true,
			    		maxlength:20
			    	}
				});
			 *3、自定义验证提示信息用messages，如下
			 */

			//
			$("#formsubmit").validate({
				rules : {
					"account" : {
						required : true
					},
					"password" : {
						required : true,
						isPassword : false
					}
				},
				messages : {
					"account" : {
						required : "请填写域账号"
					},
					"password" : {
						required : "密码不能为空",
						isPassword : "密码由字母与数字组合并包含特殊字符（除字母、数字、空格外的字符）并且8位以上！"
					}
				}
			});
		});

		window.onload = function() {
			$(".connect p").eq(0).animate({
				"left" : "38%"
			}, 600);
		}
	</script>

</body>

</html>