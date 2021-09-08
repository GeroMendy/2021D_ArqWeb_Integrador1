package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryInsertFacturaProducto implements QueryWithoutResponse {

	private int idFactura;
	private int idProducto;
	private int cantidad;

	public QueryInsertFacturaProducto(int idFactura, int idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	@Override
	public void sendQuery(Connection connection) throws SQLException {

		String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,idFactura);
		preparedStatement.setInt(2,idProducto);
		preparedStatement.setInt(3,cantidad);

		preparedStatement.executeUpdate();
		preparedStatement.close();


	}

}
