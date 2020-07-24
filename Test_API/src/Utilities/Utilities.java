package Utilities;

import io.restassured.path.json.JsonPath;

public class Utilities 
{
	public static String JsonParsingMethod(String MethodRespopnse,String path)
	{
		JsonPath JS = 
				new JsonPath(MethodRespopnse); // JsonPath takes Strig and converts it into jason file, thats why we stored response in string
		
		String ParsedJsonFileData = JS.get(path);
		
		return ParsedJsonFileData;
		
	}
	
	public static int ComplexJsonParsing_CourseSize(String MethodResponse)
	{
		
		JsonPath JS = new JsonPath(MethodResponse);
		
		int Courses_Size = JS.getInt("courses.size()");
		
		//System.out.println("Total Courses are:"+Courses_Size);
		
		return Courses_Size;
	}
	
	public static int ComplexJsonParsing_PurchaseAnount(String MethodResponse,String path)
	{
		JsonPath JS = new JsonPath(MethodResponse);
		
		int PurchaseAmount = JS.getInt(path);
		
		return PurchaseAmount;
	}
	
	public static String ComplexJsonParsing_CourseTitle(String MethodResponse,String path)
	{
		JsonPath JS = new JsonPath(MethodResponse);
		
		String CourseTitle = JS.get(path);
		
		return CourseTitle;
	}
}
