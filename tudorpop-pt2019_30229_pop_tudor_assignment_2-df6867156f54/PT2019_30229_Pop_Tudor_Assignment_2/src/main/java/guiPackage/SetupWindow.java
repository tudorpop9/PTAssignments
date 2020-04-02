package guiPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SetupWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//generat automat...
	@SuppressWarnings("unused")
	private boolean nextWindow;
	
	private JLabel labelIntervalGen;
	private JLabel labelProcessInterval;
	private JLabel labelSolverSimTime;
	private JLabel statusLabel;
	
	

	//Panourile principale
	private JPanel intervalGenPanel;
	private JPanel processIntervalPanel;
	private JPanel solverSimTimePanel;
	private JPanel submitPanel;
	
	//Interval generare taskuri
	private TextField genIntMinTF;
	private TextField genIntMaxTF;
	
	//Interval timp de procesare taskuri
	private TextField genPrcTimeMinTF;
	private TextField genPrcTimeMaxTF;
	
	//Number of TaskSolvers and simulation time
	private TextField threadNumberTF;
	private TextField simulationTimeTF;
	
	private Button submitButton;
	
	
	
	//Constructor
	public SetupWindow() {
		super("AplicatieTema2");
		this.nextWindow = false;
		//genious design
		this.setVisible(true);
		this.setLayout(new GridLayout(8,1));
		
		this.statusLabel = new JLabel();
		this.labelIntervalGen = new JLabel("          Interval de timp pentru generarea random a task-urilor (secunde)");
		this.labelProcessInterval = new JLabel("          Interval de timp pentru generarea random a timpului de procesare (secunde)");
		this.labelSolverSimTime = new JLabel("          Introduceti numarul de threaduri si timpul de simulare ");
		
		//initialiare panoiuri
		this.intervalGenPanel = new JPanel();
		this.intervalGenPanel.setLayout(new FlowLayout());
		this.processIntervalPanel = new JPanel();
		this.processIntervalPanel.setLayout(new FlowLayout());
		this.solverSimTimePanel = new JPanel();
		this.solverSimTimePanel.setLayout(new FlowLayout());
		this.submitPanel = new JPanel();
		this.submitPanel.setLayout(new FlowLayout());
		
		this.genIntMinTF = new TextField(10);//Crearea/initializarea
		this.genIntMaxTF = new TextField(10);
		this.intervalGenPanel.add(new JLabel("Minim: "));
		this.intervalGenPanel.add(this.genIntMinTF);//Adaugare in panoul corespunzator 
		this.intervalGenPanel.add(new JLabel("      Maxim: "));
		this.intervalGenPanel.add(this.genIntMaxTF);
		
		this.genPrcTimeMinTF = new TextField(10);//Crearea/initializarea
		this.genPrcTimeMaxTF = new TextField(10);
		this.processIntervalPanel.add(new JLabel("Minim: "));
		this.processIntervalPanel.add(this.genPrcTimeMinTF);//Adaugare in panoul corespunzator 
		this.processIntervalPanel.add(new JLabel("      Maxim: "));
		this.processIntervalPanel.add(this.genPrcTimeMaxTF);
		
		
		this.threadNumberTF = new TextField(10);//Crearea/initializarea
		this.simulationTimeTF = new TextField(10);
		this.solverSimTimePanel.add(new JLabel("Numar thread-uri(2-8): "));
		this.solverSimTimePanel.add(this.threadNumberTF);//Adaugare in panoul corespunzator 
		this.solverSimTimePanel.add(new JLabel("      Timp simulare(sec): "));
		this.solverSimTimePanel.add(this.simulationTimeTF);
		
		this.submitButton = new Button("Submit"); //Crearea/initializarea
		this.submitPanel.add(this.submitButton); //Adaugare in panoul corespunzator
		this.submitButton.setActionCommand("CheckFields");
		
		this.add(this.labelIntervalGen);
		this.add(this.intervalGenPanel);
		this.add(this.labelProcessInterval);
		this.add(this.processIntervalPanel);
		this.add(this.labelSolverSimTime);
		this.add(this.solverSimTimePanel);
		this.add(this.statusLabel);
		this.add(this.submitPanel);
		
		
		//genious design
		this.setDefaultCloseOperation(SetupWindow.EXIT_ON_CLOSE);
		this.setSize(600, 400);
	}
	
	public void perform() {
		this.submitButton.addActionListener(new ProjectListener());
	}
	
	
	//Listener
	private class ProjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("CheckFields")){
				
				float minGen, maxGen, minLim, maxLim, simTime;
				int nrThrd;	
				minLim = Float.parseFloat(genIntMinTF.getText());
				maxLim = Float.parseFloat(genIntMaxTF.getText());
				minGen = Float.parseFloat(genPrcTimeMinTF.getText());
				maxGen = Float.parseFloat(genPrcTimeMaxTF.getText());
				nrThrd = Integer.parseInt(threadNumberTF.getText());
				simTime = Float.parseFloat(simulationTimeTF.getText());
				
				if(minGen > maxGen || minLim > maxLim || nrThrd < 2 || nrThrd > 8 || minGen <= 0 || minLim <= 0 ) {
					throw new Exception("nope nope nope");
				}
				nextWindow = true;
				setVisible(false);
				statusLabel.setText("");
				
				LogWindow log = new LogWindow(minGen, maxGen, minLim, maxLim, nrThrd, simTime);
				log.doStuf();
			}
			
				
		}catch(Exception exception) {
			nextWindow = false;
			statusLabel.setText("                                  INVALID INPUT");
		}

		}

	}
}
