package edu.isistan.db.main;

import edu.isistan.db.DataBaseManager;
import edu.isistan.db.DataBaseMySQLManager;

public class DropAllTables {

	public static void main(String[] args) {


		DataBaseManager dataBaseManager = new DataBaseMySQLManager();
		
		dataBaseManager.dropAllTables();

	}

}
