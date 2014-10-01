package com.java.caching;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.csvreader.CsvWriter;

public class Scraper {

	private Document doc = null;
	private JSONArray jsonArray = null;
	private String strArray = null;
	private int count = 1;
	private String csvFile = null;
	private CsvWriter writer = null;

	public Scraper() {

		this.csvFile = "/Users/snehaldmello/ForSale.csv";
		// this.csvFile =
		// "/Users/snehal/Documents/MS-Software Engineering/SEM-IV/CMPE 295B/Data Sets/ForSale.csv";
		// this.csvFile =
		// "/Users/snehal/Documents/MS-Software Engineering/SEM-IV/CMPE 295B/Data Sets/SanJose_ForSale.csv";
		// this.csvFile =
		// "/Users/snehal/Documents/MS-Software Engineering/SEM-IV/CMPE 295B/Data Sets/SanJose_sold.csv";

		try {
			boolean alreadyExists = new File(csvFile).exists();
			this.writer = new CsvWriter(new FileWriter(this.csvFile, true), ',');

			System.out.println("alreadyExists : " + alreadyExists);

			// if the file didn't already exist then we need to write out the
			// header line
			if (!alreadyExists) {
				System.out.println("In createCSVFile !alreadyExists");
				this.writer.write("id");
				this.writer.write("address");
				this.writer.write("bathroom");
				this.writer.write("bedroom");
				this.writer.write("city");
				this.writer.write("formattedSqft");
				this.writer.write("lastSaleDate");
				this.writer.write("lastSalePrice");
				this.writer.write("latitude");
				this.writer.write("longitude");
				this.writer.write("stateCode");
				this.writer.write("typeDisplay");
				this.writer.write("price");
				this.writer.write("formattedTruliaEstimate");
				this.writer.write("zipCode");
				this.writer.write("neighborhood");
				this.writer.write("walkScore");
				this.writer.write("transitScore");
				this.writer.write("status");
				this.writer.write("county");
				this.writer.endRecord();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String fetchData(Integer pageNumber, String city) {
		try {
			// Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

			String url = "http://www.trulia.com/for_sale/" + city + "/"
					+ pageNumber + "_p";
			// String url = "http://www.trulia.com/for_sale/Sunnyvale,CA/"+
			// pageNumber + "_p";
			// String url = "http://www.trulia.com/sold/Sunnyvale,CA/"+
			// pageNumber + "_p";

			System.out.println(url);
			doc = Jsoup.connect(url).get();

			// String doc =
			// Jsoup.connect("http://www.trulia.com/for_sale/San_Francisco,CA/2_p").ignoreContentType(true).execute().body();
			// System.out.println(doc);

			if (doc == null) {
				System.out.println("TRULIA RETURNED NULL VALUES");
				return null;
			}

			Elements script = doc.select("script");
			// Element script1 = doc.select("script").get(53);
			// Element script2 = doc.select("script").get(58);

			// String s = script2.toString();
			String s = script.toString();
			// System.out.println(script2);

			// strArray = s.substring(s.indexOf("[{"), (s.indexOf("}],") + 2));
			strArray = s.substring(s.indexOf("data: [{"),
					(s.indexOf("}],") + 2)).trim();
			System.out.println("strArray1 = " + strArray);
			strArray = strArray.substring(6);
			System.out.println("strArray2 = " + strArray);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

		return strArray;
	}

	private void convertToJSONAndWrite(String strArray) {
		JSONObject childJSONObject = null;

		try {

			jsonArray = new JSONArray(strArray);

			for (int i = 0; i < jsonArray.length(); i++) {

				childJSONObject = jsonArray.getJSONObject(i);
				this.writeToCSVFile(childJSONObject);
			}
		} catch (Exception e) {
			System.out.println("Exception Caught: " + e.getMessage());
		}
	}

	private void writeToCSVFile(JSONObject childJSONObject) {
		System.out.println("In writeCSVFile");

		String address, bathroom, bedroom, city, formattedSqft, lastSaleDate, lastSalePrice, latitude, longitude, stateCode, typeDisplay, price, formattedTruliaEstimate, zipCode, id, neighborhood, walkScore, transitScore, status, county;

		try {
			// id(increment), version
			address = childJSONObject.getString("shortDescription");
			// String address = childJSONObject.getString("addressForDisplay");
			if (!childJSONObject.isNull("numBathrooms"))
				bathroom = "" + childJSONObject.getDouble("numBathrooms");
			else
				bathroom = null;

			if (!childJSONObject.isNull("numBedrooms"))
				bedroom = "" + childJSONObject.getDouble("numBedrooms");
			else
				bedroom = null;

			city = childJSONObject.getString("city");
			formattedSqft = childJSONObject.getString("formattedSqft");// finished_sq_ft
			lastSaleDate = childJSONObject.getString("lastSaleDate"); // last_sold_date
			lastSalePrice = childJSONObject.getString("lastSalePrice");// last_sold_price
			latitude = "" + childJSONObject.getDouble("latitude");
			longitude = "" + childJSONObject.getDouble("longitude");
			// lot_size_sq_ft
			stateCode = childJSONObject.getString("stateCode");
			// tax_assesment,tax_assesment_year,total_room
			typeDisplay = childJSONObject.getString("typeDisplay"); // use_code
			// year_built
			price = "" + childJSONObject.getLong("price");// zest_amt
			formattedTruliaEstimate = childJSONObject
					.getString("formattedTruliaEstimate"); // zestimate(Not
															// present)
			// zest_high, zest_low,zest_value_change
			zipCode = childJSONObject.getString("zipCode");
			id = "" + childJSONObject.getLong("id"); // zpid

			if (!childJSONObject.isNull("neighborhood"))
				neighborhood = childJSONObject.getString("neighborhood");
			else
				neighborhood = null;

			if (!childJSONObject.isNull("walkScore"))
				walkScore = "" + childJSONObject.getInt("walkScore");
			else
				walkScore = null;

			if (!childJSONObject.isNull("transitScore"))
				transitScore = "" + childJSONObject.getInt("transitScore");
			else
				transitScore = null;

			status = childJSONObject.getString("status"); // Eg: for sale
			county = childJSONObject.getString("county");

			// System.out.println("formattedTruliaEstimate : "+formattedTruliaEstimate);

			// write out a few records
			this.writer.write("" + (this.count)++);
			this.writer.write(address);
			this.writer.write(bathroom);
			this.writer.write(bedroom);
			this.writer.write(city);
			this.writer.write(formattedSqft);
			this.writer.write(lastSaleDate);
			this.writer.write(lastSalePrice);
			this.writer.write(latitude);
			this.writer.write(longitude);
			this.writer.write(stateCode);
			this.writer.write(typeDisplay);
			this.writer.write(price);
			this.writer.write(formattedTruliaEstimate);
			this.writer.write(zipCode);
			this.writer.write(neighborhood);
			this.writer.write(walkScore);
			this.writer.write(transitScore);
			this.writer.write(status);
			this.writer.write(county);

			this.writer.endRecord();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			System.out.println(childJSONObject.toString());
		}
	}

	private void closeWriter() {
		this.writer.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scraper ph = new Scraper();
		// WalkScore ws = new WalkScore();

		String[] city = new String[] { "Alameda,CA", "Belmont,CA",
				"Brisbane,CA", "Campbell,CA", "Daly_City,CA",
				"Hillsborough,CA", "Pleasanton,CA", "Livermore,CA",
				"Millbrae,CA", "Moss_Beach,CA", "Pacifica,CA",
				"East_Palo_Alto,CA", "Menlo_Park,CA", "Half_Moon_Bay,CA",
				"El_Granada,CA", "La_Honda,CA", "Mountain_House,CA",
				"Tracy,CA", "Redwood_City,CA", "San_Carlos,CA", "San_Ramon,CA",
				"South_San_Francisco,CA", "Sunnyvale,CA", "San_Francisco,CA",
				"San_Jose,CA", "San_Mateo,CA", "San_Bruno,CA",
				"Santa_Clara,CA", "Santa_Cruz,CA", "Scotts_Valley,CA",
				"Felton,CA", "Foster_City,CA", "Fremont,CA",
				"Mountain_View,CA", "Oakland,CA" };
		for (int i = 0; i < city.length; i++) {
			for (int pageNumber = 1; pageNumber <= 10; pageNumber++) {

				String strArray = ph.fetchData(pageNumber, city[i]);
				if (strArray == null || strArray.isEmpty())
					continue;
				ph.convertToJSONAndWrite(strArray);

				System.out.println("Successful");
				// ws.addToMongoDB(jsonData);
				System.out.println("*****************");

			}
		}
		ph.closeWriter();
	}
}