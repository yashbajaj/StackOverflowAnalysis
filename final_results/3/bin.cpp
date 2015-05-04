#include <cstdio> 
#include <fstream>
#include <iostream>
#include <set>
#include <iterator>
#include <vector>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <map>

using namespace std;

int main()


{

	FILE*fp = (FILE*)fopen("responses.txt","r");
	//FILE*sp = (FILE*)fopen("tag_dist.txt","r");
	//map<string,int> mapper;
	char name[100];
	double minutes;
	//int questions;
	int bin[15] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	// while(!feof(sp))
	// {
	// 	fscanf(sp,"%s %d\n",name,&questions);
	// 	mapper.insert(pair<string,int>(name,questions));
	// }

	//cout << mapper.size() << endl; exit(0);
	int count=0;
	while(!feof(fp))
	{
		//if(count == 2) exit(0);
		fscanf(fp,"%lf\n",&minutes);
		//cout << minutes << name ;
		// if(mapper.find(name) != mapper.end())
		// {
		//cout << count;
	//	minutes = minutes / ( (mapper.find(name))->second );

		if(minutes== 0 )
			bin[0]++;
		else if(minutes > 0 && minutes < 0.3)
			bin[1]++;
		else if(minutes >=0.3 && minutes < 0.6)
			bin[2]++;
		else if(minutes >=0.6 && minutes < 1)
			bin[3]++;
		else if(minutes >=1 && minutes < 1.25)
			bin[4]++;
		else if(minutes >=1.25 && minutes < 1.5)
			bin[5]++;
		else if(minutes >=1.5 && minutes < 1.75)
			bin[6]++;
		else if(minutes >=1.75 && minutes < 2)
			bin[7]++;
		else if(minutes >=2 && minutes < 2.25)
			bin[8]++;
		else if(minutes >=2.25 && minutes < 2.5)
			bin[9]++;
		else if(minutes >=2.5 && minutes < 3)
			bin[10]++;
		else if(minutes >=3 && minutes < 4)
			bin[11]++;
		else if(minutes >=4 && minutes < 6)
			bin[12]++;
		else if(minutes >=6 && minutes < 10)
			bin[13]++;
		else if(minutes >10)
			bin[14]++;

		// }
		// count++;
	}



	

	for (int i = 0 ; i< 15 ; i++)
	{
		cout << bin[i] << endl;
	}


	return 0;
}