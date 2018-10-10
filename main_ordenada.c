#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
	FILE *arquivoTotal;
	FILE *ptr;
	
	arquivoTotal = fopen("Arquivo Completo.txt", "w+");
	int num=3;
	char i[10];
	char nome[50]={};
	char aux[150];
	
	
	snprintf(i, 10, "%d", num);


	strcpy(nome, "arquivo");
	strcat(nome, i);
	strcat(nome, ".txt");
	
	//ptr = fopen(nome, "r");
	//fscanf(ptr, "%s", aux);
	//fprintf(arquivoTotal, "%s", aux);
	
	printf("%s\n", nome);
	ptr = fopen(nome, "r+");
	/********************/
	
	fscanf(ptr, "%s", aux);
	fprintf(arquivoTotal, "%s", aux);
	fprintf(arquivoTotal, "%s", " ");
	
	
	//tenta abrir o arquivo antes do laco
	while(ptr = fopen(nome, "r+")){
	printf(" Oi ");
		//se conseguir guarda os dados aqui
		
		fscanf(ptr, "%s", aux);
		fprintf(arquivoTotal, "%s", aux);
		fprintf(arquivoTotal, "%s", " ");
		
		while(fscanf(ptr, "%s", aux)!=EOF){
			fprintf(arquivoTotal, "%s", aux);
			fprintf(arquivoTotal, "%s", " ");
		}
		
		fprintf(arquivoTotal, "%s", "\n");
	
		num++;
		snprintf(i, 10, "%d", num);
		strcpy(nome, "arquivo");
		strcat(nome, i);
		strcat(nome, ".txt");
		ptr = fopen(nome, "r+");
		
		printf("%s\n", nome);
		printf("1");
	}
	return 0;
}
