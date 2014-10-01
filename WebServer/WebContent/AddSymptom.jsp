<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
<title>Add A Symptom</title>
</head>
<body>
<div class="containter">
<div class="pageMain">
<div class="pageTitle">add a symptom.</div>

<form class="potatoForm" name="newSymptom" action="Symptom" method="post" enctype="multipart/form-data">
<label>Parent Symptom</label><br />
<select name="parentSymptom">
	<option value="null" >No parent</option>
<%
	List<SymptomStore> symptoms = (List<SymptomStore>)request.getAttribute("symptoms");
	if(symptoms != null)
	{
		for(SymptomStore symptom: symptoms) {
			%>
			<option value="<%= symptom.getId() %>"><%=symptom.getDescription() %></option>
			<%
		}
	}
%>
</select> <br /><br />
<label>Symptom Description</label><br />
<input type="text" name="symptomDescription" placeholder="Symptom description" /><br /><br />
<label>Symptom Type</label><br />
<select name="type">
	<option value="Plant">Plant</option>
	<option value="Pest">Pest</option>
	<option value="Tuber">Tuber</option>
</select><br /><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br /><br />
</span>
<input type="submit" value="Add Symptom" />
</form>
</div>
</div>
</body>
</html>