<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add A Symptom</title>
</head>
<body>
<h1>Add a new symptom</h1>

<form name="newSymptom" action="Symptom" method="post" enctype="multipart/form-data">
<label>Symptom Name</label><br />
<input type="text" name="symptomName" placeholder="Symptom name"/><br />
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
</select> <br />
<label>Symptom Description</label><br />
<input type="text" name="symptomDescription" placeholder="Symptom description" /><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br />
</span>
<input type="submit" value="Add Symptom" />
</form>
</body>
</html>