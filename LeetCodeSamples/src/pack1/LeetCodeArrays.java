package pack1;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


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
    
    public static int evenNumberBitwiseORs(int[] nums) 
    {
    	//Tarih : 11.03.2026
    	
    	int result = 0;
    	for(int num:nums) 
    	{
    		//if(num%2==0)
    		if( (num & 1 )== 0)	
    			result = result | num;
    	}
    	
    	return result;
    }
    
    public static boolean divideArray(int[] nums) 
    {
    	//Tarih : 11.03.2026
    	HashMap<Integer, Integer> map = new HashMap<>();
    	boolean ans = true;
    	
    	int size = nums.length;
    	
    	for(int num:nums) 
    	{
    		int count = map.getOrDefault(num, 0);
    		map.put(num, count+1);
    	}
    	
    	for(Map.Entry<Integer, Integer> ent: map.entrySet()) 
    	{
    		if(ent.getValue()%2!=0) 
    		{ 
    			ans = false; 
    			break; 
    		}
    	}
    	
    	ans = map.size()==size/2  && ans;   
    	
    	return ans;
    }
    
    public static int[] sortByBits(int[] arr) 
    {
    	//Tarih : 11.03.2026
    	//Durum : Yapildi
    	//Aciklama: 
    	
    	Map<Integer, List<Integer>> map = new HashMap<>();   //freq of 1 bits, num list
    	
    	for(int num : arr) 
    	{
    		//1 bitlerinin sayýsýný bulmak için doðrudan Integer.bitCount(4) fonksiyonu kullanýlabilir.
    		String binStr = Integer.toBinaryString(num);
    		int count = 0;
    		
    		for( char ch : binStr.toCharArray() ) 
    		{
    			count += ch-'0';
    		}
    		
    		List<Integer> list = map.get(count);
    		if(list==null) {
    			list = new ArrayList<>();    			    		
    		}
    		list.add(num);    		
    		map.put(count, list);
    	}
    	
    	//sort process1
    	
    	List<Entry<Integer, List<Integer>>> list2 = new ArrayList<>(map.entrySet());
    	list2.sort(Entry.comparingByKey());
    	
    	//sort process2 - sort numbers with same frequency 
    	List<Integer> list3 = new ArrayList<>();
    	for(int i=0; i<list2.size(); i++) 
    	{    		
    		List<Integer> list4 = list2.get(i).getValue();
    		Collections.sort(list4);
    		list3.addAll(list4);
    	}
    	
    	int [] ans = new int[list3.size()];
    	ans = list3.stream().mapToInt(Integer::intValue).toArray();
    	
    	return ans;
    }
    
    public static int removeDuplicates(int[] nums) {
    	/*
		  Tarih         : 16.03.2026
		  Durum         : Yapildi.
		  Problem Adi   : Remove Duplicates from Sorted Array
		  Problem Link  : https://leetcode.com/problems/remove-duplicates-from-sorted-array/
		  Algoritma     :
		  Diger         :
		*/
    	
    	int idx1=1, idx2=0;
    	
    	int size = nums.length;
    	
    	for(int i=1; i<size; i++) 
    	{
    		if(nums[i]==nums[i-1]) 
    		{
    			continue;
    		}
    		else 
    		{ 
    			idx2++; 
    			nums[idx2] = nums[i];    			
    		}
    	}    
    	
    	return idx2+1;
    	
    }
    
    public static void FancySequence() {
		/*
		  Tarih         : 16.03.2026
		  Durum         : Yapilmadi. 
		  Problem Adi   : Fancy Sequence
		  Problem Link  : https://leetcode.com/problems/fancy-sequence/?envType=daily-question&envId=2026-03-15
		  Algoritma     :
		  Diger         : Zaman-Ýþlem karmaþýklýðý O(n) olunca TimeLimitException oluþuyor. 
		                  Daha verimli bir yöntem denenebilir mi?
		                  Yorumlarda yaklaþým olarak "Lazy evalutaion" ve Fermat's Little Theorem" belirtilmiþ.
		*/
    	
    	class Fancy {
    		int [][] nums;     //number,     time
    		int [][] opt;      //operation,  time
    		int [][] incArr;   //increment,  time
    		int [][] mulArr;   //multiplier, time
    		int index;
    		int time;

    	    public Fancy() 
    	    {
    	        nums   = new int[100000][2];
    	        opt    = new int[100000][2];
    	        incArr = new int[100000][2];
    	        mulArr = new int[100000][2];
    	        index  = -1;
    	        time   = -1;
    	    }
    	    
    	    public void append(int val) 
    	    {
    	    	index++;
    	    	time++;
    	        nums[index][0] = val;
    	        nums[index][1] = time;
    	    }
    	    
    	    public void addAll(int inc) 
    	    {
    	    	index++;
    	    	time++;
    	        incArr[index][0] = inc;
    	        incArr[index][1] = time;    	    	
    	    }
    	    
    	    public void multAll(int m) 
    	    {
    	    	index++;
    	    	time++;
    	        mulArr[index][0] = m;
    	        mulArr[index][1] = time;
    	    }
    	    
    	    public int getIndex(int idx) 
    	    {
    	    	time++;
    	    	
    	    	for(int i=nums[idx][1]; i<time; i++) 
    	    	{
    	    		
    	    	}
    	    	
    	        return nums[idx][0];
    	    }
    	}
    	
		
	}

    public static String longestCommonPrefix(String[] strs) 
    {
    	//Tarih : 18.03.2026
    	//Durum : Yapildi.
    	
    	StringBuilder sb = new StringBuilder();    	
    	int minLength = 200;
    	
    	//find min size of strings
    	for(int i=0; i<strs.length; i++)   
    		minLength = Math.min(minLength, strs[i].length());
    	
    	for(int i=0; i<minLength; i++)   //check ith character 
    	{
    		boolean isAllSame = true;
    		char ch = strs[0].charAt(i);
    		
    		for(String str : strs) 
    		{    
    			if(str.charAt(i)!=ch) 
    			{ 
    				isAllSame=false; 
    				break; 
    			}
    		}
    		
    		if(isAllSame) 
    			sb.append(ch);
    		else 
    			break;
    	}
    	
    	return sb.toString();
    }
    
    public static void testCases() 
	{
    	/*
		String [][] strs = {{"flower","flow","flight", ""},{"dog","racecar","car"}, {"cir","car"}};
		int index = 2;
		String result =  longestCommonPrefix(strs[index]);
		System.out.println("sonuc : " + result);
		*/
    	
    	/*
		int [][] nums = {{0,2,1,3,4,6,5,7,8},{1024,512,256,128,64,32,16,8,4,2,1},{12,0,0,11,1}};
		int index = 2;
		int[] result = sortByBits(nums[index]);
		printArrayContent(result, true);
		*/
    	
    	/*
		int [][] nums = {{3,2,3,2,2,2},{7,9,11},{1,8,16}};		
		System.out.println(divideArray(nums[0]));
		*/
    	
    	/*
    	int [][] nums = {{1,2,3,4,5,6},{7,9,11},{1,8,16}};		
		System.out.println(evenNumberBitwiseORs(nums[2]));
    	*/
    	
    	/*
    	int [][] nums = {{1,2,3},{4,3,2,1},{9},{1,9,2},{9,9,9},{9,9,1,9}};		
		//System.out.println( plusOne(nums[0]) )
		printArrayContent(plusOne(nums[5]),true);
		*/
		
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
		
		/*
		int [][] nums = {{0,2,1,3,4,6,5,7,8},{1024,512,256,128,64,32,16,8,4,2,1},{12,0,0,11,1}};
		int index = 2;
		int[] result = sortByBits(nums[index]);
		printArrayContent(result, true);
		*/
		
		//System.out.println( Integer.bitCount(4) );
		
		String [][] strs = {{"flower","flow","flight", ""},{"dog","racecar","car"}, {"cir","car"}};
		int index = 2;
		String result =  longestCommonPrefix(strs[index]);
		System.out.println("sonuc : " + result);
		
		
		
		
	}

}
