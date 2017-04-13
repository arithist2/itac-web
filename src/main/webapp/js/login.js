$(document).ready(function(){
	$("#login-div").keyup(function(event){
		if (event.keyCode == 13) {
			login();
		}
	});
	
	$(function(){
		$("#login-user").focus();
	});
	
	$("body").mLoading({
	    text:"加载中...",//加载文字，默认值：加载中...
	    icon:"",//加载图标，默认值：一个小型的base64的gif图片
	    html:false,//设置加载内容是否是html格式，默认值是false
	    content:"",//忽略icon和text的值，直接在加载框中显示此值
	    mask:true//是否显示遮罩效果，默认显示
	});
	$("body").mLoading("hide");
});

function login() {
	$("body").mLoading("show");
	var loginName = $("#login-user").val();
	var password = $("#login-pwd").val();
	if (JUDGE.isNull(loginName) || JUDGE.isNull(password)) {
		$.messager.alert("Login","User name or password can not be empty.","Warning");
		return;
	}
	
	var loginInfo = {
		"loginName":loginName,
		"password":password
	};
	
	$.ajax({
		type:"post",
		url:"login",
		data:loginInfo,
		success:function(resp)
		{
			$("body").mLoading("hide");
			if (resp.code == 200)
			{
				$("#login-user").empty();
				$("#login-pwd").empty();
				window.location.href = "home";
			}else
			{
				$.messager.alert("Login", resp.msg, "Warning");
			}
		},
		error:function(event, request, settings)
		{
			$("body").mLoading("hide");
			$.messager.alert("Login","Failed to connect to server.","error");
		}
	});
}