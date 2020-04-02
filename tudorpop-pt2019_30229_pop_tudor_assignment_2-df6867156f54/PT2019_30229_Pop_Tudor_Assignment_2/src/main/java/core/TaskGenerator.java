package core;

import java.util.TreeSet;

import simulator.ValuePerSecond;

public class TaskGenerator extends Thread{
	private TreeSet<TaskSolver> t;
	private ValuePerSecond peakValues;
	public static String realTimeLog ="";
	//public static String real ="";
	
	private float nrOfTasks;
	private int nrOfTaskSolvers;
	private float minServ;
	private float maxServ;
	private float minGenIntv;
	private float maxGenIntv;
	private float simulationTime;
	private String[] rezLog;
	private int waitTime;

	
	
	public TaskGenerator(int nrOfTaskSolvers, float minServ, float maxServ, float minGenIntv, float maxGenIntv, float simulationTime) {
		super();
		this.setT(new TreeSet(new TaskSolverComparator()));
		this.peakValues = new ValuePerSecond();
		
		this.nrOfTaskSolvers = nrOfTaskSolvers;
		this.minServ = minServ;
		this.maxServ = maxServ;
		this.minGenIntv = minGenIntv;
		this.maxGenIntv = maxGenIntv;
		this.nrOfTasks = 0;
		this.simulationTime = simulationTime;
		this.rezLog = new String[2]; rezLog[0]=""; rezLog[1]="";
		
		for(int i=0;i<nrOfTaskSolvers; i++) {
			t.add(new TaskSolver(i+1));
		}
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		float timePassed = 0.0f;
		while(timePassed < this.simulationTime) {
			
			try {
				this.waitTime = randomTaskInterval(this.minGenIntv, this.maxGenIntv); // generarea delay-ul pt aparitia unui alt task
				timePassed += this.waitTime/1000.0f;
				TaskSolver dummy;
				dummy = getT().first();
				getT().remove(dummy);
				dummy.addTask(dummy.randomTaskGen(this.minServ, this.maxServ)); // generare task cu un timp de asteptare random
				getT().add(dummy);
				this.peakTime((long)timePassed); // genereaza peak time moment
				
				sleep(this.waitTime); // asteapta pana la urmatorul task
				
			} catch (InterruptedException e) {
				System.out.println("exceptie in Simulator, run");
				System.out.println(e.getClass() + e.getMessage());
			}
			
		}
		
		this.stopThreads();
		
		realTimeLog += TaskSolver.logThings; // Afisajul pt Log-ul principal
		TaskSolver.logThings = ""; // init the thing "in a static way"
		for(TaskSolver i: getT()) {
			rezLog[0] += ("Thread: "+i.getSolverNumber()+" Solved: "+i.getNrTasksSolved()+" Tasks <br>"); //Statisticile de la finalul simularii
		}
		rezLog[1] = ("Peak time: " + this.peakValues); //Statistica de peak time
	}
	
	public String[] simulate() { // simularea pe o perioada de timp
		
		String[] rez = new String[2]; rez[0]=""; rez[1]="";
		this.start();
		rez = this.rezLog;
		
		return rez;
		
		
	}
	
	public synchronized static int randomTaskInterval(float min, float max) {//Genereaza delay-ul de generare al taskurilor	
		min *= 1000;
		max *= 1000;
		int range = (int) Math.abs(max - min);
		return (int)(Math.random() * range + min); //https://www.geeksforgeeks.org/java-math-random-method-examples/
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void stopThreads() {
		try {
			for(TaskSolver i : this.getT()) {
				i.setTimeToStop(false);
				i.stop();
			}
		}catch(Exception e) {
			System.out.println("Exceptie la oprirea threadurilor");
		}
	}
	
	public synchronized void resetThreads() {
		try {
			for(TaskSolver i : this.getT()) {
				i.setTimeToStop(true);
			}
		}catch(Exception e) {
			System.out.println("Exceptie la reinitializarea threadurilor");
		}
	}
	
	public synchronized void startThreads() {
		try {
			for(TaskSolver i : this.getT()) {
				if(!i.isAlive()) {
					i.start();					
				}
				//System.out.println("fucking start");
			}
		}catch(Exception e) {
			System.out.println(e.getCause() + "Exceptie la pornirea threadurilor");
			this.stopThreads();
		}
	}
	
	public synchronized void peakTime(long current) {//peak time
		current /= 1000.0f;
		float actual = this.avgWaitingTime();
		if(actual > this.peakValues.getValue()) {
			this.peakValues.setValue(actual);
			this.peakValues.setTimeMoment(current);
		}
	}
	
	public synchronized float avgWaitingTime() {// avg waiting time
		int total = 0;
		for(TaskSolver i : this.getT()) {
			total += i.avgTimePerTask();
		}
		if(this.nrOfTaskSolvers != 0) {
			return total/this.nrOfTaskSolvers/1000.0f;			
		}else {
			return 0;
		}
	}


	public TreeSet<TaskSolver> getT() {
		return t;
	}


	public void setT(TreeSet<TaskSolver> t) {
		this.t = t;
	}


	public float getNrOfTasks() {
		nrOfTasks = 0;
		for(TaskSolver i: this.t) {
			nrOfTasks += i.getNrTasksSolved();
		}
		return nrOfTasks;
	}


	public void setNrOfTasks(float nrOfTasks) {
		this.nrOfTasks = nrOfTasks;
	}

}
