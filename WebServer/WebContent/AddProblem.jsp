<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<h1>Add a new problem</h1>

<form name="newProblem" action="Problem" method="post">
<label>Problem Name</label>< br/>
<input type="text" name="problemName" /><br />
<label>Problem Type</label><br />
<select name="problemType">
	<option value="Pest" />
	<option value="Plant" />
	<option value="Tubor" />
</select> <br />
<label>Problem Description</label><br />
<input type="text" value="Problem description" /><br />
<label>Images</label><br />
<input type="file" /><br />

</form>
</body>
</html>