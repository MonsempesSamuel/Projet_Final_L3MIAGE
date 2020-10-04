package DAO;

import java.sql.*;

public abstract class SqlDAO<T> implements DAO<T>{
	protected Connection connect;
	
	public SqlDAO () throws SQLException{
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		connect = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "login", "mdp");
		connect.setAutoCommit(true);
	}
	
	public abstract boolean create(T obj);
	
	public abstract T read(int id);
	
	public abstract boolean update(T obj);
	
	public abstract boolean delete(T obj);
	
}
