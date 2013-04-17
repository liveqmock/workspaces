<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绿岸网络运营管理平台-登录</title>
<style type="text/css">
html,body,div,h1,h2,p,img,ol,ul,li,table,tr,td,form {
	border: 0 none;
	margin: 0;
	padding: 0;
}

html {
	background: none repeat scroll 0 0 #FFFFFF;
	color: #333333;
	direction: ltr;
	font: 81.25%/1 arial, helvetica, sans-serif;
}

html,body {
	height: 100%;
	min-width: 100%;
	position: absolute;
	margin: 0px;
}

button,input,select,textarea {
	font-family: inherit;
	font-size: inherit;
}

button::-moz-focus-inner,input::-moz-focus-inner {
	border: 0 none;
}

.wrapper {
	min-height: 100%;
	position: relative;
}

.google-header-bar {
	background: none repeat scroll 0 0 #F5F5F5;
	border-bottom: 1px solid #E5E5E5;
	height: 71px;
	overflow: hidden;
}

.main {
	max-width: 1000px;
	min-width: 780px;
	width: auto;
	margin: 0 auto;
	padding-bottom: 100px;
	padding-top: 23px;
}

.product-info {
	margin: 0 385px 0 0;
}

.product-info h3 {
	font-size: 1.23em;
	font-weight: normal;
}

.product-info a:visited {
	color: #6611CC;
}

.product-info .g-button:visited {
	color: #666666;
}

.content {
	padding: 0 44px;
}

.clearfix:after {
	clear: both;
	content: ".";
	display: block;
	font-size: 0;
	height: 0;
	visibility: hidden;
}

.sign-in {
	float: right;
	width: 335px;
}

.signin-box h2 {
	font-size: 16px;
	height: 16px;
	line-height: 17px;
	margin: 0 0 1.2em;
	position: relative;
}

.signin-box label {
	display: block;
	margin: 0 0 1.5em;
}

.signin-box {
	background: none repeat scroll 0 0 #F5F5F5;
	border: 1px solid #E5E5E5;
	margin: 12px 0 0;
	padding: 20px 25px 15px;
}

.signin-box .email-label,.signin-box .passwd-label  .passpod-label {
	-moz-user-select: none;
	display: block;
	font-weight: bold;
	margin: 0 0 0.5em;
}

strong {
	color: #222222;
}

.signin-box input[type="text"],.signin-box input[type="password"] {
	font-size: 15px;
	height: 32px;
	width: 100%;
	-moz-border-bottom-colors: none;
	-moz-border-image: none;
	-moz-border-left-colors: none;
	-moz-border-right-colors: none;
	-moz-border-top-colors: none;
	-moz-box-sizing: border-box;
	background: none repeat scroll 0 0 #FFFFFF;
	border-color: #C0C0C0 #D9D9D9 #D9D9D9;
	border-radius: 1px 1px 1px 1px;
	border-right: 1px solid #D9D9D9;
	border-style: solid;
	border-width: 1px;
	display: inline-block;
	margin: 0;
	padding: 0 8px;
}

.signin-box input[type="submit"] {
	font-size: 13px;
	height: 32px;
	margin: 0 1.5em 1.2em 0;
}

.signin-box ul {
	margin: 0;
}

.redtext {
	color: #DD4B39;
}

.main {
	margin: 0 auto;
	padding-bottom: 100px;
	padding-top: 23px;
	width: 650px;
}

.g-button {
	-moz-transition: all 0.218s ease 0s;
	-moz-user-select: none;
	background-color: #F5F5F5;
	background-image: -moz-linear-gradient(center top, #F5F5F5, #F1F1F1);
	border: 1px solid #DCDCDC;
	border-radius: 2px 2px 2px 2px;
	color: #444444;
	cursor: default;
	display: inline-block;
	font-size: 11px;
	font-weight: bold;
	height: 27px;
	line-height: 27px;
	min-width: 46px;
	padding: 0 8px;
	text-align: center;
}

.g-button:hover {
	-moz-transition: all 0s ease 0s;
	background-color: #F8F8F8;
	background-image: -moz-linear-gradient(center top, #F8F8F8, #F1F1F1);
	border: 1px solid #C6C6C6;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
	color: #333333;
	text-decoration: none;
}

.g-button:active {
	background-color: #F6F6F6;
	background-image: -moz-linear-gradient(center top, #F6F6F6, #F1F1F1);
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset;
}

.g-button:visited {
	color: #666666;
}

.g-button-submit {
	background-color: #4D90FE;
	background-image: -moz-linear-gradient(center top, #4D90FE, #4787ED);
	border: 1px solid #3079ED;
	color: #FFFFFF;
	text-shadow: 0 1px rgba(0, 0, 0, 0.1);
}

.g-button-submit:hover {
	background-color: #357AE8;
	background-image: -moz-linear-gradient(center top, #4D90FE, #357AE8);
	border: 1px solid #2F5BB7;
	color: #FFFFFF;
	text-shadow: 0 1px rgba(0, 0, 0, 0.3);
}

.g-button-submit:active {
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.3) inset;
}

.product-headers {
	margin: 0 0 1.5em;
}

.product-headers h1 {
	font-size: 25px;
	margin: 0 !important;
}

.product-headers h2 {
	font-size: 16px;
	margin: 0.4em 0 0;
}

.features {
	margin: 2em 0 0;
	overflow: hidden;
}

.features li {
	margin: 3px 0 2em;
}

.features img {
	float: left;
	margin: -3px 0 0;
}

.features p {
	margin: 0 0 0 68px;
}

.features .title {
	font-size: 16px;
	margin-bottom: 0.3em;
}

.features.no-icon p {
	margin: 0;
}

.features .small-title {
	font-size: 1em;
	font-weight: bold;
}

.notification-bar {
	background: none repeat scroll 0 0 #F9EDBE;
	padding: 8px;
}

.mail .appsPromo {
	border: 1px solid #EBEBEB;
	margin-top: 25px;
	padding: 10px;
}

.mail .appsPromoHead {
	font-size: 16px;
	margin: 0 0 0.2em;
	padding-bottom: 0;
	padding-top: 2px;
}

.mail .appsPromoBody {
	margin-bottom: 2px;
	padding-top: 4px;
}

.mail ul.mail-links {
	list-style: none outside none;
	margin: 0;
	overflow: hidden;
}

.mail ul.mail-links li {
	float: left;
	margin: 0 20px 0 0;
}
.error{
	color: red;
	font-weight: bolder;
}
</style>
</head>
<body>
	<div class="wrapper">
		<div class="google-header-bar">
			<div class="header content clearfix"></div>
		</div>
		<div class="main content clearfix">
			<div class="sign-in">
				<div class="signin-box">
					<h2>
						登录 <strong></strong>
					</h2>
					<form id="loginform" method="post" action="j_spring_security_check">
						<label> <strong class="email-label">用户名</strong> <input
							id="username" type="text" value="" name="j_username"
							spellcheck="false" />
						</label> <label> <strong class="passwd-label">密码</strong> <input
							id="password" type="password" name="j_password" />
						</label><label> <strong class="passpod-label">动态密码</strong> <input
							id="passpod" type="password" name="j_passpod" />
						</label><input id="signIn" class="g-button g-button-submit" type="submit"
							value="登录" name="signIn" />
						<div>
						<% if(request.getParameterMap().containsKey("error")){ %>
							<span class="error"><%=request.getAttribute("msg") %></span>
						<%} %>
						</div>
					</form>
				</div>
			</div>
			<div class="product-info mail">
				<div class="product-headers">
					<h1 class="redtext">绿岸网络</h1>
					<h2>游戏管理平台</h2>
				</div>
				<p>绿岸运营管平台，其提供一站式的：</p>
				<ul class="features">
					<li><img alt=""
						src="//ssl.gstatic.com/images/icons/feature/filing_cabinet-g42.png">
						<p class="title">游戏业务后台管理</p>
						<p>&nbsp;</p></li>
					<li><img alt=""
						src="//ssl.gstatic.com/images/icons/feature/filing_cabinet-g42.png">
						<p class="title">数据统计分析</p>
						<p>&nbsp;</p></li>
					<li><img alt=""
						src="//ssl.gstatic.com/images/icons/feature/filing_cabinet-g42.png">
						<p class="title">玩家帐号管理</p>
						<p>&nbsp;</p></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>