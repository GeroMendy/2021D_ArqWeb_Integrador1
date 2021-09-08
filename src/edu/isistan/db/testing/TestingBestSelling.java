package edu.isistan.db.testing;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.isistan.db.DataBaseDerbyManager;

public class TestingBestSelling {

	public static void main(String[] args) {

		DataBaseDerbyManager derby = new DataBaseDerbyManager();
		
		ResultSet r = derby.selectBestSellingProduct();
		
		try {
			while(r.next()) {
				System.out.println(productoToString(r));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
