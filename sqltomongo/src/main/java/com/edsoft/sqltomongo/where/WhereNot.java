package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereNot {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");

		DBCursor cursor = sqlWhereNot(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	private static DBCursor sqlWhereNot(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("sex", new BasicDBObject("$ne", "M"));
		return col.find(obj);
	}
}
