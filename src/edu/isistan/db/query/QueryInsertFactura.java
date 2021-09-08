package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryInsertFactura implements QueryWithoutResponse {

	private int idFactura;
	private int idCliente;


	public QueryInsertFactura(int idFactura, int idCliente) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}


	@Override
	public void sendQuery(Connection connection) throws SQLException {

		String query = "INSERT INTO Factura (idCliente, idFactura) VALUES (?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,idCliente);
		preparedStatement.setInt(2,idFactura);

		preparedStatement.executeUpdate();
		preparedStatement.close();


	}

}
