package guiPackage;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import simulator.Simulator;


public class LogWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float minGen, maxGen, minLim, maxLim, simTime;
	private int nrThrd;
	private JPanel contentPanel;
	private JTextArea logArea;
	private JScrollPane logScroll;
	
	
	private JLabel finalStats;
	private JLabel statLabel;
	private JPanel controlPanel;
	private JPanel generalStatPanel;
	private JPanel miniGenStatPanel;
	private Button againButton;
	private Button startButton;
	private Button stopButton;
	
	private TextField avgTimeTF;
	private TextField peakTimeTF;
	private TextField nrOfTasksTF;
	private Timer time;
	
	String logString ;
	String finalLogString;
	String peakTimeString ;
	
	private QueueWindow qWd;
	
	public LogWindow(float minGen, float maxGen, float minLim, float maxLim, int nrThrd, float simTime) throws HeadlessException {
		super();
		this.minGen = minGen;
		this.maxGen = maxGen;
		this.minLim = minLim;
		this.maxLim = maxLim;
		this.nrThrd = nrThrd;
		this.simTime = simTime;
		this.time = new Timer();
		this.logString = new String(""); 
		this.finalLogString = new String("");
		this.peakTimeString = new String(""); 
		
		this.setVisible(true);
		this.setLayout(new GridLayout(2, 1));//Layout
		
		//Elementele principale
		this.logArea = new JTextArea();
		this.logArea.setEditable(false);
		this.logScroll = new JScrollPane(logArea);
		
		
		this.contentPanel = new JPanel();
		this.contentPanel.setLayout(new GridLayout(3, 1));
		
		this.finalStats = new JLabel();
		this.statLabel = new JLabel();
		this.controlPanel = new JPanel(); // Control Panel
		this.controlPanel.setLayout(new FlowLayout());
		this.generalStatPanel = new JPanel(); // General statistic Panel
		this.generalStatPanel.setLayout(new FlowLayout());
		this.miniGenStatPanel = new JPanel(); // Mini general statistic panel
		this.miniGenStatPanel.setLayout(new GridLayout(3, 1));
		
		//Buttons
		this.againButton = new Button("Again");
		this.startButton = new Button("Start");
		this.stopButton = new Button("Stop");
		this.againButton.setActionCommand("restart");
		this.startButton.setActionCommand("startsim");
		this.stopButton.setActionCommand("stopsim");
		
		//TextFields
		this.avgTimeTF = new TextField(30);
		this.avgTimeTF.setEditable(false);
		this.peakTimeTF = new TextField(30);
		this.peakTimeTF.setEditable(false);
		this.nrOfTasksTF = new TextField(30);
		this.nrOfTasksTF.setEditable(false);
		
		//Element arrangement 
		this.miniGenStatPanel.add(avgTimeTF);//Mini panel
		this.miniGenStatPanel.add(nrOfTasksTF);
		this.miniGenStatPanel.add(peakTimeTF);
		
		this.generalStatPanel.add(this.finalStats);//General stat Panel
		this.generalStatPanel.add(this.miniGenStatPanel);
		
		this.controlPanel.add(this.startButton);
		this.controlPanel.add(this.stopButton);
		this.controlPanel.add(this.againButton);
		
		this.contentPanel.add(this.generalStatPanel);//Big content panel
		this.contentPanel.add(this.controlPanel);
		this.contentPanel.add(this.statLabel);
		
		this.add(logScroll);
		this.add(contentPanel);
		
		
		//genious design
		this.setDefaultCloseOperation(SetupWindow.EXIT_ON_CLOSE);
		this.setSize(600, 850); //  'reglabil'
		
	}
	
	// the part that does something
	public void doStuf() {
		this.startButton.addActionListener(new LogWListener());
		this.stopButton.addActionListener(new LogWListener());
		this.againButton.addActionListener(new LogWListener());
	}
	
	//Listener aka the part that actually does something
		private class LogWListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
			String toDo = e.getActionCommand();
			try {		
					if(toDo.equals("startsim")) {
						String[] retSim;
						if(qWd != null) { // pt repornire
							qWd.setVisible(false);							
						}
						
						final Simulator sim = new Simulator(nrThrd, minGen, maxGen, minLim, maxLim,simTime);//Simulatorul
						sim.simulate();
						
						qWd = new QueueWindow(nrThrd, sim); // out of memory error
						retSim = sim.rez;
						Simulator.realTimeLog = ""; // 'static way'
						logString = Simulator.realTimeLog;
						finalLogString = retSim[0];
						peakTimeString = retSim[1];
						
						time.scheduleAtFixedRate(new TimerTask() {
							
							@Override
							public void run() {
								
								logArea.setText(Simulator.realTimeLog); //real time action//////////////////////////////////////
								finalStats.setText(Simulator.finalStats); // some stats
								//System.out.println(finalLogString);
								peakTimeTF.setText(Simulator.staticPeakTime); // peak time stats
								avgTimeTF.setText("Timp mediu de asteptare: "+sim.avgWaitingTime()); // avg waiting time
								nrOfTasksTF.setText("Numarul de Task-uri procesate: " + sim.getNrOfTasks());
								
			
							}
							
							
						}, 100, 100);
						
						
						PrintWriter write = new PrintWriter("LogOutput.txt","UTF-8");//https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
						write.println(logString);
						write.close();
						
						
					}else if (toDo.equals("stopsim")) {
						//sim.stopThreads();
					}else if (toDo.equals("restart")) {
						setVisible(false);
						SetupWindow setWid = new SetupWindow();
						if(qWd != null) {
							qWd.setVisible(false);							
						}
						setWid.perform();
					}
					
					
				}catch(Exception exception) {
					statLabel.setText("     "+exception.getClass().getSimpleName()+" "+exception.getMessage());
				}

			}
		}
		
		public synchronized void updateLog(Simulator sim) {
			logArea.setText(logString); //real time action//////////////////////////////////////
			finalStats.setText(finalLogString); // some stats
			peakTimeTF.setText(peakTimeString); // peak time stats
			avgTimeTF.setText("Timp mediu de asteptare: "+sim.avgWaitingTime()); // avg waiting time
			nrOfTasksTF.setText("Numarul de Task-uri procesate: " + sim.getNrOfTasks());
		}
	
	
}
