import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class tag_reputation { 
	public static class ts{
			public int tag;
			public int cnt;
			public ts(int s,int i)
			{
				this.cnt =i;
				this.tag =s;
			}
		}
	  public static void main(String[] args) throws Exception{

	  BufferedReader pw = new BufferedReader(new FileReader("/home/bt3/11CS10034/Posts.xml"));
	  BufferedReader bw = new BufferedReader(new FileReader("Users_Reputation_High.txt"));

	  PrintWriter  tr = new PrintWriter("Rep_count.txt");

	  String line = pw.readLine();
	  String pline = bw.readLine();

	  ArrayList<Integer> user_list;
	  while(pline!= null)
	  {
	  	user_list.add(Integer.parseInt(pline));
	  	pline = bw.readLine();
	  }



	  Document doc;
	  int cnt = 0,i=0;
	  int userid;
	  HashMap<Integer, ArrayList<ts >> map = new HashMap<Integer,ArrayList<ts>>();
	  HashMap<Integer,Integer> mapper_vertices = new HashMap<Integer,Integer>(); 
	  HashMap<String,Integer> mapper_tags = new HashMap<String,Integer>(); 
	  HashMap<String,Integer> tag_count = new HashMap<String,Integer>();

	    int x=0,y=0,cnn=0;
	    long counter=0;
	    int finish=0;
	    while(line!= null&&finish==0)
	  {
	
	    cnt++;
	    if(cnt%100000 == 0)
	      System.out.println(cnt+" "+mapper_vertices.size());
	    doc = null;
	  
	        try{
	              doc = Jsoup.parse(line);
	              
	              if((Integer.parseInt(doc.select("row").first().attr("PostTypeId"))!= 1) || doc.select("row").first().attr("OwnerUserId") == null || Integer.parseInt(doc.select("row").first().attr("OwnerUserId")) == -1 ) 
	                { 
	                  line = pw.readLine();
	                  continue;
	                }
	              
	              userid = Integer.parseInt(doc.select("row").first().attr("OwnerUserId"));
	              if(!tag_reputation.contains(userid)){
	              	line = pw.readLine();
	              	continue;
	              }
	              String[] tags = doc.select("row").first().attr("Tags").replaceAll("><"," ").replaceAll("[<|>]","").split(" ");
	              for(String t:tags)
            	  {
	            	  
	                  if(tag_count.containsKey(t))
	                  {
	                  	tag_count.put(t,tag_count.get(t)+1);
	                  }
	                  else
	                  {
	                  	tag_count.put(t,1);
	                  }
            	  }
	             
	           }
	        catch(Exception e){
	          if(line.contains("</posts>"))
	            { 
	        	  finish=1;
	        	  break; 
	            }
	   			}
	    line = pw.readLine();
	  }
	   
		   
	  pw.close();

	  // sort hashmap based on value
	  // use tr printwriter in file..
	}
}
