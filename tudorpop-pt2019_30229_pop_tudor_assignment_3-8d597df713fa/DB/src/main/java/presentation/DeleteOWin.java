package presentation;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.OrderDAO;


public class DeleteOWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dLabel;
	private TextField id;
	private Button submitButton;
	
	public DeleteOWin() {
		super("DeleteOrder");
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
		this.submitButton.addActionListener(new DeleteOListener());
	}
	
	//Listener
	class DeleteOListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("DeletePls")) {
			//Call db and try to delete
			OrderDAO cd = new OrderDAO();
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
