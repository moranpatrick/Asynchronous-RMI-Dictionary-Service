package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InQueue implements Runnable {
	private Query query;
	
	BlockingQueue<Query> queue = new ArrayBlockingQueue<Query>(10);
	
	public InQueue(Query query) {
		this.query = query;
		System.out.println("In INQ Constructor");
	}
	
	@Override
	public void run() {
        System.out.println("Thread Started: In Run()");
		
        try {
			queue.put(query);
			System.out.println("Total Objects In Queue: " + queue.size());
			System.out.println("Objects In This Queue: " + queue.toArray());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
