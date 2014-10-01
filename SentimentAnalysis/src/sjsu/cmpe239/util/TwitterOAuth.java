//##############################################################
//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.util;

import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.model.OAuthRequest;

public class TwitterOAuth {

	private String consumerKey = "woFJ9MxgG7mZnTiT3Meg";
	private String consumerSecret = "EN8NN0NX77EwsOU4UuHuM0UHnKus9WrPmh2SDYeIY";
	
	private Token accessToken = null;
	private OAuthService oauthService = null;
	
	private static TwitterOAuth instance = null;

	private TwitterOAuth() throws Exception{
		if(!this.initOAuth()) throw new Exception("Failed to initialize OAuth");
	}

	public static TwitterOAuth getInstance() throws Exception {
		if(TwitterOAuth.instance == null) {
			TwitterOAuth.instance = new TwitterOAuth();
		}		
		return TwitterOAuth.instance;
	}
	
	public void signRequest(OAuthRequest request) {
		this.oauthService.signRequest(this.accessToken, request);
	}
	
	private  boolean initOAuth() {
		try {
			this.oauthService = new ServiceBuilder()
	    							.provider(TwitterApi.class)
	    							.apiKey(this.consumerKey)
	    							.apiSecret(this.consumerSecret)
	    							.build();
		
			System.out.println("Fetching the Request Token...");
			Token requestToken = this.oauthService.getRequestToken();
	    	System.out.println("Got the Request Token!");
	    	System.out.println();

	    	System.out.println("Now go and authorize App here:");
	    	System.out.println(this.oauthService.getAuthorizationUrl(requestToken));
	    	System.out.println("And paste the verifier here");
	    	System.out.print(">>");
	    
	    	Scanner in = new Scanner(System.in);
	    	Verifier verifier = new Verifier(in.nextLine());
	    	System.out.println();
	    
	    	System.out.println("Trading the Request Token for an Access Token...");
	    	this.accessToken = this.oauthService.getAccessToken(requestToken, verifier);
	    	System.out.println("Got the Access Token! : " + accessToken);
	    	System.out.println();
	    
	    	return true;
		} catch (OAuthException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			TwitterOAuth instance = TwitterOAuth.getInstance();
			OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/search/tweets.json?q=Obama");
			instance.signRequest(request);
			Response response = request.send();
			
			if(response != null && response.isSuccessful()){
				System.out.println(response.getBody());
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(response.getBody());
				JSONArray statuses = (JSONArray)jsonObj.get("statuses");
				
				Iterator<JSONObject> it = statuses.iterator();
				while(it.hasNext()){
					JSONObject tweetData = it.next();
					tweetData.get("metadata");
					String tweet = (String)tweetData.get("text");
					System.out.println(tweet);
				}
			}
		} catch(Exception e) {
			
		}
	}
}
