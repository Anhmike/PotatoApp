<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit a Problem</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
<script type="text/javascript">
$(document).ready(function () {
	var imageCount = 1;
	var allowedImageCount = 5;
	$('#addImageButton').on('click', function (e) {
		var imageSpan = $('#uploadImageSpan');
		if(imageCount < allowedImageCount) {
			imageCount++;
			imageSpan.html(imageSpan.html() + '<input type=\"file\" name=\"file' + imageCount + '\" /><br />');
			if(imageCount == allowedImageCount) { $('#addImageButton').remove(); }
		} 
;	});
});
</script>
</head>
<body>
<div class="container">
<div class="pageMain">
<div class="pageTitle">edit a problem</div>
<% ProblemStore problem = (ProblemStore)request.getAttribute("problem"); 
%>
<form class="potatoForm" name="editProblem" action="../Problem" method="post" enctype="multipart/form-data">
<input class="hiddenID" name="id" value="<%=problem.getId() %>"/>
<label>Problem Name</label><br />
<input type="text" name="problemName" placeholder="Problem name" value="<%= problem.getName() %>"/><br />
<label>Problem Type</label><br />
<select name="problemType">
	<option value="Pest" <% if(problem.getType().equals("Pest")) {out.print("selected"); } %>>Pest</option>
	<option value="Plant" <% if(problem.getType().equals("Plant")) {out.print("selected"); } %>>Plant</option>
	<option value="Tuber" <% if(problem.getType().equals("Tuber")) {out.print("selected"); } %>>Tuber</option>
</select> <br />
<label>Problem Description</label><br />
<textarea id="problemDescription" name = "problemDescription"  placeholder= "Description" rows = "10" cols ="80" ><%= problem.getDescription() %></textarea><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br />
</span>
<button type="button" id="addImageButton">Add another image</button><br /><br />

<input type="submit" name="submit" value="Save">
</form>
</div>
</div>
</body>
</html>