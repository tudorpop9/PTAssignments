package core;

public class Task {
	
	public static int randomTaskNumber = 0;
	private int id;
	private int servingTime;
	
	public Task(int id, int servingTime) {
		super();
		this.id = id;
		this.servingTime = servingTime;
	}
	
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public synchronized int getServingTime() {
		return servingTime;
	}
	public synchronized void setServingTime(int servingTime) {
		this.servingTime = servingTime;
	}
	
	

	
	@Override
	public synchronized String  toString() {
		return "{"+this.id +", " + this.servingTime + "} ";
	}
	
	
}
