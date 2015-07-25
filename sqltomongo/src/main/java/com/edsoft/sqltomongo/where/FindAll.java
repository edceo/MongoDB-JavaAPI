package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class FindAll {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		DBCursor cursor = sqlAll(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT * FROM `employee`
	 * 
	 * db.employee.find()
	 * 
	 * @param col
	 * @return
	 */
	private static DBCursor sqlAll(DBCollection col) {
		// TODO Auto-generated method stub
		return col.find();
	}
}
