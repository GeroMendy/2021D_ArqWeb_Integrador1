package edu.isistan.db.testing;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.isistan.db.*;

public class TestingDerby {

	public static void main(String[]args) {
		
	
		//Gets the path for the project folder. Java has no relative path function 
		String pathToFolderCSV = new File("").getAbsolutePath()+"/src/csv_files/";
		
		String pathToClienteCSV = pathToFolderCSV+ "clientes.csv";
		String pathToProductoCSV = pathToFolderCSV+"productos.csv";
		String pathToFacturaCSV = pathToFolderCSV+"facturas.csv";
		String pathToFacturaProductoCSV = pathToFolderCSV+"facturas-productos.csv";
		
		DataBaseDerbyManager derby = new DataBaseDerbyManager();
		
		derby.dropAllTables();
		
		derby.importTableCliente(pathToClienteCSV);
		derby.importTableProducto(pathToProductoCSV);
		derby.importTableFactura(pathToFacturaCSV);
		derby.importTableFacturaProducto(pathToFacturaProductoCSV);
		
		ResultSet resultsCliente = derby.selectAllCliente();
		ResultSet resultsProducto = derby.selectAllProducto();
		ResultSet resultsFactura = derby.selectAllFactura();
		ResultSet resultsFacturaProducto = derby.selectAllFacturaProducto();
		
		try {
			System.out.println("\\nClientes: \\n");
			while(resultsCliente.next()) {
				System.out.println(clienteToString(resultsCliente));
			}
			System.out.println("\\n\\nFacturas: \\n");
			while(resultsProducto.next()) {
				System.out.println(productoToString(resultsProducto));
			}
			System.out.println("\\n\\nProductos: \\n");
			while(resultsFactura.next()) {
				System.out.println(facturaToString(resultsFactura));
			}
			System.out.println("\\n\\nFacturas-Productos: \\n");
			while(resultsFacturaProducto.next()) {
				System.out.println(facturaProductoToString(resultsFacturaProducto));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		derby.dropAllTables();
//		
//		
//		try {
//			derby.connection.commit();
//			derby.connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public static String clienteToString(ResultSet cliente) {
		
		int idCliente = -1;
		String nombre = "[Failed to read name]";
		String email = "[Failed to read email]";
		try {
			idCliente = cliente.getInt(1);
			nombre = cliente.getString(2);
			String email_aux = cliente.getString(3);
			if(email_aux ==null) {
				email="[No Email Registered]";
			}else {
			email = email_aux;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCliente+" - "+nombre+" - "+email;
	}
	public static String facturaToString(ResultSet factura) {
		
		int idFactura = -1;
		int idCliente = -1;
		try {
			idFactura = factura.getInt(1);
			idCliente = factura.getInt(2);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idFactura+" - "+idCliente;
	}
	public static String productoToString(ResultSet producto) {
		
		int idProducto = -1;
		String nombre = "[Failed to read name]";
		float valor = -1;
		try {
			idProducto = producto.getInt(1);
			nombre = producto.getString(2);
			valor = producto.getFloat(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idProducto+" - "+nombre+" - "+valor;
	}
	public static String facturaProductoToString(ResultSet facturaProducto) {
		
		int idFactura = -1;
		int idProducto = -1;
		int cantidad = -1;
		try {
			idFactura = facturaProducto.getInt(1);
			idProducto = facturaProducto.getInt(2);
			cantidad = facturaProducto.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idFactura+" - "+idProducto+" - "+cantidad;
	}






}
