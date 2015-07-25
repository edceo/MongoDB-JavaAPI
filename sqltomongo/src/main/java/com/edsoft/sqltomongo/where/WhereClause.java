package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereClause {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");

		DBCursor cursor = sqlWhere(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT * FROM `employee` WHERE `fname` = 'Justin'
	 * 
	 * db.employee.find({"fname":"Justin"})
	 * 
	 * @param col
	 * @return
	 */
	private static DBCursor sqlWhere(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject name = new BasicDBObject("fname", "Justin");
		return col.find(name);
	}

}
