package com.edsoft.sqltomongo.mongoimport;

import java.net.UnknownHostException;

public class MongoImporter {
	public static void main(String[] args) throws UnknownHostException {

		ImportDB("C:/Program Files/MongoDB/Server/3.0/bin/mongoimport.exe",
				"C:/Users//edsoft//Desktop//couchbase.json");

	}

	public static void ImportDB(String importPath, String filePath) {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		String command = importPath + " --db local --collection couchbasecol "
				+ "--file " + filePath;
		try {
			p = r.exec(command);
			System.out.println(p.toString());

		} catch (Exception e) {
			System.out.println("Error executing " + command + e.toString());
		}
	}
}
