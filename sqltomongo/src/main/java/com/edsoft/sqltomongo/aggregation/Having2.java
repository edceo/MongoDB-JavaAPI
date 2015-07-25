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

public class Having2 {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection collection = db.getCollection("employee");

		AggregationOutput output = sqlHaving2(collection);
		Iterator<DBObject> iter = output.results().iterator();
		while (iter.hasNext()) {
			DBObject dbObject = (DBObject) iter.next();
			System.out.println(dbObject);
		}
	}

	/**
	 * SQL: SELECT ssn, SUM(salary) FROM employee WHERE sex = "M" GROUP BY ssn HAVING salary > 60000 ORDER BY ssn
	 * 
	 * @param collection
	 * @return
	 */
	private static AggregationOutput sqlHaving2(DBCollection collection) {
		// TODO Auto-generated method stub
		DBObject where = new BasicDBObject();
		where.put("$match", new BasicDBObject("sex", "M"));
		DBObject groupFields = new BasicDBObject();
		groupFields.put("_id", "$ssn");
		groupFields.put("a", new BasicDBObject("$sum", "$salary"));
		DBObject group = new BasicDBObject("$group", groupFields);
		DBObject having = new BasicDBObject("$match", new BasicDBObject("a", new BasicDBObject("$gt", 60000)));
		DBObject sort = new BasicDBObject();
		sort.put("$sort", new BasicDBObject("_id", 1));
		return collection.aggregate(Arrays.asList(where, group, having, sort));
	}
}
