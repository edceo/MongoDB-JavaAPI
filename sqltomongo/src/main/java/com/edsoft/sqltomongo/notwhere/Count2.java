package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Count2 {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		long count = sqlCount(collection);
		System.out.println(count);
	}

	/**
	 * SQL: SELECT COUNT(salary) FROM employee
	 * 
	 * @param collection
	 * @return
	 */
	private static long sqlCount(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("salary", new BasicDBObject("exists", true));
		return collection.count(obj);
	}
}
