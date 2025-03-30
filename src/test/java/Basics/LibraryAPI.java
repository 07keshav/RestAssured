package Basics;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


// taking static data Directly from file having .json extension for that 
// We need to convert file data -->> byte and then byte data will be converted  -->> string

//whe have Files.readAllBytes()-- and within this we can pass file location but it is always expecting
// Path class object so .. pass your file location into path class and then that path class into readAllBytes
//then pass the entire part into String constuctor as new String (....)

public class LibraryAPI {

	@Test(dataProvider="AddBookData")
	public void AddBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().log().all().header("Content-Type" , "application/json").body(Payloads.addBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
		
		JsonPath js = new JsonPath(resp);
		System.out.println(js.getString("ID"));
		
		
	}
	@DataProvider(name="AddBookData")
	public Object[][] getData()
	{
		Object[][] d= {{"xyz","254"},{"hgf","568"},{"lop","756"}};	
		return d;
		// or use return new Object[][]{{"xyz","254"},{"hgf","568"},{"lop","756"}}
	}
	
	
}
