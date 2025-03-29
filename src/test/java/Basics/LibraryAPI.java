package Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryAPI {

	@Test
	public void AddBook()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().log().all().header("Content-Type" , "application/json").body(Payloads.addBook("abcd","123"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
		
		JsonPath js = new JsonPath(resp);
		System.out.println(js.getString("ID"));
		
		
		
		
		
		
	}
	
	
}
