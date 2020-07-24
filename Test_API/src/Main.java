import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.Hashtable;
import java.util.Random;

import Files.Body;
import Files.RandomString;

public class Main 
{
	public static void main(String args[]) throws Exception
	{
		// setting base URL
		
		JsonPath JS;
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String POSTResponse = given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body(Body.PostBody())
		
		//here using POST method
		.when().post("maps/api/place/add/json")
		
		//here validating the header response & extracting the response
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		/* equalTo method comes from static pachage hamcrest --> Matchers, so need to import it manually*/
		
		// Next goal is to add a place, update the address and the get the updated details back
		
		 JS = new JsonPath(POSTResponse); // JsonPath takes Strig and converts it into jason file, thats why we stored response in string
		
		String Place_ID = JS.get("place_id");
		
		System.out.println("Place id is: "+""+Place_ID);
		
		//calling put method to change address for recently generated place ID
		
		Hashtable<Integer,String> table = new Hashtable<Integer,String>();
		
		table.put(0, "USA");
		
		table.put(1, "Australia");
		
		table.put(2, "Canada");
		
		table.put(3, "Russia");
		
		table.put(4, "India");
		
		Random random = new Random();
		
		int randomIndex = random.nextInt(table.size());
		
		String Country = table.get(randomIndex);
		
		String new_Address = RandomString.randomString()+","+RandomString.randomString()+","+Country;
		
		String PUTResponse = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
		.body(Body.PUTBody(Place_ID,new_Address))
		
		.when().put("/maps/api/place/update/json")
		
		.then().assertThat().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).header("Content-Length", "38")
		.extract().response().asString();
		
		JS = new JsonPath(PUTResponse);
		
		String PUT_Message = JS.get("msg");
		
		//System.out.println("JSON PUT Response is:"+" "+PUT_Message);
		
		//in get request we are not sending any body so don't neet to give header in request
		
		//-------------------------------------------------------working code
		
		/*Response response = 
				given().log().all().queryParam("place_id", Place_ID)
				.queryParam("Key", "qaclick123")
				
				.when().get("maps/api/place/get/json")
				
				.then().log().all().extract().response();
		
		System.out.println(response.getStatusCode());
		
		System.out.println(response.asString()); */
		
		String string="";
			string = given().log().all().queryParam("place_id", Place_ID)
			.queryParam("key", "qaclick123")
			
			.when().get("maps/api/place/get/json")
			
			.then().log().all().extract().response().asString();
		
		JsonPath JS1 = new JsonPath(string);
		
		String update = JS1.getString("address");
		
		System.out.println(update);
		
		System.out.println(new_Address);
		
		if(new_Address.contentEquals(update))
		{
			System.out.println("Updated address is retrived from GET method");
		}
		else {
			throw new Exception();
		}
		
		
	}
}
