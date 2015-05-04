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

#include "pugixml.hpp"



using namespace std;
using namespace pugi;

xml_document doc;
xml_parse_result result;
xml_node posts,row;


int main(int argc,char* argv[])
{

	int i=0;
	int post_type;
	char tim[5]; 
	string line;
	ifstream myfile( "test.xml" );
 	while (getline( myfile, line ))   
	{  
		string str1="<posts>\n";
		string str2="\n</posts>";
		str1.append(line);
		str1.append(str2);
		cout << str1 << endl;

		const char *source = str1.c_str();
		size_t size = sizeof(source);
		 
		result = doc.load_buffer(source, size);
		cout << result.description() << endl;
	}
    myfile.close();
	
	posts = doc.child("posts");
	int year;
	cout << "Please enter the year" << endl;
	cin >> year;
	int current_year;
	char s[1000];
	char* pch;
	vector<string > mapper_vertices;
	vector< string > previous ;
	vector<string>::iterator it,git;
	
	vector< int > next;
	vector<int >::iterator int_it,int_git;


	set< pair<int,int> > mapper_edges ;
	set< pair<int,int> >::iterator edge_it;
	pair<int,int> tuple;


	// for(row = posts.child("row"); row ;row =row.next_sibling("row"))
	// {
	// 	previous.clear();
	// 	next.clear();
	// 	post_type = atoi(row.attribute("PostTypeId").value());
	// 	if(post_type != 1) continue;
	// 	strncpy(tim,row.attribute("CreationDate").value(),4);
	// 	tim[5] = '\0';
	// 	current_year = atoi(tim);
	// 	if(year!=current_year) continue;
	// 	strcpy(s,row.attribute("Tags").value());
	// 	pch = strtok(s," <>");
	// 	while(pch!= NULL)
	// 	{
	// 		previous.push_back(pch);
	// 		pch = strtok(NULL," <>");
	// 	}
	// 	for(it=previous.begin();it!= previous.end();it++)
	// 	{
	// 		git = find(mapper_vertices.begin(),mapper_vertices.end(),*it);
	// 		if(git == mapper_vertices.end()){
	// 			mapper_vertices.push_back(*it);
	// 		}
	// 		git = find(mapper_vertices.begin(),mapper_vertices.end(),*it);
	// 		next.push_back(distance(mapper_vertices.begin(),git));
 
	// 	}
	// 	for(int_it = next.begin();int_it != next.end() && next.size() != 1;int_it++)
 //    		{
 //      			for(int_git = int_it+1; int_git < next.end();int_git++)
 //      					{
 //        					tuple.first = *int_git ;
 //       						tuple.second = *int_it;
 //       						if(mapper_edges.find(tuple) == mapper_edges.end())
 //       						{
 //       						tuple.first = *int_it ;
 //       						tuple.second = *int_git;	
 //       						mapper_edges.insert(tuple);
 //       						}
 //     					 }
 //    		}

	// //	cout << endl;
	// 	i++;

	// }
	// cout << "rows  " << i << endl; 
	// FILE* f1 = fopen(argv[1],"w");
  
 // 	for(edge_it=mapper_edges.begin();edge_it!=mapper_edges.end();edge_it++)
 // 	{
 // 		fprintf(f1,"%d\t%d\n",(*edge_it).first,(*edge_it).second);
 //  	}


	// FILE* f2 = fopen(argv[2],"w");
	// for(i = 0;i<mapper_vertices.size();i++)
	// {
 //  		fprintf(f2,"%d\t%s\n",i,mapper_vertices[i].c_str() );
	// }
	return 0;
}