package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public interface IRestaurantProcessing extends Serializable{
	/** 
	 * @requires order!=null
	 * @requires food!=null
	 * @invariant isWellFormed()
	 */
	public void addOrder(Order order, ArrayList<MenuItem> food);
	
	/** 
	 * @requires o!=null
	 * @invariant isWellFormed()
	 */
	public void addItem(MenuItem o);
	
	/** 
	 * @requires name!=null
	 * @invariant isWellFormed()
	 */
	public void deleteItem(String name);
	
	/** 
	 * @requires item!=null
	 * @invariant isWellFormed()
	 * @ensures boolean
	 */
	public boolean exists(MenuItem item);
	
	/** 
	 * @requires name!=null
	 * @invariant isWellFormed()
	 * @ensures MenuItem
	 */
	public MenuItem findByName(String name);
	
	/** 
	 * @invariant isWellFormed()
	 * @requires ItemName!=null
	 * @requires filed!=null
	 * @requires value!=null
	 * @ensures void
	 */
	public void editItem(String ItemName, String field, String value);
	
	/** 
	 * @requires ordId>0
	 * @invariant isWellFormed()
	 */
	public int computeOrderPrice(int ordId);
	
	/** 
	 * @requires orderId>0
	 * @invariant isWellFormed()
	 * @ensures Order
	 */
	public Order getOrderById(int orderId);
	
	/** 
	 * @requires o!=null
	 * @invariant isWellFormed()
	 * @ensures void
	 */
	public void deleteOrder(Order o);
	
	/** 
	 * @invariant isWellFormed()
	 * @ensures String[][]
	 */
	public String[][] avaibleOrders();
	
	/** 
	 * @invariant isWellFormed()
	 * @ensures String[][]
	 */
	public String[][] avaibleMenuItems();
	
	/**
	 * @invariant isWellFormed()
	 * @ensures void
	 */
	public void saveData();
	
	/** 
	 * @invariant isWellFormed()
	 * @ensures boolean
	 */
	public boolean isWellFormed();
}
