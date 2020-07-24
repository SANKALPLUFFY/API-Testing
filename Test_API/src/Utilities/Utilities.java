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
}
