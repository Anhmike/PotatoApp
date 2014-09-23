<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Potato Database</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="title">Potato Disease and Pest Database</div>
			<form name="loginForm" action="Login" method="post">
				<%
					if (request.getParameter("r") != null) {
				%>
				<b>Incorrect username or password</b><br />
				<%
					}
				%>
				<input type="text" name="username" class="loginInput" placeholder="Username" /><br />
				<input type="password" name="password" class="loginInput" placeholder="Password"/><br />
				<input class="loginButton" type="submit" value="Login" />
			</form>
		</div>
	</div>
</body>
</html>