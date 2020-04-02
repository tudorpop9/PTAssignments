package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataAccessLayer.DBConnection;
import model.*;

public class OrderDAO extends AbstractDAO<OrderTable> {
	
	
	private String selectQuantValQuery() {
		StringBuilder ret = new StringBuilder();
		ret.append("SELECT ");
		ret.append(" productQuant ");
		ret.append(" FROM Product");
		ret.append(" WHERE productId =?");
		return ret.toString();
	}
	
	public int findQuantVal(int id) {
		Connection conn = null;
		PreparedStatement statm = null;
		ResultSet rezSet = null;
		String selStat = selectQuantValQuery();
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			statm.setInt(1, id);
			rezSet = statm.executeQuery();
			
			rezSet.next();
			return rezSet.getInt(1);
			
		}catch(Exception e) {
			System.out.println("AbtractDAO findObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			DBConnection.close(rezSet);
			DBConnection.close(statm);
			DBConnection.close(conn);
		}
		return Integer.MIN_VALUE;
	}
}