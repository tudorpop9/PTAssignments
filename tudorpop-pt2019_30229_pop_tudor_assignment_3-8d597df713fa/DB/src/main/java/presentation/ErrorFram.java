package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ErrorFram extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel msg;
	private JButton ok;
	public ErrorFram(String string) {
		super("Error");
		this.setVisible(true);
		this.setSize(300, 80);
		msg = new JLabel();
		msg.setText(string);
		
		ok = new JButton("Ok");
		ok.setActionCommand("closePls");
		
		this.setLayout(new FlowLayout());
		this.add(msg);
		this.add(ok);
		
		doStuff();
	}
	
	public void doStuff() {	
		this.ok.addActionListener(new ErrorListener());
	}
	
	//Listener
	class ErrorListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		
		if(toDo.equals("closePls")){
			setVisible(false);
		}
	
		}
	}

}
