package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class WithoutMapReduceCount {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		long count = sqlCount(collection);
		System.out.println(count);
	}

	/**
	 * SQL: SELECT COUNT(*) FROM employee 
	 * @param collection
	 * @return
	 */
	private static long sqlCount(DBCollection collection) {
		// TODO Auto-generated method stub
		return collection.count();
	}
}
