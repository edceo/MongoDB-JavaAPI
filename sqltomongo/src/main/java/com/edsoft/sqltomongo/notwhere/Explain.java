package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Explain {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		DBObject explain = sqlExplain(collection);
	}

	/**
	 * SQL: EXPLAIN SELECT * FROM employee WHERE fname = Justin
	 * @param collection
	 * @return
	 */
	private static DBObject sqlExplain(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("fname", "Justin");
		return collection.find(obj).explain();
	}
}
