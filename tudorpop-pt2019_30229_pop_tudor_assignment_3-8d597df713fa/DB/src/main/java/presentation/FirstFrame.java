package presentation;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class FirstFrame extends JFrame{
	private JPanel clientPan;
	private JPanel productPan;
	private JPanel orderPan;
	private JPanel logPan;
	private JPanel billPan;
	
	private JLabel clientLabel;
	private JLabel productLabel;
	private JLabel orderLabel;
	private JLabel billLabel;
	private JLabel logTitleLabel;
	private JLabel logTextLabel;
	
	private JButton CUBtn;
	private JButton CIBtn;
	private JButton CDBtn;
	private JButton CFBtn;
	private JButton CFABtn;
	
	private JButton PUBtn;
	private JButton PIBtn;
	private JButton PDBtn;
	private JButton PFBtn;
	private JButton PFABtn;
	
	private JButton OUBtn;
	private JButton OIBtn;
	private JButton ODBtn;
	private JButton OFBtn;
	private JButton OFABtn;
	
	private JButton BUBtn;
	private JButton BIBtn;
	private JButton BDBtn;
	private JButton BFBtn;
	private JButton BFABtn;
	private JLabel dummyLab1;
	private JLabel dummyLab2;
	
	public FirstFrame() throws HeadlessException {
		super();
		this.setVisible(true);
		this.setLayout(new GridLayout(5,1));
		
		this.clientPan = new JPanel();
		this.clientPan.setLayout(new GridLayout(1,6));
		
		this.productPan = new JPanel();
		this.productPan.setLayout(new GridLayout(1,6));
		
		this.orderPan = new JPanel();
		this.orderPan.setLayout(new GridLayout(1,6));
		
		this.billPan = new JPanel();
		this.billPan.setLayout(new GridLayout(1,4));
		
		this.logPan = new JPanel();
		this.logPan.setLayout(new GridLayout(1,2));
		
		this.clientLabel = new JLabel();
		this.clientLabel.setText("    Client:");
		
		this.productLabel = new JLabel();
		this.productLabel.setText("    Product:");
		
		this.orderLabel = new JLabel();
		this.orderLabel.setText("    Order:");
		
		this.billLabel = new JLabel();
		this.billLabel.setText("    Bill:");
		this.dummyLab1 = new JLabel();
		this.dummyLab2 = new JLabel();
		
		
		
		this.logTitleLabel = new JLabel(); ///////////////////////LOG
		this.logTitleLabel.setText("    Log:");
		this.logTextLabel = new JLabel();
		this.logPan.add(this.logTitleLabel);
		this.logPan.add(this.logTextLabel);
		
		///////////////////////////////////////////////Buttons
		this.CUBtn = new JButton();
		this.CUBtn.setText("Update");
		this.CUBtn.setActionCommand("UC");
		this.CIBtn = new JButton();
		this.CIBtn.setText("Insert");
		this.CIBtn.setActionCommand("IC");
		this.CDBtn = new JButton();
		this.CDBtn.setText("Delete");
		this.CDBtn.setActionCommand("DC");
		this.CFBtn = new JButton();
		this.CFBtn.setText("Find");
		this.CFBtn.setActionCommand("FC");
		this.CFABtn = new JButton();
		this.CFABtn.setText("FindAll");
		this.CFABtn.setActionCommand("FAllC");
		
		this.PUBtn = new JButton();
		this.PUBtn.setText("Update");
		this.PUBtn.setActionCommand("UP");
		this.PIBtn = new JButton();
		this.PIBtn.setText("Insert");
		this.PIBtn.setActionCommand("IP");
		this.PDBtn = new JButton();
		this.PDBtn.setText("Delete");
		this.PDBtn.setActionCommand("DP");
		this.PFBtn = new JButton();
		this.PFBtn.setText("Find");
		this.PFBtn.setActionCommand("FP");
		this.PFABtn = new JButton();
		this.PFABtn.setText("FindAll");
		this.PFABtn.setActionCommand("FAllP");
		
		this.OUBtn = new JButton();
		this.OUBtn.setText("Update");
		this.OUBtn.setActionCommand("UO");
		this.OIBtn = new JButton();
		this.OIBtn.setText("Insert");
		this.OIBtn.setActionCommand("IO");
		this.ODBtn = new JButton();
		this.ODBtn.setText("Delete");
		this.ODBtn.setActionCommand("DO");
		this.OFBtn = new JButton();
		this.OFBtn.setText("Find");
		this.OFBtn.setActionCommand("FO");
		this.OFABtn = new JButton();
		this.OFABtn.setText("FindAll");
		this.OFABtn.setActionCommand("FAllO");
		
		this.BIBtn = new JButton();
		this.BIBtn.setText("Create");
		this.BIBtn.setActionCommand("IB");
		this.BFBtn = new JButton();
		this.BFBtn.setText("Find");
		this.BFBtn.setActionCommand("FB");
		this.BFABtn = new JButton();
		this.BFABtn.setText("FindAll");
		this.BFABtn.setActionCommand("FAllB");
		
		this.clientPan.add(this.clientLabel);
		this.clientPan.add(this.CIBtn);
		this.clientPan.add(this.CUBtn);
		this.clientPan.add(this.CDBtn);
		this.clientPan.add(this.CFBtn);
		this.clientPan.add(this.CFABtn);
		
		this.productPan.add(this.productLabel);
		this.productPan.add(this.PIBtn);
		this.productPan.add(this.PUBtn);
		this.productPan.add(this.PDBtn);
		this.productPan.add(this.PFBtn);
		this.productPan.add(this.PFABtn);
		
		this.orderPan.add(this.orderLabel);
		this.orderPan.add(this.OIBtn);
		this.orderPan.add(this.OUBtn);
		this.orderPan.add(this.ODBtn);
		this.orderPan.add(this.OFBtn);
		this.orderPan.add(this.OFABtn);
		
		this.billPan.add(this.billLabel);
		this.billPan.add(this.BIBtn);
		this.billPan.add(this.BFBtn);
		this.billPan.add(this.BFABtn);
		this.billPan.add(dummyLab1);
		this.billPan.add(dummyLab2);
		
		this.add(clientPan);
		this.add(productPan);
		this.add(orderPan);
		this.add(billPan);
		this.add(logPan);
		
		this.setSize(800, 200);	
	}
	
	public void perform() {	
		
		this.CIBtn.addActionListener(new ProjectListener());
		this.CUBtn.addActionListener(new ProjectListener());
		this.CDBtn.addActionListener(new ProjectListener());
		this.CFBtn.addActionListener(new ProjectListener());
		this.CFABtn.addActionListener(new ProjectListener());

		this.PIBtn.addActionListener(new ProjectListener());
		this.PUBtn.addActionListener(new ProjectListener());
		this.PDBtn.addActionListener(new ProjectListener());
		this.PFBtn.addActionListener(new ProjectListener());
		this.PFABtn.addActionListener(new ProjectListener());
		
		this.OIBtn.addActionListener(new ProjectListener());
		this.OUBtn.addActionListener(new ProjectListener());
		this.ODBtn.addActionListener(new ProjectListener());
		this.OFBtn.addActionListener(new ProjectListener());
		this.OFABtn.addActionListener(new ProjectListener());
		
		this.BIBtn.addActionListener(new ProjectListener());
		this.BFBtn.addActionListener(new ProjectListener());
		this.BFABtn.addActionListener(new ProjectListener());
		
		this.setDefaultCloseOperation(FirstFrame.EXIT_ON_CLOSE);

	}
	
	//Listener
	class ProjectListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String toDo = e.getActionCommand();
			System.out.println(toDo);
		try {
			if(toDo.equals("UC")) {
				UpdateCWin uc = new UpdateCWin();
			}else if (toDo.equals("IC")) {
				InsertCWin ic = new InsertCWin(); 
			}else if (toDo.equals("DC")) {
				System.out.println("????????????");
				DeleteCWin dc = new DeleteCWin();
			}else if (toDo.equals("FC")) {
				FindCWin fc = new FindCWin();
			}else if (toDo.equals("FAllC")) {
				FindAllCWin fallc = new FindAllCWin();
			}else if(toDo.equals("UP")) {
				UpdatePWin up = new UpdatePWin();
			}else if (toDo.equals("IP")) {
				InsertPWin ip = new InsertPWin();
			}else if (toDo.equals("DP")) {
				DeletePWin dp = new DeletePWin();
			}else if (toDo.equals("FP")) {
				FindPWin fp = new FindPWin();
			}else if (toDo.equals("FAllP")) {
				FindAllPWin fallp = new FindAllPWin();
			}else if(toDo.equals("UO")) {
				UpdateOWin uo = new UpdateOWin();
			}else if (toDo.equals("IO")) {
				InsertOWin io = new InsertOWin();
			}else if (toDo.equals("DO")) {
				DeleteOWin delO = new DeleteOWin();
			}else if (toDo.equals("FO")) {
				FindOWin fo = new FindOWin();
			}else if (toDo.equals("FAllO")) {
				FindAllOWin fallo = new FindAllOWin();
			}else if (toDo.equals("IB")) {
				//Create Bill
				CreateBillWin cBill = new CreateBillWin();
			}else if (toDo.equals("FB")) {
				FindBillWin fBill = new FindBillWin();
			}else if (toDo.equals("FAllB")) {
				FindAllBillsWin fAllBills = new FindAllBillsWin();
			}
				
		}catch(Exception exception) {
			logTextLabel.setText("     "+exception.getClass().getSimpleName()+" "+exception.getMessage());
			exception.printStackTrace();
		}

	}
}

	public FirstFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public FirstFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public FirstFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public JLabel getLogTextLabel() {
		return logTextLabel;
	}

	public void setLogTextLabel(JLabel logTextLabel) {
		this.logTextLabel = logTextLabel;
	}
	
		
	
}
