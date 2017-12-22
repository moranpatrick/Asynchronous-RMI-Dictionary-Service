<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.client.Query"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Dictionary Service</title>
</head>
<body>

	<form  method="post" action="DictionaryServlet">
			<h2>Dictionary Service</h2>	
			<input name="query" placeholder="Enter Query Here" type="text" size="50"/>
			<input type="submit" value="Submit">											
	</form>		

</body>
</html>