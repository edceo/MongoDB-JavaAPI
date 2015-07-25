package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Index {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		sqlIndex(collection);
	}

	/**
	 * SQL: CREATE INDEX ON employee (fname ASC)
	 * @param collection
	 */
	private static void sqlIndex(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("fname", 1);
		collection.ensureIndex(obj);
	}
}
