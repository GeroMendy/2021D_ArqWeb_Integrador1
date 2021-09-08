package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySelectByDoubleKey extends QueryGenericSelect {
	
	protected int secondaryKey = -1;
	protected String otherKeyName = "";

	public QuerySelectByDoubleKey(String table_name,String primaryKeyName, int primaryKey, String otherKeyName,  int otherKey) {
		super(table_name,primaryKeyName, primaryKey);
		this.secondaryKey = otherKey;
		this.otherKeyName = otherKeyName;
	}
	

	public ResultSet select(Connection connection) throws SQLException {

		String select = "SELECT * FROM "+table_name+" WHERE "+primaryKeyName+"=? AND "+otherKeyName+"=?";
		PreparedStatement preparedStatement = connection.prepareStatement(select);
		preparedStatement.setInt(1, this.primaryKey);
		preparedStatement.setInt(2, this.secondaryKey);
		return preparedStatement.executeQuery();
	}

}
