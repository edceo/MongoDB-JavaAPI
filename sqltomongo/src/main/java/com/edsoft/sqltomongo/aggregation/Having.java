package com.edsoft.sqltomongo.aggregation;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Having {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		AggregationOutput output = sqlHaving(collection);
		Iterator<DBObject> iter = output.results().iterator();
		while (iter.hasNext()) {
			DBObject dbObject = (DBObject) iter.next();
			System.out.println(dbObject);
		}
	}

	private static AggregationOutput sqlHaving(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject having = new BasicDBObject();
		having.put("$match", new BasicDBObject("count", new BasicDBObject("$gt", 1)));
		

		
		
		DBObject groupFields = new BasicDBObject();
		groupFields.put("_id", "$dno");
		groupFields.put("count", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);
		
		
		return collection.aggregate(Arrays.asList(group, having));
	}
}
