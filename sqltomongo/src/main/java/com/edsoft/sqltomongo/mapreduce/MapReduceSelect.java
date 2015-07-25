package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class MapReduceSelect {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("employee");
		
		/*DBCursor cursor = col.find();
		while(cursor.hasNext()) {
			System.out.println(cursor.next().get("salary"));
		}*///Departman
		
		String map = "function() {"
				+ ""//Burada iþlem yapýlmýyor
				+ "emit(this.dno,{a: this.salary});}";
		
		 String reduce = "function(key, values) { "
                 + "var sum = 0; "
                 + "values.forEach(function(doc) { "
                 + "sum += doc.a;}); "
                 + "return sum;} ";        

		
		MapReduceCommand cmd = new MapReduceCommand(col, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
		MapReduceOutput out = col.mapReduce(cmd);
		for(DBObject o : out.results()) {
			/*String orderid =  (String) o.get("_id");
            Double cost = 0.0;
            Object obj = o.get("value");
            if (obj instanceof DBObject) {                 
                         DBObject new_name = (DBObject) obj;
                         cost = (Double) new_name.get("cost");
                   }
            else
            {
                   cost = (Double) o.get("value");
            }*/
          System.out.println(o);
		}
	}
}
