package HW4.T4;

import java.io.Serializable;
import java.util.ArrayList;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.AdminGUI;
import presentationLayer.WaiterGUI;

/**
 * Hello world!
 *
 */
public class App  implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6671293762474071678L;

	public static void main( String[] args )
    {
    	/*ArrayList<MenuItem> test = new ArrayList<MenuItem>();
    	test.add(new BaseProduct("apa", 2));
    	test.add(new BaseProduct("suc", 1));*/
        Restaurant r = new Restaurant(new RestaurantSerializator());
        /*try {
			r.addOrder(new Order("now", 2), test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        System.out.println(r.orders.toString());
        //System.out.println(r == null);
        WaiterGUI waiter = new WaiterGUI(r);
        AdminGUI admin = new AdminGUI(r);
    }
}
