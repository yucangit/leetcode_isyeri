package pack1;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;


public class LeetCodeOther {

	public static int reverse(int x) 
	{
		//Durum : Yapildi.
		//Tarih : 06.04.2026 
		
		//String result="";
		/*int y = Math.abs(x);
		
		while( y>0 ) 
		{
			result = result + y%10 ;
			y=y/10;
		}*/
		
		//String y = x+"";
		String result = new StringBuilder((x+"").replace("-", "")).reverse().toString();
				
		
		if(x<0) 
			result = "-" + result;
		
		String maxInt = Integer.MAX_VALUE+"";
		String minInt = Integer.MIN_VALUE+"";		
		
		//out of range control
		boolean isOutOfBound = false;
		
		if(x>0 && result.length()<maxInt.length()) 
		{
			//nothing to do
			System.out.println("");
		}
		else if(x<0 && result.length()<minInt.length()) 
		{
			//nothing to do
			System.out.println("");
		}
		else if(x>0) 
		{
			for(int i=0; i<maxInt.length(); i++) 
			{
				if(result.charAt(i)>maxInt.charAt(i)) 
				{ 
					isOutOfBound=true;
					break;
				}
				else if(result.charAt(i)<maxInt.charAt(i))
				{
					break;
				}
			}
		}		
		else if(x<0)
		{
			for(int i=0; i<minInt.length(); i++) 
			{
				if(result.charAt(i)>minInt.charAt(i)) 
				{ 
					isOutOfBound=true;
					break;
				}
				else if(result.charAt(i)<minInt.charAt(i))
				{
					break;
				}
				//other case is values equals each other
			}
		}	
		
		System.out.println("reversed:"+result);
		
		return (isOutOfBound) ? 0 : Integer.parseInt(result); 
		
	}
	
    public int countDigits(int num) 
    {
        int count = 0;
                       
        for(char ch:(num+"").toCharArray()) 
        {
        	int div = ch-'0';
        	if(num%div==0) count++;
        }
        
        return count;
    }
	
    public static int digitFrequencyScore(int n) 
    {
        int []freq = new int[10];  /*[0-9] -> 10 tane*/
        int result = 0;
        
        String str = n + "";
        
        for(char ch:str.toCharArray())
        	freq[ch-'0']++;
        
        for(int i=0; i<10; i++) 
        {
        	result += freq[i]*i;
        }
        
		return result;            
    }
    
    public int mirrorDistance(int n) 
    {    	 
		/*
		  Tarih         : 03.11.2026
		  Durum         : 
		  Problem Adi   :
		  Problem Link  :
		  Algoritma     :
		  Diger         :
		*/
    	
    	int revInt = Integer.parseInt( new StringBuilder(n+"").reverse().toString() );
		
    	return Math.abs(revInt-n);
        
    }
       
    public int[] minOperations(String boxes) 
    {    
		/*
		  Tarih         : 03.11.2026
		  Durum         : 
		  Problem Adi   : 1769. Minimum Number of Operations to Move All Balls to Each Box
		  Problem Link  : https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/
		  Algoritma     :
		  Diger         :
		*/
					
    	
    	//bu yöntem brute force.
    	//prefix sum yöntemi ile yapýlabiliyor 
    	
    	int boxCount = boxes.length();
    	int []result = new int[boxCount]; 
    	
    	for(int i=0; i<boxCount; i++) 
    	{
    		result[i] = 0;
    		
    		for(int j=0; j<boxCount; j++) 
    		{
    			if(boxes.charAt(j)=='1')
    				result[i] += Math.abs(j-i);
    		} 
    	}
    	
		return result;        
    }
    
    public int minimumSum(int num) 
    {
    	/*
		  Tarih         : 04.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2160. Minimum Sum of Four Digit Number After Splitting Digits
		  Problem Link  : https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/ 
		  Algoritma     : 
		  Diger         : 
		*/
	    	
        int []digits = new int[4];

        for(int i=0; i<4; i++) 
        {   
            digits[i] = num%10;
            num/=10;
        }

        Arrays.sort(digits);
        
		return (digits[0]*10+ digits[2]) + (digits[1]*10+ digits[3]);       
    }
    
    public static int sumOfMultiples(int n) 
    {
        int sum = 0;
        
        for (int i = 1; i <= n; i++) 
        {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) 
            {
                sum += i;
                
                System.out.print(i + ",");
            }
        }
        return sum;
    }
    
    public static void testCases() 
    {
    	/*
    	 int [] arr = { 122, 101 } ;
		 int index =0; 
		 int result = digitFrequencyScore(arr[index]);
		 System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );     	 
    	 */
    	
    	/*
    	int [] arr = { 123, -123,  120, 1534236469, 1563847412, -2147483412, -2147483648, 2147483647, 1563847412, -1563847412} ;
		int index =5; 
		int result = reverse(arr[index]);
		System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );
    	*/
    	
    	/*
    	int [] arr = { 122, 101 } ;
		int index =0; 
		int result = digitFrequencyScore(arr[index]);
		System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );
		*/
    	
    	/*
    	int [][] arr = {{8,1,2,2,3}, {6,5,4,8}};
		int index =0; 
		int []result = smallerNumbersThanCurrent(arr[index]);
		System.out.println("Girdi :  " + Arrays.toString(arr[index]) + "\nCikti :  " + Arrays.toString(result) );
		*/
    	
    	//System.out.println(sumOfMultiples(15) );    	    	
    	
    	
    }
    
	public static void main(String[] args) 
	{								
							
		int [][] arr = { { 1,5,2,4,1,3,0} } ;
		//int index =0; 
		//int result = peakValleyDifference(arr[0]);
		//System.out.println("Girdi :   " + Arrays.toString(arr[0]) + "\nCikti :  " + result );
		
	}

}
