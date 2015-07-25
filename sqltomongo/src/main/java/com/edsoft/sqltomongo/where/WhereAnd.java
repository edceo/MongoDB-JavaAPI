package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereAnd {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");

		DBCursor cursor = sqlWhereAnd(col);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT * FROM `employee` WHERE 'sex' = 'M' and 'salary' > 50000
	 * @param col
	 * @return
	 */
	private static DBCursor sqlWhereAnd(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject("sex", "M"));// Where sex = 'M'
		list.add(new BasicDBObject("salary", new BasicDBObject("$gt", 50000)));
		obj.put("$and", list);// Where sex = 'M' and salary > 50000
		return col.find(obj);
	}

}
