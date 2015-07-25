package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TwoSelectWhereClause {
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
		fields.put("fname", 1);
		fields.put("address", 1);
		fields.put("_id", 0);
		obj.put("salary", new BasicDBObject("$gt", 40000));
		return col.find(obj, fields);
	}
}
