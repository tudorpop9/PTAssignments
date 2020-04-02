package presentationLayer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class AdminGUI extends JFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1536388089112005145L;
	
	private IRestaurantProcessing forRest;
	private ArrayList<MenuItem> compositeList;
	
	private JButton createSimpleIt;
	private JButton prepCompIt;
	private JButton createCompIt;
	private JButton viewItems;
	private JButton deleteItem;
	private JButton updateItem;
	private JButton addComponentItem;
	private JButton saveButton;
	
	private JTextField forDelete = new JTextField(10);
	private JTextField updField = new JTextField(15);
	private JTextField updValue= new JTextField(15);
	private JTextField updName= new JTextField(15);
	private JTextField simpleName= new JTextField(15);
	private JTextField simplePrice= new JTextField(15);
	private JTextField compPart= new JTextField(15);
	private JTextField compName= new JTextField(15);
	
	private JLabel updItmTarget = new JLabel();
	private JLabel updLabelField = new JLabel();
	private JLabel updLabelValue = new JLabel();
	private JLabel labSimpleName = new JLabel();
	private JLabel labSimplePrice = new JLabel();
	private JLabel labCompName = new JLabel();
	private JLabel labPart = new JLabel();
	
	private JTable itemTable;
	
	private JPanel upperPanel = new JPanel();
	private JPanel addSimplePanel = new JPanel();
	private JPanel addCompPanel = new JPanel();
	private JPanel updatePanel = new JPanel();
	
	private String[] columnNames = {"Menu Items"};

	private JScrollPane ordScroll;
	
	public AdminGUI(Restaurant forRest) throws HeadlessException {
		super("AdminWindow");
		this.forRest = forRest;
		this.compositeList = null;
		
		this.createSimpleIt = new JButton("CreateSimple");
		this.createSimpleIt.setActionCommand("creatSimple");	
		this.createCompIt = new JButton("CreateComposite");
		this.createCompIt.setActionCommand("creatComp");
		this.prepCompIt = new JButton("SignalComp");
		this.prepCompIt.setActionCommand("signalComp");
		this.deleteItem = new JButton("Delete");
		this.deleteItem.setActionCommand("delete");
		this.updateItem = new JButton("Update");
		this.updateItem.setActionCommand("update");
		this.viewItems = new JButton("View");
		this.viewItems.setActionCommand("view");
		this.addComponentItem = new JButton("addComponent");
		this.addComponentItem.setActionCommand("addc");
		this.saveButton = new JButton("Save");
		this.saveButton.setActionCommand("save");
		
		
		this.upperPanel.setLayout(new FlowLayout());
		this.addSimplePanel.setLayout(new FlowLayout());
		this.addCompPanel.setLayout(new FlowLayout());
		this.updatePanel.setLayout(new FlowLayout());
		
		this.upperPanel.add(prepCompIt);
		this.upperPanel.add(viewItems);
		this.upperPanel.add(deleteItem);
		this.upperPanel.add(forDelete);
		
		this.addSimplePanel.add(labSimpleName);
		this.addSimplePanel.add(simpleName);
		this.addSimplePanel.add(labSimplePrice);
		this.addSimplePanel.add(simplePrice);
		this.addSimplePanel.add(createSimpleIt);
		
		this.addCompPanel.add(labCompName);
		this.addCompPanel.add(compName);
		this.addCompPanel.add(labPart);
		this.addCompPanel.add(compPart);
		this.addCompPanel.add(addComponentItem);
		this.addCompPanel.add(createCompIt);
		
		this.updatePanel.add(updItmTarget);
		this.updatePanel.add(updName);
		this.updatePanel.add(updLabelField);
		this.updatePanel.add(updField);
		this.updatePanel.add(updLabelValue);
		this.updatePanel.add(updValue);
		this.updatePanel.add(updateItem);
		
		updItmTarget.setText("Target: ");
		updLabelField.setText("Field: ");
		updLabelValue.setText("Value: ");
		labSimpleName.setText("Name: ");
		labSimplePrice.setText("Price: ");
		labCompName.setText("Name: ");
		labPart.setText("Component: ");
		
		this.itemTable = new JTable(this.forRest.avaibleMenuItems(),columnNames);
		this.ordScroll = new JScrollPane(itemTable);
		
		this.setLayout(new GridLayout(6, 1));
		this.add(upperPanel);
		this.add(addSimplePanel);
		this.add(addCompPanel);
		this.add(updatePanel);
		this.add(ordScroll);
		this.add(saveButton);
		this.setVisible(true);
		this.setSize(900,400);
		
		this.doStuff();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createSimpleItem(String name, int price) {
		try {
			MenuItem itm = new BaseProduct(name, price);
			this.forRest.addItem(itm);
		}catch(NullPointerException e) {
			System.out.println("createSimpleItem(String name, int price)");
		}			
	}
	
	private void prepareComposite() {
		this.compositeList = new ArrayList<MenuItem>();
	}
	
	private void addToCompositeElm(String name) {
		MenuItem itm = this.forRest.findByName(name);
		if(itm != null && this.compositeList.contains(itm) == false) {
			this.compositeList.add(itm);
			System.out.println(itm);
		}else {
			System.out.println("MenuItem: "+name+" does not exist or has already been introduced");
		}
	}
	
	private void createCompositeElm(String name) {
		this.forRest.addItem(new CompositeProduct(name, compositeList));
	}
	
	private void deleteMenuItem(String name) {
		this.forRest.deleteItem(name);
	}
	
	private void editMenuItem(String ItemName, String field, String value) {
		this.forRest.editItem(ItemName, field, value);
	}
	
	public void doStuff() {	
		this.createSimpleIt.addActionListener(new AdminListener());
		this.prepCompIt.addActionListener(new AdminListener());
		this.createCompIt.addActionListener(new AdminListener());
		this.viewItems.addActionListener(new AdminListener());
		this.deleteItem.addActionListener(new AdminListener());
		this.updateItem.addActionListener(new AdminListener());
		this.addComponentItem.addActionListener(new AdminListener());
		this.saveButton.addActionListener(new AdminListener());
	}
	
	class AdminListener implements ActionListener,Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4477075639658800030L;

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		System.out.println(toDo);
	
		if(toDo.equals("signalComp")) {
			prepareComposite();
		}else if(toDo.equals("view")) {
			DefaultTableModel model = new DefaultTableModel(forRest.avaibleMenuItems(),columnNames);
			itemTable.setModel(model);
			model.fireTableDataChanged();
		}else if(toDo.equals("delete")) {
			deleteMenuItem(forDelete.getText());
		}else if(toDo.equals("creatSimple")) {
			int i= 0;
			String denumire = null;
			try {
				i = Integer.parseInt(simplePrice.getText());
				denumire = simpleName.getText();
			}catch(NumberFormatException e1) {
				System.out.println("price field is NAN");
			}
			if(i>0) {
				createSimpleItem(denumire, i);
			}else {
				System.out.println("Price must be > 0");
			}
		}else if(toDo.equals("creatComp")) {
			createCompositeElm(compName.getText());
		}else if(toDo.equals("update")) {
			editMenuItem(updName.getText(), updField.getText(), updValue.getText());
		}else if(toDo.equals("addc")) {
			addToCompositeElm(compPart.getText());
		}else if (toDo.equals("save")) {
			forRest.saveData();
		}
			
	}
}
}
