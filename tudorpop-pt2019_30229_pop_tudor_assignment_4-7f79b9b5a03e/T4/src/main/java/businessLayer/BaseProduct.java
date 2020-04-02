package businessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4998389177496040266L;

	public BaseProduct(String name, int price) {
		super();
		this.name = name;
		this.price = price;
		setComposed(false);
	}

	@Override
	public int computePrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

}
