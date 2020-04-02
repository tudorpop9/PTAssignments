package presentationLayer;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.WriterToFile;

//@SuppressWarnings("unused")
public class WaiterGUI extends JFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217197972780267989L;
	
	private IRestaurantProcessing forRest;
	private Order currentOrder;
	private WriterToFile wr;
	private ArrayList<MenuItem> content;
	private JButton addOrder;
	private JButton viewOrders;
	private JButton bill;
	private JTextField forBill;
	private JPanel cont;
	
	private JButton addItem;
	private JButton submitOrder;
	private JTextField table;
	private JTextField itemName;
	private JLabel tableLab;
	private JLabel itemLab;
	private JPanel newOrderPanel;
	
	private String[] columnNames = { "Name", "Content"};

	private JScrollPane ordScroll;
	private JTable ordTable; 

	public WaiterGUI(Restaurant Rest) {
		super("WaiterWindow");
		this.forRest = Rest;
		this.wr = new WriterToFile("Bill.txt");
		this.currentOrder = null;
		this.content = null;
		
		this.addOrder = new JButton("NewOrder");
		this.addOrder.setActionCommand("add");
		this.viewOrders = new JButton("ViewOrders");
		this.viewOrders.setActionCommand("view");
		this.bill = new JButton("Bill");
		this.bill.setActionCommand("bill");
		this.forBill = new JTextField(10);
		
		this.addItem = new JButton("Add Item");
		this.addItem.setActionCommand("AddItem");
		this.submitOrder = new JButton("Submit Order");
		this.submitOrder.setActionCommand("submitOrder");
		this.tableLab = new JLabel();
		this.tableLab.setText("Table: ");
		this.itemLab = new JLabel();
		this.itemLab.setText("Item: ");
		this.table = new JTextField(15);
		this.itemName = new JTextField(15);
		
		this.newOrderPanel = new JPanel();
		this.newOrderPanel.setLayout(new FlowLayout());
		this.newOrderPanel.add(tableLab);
		this.newOrderPanel.add(table);
		this.newOrderPanel.add(itemLab);
		this.newOrderPanel.add(itemName);
		this.newOrderPanel.add(addItem);
		this.newOrderPanel.add(submitOrder);
		
		
		this.cont = new JPanel();
		this.cont.setLayout(new FlowLayout());
		this.cont.add(addOrder);
		this.cont.add(viewOrders);
		this.cont.add(bill);
		this.cont.add(forBill);
		
		this.ordTable = new JTable(this.forRest.avaibleOrders(),columnNames);
		this.ordScroll = new JScrollPane(ordTable);
		
		this.setLayout(new GridLayout(3, 1));
		this.add(cont);
		this.add(newOrderPanel);
		this.add(ordScroll);
		
		this.setVisible(true);
		this.setSize(750,300);
		this.doStuff();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void prepareOrder(int table) {
		String date = LocalDate.now().toString();
		this.currentOrder = new Order(date, table);
		this.content = new ArrayList<MenuItem>();
	}
	
	private void addToContent(String name) {
		MenuItem itm = this.forRest.findByName(name);
		if(itm != null) {
			this.content.add(itm);
		}else {
			System.out.println("MenuItem: "+name+" does not exist");
		}
	}
	
	private void addTheOrder() {
		try {
			this.forRest.addOrder(currentOrder, content);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " in addTheOrder(Waiter)");
			e.printStackTrace();
		}
	}
	
	private void generateBill(int orderId) {
		/*int price = this.forRest.computeOrderPrice(orderId);
		Order order = this.forRest.getOrderById(orderId);
		String orderText = order.toString();
		//System.out.println(order);*/
		try {
			int price = this.forRest.computeOrderPrice(orderId);
			Order order = this.forRest.getOrderById(orderId);
			String orderText = order.toString();
			wr.writeBill(orderText + ", Total price: " + price);
			this.forRest.deleteOrder(order);
		} catch (Exception e) {
			System.out.println("Exceptie in generateBill, waiter");
			//e.printStackTrace();
		}

	}
	
	public void doStuff() {	
		this.addOrder.addActionListener(new WaiterListener());
		this.viewOrders.addActionListener(new WaiterListener());
		this.bill.addActionListener(new WaiterListener());
		this.addItem.addActionListener(new WaiterListener());
		this.submitOrder.addActionListener(new WaiterListener());
	}
	
	class WaiterListener implements ActionListener, Serializable{
			/**
		 * 
		 */
		private static final long serialVersionUID = 2419373572862454478L;

			public void actionPerformed(ActionEvent e) {
			String toDo = e.getActionCommand();
			System.out.println(toDo);
		
			if(toDo.equals("add")) {
				int i= 0;
				try {
					i = Integer.parseInt(table.getText());					
				}catch(NumberFormatException e1) {
					System.out.println("NAN");
				}
				if(i>0) {
					prepareOrder(i);
				}else {
					System.out.println("Table number must be > 0");
				}
			}else if(toDo.equals("view")) {
				//ordTable = new JTable(forRest.avaibleOrders(), columnNames);
				//System.out.println("updated");
				DefaultTableModel model = new DefaultTableModel(forRest.avaibleOrders(),columnNames);
				ordTable.setModel(model);
				model.fireTableDataChanged();
				
			}else if(toDo.equals("bill")) {
				int i= 0;
				try {
					i = Integer.parseInt(forBill.getText());					
				}catch(NumberFormatException e1) {
					System.out.println("NAN");
				}
				if(i>0) {
					generateBill(i);
				}else {
					System.out.println("Order id must be > 0");
				}
			}else if(toDo.equals("AddItem")) {
				addToContent(itemName.getText());
			}else if(toDo.equals("submitOrder")) {
				addTheOrder();
			}
			
			
			
		}
	}
	
	

}
