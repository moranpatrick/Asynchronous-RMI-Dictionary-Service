package ie.gmit.sw.client;

import java.io.IOException;
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
	
	private BlockingQueue<Query> inQ = new ArrayBlockingQueue<Query>(5);
	private BlockingQueue<Query> outQ = new ArrayBlockingQueue<Query>(10);
	private RMIWorker worker;
	
	
	
	//private Map<String, Query> queries = new HashMap<String, Query>();
	//String test;
	
	
	
	private static int idCounter = 0;	

    public DictionaryServlet() {
        worker = new RMIWorker(inQ, outQ);
        //worker.start();
        new Thread(worker).start();       
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String definition;
		definition = checkQueue(request.getParameter("jobID"));
		request.setAttribute("definition", definition);
		this.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		idCounter++;
		query = new Query(idCounter, request.getParameter("query"));
		
		//Add query object to in queue
		try {
			inQ.put(query);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		//queries.put(Integer.toString(idCounter), query);				
		request.setAttribute("jobId", query.getJobID());
		this.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);	
	}
	

	
	public String checkQueue(String id){
		System.out.println("Checking JobID: " + id);
		String result = "";
		Query _query = outQ.peek();
		
		if(_query != null && _query.getJobID() == Integer.parseInt(id)){
			result = outQ.poll().getMessage();
		}
		
		return result;
		
	}

}
