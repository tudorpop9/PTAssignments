package model;

import java.util.ArrayList;

public class OrderTable {
	private int ordertableId;
	private int clientId;
	private String clientAdr;
	private int productId;
	private int orderedQuant;
	
	public Object[] getDataInArray() {
		ArrayList<String> s = new ArrayList<String>();
		s.add(String.valueOf(this.ordertableId));
		s.add(String.valueOf(this.clientId));
		s.add(clientAdr);
		s.add(String.valueOf(this.productId));
		s.add(String.valueOf(this.orderedQuant));
		
		Object[] rez = s.toArray();
		
		return rez;
	}
	
	
	public OrderTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderTable(int ordertableId, int clientId, String clientAdr, int productId, int orderedQuant) {
		super();
		this.ordertableId = ordertableId;
		this.clientId = clientId;
		this.clientAdr = clientAdr;
		this.productId = productId;
		this.orderedQuant = orderedQuant;
	}
	public int getOrdertableId() {
		return ordertableId;
	}
	public void setOrdertableId(int orderId) {
		this.ordertableId = orderId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientAdr() {
		return clientAdr;
	}
	public void setClientAdr(String clientAdr) {
		this.clientAdr = clientAdr;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderedQuant() {
		return orderedQuant;
	}
	public void setOrderedQuant(int orderedQuant) {
		this.orderedQuant = orderedQuant;
	}
	
	@Override
	public String toString() {
		return "Order: [ "+this.ordertableId+" Client: "+ this.clientId + " Ard:" + this.clientAdr + " Product:" + this.productId + " Quant:"+ this.orderedQuant+"] ";
	}
}
