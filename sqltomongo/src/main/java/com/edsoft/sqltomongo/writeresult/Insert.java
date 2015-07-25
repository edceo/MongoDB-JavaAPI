package com.edsoft.sqltomongo.writeresult;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Insert {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		WriteResult result = sqlInsert(collection);
		System.out.println(result);
	}

	/**
	 * SQL: INSERT VALUE 
	 * @param collection
	 * @return
	 */
	private static WriteResult sqlInsert(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("name", "Yusuf");
		return collection.insert(obj);
	}
}
