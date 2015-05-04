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

public class user_topic_graph { 
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
	  String line = pw.readLine();    
	  Document doc;
	  int cnt = 0,i=0;
	  int userid;
	  HashMap<Integer, ArrayList<ts >> map = new HashMap<Integer,ArrayList<ts>>();
	    HashMap<Integer,Integer> mapper_vertices = new HashMap<Integer,Integer>(); 
	    HashMap<String,Integer> mapper_tags = new HashMap<String,Integer>(); 
	    
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
	              if(Integer.parseInt(doc.select("row").first().attr("CreationDate").split("-")[0])<2014)
	              {  
	                line = pw.readLine();
	          		continue;
	              }
	              if(Integer.parseInt(doc.select("row").first().attr("CreationDate").split("-")[0])==2015)
	              {
	              	finish=1;
	              	break;
	              }
//	               System.out.println(Integer.parseInt(doc.select("row").first().attr("OwnerUserId")));
	              userid = Integer.parseInt(doc.select("row").first().attr("OwnerUserId"));
	              String[] tags = doc.select("row").first().attr("Tags").replaceAll("><"," ").replaceAll("[<|>]","").split(" ");
//	              cnn = cnn+tags.length;
	              for(String t:tags)
            	  {
	            	  if(!mapper_tags.containsKey(t))
	                  { 
	                		mapper_tags.put(t,y);
	                		y++;         
	                  } 
            	  }
	              if(!mapper_vertices.containsKey(userid))
	              {
	            	  mapper_vertices.put(userid,x);
	            	  ArrayList<ts > temp = new ArrayList<ts >();
	            	  for(String t:tags)
	            	  {
	            		  ts te = new ts(mapper_tags.get(t),1); 
	            		  temp.add(te);
	            	  }
	            	  map.put(x,temp);
		        	  x++;
	              }
	              else
	              {
	            	  int yx = mapper_vertices.get(userid);
	            	  ArrayList<ts > temp = map.get(yx);
	            	  for(String t:tags)
	            	  {
	            		  int found = 0 ;
	            		  for(ts te:temp)
	            		  {
	            			 if(te.tag == mapper_tags.get(t))
	            			 {
	            				 found=1;
	            				 te.cnt++;
	            			 }
	            		  }
	            		  if(found==0)
	            		  {
	            			  ts te = new ts(mapper_tags.get(t),1); 
		            		  temp.add(te);
	            		  } 
	            	  }
	            	  map.put(yx,temp);
	              }
	           }
	        catch(Exception e){
	          if(line.contains("</posts>"))
	            { 
	        	  finish=1;
//	        	  PrintWriter pw1 = new PrintWriter("2014_topics.txt");
//	    		  PrintWriter pw2 = new PrintWriter("2014_userid_.txt");
//	    		  pw1.println(mapper_vertices.size());
//	    		  for(Map.Entry<Integer,Integer> entry : mapper_vertices.entrySet()) 
//	    			{
//	    			  Integer key = entry.getKey();
//	    			  Integer value = entry.getValue(); 
//	    			  pw1.println(key + "\t" +value );
//	    			} 
//	    		  pw1.println(mapper_vertices.size());
//	    		  for(Entry<String, Integer> entry : mapper_tags.entrySet()) 
//	    			{
//	    			  String key = entry.getKey();
//	    			  Integer value = entry.getValue(); 
//	    			  pw2.println(key + "\t" +value );
//	    			} 
////	        	  for(Map.Entry<Integer, ArrayList<ts > > entry : map.entrySet()) 
////	    			{
////	    			  int key = entry.getKey();
////	    			  ArrayList<ts > value = entry.getValue(); 
////	    			  for(ts te:value)
////	    				  {
////	    				  	counter = counter + te.cnt;
////	    				  	
////	    				  }
////	    			}
//	        	  pw1.close();
//	        	  pw2.close();
//	        	  // 23323920 , 1474291 , 38196, 
//	        	  System.out.println(counter+" "+cnn);
	        	  break; 
	            }
	        }
	    line = pw.readLine();
	  }
	   if(finish==1)
	   {
		   PrintWriter pw1 = new PrintWriter("2014_topics.txt");
 		  PrintWriter pw2 = new PrintWriter("2014_userid_.txt");
 		  pw1.println(mapper_vertices.size());
 		  for(Map.Entry<Integer,Integer> entry : mapper_vertices.entrySet()) 
			{
			  Integer key = entry.getKey();
			  Integer value = entry.getValue(); 
			  pw1.println(key + "\t" +value );
			}
 		  pw1.flush();
 		  pw2.println(mapper_tags.size());
 		  for(Entry<String, Integer> entry : mapper_tags.entrySet()) 
 			{
 			  String key = entry.getKey();
 			  Integer value = entry.getValue(); 
 			  pw2.println(key + "\t" +value );
 			}
 		 PrintWriter pw3 = new PrintWriter("2014_user_topic_bi.txt");
		   for(Map.Entry<Integer, ArrayList<ts > > entry : map.entrySet()) 
			{
			  int key = entry.getKey(); 
			  ArrayList<ts > value = entry.getValue(); 
			  for(ts te:value)
			  {
			  	if(te.cnt>=3)
			  		pw3.println(key+"\t"+(te.tag+mapper_tags.size()));				  	
			  }
			}
		   pw1.close();
		   pw3.close();
	   }
	  pw.close();
	}
}
