<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403 forbidden</title>
</head>
<body>
	<%--
		org.springframework.security.access.AccessDeniedException ex = (org.springframework.security.access.AccessDeniedException) request
				.getAttribute(org.springframework.security.web.WebAttributes.ACCESS_DENIED_403);
		java.io.StringWriter sw = new java.io.StringWriter();
		ex.printStackTrace(new java.io.PrintWriter(sw));
	--%>
	<h2 style="color:red;">对不起，您没有权限进行该操作问！</h2>
	<%-- <p>
		请<a href="login.jsp">重登录系统</a>
	</p>
	<p>
		Access to the specified resource has been denied for the following
		reason: <strong><%=ex.getMessage()%></strong>.
	</p>
	<em>Error Details (for Support Purposes only):</em>
	<br />
	<blockquote>
		<pre><%=sw.toString()%></pre>
	</blockquote> --%>
</body>
</html>