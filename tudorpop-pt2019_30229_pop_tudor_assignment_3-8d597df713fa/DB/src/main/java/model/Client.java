package model;

import java.util.ArrayList;

public class Client {
	private int clientId;
	private String clientName;
	private String clientEmail;
	private String clientAdr;
	
	
	
	public Client() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	public Client(int clientId, String clientName, String clientEmail, String clientAdr) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientAdr = clientAdr;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
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
	public String getClientAdr() {
		return clientAdr;
	}
	public void setClientAdr(String clientAdr) {
		this.clientAdr = clientAdr;
	}
	
	@Override
	public String toString() {
		return "Client: [ "+this.clientId+" "+ this.clientName + " " + this.clientEmail + " " + this.clientAdr +"] ";
	}
	
	public Object[] getDataInArray() {
		ArrayList<String> s = new ArrayList<String>();
		s.add(String.valueOf(this.clientId));
		s.add(clientName);
		s.add(clientEmail);
		s.add(clientAdr);
		
		Object[] rez = s.toArray();
		
		return rez;
	}
	
	
}
