<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add a Problem</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
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
<h1>Add a new problem</h1>

<form name="newProblem" action="Problem" method="post" enctype="multipart/form-data">
<label>Problem Name</label><br />
<input type="text" name="problemName" placeholder="Problem name"/><br />
<label>Problem Type</label><br />
<select name="problemType">
	<option value="Pest" >Pest</option>
	<option value="Plant">Plant</option>
	<option value="Tubor">Tubor</option>
</select> <br />
<label>Problem Description</label><br />
<input type="text" name="problemDescription" placeholder="Problem description" /><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br />
</span>
<button type="button" id="addImageButton">Add another image</button><br /><br />

<input type="submit" value="Add Symptoms" />
</form>
</body>
</html>