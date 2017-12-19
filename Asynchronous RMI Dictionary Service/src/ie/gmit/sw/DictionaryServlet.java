package ie.gmit.sw;

import java.io.IOException;
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
			
		inQueue = new Thread(new InQueue(query));
		inQueue.start();
					
		request.setAttribute("queries", queries);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
