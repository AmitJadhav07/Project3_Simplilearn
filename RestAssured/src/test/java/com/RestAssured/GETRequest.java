package com.RestAssured;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class GETRequest {
	
	String baseURI = "https://reqres.in";
	String reqBody = "{\r\n"
			+ "    \"name\": \"Amit\",\r\n"
			+ "    \"job\": \"Jadhav\"\r\n"
			+ "}";
	public static Logger log = Logger.getLogger(GETRequest.class);

	
	@Test
	public void GetUsersList() {
		PropertyConfigurator.configure("./LogFiles/Log4j.properties");
		log.info("Getting User List");
		ValidatableResponse res1 = RestAssured
				.given().baseUri(baseURI)
				.when().get("/api/users?page=2")
				.then().statusCode(200).statusLine("HTTP/1.1 200 OK")
				.log().all();
		log.info("Getting User List Completed");
	}
	
	
	@Test
	public void CreateNewUser() {
		log.info("Creating New User");
		ValidatableResponse res2 = RestAssured
				.given().baseUri(baseURI)
				.contentType(ContentType.JSON)
				.body(reqBody)
				.when().post("/api/users")
				.then().assertThat().statusCode(201).statusLine("HTTP/1.1 201 Created")
				.log().all();
		log.info("New User Created");
	}
	

}
