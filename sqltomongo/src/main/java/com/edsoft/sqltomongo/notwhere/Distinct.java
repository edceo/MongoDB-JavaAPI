package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Distinct {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		List<?> list = sqlDistinct(collection);
		
	}

	/**
	 * SQL: SELECT DISTINCT fname FROM employee
	 * @param collection
	 * @return
	 */
	private static List<?> sqlDistinct(DBCollection collection) {
		// TODO Auto-generated method stub
		return collection.distinct("fname");
	}
}
