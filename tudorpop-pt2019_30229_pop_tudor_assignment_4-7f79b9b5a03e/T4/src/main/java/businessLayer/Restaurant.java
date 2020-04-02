package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import dataLayer.RestaurantSerializator;
import presentationLayer.ChefGUI;

@SuppressWarnings("deprecation")
public class Restaurant extends Observable implements Serializable, IRestaurantProcessing{

	/**
	 * @invariant isWellFormed();
	 */
	private static final long serialVersionUID = 7122743076193835021L;
	public ArrayList<MenuItem> menu;
	public Map<Order, ArrayList<MenuItem>>  orders;
	@SuppressWarnings("unused")
	private List<Observer> obsv = new ArrayList<Observer>();
	private RestaurantSerializator r;
	
	public boolean isWellFormed() {
		return this instanceof Restaurant;
	}

	public Restaurant(RestaurantSerializator r) {
		super();
		this.orders = new HashMap<Order, ArrayList<MenuItem>>();
		//this.obsv = new ArrayList<Observer>();
		this.addObserver(new ChefGUI());
		
		 this.r = r;
		Restaurant dummy = r.serialRead();
		if(dummy != null) {
			this.menu = dummy.menu;			
		}else {
			this.menu = new ArrayList<MenuItem>();
		}
	}
	
	public void addOrder(Order order, ArrayList<MenuItem> food) {
		assert order != null;
		assert food != null;
		assert isWellFormed();
		
		orders.put(order, food);
		this.notifyObservers(order);
		//System.out.println(this.countObservers());
		//this.countObservers();
		
	}
	
	@Override
	public void addObserver(Observer o) {
		this.obsv.add(o);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		this.obsv.get(0).update(this, arg);
	}
	
	//@Requires("item != null") 
	public boolean exists(MenuItem item) {
		assert item != null;
		assert isWellFormed();
		
		boolean ok = true;
		for(MenuItem ex : menu) {
			if(!item.name.equalsIgnoreCase(ex.name)) {
				ok=false;
			}
		}
		return ok;
	}

	

	public MenuItem findByName(String name) {
		assert name != null;
		assert isWellFormed();
		
		MenuItem it=null;
		for(MenuItem o : menu) {
			if(o.name.equalsIgnoreCase(name)){
				it = o;
				break;
			}
		}
		return it;
	}

	public String[][] avaibleOrders(){
		assert isWellFormed();
		
		Set<Order> ord = orders.keySet();
		String[][] rez = new String[ord.size()][2];
		int i = 0;
		for(Object o: ord) {
			Order ord1 = (Order)o;
			rez[i][0] = ord1.toString();
			rez[i][1] = this.orders.getOrDefault(ord1, null).toString();
			i++;
		}
		return rez;
		
	}
	
	/** 
	 * @invariant isWellFormed()
	 * @ensures String[][]
	 */
	public String[][] avaibleMenuItems(){
		assert isWellFormed();
		
		String[][] rez = new String[menu.size()][1];
		int i = 0;
		for(MenuItem meniu: menu) {
			rez[i][0] = meniu.toString();
			i++;
		}
		return rez;
		
	}
	
	public void addItem(MenuItem o) {
		assert o != null;
		assert isWellFormed();
		
		if(findByName(o.name) == null) {
			this.menu.add(o);
			System.out.println("Added new menuItem: "+ o.name);
		}else {
			System.out.println("Item already exists");
		}
	}

	public void deleteItem(String name) {
		assert name != null;
		assert isWellFormed();
		
		MenuItem rem = this.findByName(name);
		if(rem != null) {
			this.menu.remove(rem);
			System.out.println("Removed menuItem: " + name);
		}else {
			System.out.println("Item does not exist");
		}
	}

	public void editItem(String ItemName, String field, String value) {
		assert ItemName != null;
		assert field != null;
		assert value != null;
		assert isWellFormed();
		
		MenuItem ed = findByName(ItemName);
		if(ed != null) {
			if(field.equalsIgnoreCase("name")) {
				ed.name = value;
			}else if(field.equalsIgnoreCase("price")) {
				try {
					ed.price = Integer.parseInt(value);
				}catch(NumberFormatException e) {
					System.out.println("That's not a number");
				}
			}
		}else {
			System.out.println("Item does not exist");
		}
	}

	public int computeOrderPrice(int ordId) {
		assert isWellFormed();
		
		int totalValue = 0;
		Order target = null;
		Set<Order> ord = orders.keySet();
		for(Object o: ord) {
			if (((Order)o).orderId == ordId) {
				target = (Order)o;
				break;
			}
		}
		if(target == null) {
			System.out.println("Order not found, retuned 0");
		}else {
			ArrayList<MenuItem> ordContent = this.orders.get(target);
			for(MenuItem i : ordContent) {
				totalValue += i.price;
			}
		}
		return totalValue;
	}
	
	public Order getOrderById(int orderId) {
		assert isWellFormed();
		
		Order target = null;
		Set<Order> ord = orders.keySet();
		for(Object o: ord) {
			
			//System.out.println(((Order)o).orderId);
			if (((Order)o).orderId == orderId) {
				target = (Order)o;
				//break;
			}
		}

		if(target == null) {
			System.out.println("Order not found, retuned null");
		}
		//System.out.println(target);
		return target;
	}
	
	public void deleteOrder(Order o) {
		assert o != null;
		assert isWellFormed();
		
		this.orders.remove(o);
	}
	
	public void saveData() {
		assert isWellFormed();
		
		if(this.r != null) {
			r.serialWrite(this);
		}
	}
	
	
}
