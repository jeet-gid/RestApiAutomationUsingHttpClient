package com.restapi.utility;

import org.json.JSONException;
import org.json.JSONObject;

public class TestUtils {

	public static Object getValueByJPath(JSONObject responseobject ,String path){
		Object value=null;
			if(!path.isEmpty()){
		//			if(path.contains("[")){
						    try {
						  value   =	responseobject.get(path);
								System.out.println(responseobject.get(path));
							} catch (JSONException e) {
								e.printStackTrace();
							}
					}
		
			
			return value;
	 }

	
}
