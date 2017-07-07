package com.example.wss.curl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.wss.model.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goebl.david.Response;
import com.goebl.david.Webb;

public class RunCurl {
	
    static final String HOST_IP = "localhost"; // set you're own address (`hostname -I`)
    
    static final String URL_GOOGLE = "https://google.com";
    static final String URL_FACEBOOK = "https://graph.facebook.com/v2.9";
    
	static final String FACEBOOK_ACCESS_TOKEN = "EAACEdEose0cBAMQIGFOsBhCMtYk"
			+ "y0IvIjUP4subWZBKzNblnvZBJBA53ZBukumSbANxfwDd8XhejAH0ltm3WgroDTC"
			+ "1ZB1dZCVVE2az8m60FQX6RX7ZAODZBT4ZBzByeFnLBGvQqYlJRh7JqVZB3gZB8X"
			+ "7SzOIBegO52iU1lwFtuWlaZBriqMv5TZB8q3poJYAvIUhNcZAu2NZCFJcKiXiRCoSbde6";
    
    static Webb webb;
    
    // TEST GET:
    public static void testFacebookGetHttp() throws IOException {
    	webb.setBaseUri(URL_FACEBOOK);
    	
		Response<String> respone = 
				webb
					.get("/quocminh.chau")
					.param("fields", "id, gender, name")
					.param("access_token", FACEBOOK_ACCESS_TOKEN)
					.asString();
		
		System.out.println("Content Type: " + respone.getContentType());
		System.out.println("Date: " + respone.getDate());
		System.out.println("Status Code: " + respone.getStatusCode());
		System.out.println("Last modified: " + respone.getLastModified());
		System.out.println("Connection: " + respone.getConnection().getContent().toString());
		System.out.println("Body: " + respone.getBody());
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(respone.getBody());
			
			System.out.println("JSONObject: " + jsonObject.toString());
			System.out.println("id = " + jsonObject.getLong("id"));
			System.out.println("name = " + jsonObject.getString("name"));
			System.out.println("gender = " + jsonObject.getString("gender"));
			
		} catch (JSONException e) {
			System.out.println("JSONObject: NULL");
			e.printStackTrace();
		}
    }
    
    // TEST POST:
    public static void testPostHttp() throws JSONException {
    	webb.setBaseUri("http://" + HOST_IP + ":8080");
    	
    	Greeting greeting = new Greeting(10, "Hello, CQM !");
    	ObjectMapper mapper = new ObjectMapper();
    	
		try {
			webb
				.put("/greeting")
				.body(new JSONObject(mapper.writeValueAsString(greeting)))
				.asVoid();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) throws IOException {
		// SET UP:
		Webb.setGlobalHeader(Webb.HDR_USER_AGENT, Webb.DEFAULT_USER_AGENT);
		webb = Webb.create();
		
		try {
			testPostHttp();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// testFacebookGetHttp();
	}

}
