import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class user_location {
	
	static HashMap<String,Integer> map = new HashMap<String,Integer>();
	static PrintWriter pw1;
	static BufferedReader br;

	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub

        System.setProperty("http.proxyHost", "10.3.100.207");
        System.setProperty("http.proxyPort", "8080");
		br = new BufferedReader(new FileReader("Data/lo"));  
		String line = br.readLine().replaceAll(" ","+");
		line = br.readLine().replaceAll(" ","+");
		String base = "http://maps.googleapis.com/maps/api/geocode/json?address="+line;
		int cnt=0;
		while(line!=null)
		{
			Document doc =null ;        
	        try
	        {
	            doc = Jsoup.connect(base).ignoreContentType(true).get();
	        }
	        catch(Exception e)
	        { 
	            // e.printStackTrace();	            
	        	continue;
	        }
	        JSONObject obj = new JSONObject(doc.text());
	        JSONArray res = obj.getJSONArray("results");
	        JSONObject address = res.getJSONObject(0);
	        String add = address.get("formatted_address").toString();	        

	        if(map.containsKey(add.split(",")[add.split(",").length-1]))
	        {
	        	int i = map.get(add.split(",")[add.split(",").length-1]);
	        	map.put(add.split(",")[add.split(",").length-1],i+1);
	        }
	        else
	        {	        	
	        	map.put(add.split(",")[add.split(",").length-1],1);
	        }
	        try
	        {
	        	line = br.readLine().replaceAll(" ","+");
				base = "http://maps.googleapis.com/maps/api/geocode/json?address="+line;
	        }
	        catch(Exception ex){
	        	break;
	        }
			if(cnt%10==0)
			{
				System.out.println(cnt);
			}
	        cnt++;
		}		
		System.out.println(map.toString());
	}
}
