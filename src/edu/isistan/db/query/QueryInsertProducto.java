package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryInsertProducto implements QueryWithoutResponse {
	
	private int idProducto;
	private String nombre;
	private float valor;

	public QueryInsertProducto(int idProducto, String nombre, float valor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
	}

	@Override
	public void sendQuery(Connection connection) throws SQLException {


		String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,idProducto);
		preparedStatement.setString(2,nombre);
		preparedStatement.setFloat(3,valor);

		preparedStatement.executeUpdate();
		preparedStatement.close();



	}

}
