package Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pOJO.AddPlace;
import pOJO.Location;


public class AddPlaceUsingPojo {

	@org.testng.annotations.Test
	public void Test() {
		
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName("keshav --house");
		ap.setAddress("29, side layout, cohen 09");
		List<String> l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		ap.setTypes(l);
		ap.setLanguage("French-IN");
		ap.setWebsite("http://google.com");
		ap.setPhone_number("(+91)9838933937");
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	String resp =given().
	
	log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(ap)
	.when().
	
	post("/maps/api/place/add/json")
	.then().
	
	log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().asPrettyString();
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
}
