package presentation;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.ProductDAO;


public class DeletePWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dLabel;
	private TextField id;
	private Button submitButton;
	
	public DeletePWin() {
		super("DeleteProduct");
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
		this.submitButton.addActionListener(new DeletePListener());
	}
	
	//Listener
	class DeletePListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("DeletePls")) {
			//Call db and try to delete
			ProductDAO cd = new ProductDAO();
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
