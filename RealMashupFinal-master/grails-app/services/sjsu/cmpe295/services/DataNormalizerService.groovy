package sjsu.cmpe295.services

import com.sun.org.apache.xml.internal.serialize.XMLSerializer
import grails.converters.JSON
import grails.converters.XML
import grails.plugins.rest.client.*
import grails.rest.*

import groovy.json.JsonBuilder
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.runtime.DateGroovyMethods
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport
import sjsu.cmpe295.models.MasterSoldProperty
import sjsu.cmpe295.models.MasterUnSoldProperty
import sjsu.cmpe295.models.MasterSoldProperty

import groovy.time.TimeCategory
import java.text.SimpleDateFormat
import java.util.Date
import static java.util.Calendar.*


class DataNormalizerService extends DateGroovyMethods {

	
	// scaling constants
	final def A1 = 2
	final def A = 1.5
	final def B1 = 1
	final def B = 0.5
	final def C1 = 0
	final def C = -0.5
	final def D1 = -1
	final def D = -1.5
	final def F = -2
	

	
	def public normalizeData(String address, Double bathroom, Double bedroom,String city, Double fArea, String lastSoldDate, Double lastSoldPrice,Double lat, Double lon, Double lArea, String state, Double tax, String useCode,Double yearBuilt, Double zestAmt,Double zestHigh, Double zestLow, Double zestVal,String zipcode, Double zpid )
	{	def property
		String result= "failure"
		try 
		{	
			// if ZPID exists then we are just updating the record
			// else we are adding a new record

			//if(MasterSoldProperty.findByAddress(address) != null)
			//	property = MasterSoldProperty.findByAddress(address) //update
			//else
			//{
			property = new MasterSoldProperty() // insert
				property.setAddress(address)
				property.setState(state)
				property.setLatitude(lat)
				property.setLongitude(lon)
				property.setZipcode(zipcode)
			//}	 
			// Set external factors
			// These factors are manually entered from areavibes.com
			property = populateExternalFactors(property,city.replace(" ", ""))
			
			// set house age and lastSoldDate
			// These are required for correct prediction
			
			if(yearBuilt != 0 )
			{
				def age = (new GregorianCalendar()).get(Calendar.YEAR) - yearBuilt
				println("Age: "+ age)
				property.setAge(age)
				property.setYearBuilt(yearBuilt)
			}
			else
			{
				property.setAge(0)
				property.setYearBuilt(yearBuilt)
			}
			
			if(lastSoldDate != 0 && !lastSoldDate.equals("0"))
			{
				def today = new Date()
				def sdf = new SimpleDateFormat("MM/dd/yyyy")
				Date lastDate = sdf.parse(lastSoldDate)
				def days = (Double)DateGroovyMethods.minus(today,lastDate)
				property.setDaysPending(days)
				property.setLastSoldDate(lastSoldDate)
			}
			else
			{	
				property.setDaysPending(0)
				property.setLastSoldDate(lastSoldDate.toString())
			}
			
			//populateInternal factors
			//property = populateInternalFactors(property, bathroom, bedroom, fArea, lArea,lastSoldPrice,  tax,  useCode,  zestAmt, zestHigh,  zestLow,  zestVal,  zpid)
			property.setBathroom(bathroom)
			property.setBedroom(bedroom)
			property.setFinishedSqFt(fArea)
			property.setLotSizeSqFt(lArea)
			property.setLastSoldPrice(lastSoldPrice)
			property.setTaxAssesment(tax)
			property.setZest_amt(zestAmt)
			property.setZest_high(zestHigh)
			property.setZest_low(zestLow)
			property.setZest_valueChange(zestVal)
			property.setZpID(zpid)
			property.setPredictedpriceAppreciation(false);
			property.setRecentlySold(true);
			
			property.setCity(city.replace(" ", "").toLowerCase())
			property.setUseCode(useCode)
			
			// Algorithm to calculate price Appreciation factor
			// zestVal tells us how much price has changed from last updation
			// We cannot rely on lastSoldPrice because the house may be sold
			// 50 years back as per the rate in that time
			
			
			if(zestVal > 0 )
				property.setPriceAppreciated(true)
			else
				property.setPriceAppreciated(false)
			
				
			//println(property.getPriceAppreciated())
			property.save() // save to Database
			
			println(property.getErrors()) //check errors
			
			if(property.getErrors().toString().contains(" 0 "))
				result = "success"
			else
			{
				println("Record not inserted")
				result = "failure"
			}
		}
		catch(Exception e)
		{	println(e.getMessage())
			e.printStackTrace()
			println("Record not inserted")
			result = "failure"
			return result
		}
		
		return result
	}
	
	def public updateData(String address, String thumbs1, String thumbs2 )
	{	
		println("In DataNormalizerService/updateData")
		
		MasterUnSoldProperty property
		String result= "failure"
		try
		{
			// if ZPID exists then we are just updating the record
			// else we are adding a new record

			if(MasterUnSoldProperty.findByAddress(address) != null)
			{
				property = MasterUnSoldProperty.findByAddress(address) //update
			
				property.setThumbs1(thumbs1)
				property.setThumbs2(thumbs2)
			
				property.save(flush:true) // save to Database
			
				println(property.getErrors()) //check errors
			
				if(property.getErrors().toString().contains(" 0 "))
					result = "success"
				else
				{
					println("Record not updated")
					result = "failure"
				}
			}
			else
			{
				println("Record not found")
				result = "failure"
			}
		}
		catch(Exception e)
		{	println(e.getMessage())
			e.printStackTrace()
			println("Record not inserted")
			result = "failure"
			return result
		}
		
		return result
	}
	
	
	def populateExternalFactors(MasterSoldProperty property,String city)
	{	
		
		switch(city.toLowerCase())
		{	
			
			case "alameda":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(B)
				property.setEducation(C1)
				property.setEmployment(B)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "brisbane":
				property.setAmenities(A)
				property.setCostofliving(F)
				property.setCrimerate(B1)
				property.setEducation(B)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "belmont":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "campbell":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(C1)
				property.setEducation(C1)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "dalycity":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(C)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "eastpaloalto":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(D)
				property.setEducation(F)
				property.setEmployment(B)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "elgranada" :
				property.setAmenities(D)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "felton" :
				property.setAmenities(D)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(B)
				property.setHousing(A1)
				property.setWeather(C1)
				break;
			case "fostercity":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A1)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "fremont" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(C1)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "halfmoonbay" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(C)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "hillsborough":
				property.setAmenities(D)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "laHonda":
				// evened out as no data available
				property.setAmenities(C)
				property.setCostofliving(C)
				property.setCrimerate(C)
				property.setEducation(C)
				property.setEmployment(C)
				property.setHousing(C)
				property.setWeather(C)
				break;
			case "livermore":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(B)
				property.setEducation(C1)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "menlopark":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "millbrae":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(C1)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "mossbeach" :
				property.setAmenities(F)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(A)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "mountainhouse":
				// evened out as no data available
				property.setAmenities(C)
				property.setCostofliving(C)
				property.setCrimerate(C)
				property.setEducation(C)
				property.setEmployment(C)
				property.setHousing(C)
				property.setWeather(C)
				break;
			case "mountainview":
				property.setAmenities(A1)
				property.setCostofliving(D)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "oakland" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(F)
				property.setEducation(D1)
				property.setEmployment(C1)
				property.setHousing(A)
				property.setWeather(B)
				break;
			case "pacifica":
				property.setAmenities(A)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "pleasanton":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "redwoodcity" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A)
				property.setEducation(C)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "sanbruno" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A)
				property.setEducation(C1)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "sanfrancisco":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(F)
				property.setEducation(C)
				property.setEmployment(B)
				property.setHousing(A1)
				property.setWeather(C1)
				break;
			case "sancarlos":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "sanjose":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(B)
				property.setEducation(C)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "sanramon" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B1)
				property.setEmployment(A1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "sanmateo":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A)
				property.setEducation(C1)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B1)
				break;
			case "santaclara":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A)
				property.setEducation(C1)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "santacruz":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(F)
				property.setEducation(C1)
				property.setEmployment(B)
				property.setHousing(A1)
				property.setWeather(C1)
				break;
			case "scottsvalley":
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A1)
				property.setEducation(B)
				property.setEmployment(A)
				property.setHousing(A1)
				property.setWeather(C1)
				break;
			case "southsanfrancisco" :
				property.setAmenities(A1)
				property.setCostofliving(F)
				property.setCrimerate(A)
				property.setEducation(C)
				property.setEmployment(B1)
				property.setHousing(A1)
				property.setWeather(B)
				break;
			case "tracy":
				property.setAmenities(A1)
				property.setCostofliving(D)
				property.setCrimerate(A)
				property.setEducation(C)
				property.setEmployment(B1)
				property.setHousing(A)
				property.setWeather(B1)
				break;
			case "":
				// if city attribute is null, average out the outliers
				property.setAmenities(C)
				property.setCostofliving(C)
				property.setCrimerate(C)
				property.setEducation(C)
				property.setEmployment(C)
				property.setHousing(C)
				property.setWeather(C)
				break;
			case null:
				// if city attribute is null, average out the outliers
				property.setAmenities(C)
				property.setCostofliving(C)
				property.setCrimerate(C)
				property.setEducation(C)
				property.setEmployment(C)
				property.setHousing(C)
				property.setWeather(C)
				break;
		}
		
		return property;
	}
	
	def populateInternalFactors(MasterSoldProperty property,Double  bathroom,Double  bedroom,Double fArea,Double lArea,Double lastSoldPrice, Double tax,String useCode,Double zestAmt,Double zestHigh,Double zestLow,Double zestVal, Double zpid)
	{	
		//scale bedrooms and bathroom
		property = scaleBathRooms(property, bathroom)
		property = scaleRooms(property, bedroom)
		
		// scale finished and lot sq ft area
		property = scaleFArea(property,fArea)
		property = scaleLArea(property,lArea)
		
		//scale Usecode
		//property = scaleUseCode(property,useCode)
		
		//Real and Double values like taz, zestimate not scaled
		property.setLastSoldPrice(lastSoldPrice)
		property.setTaxAssesment(tax)
		property.setZest_amt(zestAmt)
		property.setZest_high(zestHigh)
		property.setZest_low(zestLow)
		property.setZest_valueChange(zestVal)
		property.setZpID(zpid)
		
		
		return property;
	}
	
	def scaleUseCode(MasterSoldProperty property, String useCode)
	{
		switch(useCode)
		{
			case "Condominium":
				property.setUseCode(B)
				break;
			case "SingleFamily":
				property.setUseCode(C)
				break;
			case "Condominium":
				property.setUseCode(B)
				break;
			case "Townhouse":
				property.setUseCode(B1)
				break;
			case "Duplex":
				property.setUseCode(A)
				break;
			case "MultiFamily2To4":
				property.setUseCode(A1)
				break;
			
			case "0":
				property.setUseCode(C)
				break;
			case 0:
				property.setUseCode(C)
				break;
			case null:
				property.setUseCode(C)
				break;
		}
		
		return property
	}
	
	def scaleRooms(MasterSoldProperty property, Double bedroom)
	{
		switch (bedroom)
		{
			case {it<= 1}:
				property.setBedroom(D)
				break;
			case {it = 2}:
				property.setBedroom(C)
				break;
			case {it = 3 }:
				property.setBedroom(C1)
				break;
			case {it = 4}:
				property.setBedroom(B)
				break;
			case {it = 5}:
				property.setBedroom(B1)
				break;
			case { it = 6}:
				property.setBedroom(A)
				break;
			case { it >= 7}:
				property.setBedroom(A1)
				break;
			
		}
		
		return property
	}
	
	def scaleBathRooms(MasterSoldProperty property, Double bathroom)
	{
		switch (bathroom)
		{
			case {it<= 1}:
				property.setBathroom(C)
				break;
			case {it <=1.5}:
				property.setBathroom(C1)
				break;
			case {it <=2}:
				property.setBathroom(B)
				break;
			case {it <=2.5}:
				property.setBathroom(B1)
				break;
			case {it <= 3}:
				property.setBathroom(A)
				break;
			case { it >= 3.5}:
				property.setBathroom(A1)
				break;
			
		}
		
		return property
	}
	
	def scaleFArea(MasterSoldProperty property, Double Farea)
	{
		switch (Farea)
		{
			case {it < 1500}:
				property.setFinishedSqFt(F)
				break;
			case {it <= 2000}:
				property.setFinishedSqFt(D)
				break;
			case {it <= 300}:
				property.setFinishedSqFt(D1)
				break;
			case {it <= 4000}:
				property.setFinishedSqFt(C)
				break;
			case {it <= 5000}:
				property.setFinishedSqFt(C1)
				break;
			case { it <= 8000}:
				property.setFinishedSqFt(B)
				break;
			case { it <= 15000}:
				property.setFinishedSqFt(B1)
				break;
			case { it <= 25000}:
				property.setFinishedSqFt(A)
				break;
			case { it > 25000}:
				property.setFinishedSqFt(A1)
				break;
			
		}
		
		return property
	}
	
	def scaleLArea(MasterSoldProperty property, Double Larea)
	{
		switch (Larea)
		{
			case {it < 1500}:
				property.setLotSizeSqFt(F)
				break;
			case {it <= 2000}:
				property.setLotSizeSqFt(D)
				break;
			case {it <= 300}:
				property.setLotSizeSqFt(D1)
				break;
			case {it <= 4000}:
				property.setLotSizeSqFt(C)
				break;
			case {it <= 5000}:
				property.setLotSizeSqFt(C1)
				break;
			case { it <= 8000}:
				property.setLotSizeSqFt(B)
				break;
			case { it <= 15000}:
				property.setLotSizeSqFt(B1)
				break;
			case { it <= 25000}:
				property.setLotSizeSqFt(A)
				break;
			case { it > 25000}:
				property.setLotSizeSqFt(A1)
				break;
			
		}
		
		return property
	}
}

