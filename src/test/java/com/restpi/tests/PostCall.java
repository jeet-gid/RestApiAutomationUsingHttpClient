package com.restpi.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.client.RestClient;
import com.restapi.data.User;
import com.restapi.utility.TestConstants;

public class PostCall {
	 RestClient restClient;
	 CloseableHttpResponse httpResponse;
	
	@Test
	public void testCreateUserApi() throws ClientProtocolException, IOException{
		 restClient =new RestClient("createNewUser");
		 
		 // create the headers
		 HashMap headerMap =new HashMap<String, String>();
		 headerMap.put("content-type","application/json");
		 
		 // create the payload
		 
		 User user =new User("def", "Manager");
		 ObjectMapper mapper =new ObjectMapper();
		String payload= mapper.writeValueAsString(user);
		
		httpResponse= restClient.testCreateNewUserApi(payload, headerMap);
	Integer statuscode=	httpResponse.getStatusLine().getStatusCode();
		
		Assert.assertEquals(statuscode,TestConstants.STATUS_CODE_201,"Response Code not Matches");
		
		//Asserting the body
		
	String stringResponse=EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	 User actualUser= mapper.readValue(stringResponse, User.class);

	 Assert.assertEquals(actualUser.getName(), user.getName(),"Name not Matched");
	 Assert.assertEquals(actualUser.getJob(), user.getJob() , "Job not Matches");

	System.out.println(actualUser.getId()+"      "+actualUser.getCreatedAt());
	 
	}

}
