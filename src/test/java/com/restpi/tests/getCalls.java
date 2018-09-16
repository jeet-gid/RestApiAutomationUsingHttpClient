package com.restpi.tests;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapi.client.RestClient;
import com.restapi.utility.TestConstants;
import com.restapi.utility.TestUtils;

public class getCalls {

	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	Integer statusCode;
	JSONObject jsonObject;
	String stringResponse;
	@Test
	public void testSingleUserDetailsApi() throws JSONException, ParseException, IOException{
		restClient =new RestClient("serviceUrl");
	    closeableHttpResponse = restClient.getSingleUserApi();
		// Asserting statusCode 
		  statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(TestConstants.STATUS_CODE_200 , statusCode , "Response Code Not Matches");		
		//Asserting Headers
		Header [] headers =closeableHttpResponse.getAllHeaders();
		for(Header header : headers){
			System.out.println(header.getName()+" : "+header.getValue());
		}		
	// Aserting Body
	     stringResponse=EntityUtils.toString(closeableHttpResponse.getEntity() ,"UTF-8");
		 jsonObject=new JSONObject(stringResponse);
		Assert.assertEquals(((JSONObject) jsonObject.get("data")).get("id"),2);
		Assert.assertEquals(((JSONObject) jsonObject.get("data")).get("first_name"),"Janet");
		Assert.assertEquals(((JSONObject) jsonObject.get("data")).get("last_name"), "Weaver");
		Assert.assertEquals(((JSONObject) jsonObject.get("data")).get("avatar"),"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		
		
	
	}
	
	
	@Test
	public void listAllUsersApi() throws ParseException, IOException, JSONException{
		restClient =new RestClient("listUsers");
		closeableHttpResponse=restClient.getListUsersApi();
		// Assert status
		statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, TestConstants.STATUS_CODE_200,"Status code not matches");
		
		// Assert the body
		stringResponse = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
//		System.out.println(stringResponse);
		jsonObject = new JSONObject(stringResponse);
		
        Assert.assertEquals(jsonObject.get("page"),2);
        Assert.assertEquals(jsonObject.get("per_page"),3);
        JSONArray jsonArray=(JSONArray)jsonObject.get("data");
        
        for (int i=0; i<jsonArray.length();i++){
        	 JSONObject jsonObject =(JSONObject)jsonArray.get(i);
        	 System.out.println(jsonObject.get("id"));
        	 System.out.println(jsonObject.get("first_name"));
        	 System.out.println(jsonObject.get("last_name"));
        	 System.out.println(jsonObject.get("avatar"));
        	 System.out.println("---------------------------");
        	   
        }
          		
			
	}
}
