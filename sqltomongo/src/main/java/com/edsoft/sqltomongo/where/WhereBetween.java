package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class WhereBetween {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		DBCursor cursor = sqlWhereBetween(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	private static DBCursor sqlWhereBetween(DBCollection col) {
		// TODO Auto-generated method stub
		return null;
	}
}
