package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133112207865763868L;
	public ArrayList<MenuItem> part;
	
	
	public CompositeProduct(String name, ArrayList<MenuItem> part) {
		super();
		this.name = name;
		this.part = part;
		setComposed(true);
		this.price = computePrice();
	}

	@Override
	public int computePrice() {
		int finalPr = 0;
		for(MenuItem m : part) {
			finalPr += m.computePrice();
		}
		return finalPr;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof BaseProduct) {
			return false;
		}
		CompositeProduct i = (CompositeProduct)o;
		if(super.equals(i) && this.part.equals(i.part) ) {
			return true;
		}
		return false;	
	}

	@Override
	public String toString() {
		return super.toString()+" CompositeProduct [part=" + part + "]";
	}
	
	
}
