package Basics;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pOJO.Api;
import pOJO.CourseDetails;
import pOJO.WebAutomation;

// to access restricted APis we nee dto have access token 
// to get that Access token we have authorization server from where we get after providing grant Types

public class BasicsOAuth2 {

	public static void main(String[] args) throws InterruptedException {

		// TODO Auto-generated method stub
		String[] expected = {"Selenium Webdriver Java","Cypress","Protractor"};
		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.formParams("grant_type", "client_credentials")

						.formParams("scope", "trust")

						.when().log().all()

						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asPrettyString();

		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

		CourseDetails crsDtl = given()

				.queryParams("access_token", accessToken)

				.when()

				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

				.as(CourseDetails.class);

		System.out.println(crsDtl);
		System.out.println(crsDtl.getLinkedIn());
		System.out.println(crsDtl.getExpertise());

		// since get api is list of courses hence we need a specific index so using get
		// method
		// Using this we don't need any knowledge of JSON pATh

		System.out.println(crsDtl.getCourses().getApi().get(1).getCourseTitle());

		// need to properly use data type of Pojo classes
		List<Api> apidtl = crsDtl.getCourses().getApi();
		for (int i = 0; i < apidtl.size(); i++) {
			if (crsDtl.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(crsDtl.getCourses().getApi().get(i).getPrice());
				break;
			}
		}
		List<String> actual = new ArrayList<String>();
		List<WebAutomation> webdtls = crsDtl.getCourses().getWebAutomation();
		for (int i = 0; i < webdtls.size(); i++) {
			System.out.println(webdtls.get(i).getCourseTitle());
			actual.add(webdtls.get(i).getCourseTitle());
		}
		//.equals() will check based on index and the individual content both 
		Assert.assertTrue(Arrays.asList(expected).equals(actual));
		
	}
}
