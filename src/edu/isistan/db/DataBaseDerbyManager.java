package edu.isistan.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseDerbyManager extends DataBaseManager {

	public DataBaseDerbyManager() {

		super("org.apache.derby.jdbc.EmbeddedDriver","jdbc:derby:MyDerbyDb;create=true");
		
	}

	@Override
	protected void openConnection() throws SQLException {
		if(connection!=null)return;
		connection = DriverManager.getConnection(uri);
		connection.setAutoCommit(false);
	}


}
