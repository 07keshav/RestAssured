package Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payloads;

public class GoogleMapAPis {

	public static void main(String[] args)
	{
		
		//given -- all input details
		//when -- submit the api with resource and http method
		//then -- validate the response
		// body()-- method requires json body in string format
		/* equalTo() is also coming from static org.hamcrest.Matchers.*; package as a static--- used especially for body validation
		not necessarily required for other validation like header*/
		//log().all() to log everything in the output as a part of request and response
		//the same methods used in given() is when used in then () acts as output validation
		// If you want to extract the response and then perform any validation then you need to parse the JSON
		//JsonPath Class takes string as Input and convert that to JSON 
		//if complex json then we need to use parent.child[index].child
		// 2 ways to do assertion one directly using body() and hamcrest package or you first extract and convert to json and then validate
		//passing dynamic values you need --->>  "+variable+"
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String resp =given().
		
		log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payloads.addPlace())
		.when().
		
		post("/maps/api/place/add/json")
		.then().
		
		log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().asPrettyString();
		System.out.println("extract As String ="+resp);
		JsonPath js =new JsonPath(resp);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		System.out.println("***********************************************");
		
		//Add place -->> update place -->> getPlace to check whether successfully added or not so for that need to store place Id 
		String updtAdress ="80 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+updtAdress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		System.out.println("***********************************************");
		String getPlaceresponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceresponse);
		String updatedAdress = js1.getString("address");
		Assert.assertEquals(updtAdress, updatedAdress);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
