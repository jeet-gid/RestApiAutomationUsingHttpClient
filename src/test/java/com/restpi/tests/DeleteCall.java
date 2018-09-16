package com.restpi.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapi.client.RestClient;
import com.restapi.utility.TestConstants;

public class DeleteCall {
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@Test
	public void testSingleUserDeleteApi() throws ClientProtocolException, IOException{
		 restClient =new RestClient("serviceUrl");
		 httpResponse=restClient.deleteUser();
		System.out.println(httpResponse);
		Integer statuscode= httpResponse.getStatusLine().getStatusCode();
		
		// Assert status code
		Assert.assertEquals(statuscode,TestConstants.STATUS_CODE_204,"Respose code not Matches");
//		String stringResponse=EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
//		System.out.println(stringResponse);
//		
	
	}
}