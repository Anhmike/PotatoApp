<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.PotatoServer.Stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problems Home</title>
</head>
<body>
<h1> List of Problems </h1>
<%
System.out.println("In render");
List<ProblemStore> problems = (List<ProblemStore>)request.getAttribute("Name");
if (problems==null){
 %>
	<p>No faults found</p>
	<% 
}else{
%>


<% 
Iterator<ProblemStore> iterator;


iterator = problems.iterator();     
while (iterator.hasNext()){
	ProblemStore md = (ProblemStore)iterator.next();

	%>
	<input type= "radio" name="Select" value="Name?P_ID=<%=md.getId() %>" >ID:<%=md.getId() %>  <%=md.getDescription() %><br/><%
}
}
%>
<input type="submit" name="submit" value="Delete"> <input type="submit" name="submit" value="Edit">

</body>
</html>