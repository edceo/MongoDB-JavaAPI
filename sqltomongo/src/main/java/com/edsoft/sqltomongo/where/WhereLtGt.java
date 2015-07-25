package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereLtGt {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		
		DBCursor cursor = sqlWhereLtGt(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT * FROM `employee` WHERE 80000 > 'salary' > 40000
	 * db.employee.find({"salary"
	 * @param col
	 * @return
	 */
	private static DBCursor sqlWhereLtGt(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("salary", new BasicDBObject("$gt", 40000).append("$lt", 80000));
		return col.find(obj);
	}

}
