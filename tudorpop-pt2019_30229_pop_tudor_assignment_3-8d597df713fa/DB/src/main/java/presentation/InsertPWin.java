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

import dao.ProductDAO;


public class InsertPWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField quant;
	
	private JButton submit;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel subp;
	
	private JLabel lid;
	private JLabel lname;
	private JLabel lprice;
	private JLabel lquant;
	
	public InsertPWin() throws HeadlessException {
		super("Insert Product");
		this.setVisible(true);
		this.setLayout(new GridLayout(5,1));
		this.setSize(350, 400);
		
		this.id = new JTextField(20);
		this.name = new JTextField(20);
		this.price = new JTextField(20);
		this.quant = new JTextField(20);
		
		this.lid = new JLabel();
		this.lname = new JLabel();
		this.lprice = new JLabel();
		this.lquant = new JLabel();
		
		this.lid.setText("Product ID: ");
		this.lname.setText("Product Name: ");
		this.lprice.setText("Product Price: ");
		this.lquant.setText("Product Quant: ");
		
		
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
		this.pan3.add(lprice);
		this.pan3.add(price);
		
		this.pan4 = new JPanel();
		this.pan4.setLayout(new FlowLayout());
		this.pan4.add(lquant);
		this.pan4.add(quant);
		
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
		this.submit.addActionListener(new addPListener());
	}
	
	//Listener
	class addPListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		
		if(toDo.equals("add")) {
			//Call db and try to do stuff
			ProductDAO pd = new ProductDAO();
			ArrayList<String> values = new ArrayList<String>();
			
			values.add(id.getText());
			values.add(name.getText());
			values.add(price.getText());
			values.add(quant.getText());
			
			pd.insertObject(values);
			setVisible(false);
		}
	
		}
	}
	
	
	
}
