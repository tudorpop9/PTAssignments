package dataLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import businessLayer.Restaurant;

public class RestaurantSerializator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8554005117461115069L;
	private ObjectOutputStream outHere;
	private ObjectInputStream inHere;

	public RestaurantSerializator() {
		super();
		try {
			outHere = new ObjectOutputStream(new FileOutputStream("restData.bin"));
			inHere = new ObjectInputStream(new FileInputStream("restData.bin"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("RestaurantSerializator1\n");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("RestaurantSerializator2\n");
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	public void serialWrite(Restaurant r) {
		try {
			this.outHere.writeObject(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("serialWrite1\n");
			e.printStackTrace();
		}finally{
			try {
				outHere.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Restaurant serialRead() {
		Restaurant rest = null;
		try {
			rest = (Restaurant) this.inHere.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("serialRead1\n");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("serialRead2\n");
			e.printStackTrace();
		} finally {
			try {
				inHere.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rest;
	}
}
