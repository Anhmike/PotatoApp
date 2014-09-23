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
<script type="text/javascript">$(document).ready(function(){
	$('#delete').on('click', function(e){

		var id = $('input[name=Select]:checked').val();
		window.location.replace("http://localhost:8080/PotatoServer/Symptom/delete?id="+ id);
	});
	
	$('#edit').on('click', function(e){

		var id = $('input[name=Select]:checked').val();
		window.location.replace("http://localhost:8080/PotatoServer/Symptom/edit?id="+ id);
	});
})</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symptoms</title>
</head>
<body>
<h1> List of Problems </h1>
<%
System.out.println("In render");
List<SymptomStore> symptoms = (List<SymptomStore>)request.getAttribute("symptoms");
if (symptoms==null){
 %>
	<p>No symptoms in the database</p>
	<% 
}else{
Iterator<SymptomStore> iterator;
iterator = symptoms.iterator();     
while (iterator.hasNext()){
	SymptomStore ss = (SymptomStore)iterator.next();

	%>
	<input type= "radio" name="Select" value="<%=ss.getId() %>" >ID:<%=ss.getId() %>  <%=ss.getDescription() %><br/>
	<%
}
}
%>
<input id="delete" type="submit" name="submit" value="Delete"> <input id="edit" type="submit" name="submit" value="Edit">

</body>
</html>