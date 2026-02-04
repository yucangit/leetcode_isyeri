package pack1;


import java.util.*;
import java.io.*;


public class ScannerSample1 {
	
    public static void main(String []argh){
        
    	Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        
        int a=0,b=0,c=0;
        for(int i=0;i<t;i++)
        {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
        }
        in.close();
        
        System.out.println("t=" + t + "a = " + a + " b=" + b + "  c=" +c);
    }
}
