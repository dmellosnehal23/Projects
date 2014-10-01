package dm.ex1;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mysql.jdbc.PreparedStatement;

public class MongoToSQLStatsCollector implements Runnable {

	MongoClient mongo;
	Connection con;

	String vmNames[] = { "Team07_VM1", "Team07_VM2" };
	String URL = "jdbc:mysql://localhost:3306/sample";
	String username = "root";
	String password = "root";
	long waitTime = 0;
	String tableName = "";

	public MongoToSQLStatsCollector(long time, String tableName) throws UnknownHostException, SQLException,
			IllegalAccessException, InstantiationException,
			ClassNotFoundException {
		mongo = new MongoClient("127.0.0.1", 27017);
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		con = (Connection) DriverManager.getConnection(URL, username, password);

		waitTime = time * 60 * 1000; // min to millsec
		this.tableName = tableName;
	}

	public BasicDBObject constructQuery(String name, ObjectId lastOid) {

		BasicDBObject query = null;
		if (lastOid != null) {
			BasicDBObject vmname = new BasicDBObject("vmname", name);
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("_id", new BasicDBObject("$gt", lastOid));

			query = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(vmname);
			obj.add(gtQuery);
			query.put("$and", obj);
		} else {
			query = new BasicDBObject("vmname", name);
		}

		System.out.println(query.toString());
		return query;
	}

	public void run() {
		PreparedStatement pt = null;
		try {
			System.out.println("Five Minutes Called");
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar cal = null;
			DB db = mongo.getDB("sample");
			Map<String, ObjectId> lastOidLookup = new HashMap<String, ObjectId>();

			// @sneh:current_timestamp =0; hint - convert to unix timestamp.

			while (true) {
				cal = Calendar.getInstance(TimeZone.getDefault());
				System.out.println("Five Minutes: " + cal.getTime());
				cal.add(Calendar.SECOND, -300);
				//System.out.println("Five Minutes 2: " + cal.getTime());

				Date dateGMT = cal.getTime();

				for (int n = 0; n < vmNames.length; n++) {
					String vmName = vmNames[n];

					DBCollection coll = db.getCollection("metrics3");

					BasicDBObject query = null;
					if (lastOidLookup.containsKey(vmName)) {
						query = constructQuery(vmName,
								lastOidLookup.get(vmName));
					} else {
						query = constructQuery(vmName, null);
					}

					DBCursor cursor = coll.find(query);

					if (cursor.count() > 0) {
						//System.out.println("Cursor: " + cursor);
						// DBCursor cursor1 = coll.findOne();

						System.out.println(query.toString());
						System.out.println("Five Minutes Cursor Count: "
								+ cursor.count());

						double cpu = 0.0f;
						double ioread = 0.0f;
						double iowrite = 0.0f;
						double network = 0.0f;
						int mem = 0;

						while (cursor.hasNext()) {
							DBObject getdata = cursor.next();

							cpu += (Double) getdata.get("cpu_usage_perc");
							mem += (Integer) getdata.get("mem_used_kb");
							ioread += (Double) getdata.get("io_read_byte");
							iowrite += (Double) getdata.get("io_write_byte");
							network += (Double) getdata.get("nw_tx_kbps");

							// add object id to looup
							lastOidLookup.put(vmName,
									(ObjectId) getdata.get("_id"));
							//System.out.println(lastOidLookup.get(vmName).toString());
						}

						System.out.println(cpu + " " + mem + " " + ioread + " "
								+ iowrite + " " + network);
						cal = null;
						cal = Calendar.getInstance(TimeZone.getDefault());
						String sql1 = "INSERT into "+this.tableName+ "(vmname,cpu,mem,ioread,iowrite,network,timeinserted) values(?,?,?,?,?,?,?)";
						
						pt = (PreparedStatement) con.prepareStatement(sql1);
						if (cursor.count() > 0) {
							pt.setString(1, vmName);
							pt.setDouble(2, cpu / cursor.count());
							pt.setFloat(3, mem / cursor.count());
							pt.setDouble(4, ioread / cursor.count());
							pt.setDouble(5, iowrite / cursor.count());
							pt.setDouble(6, network / cursor.count());
							pt.setTimestamp(7,
									new Timestamp(new Date().getTime()));
							pt.addBatch();
						}

						pt.executeBatch();
						System.out.println(sql1);
						System.out.println("Record Inserted");
					}

				}

				// wait
				Thread.sleep(waitTime);
			}// while

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		try {
			Thread five = new Thread(new MongoToSQLStatsCollector(5, "FiveMinutes"));
			five.start();
			
			Thread hour = new Thread(new MongoToSQLStatsCollector(60, "Hour"));
			hour.start();
			
			Thread twentyFourHour = new Thread(new MongoToSQLStatsCollector(1440, "TwentyFourHour")); // 24*60
			twentyFourHour.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
