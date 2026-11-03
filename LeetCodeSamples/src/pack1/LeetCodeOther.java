package pack1;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

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
    
    public int[] recoverOrder(int[] order, int[] friends) 
    {
    	Set<Integer> set = new HashSet<Integer>();
    	
    	int[] result = new int[friends.length];
    	
    	for(int i=0; i<friends.length; i++) 
    	{
    		set.add(friends[i]);
    	}
    	
    	for(int i=0, j=0; i<order.length; i++) 
    	{
    		if(set.contains(order[i])) 
    			result[j++] = order[i];
    	}
    	
    	return result;
    }
    
    public int[] concatWithReverse(int[] nums) 
    {
    	int size = nums.length;
    	int  [] resultArr = new int[2*size];
    	
    	for(int i=0; i<size; i++) 
    	{
    		resultArr[i] = nums[i];
    		resultArr[size+i] = nums[size-1-i];
    	}
    	
    	return resultArr;
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
    
    public List<Integer> findMissingElements(int[] nums) 
    {
    	List<Integer> list = new ArrayList<Integer>();
    	
    	int size = nums.length;
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	
    	Set<Integer> set = new HashSet<Integer>();
    	
    	for(int i=0; i<size; i++) 
    	{
    		int num = nums[i];
    		if(num<min) min = num;
    		if(num>max) max = num;
    		
    		set.add(num);
    	}
        
    	for(int i = min+1; i<max; i++) 
    	{
    		if(!set.contains(i))  list.add(i);
    	}
    	
		return list;    	
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
    }
    
	public static void main(String[] args) 
	{
								
		
		int [] arr = { 122, 101 } ;
		int index =0; 
		int result = digitFrequencyScore(arr[index]);
		System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );

	}

}
