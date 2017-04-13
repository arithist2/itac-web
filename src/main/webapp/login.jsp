<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta content="IE=11.0000" http-equiv="X-UA-Compatible">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE/9/10/11" >
		<title>iTAC Web</title>

		<link rel="shortcut icon" href="image/logo.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery.mloading.css">
		<script src="easyui/jquery.min.js"></script>
		<script src="easyui/jquery.easyui.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/login.js"></script>
		<script src="js/jquery.mloading.js"></script>
	</head>
	<body>
		<div id="login-div">
			<form id="login-form" action="/itac-web/login">
				<input type="text" id="login-user" class="login-user" maxlength="28" placeholder="User Name" tabindex="1"/><br>
				<input type="password" id="login-pwd" class="login-pwd" maxlength="28" placeholder="Password" tabindex="2"/><br>
				<input type="button" id="login-button" class="login-button" onclick="login();" onkeyup="login();" value="Login">
			</form>
		</div>
	</body>
</html>