
    
#include <stdio.h>
#include <string.h>
#include <stdlib.h>



char* concat(char* s1, char* s2){
    char* a = malloc(strlen(s1)+strlen(s2)+1);
    strcpy(a,s1);
    strcat(a,s2);
    return a;
}   

void printInt(int d){

print("%d\n",d );

}

char* input(char* msg, int buffer){
    
    printf("%s\n", msg);
    char* buff = malloc(buffer);
    fgets(buff, buffer, stdin);
    return buff;
}

printf()
int main(){    

concat("hello bob", " hello Jill") ;   
return 0;   
}  