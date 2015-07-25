package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class WithoutMapReduceAverage {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		AggregationOutput output = sqlGroup(collection);
		Iterator<DBObject> iter = output.results().iterator();
		while (iter.hasNext()) {
			DBObject dbObject = (DBObject) iter.next();
			System.out.println(dbObject);
		}
	}

	/**
	 * SQL: Select sex, avg(salary) from employee group by sex, salary
	 * @param collection
	 * @return
	 */
	private static AggregationOutput sqlGroup(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject having = new BasicDBObject();
		having.put("$match", new BasicDBObject("salary", new BasicDBObject("$gt", 50000)));
		DBObject fields = new BasicDBObject();
		fields.put("salary", 1);
		fields.put("_id", 0);
		DBObject project = new BasicDBObject("$project", fields);

		DBObject groupFiels = new BasicDBObject();
		groupFiels.put("_id", null);
		groupFiels.put("average", new BasicDBObject("$avg", "$salary"));
		DBObject group = new BasicDBObject("$group", groupFiels);
		List<DBObject> list = Arrays.asList(having, project, group);
		return collection.aggregate(list);
	}

}
