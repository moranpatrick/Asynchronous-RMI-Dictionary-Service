<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.client.Query"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Dictionary Service</title>
</head>
<body>		
	<h2>Dictionary Service</h2>
	<form name="myForm" method="get" action="DictionaryServlet">
		<input name="jobID" value=<%=request.getAttribute("jobId")%> type="hidden"/>												
	
		<%
			String definition = (String)request.getAttribute("definition");
			String jobID = (String)request.getAttribute("jobId").toString();
			if(definition == null || definition == "No"){
				out.println("Waiting For Response...");
			}
			else{
				out.print("<b>Response:</b> " + definition);
			}
		%>	
	</form>
	<br />
	<br />
	<a href="index.jsp"><button>Make Another Query</button></a>
	
	<script type="text/javascript">
		// Reloads The page every 10 seconds and the form does a get - which polls the out queue for a response
		window.setTimeout(function() { document.myForm.submit(); }, 10000);
	</script>

	
</body>
</html>