package com.it.customer;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.it.api.baseclass.BaseclassUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SupplierTest extends BaseclassUtility {
	

	@Test
	public void createSupplier() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		.body("")
		.when()
		.post("http://49.249.28.218:8081/user/supplier");
		resp.then()
		
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThanOrEqualTo(1800L))
		.spec(specRespObj)
		.log().all();
		  supplierID = resp.jsonPath().get("Id");
		
		resp.then().assertThat().body("CompanyName",Matchers.equalTo("FoolsParadise"));
		resp.then().assertThat().body("Province",Matchers.equalTo("Abra"));
		resp.then().assertThat().body("city",Matchers.equalTo("Luba"));
		resp.then().assertThat().body("PhoneNumber",Matchers.equalTo("8889997456"));
		
	}  
	@Test(dependsOnMethods = "createSupplier")
	public void getSupplier() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		
		.when()
		.get("http://49.249.28.218:8081/"+repos+"/"+owner+"/"+supplierID);
		resp.then()
		
		.assertThat().time(Matchers.lessThanOrEqualTo(750L))
		.spec(specRespObj)
		.log().all();
		
		resp.then().assertThat().body("CompanyName",Matchers.equalTo("FoolsParadise"));
		resp.then().assertThat().body("Province",Matchers.equalTo("Abra"));
		resp.then().assertThat().body("city",Matchers.equalTo("Luba"));
		resp.then().assertThat().body("PhoneNumber",Matchers.equalTo("8889997456"));
		
	
	}
	
	
	@Test(dependsOnMethods = "getSupplier")
	public void updateSupplier() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		.body("")
		.when()
		.patch("http://49.249.28.218:8081/"+repos+"/"+owner+"/"+supplierID);
		resp.then()
		
		.assertThat().statusCode(200)
		.assertThat().time(Matchers.lessThanOrEqualTo(1200L))
		.spec(specRespObj)
		.log().all();
		
		resp.then().assertThat().body("PhoneNumber",Matchers.equalTo("8889997456"));
		
		
	}
	
	@Test(dependsOnMethods = "updateSupplier")
	public void deleteSupplier() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		
		.when()
		.delete("http://49.249.28.218:8081/"+repos+"/"+owner+"/"+supplierID);
		resp.then()
		
		.assertThat().statusCode(204)
		.assertThat().time(Matchers.lessThanOrEqualTo(600L))
		.spec(specRespObj)
		.log().all();
	}
	

}
