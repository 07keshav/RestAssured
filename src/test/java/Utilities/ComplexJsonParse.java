package Utilities;

import io.restassured.path.json.JsonPath;


import org.testng.Assert;

import files.Payloads;

//This will Act as mocking of API .
//So we are have got sample response now taking that response and validating the outcomes.---is mocking

//getInt will take input in string format but that format has some thing numeric to get printed 
//hence jsonpath has size() method to get the size of an array.
// if the value is of int type hen get INT () will easily provide value
//passing dynamic values in jsonpath is  by using "+value+"
//IN point 3 .toString to get Print the string type if dont want to store in variable
// without using main method through TestNG simply use @Test and wrap all stmnts within a method-- will also work

public class ComplexJsonParse {

	public static void main(String[] args) 
	{
		JsonPath js = new JsonPath(Payloads.courseprice());
	
	//	1. Print No of courses returned by API

	
	int coursesize = js.getInt("courses.size()");
	System.out.println("course size :: "+coursesize);
	
	
		//	2.Print Purchase Amount

	int purchAmt = js.getInt("dashboard.purchaseAmount");
	System.out.println("purchase Amt :: "+purchAmt);
	
	
		//	 3. Print Title of the first course
	
	String firstTitle = js.getString("courses[0].title");
	System.out.println("First Title :: "+ firstTitle);
	System.out.println("First Title :: "+ js.get("courses[0].title").toString());

	//	4. Print All course titles and their respective Prices
	
	for(int i=0; i<coursesize; i++)
	{
		System.out.println("Title :: "+ js.getString("courses["+i+"].title") );
		System.out.println("Price :: "+js.getString("courses["+i+"].price"));
		System.out.println("*********************");
	}

		//5. Print no of copies sold by RPA Course
	
	for(int i=0; i<coursesize; i++)
	{
		if( js.getString("courses["+i+"].title") .equalsIgnoreCase("RPA")) {
			System.out.println("Price :: "+js.getString("courses["+i+"].price"));
			break;}//since the index will be different every time and once found we dont want to iterate further.
	}

		//6. Verify if Sum of all Course prices matches with Purchase Amount
	int sum =0;
	for(int i=0; i<coursesize; i++)
	{
		
		 int price = js.getInt("courses["+i+"].price");
		 int copies = js.getInt("courses["+i+"].copies");
		 sum += price*copies;
		
	}
	System.out.println(sum);
	Assert.assertEquals(sum,purchAmt);
	
	
	}
	
}
