package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereRegex {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		DBCursor cursor = sqlWhereRegex(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	private static DBCursor sqlWhereRegex(DBCollection col) {
		// FIXME Auto-generated method stub
		DBObject regex = new BasicDBObject();
		regex.put("name", java.util.regex.Pattern.compile("arl"));
		return col.find(regex);
	}
}
