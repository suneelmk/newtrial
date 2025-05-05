package com.it.customer;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.it.api.baseclass.BaseclassUtility;
import com.it.api.constants.endpoints.IEndPoint;
import com.it.api.genericutility.JsonUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CustomerTest extends BaseclassUtility {
	
	@Test
	public void createCustomerTest() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		.body("")
		.when()
		.post("/user/customer");
		resp.then()
		
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThanOrEqualTo(1800L))
		.spec(specRespObj)
		.log().all();
		  customerID = resp.jsonPath().get("Id");
		
		resp.then().assertThat().body("FirstName",Matchers.equalTo("sunil"));
		resp.then().assertThat().body("LastName",Matchers.equalTo("kittali"));
		resp.then().assertThat().body("PhoneNumber",Matchers.equalTo("9876543210"));
		

		
	}
	
	@Test(dependsOnMethods = "createCustomerTest")
	public void getCustomerTest() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		
		.when()
		.get(IEndPoint.getCustomer);
		resp.then()
		
		.assertThat().statusCode(200)
		.assertThat().time(Matchers.lessThanOrEqualTo(750L))
		.spec(specRespObj)
		.log().all();
		
		resp.then().assertThat().body("FirstName",Matchers.equalTo("sunil"));
		resp.then().assertThat().body("LastName",Matchers.equalTo("kittali"));
		resp.then().assertThat().body("PhoneNumber",Matchers.equalTo("9876543210"));
		
	}
	@Test(dependsOnMethods = "getCustomerTest")
	public void updateCustomerTest() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		.body("")
		.when()
		.patch("http://49.249.28.218:8081/"+repos+"/"+owner+"/"+customerID);
		resp.then()
		
		.assertThat().statusCode(200)
		.assertThat().time(Matchers.lessThanOrEqualTo(1200L))
		.spec(specRespObj)
		.log().all();
		
		resp.then().assertThat().body("FirstName",Matchers.equalTo("suneel"));
		
		
	}
	
	@Test(dependsOnMethods = "updateCustomerTest")
	public void deleteCustomerTest() throws IOException
	{
		String token = jLib.getAccessToken();
		Response resp = given()
				.spec(specReqObj)
		.auth().oauth2(token)
		
		
		.when()
		.delete("http://49.249.28.218:8081/"+repos+"/"+owner+"/"+customerID);
		resp.then()
		
		.assertThat().statusCode(204)
		.assertThat().time(Matchers.lessThanOrEqualTo(600L))
		.spec(specRespObj)
		.log().all();
		
		
	}

}
