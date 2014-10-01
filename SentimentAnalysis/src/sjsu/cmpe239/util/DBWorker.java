//##############################################################
//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class DBWorker implements Runnable {
	private MongoClient mongoClient = null;
	private DB db = null;
	private Map<String, Long> countResult = null;
	private DBCollection coll = null;
	private static String _id = "1111111";

	public DBWorker(){
		try{
			this.mongoClient = new MongoClient("localhost" , 27017);
			List<String> dbs = mongoClient.getDatabaseNames();
			System.out.println(dbs);	
			db = mongoClient.getDB("SentimentAnalysis");
			coll = db.createCollection("TwitterClassification", null); // the method getCollection() also does the same thing	
			
			countResult = Collections.synchronizedMap(new HashMap<String, Long>());
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public void run(){
		try{
			Long d = null, r = null;
			System.out.println("START");
			
			BasicDBObject oldDoc = new BasicDBObject("_id", DBWorker._id);
			DBObject result = this.coll.findOne(oldDoc);
			if(result == null || !((String)result.get("_id")).equals(DBWorker._id)) {
				oldDoc.append("democrat", d).append("republican", r);
				this.coll.insert(oldDoc);
			} 
			else System.out.println(result.toString());
			
			while(!Thread.interrupted()){
				if(d != this.countResult.get("democrat") || r != this.countResult.get("republican")){
					System.out.println("Before: "+d+"   "+r);
					d = this.countResult.get("democrat");
					r = this.countResult.get("republican");
					
					System.out.println("After: "+d+"   "+r);
					BasicDBObject newDoc = new BasicDBObject("_id", DBWorker._id).
															append("democrat", d).
															append("republican", r);
					this.coll.update(oldDoc, newDoc);
					oldDoc = newDoc;				
					Thread.sleep(1000);
				}
			}
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		} finally{
			this.coll = null;
			this.db = null;
			this.mongoClient.close();
		}
		System.out.println("END");
	}
	
	/**
	 * @param args
	 */
	
	public synchronized void updateCount(long democraticCount, long republicanCount){
		this.countResult.put("democrat", democraticCount);
		this.countResult.put("republican", republicanCount);
	}
	
	public DBObject getRecord(){
		BasicDBObject doc = new BasicDBObject("_id", DBWorker._id);
		DBObject result = this.coll.findOne(doc);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			DBWorker db = new DBWorker();
			Thread t = new Thread(db);
			t.start();
			db.updateCount(10, 20);
			Thread.sleep(5000);
			db.updateCount(15, 25);
			Thread.sleep(5000);
			t.interrupt();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
