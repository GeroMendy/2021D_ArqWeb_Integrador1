package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySelectBestCliente implements QueryWithResponse {

	@Override
	public ResultSet select(Connection connection) throws SQLException {
		String query ="SELECT c.* FROM cliente c INNER JOIN factura f ON c.idCliente = f.idCliente\r\n"
				+ " INNER JOIN factura_producto fp on f.idFactura = fp.idFactura\r\n"
				+ " INNER JOIN producto p ON fp.idProducto = p.idProducto\r\n"
				+ "GROUP BY c.idCliente\r\n"
				+ "ORDER BY SUM(CAST('p.valor' AS int)) DESC";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		preparedStatement.close();
		
		return resultSet;
	}

}
