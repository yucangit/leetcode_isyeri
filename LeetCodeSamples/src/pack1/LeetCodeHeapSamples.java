package pack1;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Map;
import java.util.HashMap;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashSet;

import java.util.PriorityQueue;

public class LeetCodeHeapSamples 
{
	
	public int[] numberGame(int[] nums) 
    {
		/*
		  Tarih         : 10.11.2026
		  Durum         : Çözüldü 
		  Problem Adi   : 2974. Minimum Number Game
		  Problem Link  : https://leetcode.com/problems/minimum-number-game/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : 
		*/
		
		int size = nums.length;
		int []arr = new int[size];    //result
		
		PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> a-b);
		
		for(int i=0; i<size; i++) 
		{
			heap.add(nums[i]);
		}
		
		for(int i=0; i<size/2; i++) 
		{
			int numAlice = heap.poll();
			int numBob = heap.poll();			
			
			arr[2*i] = numBob;
			arr[2*i+1] = numAlice;
		}
		
		return arr;
        
    }
	
	
	public int[] getFinalState(int[] nums, int k, int multiplier) 
    {	
		
		/*
		  Tarih         : 10.11.2026
		  Durum         : Çözüldü.(Editörün çözümüne bakýldý.)
		  Problem Adi   : 
		  Problem Link  : https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/description/ 
		  Algoritma     : 
		  Diger         : 
		*/
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(  ( a , b ) ->               //deðer, index 
			{ 
			    int valueComparison = Integer.compare(a[0], b[0]);             //deðerler karþýlaþtýrýlýr.
			    
	            if (valueComparison == 0) 
	            {
	                return Integer.compare(a[1], b[1]);        //deðerler aynýysa indexlere göre sýralama yapýlýr 
	            }
	            return valueComparison;
	        }
		);
		
		int size = nums.length;
		
		//Priority Queue doldurulur.
		for(int i=0; i<size; i++) 
		{
			pq.add(new int[] {nums[i],i});
		}
		
		//k defa en küçük elaman için 2 ile çarpma yapýlýr. 
		for(int i=0; i<k; i++) 
		{
			int []smallestItem = pq.poll();
			int index = smallestItem[1];
			nums[index] *= multiplier;
			
			pq.offer(smallestItem);
		}
		
		
		return nums;        
    }
	
	public static long pickGifts(int[] gifts, int k) 
	{
		/*
		  Tarih         : 12.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2558. Take Gifts From the Richest Pile
		  Problem Link  : https://leetcode.com/problems/take-gifts-from-the-richest-pile/?envType=problem-list-v2&envId=heap-priority-queue 
		  Algoritma     : 
		  Diger         : 
		*/
		
		int size = gifts.length;
		PriorityQueue<Integer> heap = new PriorityQueue<>( (a,b) -> b-a);   //varsayýlan olarak minHeap(baþtaki hep minimum deðer)
		
		for(int i=0; i<size; i++) 
		{
			heap.add(gifts[i]);
		}
		
		long sum = 0;
		
		for(int i=0; i<k; i++) 
		{
			int num = heap.poll();
			heap.add((int)Math.sqrt(num) );			
		}
		
		for(int i=0; i<size; i++) 
		{
			sum += heap.poll();
		}
		
		return sum;						
    }
	
	
	public static int[] kWeakestRows(int[][] mat, int k) 
	{
		/*
		  Tarih         : 12.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 1337. The K Weakest Rows in a Matrix
		  Problem Link  : https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : heap'in compare kýsmý daha farklý bir þekilde de yapilabilir.
		*/
		
		PriorityQueue<int[]> heap = new PriorityQueue<> (  ( a, b) ->                           //value, index
			{
				int valComparison = Integer.compare(a[0],b[0]);
				
				if(valComparison==0)                                 //deðerler ayný ise indexler karþýlaþtýrýlýr.
					return Integer.compare(a[1], b[1]); 
				
				return valComparison;
			}
		);
		
		int rows = mat.length;
		int cols = mat[0].length;
		
		for(int i=0; i<rows; i++)    //rows 
		{
			int numOfSoldiers = 0;
			
			for(int j=0; j<cols; j++)    //rows
				numOfSoldiers += mat[i][j];
			
			heap.offer(new int[] {numOfSoldiers, i} );
		}
		
		int [] result = new int[k];
		
		for(int i=0; i<k; i++)
			result[k] = heap.poll()[0];
		
		return result;

	}
	
    public static int lastStoneWeight(int[] stones) 
    {
    	
    	/*
		  Tarih         : 13.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 1046. Last Stone Weight 
		  Problem Link  : https://leetcode.com/problems/last-stone-weight/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : 
		*/
		    	
    	int size = stones.length;    	
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> b-a );     //default min is head    	    
    	
    	for(int i=size-1; i>=0; i--)
    		maxHeap.offer(stones[i]);
    	
    	while( maxHeap.size()>1 ) 
    	{
    		int val1 = maxHeap.poll();
    		int val2 = maxHeap.poll();
    		
    		if(val2<val1)  
    			maxHeap.add(val1-val2);
    	}
    	
    	return maxHeap.size()>0 ? maxHeap.peek() : 0;    	    	
    }
	
    public static int minimumOperations(int[] nums) 
    {
    	/*
		  Tarih         : 13.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2357. Make Array Zero by Subtracting Equal Amounts
		  Problem Link  : https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : 
		*/		
    	
    	PriorityQueue<Integer> minHeap = new PriorityQueue<>( );        //default minHeap
    	PriorityQueue<Integer> minHeapTemp = new PriorityQueue<>( );    //default minHeap
    	
    	int size = nums.length;
    	
    	for(int i=0; i<size; i++) 
    	{
    		if(nums[i]>0) 
    			minHeap.add(nums[i]);
    	}
    	
    	int count = 0;    	    	
    	
    	while(minHeap.size()>0) 
    	{
    		int val1 = minHeap.poll();
    		    		
    		while(minHeap.size()>0) 
    		{
	    		int val2 = minHeap.poll();
	    		int diff = val2-val1;
	    		
	    		if( diff>0 ) 
	    			minHeapTemp.add(diff);
    		}
    		
    		minHeap.addAll(minHeapTemp);
    		minHeapTemp.clear();
    		
    		count++;
    	}    	    	
    	
    	return count;    	
    }
    
    
    public int largestInteger(int num) 
    {
    	/*
		  Tarih         : 13.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2231. Largest Number After Digit Swaps by Parity
		  Problem Link  : https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : 
		*/	
    	
    	PriorityQueue<Integer> oddNumHeap = new PriorityQueue<>( (a,b) -> b-a );        //default minHeap
    	PriorityQueue<Integer> evenNumHeap = new PriorityQueue<>( (a,b) -> b-a );       //default minHeap
    	    	
    	char []arr = (num+"").toCharArray();
    	
    	for(int i=0; i<arr.length; i++)
    	{    		
    		int digit = arr[i]-'0';
    		
    		if(digit%2==0)  evenNumHeap.add(digit);
    		else            oddNumHeap.add(digit);    		
    	} 
    	
    	String maxNumStr = "";
    	
    	for(int i=0; i<arr.length; i++) 
    	{    		
    		int digit = arr[i]-'0';    		
    		
    		if(digit%2==0)  maxNumStr += evenNumHeap.poll();
    		else            maxNumStr += oddNumHeap.poll();    		    		
    	}
    	
    	return Integer.parseInt(maxNumStr);
    }
    
    class KthLargest 
    {
    	/*
		  Tarih         : 16.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 703. Kth Largest Element in a Stream
		  Problem Link  : https://leetcode.com/problems/kth-largest-element-in-a-stream/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : MinHeap
		  Diger         : 
		*/
		

    	
    	PriorityQueue<Integer> minHeap;
    	int k;    	
    	
        public KthLargest(int k, int[] nums) 
        {
        	this.k = k;        	
        	minHeap = new PriorityQueue<>();    //default minHeap oluþturulur.
        	
        	for(int num:nums)
        		minHeap.add(num);        	        	
        	
        	while(minHeap.size()>k) 
        	{
        		minHeap.poll();
        	}
        	
        }
        
        public int add(int val) 
        {        	        	
        	int heapSize = minHeap.size();
        	
        	if(heapSize<k)  minHeap.add(val);
        	else if(heapSize>=k) 
        	{
        		int minVal = minHeap.peek();
        		
        		if( minVal<val ) 
        		{
        			minHeap.poll();
        			minHeap.add(val);
        		}
        	}
        		
        	return minHeap.peek();
        }
    }
    
    public static int[] findXSum(int[] nums, int k, int x) 
    {
    	/*
		  Tarih         : 16.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 3318. Find X-Sum of All K-Long Subarrays I
		  Problem Link  : https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/?envType=problem-list-v2&envId=heap-priority-queue 
		  Algoritma     : 
		  Diger         : Heap oluþturma kýsmýnda açýklama durumuna getirilen yöntemin neden çalýþmadýðý belirlenemedi.
		*/		
    	
        int n = nums.length;
        int [] result = new int[n-k+1];

        for(int i=0; i<n-k+1; i++)
        {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) ->   //freq, deger
                {
                    //if(b[0]-a[0]>0) return b[0]-a[0];  
                    //else            return b[1]-a[1];
                	
                	int valComparison = Integer.compare(b[0],a[0]);          //frekanslar karsilatirilirç
    				
    				if(valComparison==0)                                 
    					return Integer.compare(b[1], a[1]);                 //frekanslar ayný ise deðerler karþýlaþtýrýlýr.
    				return valComparison;
                }
            );    

            int []freq = new int[51];
            
            for(int j=i; j<i+k; j++)
                freq[nums[j]]++;
            
            for(int num=0; num<51; num++)         //heap doldurulur
                if(freq[num]>0)
                    maxHeap.add(new int[]{freq[num],num});

            for(int j=0; j<x && maxHeap.size()>0; j++)
            {
                int []arr = maxHeap.poll();
                result[i] += arr[0]*arr[1];
            }
        }

        return result;
    }

    public String[] findRelativeRanks(int[] score) 
    {
    	/*
		  Tarih         : 16.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 506. Relative Ranks
		  Problem Link  : https://leetcode.com/problems/relative-ranks/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : 
		  Diger         : 
		*/	
    	
    	int size = score.length;
    	
    	String []result = new String[size];
    	
    	Map<Integer, Integer> mapValIndex = new HashMap<>();
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> b-a);    //default is minHeap, convert it to maxHeap
    	
    	for(int i=0; i<size; i++) 
    	{
    		int val = score[i];
    		maxHeap.add(val);
    		mapValIndex.put(val,  i);
    	}
    	
    	for(int i=0; i<size; i++) 
    	{
    		int val = maxHeap.poll();
    		int orgIndex = mapValIndex.get(val);
    		
    		if(i==0)       result[orgIndex] ="Gold Medal";
    		else if(i==1)  result[orgIndex] ="Silver Medal";
    		else if(i==2)  result[orgIndex] ="Bronze Medal";
    		else 
    		{
    			result[orgIndex] = (i + 1) + ""; 
    		}
    	}
    	
		return result;
    	    	
    }

    public int[] rearrangeArray(int[] nums) 
    {
    	/*
		  Tarih         : 16.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 4065. Rearrange Array by Removing Distinct Values
		  Problem Link  : https://leetcode.com/problems/rearrange-array-by-removing-distinct-values/description/?envType=problem-list-v2&envId=heap-priority-queue
		  Algoritma     : Min heap
		  Diger         : 
		*/
		

    	
        int size = nums.length;
        int []result = new int[size];
        int counter=0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();   //deafult is minHeap
        //TreeSet<Integer> set;
        
        LinkedList<Integer> list = new LinkedList<>();
        
        for(int i=0; i<size; i++)
            list.add(nums[i]);

        while( list.size()>0 ) 
        {
        	//set = new TreeSet<>();
        	minHeap = new PriorityQueue<>();
        	
        	for(int i=0; i<list.size();i++) 
        	{
        		int num = list.get(i);
        		if(!minHeap.contains(num)) 
        		{
        			//set.add(num);
        			minHeap.add(num);
        		}        		
        	}
        	
        	while(minHeap.size()>0) 
        	{
        		int num = minHeap.poll();
        		list.removeFirstOccurrence(num);
        		result[counter++] = num;
        	}
        }
        
        return result;
        
    }
    
    public int[] maxSubsequence(int[] nums, int k) 
    {
    	/*
		  Tarih         : 16.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2099. Find Subsequence of Length K With the Largest Sum
		  Problem Link  : https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
		  Algoritma     : 
		  Diger         : 
		*/
		

    	
    	PriorityQueue<int[]> maxHeapVal = new PriorityQueue<>( (a,b) -> b[1]-a[1]);     //default min heap, We convert it to max heap.    int[2] : index, value   	    	    	
    	
    	for(int i=0; i<nums.length; i++) 
    	{
    		int []arr = new int[] {i, nums[i]};  
    		maxHeapVal.add(arr);
    	}    	    	
    	
    	PriorityQueue<int[]> minHeapIndex = new PriorityQueue<>( (a,b) -> a[0]-b[0]);     //default min heap, We convert it to max heap.    int[2] : index, value
    	for(int i=0; i<k; i++) 
    	{
    		int [] arr = maxHeapVal.poll();
    		minHeapIndex.add(arr);
    	}
    	
    	int []result = new int[k];
    	for(int i=0; i<k; i++) 
    	{
    		int [] arr = minHeapIndex.poll();
    		result[i] = arr[1];
    	}
    	
    	return result;
    }
    
    
    public void testCases() 
    {
		/*
		int []gifts = {25,64,9,4,100};
		int k = 4;		
		pickGifts(gifts, k);
		*/
		
		/*
		int [][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
		int k = 3;		
		kWeakestRows(mat, k);
		*/
    	
    	/*
    	int []stones = new int[]{2,7,4,1,8,1};
		lastStoneWeight(stones);
    	*/
    	
    	/*
    	int []nums = new int[]{1,5,0,3,5};
		minimumOperations(nums);
		*/
    }
        
	
	public static void main(String []args) 
	{
		System.out.println("");
		
		
		int [][]nums = new int[][]{{1,1,2,2,3,4,2,3},{3,8,7,8,7,5}, {9,2,2}};
		int [] k = {6,2,3}, x = {2,2,3};
		int index = 2;
		int []result = findXSum(nums[index], k[index], x[index]);
		System.out.println(Arrays.toString(result));
		
	}
}
