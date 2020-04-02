package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DBConnection;

public class AbstractDAO<T> {
	protected final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		super();
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String createSelectQuery(String field) {
		StringBuilder ret = new StringBuilder();
		ret.append("SELECT ");
		ret.append(" * ");
		ret.append(" FROM ");
		ret.append(type.getSimpleName());
		ret.append(" WHERE " + field + " =?");
		return ret.toString();
	}
	
	private String createSelectAllQuery() {
		StringBuilder ret = new StringBuilder();
		ret.append("SELECT ");
		ret.append(" * ");
		ret.append(" FROM ");
		ret.append(type.getSimpleName());
		return ret.toString();
	}
	
	
	
	private String createDeleteQuery(int rowId) {
		StringBuilder ret = new StringBuilder();
		ret.append("DELETE ");
		ret.append(" FROM ");
		ret.append(type.getSimpleName());
		ret.append(" WHERE " + type.getSimpleName().toLowerCase()+"Id" + " =" + rowId);
		return ret.toString();
	}
	
	private String createUpdateQuery(int rowId, String field) {
		StringBuilder ret = new StringBuilder();
		ret.append("UPDATE ");
		ret.append(type.getSimpleName());
		ret.append(" SET ");
		ret.append(field + " =?");
		ret.append(" WHERE " + type.getSimpleName().toLowerCase()+"Id" + " =" + rowId);
		return ret.toString();
	}
	
	private String createInsertQuery(int fieldsNumber) {
		StringBuilder ret = new StringBuilder();
		ret.append("INSERT INTO ");
		ret.append(type.getSimpleName());
		ret.append(" VALUES ");
		ret.append("( ");
		for(int i=0;i<fieldsNumber-1;i++) {
			ret.append("?, ");
		}
		ret.append("?);");
		return ret.toString();
	}
	
	public void insertObject(ArrayList<String> values) { // Convert ints in Integer ?????
		Connection conn = null;
		PreparedStatement statm = null;
		@SuppressWarnings("unused")
		int rezSet = 0;
		String selStat = createInsertQuery(values.size());
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			int intVal = -1;
			
			for(int i=1;i<=values.size(); i++) {
				boolean isNr = true;
				try {
					intVal = Integer.parseInt(values.get(i-1));
				}catch(Exception e) {
					isNr = false;
				}
				
				if(isNr) {
					statm.setInt(i, intVal);					
				}else if (values.get(i-1) instanceof String) {
					statm.setString(i, "'"+values.get(i-1)+"'" );	
				}
			}
			rezSet = statm.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("AbtractDAO insertObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			//DBConnection.close(rezSet);
			DBConnection.close(statm);
			DBConnection.close(conn);
		}
	}
	
	public T findObject(int id) {
		Connection conn = null;
		PreparedStatement statm = null;
		ResultSet rezSet = null;
		String selStat = createSelectQuery(type.getSimpleName()+"Id");
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			statm.setInt(1, id);
			rezSet = statm.executeQuery();
			
			return createObjects(rezSet).get(0);
			
		}catch(Exception e) {
			System.out.println("AbtractDAO findObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			DBConnection.close(rezSet);
			DBConnection.close(statm);
			DBConnection.close(conn);
		}
		return null;
	}
	
	public ResultSet findAllObjects() {
		Connection conn = null;
		PreparedStatement statm = null;
		ResultSet rezSet = null;
		String selStat = createSelectAllQuery();
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			rezSet = statm.executeQuery();
			
			return rezSet;
			
		}catch(Exception e) {
			System.out.println("AbtractDAO findObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			//DBConnection.close(rezSet);
			//DBConnection.close(statm);
			//DBConnection.close(conn);
		}
		return null;
	}
	
	public void deleteObject(int id) {
		Connection conn = null;
		PreparedStatement statm = null;
		@SuppressWarnings("unused")
		int rezSet = 0;
		String selStat = createDeleteQuery(id);
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			rezSet = statm.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("AbtractDAO deleteObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			//DBConnection.close(rezSet);
			DBConnection.close(statm);
			DBConnection.close(conn);
		}
	}
	
	public void updateObject(int id, String field, String value) {// value poate fi "C1 + 1", " 'StringValue' " etc;
		Connection conn = null;
		PreparedStatement statm = null;
		@SuppressWarnings("unused")
		int rezSet;
		int intVal = 0;
		String selStat = createUpdateQuery(id,field);
		try {
			conn = DBConnection.getConnection();
			statm = conn.prepareStatement(selStat);
			
			boolean isNr = true;
			try {
				intVal = Integer.parseInt(value);
			}catch(Exception e) {
				isNr = false;
			}
			
			if(isNr) {
				statm.setInt(1, intVal);					
			}else if (value instanceof String) {
				statm.setString(1, "'"+value+"'" );	
			}
			
			rezSet =statm.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("AbtractDAO updateObject exception: "+ e.getClass()+"/n");
			e.printStackTrace();
		} finally {
			//DBConnection.close(rezSet);
			DBConnection.close(statm);
			DBConnection.close(conn);
		}
	}
	
	public List<T> createTableData(){
		return createObjects(this.findAllObjects());
	}
	
	public List<String> createTableHead(){
		List<String> l = new ArrayList<String>();
		
		for (Field field : type.getDeclaredFields()) {
			field.setAccessible(true);
			l.add(field.getName());
		}
		return l;
	}
	
	
	
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) { 
				@SuppressWarnings("deprecation")
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					field.setAccessible(true);
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					System.out.println(field.toString());
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
