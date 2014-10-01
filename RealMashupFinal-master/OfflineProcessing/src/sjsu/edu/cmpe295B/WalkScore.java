package sjsu.edu.cmpe295B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class WalkScore {
	private final String url = "http://api.walkscore.com/score?format=json&address={0}&lat={1}&lon={2}&wsapikey=615442d77e3e1c748f767fca8cb04ace";
	private HttpClient client;
	private HttpGet request;
	private String database ="RealEstate";
	//private String collection="WalkScore";
	private String collection="Trulia";
	
	private MongoClient mongoClient = null;
	private DB db = null;
	private DBCollection coll = null;
	
	public WalkScore() {
		try{
			client = HttpClientBuilder.create().build();
			this.mongoClient = new MongoClient("localhost", 27017);
			List<String> dbs = mongoClient.getDatabaseNames();
			System.out.println(dbs);
			db = mongoClient.getDB(this.database);
			System.out.println("DB = "+db);
			coll = db.createCollection(this.collection, null);
			System.out.println("coll = "+coll);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
   
	public void addToMongoDB(JSONObject childJSONObject) {
		BasicDBObject document = new BasicDBObject();
		//document.put("status", 1);
		//document.put("walkscore", 97);
		document.put("data", childJSONObject);
		coll.insert(document);
	}

	public void process() {
		
		// MessageFormat msgFormat = MessageFormat.format(url,
		// "1119 8th Avenue Seattle WA 98101","47.6085","-122.3295");
		try {
			request = new HttpGet(MessageFormat.format(url,
					URLEncoder.encode("1119 8th Avenue Seattle WA 98101", "ISO-8859-1"), "47.6085", "-122.3295"));
			
			System.out.println("URL :" + request.getURI().toString());
			HttpResponse response = client.execute(request);
			System.out.println("Code = "+response.getStatusLine().getStatusCode());
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			StringBuffer sb = new StringBuffer();
			String line = "";
			
			while((line = bfr.readLine()) != null) {
				sb.append(line);
			}
			
			System.out.println("StringBuffer :"+sb.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String agrgs[]) {
		System.out.println("Hello World");
		WalkScore ws = new WalkScore();
		//ws.process();
		//ws.addToMongoDB();
	}

}
