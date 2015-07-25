package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Sort {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		DBCursor cursor = sqlSort(collection);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT fname FROM `employee` WHERE `salary` > 25000 order by fname ASC
	 * @param collection
	 * @return
	 */
	private static DBCursor sqlSort(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		DBObject field = new BasicDBObject();
		field.put("_id", 0);
		field.put("fname", 1);
		obj.put("salary", new BasicDBObject("$gt", 25000));
		return collection.find(obj, field).sort(new BasicDBObject("fname", 1));
	}
}
