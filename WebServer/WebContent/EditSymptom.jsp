<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit A Symptom</title>
<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
</head>
<body>
<div class="containter">
<div class="pageMain">
<div class="pageTitle">edit a symptom.</div>
<% SymptomStore symptom = (SymptomStore)request.getAttribute("symptom"); 
%>

<form class="potatoForm" name="editSymptom" action="http://localhost:8080/PotatoServer/Symptom" method="post" enctype="multipart/form-data">
<input class="hiddenID" type="text" name="id" value="<%= symptom.getId() %>" /><br />
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
</select> <br /><br />
<label>Symptom Description</label><br />
<input name="symptomDescription" placeholder="Symptom description" value="<%=symptom.getDescription() %>" /><br /><br />
<span id="uploadImageSpan">
<label>Images</label><br />
<input type="file" name="file1" /><br /><br />
</span>
<input type="submit" value="Update Symptom" />
</form>
</div>
</div>
</body>
</html>