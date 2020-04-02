package businessLayer;

import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2144371528419078291L;
	public int orderId;
	public String date;
	public int table;
	private static int orderNr = 0;
	
	
	public Order(String date, int table) {
		super();
		this.orderId = ++orderNr;
		this.date = date;
		this.table = table;
	}

	public static int getOrderNr() {
		return orderNr;
	}

	public static void setOrderNr(int orderNr) {
		Order.orderNr = orderNr;
	}

	@Override
	public int hashCode() {
		return orderId + table;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.orderId == ((Order)o).orderId;
	}

	@Override
	public String toString() {
		return "Order [orderId= " + orderId + ", date= " + date + ", table= " + table + "]";
	}
	
	
}
