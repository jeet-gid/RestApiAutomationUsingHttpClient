package com.restapi.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.data.User;
import com.restapi.utility.TestBase;

public class RestClient extends TestBase {
	
		String endPointUrl;
		CloseableHttpResponse httpResponse;
		CloseableHttpClient closeableHttpClient;
		HttpGet httpRequest;
	
		public RestClient(String serviceUrl){
		endPointUrl=prop.getProperty("fixUrl")+prop.getProperty(serviceUrl);
		}
	
	public CloseableHttpResponse getSingleUserApi(){
	 closeableHttpClient=HttpClients.createDefault();
	 httpRequest=new HttpGet(endPointUrl);
		try {
			httpResponse=closeableHttpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return httpResponse;
		
	}

   public CloseableHttpResponse getListUsersApi(){
	       closeableHttpClient = HttpClients.createDefault();
	       httpRequest=new HttpGet(endPointUrl);
	       httpRequest.addHeader("content-type", "application/json");
	       httpRequest.addHeader("username", "jeet");
	       try {
	    	   httpResponse=closeableHttpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
		return httpResponse;
   }
   
   public  CloseableHttpResponse  testCreateNewUserApi(String payload , HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
	   	 closeableHttpClient= HttpClients.createDefault();
	   	 HttpPost httpPost=new HttpPost(endPointUrl);
	   	 // Set the headers
	   	 
	   	  for(Map.Entry<String, String> entry : headerMap.entrySet()){
	   		   httpPost.addHeader(entry.getKey(), entry.getValue());
	   	  }
	   	  
	   	  
	   	  //set the payload
	   	 
	   	   httpPost.setEntity(new StringEntity(payload));
	   	   
	   	   // Execute the request
	      httpResponse=closeableHttpClient.execute(httpPost);
	   
	return httpResponse;
		
	}

   public CloseableHttpResponse deleteUser() throws ClientProtocolException, IOException{
	     closeableHttpClient= HttpClients.createDefault();
	     HttpDelete httpDelete=new HttpDelete(endPointUrl);
	    httpResponse= closeableHttpClient.execute(httpDelete);
	   return httpResponse;
	   
   }
   
   public CloseableHttpResponse updateUser(User user) throws ClientProtocolException, IOException{
	   closeableHttpClient=HttpClients.createDefault();
	   HttpPatch httpPatch=new HttpPatch(endPointUrl);
	   
	   
	   ObjectMapper mapper =new ObjectMapper();
	String jsonString= mapper.writeValueAsString(user);
	   httpPatch.setEntity(new StringEntity(jsonString));
	   
	   httpResponse=closeableHttpClient.execute(httpPatch);
	   
	   
	return httpResponse;
	   
   }
	
	
	
}
