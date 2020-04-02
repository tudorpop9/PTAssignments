package simulator;

import java.util.*;

import javax.swing.JLabel;

//import com.sun.swing.internal.plaf.synth.resources.synth;

import core.*;

public class Simulator extends Thread{
	private TreeSet<TaskSolver> t;
	private TaskSolver[] Solvers;
	private ValuePerSecond peakValues;
	public static String realTimeLog ="";
	public static String finalStats="";
	public static String staticPeakTime="";
	//public static String real ="";
	
	private float nrOfTasks;
	private int nrOfTaskSolvers;
	private float minServ;
	private float maxServ;
	private float minGenIntv;
	private float maxGenIntv;
	private float simulationTime;
	private int waitTime;
	public String[] rez;
	
	public Simulator(int nrOfTaskSolvers, float minServ, float maxServ, float minGenIntv, float maxGenIntv, float simulationTime) {
		super();
		this.setSolvers(new TaskSolver[nrOfTaskSolvers]);
		this.peakValues = new ValuePerSecond();
		this.t = new TreeSet(new TaskSolverComparator());
		this.setNrOfTaskSolvers(nrOfTaskSolvers);
		this.minServ = minServ;
		this.maxServ = maxServ;
		this.minGenIntv = minGenIntv;
		this.maxGenIntv = maxGenIntv;
		this.nrOfTasks = 0;
		this.simulationTime = simulationTime;
		for(int i=1; i<=this.getNrOfTaskSolvers(); i++) {
			getT().add(new TaskSolver(i));
			this.Solvers[i-1] = new TaskSolver(i);
			this.Solvers[i-1].start();
		}
		this.rez = new String[2];
	}
	
	@Override
	public void run() {
		float timePassed = 0.0f;
		
		while(timePassed < this.simulationTime) {
			
			
			this.waitTime = randomTaskInterval(this.minGenIntv, this.maxGenIntv); // generarea delay-ul pt aparitia unui alt task
			timePassed += this.waitTime/1000.0f;
			
			try {
				sleep(this.waitTime);
			} catch (Exception e) {
				System.out.println("exceptie in Simulator, run");
				System.out.println(e.getClass() + e.getMessage());
			}
			TaskSolver dummy;
			int minIdx = this.minIndex();
			dummy = this.getSolvers()[minIdx];
			dummy.addTask(TaskSolver.randomTaskGen(this.minServ, this.maxServ)); // generare task cu un timp de asteptare random
			this.peakTime(timePassed); // genereaza peak time moment
			this.updateLog();
		}
		for(int i=0; i< this.getNrOfTaskSolvers(); i++) {
			finalStats += this.getSolvers()[i].solvedTasks(); //Statisticile de la finalul simularii
			//System.out.println(finalStats);
		}
		finalStats = "<html>"+ finalStats + "</html>";
		//rez[0] = s;
		
		this.stopThreads();
	}
	
	public void simulate() { // simularea
		
		rez[0]=""; rez[1]="";
		this.start();
		
	}
	
	private synchronized void updateLog() {
		realTimeLog += TaskSolver.logThings; // Afisajul pt Log-ul principal
		staticPeakTime = ("Peak time: " + this.peakValues); //Statistica de peak time
		TaskSolver.logThings = ""; // init the thing "in a static way"
	}
	
	public synchronized int minIndex() {
		
		int minWait = this.getSolvers()[0].getWaitingTime();
		int minIdx = 0;
		for(int i=0; i< this.getNrOfTaskSolvers(); i++) {
			if(minWait > this.getSolvers()[i].getWaitingTime()) {
				minIdx = i;
			}
		}
		return minIdx;
	}
	
	
	
	public synchronized static int randomTaskInterval(float min, float max) {//Genereaza delay-ul de generare al taskurilor	
		min *= 1000;
		max *= 1000;
		int range = (int) Math.abs(max - min);
		return (int)(Math.random() * range + min); //https://www.geeksforgeeks.org/java-math-random-method-examples/
	}
	
//	@SuppressWarnings("deprecation")
	public synchronized void stopThreads() {
		try {
			for(int i=0; i< this.getNrOfTasks(); i++) {
				//this.stop();
				this.getSolvers()[i].setTimeToStop(false);
			}
		}catch(Exception e) {
			//System.out.println("Exceptie la oprirea threadurilor");
		}
	}
	
	public synchronized void resetThreads() {
		try {
			for(int i=0; i< this.getNrOfTasks(); i++) {
				this.getSolvers()[i].setTimeToStop(true);;
			}
		}catch(Exception e) {
			System.out.println("Exceptie la reinitializarea threadurilor");
		}
	}
	
	
	public synchronized void peakTime(float current) {//peak time
		float actual = this.avgWaitingTime();
		if(actual > this.peakValues.getValue()) {
			this.peakValues.setValue(actual);
			this.peakValues.setTimeMoment(current);
		}
	}
	
	public synchronized float avgWaitingTime() {// avg waiting time
		int total = 0;
		for(int i=0; i< this.nrOfTaskSolvers; i++){
			total += this.getSolvers()[i].avgTimePerTask();
		}
		if(this.getNrOfTaskSolvers() != 0) {
			return total/this.getNrOfTaskSolvers()/1000.0f;			
		}else {
			return 0;
		}
	}


	public synchronized TreeSet<TaskSolver> getT() {
		return t;
	}


	public synchronized void setT(TreeSet<TaskSolver> t) {
		this.t = t;
	}


	public synchronized float getNrOfTasks() {
		nrOfTasks = 0;
		for(int i=0; i< this.getNrOfTaskSolvers(); i++){
			nrOfTasks += this.getSolvers()[i].getNrTasksSolved();
		}
		return nrOfTasks;
	}


	public void setNrOfTasks(float nrOfTasks) {
		this.nrOfTasks = nrOfTasks;
	}

	public synchronized TaskSolver[] getSolvers() {
		return Solvers;
	}

	public void setSolvers(TaskSolver[] solvers) {
		Solvers = solvers;
	}

	public synchronized int getNrOfTaskSolvers() {
		return nrOfTaskSolvers;
	}

	public synchronized void setNrOfTaskSolvers(int nrOfTaskSolvers) {
		this.nrOfTaskSolvers = nrOfTaskSolvers;
	}


	
	
	
	
}
