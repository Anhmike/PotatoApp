<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
<%String problemName = (String)request.getAttribute("problemName"); %>
<title>Add Symptoms for <%=problemName %></title>
</head>
<body>
<div class="containter">
<div class="pageMain">
<div class="pageTitle">add symptoms for <%=problemName %>.</div>
<form action="/PotatoServer/Problem/saveSymptoms" method="post" class="potatoForm">
<input type="text" class="hiddenID" value="<%=problemName %>" name="problemName"/>
<%
	List<SymptomStore> symptoms = (List<SymptomStore>)request.getAttribute("symptoms");
	if(symptoms != null)
	{
		for(int i = 0; i < symptoms.size(); i++) {
			%>
			<div class="checkBox"><input type="checkbox" name="symptom" value="<%=symptoms.get(i).getId() %>"><%=symptoms.get(i).getDescription() %></div>
			<%if (i+1 < symptoms.size())
			{ %>
			<div class="checkBox"><input type="checkbox" name="symptom" value="<%=symptoms.get(i+1).getId() %>"><%=symptoms.get(i+1).getDescription() %></div>
			<% 
			}
			i++;
		}
	}
%>
<input type="submit" value="Save Symptoms" />
</form>
</div>
</div>

</body>
</html>