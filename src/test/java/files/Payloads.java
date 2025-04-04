package files;

public class Payloads {
	//return in String since it will be passed within body and that requires Payload in string format
	//making the method static so that directly use without creating object
	
	/*Advance payload cReation Strategies
	 * 
	 * 	1.Dynamically Build JSON with external data Inputs
	 *  2.Parameterize the API Tests with multiple data sets.
	 *  3.How to send static JSON files(payloads) directly into Post method of RestAssured
	 *  4.Feed JSON Payload from using Excel with HAshMAp
	 *  5.Using POJO classes via serialization and deserialization
	 * 
	 */

	
	
	public static String addPlace()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"keshavs house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}

	public static String courseprice()
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, String aisle)
	{
		String s ="{\r\n"
				+ "    \"name\": \"Learn Appium Automation wigdrtgth Java\",\r\n"
				+ "    \"isbn\": \""+isbn+"\",\r\n"
				+ "    \"aisle\": \""+aisle+"\",\r\n"
				+ "    \"author\": \"John foe\"\r\n"
				+ "}";
		
		return s;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
