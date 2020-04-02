package guiPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import core.Polinom;

public class AppWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//generat automat...
	
	private JLabel LabelPolA;
	private JLabel LabelPolB;
	private JLabel LabelOp;
	
	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public TextField getPolA() {
		return polA;
	}

	public void setPolA(TextField polA) {
		this.polA = polA;
	}

	public TextField getPolARez() {
		return polARez;
	}

	public void setPolARez(TextField polARez) {
		this.polARez = polARez;
	}

	public TextField getPolB() {
		return polB;
	}

	public void setPolB(TextField polB) {
		this.polB = polB;
	}

	public TextField getPolBRez() {
		return polBRez;
	}

	public void setPolBRez(TextField polBRez) {
		this.polBRez = polBRez;
	}

	public TextField getRezOperatii() {
		return rezOperatii;
	}

	public void setRezOperatii(TextField rezOperatii) {
		this.rezOperatii = rezOperatii;
	}

	//Panourile principale
	private JLabel statusLabel;
	private JPanel firstPolPanel;
	private JPanel secondPolPanel;
	private JPanel operationsPanel;
	private JPanel resultPanel;
	
	//Componentele pt primul polinom
	private TextField polA;
	private TextField polARez;
	private Button derivA;
	private Button integrA;
	
	//Componentele pt al doilea polinom
	private TextField polB;
	private TextField polBRez;
	private Button derivB;
	private Button integrB;
	
	//Compenente pt operatii
	private Button adunare;
	private Button scadere;
	private Button inmultire;
	private Button impartire;
	
	private TextField rezOperatii;
	
	//Constructor
	public AppWindow() {
		super("AplicatieTema1");
		//cuz' why make it default
		this.setVisible(true);
		this.setLayout(new GridLayout(5,1));
		
		//Initialiarea primului panou (Polinom A)
		this.firstPolPanel = new JPanel();
		this.firstPolPanel.setLayout(new FlowLayout());
		this.polA = new TextField(30);
		//polA.setBounds(x, y, width, height);
		this.polARez = new TextField(30);
		this.polARez.setEditable(false);
		//polARez.setBounds(x, y, width, height);
		this.derivA = new Button("Deriveaza");
		this.derivA.setActionCommand("derivA");
		
		this.integrA = new Button("Integreaza");
		this.integrA.setActionCommand("integrA");
		
		this.LabelPolA = new JLabel("Polinom A: ");
		this.firstPolPanel.add(LabelPolA);
		this.firstPolPanel.add(polA);
		this.firstPolPanel.add(derivA);
		this.firstPolPanel.add(integrA);
		this.firstPolPanel.add(polARez);
		
		//Initialiarea celui de-al doilea panou (Polinom B)
		this.secondPolPanel = new JPanel();
		this.secondPolPanel.setLayout(new FlowLayout());
		this.polB = new TextField(30);
		//polB.setBounds(x, y, width, height);
		this.polBRez = new TextField(30);
		this.polBRez.setEditable(false);
		//polBRez.setBounds(x, y, width, height);
		this.derivB = new Button("Deriveaza");
		this.derivB.setActionCommand("derivB");
		
		this.integrB = new Button("Integreaza");
		this.integrB.setActionCommand("integrB");
		
		this.LabelPolB = new JLabel("Polinom B: ");
		this.secondPolPanel.add(LabelPolB);
		this.secondPolPanel.add(polB);
		this.secondPolPanel.add(derivB);
		this.secondPolPanel.add(integrB);
		this.secondPolPanel.add(polBRez);
		
		//initializarea panoului cu operatii
		this.operationsPanel = new JPanel();
		this.operationsPanel.setLayout(new FlowLayout()); // hmm...
		
		this.adunare = new Button("Adunare");
		this.adunare.setActionCommand("add");
		
		this.scadere = new Button("Scadere");
		this.scadere.setActionCommand("sub");
		
		this.inmultire = new Button("Inmultire");
		this.inmultire.setActionCommand("mul");
		
		this.impartire = new Button("Impartire");
		this.impartire.setActionCommand("div");
		
		this.operationsPanel.add(adunare);
		this.operationsPanel.add(scadere);
		this.operationsPanel.add(inmultire);
		this.operationsPanel.add(impartire);
		
		//Rezultatul operatiilor
		this.resultPanel = new JPanel();
		this.rezOperatii = new TextField(60);
		this.rezOperatii.setEditable(false);
		this.LabelOp = new JLabel("A op B = ");
		this.resultPanel.add(LabelOp);
		this.resultPanel.add(rezOperatii);
		
		//Status label
		statusLabel = new JLabel();
		
		this.add(statusLabel);
		this.add(firstPolPanel);
		this.add(secondPolPanel);
		this.add(operationsPanel);
		this.add(resultPanel);
		
		//cuz' why make it default
		this.setDefaultCloseOperation(AppWindow.EXIT_ON_CLOSE);
		this.setSize(800, 400);
	}
	
	public void perform() {
		
		this.derivA.addActionListener(new ProjectListener());
		this.integrA.addActionListener(new ProjectListener());
		
		this.derivB.addActionListener(new ProjectListener());
		this.integrB.addActionListener(new ProjectListener());
		
		this.adunare.addActionListener(new ProjectListener());
		this.scadere.addActionListener(new ProjectListener());
		this.inmultire.addActionListener(new ProjectListener());
		this.impartire.addActionListener(new ProjectListener());
	}
	
	//Listener
	private class ProjectListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String toDo = e.getActionCommand();
		try {
			Polinom A =new Polinom(polA.getText());
			Polinom B =new Polinom(polB.getText());
				if(toDo.equals("derivA")) {
					
					polARez.setText((Polinom.derivP(A)).toString());
					
				}else if(toDo.equals("integrA")) {
					
					polARez.setText((Polinom.integrP(A)).toString());
					
				}else if(toDo.equals("derivB")) {
					
					polBRez.setText((Polinom.derivP(B)).toString());
					
				}else if(toDo.equals("integrB")) {
					
					polBRez.setText((Polinom.integrP(B)).toString());
					
				}else if(toDo.equals("add")) {
					
					rezOperatii.setText((Polinom.addP(A, B)).toString());
					
				}else if(toDo.equals("sub")) {

					rezOperatii.setText((Polinom.supP(A, B)).toString());
					
				}else if(toDo.equals("mul")) {
					
					rezOperatii.setText((Polinom.mulP(A, B)).toString());
					
				}else if(toDo.equals("div")) {
					
					ArrayList<Polinom> rezult = Polinom.divP(A, B);
					rezOperatii.setText("Q: " + rezult.get(0) + ", R: " + rezult.get(1) );
				}
				statusLabel.setText("");
				
			}catch(Exception exception) {
				statusLabel.setText("     "+exception.getClass().getSimpleName()+" "+exception.getMessage());
			}

		}

	}
}
