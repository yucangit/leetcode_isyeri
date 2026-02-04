package pack1;

import java.util.Arrays;
import java.util.*;

public class LeetCodePrefixSum {
		
	public static void printArrContent(int [] arr, boolean yatay)
	{
		int size = arr.length;
		for(int i=0; i<size;i++)		
		{
			System.out.print(arr[i]);
			
			if(!(i<size-1)) continue;   //son deðerden sonra , karakteri basýlmasýn.
			
			if( yatay == true) 
			{				
				System.out.print(", ");
			}
			else 
			{				
				System.out.println(", ");
			}					
		}				
	}
	
	
	public static int[] leftRightDifference(int[] nums) {
        int size = nums.length;
        int [] leftSum  = new int[size];
        int [] rightSum = new int[size];

        int runningSum1 = 0;     //new int[size];
        int runningSum2 = 0 ;    //new int[size];
        
        leftSum[0] = 0;
        rightSum[size-1] = 0;

        for(int i=1; i<size; i++)
        {
            runningSum1 += nums[i-1];
            leftSum[i] = runningSum1;

            runningSum2+=nums[size-(i-1)-1];
            rightSum[size-(i-1)-2] = runningSum2;
        }
        
        System.out.print("leftSumArr  : ");
        printArrContent(leftSum, true);
        
        System.out.println("");
        System.out.print("rightSumArr : ");
        printArrContent(rightSum, true);
        
        int []result = new int[size];
        
        for(int i=0; i<size; i++)
        {
        	result[i] = Math.abs(leftSum[i]-rightSum[i]);
        }
        return result;
        
    }
	
	public static int subarraySum(int[] nums) {
        
        int size = nums.length;
        int runningSum = 0;
        int [] dp = new int[size];

        int subArrSum = 0;

        for(int i=0; i<size; i++)
        {
            runningSum += nums[i];
            dp[i] = runningSum;

            int startIdx = Math.max(0, i-nums[i]);

            if(startIdx>0)
                subArrSum += runningSum-dp[startIdx-1];
            else 
                subArrSum += runningSum;
        }

        return subArrSum;        
    }
	
    public static int pivotIndex(int[] nums) {
        int size = nums.length;                
        int sum=0, runningSum = 0;
        int pivotIdx=-1;        

        for(int i=0; i<size; i++)
            sum += nums[i];

        for(int i=0; i<size; i++)
        {   
            if(runningSum == sum-nums[i]-runningSum) 
            {
                pivotIdx = i;
                break;
            }
            runningSum += nums[i];                        
        }

        return pivotIdx;
    }
    
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        return null;
    }
    
    public static boolean isCovered(int[][] ranges, int left, int right) {
        boolean result=false;

        for(int val=left; val<=right; val++)
        {
            result=false;

            for(int[] range : ranges)
            {
                if( val>=range[0] && val <=range[1] ){ result = true; break;}
            }
            if(result==false) break;
        }

        return result;
    }
    
    public static void test() 
    {
    	/*    	
    	//nums = [[3,6],[1,5],[4,7]]
		List<Integer> range1 = new LinkedList<Integer>();
		List<Integer> range2 = new LinkedList<Integer>();
		List<Integer> range3 = new LinkedList<Integer>();
		range1.add(3);  range1.add(6);
		range2.add(1);  range2.add(5);
		range3.add(4);  range3.add(7);
		
		List<List<Integer>> nums = new LinkedList<List<Integer>>(); 
		
		nums.add(range1);
		nums.add(range2);
		nums.add(range3);						
		
		//int [][]nums = {{3,6}, {1,5},{4,7}};
		System.out.println(numberOfPoints(nums));		
		
		Set<Integer> s  = new HashSet<Integer>();
		s.add(null);
        */
    	
    	
    	/*
		//int [][]ranges = {{1,2},{3,4},{5,6}};
		//int left = 2, right = 5;
		
		//int [][]ranges = {{1,10},{10,20}};
		//int left = 21, right = 21;
		
		
		//int [][]ranges = {{37,49},{5,17},{8,32}};
		//int left = 29, right = 49;
		
		int [][]ranges = {{1,50}};
		int left = 1, right = 50;
						
		boolean result = isCovered(ranges, left, right);
		System.out.println(result);
		*/
    	
    	/*
		//int [] arr = {-1,-1,-1,-1,0,1};    //1
		//int [] arr = {5};    //
		//int [] arr = {3,2};    //-1
		//int [] arr = {-1,-1,0,1,1,0};    //5
		int [] arr = {-1,-1,0,0,-1,-1};    //5
		
		
		int result = pivotIndex(arr);
		System.out.println(result);
		*/
    	
    	/*
		int [] arr = {10,4,8,3};		
		
		int[] result = leftRightDifference(arr);
		//System.out.println("1");
		
		System.out.print("\nSum diffs   : ");
		printArrContent(result, true);
	    */
		
		/*
		int [] arr = {3,1,5};
		int result = subarraySum(arr);
		System.out.println(result);
		*/
    	
    }
         
    public static int numberOfPoints(List<List<Integer>> nums) 
    {
    	int []arr = new int[102];

        for(List<Integer> range: nums)
        {
            arr[range.get(0)]++;
            arr[range.get(1)+1]--;            
        }

        int sum = 0;
        int onLine=0;
        for(int i=1; i<102;i++)
        {
            sum = sum + arr[i];
            if(sum>0)  onLine++;
        }

        return onLine;
    }

  
    public static int minimumSumSubarray(List<Integer> nums, int l, int r) {
    	
    	/*
    	//nums = [3,-2,1,4] , l=2, r=3
    	//nums = [-2,2,-3,1], l=2, r=3
    	//nums = [1,2,3,4] ,  l=2, r=4
		
    	//nums=[4,-10],   l=1, r=1 için hatalý çalýþýyor(düzeltildi).
        //nums=[-23,3],   l=2, r=2 için hatalý çalýþýyor(düzeltildi).		
		//nums=[5,8,-6],  l=1, r=3
		
		List<Integer> nums = new LinkedList<Integer>();
		int l=0, r=0;
		
		//nums.add(4);  nums.add(-10);   l=1; r=1;
		//nums.add(-23);  nums.add(3);   l=2; r=2;		
		nums.add(5);  nums.add(8);  nums.add(-6);   l=1;  r=3;
		
		
		int result = minimumSumSubarray(nums,l,r);
		
		System.out.println(result);
		*/
    	
    	//nums = [3,-2,1,4] , l=2, r=3
    	//nums = [-2,2,-3,1], l=2, r=3
    	//nums = [1,2,3,4] ,  l=2, r=4
    	
    	//nums=[4,-10],       l=1, r=1 için hatalý çalýþýyor.
        //nums=[-23,3],       l=2, r=2 için hatalý çalýþýyor.
    	//nums=[5,8,-6],  l=1, r=3
    	
        int minSum=Integer.MAX_VALUE;
        int nums_size=nums.size();
        
        for(int i=0; i<nums_size;i++)   //start index
        {
            int sum = 0;
            int j=0;
            for(; j<l && i+j<nums_size; j++)
            {
                sum+=nums.get(i+j);
            }
            
            if(j<l) break;
            
            if(sum<minSum && sum>0)     
            	minSum=sum;
            if( l==r && sum<minSum && sum>0)     //l==r durumu için
                minSum=sum;

            for(int k=0; l+k<r && i+l+k<nums_size; k++)
            {                
                sum+=nums.get(i+l+k);
                if(sum<minSum && sum>0) 
                    minSum=sum;
            }
        }

        int result =-1;

        if(minSum>0 && minSum!=Integer.MAX_VALUE) result = minSum;
        
        return result;

    }
    
	public static void main(String[] args) {								
		
		
		
		
		

	}

}
