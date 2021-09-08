package edu.isistan.db.main;
import java.io.File;
import edu.isistan.db.*;

public class InitializeDataBase {

	public static void main(String[] args) {

		String pathToFolderCSV = new File("").getAbsolutePath()+"/src/csv_files/";
		
		String pathToClienteCSV = pathToFolderCSV+ "clientes.csv";
		String pathToProductoCSV = pathToFolderCSV+"productos.csv";
		String pathToFacturaCSV = pathToFolderCSV+"facturas.csv";
		String pathToFacturaProductoCSV = pathToFolderCSV+"facturas-productos.csv";
		
		DataBaseManager dataBaseManager = new DataBaseMySQLManager();
		
		dataBaseManager.importTableCliente(pathToClienteCSV);
		dataBaseManager.importTableProducto(pathToProductoCSV);
		dataBaseManager.importTableFactura(pathToFacturaCSV);
		dataBaseManager.importTableFacturaProducto(pathToFacturaProductoCSV);
	}

}
