package com.restapi.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
public static	Properties prop;
	
	public TestBase(){
		prop = new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//restapi//config//config.properties");
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
				
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured in loading Properties file");
		}
	}
  
}
