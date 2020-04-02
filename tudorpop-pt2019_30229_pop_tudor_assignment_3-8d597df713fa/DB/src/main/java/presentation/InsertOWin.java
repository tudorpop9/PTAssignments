package presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.OrderDAO;
import dao.ProductDAO;


public class InsertOWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField orderid;
	private JTextField clientid;
	private JTextField clientadr;
	private JTextField productid;
	private JTextField quant;
	
	private JButton submit;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel pan5;
	private JPanel subp;
	
	private JLabel lid;
	private JLabel lcid;
	private JLabel lpid;
	private JLabel ladr;
	private JLabel lquant;
	
	public InsertOWin() throws Exception {
		super("Insert Product");
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));
		this.setSize(350, 400);
		
		this.orderid = new JTextField(20);
		this.clientid = new JTextField(20);
		this.clientadr = new JTextField(20);
		this.productid = new JTextField(20);
		this.quant = new JTextField(20);
		
		this.lid = new JLabel();
		this.lcid = new JLabel();
		this.lpid = new JLabel();
		this.ladr = new JLabel();
		this.lquant = new JLabel();
		
		this.lid.setText("Order ID: ");
		this.lcid.setText("Client ID: ");
		this.lpid.setText("Product ID: ");
		this.ladr.setText("Client Adr: ");
		this.lquant.setText("Product Quant: ");
		
		
		this.submit = new JButton("Submit");
		this.submit.setActionCommand("add");
		
		this.pan1 = new JPanel();
		this.pan1.setLayout(new FlowLayout());
		this.pan1.add(lid);
		this.pan1.add(orderid);
		
		this.pan2 = new JPanel();
		this.pan2.setLayout(new FlowLayout());
		this.pan2.add(lcid);
		this.pan2.add(clientid);
		
		this.pan3 = new JPanel();
		this.pan3.setLayout(new FlowLayout());
		this.pan3.add(ladr);
		this.pan3.add(clientadr);
		
		this.pan4 = new JPanel();
		this.pan4.setLayout(new FlowLayout());
		this.pan4.add(lpid);
		this.pan4.add(productid);
		
		this.pan5 = new JPanel();
		this.pan5.setLayout(new FlowLayout());
		this.pan5.add(lquant);
		this.pan5.add(quant);
		
		this.subp = new JPanel();
		this.subp.setLayout(new FlowLayout());
		this.subp.add(submit);
		
		this.add(this.pan1);
		this.add(this.pan2);
		this.add(this.pan3);
		this.add(this.pan4);
		this.add(this.pan5);
		this.add(this.subp);
		
		this.doStuff();
	}
	public void doStuff() throws Exception{	
		this.submit.addActionListener(new addOListener());
	}
	
	//Listener
	class addOListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		
		if(toDo.equals("add")) {
			//Call db and try to do stuff
			OrderDAO pd = new OrderDAO();
			ArrayList<String> values = new ArrayList<String>();
							
			int val = Integer.MIN_VALUE;
			val = pd.findQuantVal(Integer.parseInt(productid.getText()));
			
			if(val < Integer.parseInt(quant.getText())) {
				if(val == Integer.MIN_VALUE) {
					new ErrorFram("Statement Error");
				}else {
					new ErrorFram("Out of stock");					
				}
			}else {
				values.add(orderid.getText());
				values.add(clientid.getText());
				values.add(clientadr.getText());
				values.add(productid.getText());
				values.add(quant.getText());
				
				pd.insertObject(values);
				ProductDAO prd = new ProductDAO();
				
				int newQuant = val - Integer.parseInt(quant.getText()) ;
				prd.updateObject(Integer.parseInt(orderid.getText()), "productQuant", String.valueOf(newQuant) );
			}
			
			setVisible(false);
		}
	
		}
	}
	
	
	
}
