package guiPackage;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import simulator.Simulator;

public class QueueWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer time;
	private Simulator sim;
	private int nrLabels;
	private ArrayList<JLabel> arr;
	
	public QueueWindow(int nrLabels, Simulator sim) {
		super("Real time Queues");
		this.setVisible(true);
		
		this.arr = new ArrayList(nrLabels);
		this.time = new Timer();
		
		this.nrLabels = nrLabels;
		this.sim = sim;
		this.setLayout(new GridLayout(nrLabels, 1));
		
		for(int i=0; i < this.nrLabels; i++) {
			arr.add(new JLabel());
			this.add(arr.get(i));
		}
		
		time.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				updateLabels();
			}
		}, 100, 100);
		
		
		this.setDefaultCloseOperation(SetupWindow.EXIT_ON_CLOSE);
		this.setSize(600, 600); //  'reglabil'
	}
	
	private synchronized void  updateLabels() {

		try {
		for(int i=0; i< sim.getNrOfTaskSolvers(); i++) {
			this.arr.get(i).setText("Queue TaskSolver "+ sim.getSolvers()[i].getSolverNumber()+":" + sim.getSolvers()[i].toString());
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getClass());
			System.out.println("Exceptie in queuewindow");
		}
	}
	
	
	
}
