import Utilities.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Parsing_Complex_JSON {
	public static void main(String[] args) {
		// Taking dummy json request response and parsing it to fetch contents

		/*
		 * {
		 * 
		 * "dashboard": {
		 * 
		 * "purchaseAmount": 910,
		 * 
		 * "website": "rahulshettyacademy.com"
		 * 
		 * },
		 * 
		 * "courses": [
		 * 
		 * {
		 * 
		 * "title": "Selenium Python",
		 * 
		 * "price": 50,
		 * 
		 * "copies": 6
		 * 
		 * },
		 * 
		 * {
		 * 
		 * "title": "Cypress",
		 * 
		 * "price": 40,
		 * 
		 * "copies": 4
		 * 
		 * },
		 * 
		 * {
		 * 
		 * "title": "RPA",
		 * 
		 * "price": 45,
		 * 
		 * "copies": 10
		 * 
		 * }
		 * 
		 * ]
		 * 
		 * }
		 */
		
/*
1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
*/
		
		String DummyRequestResponse = "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n"
				+ "\"purchaseAmount\": 910,\r\n" + "\r\n" + "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "\"courses\": [\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n" + "\r\n" + "\"copies\": 6\r\n"
				+ "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Cypress\",\r\n" + "\r\n"
				+ "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n" + "\r\n" + "\"copies\": 10\r\n" + "\r\n"
				+ "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}";
		
		//1. Print No of courses returned by API
		
		//int courseSize_Response = Utilities.ComplexJsonParsing_CourseSize(DummyRequestResponse);
		
		//System.out.println("No of courses returned by API are:"+""+courseSize_Response);
		
		//2.Print Purchase Amount, purchase amount fall under dashboard-->purchase amount
		// parent-child path is given as "PARENT.CHILD"
		
		int courseSize_Response = 0;
		
		int PurchaseAmount=0;
		
		String courseTitle=null;
		
		for(int j=1;j<7;j++)
		{
			switch(j)
			{
				case 1: //1. Print No of courses returned by API
					
					courseSize_Response = Utilities.ComplexJsonParsing_CourseSize(DummyRequestResponse);
					
					System.out.println("No of courses returned by API are:"+""+courseSize_Response);
				
					break;
				
				case 2: //2.Print Purchase Amount
					//purchase amount fall under dashboard-->purchase amount
					// parent-child path is given as "PARENT.CHILD"
					
					PurchaseAmount = Utilities.ComplexJsonParsing_PurchaseAnount(DummyRequestResponse, "dashboard.purchaseAmount");
					
					System.out.println("Purchase amount is:"+""+PurchaseAmount);
					
					break;
				 
				case 3://3. Print Title of the first course
					
					//For particular course title we need to provide "course index.courseName" as path
					
					Utilities.ComplexJsonParsing_CourseTitle(DummyRequestResponse, courseSize_Response);
					
					break;
					
				case 4://4. Print All course titles and their respective Prices
					
					Utilities.ComplexJsonParsing_Course_Prices(DummyRequestResponse, courseSize_Response);
					
					break;
					
				case 5://5. Print no of copies sold by each Course
					
					Utilities.ComplexJsonParsing_Course_Sale(DummyRequestResponse, courseSize_Response);
					
					break;
					
				case 6://6. Verify if Sum of all Course prices matches with Purchase Amount
					
					Utilities.totalSaleOfAllCourses(DummyRequestResponse);
			}
		}
	}
}
