#include <igraph.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


void create_graph(igraph_t * g)
{
	int i, nVertices=0, nEdges=0, a, b, wt;
	
	scanf("%d %d",&nVertices, &nEdges);

	igraph_vector_t v;
	igraph_vector_init (&v, 2*nEdges);
	for(i=0;i<2*nEdges;i+=2){
		scanf("%d %d",&a,&b);
		if(a>=nVertices || b>=nVertices){
			printf("vertex id overflow : %d-%d\n",a,b);
			break;
		}
		VECTOR(v)[i]=a;
		VECTOR(v)[i+1]=b;
	}

	igraph_create(g, &v, 0, 0);
	igraph_simplify(g, 1, 0, 0);
}

void getMembershipFromFile(char filename[], igraph_vector_t * membership)
{
	char line[10000];
	FILE * fp;
	fp = fopen(filename, "r");
	VECTOR(*membership)[0] = 0;
	while(fgets(line, 10000, fp))
	{
		char * pch = strtok(line, " \t\r\n");
		int vertex = atoi(pch);
		pch = strtok(NULL, " \t\r\n");
		int comm = atoi(pch);
		VECTOR(*membership)[vertex] = comm;
	}
	fclose(fp);
}

int main(int argc, char * argv[])
{
	int i, nVertices, maxIt;
	//double Netw_perm, result;
	char line[10000];
	FILE *fp;
	igraph_t g;
	int temp;
	FILE*out = (FILE*)fopen("core_out.txt","w");
	igraph_vector_t membership;
	
	create_graph(&g);
	nVertices = igraph_vcount(&g);
	
	igraph_vector_init(&membership, nVertices);
	

	

	temp = igraph_coreness(&g,&membership,IGRAPH_ALL);

	
	for(i=0;i<nVertices;++i)
	{
		fprintf(out, "%d\t%ld\n",i,(long)VECTOR(membership)[i]);
	}
	
	
	
	//To find the NMI with ground truth file:community.dat

	//Compare with ground truth of LFR
	
	
	
	//Free allocated resources
	igraph_vector_destroy(&membership);
	
	igraph_destroy(&g);

	return 0;
}
