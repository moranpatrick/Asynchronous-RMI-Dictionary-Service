<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.client.Query"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Dictionary Service</title>
</head>
<body>	
	
	<h2>Dictionary Service</h2>
	<form  method="get" action="DictionaryServlet">
		<input name="jobID" value=<%=request.getAttribute("jobId")%> type="text" size="50"/>
		<input type="submit" value="Submit">											
	</form>	
	
	Definition = <%=request.getAttribute("definition")%>
	

</body>
</html>