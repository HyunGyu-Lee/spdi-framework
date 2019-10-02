package com.mvcsupporter.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
	
	private static final JSONParser jParser = new JSONParser();
	
	public static JSONObject json(String jsonString) {
		JSONObject jObj = null;
		try
		{
			jObj = (JSONObject)jParser.parse(jsonString);
		}
		catch (ParseException e)
		{
			return null;
		}
		return jObj;
	}
	
	public static String readJsonFile(String jsonFile) {
		try
		{
			return new String(Files.readAllBytes(new File(jsonFile).toPath()));
		}
		catch (IOException e)
		{
			return null;
		}
	}
	
}
