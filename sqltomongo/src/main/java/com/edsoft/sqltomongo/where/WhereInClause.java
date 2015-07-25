package com.edsoft.sqltomongo.where;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WhereInClause {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		DBCollection colTwo = db.getCollection("dept_locations");

		//DBCursor cursor = sqlWhereIn(col);
		DBCursor cursor2 = sqlWhereInSelect(col, colTwo);
		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
	}

	private static DBCursor sqlWhereInSelect(DBCollection col, DBCollection colTwo) {
		// TODO Auto-generated method stub
		
		return col.find()/*.sort(new BasicDBObject("fname", 1))*/;
	}

	/**
	 * SQL: SELECT * FROM `employee` WHERE `minit` in 'D', 'E'
	 * db.employee.find({"minit":{$in{D,E}})
	 * 
	 * @param col
	 * @return
	 */
	private static DBCursor sqlWhereIn(DBCollection col) {
		// TODO Auto-generated method stub
		DBObject minit = new BasicDBObject();
		BasicDBList list = new BasicDBList();
		list.add("D");
		list.add("E");
		minit.put("minit", new BasicDBObject("$in", list));
		return col.find(minit);
	}
}
