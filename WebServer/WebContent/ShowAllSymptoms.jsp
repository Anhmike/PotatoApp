<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
<script type="text/javascript">$(document).ready(function(){
	$('.deleteButton').on('click', function(e){

		var id = $(this).attr('id');
		window.location.assign("http://localhost:8080/PotatoServer/Symptom/delete?id="+ id);
	});
	
	$('.editButton').on('click', function(e){

		var id = $(this).attr('id');
		window.location.assign("http://localhost:8080/PotatoServer/Symptom/edit?id="+ id);
	});
	$('.addButton').on('click', function (e) {
		window.location.assign("http://localhost:8080/PotatoServer/AddSymptom.jsp");
	});
})</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symptoms</title>
</head>
<body>
<div class="links"><a href="/PotatoServer/Problem">problems</a><a href="/PotatoServer/Symptom">symptoms</a></div>
<div class="containter">
<div class="pageMain">
<div class="pageTitle">list of symptoms.</div>
<div class="addButton">add a symptom</div>
<%
System.out.println("In render");
List<SymptomStore> symptoms = (List<SymptomStore>)request.getAttribute("symptoms");
if (symptoms==null){
 %>
	<div class="listEntry">No problems found</div>
	<% 
}else{
Iterator<SymptomStore> iterator;
iterator = symptoms.iterator();     
while (iterator.hasNext()){
	SymptomStore ss = (SymptomStore)iterator.next();

	%>
	<div class="listEntry">
	<div class="entryDetails"><%= ss.getDescription() %></div><div id="<%=ss.getId() %>" class="editButton">edit</div><div id="<%=ss.getId() %>" class="deleteButton">remove</div>
	</div>
	<%
}
}
%>
</div>
</div>
</body>
</html>