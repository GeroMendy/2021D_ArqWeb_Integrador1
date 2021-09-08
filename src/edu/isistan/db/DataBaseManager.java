package edu.isistan.db;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.*;

import java.sql.ResultSet;

import edu.isistan.db.query.*;

public abstract class DataBaseManager {


	protected String jdbcDriver;
	protected String uri;
	public Connection connection;

	protected final String TABLE_NAME_CLIENTE = "Cliente";
	protected final String PRIMARY_KEY_NAME_CLIENTE = "idCliente";
	protected final String TABLE_NAME_FACTURA = "Factura";
	protected final String PRIMARY_KEY_NAME_FACTURA = "idFactura";
	protected final String TABLE_NAME_FACTURA_PRODUCTO = "Factura_Producto";
	protected final String TABLE_NAME_PRODUCTO = "Producto";
	protected final String PRIMARY_KEY_NAME_PRODUCTO = "idProducto";


	public DataBaseManager(String jdbcDriver, String uri) {
		this.jdbcDriver = jdbcDriver;
		this.uri = uri;


		try {
			Class.forName(jdbcDriver).getDeclaredConstructor().newInstance();
			openConnection();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.exit(1);
		}
	}

	public void importTableCliente(String urlToCSVFileCliente) {

		Iterable<CSVRecord> records = readCSVFile(urlToCSVFileCliente);
		if(records==null) return;

		String queryCreateTable = 
				"CREATE TABLE CLIENTE("
						+ "idCliente INT NOT NULL,"
						+ "nombre VARCHAR(500) NOT NULL,"
						+ "email VARCHAR(255),"
						+ "PRIMARY KEY (idCliente)"
						+ ")";

		try {
			directlySendQuery(queryCreateTable);

			for(CSVRecord record : records) {

				int idCliente = toInteger(record.get("idCliente"));
				String nombre = record.get("nombre");
				String email = record.get("email");

				insertCliente(idCliente,nombre,email);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException ex){
			ex.printStackTrace();
		}


	}

	public void importTableProducto(String urlToCSVFileCliente) {

		Iterable<CSVRecord> records = readCSVFile(urlToCSVFileCliente);
		if(records==null) return;

		String queryCreateTable = 
				"CREATE TABLE PRODUCTO("
						+ "idProducto INT NOT NULL,"
						+ "nombre VARCHAR(45) NOT NULL,"
						+ "valor float NOT NULL,"
						+ "PRIMARY KEY (idProducto)"
						+ ")";

		try {
			directlySendQuery(queryCreateTable);

			for(CSVRecord record : records) {

				int idProducto = toInteger(record.get("idProducto"));
				String nombre = record.get("nombre");
				float valor = toFloat(record.get("valor"));

				insertProducto(idProducto,nombre,valor);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException ex){
			ex.printStackTrace();
		}


	}

	public void importTableFactura(String urlToCSVFileCliente) {

		Iterable<CSVRecord> records = readCSVFile(urlToCSVFileCliente);
		if(records==null) return;

		String queryCreateTable = 
				"CREATE TABLE FACTURA("
						+ "idFactura INT NOT NULL,"
						+ "idCliente INT NOT NULL,"
						+ "PRIMARY KEY (idFactura),"
						+ "FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente)"
						+ ")";

		try {
			directlySendQuery(queryCreateTable);

			for(CSVRecord record : records) {

				int idFactura = toInteger(record.get("idFactura"));
				int idCliente = toInteger(record.get("idCliente"));

				insertFactura(idFactura,idCliente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException ex){
			ex.printStackTrace();
		}

	}

	public void importTableFacturaProducto(String urlToCSVFileCliente) {

		Iterable<CSVRecord> records = readCSVFile(urlToCSVFileCliente);
		if(records==null) return;

		String queryCreateTable = 
				"CREATE TABLE FACTURA_PRODUCTO("
						+ "idFactura INT NOT NULL,"
						+ "idProducto INT NOT NULL,"
						+ "cantidad INT NOT NULL,"
						+ "FOREIGN KEY (idProducto) REFERENCES PRODUCTO(idProducto),"
						+ "FOREIGN KEY (idFactura) REFERENCES FACTURA(idFactura),"
						+ "PRIMARY KEY (idFactura,idProducto)"
						+ ")";

		try {
			directlySendQuery(queryCreateTable);

			for(CSVRecord record : records) {

				int idFactura = toInteger(record.get("idFactura"));
				int idProducto = toInteger(record.get("idProducto"));
				int cantidad = toInteger(record.get("cantidad"));
				insertFacturaProducto(idFactura,idProducto,cantidad);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException ex){
			ex.printStackTrace();
		}


	}

	private void directlySendQuery(String query)throws SQLException {

		//		openConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.execute();
		closeConnection();
	}

	private int toInteger(String integerWithinString) throws NumberFormatException{
		return Integer.parseInt(integerWithinString);
	}
	private float toFloat(String floatWithinString) throws NumberFormatException{
		return Float.parseFloat(floatWithinString);
	}

	private Iterable<CSVRecord> readCSVFile(String urlToCSVFileCliente){
		Iterable<CSVRecord> records = null;
		Reader reader;
		try {
			reader = new FileReader(urlToCSVFileCliente);

			records = CSVFormat.DEFAULT.withHeader().parse(reader);	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}

	public void dropTable(String tableName) {
		String query ="DROP TABLE "+tableName+" IF EXISTS";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dropAllTables() {

		//		dropTable(this.TABLE_NAME_CLIENTE);
		//		dropTable(this.TABLE_NAME_PRODUCTO);
		//		dropTable(this.TABLE_NAME_FACTURA);
		//		dropTable(this.TABLE_NAME_FACTURA_PRODUCTO);


		String query ="DROP TABLE CLIENTE";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}
		query ="DROP TABLE PRODUCTO";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}
		query ="DROP TABLE FACTURA";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}
		query ="DROP TABLE FACTURA_PRODUCTO";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}
		query ="DROP TABLE FACTURAPRODUCTO";
		try {
			directlySendQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

	}

	protected abstract void openConnection() throws SQLException;

	protected void closeConnection() throws SQLException {
		if(connection==null)return;
		connection.commit();
		//		connection.close();
	}

	private void sendQuerySafely(QueryWithoutResponse query) {

		try {

			//			openConnection();

			query.sendQuery(connection);

			closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private ResultSet sendSelectAllQuerySafely(QueryGenericSelect query) {

		ResultSet resultSet = null;

		try {

			//			openConnection();

			resultSet = query.selectAll(connection);

			closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;

	}
	private ResultSet sendIndividualSelectQuerySafely(QueryWithResponse query) {

		ResultSet resultSet = null;

		try {

			//			openConnection();

			resultSet = query.select(connection);

			closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;

	}

	//Inserts

	public void insertCliente(int idCliente, String nombre, String email) {

		QueryWithoutResponse query = new QueryInsertCliente(idCliente,nombre,email);

		sendQuerySafely(query);

	}

	public void insertCliente(int idCliente, String nombre) {

		String email = null;

		QueryWithoutResponse query = new QueryInsertCliente(idCliente,nombre,email);

		sendQuerySafely(query);

	}

	public void insertFactura(int idFactura, int idCliente) {

		QueryWithoutResponse query = new QueryInsertFactura(idFactura,idCliente);

		sendQuerySafely(query);

	}

	public void insertFacturaProducto(int idFactura, int idProducto, int cantidad) {

		QueryWithoutResponse query = new QueryInsertFacturaProducto(idFactura, idProducto, cantidad);

		sendQuerySafely(query);

	}

	public void insertProducto(int idProducto, String nombre, float valor) {

		QueryWithoutResponse query = new QueryInsertProducto(idProducto, nombre, valor);

		sendQuerySafely(query);

	}

	//Selects (byId and selectAll)

	public ResultSet selectByIdCliente(int idCliente) {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_CLIENTE,PRIMARY_KEY_NAME_CLIENTE,idCliente);

		return sendIndividualSelectQuerySafely(query);
	}

	public ResultSet selectAllCliente() {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_CLIENTE);

		return sendSelectAllQuerySafely(query);
	}

	public ResultSet selectByIdFactura(int idFactura) {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_FACTURA,PRIMARY_KEY_NAME_FACTURA,idFactura);

		return sendIndividualSelectQuerySafely(query);
	}

	public ResultSet selectAllFactura() {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_FACTURA);

		return sendSelectAllQuerySafely(query);
	}
	public ResultSet selectByIdFacturaProducto(int idFactura, int idProducto) {

		QueryWithResponse query = new QuerySelectByDoubleKey(TABLE_NAME_FACTURA_PRODUCTO,PRIMARY_KEY_NAME_FACTURA, idFactura,PRIMARY_KEY_NAME_PRODUCTO, idProducto);

		return sendIndividualSelectQuerySafely(query);
	}
	public ResultSet selectAllFacturaProducto() {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_FACTURA_PRODUCTO);

		return sendSelectAllQuerySafely(query);
	}
	public ResultSet selectByIdProducto(int idProducto) {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_PRODUCTO,PRIMARY_KEY_NAME_PRODUCTO, idProducto);

		return sendIndividualSelectQuerySafely(query);
	}
	public ResultSet selectAllProducto() {

		QueryGenericSelect query = new QueryGenericSelect(TABLE_NAME_PRODUCTO);

		return sendSelectAllQuerySafely(query);
	}

	//3)Best selling product.

	public ResultSet selectBestSellingProduct() {
		QueryWithResponse query = new QuerySelectBestSellingProduct();
		ResultSet resultSet = sendIndividualSelectQuerySafely(query);
		return resultSet;
	}
	//4)Client who payed the more.
	public ResultSet selectBestCliente() {
		QueryWithResponse query = new QuerySelectBestCliente();
		ResultSet resultSet = sendIndividualSelectQuerySafely(query);
		return resultSet;
	}

}















