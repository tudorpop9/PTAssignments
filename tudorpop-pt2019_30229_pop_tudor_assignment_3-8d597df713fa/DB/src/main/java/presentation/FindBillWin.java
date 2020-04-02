package presentation;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.BillDAO;
import model.Bill;


@SuppressWarnings("serial")
public class FindBillWin extends JFrame {

	private JLabel dLabel;
	private TextField id;
	private Button submitButton;
	private Button CloseButton;
	private TextArea rez;
	private JPanel pan;
	
	public FindBillWin() {
		super("FindBill");
		this.setVisible(true);
		this.setSize(500,100);
		
		this.setLayout(new GridLayout(2,1));
		
		this.dLabel = new JLabel();
		this.dLabel.setText("      Enter Id");
		
		this.id = new TextField(30);
		this.submitButton = new Button("Submit");
		this.submitButton.setActionCommand("FindPls");
		
		this.rez = new TextArea();
		this.rez.setEditable(false);
		this.CloseButton = new Button("Close window");
		this.CloseButton.setActionCommand("closePls");
		
		pan = new JPanel();
		pan.setLayout(new FlowLayout());
		
		this.pan.add(dLabel);
		this.pan.add(id);
		this.pan.add(submitButton);
		
		this.add(pan);
		this.add(rez);		
		
		this.doStuff();
	}
	public void doStuff() {	
		this.submitButton.addActionListener(new FindBListener());
	}
	
	//Listener
	class FindBListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("FindPls")) {
			try {
				BillDAO bd = new BillDAO();
				Bill b = bd.findObject(Integer.parseInt(id.getText()));
				rez.setText(b.toString());				
			}catch(Exception notFound) {
				new ErrorFram("Not found");
				rez.setText("");
			}
			
		}else if(toDo.equals("closePls")) {
			setVisible(false);
		}
	
		}
	}
	
}
