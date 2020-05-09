package com.swm.util;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
public class TestBaseAPIUtil 
{	
	
	//1. Get Method
	public void get(String url) throws ClientProtocolException, IOException
	{
		//Creates CloseableHttpClient instance with default configuration.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Creates a GET Connection
		HttpGet httpGet = new HttpGet(url); //url is a http get request
		
		//http Response
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// hit the get URL
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(" Status Code received is " + statusCode);
		
		// Json Response
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(responseString);
		System.out.println("Response JSON received agter GET Method " + jsonResponse);
		
		// Headers in Response
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		for(Header header:headerArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}		
		System.out.println("Headers in the response : "+allHeaders);		
	}
	
	public String getValuebByJPath(JSONObject responseJson, String jPath)
	{
		
		Object obj = responseJson;
		for(String s : jPath.split("/"))
		{
			if(!s.isEmpty())
			{
				if(!(s.contains("[")) || (s.contains("]")))
				{
					obj = ((JSONObject)obj).get(s);
				}
				else if((s.contains("[")) || (s.contains("]")))
				{
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).
							get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}
			}
		}
		return obj.toString();
	}
}
