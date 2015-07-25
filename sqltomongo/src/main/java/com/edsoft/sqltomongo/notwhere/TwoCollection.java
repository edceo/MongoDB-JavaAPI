package com.edsoft.sqltomongo.notwhere;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TwoCollection {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collectionEmp = db.getCollection("employee");
		DBCollection collectionDept = db.getCollection("department");

		DBCursor cursor = sqlTwoCollection(collectionEmp, collectionDept);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * SQL: SELECT fname FROM `employee`, `department` WHERE dnumber = 6 and dno = dnumber
	 * 
	 * @param collectionEmp
	 * @param collectionDept
	 * @return
	 */
	private static DBCursor sqlTwoCollection(DBCollection collectionEmp,
			DBCollection collectionDept) {
		// TODO Auto-generated method stub
		DBObject fname = new BasicDBObject();
		DBObject fields = new BasicDBObject();
		fields.put("_id", 0);
		fields.put("fname", 1);
		DBObject dept = collectionDept.findOne(new BasicDBObject()
				.append("dnumber", 6));
		fname.put("dno", dept.get("_id"));
		return collectionEmp.find(fname, fields);
	}

}
