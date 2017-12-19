package ie.gmit.sw;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DictionaryServlet")
public class DictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Query query = null;
	private Map<String, Query> queries = new HashMap<String, Query>();
	String test;
	
	Thread inQueue;
	
	private static int idCounter = 0;

	BlockingQueue<Query> queue = new ArrayBlockingQueue<Query>(10);
	

    public DictionaryServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		idCounter++;
		query = new Query(idCounter, request.getParameter("query"));
		System.out.println("Job ID: " + query.getJobID());
		
		queries.put(Integer.toString(idCounter), query);
			
		Runnable r = new InQueue(query);
		new Thread(r).start();
					
		request.setAttribute("queries", queries);
		
		try {
			DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");
			test = ds.findDefinition(request.getParameter("query"));
		} catch (NotBoundException e) {
			
			e.printStackTrace();
		}
		System.out.println("Message From Remote Server!: " + test);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
