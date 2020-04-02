package model;

import java.util.ArrayList;

public class Product {
	private int productId;
	private String productName;
	private int productPrice;
	private int productQuant;
	
	
	public Object[] getDataInArray() {
		ArrayList<String> s = new ArrayList<String>();
		s.add(String.valueOf(this.productId));
		s.add(productName);
		s.add(String.valueOf(this.productPrice));
		s.add(String.valueOf(this.productQuant));
		
		Object[] rez = s.toArray();
		
		return rez;
	}
	
	public Product() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(int productId, String productName, int productPrice, int pruductQuant) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuant = pruductQuant;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuant() {
		return productQuant;
	}
	public void setProductQuant(int pruductQuant) {
		this.productQuant = pruductQuant;
	}
	
	@Override
	public String toString() {
		return "Produs: [ "+this.productId+" "+ this.productName + " Price: " + this.productPrice + " Quant:" + this.productQuant +"] ";
	}
}
