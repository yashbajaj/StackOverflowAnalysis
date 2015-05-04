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

struct tag {
	double val;
	char tags[100];
};


typedef struct tag node;

bool comparebyval(const node &a, const node &b)
{
	return(a.val > b.val);
}


int main()


{

	FILE*fp = (FILE*)fopen("tag_rep_voter.txt","r");
	vector<node> high,medium,low;
	char name[100];
    double a,b,c,d,sum;
    node x;
	while(!feof(fp)){
		fscanf(fp,"%s %lf %lf %lf %lf\n",name,&a,&b,&c,&d);

		sum = a+b+c+d;
		if(sum==0 || a<0 || b<0 || c<0 || d<0)continue;
		strcpy(x.tags,name);
        a = a/(double)sum;
		x.val = a;
		high.push_back(x);
		b = b/(double)sum;
		x.val = b;
		medium.push_back(x);
		c = c/(double)sum;
		x.val = c;
		low.push_back(x);

	}

 // cout << high.size() << endl << medium.size() << endl << low.size() << endl;

	sort(high.begin(),high.end(),comparebyval);
	sort(medium.begin(),medium.end(),comparebyval);
	sort(low.begin(),low.end(),comparebyval);
	int i;
	cout << "high " << endl;
	for(i=0;i<high.size();i++)
	{
		if(strcmp(high[i].tags,"c#") == 0)
		cout << high[i].val << " "  << endl;
	}
	cout << "medium " << endl;
	for(i=0;i<medium.size();i++)
	{
		if(strcmp(medium[i].tags,"c#") == 0)
		cout << medium[i].val << " " << endl;
	}
	cout << "low " << endl;
	for(i=0;i<low.size();i++)
	{
		if(strcmp(low[i].tags,"c#") == 0)
		cout << low[i].val << " "  << endl;
	}
	return 0;
}