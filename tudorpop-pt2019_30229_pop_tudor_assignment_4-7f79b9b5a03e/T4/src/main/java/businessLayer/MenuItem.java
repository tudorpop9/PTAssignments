package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4561986279476793825L;
	public String name;
	public int price;
	private boolean composed;
	public abstract int computePrice();
	
	public boolean isComposed() {
		return composed;
	}
	public void setComposed(boolean composed) {
		this.composed = composed;
	}
	
	@Override
	public boolean equals(Object o) {
		MenuItem i = (MenuItem)o;
		if(name.equals(i.name) && price == i.price && composed == i.composed ) {
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		return "MenuItem [name= " + name + ", price= " + price + "]";
	}
	
}
