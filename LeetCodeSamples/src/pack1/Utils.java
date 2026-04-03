package pack1;

import java.math.BigInteger;

public class Utils 
{
	public char intToChar(int num) 
	{		
		char ch = (char) ('a'+num);
		
		return ch;
	}

	public static String intToBinary(int n) 
	{
		String result="";
		
		while(n>1) 
		{
			result =  n%2 + result;
			n/=2;
		}
		result = n+ result;
		
		return result;
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
	 	 
	public static int getIndex(int [] arr, int val) 
	{
		 int idx = -1;
		 for(int i=0; i<arr.length; i++) 
		 {
			 if(arr[i]==val) 
			 {
				 idx = i;
				 break;
			 }
		 }
		 return idx;
	}
	 
	public static int getIndex(Integer [] arr, int val) 
	{
		 int idx = -1;
		 for(int i=0; i<arr.length; i++) 
		 {
			 if(arr[i]==val) 
			 {
				 idx = i;
				 break;
			 }
		 }
		 return idx;
	}

	public static boolean isNumber(String str) 
	{
		return str.matches("^\\d+$");
	}
}
