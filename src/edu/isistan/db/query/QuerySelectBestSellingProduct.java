package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySelectBestSellingProduct implements QueryWithResponse {

	@Override
	public ResultSet select(Connection connection) throws SQLException {

		String query = "SELECT p.* "
				+ "FROM PRODUCTO p INNER JOIN FACTURA_PRODUCTO fp ON p.idProducto = fp.idProducto"
				+ "GROUP BY p.idProducto"
				+ "ORDER BY SUM(CAST('p.valor' AS int)) DESC"
				+ "LIMIT 1";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		preparedStatement.close();
		
		return resultSet;
	}


}
