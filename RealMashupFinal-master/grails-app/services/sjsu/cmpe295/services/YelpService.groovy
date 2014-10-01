package sjsu.cmpe295.services

import org.scribe.builder.ServiceBuilder
import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.oauth.OAuthService
import org.scribe.builder.api.DefaultApi10a
import groovy.json.JsonSlurper


class YelpService {


	private static def consumerKey = "Th7pV34YgNgYcFoicwuMEQ"
	private static def consumerSecret = "bLaBzeyTTQhtLSxYrC5fI6vRyRI"
	private static def token = "73TGgiqkBaKH9FRVcACjm0UWIQH3EjHO"
	private static def tokenSecret = "E6zWdfZDZEdK3u0xEc89LGIY_ZA"

	def OAuthService service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build()
	def Token accessToken = new Token(token, tokenSecret)

	def search(def term, def latitude, def longitude) {

		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search")
			request.addQuerystringParameter("term", term)
			request.addQuerystringParameter("ll", latitude + "," + longitude)

			this.service.signRequest(this.accessToken, request)

			Response response = request.send()

			def rawData = response.getBody()
			def data = new JsonSlurper().parseText(rawData)

			def neighborhoods = []
			for(def business in data.businesses) {
				def neighborhood = [:]
				neighborhood['name'] = business['name']
				def addresslist = business['location']['display_address']
				def display_address = ""
				for( def address in addresslist) {
					display_address += " " + address
				}
				neighborhood['address'] = display_address.trim()

				neighborhoods.add(neighborhood)
			}

			return neighborhoods
		}catch(Exception e) {
			println(e)
		}

		return null
	}
}
