package sjsu.cmpe295.controllers

import sjsu.cmpe295.models.MasterUnSoldProperty
import sjsu.cmpe295.models.User
import grails.converters.XML
import grails.converters.JSON
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import grails.plugins.rest.client.*
import grails.rest.*
import java.text.DecimalFormat
import static groovyx.net.http.ContentType.JSON
import groovyx.net.http.*

// This class calls all the URLs defined in REST controllers making making REST API calls

class RestClientController 
{
	def getProperties() 
	{
		println("In RestClientController/getProperties()")
		
		if(params.query)
		{
			// make the REST api call
			def data
		try
		{
			data = new URL("http://realmashup.aws.af.cm/rest/getProperties?query="+params.query.replace(" ","+")+"&paginate=false").getText()
			
			//def data = new URL("http://realmashup.aws.af.cm/rest/getProperties?query="+params.query.replace(" ","+")+"&paginate=false").getText()
			println(data)
			def json = new JsonSlurper().parseText(data)
			println(json)
			
			if(json.error == "success")
			{
				if(json.type == "address")
				{
					flash.address = json.properties.address
					flash.city = json.properties.city;
					
					DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
					String zestAmount = '$' + (dFormat.format(json.properties.zest_amt));
					println(zestAmount)
					
					flash.zestAmt = zestAmount;
					flash.bathroom = String.valueOf(json.properties.bathroom);
					flash.bedroom = String.valueOf(json.properties.bedroom);
					flash.fArea = json.properties.finishedSqFt
					flash.lArea = json.properties.lotSizeSqFt
					flash.lat = json.properties.latitude
					flash.lon = json.properties.longitude
					flash.zip = json.properties.zipcode
					flash.amenities = json.properties.amenities
					flash.crimeRate = json.properties.crimerate
					flash.education = json.properties.education
					flash.employment = json.properties.employment
					flash.weather = json.properties.weather
					flash.costOfLiving = json.properties.costofliving
					flash.priceAppreciated = json.properties.priceAppreciated
					flash.thumbnail = json.properties.thumbs1
					println(json.properties.priceAppreciated)
					
					
					// new algorithm with confidence score
					def baseConf = 0;
					
					// Cost of living is High and prices going down => wait
					if(flash.costOfLiving <= 1.5 && flash.priceAppreciated == false )
					{
						flash.ifBuy = "wait";
						baseConf = 30;
					}
					// Cost of living is High and prices going up => Buy if budget is high
					else if(flash.costOfLiving <= 1.5 && flash.priceAppreciated == true )
					{
						flash.ifBuy = "buy";
						baseConf = 50;
					}
					// Cost of living is Low and prices going down => Dont Buy
					else if(flash.costOfLiving > 1.5 && flash.priceAppreciated == false )
					{
						flash.ifBuy = "wait";
						baseConf = 50;
					}
					// Cost of living is Low and prices going up => Buy (Good investment)
					else if(flash.costOfLiving > 1.5 && flash.priceAppreciated == true )
					{
						flash.ifBuy = "buy";
						baseConf = 50;
					}
					
					flash.cscore = baseConf + ((flash.education+3) * 2) + ((flash.crimeRate+3) *5) + ((flash.employment+3) * 2) + ((flash.amenities+3))
					print("Thumbnail: "+flash.thumbnail)
					
					//render(view: "/home/listings")
					//redirect(controller:"home", action:"listings"  )
					render(view: "/home/listings", model:['watchlist': params.watchlist])
				}
				else if(json.type == "city")
				{	
						def properties = json.properties
						def total = json.total
						println("Total:"+total.toString())
						printf(properties.size().toString())
						
						for (it in properties)
						{
							DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
							String zestAmount = '$' + (dFormat.format(it.zest_amt));
							println(zestAmount)
							it.zest_amt = zestAmount
							
						}
						
						flash.query = params.query
						flash.propertiesStr = JsonOutput.toJson(json.properties)
						flash.properties = properties
						
						
						//redirect(controller: "home", action:"showResult",  model:['properties':properties, 'total': total, 'watchlist': false])
						render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': false])
				}
				else
				{
						flash.errorMessage = (json.error.split(":"))[1]
						redirect(controller:"home", action: "showError")
				}
			}
			else
			{
					flash.errorMessage = (json.error.split(":"))[1]
					redirect(controller:"home", action: "showError")
			}
		}	
		catch(Exception e)
		{
				redirect(controller:"home", action: "show404")
		} //try
		} // guery if
	}
	
	def getPropertiesInfoByAjax()
	{
		println("In RestClientController/getPropertiesInfoByAjax()")
		println(params.toString())
		// make the REST api call
		try{
			def data = new URL("http://realmashup.aws.af.cm/rest/ajax/getPropertiesInfoByAjax?query="+params.value.replace(" ","+")+"&paginate=false").getText()
		
		
			def json = new JsonSlurper().parseText(data)
			//println(json)
			def properties = json.properties
			println(properties.toString())
			flash.properties = properties 
			println(properties);
			render ( properties )
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
	}
	
	
	def paginateAddresses()
	{
		println("In RestClientController/paginateAddresses()")
		
		try{
			// make the REST api call
			def data = new URL("http://realmashup.aws.af.cm/rest/getProperties?query="+params.query.replace(" ","+")
				+"&paginate=true"+"&max="+params.max+"&offset="+params.offset).getText()
		
			println(data)
			
			def json = new JsonSlurper().parseText(data)
			def properties = json.properties
			def total = params.total
			println(properties.size())
			for (it in properties)
			{
				DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
				String zestAmount = '$' + (dFormat.format(it.zest_amt));
				println(zestAmount)
				it.zest_amt = zestAmount
			}
			
			flash.query = params.query
			flash.propertiesStr = JsonOutput.toJson(json.properties)
			flash.properties = properties
			render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': false])
			
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
	}
	
	def paginateWatchList()
	{
		println("In RestClientController/paginateWatchList()")
		
		try{
		// make the REST api call
			def data = new URL("http://realmashup.aws.af.cm/rest/watchlist/getUserWatchlist?email="+session.email
				+"&paginate=true"+"&max="+params.max+"&offset="+params.offset).getText()
	
			println(data)
			
			def json = new JsonSlurper().parseText(data)
			def properties = json.properties
			def total = params.total
			println(properties.size())
			for (it in properties)
			{
				DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
				String zestAmount = '$' + (dFormat.format(it.zest_amt));
				println(zestAmount)
				it.zest_amt = zestAmount
			}
			
			flash.properties = properties
			render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': true])
		
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
	}
	
	def authenticateUser() 
	{
		println("In RestClientController/authenticateUser()")
		// make the REST api call
		//def data = new URL("http://realmashup.aws.af.cmFinal/rest/user/authenticateUser").getText()
		println(params.toString())
		def error
		User user
		
		try{
			def http = new HTTPBuilder("http://realmashup.aws.af.cm/rest/user/authenticateUser")
		
		
			http.request(Method.POST, groovyx.net.http.ContentType.JSON)
			{
				body = [email:params.email, password:params.password];
				response.success = { resp, json ->
									//println(json)
									user = json.user
									error =json.error
								}
			}
			
			if(error == "success")
			{	
				//render(view: '/home/index')
				session.username = user.firstname
				session.lastname = user.lastname
				session.email = user.email
				redirect(controller: "home", action:"index")
			}
			else
			{
				flash.errorMessage = (error.split(":"))[1]
				redirect(controller:"home", action: "showError")
			}
		
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
	}
	
	def registerUser()
	{
		println("In RestClientController/registerUser()")
		// make the REST api call
		//def data = new URL("http://realmashup.aws.af.cmFinal/rest/user/registerUser").getText()
		def error
		User user
		
		try{
			//def http = new HTTPBuilder("http://realmashup.aws.af.cmFinal/rest/user/registerUser")
			def http = new HTTPBuilder("http://realmashup.aws.af.cm/rest/user/registerUser")
		
			http.request(Method.PUT, groovyx.net.http.ContentType.JSON)
			{
				body = [fname:params.fname, lname:params.lname, email:params.email, password:params.password];
				response.success = { resp, json ->
									println(json)
									user = json.user
									error =json.error
								}
			}
			
			if(error == "success")
			{
				//render(view: '/home/index')
				session.username = user.firstname
				session.lastname = user.lastname
				session.email = user.email
				redirect(controller: "home", action:"index")
			}
			else
			{
				flash.errorMessage = (error.split(":"))[1]
				redirect(controller:"home", action: "showError")
			}
			
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
		
	}
	
	def getUserWatchlist() {
		println("In class RestClientController/getUserWatchlist()")
		
		try{
			def data = new URL("http://realmashup.aws.af.cm/rest/watchlist/getUserWatchlist?email="+session.email).getText()
		
			println(data)
			println("params "+params.toString())
			
			def json = new JsonSlurper().parseText(data)
			
			//redirect(controller: "home", action:"showResult",  model:['properties':properties, 'total': total, 'watchlist': false])
			if(json.error == "success")
			{	
				def properties = json.properties
				def total = properties.size()
				println(total.toString())
				printf(properties.size().toString())
				for (it in properties)
				{
					DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
					String zestAmount = '$' + (dFormat.format(it.zest_amt));
					println(zestAmount)
					it.zest_amt = zestAmount
				}
				flash.properties = properties
				render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': true])
			}
			else
			{
				flash.errorMessage = (json.error.split(":"))[1]
				println(flash.errorMessage)
				redirect(controller:"home", action: "showError")
			}
		
		}catch(Exception e)
		{
			redirect(controller:"home", action: "show404")
		}
	}

   def addToUserWatchList() {
	   println("In class RestClientController/addToUserWatchList()")
	   
	   def error
	   def properties
	   
	   try{
		   def http = new HTTPBuilder("http://realmashup.aws.af.cm/rest/watchlist/addToUserWatchList?email="+session.email)
	   
		   println(params.toString())
		   
		   http.request(Method.POST, groovyx.net.http.ContentType.JSON)
		   {
			   body = [address:params.address];
			   response.success = { resp, json ->
								   println(json)
								   error =json.error
								   properties = json.properties
							   }
		   }
		  
		   
		   if(error == "success")
		   {	
			   def total = properties.size()
			   println(total.toString())
			   printf(properties.size().toString())
			   flash.properties = properties
			   render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': true])
			   //redirect(controller: "home", action:"index")
		   }
		   else
		   {
			   flash.errorMessage = (error.split(":"))[1]
			   println(flash.errorMessage)
			   redirect(controller:"home", action: "showError")
		   }
	   }catch(Exception e)
	   {
		   redirect(controller:"home", action: "show404")
	   }
   }

   def removeFromWatchList() {
	   println("In class RestClientController/removeFromWatchList()")
	   
	   def error
	   def properties
	   
	   try{
		   def http = new HTTPBuilder("http://realmashup.aws.af.cm/rest/watchlist/removeFromWatchList")
	   
		   //println(params.toString())
		   
		   http.request(Method.PUT, groovyx.net.http.ContentType.JSON)
		   {
			    body = [address:params.address, email:session.email];
			   response.success = { resp, json ->
								  // println(json)
								   properties = json.properties
								   error =json.error
							   }
		   }
		  
		   
		   if(error == "success")
		   {
			   def total = properties.size()
			   println(total.toString())
			   printf(properties.size().toString())
			   flash.properties = properties
			   render(view: "/home/result", model:['properties':properties, 'total': total, 'watchlist': true])
			   //redirect(controller: "home", action:"index")
		   }
		   else
		   {
			   flash.errorMessage = (error.split(":"))[1]
			   println(flash.errorMessage)
			   redirect(controller:"home", action: "showError")
		   }
	   }catch(Exception e)
	   {
		   redirect(controller:"home", action: "show404")
	   }
   }
}
