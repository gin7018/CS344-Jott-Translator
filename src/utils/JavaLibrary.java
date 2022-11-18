package utils;

import java.util.Scanner;

public class JavaLibrary {
public static void print(Object o){
    System.out.println(o);
}    

public static String concat(String s1,String s2){
    return s1 +s2;
}

public static String input(String msg,int buffer){
    System.out.println(msg);
    Scanner reader = new Scanner(System.in);
    String out = reader.nextLine();
    reader.close();
    return out;
}

public static int length(String string){
    return string.length();
}


}

