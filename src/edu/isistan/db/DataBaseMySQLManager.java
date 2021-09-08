package edu.isistan.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseMySQLManager extends DataBaseManager {

	public DataBaseMySQLManager() {

		super("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/tp1DB");
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void openConnection() throws SQLException {
		if(connection!=null)return;
		connection = DriverManager.getConnection(uri, "root", "password");
		connection.setAutoCommit(false);
	}

}
