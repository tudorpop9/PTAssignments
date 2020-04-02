package presentationLayer;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLayer.Order;

@SuppressWarnings("deprecation")
public class ChefGUI extends JFrame implements Observer, Serializable{
	
	private JTextField note = new JTextField(50);
	
	public ChefGUI() throws HeadlessException {
		super("ChefGUI");
		note.setEditable(false);
		this.setVisible(true);
		this.setSize(500, 100);
		this.setLayout(new FlowLayout());
		this.add(note);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7822979461495821894L;
	public void update(Observable o, Object arg) {
		Order ord = (Order)arg;
		note.setText("Notified by: "+ord.toString() +" from Restaurant");
		
	}

}
