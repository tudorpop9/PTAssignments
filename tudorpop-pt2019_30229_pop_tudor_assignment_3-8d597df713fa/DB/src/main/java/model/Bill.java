package model;

import java.util.ArrayList;

public class Bill {
	private int billId;
	private String productName;
	private String clientName;
	private String clientEmail;
	private int orderedQuant;
	private int billValue;
	
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object[] getDataInArray() {
		ArrayList<String> s = new ArrayList<String>();
		s.add(String.valueOf(this.billId));
		s.add(productName);
		s.add(clientName);
		s.add(clientEmail);
		s.add(String.valueOf(this.orderedQuant));
		s.add(String.valueOf(this.billValue));
		
		Object[] rez = s.toArray();
		
		return rez;
	}
	
	public Bill(int billId, String productName, String clientName, String clientEmail, int orderedQuant,
			int billValue) {
		super();
		this.billId = billId;
		this.productName = productName;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.orderedQuant = orderedQuant;
		this.billValue = billValue;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int orderId) {
		this.billId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public int getOrderedQuant() {
		return orderedQuant;
	}
	public void setOrderedQuant(int orderedQuant) {
		this.orderedQuant = orderedQuant;
	}
	public int getBillValue() {
		return billValue;
	}
	public void setBillValue(int billValue) {
		this.billValue = billValue;
	}
	
	@Override
	public String toString() {
		return "Bill: [Nr: "+this.billId+", Client: "+ this.clientName + ", Email:" + this.clientEmail + ". Product:" + this.productName + " Quantity:"+ this.orderedQuant+", Total: "+this.billValue + "lei ] ";
	}
}
