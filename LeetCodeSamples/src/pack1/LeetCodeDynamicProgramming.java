package pack1;

import java.util.*;

public class LeetCodeDynamicProgramming {

	public static List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new LinkedList<List<Integer>>();        
        List<Integer> preRow = new LinkedList<Integer>();

        preRow.add(1);
        result.add(preRow);
        
        if(numRows==1) return result;
        
        preRow = new LinkedList<Integer>();
        preRow.add(1);
        preRow.add(1);
        result.add(preRow);
        
        List<Integer> currRow = new LinkedList<Integer>();
        
        for(int i=2; i<numRows; i++)
        {
        	currRow = new LinkedList<Integer>();
        	currRow.add(1);
        	for(int j=0; j<preRow.size()-1; j++) 
        	{
        		int sum = preRow.get(j)+preRow.get(j+1);
        		currRow.add(sum);
        	}
        	currRow.add(1);
        	result.add(currRow);
        	preRow = currRow;
        
        }

        return result;
    }

	public List<Integer> getRow(int rowIndex) 
	{
		//Pascal's Triangle II
		//https://leetcode.com/problems/pascals-triangle-ii/description/?envType=problem-list-v2&envId=dynamic-programming
        
        List<Integer> result = new LinkedList<>();

        result.add(1);        
        if(rowIndex==0) return result;

        result.add(1);
        if(rowIndex==1) return result;                

        int [] prevRow = new int[2];
        prevRow[0]=1;
        prevRow[1]=1;

        int [] currRow = new int[3];

        for(int i=2; i<=rowIndex; i++)
        {
            currRow = new int[i+1];
            currRow[0] = 1; 
            currRow[i] = 1;

            for(int j=1; j<i; j++)
                currRow[j] = prevRow[j-1]+prevRow[j];
            
            prevRow = currRow;
        }

        result.clear();
        for(int i=0; i<=rowIndex; i++)
            result.add(currRow[i]);

        return result;
    }

	public static int climbStairs(int n)          // n-> cikilacak basamak numarasý 
	{
		/*
		  Tarih         : 13.10.2026
		  Durum         : 
		  Problem Adi   : 70. Climbing Stairs
		  Problem Link  : https://leetcode.com/problems/climbing-stairs/description/?envType=study-plan-v2&envId=dynamic-programming
		  Algoritma     :
		  Diger         : You are climbing a staircase. It takes n steps to reach the top.
						  Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
		*/
		
		int [] dp = new int [100];
		
		dp[0] = 0;       //0.basamak için adim atmaya gerek yok.   //tekrar düþünülecek
		dp[1] = 1;       //1.basamaga 1 adim ile doðrudan çýkýlýr
		dp[2] = 2;       //2.basamaga 1+1 adim ya da doðrudan 2 adim ile cikilir
		
		for(int i=3; i<=n; i++) 
		{
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		return dp[n];	
	}
	
	public static int minCost(int n) 
	{
		/*
		  Tarih         : 13.10.2026
		  Durum         : 
		  Problem Adi   : 3857. Minimum Cost to Split into Ones
		  Problem Link  : https://leetcode.com/problems/minimum-cost-to-split-into-ones/?envType=problem-list-v2&envId=dynamic-programming
		  Algoritma     :
		  Diger         : 1 <= n <= 500
		*/
		
		int []dp = new int[501];
		
		dp[0]=0;
		dp[1]=0;
		dp[2]=1;
		
		int temp = 0;
				
		for(int i=3; i<=n; i++) 
		{
			dp[i] = (i-1)*1 + dp[i-1];
		}
		
		return dp[n];						
	}
	
	public static void testCases() 
	{
		int sonucInt=0;
		
		//-------------------------------------------------------------------------------
		sonucInt = minCost(500);
		System.out.println(sonucInt);
		
		//-------------------------------------------------------------------------------
		sonucInt = climbStairs(4);
		System.out.println(sonucInt);
		
		//-------------------------------------------------------------------------------
		generate(5);
		
		List<Integer> result = new LinkedList<>();
		result.add(1);
		result.clear();
	}
	
	public static void main(String[] args) 
	{
			
		int sonucInt = minCost(500);
		System.out.println(sonucInt);

	}

}
