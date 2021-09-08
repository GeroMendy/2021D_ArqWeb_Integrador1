package edu.isistan.db.query;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class QueryInsertCliente implements QueryWithoutResponse {
	
	private int idCliente;
	private String nombre;
	private String email;

	private final String QUERY = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
	
	public QueryInsertCliente(int idCliente, String nombre, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}
	
	@Override
	public void sendQuery(Connection connection) throws SQLException {
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
		preparedStatement.setInt(1,idCliente);
		preparedStatement.setString(2,nombre);
		preparedStatement.setString(3,email);

		preparedStatement.executeUpdate();
		preparedStatement.close();
		
	}

}
