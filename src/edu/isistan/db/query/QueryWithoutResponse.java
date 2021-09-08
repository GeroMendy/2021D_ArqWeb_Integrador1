package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryWithoutResponse {

	public abstract void sendQuery(Connection connection) throws SQLException;
}
