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
	private String test;
	public RMIWorker(BlockingQueue<Query> inQ, BlockingQueue<Query> outQ) {
		this.inQ = inQ;
		this.outQ = outQ;
	}
	
	@Override
	public void run() {
		System.out.println("Thread Started...");
		
		while(true){
			
			try {
				//Thread.sleep(10000);
				// Poll the Web Server every 10 Seconds
				query = inQ.poll(10, TimeUnit.SECONDS);
				System.out.println("Number of Objects in Q: " + inQ.size());				
				DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");
				
				if(query != null){
					test = ds.findDefinition(query.getMessage());
					System.out.println("RMI Response: " + test);
					
					query = new Query(query.getJobID(), test);
					outQ.add(query);
				}
											
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}	
}
