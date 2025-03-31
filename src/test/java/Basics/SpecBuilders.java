package Basics;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class SpecBuilders {

	public static void main(String[] args) throws IOException
	{
		
	//	.log().all()	 needs to be added after build() in request spec builder	
	//  	break the sentence till body as RequestSpecification request 
		// from when till all validation comes under response 
		//In response spec bilder no need to add assert statement 
		
		RequestSpecification reqspeq = new RequestSpecBuilder()
										.setContentType(ContentType.JSON)
										.setBaseUri("https://rahulshettyacademy.com")
										.addQueryParam("key", "qaclick123")
											.build().log().all();
		
		ResponseSpecification respspeq = new ResponseSpecBuilder().expectStatusCode(200)
										.expectContentType(ContentType.JSON)
											.build();		
		
		RequestSpecification request =given().spec(reqspeq)
		
		.body(new String(Files.readAllBytes(Paths.get("C:\\Eclipse\\2025-03\\workspace\\RestAssuredFramework\\addBook.json"))));
		// taking string data type if taken as response then the data type should be Response
		Response response = request.when().
		
		post("/maps/api/place/add/json")
		.then()
		
		.spec(respspeq).log().all().body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
		
		String responseString = response.asString();
		System.out.println("extract As String ="+response);
		JsonPath js =new JsonPath(responseString);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		System.out.println("***********************************************");
		
		//Add place -->> update place -->> getPlace to check whether successfully added or not so for that need to store place Id 
		String updtAdress ="80 winter walk, USA";
		
		given().spec(reqspeq)
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+updtAdress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().spec(respspeq).log().all().body("msg", equalTo("Address successfully updated"));
		System.out.println("***********************************************");
		
		
		
		String getPlaceresponse = given().spec(reqspeq).queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().spec(respspeq).log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceresponse);
		String updatedAdress = js1.getString("address");
		Assert.assertEquals(updtAdress, updatedAdress);
		
		
		
	}
	
	
	
}
