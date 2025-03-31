package Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pOJO.Login;
import pOJO.LoginResponse;
import pOJO.OrderDetails;
import pOJO.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcomEndToEnd {
	
	//request url from network tab is considered not the UI 
	//Payload use the View parsed - that has all key value pairs required n din required format
	//we can send data in POST request via Form Data as well use bulk post 
	// for authorization added token in the header check key value pair with developer
	//for specific user need to add item so add that specific user ID 
	// form-data need to be sended as param if in text format 
	// if in multimedia format then multipart
	// body to use to send if some json is there else sended using form data 
	// we authorization and we dont authentication 
	// to bypass SSL certification use .setRelaxedHTTPSValidation()
	
	@Test
	public void EcomFLow() {
		
	RequestSpecification loginspec	 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build().log().all();
		
	Login lg = new Login();
	lg.setUserEmail("postman@gmail.com");
	lg.setUserPassword("Hello@123");
	
	
	RequestSpecification loginrequest	= given().spec(loginspec).body(lg);
	
	LoginResponse loginresponse = loginrequest.when().post("/api/ecom/auth/login")
	.then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);
	String token=loginresponse.getToken();
	String userId = loginresponse.getUserId();
	System.out.println("***********************************");
	System.out.println(token);
	System.out.println(userId);
	System.out.println(loginresponse.getMessage());
	System.out.println("***********************************");
	System.out.println("-------------------------------------------------------");
	
	RequestSpecification AddProductSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token).build().log().all();
	// No body is there only form parameter is available sending using param 
	RequestSpecification addProductRequest = given().spec(AddProductSpec).param("productName", "Newla")
			.param("productAddedBy", userId).param("productCategory", "fashion").param("productSubCategory", "shirt")
			.param("productPrice", "100000").param("productDescription", "AdidasOriginals").param("productFor", "women")
			.multiPart("productImage",new File("C:\\Users\\Hanuman Ji\\Downloads\\lionimage.jpeg"));
	
	String addProductResponseString = addProductRequest.when().post("/api/ecom/product/add-product")
	.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js = new JsonPath(addProductResponseString);
	String ProductId = js.getString("productId");
	System.out.println("***********************************");
	System.out.println(ProductId);
	System.out.println("***********************************");
	System.out.println("-------------------------------------------------------");
	
	RequestSpecification createOrderSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON).addHeader("Authorization", token).build().log().all();
	
	OrderDetails ordtl = new OrderDetails();
	ordtl.setCountry("India");
	ordtl.setProductOrderedId(ProductId);
	List <OrderDetails> l = new ArrayList<OrderDetails>();
	l.add(ordtl);
	Orders ord = new Orders();
	ord .setOrderDetails(l);
	
	RequestSpecification createOrderRequest = given().spec(createOrderSpec).body(ord);
	
	String createOrderResponseString = createOrderRequest.when().post("/api/ecom/order/create-order")
	.then().log().all()/*.assertThat().statusCode(201)*/.extract().response().asString();
	
	System.out.println(createOrderResponseString);
	
	RequestSpecification deleteProductSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON).addHeader("Authorization", token).build().log().all();
	
	RequestSpecification deleteRequest = given().spec(deleteProductSpec).pathParam("productId", ProductId);
	
	String deleteResponseString = deleteRequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath js1 = new JsonPath(deleteResponseString);
	
	String DeleteresponseMessage = js1.getString("message");
	Assert.assertEquals(DeleteresponseMessage, "Product Deleted Successfully");
	
	
	
	
	
		
	}
	
	

}
