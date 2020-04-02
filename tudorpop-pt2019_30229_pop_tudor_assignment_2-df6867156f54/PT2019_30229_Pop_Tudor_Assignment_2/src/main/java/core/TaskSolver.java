package core;

import java.util.LinkedList;
import java.util.Queue;

public class TaskSolver extends Thread{
	
	public static String logThings = "";
	private int solverNumber;
	private int waitingTime;
	private int totalTimePassed;
	private int nrTasksSolved;
	private boolean timeToStop;

	public Queue<Task> q;
	
	@Override
	public void run() {
		Task t;
		//this.runningBoi = true;
		while(this.timeToStop) {
			
			if(!this.emptyQueue()) {			
				t = this.removeTask();
				try {
					sleep(t.getServingTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("la sleep.. int taskSolver");
				}
				this.nrTasksSolved ++;
				this.updateTimePassed(t);
				this.updateServingTime(t);
				//System.out.println("The task :"+"Id: "+t.getId() + ", Serving time:"+(t.getServingTime()/1000f)+" seconds   Solved by Thread: "+this.solverNumber);
				this.updateLogThings(t);
				
			}
			
		}
	}
	public synchronized void updateLogThings(Task t) {
		logThings += ("The task :"+"Id: "+t.getId() + ", Serving time:"+(t.getServingTime()/1000f)+" seconds   Solved by Thread: "+this.solverNumber+ "\n");
	}
	public synchronized void updateTimePassed(Task t) {
		this.totalTimePassed += t.getServingTime();
	}
	
	public synchronized void updateServingTime(Task t) {
		this.waitingTime -= t.getServingTime();
	}
	
	//Generator random de task-uri
	public synchronized boolean emptyQueue() {
		return this.q.isEmpty();
	}
	
	public synchronized Task removeTask() {
		return this.q.poll();
	}
	public static  synchronized Task randomTaskGen(float min, float max) {
		min*=1000;
		max*=1000;
		Task.randomTaskNumber++;
		return new Task(Task.randomTaskNumber,TaskSolver.randomServingTime(min, max) );
	}
		//Random serving time generator between min/max limits
	public static synchronized int randomServingTime(float min, float max) {	
		int range = (int) Math.abs(max - min);
		return (int)(Math.random() * range + min); //https://www.geeksforgeeks.org/java-math-random-method-examples/
	}
		
	public synchronized String realTimeString() {
		return "TaskSolver "+this.getSolverNumber()+": " + this.q.toString();
	}
	
	public synchronized  void addTask(Task t) {
		this.q.add(t);
		this.waitingTime += t.getServingTime();
	}
	
	public synchronized float avgTimePerTask() {
		if (this.nrTasksSolved != 0) {
			return 1.0f*this.totalTimePassed/this.nrTasksSolved;			
		}else {
			return 0;
		}
	}
	
	public TaskSolver(int solverNumber) {
		super();
		this.solverNumber = solverNumber;
		this.waitingTime = 0;
		this.totalTimePassed = 0;
		this.nrTasksSolved = 0;
		timeToStop = true;
		this.q = new LinkedList<Task>();
	}
	
	public synchronized String solvedTasks() {
		return ("Thread: "+this.getSolverNumber()+" Solved: "+this.getNrTasksSolved()+" Tasks<br>");
	}
	
	public synchronized int getSolverNumber() {
		return solverNumber;
	}



	public synchronized void setSolverNumber(int solverNumber) {
		this.solverNumber = solverNumber;
	}



	public synchronized int getWaitingTime() {
		return waitingTime;
	}



	public synchronized void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}



	public synchronized int getTotalTimePassed() {
		return totalTimePassed;
	}



	public synchronized void setTotalTimePassed(int totalTimePassed) {
		this.totalTimePassed = totalTimePassed;
	}



	public synchronized int getNrTasksSolved() {
		return nrTasksSolved;
	}



	public synchronized void setNrTasksSolved(int nrTasksSolved) {
		this.nrTasksSolved = nrTasksSolved;
	}

	public synchronized boolean isTimeToStop() {
		return timeToStop;
	}

	public synchronized void  setTimeToStop(boolean timeToStop) {
		this.timeToStop = timeToStop;
	}
	
	@Override
	public String toString() {
		return this.q.toString();
	}
}
