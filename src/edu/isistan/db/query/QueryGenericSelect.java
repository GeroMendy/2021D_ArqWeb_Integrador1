package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class QueryGenericSelect implements QueryWithResponse {
	
	protected String table_name;
	protected int primaryKey = -1;
	protected String primaryKeyName = "id";
	

	public QueryGenericSelect(String table_name) {
		super();
		this.table_name = table_name;
	}

	public QueryGenericSelect(String table_name, String primaryKeyName, int primaryKey) {
		super();
		this.table_name = table_name;
		this.primaryKeyName = primaryKeyName;
		this.primaryKey = primaryKey;
	}

	@Override
	public ResultSet select(Connection connection) throws SQLException {

		String select = "SELECT * FROM "+table_name+" WHERE " + primaryKeyName + "=?";
		PreparedStatement preparedStatement = connection.prepareStatement(select);
		preparedStatement.setInt(1, this.primaryKey);
		return preparedStatement.executeQuery();
	}

	public ResultSet selectAll(Connection connection) throws SQLException {

		String select = "SELECT * FROM "+table_name;
		PreparedStatement preparedStatement = connection.prepareStatement(select);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet;
	}


}
