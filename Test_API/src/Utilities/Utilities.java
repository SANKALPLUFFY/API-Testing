package Utilities;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class Utilities
{
	public static Hashtable<Integer, Integer> copiesSold = new Hashtable<Integer,Integer>();
	
	public static Hashtable<Integer, Integer> Price = new Hashtable<Integer,Integer>();
	
	public static Hashtable<Integer, String> Course= 
            new Hashtable<Integer, String>();
	
	public static ArrayList<Integer> eachSale = new ArrayList<Integer>();
	
	public static int totalSale=0;

	public static String JsonParsingMethod(String MethodRespopnse, String path) {
		JsonPath JS = new JsonPath(MethodRespopnse); // JsonPath takes Strig and converts it into jason file, thats why
														// we stored response in string

		String ParsedJsonFileData = JS.get(path);

		return ParsedJsonFileData;

	}

	public static int ComplexJsonParsing_CourseSize(String MethodResponse) {

		JsonPath JS = new JsonPath(MethodResponse);

		int Courses_Size = JS.getInt("courses.size()");

		// System.out.println("Total Courses are:"+Courses_Size);

		return Courses_Size;
	}

	public static int ComplexJsonParsing_PurchaseAnount(String MethodResponse, String path) {
		JsonPath JS = new JsonPath(MethodResponse);

		int PurchaseAmount = JS.getInt(path);

		return PurchaseAmount;
	}

	public static String ComplexJsonParsing_CourseTitle(String MethodResponse, int courses) {
		String firstHalfPath = "courses[";

		String secondHalfPath = "].title";

		for (int i = 0; i < courses; i++) {
			String path = firstHalfPath + i + secondHalfPath;

			JsonPath JS = new JsonPath(MethodResponse);

			String CourseTitle = JS.get(path);

			// courseTitle = Utilities.ComplexJsonParsing_CourseTitle(DummyRequestResponse,
			// path);

			if (i == 0) {
				System.out.println(i + 1 + "st Title is" + " " + CourseTitle);
			} else if (i == 1) {
				System.out.println(i + 1 + "nd Title is" + " " + CourseTitle);
			} else if (i == 2) {
				System.out.println(i + 1 + "rd Title is" + " " + CourseTitle);
			}
		}

		return null;
	}

	public static void ComplexJsonParsing_Course_Prices(String MethodResponse, int courses) {
		String firstHalfPath = "courses[";

		String secondHalfPath = "].title";

		String firstHalfPrice = "courses[";

		String secondHalfPrice = "].price";

		for (int i = 0; i < courses; i++) {
			String path = firstHalfPath + i + secondHalfPath;

			String pricePath = firstHalfPrice + i + secondHalfPrice;

			JsonPath JS = new JsonPath(MethodResponse);

			String CourseTitle = JS.get(path);

			int Course_Price = JS.getInt(pricePath);

			// courseTitle = Utilities.ComplexJsonParsing_CourseTitle(DummyRequestResponse,
			// path);

			if (i == 0) {
				System.out.println(
						i + 1 + "st Title is" + " " + CourseTitle + " " + ", price per unit is:" + " " + Course_Price);
			} else if (i == 1) {
				System.out.println(
						i + 1 + "nd Title is" + " " + CourseTitle + " " + ", price per unit is:" + " " + Course_Price);
			} else if (i == 2) {
				System.out.println(
						i + 1 + "rd Title is" + " " + CourseTitle + " " + ", price per unit is:" + " " + Course_Price);
			}
			
			Price.put(i, Course_Price);
		}
	}

	public static void ComplexJsonParsing_Course_Sale(String MethodResponse, int courses) {
		String firstHalfPath = "courses[";

		String secondHalfPath = "].title";

		String firstHalsSale = "courses[";

		String secondHalfSale = "].copies";

		for (int i = 0; i < courses; i++) {

			String Courses = firstHalfPath + i + secondHalfPath;

			String Copies_Sold = firstHalsSale + i + secondHalfSale;

			JsonPath JS = new JsonPath(MethodResponse);

			String CourseTitle = JS.get(Courses);

			// JS = new JsonPath(MethodResponse);

			int Copies = JS.getInt(Copies_Sold);

			System.out.println("For the course" + " " + CourseTitle + "," + " " + Copies + " " + "copies are sold");
			
			copiesSold.put(i,Copies);
			
			Course.put(i,CourseTitle);
		}
	}

	public static void totalSaleOfAllCourses(String MethodRespopnse)
	{
		int x = copiesSold.size();
		
		int y = Price.size();
		
		int z = Course.size();
		
		if(x==y&&y==z&&x==z)
		{
			for(int i=0;i<x;i++)
			{
				System.out.println("For the course"+" "+Course.get(i)+" "+"total copies sold are:"+" "+copiesSold.get(i)+" ,and price for each:"
						+ " "+Price.get(i));
				
				int sumIndividual = copiesSold.get(i) * Price.get(i);
				
				System.out.println("Total sale for"+" "+Course.get(i)+" "+"is"+" "+sumIndividual);
				
				eachSale.add(sumIndividual);
			}
		}
		else
		{
			
		}
		
		for(int i=0;i<eachSale.size();i++)
		{
			int a = eachSale.get(i);
			
			int temp = a;
			
			totalSale = totalSale+temp;
			
		}
		
		System.out.println("Total sale of all courses is:"+" "+totalSale);
		
		JsonPath JS = new JsonPath(MethodRespopnse);
		
		int purchaseAmount = JS.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(totalSale, purchaseAmount);
		
		System.out.println("Total purchase amount from Json response is:"+" "+purchaseAmount);
	}
}
