package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WithoutMapReduceGtLt {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		
		int cursor = sqlWhereLtGt(col);
		int cursor2 = sqlWhereLtGt2(col);
		
		System.out.println("Major " + cursor + "\n" + "Minor " + cursor2);
	}

	/**
	 * SQL: SELECT * FROM `employee` WHERE 'salary' > 50000
	 * db.employee.find({"salary"
	 * @param col
	 * @return
	 */
	private static int sqlWhereLtGt(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("salary", new BasicDBObject("$gt", 50000));
		return col.find(obj).count();
	}
	
	private static int sqlWhereLtGt2(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put("salary", new BasicDBObject("$lt", 50000));
		return col.find(obj).count();
	}
	
	
}
