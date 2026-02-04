package pack1;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class LeetCodeArrays {
	
	public static void printArrayContent(int[] arr, boolean yatay)
	{
		
		int size = arr.length;
		
		for(int i=0; i<size; i++) 
		{
			int val =arr[i];
			if( yatay == true) 
			{
				System.out.print(val);
				if( i!=size-1 ) System.out.print(", ");
			}
			else 
			{
				System.out.print(val);
				if( i!=size-1 ) System.out.print(", ");
			}						
		}				
	}

	public static int[] orArray(int[] nums) {
		int [] result = new int [nums.length-1];
		
		for(int i=0; i<nums.length-1; i++ )
			result[i] = nums[i] | nums[i+1];
		
		return result;
    }
	
	public static int finalValueAfterOperations(String[] operations) {
		int result = 0;

        for(int i=0; i<operations.length; i++ )
        {
            if(operations[i].charAt(1) == '-')  result--;
            else if (operations[i].charAt(1)=='+')  result++;

	    }
	    return result;
	}
	
	public static int[] getSneakyNumbers(int[] nums) {
        HashMap<Integer,Integer> m = new HashMap<Integer, Integer>();
        int j=0;
        int [] result = new int[2];
        
        for(int i=0; i<nums.length && j<2; i++)
        {
        	
        	int val = nums[i];
        	
            if(m.containsKey(val)) {
            	m.replace(val, m.get(val)+1);
            	result[j] = val;
            	j++;
            }
            else m.put(val, 1);
        	
        	//System.out.println("asdfsd");
        }
                
        
        return result;
    }
		
	public static List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i=0; i<words.length; i++)
            if(words[i].indexOf(x)!=-1) result.add(i);
        
        return result;
    }
		
	public static int countConsistentStrings(String allowed, String[] words) {
		
		int result =0;
		
        for(String s : words)
        {
            char [] arr = s.toCharArray();
            result++;
            
            for(char ch : arr) {
                if(allowed.indexOf(ch+"")==-1) { 
                	result--; 
                	break;
                }
            }
        }
        return result;
    }
	
	public static int[][] largestLocal(int[][] grid) {
		
		int n = grid.length;
        int [][] result = new int[n-2][n-2];
        
        for(int i=0; i<n; i++) 
        {
        	for(int j=0; j<n; j++)
        	{
        		int max = 0;
        		
        		for(int a=0; a<3; a++) 
        		{
        			for(int b=0; b<3; b++) 
        			{
        				if(grid[a+i][b+j]>max) max = grid[a+i][b+j]; 
        			}
        		}
        		result[i][j] = max;
        			
        	}
        }
        
        return result;
        
    }
	
    public static int[] twoSum(int[] nums, int target) {

        int sum=0;
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();   //val, index
        int []result = new int[2];
        
        for(int i=0; i<nums.length; i++) {
            sum+=nums[i];                   
            m.put(nums[i],i);
        }

        for(int i=0; i<nums.length; i++) 
        {
        	int diff = target-nums[i] ;
        	if( m.containsKey(diff) &&  m.get(diff)!=i) { 
        		int secondIdx = m.get(diff);
        		result[0] = i; result[1]=secondIdx;        		        		  
        		break;
        	}
        }
        
        return result;
    }
	    
    public static int prefixCount(String[] words, String pref) {
    	int result=0;
    	
    	for(int i=0; i<words.length; i++) 
    	{
    		if ( words[i].matches("^" + pref+".*")) result++;
    	}
    	
        return result;
    }
	
    public static int sumFourDivisors(int[] nums) 
    {
        //yapýlma tarihi: 05.01.2026
        int size = nums.length;
        int sum2 = 0;
        
        for(int i=0; i<size; i++){
            int num = nums[i];
            //int sq = (int) Math.ceil(Math.sqrt(num));
            int sq = (int) Math.sqrt(num);
            
            int cnt = 0;
            int sum1 = 0;
            
            for(int j=1; j<=sq; j++)
            {
            	if(cnt>4) break;
            	
                if(num%j==0) {
                	if(j==num/j) {
                		cnt+=1;
                    	sum1 += j;
                	}
                    else 
                    {
                    	cnt+=2;
                    	sum1 += j + num/j;
                    }
                }
            }
            
            if(cnt==4)
                sum2+=sum1;            
        }

        return sum2;
        
    }
    
    public static void printArrayContent() 
    {
    	
    }
    
    public static int[] plusOne(int[] digits) 
    {
    	  int size = digits.length;
          int carry = 0;

          List<Integer> list = new ArrayList<Integer>();
          int val=1;
          
          for(int i = size-1;i>=0; i--)
          {
              int sum = (digits[i] + val + carry)%10; 
              carry = (digits[i] + val + carry)/10; 
              
              list.add(0,sum);

              val = 0;
          }
          
          if(carry>0) list.add(0, carry);
          
          int [] newArr = new int[list.size()];
          for(int i=0;i<list.size(); i++)
        	  	newArr[i] = list.get(i); 
          
          return newArr;        		  
        
    }
    
    public static void testCases() 
	{
    	
				
		
		//int a=1;
		//System.out.println(Integer.toBinaryString(a)+"\nfirst index :" + Integer.toBinaryString(a).indexOf("1") + "\nlast index : " + Integer.toBinaryString(a).lastIndexOf("1"));
		/*
		int [][] nums = {{21, 4, 7},{21,21}};
		System.out.println( sumFourDivisors(nums[1]) );
		*/
		
		/*
		//String [] words = new String[]{"pay","attention","practice","attend"};
		//String pref = "at";
		
		
		String [] words = new String[]{"leetcode","win","loops","success"};
		String pref = "code";
		
		System.out.println(prefixCount(words,pref));		
		*/
		
		
		/*twoSum function
		int []nums = new int[] {2,7,11,15};            int target = 9;
		//int []nums = new int [] {3,2,4};               int target = 6;
		//int []nums = new int [] {3,3};                  int target = 6;
		
		
		int[] result = twoSum(nums,target);
		System.out.println(result[0] + "," + result[1] );
		*/
		
		/*int [] arr = {1,3,7,15};
		int[] result = orArray(arr);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i] + ",");
		*/
		
		
		/*
		//String [] operations = new String [] {"--X","X++","X++"};
		//String [] operations = new String [] {"++X","++X","X++"};
		
		String [] operations = new String [] {"X++","++X","--X","X--"};
		
		int result = finalValueAfterOperations(operations);
		System.out.println(result);
		*/
		
		/*
		//int [] nums = {0,1,1,0};
		//int [] nums = {0,3,2,1,3,2};
		int [] nums = {7,1,5,4,3,4,6,0,9,5,8,2};
		
		int[] result = getSneakyNumbers(nums);
		
		for(int i=0;i<2;i++)
			System.out.println(result[i]);
		*/
		
		
		/*
		//int result = countConsistentStrings("ab", new String []{"ad","bd","aaab","baa","badab"});
		//int result = countConsistentStrings("abc", new String []{"a","b","c","ab","ac","bc","abc"});
		int result = countConsistentStrings("cad", new String []{"cc","acd","b","ba","bac","bad","ac","d"});
		System.out.println(result);
		*/
	}
	
	
    
	public static void main(String[] args) {
		
		int [][] nums = {{1,2,3},{4,3,2,1},{9},{1,9,2},{9,9,9},{9,9,1,9}};
		
		//System.out.println( plusOne(nums[0]) )
		printArrayContent(plusOne(nums[5]),true);
		
	}

}
