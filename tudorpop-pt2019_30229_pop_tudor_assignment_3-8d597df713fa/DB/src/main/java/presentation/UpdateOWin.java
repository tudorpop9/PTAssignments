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

import dao.OrderDAO;


public class UpdateOWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField rowId;
	private JTextField orderid;
	private JTextField clientid;
	private JTextField clientadr;
	private JTextField productid;
	private JTextField quant;
	
	private JButton submit1;
	private JButton submit2;
	private JButton submit3;
	private JButton submit4;
	private JButton submit5;
	private JButton done;
	
	private JPanel pan0;
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel pan5;
	private JPanel subp;
	
	private JLabel lRowId;
	private JLabel lid;
	private JLabel lcid;
	private JLabel lpid;
	private JLabel ladr;
	private JLabel lquant;
	
	public UpdateOWin() throws HeadlessException {
		super("Insert Client");
		this.setVisible(true);
		this.setLayout(new GridLayout(7,1));
		this.setSize(400, 400);
		
		this.rowId = new JTextField(20);
		this.orderid = new JTextField(20);
		this.clientid = new JTextField(20);
		this.clientadr = new JTextField(20);
		this.productid = new JTextField(20);
		this.quant = new JTextField(20);
		
		this.lRowId = new JLabel();
		this.lid = new JLabel();
		this.lcid = new JLabel();
		this.lpid = new JLabel();
		this.ladr = new JLabel();
		this.lquant = new JLabel();
		
		this.lRowId.setText("Row's id to be updated");
		this.lid.setText("Order ID: ");
		this.lcid.setText("Client ID: ");
		this.lpid.setText("Product ID: ");
		this.ladr.setText("Client Adr: ");
		this.lquant.setText("Product Quant: ");
		
		this.done = new JButton("Done");
		this.done.setActionCommand("done");
		
		this.submit1 = new JButton("Submit");
		this.submit1.setActionCommand("idO");
		this.submit2 = new JButton("Submit");
		this.submit2.setActionCommand("idC");
		this.submit3 = new JButton("Submit");
		this.submit3.setActionCommand("idP");
		this.submit4 = new JButton("Submit");
		this.submit4.setActionCommand("adrC");
		this.submit5 = new JButton("Submit");
		this.submit5.setActionCommand("quant");
		
		this.pan0 = new JPanel();
		this.pan0.setLayout(new FlowLayout());
		this.pan0.add(lRowId);
		this.pan0.add(rowId);
		
		this.pan1 = new JPanel();
		this.pan1.setLayout(new FlowLayout());
		this.pan1.add(lid);
		this.pan1.add(orderid);
		this.pan1.add(this.submit1);
		
		this.pan2 = new JPanel();
		this.pan2.setLayout(new FlowLayout());
		this.pan2.add(lcid);
		this.pan2.add(clientid);
		this.pan2.add(this.submit2);
		
		this.pan3 = new JPanel();
		this.pan3.setLayout(new FlowLayout());
		this.pan3.add(ladr);
		this.pan3.add(clientadr);
		this.pan3.add(this.submit3);
		
		this.pan4 = new JPanel();
		this.pan4.setLayout(new FlowLayout());
		this.pan4.add(lpid);
		this.pan4.add(productid);
		this.pan4.add(this.submit4);
		
		this.pan5 = new JPanel();
		this.pan5.setLayout(new FlowLayout());
		this.pan5.add(lquant);
		this.pan5.add(quant);
		this.pan5.add(this.submit5);
		
		this.subp = new JPanel();
		this.subp.setLayout(new FlowLayout());
		this.subp.add(done);
		
		this.add(this.pan0);
		this.add(this.pan1);
		this.add(this.pan2);
		this.add(this.pan3);
		this.add(this.pan4);
		this.add(this.pan5);
		this.add(this.subp);
		
		this.doStuff();
	}
	public void doStuff() {	
		this.submit1.addActionListener(new updateOListener());
		this.submit2.addActionListener(new updateOListener());
		this.submit3.addActionListener(new updateOListener());
		this.submit4.addActionListener(new updateOListener());
		this.submit4.addActionListener(new updateOListener());
		this.done.addActionListener(new updateOListener());
	}

	//Listener
	class updateOListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		//System.out.println(toDo);
		OrderDAO cd = new OrderDAO();
		
		if(toDo.equals("done")) {
			//Call db and try to do stuff
			
			setVisible(false);
		}else if (toDo.equals("idO")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "ordertableId", orderid.getText());
		}else if (toDo.equals("idC")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "clientId", clientid.getText());
			
		}else if (toDo.equals("idP")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "clientAdr", clientadr.getText());
			
		}else if (toDo.equals("adrC")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "productId", productid.getText());
			
		}
		else if (toDo.equals("quant")) {
			// DB stuff
			int row = Integer.parseInt(rowId.getText());
			cd.updateObject(row, "orderedQuant", quant.getText());
		}
		}
	}
	
	
	
}
