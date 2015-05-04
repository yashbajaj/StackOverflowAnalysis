import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 

public class user_data 
{
	static PrintWriter pw1,pw2,pw3;
	static BufferedReader br;
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		pw1 = new PrintWriter(new FileWriter("Data/Location.txt"));
		pw2 = new PrintWriter(new FileWriter("Data/Location_Reputation.txt"));
		pw3 = new PrintWriter(new FileWriter("Data/Age.txt"));
		br = new BufferedReader(new FileReader("/home/vivek/CN_TP/Users.xml"));  
		String line = br.readLine();
		
		int c1=0,c2=0,c3=0;
		try
		{
			line = br.readLine();
			line = br.readLine();
			while(line!=null)
			{  
				Document doc = (Document) Jsoup.parse(line);
				Element row = ((Element) doc).select("row").first();  
				if(row.attr("Location")!=""&&row.attr("Location")!=null)
				{
					pw1.println(row.attr("Location"));
					pw1.flush();			
					c1++;
				}
				if(row.attr("Location")!=""&&row.attr("reputation")!=""&&row.attr("Location")!=null&&row.attr("reputation")!=null)
				{
					pw2.println(row.attr("Location")+"\n"+row.attr("reputation"));
					pw2.flush();
					c2++;
				}
				if(row.attr("Age")!=""&&row.attr("Age")!=null)
				{
					pw3.println(row.attr("Age"));
					pw3.flush();						
					c3++;
				}						
				line = br.readLine();				  
			}
		}
		catch(Exception e)
		{
			// System.out.println(line);
			// e.printStackTrace();
		}
		System.out.println("Location:\t"+c1+"\nLocation and Reputation:\t"+c2+"\nAge:\t"+c3);
		pw1.close();
		pw2.close();
		pw3.close();
		br.close();
	}
}
