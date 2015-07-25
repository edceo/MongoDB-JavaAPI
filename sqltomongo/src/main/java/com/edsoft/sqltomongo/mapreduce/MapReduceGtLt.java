package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class MapReduceGtLt {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		
		/*DBCursor cursor = col.find();
		while(cursor.hasNext()) {
			System.out.println(cursor.next().get("salary"));
		}*///Departman
		
		String map = "function() {var salaryId;"
				+ "if( this.salary < 50000){"
				+ "salaryId = 'Minor';}"
				+ "else{"
				+ "salaryId = 'Major';}"
				+ "emit(salaryId);}";
		
		String reduce = "function(key, values) {"
				+ "var sum = 0;"
				+ "values.forEach(function(){"
				+ "sum+=1;});"
				+ "return sum;}";
		
		MapReduceCommand cmd = new MapReduceCommand(col, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
		MapReduceOutput out = col.mapReduce(cmd);
		for(DBObject o : out.results()) {
			System.out.println(o.toString());
		}
	}
}
