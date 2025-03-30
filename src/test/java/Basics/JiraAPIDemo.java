package Basics;

import org.testng.annotations.Test;
import io.restassured.RestAssured;import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

//we use multipart(key, value) to send any attachment as form parameter of file type
//we use pathparam (key,vaklue) to set a value to a vairble and then apss it in resource using {key}
//Authorization provided in Header 

public class JiraAPIDemo {

	@Test
	public void JiraTest()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy-team.atlassian.net/";		
		String createIssueResponse 	= given()		.header("Content-Type","application/json")		
		.header("Authorization","Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")		
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"Website items are not working- automation Rest Assured- by Keshav Goel\",\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}")		
		.log().all()		
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).contentType("application/json")		
		.extract().response().asString();		 		
		
		JsonPath js = new JsonPath(createIssueResponse);		 
		String issueId = js.getString("id");		 
		System.out.println(issueId);		 		
		
		given()			.pathParam("key", issueId)			
		.header("X-Atlassian-Token","no-check")			
		.header("Authorization","Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")			
		.multiPart("file",new File("C:\\Users\\Hanuman Ji\\Downloads\\ADD-DeletePlaceAPIs.docx")).log().all()			
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);			
							 		 //Add attachment		 		 		 		 		 							}
		
	}
	
}
