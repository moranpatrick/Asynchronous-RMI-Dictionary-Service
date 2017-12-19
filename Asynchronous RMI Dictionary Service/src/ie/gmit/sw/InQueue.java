package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InQueue implements Runnable {
	private Query query;
	
	private BlockingQueue<Query> queue;
	
	public InQueue(Query query) {
		this.query = query;
		queue = new ArrayBlockingQueue<Query>(10);
		queue.add(query);
		System.out.println("IN CONSTRUCTOR: \nNumber Of Items In Queue: " + queue.size());
	}
	
	@Override
	public void run() {
		System.out.println("Thread Starting...");
		
	}
}
