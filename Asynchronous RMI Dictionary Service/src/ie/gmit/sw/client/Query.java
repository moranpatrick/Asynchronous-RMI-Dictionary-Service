package ie.gmit.sw.client;

public class Query {
	private int jobID;
	private String message;
	
	public Query(int id, String msg){
		jobID = id;
		message = msg;
	}
	
	/*Getters and Setters*/
	
	public int getJobID() {
		return jobID;
	}
	
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	// Override the equals method so we can compared objects based upon their jobId
	@Override
	public boolean equals(Object obj) {
		if((Integer) obj == this.getJobID()){
			return true;
		}
		else{
			return false;
		}
	}	
}
