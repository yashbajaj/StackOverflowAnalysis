import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AgeBin {
	
	
	static long[] frequency = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ;
	static BufferedReader br;
	static String line;
	public static void main (String[] args) throws IOException{
	br = new BufferedReader(new FileReader("Data/Age.txt"));  
	int value;

	line = br.readLine();
	while(line!= null ){
		
		value = Integer.parseInt(line);
		if(value > 10 && value <= 15) frequency[0]++;
		else if(value > 15 && value <= 20) frequency[1]++;
		else if(value > 20 && value <= 22) frequency[2]++;
		else if(value > 22 && value <= 24) frequency[3]++;
		else if(value > 24 && value <= 26) frequency[4]++;
		else if(value > 26 && value <= 28) frequency[5]++;
		else if(value > 28 && value <= 30) frequency[6]++;
		else if(value > 30 && value <= 32) frequency[7]++;
		else if(value > 32 && value <= 34) frequency[8]++;
		else if(value > 34 && value <= 36) frequency[9]++;
		else if(value > 36 && value <= 38) frequency[10]++;
		else if(value > 38 && value <= 40) frequency[11]++;
		else if(value > 40 && value <= 42) frequency[12]++;
		else if(value > 42 && value <= 44) frequency[13]++;
		else if(value > 44 && value <= 46) frequency[14]++;
		else if(value > 46 && value <= 48) frequency[15]++;
		else if(value > 48 && value <= 50) frequency[16]++;
		else if(value > 50 && value <= 55) frequency[17]++;
		else if(value > 55 && value <= 60) frequency[18]++;
		else if(value > 60 && value <= 80) frequency[19]++;
		else if(value > 80 && value <= 100) frequency[20]++;
		
		
		line = br.readLine();
	}
	int i;
	for(i=0;i<21;i++)
	{
		System.out.println(frequency[i]);
	}
		
	}
	
	

}
