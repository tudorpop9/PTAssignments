package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.BillDAO;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.OrderTable;
import model.Product;

public class CreateBillWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField orderId;
	private JButton submit;
	private JLabel billLabel;
	
	public CreateBillWin() {
		super("Generate bill for an order");
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.setSize(500,100);
		
		billLabel = new JLabel();
		billLabel.setText("Order's id: ");
		orderId = new JTextField(20);
		submit = new JButton("Submit");
		submit.setActionCommand("generate");
		
		this.add(billLabel);
		this.add(orderId);
		this.add(submit);
		
		doStuff();
	}
	
	public void doStuff() {	
		this.submit.addActionListener(new FindCListener());
	}
	
	//Listener
	class FindCListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		if(toDo.equals("generate")) {
			//Call db and try to Find
			try {
				
				int billValue = 0;
				ClientDAO cd = new ClientDAO();
				ProductDAO pd = new ProductDAO();
				OrderDAO od = new OrderDAO();
				BillDAO bd = new BillDAO();
				
				OrderTable order = od.findObject(Integer.parseInt(orderId.getText()));
				Client client = cd.findObject(order.getClientId());
				Product product = pd.findObject(order.getProductId());
				billValue = order.getOrderedQuant() * product.getProductPrice();
				
				ArrayList<String> values = new ArrayList<String>();
				values.add(String.valueOf(order.getOrdertableId()));
				values.add(product.getProductName());
				values.add(client.getClientName());
				values.add(client.getClientEmail());
				values.add(String.valueOf(order.getOrderedQuant()));
				values.add(String.valueOf(billValue));
				
				bd.insertObject(values);
				
				FileWriter wr = new FileWriter("LastEmittedBill.txt");
				wr.write(bd.findObject(order.getOrdertableId()).toString() );
				wr.flush();
				wr.close();
				
			}catch(Exception billexc) {
				new ErrorFram("Eroare la generare, verificati campurile!");
			}
			
			
			setVisible(false);
		}
	
	}
	}
}
