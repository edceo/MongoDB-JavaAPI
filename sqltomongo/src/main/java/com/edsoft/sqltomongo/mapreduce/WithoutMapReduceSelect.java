package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WithoutMapReduceSelect {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");

		DBCursor cursor = sqlWhereTwoSelect(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * @param col
	 * SQL: Select fname, address FROM employee Where salary > 40000
	 * @return
	 */
	private static DBCursor sqlWhereTwoSelect(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		DBObject fields = new BasicDBObject();
		fields.put("ssn", 1);
		fields.put("salary", 1);
		fields.put("_id", 0);
		return col.find(obj, fields);
	}
}
