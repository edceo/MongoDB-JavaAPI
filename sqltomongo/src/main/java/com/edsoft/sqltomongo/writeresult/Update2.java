package com.edsoft.sqltomongo.writeresult;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Update2 {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		WriteResult result = sqlUpdate2(collection);
		System.out.println(result);
	}

	/**
	 * SQL: UPDATE employee SET salary = salary + 1000 WHERE fname = 'Justin'
	 * @param collection
	 * @return
	 */
	private static WriteResult sqlUpdate2(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("fname", "Justin");
		DBObject upd = new BasicDBObject();
		upd.put("$inc", new BasicDBObject("salary", 1000));
		return collection.update(obj, upd);
	}
}
