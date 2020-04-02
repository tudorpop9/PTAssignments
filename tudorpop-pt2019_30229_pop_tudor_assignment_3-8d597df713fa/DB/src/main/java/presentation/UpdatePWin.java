package presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDAO;


public class UpdatePWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField rowId;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField quant;
	
	private JButton submit1;
	private JButton submit2;
	private JButton submit3;
	private JButton submit4;
	private JButton done;
	
	private JPanel pan0;
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel subp;
	
	private JLabel lRowId;
	private JLabel lid;
	private JLabel lname;
	private JLabel lprice;
	private JLabel lquant;
	
	public UpdatePWin() throws HeadlessException {
		super("Insert Client");
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));
		this.setSize(400, 400);
		
		this.rowId = new JTextField(20);
		this.id = new JTextField(20);
		this.name = new JTextField(20);
		this.price = new JTextField(20);
		this.quant = new JTextField(20);
		
		this.lRowId = new JLabel();
		this.lid = new JLabel();
		this.lname = new JLabel();
		this.lprice = new JLabel();
		this.lquant = new JLabel();
		
		this.lRowId.setText("Row's id to be updated");
		this.lid.setText("Product ID: ");
		this.lname.setText("Product Name: ");
		this.lprice.setText("Product Price: ");
		this.lquant.setText("Product Quant: ");
		
		this.done = new JButton("Done");
		this.done.setActionCommand("done");
		
		this.submit1 = new JButton("Submit");
		this.submit1.setActionCommand("id");
		this.submit2 = new JButton("Submit");
		this.submit2.setActionCommand("name");
		this.submit3 = new JButton("Submit");
		this.submit3.setActionCommand("price");
		this.submit4 = new JButton("Submit");
		this.submit4.setActionCommand("quant");
		
		this.pan0 = new JPanel();
		this.pan0.setLayout(new FlowLayout());
		this.pan0.add(lRowId);
		this.pan0.add(rowId);
		
		this.pan1 = new JPanel();
		this.pan1.setLayout(new FlowLayout());
		this.pan1.add(lid);
		this.pan1.add(id);
		this.pan1.add(this.submit1);
		
		this.pan2 = new JPanel();
		this.pan2.setLayout(new FlowLayout());
		this.pan2.add(lname);
		this.pan2.add(name);
		this.pan2.add(this.submit2);
		
		this.pan3 = new JPanel();
		this.pan3.setLayout(new FlowLayout());
		this.pan3.add(lprice);
		this.pan3.add(price);
		this.pan3.add(this.submit3);
		
		this.pan4 = new JPanel();
		this.pan4.setLayout(new FlowLayout());
		this.pan4.add(lquant);
		this.pan4.add(quant);
		this.pan4.add(this.submit4);
		
		this.subp = new JPanel();
		this.subp.setLayout(new FlowLayout());
		this.subp.add(done);
		
		this.add(this.pan0);
		this.add(this.pan1);
		this.add(this.pan2);
		this.add(this.pan3);
		this.add(this.pan4);
		this.add(this.subp);
		
		this.doStuff();
	}
	public void doStuff() {	
		this.submit1.addActionListener(new updatePListener());
		this.submit2.addActionListener(new updatePListener());
		this.submit3.addActionListener(new updatePListener());
		this.submit4.addActionListener(new updatePListener());
		this.done.addActionListener(new updatePListener());
	}
	
	//Listener
	class updatePListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		ProductDAO cd = new ProductDAO();
		
		if(toDo.equals("done")) {
			//Call db and try to do stuff
			
			setVisible(false);
		}else if (toDo.equals("id")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "productId", id.getText());
			
		}else if (toDo.equals("name")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "productName", name.getText());
			
		}else if (toDo.equals("price")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "productPrice", price.getText());
			
		}else if (toDo.equals("quant")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "productQuant", quant.getText());
			
		}
		}
	}
	
	
	
}
