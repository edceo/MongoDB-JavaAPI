package com.edsoft.sqltomongo.writeresult;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Delete {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		WriteResult result = sqlDelete(collection);
		System.out.println(result);
	}

	/**
	 * SQL: DELETE FROM employee WHERE fname = 'Justin'
	 * @param collection
	 * @return
	 */
	private static WriteResult sqlDelete(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("fname", "Justin");
		return collection.remove(obj);
	}
}
