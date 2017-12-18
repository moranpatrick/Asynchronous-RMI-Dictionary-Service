package ie.gmit.sw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DictionaryServelet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DictionaryServelet(){
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("IN DO GET()");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		
		String get_query = request.getParameter("query");
		System.out.println("Query From Client: " + get_query);
		
		writer.println("<h1>Query Entered Was</h1>");
		writer.print("<h3>" + get_query +"</h3>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN DO POST()");
		//doGet(request, response);
	}

}
