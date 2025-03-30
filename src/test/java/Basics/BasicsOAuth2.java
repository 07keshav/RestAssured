package Basics;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;


// to access restricted APis we nee dto have access token 
// to get that Access token we have authorization server from where we get after providing grant Types

public class BasicsOAuth2 {


	public static void main(String[] args) throws InterruptedException {

	// TODO Auto-generated method stub

	String response =

	                given() 

	   

	                   .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

	                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

	                        .formParams("grant_type", "client_credentials")

	                        .formParams("scope", "trust")

	       

	                       

	                        .when().log().all()

	                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asPrettyString();

	System.out.println(response);

	JsonPath jsonPath = new JsonPath(response);

	    String accessToken = jsonPath.getString("access_token");

	    System.out.println(accessToken);

	String r2=    given()

	.queryParams("access_token", accessToken)

	.when()

	           .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

	.asPrettyString();

	System.out.println(r2);
	}
}
