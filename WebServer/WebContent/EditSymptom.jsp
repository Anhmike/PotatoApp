<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit A Symptom</title>
</head>
<body>
<h1>Update symptom information</h1>
<% SymptomStore symptom = (SymptomStore)request.getAttribute("symptom"); 
%>

<form name="editSymptom" action="Symptom" method="post" enctype="multipart/form-data">
<input type="text" name="id" value="<%= symptom.getId() %>" /><br />
<label>Parent Symptom</label><br />
<select name="parentSymptom">
	<option value="null" >No parent</option>
<%
	List<SymptomStore> symptomList = (List<SymptomStore>)request.getAttribute("symptoms");
	if(symptomList != null)
	{
		for(SymptomStore currentSymptom: symptomList) {
			%>
			<option value="<%= currentSymptom.getId() %>" <% if(symptom.getParentSymptom() == currentSymptom.getId()) {out.print("selected"); } %>> <%=currentSymptom.getDescription() %></option>
			<%
		}
	}
%>
</select> <br />
<label>Symptom Description</label><br />
<input type="text" name="symptomDescription" placeholder="Symptom description" value="<%=symptom.getDescription() %>"/><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br />
</span>
<input type="submit" value="Update Symptom" />
</form>
</body>
</html>