package presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ClientDAO;


public class InsertCWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField name;
	private JTextField email;
	private JTextField adr;
	
	private JButton submit;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel subp;
	
	private JLabel lid;
	private JLabel lname;
	private JLabel lemail;
	private JLabel ladr;
	
	public InsertCWin() throws HeadlessException {
		super("Insert Client");
		this.setVisible(true);
		this.setLayout(new GridLayout(5,1));
		this.setSize(350, 400);
		
		this.id = new JTextField(20);
		this.name = new JTextField(20);
		this.email = new JTextField(20);
		this.adr = new JTextField(20);
		
		this.lid = new JLabel();
		this.lname = new JLabel();
		this.lemail = new JLabel();
		this.ladr = new JLabel();
		
		this.lid.setText("Client ID: ");
		this.lname.setText("Client Name: ");
		this.lemail.setText("Client Email: ");
		this.ladr.setText("Client Adr: ");
		
		
		this.submit = new JButton("Submit");
		this.submit.setActionCommand("add");
		
		this.pan1 = new JPanel();
		this.pan1.setLayout(new FlowLayout());
		this.pan1.add(lid);
		this.pan1.add(id);
		
		this.pan2 = new JPanel();
		this.pan2.setLayout(new FlowLayout());
		this.pan2.add(lname);
		this.pan2.add(name);
		
		this.pan3 = new JPanel();
		this.pan3.setLayout(new FlowLayout());
		this.pan3.add(lemail);
		this.pan3.add(email);
		
		this.pan4 = new JPanel();
		this.pan4.setLayout(new FlowLayout());
		this.pan4.add(ladr);
		this.pan4.add(adr);
		
		this.subp = new JPanel();
		this.subp.setLayout(new FlowLayout());
		this.subp.add(submit);
		
		this.add(this.pan1);
		this.add(this.pan2);
		this.add(this.pan3);
		this.add(this.pan4);
		this.add(this.subp);
		
		this.doStuff();
	}
	public void doStuff() {	
		this.submit.addActionListener(new addCListener());
	}
	
	//Listener
	class addCListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		
		if(toDo.equals("add")) {
			//Call db and try to do stuff
			ClientDAO cd = new ClientDAO();
			ArrayList<String> values = new ArrayList<String>();
			
			values.add(id.getText());
			values.add(name.getText());
			values.add(email.getText());
			values.add(adr.getText());
			
			cd.insertObject(values);
			setVisible(false);
		}
	
		}
	}
	
	
	
}
