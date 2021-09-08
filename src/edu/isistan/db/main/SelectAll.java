package edu.isistan.db.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.isistan.db.DataBaseManager;
import edu.isistan.db.DataBaseMySQLManager;

public class SelectAll {

	public static void main(String[] args) {

		DataBaseManager dataBaseManager = new DataBaseMySQLManager();
		

		ResultSet resultsCliente = dataBaseManager.selectAllCliente();
		ResultSet resultsProducto = dataBaseManager.selectAllProducto();
		ResultSet resultsFactura = dataBaseManager.selectAllFactura();
		ResultSet resultsFacturaProducto = dataBaseManager.selectAllFacturaProducto();
		ResultSet resultsBestSellerProducto = dataBaseManager.selectBestSellingProduct();
		ResultSet resultsBestCliente = dataBaseManager.selectBestCliente();
		
		

		try {
			System.out.println("Clientes: ");
			while(resultsCliente.next()) {
				System.out.println(clienteToString(resultsCliente));
			}
			System.out.println("Facturas: ");
			while(resultsProducto.next()) {
				System.out.println(productoToString(resultsProducto));
			}
			System.out.println("Productos:");
			while(resultsFactura.next()) {
				System.out.println(facturaToString(resultsFactura));
			}
			System.out.println("Facturas-Productos: ");
			while(resultsFacturaProducto.next()) {
				System.out.println(facturaProductoToString(resultsFacturaProducto));
			}
			System.out.println("Best selling product:");
			while(resultsBestSellerProducto.next()) {
				System.out.println(productoToString(resultsBestSellerProducto));
			}
			System.out.println("Clientes en orden de ventas:");
			while(resultsBestCliente.next()) {
				System.out.println(clienteToString(resultsBestCliente));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
