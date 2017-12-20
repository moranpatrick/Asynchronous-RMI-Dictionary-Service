package ie.gmit.sw.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.server.DictionaryService;


public class RMIWorker implements Runnable{
	/* RMI Worker class handles Two Queues - One In and One Out Queue */
	private BlockingQueue<Query> inQ;
	private BlockingQueue<Query> outQ;	
	private Query query;
	private String definition;
	
	public RMIWorker(BlockingQueue<Query> inQ, BlockingQueue<Query> outQ) {
		this.inQ = inQ;
		this.outQ = outQ;
	}
	
	@Override
	public void run() {		
		while(true){
			
			try {
				// Poll the In Queue every 10 Seconds
				query = inQ.poll(10, TimeUnit.SECONDS);
				
				//Ask the registry listening in port 1099 for the instance of the DictionaryService Object
				DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");
				
				if(query != null){
					//Thread.sleep(10000);
					definition = ds.findDefinition(query.getMessage());
					
					// Create a new Query with the JobId and Definition and add it to the OutQ
					query = new Query(query.getJobID(), definition);
					outQ.offer(query);
					//System.out.println("Number of Objects in outQ: " + outQ.size());
				}										
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {			
				e.printStackTrace();
			} catch (NotBoundException e) {				
				e.printStackTrace();
			}			
		}		
	}	
}
