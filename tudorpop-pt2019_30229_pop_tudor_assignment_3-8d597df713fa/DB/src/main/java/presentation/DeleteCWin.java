package presentation;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.ClientDAO;


@SuppressWarnings("serial")
public class DeleteCWin extends JFrame {

	private JLabel dLabel;
	private TextField id;
	private Button submitButton;
	
	public DeleteCWin() {
		super("DeleteClient");
		this.setVisible(true);
		this.setSize(500, 70);
		
		this.setLayout(new GridLayout(1,3));
		
		this.dLabel = new JLabel();
		this.dLabel.setText("      Enter Id");
		
		this.id = new TextField(30);
		this.submitButton = new Button("Submit");
		this.submitButton.setActionCommand("DeletePls");
		
		this.add(dLabel);
		this.add(id);
		this.add(submitButton);
		
		this.doStuff();
	}
	public void doStuff() {	
		this.submitButton.addActionListener(new DeleteCListener());
	}
	
	//Listener
	class DeleteCListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("DeletePls")) {
			//Call db and try to delete
			ClientDAO cd = new ClientDAO();
			try {
				cd.deleteObject(Integer.parseInt(id.getText()));				
			}catch(Exception deletefail) {
				new ErrorFram("Failed to delete row");
			}
			setVisible(false);
		}
	
		}
	}
	
}
