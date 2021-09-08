package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public interface QueryWithResponse {

	public abstract ResultSet select(Connection connection) throws SQLException;
}
