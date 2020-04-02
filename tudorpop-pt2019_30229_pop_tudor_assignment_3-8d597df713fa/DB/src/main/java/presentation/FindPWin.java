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

import dao.ProductDAO;
import model.Product;


@SuppressWarnings("serial")
public class FindPWin extends JFrame {

	private JLabel dLabel;
	private TextField id;
	private Button submitButton;
	private Button CloseButton;
	private TextArea rez;
	private JPanel pan;
	
	public FindPWin() {
		super("FindProduct");
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
		this.submitButton.addActionListener(new FindPListener());
	}
	
	//Listener
	class FindPListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("FindPls")) {
			try {
				ProductDAO pd = new ProductDAO();
				Product p = pd.findObject(Integer.parseInt(id.getText()));
				rez.setText(p.toString());				
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
