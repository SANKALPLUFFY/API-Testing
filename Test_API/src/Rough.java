import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Rough 
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Response response = RestAssured.given().queryParam("place_id","55316522ba1e82928e2aeb7d321189f2")
				.queryParam("key","qaclick123")//.header("Content-Type","Application/json")
				.log().all()
				.when().get("/maps/api/place/get/json")
		.then().log().all()
		.extract().response();
		//System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}
}
