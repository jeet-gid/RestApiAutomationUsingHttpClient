package com.restpi.tests;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.client.RestClient;
import com.restapi.data.User;
import com.restapi.utility.TestConstants;

public class UpdateCall {
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@Test
	public void testUpdateUserApi() throws ClientProtocolException, IOException{
		 restClient=new RestClient("serviceUrl");
		 User user =new User("MANOJ" , "DIRECTOR");
		 httpResponse=restClient.updateUser(user);
		 System.out.println(httpResponse);
		Integer status_code= httpResponse.getStatusLine().getStatusCode();
		 
		 // Assert Status
		 Assert.assertEquals(status_code,TestConstants.STATUS_CODE_200,"Response Code not Matches");
		 
		 // getting all headers
		  Header [] headers=httpResponse.getAllHeaders();
		  for(Header header : headers){
			   System.out.println(header.getName()+" : "+header.getValue());
		  }
		 
		 // Assert the body
	String jsonStringResponse=	EntityUtils.toString(httpResponse.getEntity(),"UTF-8");  
	
	   System.out.println(jsonStringResponse);
	   
	   ObjectMapper mapper =new ObjectMapper();
	  User actual_user= mapper.readValue(jsonStringResponse, User.class);
	  
	  System.out.println(actual_user.getId());
	  System.out.println(actual_user.getName());
	  System.out.println(actual_user.getJob());
	
	
		 
		 
	}

}
