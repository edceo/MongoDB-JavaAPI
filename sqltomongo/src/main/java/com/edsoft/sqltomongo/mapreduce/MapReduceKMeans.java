package com.edsoft.sqltomongo.mapreduce;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.encog.ml.MLCluster;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.kmeans.KMeansClustering;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MapReduceKMeans {
	public static final double[][] DATA1 = { { 28, 15, 22 }, { 16, 15, 32 },
			{ 32, 20, 44 }, { 1, 2, 3 }, { 3, 2, 1 } };
	private static double[][] DATA = null;

	public static final double[][] DATA2 = { { -72.410953, 42.275103 },
			{ -72.833309, 42.381670 }, { -72.108354, 42.409698 },
			{ -72.936114, 42.182949 }, { -72.626193, 42.202007 }, { -72.844092,42.466234 }, { -72.654245, 42.324662 }, { -72.588735, 42.102900} };

	/**
	 * The main method.
	 *
	 * @param args
	 *            Arguments are not used.
	 */
	public static void main(final String args[]) throws UnknownHostException {

		final BasicMLDataSet set = new BasicMLDataSet();

		 initData();

		for (final double[] element : MapReduceKMeans.DATA) {
			set.add(new BasicMLData(element));
		}

		final KMeansClustering kmeans = new KMeansClustering(5, set);

		kmeans.iteration(1000);
		// System.out.println("Final WCSS: " + kmeans.);

		// Display the cluster
		int i = 1;
		for (final MLCluster cluster : kmeans.getClusters()) {
			System.out.println("*** Cluster " + (i++) + " ***");
			final MLDataSet ds = cluster.createDataSet();
			final MLDataPair pair = BasicMLDataPair.createPair(
					ds.getInputSize(), ds.getIdealSize());
			for (int j = 0; j < ds.getRecordCount(); j++) {
				ds.getRecord(j, pair);
				System.out.println(Arrays.toString(pair.getInputArray()));
			}
		}
		
		/*for(int i = 0; i < MapReduceKMeans.DATA2.length; i++) {
			System.out.println(Arrays.toString(MapReduceKMeans.DATA2[i]));
		}*/
	}

	private static void initData() throws UnknownHostException {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		DB db = client.getDB("local");
		DBCollection col = db.getCollection("zips");
		DBCursor cursor = findLoc(col);
		MapReduceKMeans.DATA = new double[15][2];
		int k = 0;
		for(k = 0; k < 15; k++) {

			BasicDBList list = (BasicDBList) cursor.next().get("loc");
			// System.out.println((double)list.get(0));
			MapReduceKMeans.DATA[k][0] = (Double) list.get(0);
			MapReduceKMeans.DATA[k][1] = (Double) list.get(1);

		}
	}

	private static DBCursor findLoc(DBCollection col) {
		DBObject obj = new BasicDBObject();
		DBObject field = new BasicDBObject();
		field.put("_id", 0);
		field.put("loc", 1);
		return col.find(obj, field);
	}
}
