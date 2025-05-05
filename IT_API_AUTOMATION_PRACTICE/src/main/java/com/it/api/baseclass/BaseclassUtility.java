package com.it.api.baseclass;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.auth.AUTH;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.it.api.genericutility.DataBaseUtility;
import com.it.api.genericutility.FileUtility;
import com.it.api.genericutility.JsonUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseclassUtility {
	public FileUtility fLib = new FileUtility();
	public JsonUtility jLib = new JsonUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	
	public String  customerID;
	public String supplierID;
	
	public String owner="suneelmk";
	public String repos="QSP";
	
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	
	@BeforeSuite
	public void configBS() throws SQLException, IOException
	{
		dbLib.getDbconnection();
		System.out.println("===connected to db======");
		
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
	
		builder.setBaseUri(fLib.getDataFromPropertiesFile("BASEUrl"));
		specReqObj = builder.build();
		
		ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		 specRespObj = resBuilder.build();
	}
	@AfterSuite
	public void configAS() throws SQLException
	{
		dbLib.closeConnection();
		System.out.println("======disconnected from db=====");
		}
	

	
	
}
