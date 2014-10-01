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
import groovy.time.TimeCategory
import java.text.SimpleDateFormat
import java.util.Date
import static java.util.Calendar.*


class RupdateService extends DateGroovyMethods {

	
	def public initializePriceAppreciated()
	{
		MasterUnSoldProperty property = MasterUnSoldProperty.findAll()
		for (it in property)
		{
			property.setPriceAppreciated(false)
		}
		
		property.save() // save to Database
		
		println(property.getErrors()) //check errors
		
		if(property.getErrors().toString().contains(" 0 "))
			result = "success"
		else
		{
			println("Records not initialized for price prediction")
			result = "failure"
		}
	}
	
	
	def public updateRData(Integer ids)
	{	MasterUnSoldProperty property
		String result= "failure"
		try 
		{	
			if(MasterUnSoldProperty.get(ids) != null)
			{
				property = MasterUnSoldProperty.get(ids) //update
				property.setPredictedpriceAppreciation(true)
		
			}
				
			//println(property.getPriceAppreciated())
			property.save() // save to Database
			
			println(property.getErrors()) //check errors
			
			if(property.getErrors().toString().contains(" 0 "))
				result = "success"
			else
			{
				println("Record not updated")
				result = "failure"
			}
		}
		catch(Exception e)
		{	println(e.getMessage())
			e.printStackTrace()
			println("Record not upadated")
			result = "failure"
			return result
		}
		
		return result
	}
	
	def checkAccuracy(Integer ids)
	{	println(ids )
		def accurate = false
			
			if(MasterSoldProperty.get(ids) != null)
			{
				MasterSoldProperty property = MasterSoldProperty.get(ids) //update
			 	//println(property.getPriceAppreciated())
				 accurate = property.getPriceAppreciated()
			}
		println("accuracy: "+ accurate)
		return accurate
			
	}

}

