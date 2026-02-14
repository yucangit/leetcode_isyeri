package pack1;

import java.math.BigInteger;

public class Utils 
{
	public char intToChar(int num) 
	{
		//int i = 25;
		char ch = (char) ('a'+num);
		
		return ch;
	}
	
	 public static int binaryToInt(String binary) 
	 {
		 int result =0;
		 int mul = 1;
		 
		 int size= binary.length();
		 for(int i=size-1; i>=0; i--) 
		 {
			 int bit = binary.charAt(i)=='1'?1:0;
			 result += mul*bit;
			 mul*=2;
		 }
		 
		 return result;
	 }
	 
	 
	 public static long binaryToNumber(String binary) 
	 {
		 long result =0;
		 long mul = 1;
		 
		 int size= binary.length();
		 
		 for(int i=size-1; i>=0; i--) 
		 {
			 int bit = binary.charAt(i)=='1'?1:0;
			 result += mul*bit;
			 mul*=2;
		 }
		 
		 return result;
	 }
	 
	 public static BigInteger binaryToNumber1(String binary) 
	 {		 
		 
		 BigInteger result = new BigInteger(binary, 2);				 
		 
		 return result;
	 }
	 
	 
}
