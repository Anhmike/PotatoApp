<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/PotatoServer/css/main.css" />
<script type="text/javascript">$(document).ready(function(){
	$('.deleteButton').on('click', function(e){

		var id = $(this).attr('id');
		window.location.assign("http://localhost:8080/PotatoServer/Problem/delete?id="+ id);
	});
	
	$('.editButton').on('click', function(e){

		var id = $(this).attr('id');
		window.location.assign("http://localhost:8080/PotatoServer/Problem/edit?id="+ id);
	});
	$('.addButton').on('click', function (e) {
		window.location.assign("http://localhost:8080/PotatoServer/AddProblem.jsp");
	})
})</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problems Home</title>
</head>
<body>
<div class="container">
<div class="pageMain">
<div class="pageTitle">list of problems.</div>

<div class="addButton">add a problem</div>
<%
System.out.println("In render");
List<ProblemStore> problems = (List<ProblemStore>)request.getAttribute("Problems");
if (problems==null){
 %>
	<div class="listEntry">No faults found</div>
	<% 
}else{
%>

<%
Iterator<ProblemStore> iterator;


iterator = problems.iterator();     
while (iterator.hasNext()){
	ProblemStore md = (ProblemStore)iterator.next();

	%>
	<div class="listEntry"><div class="entryDetails"><%= md.getName() %><%=md.getDescription() %></div><div id="<%=md.getId() %>" class="editButton">edit</div><div id="<%=md.getId() %>" class="deleteButton">remove</div></div>
	<%
}
}
%>
</div>
</div>
</body>
</html>