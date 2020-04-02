package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//https://bitbucket.org/utcn_dsrl/pt-reflection-example/src/master/src/main/java/connection/ConnectionFactory.java
public class DBConnection {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/hw3db";
	private static final String	USER = "root";
	private static final String PW = "";
	
	private static DBConnection singleConn = new DBConnection();
	private DBConnection() {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("DBConnection constructor exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		}
	}
	
	private Connection connect() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL,USER, PW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBConnection getConnection exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static Connection getConnection() {
		return singleConn.connect();
	}
	
	public static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("DBConnection closeConnection exception: "+ e.getClass()+"/n");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stat) {
		if(stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("DBConnection closeStatement exception: "+ e.getClass()+"/n");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rezSet) {
		if(rezSet != null) {
			try {
				rezSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("DBConnection closeResultSet exception: "+ e.getClass()+"/n");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
