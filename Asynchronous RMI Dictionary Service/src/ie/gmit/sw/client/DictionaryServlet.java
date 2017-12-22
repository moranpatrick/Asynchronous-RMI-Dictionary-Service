package ie.gmit.sw.client;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DictionaryServlet")
public class DictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Query query = null;	
	private BlockingQueue<Query> inQ = new ArrayBlockingQueue<Query>(10);
	private BlockingQueue<Query> outQ = new ArrayBlockingQueue<Query>(10);
	private RMIWorker worker;	
	private static int idCounter = 0;	

    public DictionaryServlet() {
        worker = new RMIWorker(inQ, outQ);
        //Start a worker thread to handles the queues between the remote server and the servlet
        new Thread(worker).start();       
    }
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String definition;

		definition = checkQueue(request.getParameter("jobID"));
		
		request.setAttribute("definition", definition);

		request.setAttribute("jobId", query.getJobID());
		this.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		idCounter++;
		// Create a new Query Object Based upon user input
		query = new Query(idCounter, request.getParameter("query"));
		
		//Add query object to in queue
		try {
			inQ.put(query);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}					
		request.setAttribute("jobId", query.getJobID());
		this.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);	
	}
	
	public String checkQueue(String id){
		String result = "";
		
		// Retrieves but does not remove the head of the queue
		Query _query = outQ.peek();
		
		// Using the Override Equals Method in Query Class we can compare on JobId's
		if(_query != null && _query.getJobID() == Integer.parseInt(id)){
			result = outQ.poll().getMessage();
		}
		else{
			result = "No";
		}
		return result;		
	}
}
