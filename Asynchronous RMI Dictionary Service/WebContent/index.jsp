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

	<form  method="post" action="DictionaryServlet">
			<h2>Dictionary Service</h2>	
			<input name="query" placeholder="Enter Query Here" type="text" size="50"/>
			<input type="submit" value="Submit">											
	</form>		
	
	
	<% 	Map<String, Query> queries = (HashMap<String, Query>)request.getAttribute("queries");
		if(queries == null){
			//empty
		}
		else
		{
			out.print("<h3>Jobs in Queue</h3>");
		 	for(Entry<String, Query> entry : queries.entrySet())
		 	{
				Query q = entry.getValue();
				out.print("Query: " + q.getMessage());
				out.print("<br/>");
				out.print("Job Id: " + q.getJobID());
				out.print("<br/>");
				out.print("<br/>");
	 		}
		}
	%>

</body>
</html>