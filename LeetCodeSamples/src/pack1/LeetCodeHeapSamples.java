package pack1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.PrivateCredentialPermission;

import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashSet;

import java.util.PriorityQueue;
import java.util.Queue;

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
    
    public static String frequencySort(String s) 
    {
    	/*
		  Tarih         : 17.11.2026
		  Durum         : Çözüldü.  
		  Problem Adi   : 451. Sort Characters By Frequency
		  Problem Link  : https://leetcode.com/problems/sort-characters-by-frequency/description/
		  Algoritma     : 
		  Diger         : 
		*/
    	
    	char []arr = s.toCharArray();
    	int []freq = new int[123];   //freq[0] -> 'A', freq[1]->'B'...freq[25]->'Z'    'z':122
    	//System.out.println("A:" + (int)'A' + "       " + "z:" + (int)'z' + "--------------" + "0:" + (int)'0' + (char)121);
    	
    	class Pair
    	{
    		char ch;
    		int freq;
    		public Pair(char ch, int freq) 
    		{
    			this.ch = ch;
    			this.freq = freq;
    		}
    	}
    	
    	PriorityQueue<Pair> maxHeap = new PriorityQueue<>( (a,b) -> 
	    	{
	    		int comp = Integer.compare(b.freq, a.freq);
	    		if(comp==0)    return a.ch - b.ch;        //frekanslar ayný ise karaktere göre sýrala. Problemde belirtilmemiþ ama çýktýda ayný karakterlerin yan yana olmasý isteniyor.
	    		return comp;
	    	}
    	);      //default minHeap. We convert it to maxHeap. 
    	
    	for(char ch:arr) 
    	{
    		int asciKod = (int)ch;
    		freq[asciKod]++;    		
    	}
    	
    	for(int i=0; i<freq.length; i++) 
    	{    		
    		//Pair p1 = new Pair((char)('a'+i), freq1[i]);
    		
    		//if(freq1[i]>0)  { for(int j=0; j<freq1[i]; j++)    maxHeap.add(new Pair((char)('a'+i), freq1[i])); }
    		//if(freq2[i]>0)  { for(int j=0; j<freq2[i]; j++)    maxHeap.add(new Pair((char)('A'+i), freq2[i]));   }
    		if(freq[i]>0)  { for(int j=0; j<freq[i]; j++)    maxHeap.add(new Pair((char)i, freq[i])); }
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while(maxHeap.size()>0) 
    	{
    		char ch = maxHeap.poll().ch;
    		sb.append(ch);
    	}
    	
    	return sb.toString();
    	    			
    }
    
    public int minSetSize(int[] arr) 
    {
    	/*
		  Tarih         : 17.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 1338. Reduce Array Size to The Half
		  Problem Link  : https://leetcode.com/problems/reduce-array-size-to-the-half/description/
		  Algoritma     : MaxHeap
		  Diger         : 
		*/
		

    	
        PriorityQueue<int[] > maxHeap = new PriorityQueue<>( (a,b) -> b[1]-a[1]);   //default minHeap. We must convert to maxHeap.   index0 -> val, index1->Freq        
                
        Map<Integer,Integer> mapFreq = new HashMap<>();
        
        for(int num:arr) 
        {
        	mapFreq.put(num, mapFreq.getOrDefault(num, 0) +1);
        }
        
        for(Entry<Integer, Integer> e : mapFreq.entrySet()) 
        {
        	maxHeap.add(new int[] {e.getKey(), e.getValue()});
        }
        
        int arrSize = arr.length;
        int elmFreqCount = 0;
        int elmCount=0;
        
        while(elmFreqCount<arrSize/2) 
        {
        	elmFreqCount += maxHeap.poll()[1];
        	elmCount++;
        }
        
        return elmCount;
    }
    
    public int findKthLargest(int[] nums, int k) 
    {
    	/*
		  Tarih         : 17.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 215. Kth Largest Element in an Array
		  Problem Link  : https://leetcode.com/problems/kth-largest-element-in-an-array/description/ 
		  Algoritma     : MaxHeap
		  Diger         : 
		*/		
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> b-a );   //converted to maxHeap   
    	
    	for(int num : nums)
    		maxHeap.add(num);
    	
    	int kthLargestNum=-1;
    	
    	for(int i=0; i<k;i++) 
    		kthLargestNum = maxHeap.poll();
    	
    	return kthLargestNum;
    }
    
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) 
    {
    	
    	/*
		  Tarih         : 17.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 786. K-th Smallest Prime Fraction
		  Problem Link  : https://leetcode.com/problems/k-th-smallest-prime-fraction/description/
		  Algoritma     : MinHeap
		  Diger         : 
		*/
		

    	
    	//a[0] ->pay, a[1] payda    	        	
    	PriorityQueue<int[]> minHeap = new PriorityQueue<>( (a,b) -> 
    		{
    			int comp = Double.compare(a[0]*1.0/a[1], b[0]*1.0/b[1]); 
    			return comp;    				    				
    		}
    	);
    	
    	int size = arr.length;
    	
    	for(int i=0; i<size; i++) 
    	{
    		for(int j=i+1; j<size; j++) 
    		{
    			minHeap.add(new int[] {arr[i], arr[j]});
    		}
    	}
    	
    	//int []result = new int[2];
    	
    	for(int i=0; i<k-1; i++) 
    	{
    		System.out.println(Arrays.toString(minHeap.poll()));
    	}
    	
    	return minHeap.peek();
    }

    public static int[][] kClosest(int[][] points, int k) 
    {
    	/*
		  Tarih         : 17.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 973. K Closest Points to Origin
		  Problem Link  : https://leetcode.com/problems/k-closest-points-to-origin/description/
		  Algoritma     : MinHeap
		  Diger         : 
		*/		

    	//PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->(a[0]*a[0]+a[1]*a[1])-(b[0]*b[0]+b[1]*b[1]));
    	PriorityQueue<double[]> minHeap = new PriorityQueue<>( (a,b) -> Double.compare( a[2], b[2]) );   //Min Heap. double[] -> (x,y, distance).  Orjine olan uzaklýða göre heap oluþturulsun. Min uzaklýk head olur.
    	
    	for(int []point : points) 
    	{
    		double distance = Math.sqrt( Math.pow(point[0],2) + Math.pow(point[1],2));
    		minHeap.add( new double[] { point[0], point[1], distance} );
    	}
    	
    	int[][] result = new int[k][2];
    	
    	for(int i=0; i<k; i++) 
    	{
    		double [] pointAndDistance = minHeap.poll();
    		result[i][0] = (int) pointAndDistance[0];
    		result[i][1] = (int) pointAndDistance[1];
    	}
    	
    	return result;
    	
    }
    
    public static String repeatLimitedString(String s, int repeatLimit) 
    {
    	/*
		  Tarih         : 18.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2182. Construct String With Repeat Limit 
		  Problem Link  : https://leetcode.com/problems/construct-string-with-repeat-limit/description/ 
		  Algoritma     : maxHeap
		  Diger         : 
		*/	
    	
    	PriorityQueue<Object[]> maxHeap = new PriorityQueue<>( (a,b) -> (char)b[0]-(char)a[0]);         //Convert to MaxHeap.  Array is (character, frekans)
    	
    	int chCount = 'z'-'a'+1;
    	int [] freq = new int[chCount];   //=26
    	
    	for(char ch:s.toCharArray())
    		freq[ch-'a']++;
    	
    	for(int i=0; i<chCount; i++) 
    	{
    		if(freq[i]>0) 
    			maxHeap.add(new Object[] {(char)('a'+i), freq[i]});
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while(maxHeap.size()>0) 
    	{
    		Object []chData1 = maxHeap.poll();
    		char ch1 = (char) chData1[0];
    		int count1 = (int) chData1[1];
    		
    		int tempCount1 = count1;
    		
    		for(int i=0; i<repeatLimit && i<tempCount1; i++) 
    		{
    			sb.append(ch1);
    			count1--;
    		}
    		
    		if(count1>0 && maxHeap.size()>0) 
    		{
    			Object []chData2 = maxHeap.poll();
        		char ch2 = (char) chData2[0];
        		int count2 = (int) chData2[1];        		
        		sb.append(ch2);
        		count2--;
        		if(count2>0)
        			maxHeap.add(new Object[] {ch2, count2});
        		
        		maxHeap.add(new Object[] {ch1, count1});
    		}    		
    			
    	}
    	
    	return sb.toString();
    }
        
    public static double maxAverageRatioCalcGain(int pass, int total)
    {
    	return (pass+1.0)/(total+1) - pass*1.0/total;    
    }
    
    public static double maxAverageRatio(int[][] classes, int extraStudents) 
    {
    	/*
		  Tarih         : 18.11.2026
		  Durum         : Çözüldü. (Editör'ün çözümünden yardým alýndý.) 
		  Problem Adi   : 1792. Maximum Average Pass Ratio
		  Problem Link  : https://leetcode.com/problems/maximum-average-pass-ratio/description 
		  Algoritma     : maxHeap
		  Diger         : 
		*/		
    	
    	PriorityQueue<Object[]> maxHeap = new PriorityQueue<>((a,b) -> Double.compare((double)b[2], (double)a[2]) );    //arr[3] ->{pass, total, ratioDiff}    
                       
        for(int[] c : classes )
        {
        	int pass = c[0];
        	int total = c[1];        
        	double ratioDiff = maxAverageRatioCalcGain(pass, total);
        	maxHeap.add(new Object[] { pass, total, ratioDiff });        	
        }                
        
        for(int i=0; i<extraStudents; i++) 
        {        
        	Object [] c = maxHeap.poll();	        
        	int pass = (int) c[0] + 1;
        	int total = (int) c[1] + 1;
        	double ratioDiff = maxAverageRatioCalcGain(pass, total);
        	maxHeap.add(new Object[] {pass, total, ratioDiff});	        	       
        }        
        
        double sumOfRatios = 0;
        int classCount = classes.length;
        
        for(int i=0; i<classCount; i++) 
        {
        	Object [] c = maxHeap.poll();
        	int pass = (int) c[0] ;
        	int total = (int) c[1];       
        	sumOfRatios += pass*1.0/total;
        }                
                
        return sumOfRatios/classCount;                
    }
    
    public static long maximumImportance(int n, int[][] roads) 
    {
    	/*
		  Tarih         : 18.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2285. Maximum Total Importance of Roads
		  Problem Link  : https://leetcode.com/problems/maximum-total-importance-of-roads/description
		  Algoritma     : MinHeap.
		  Diger         : 
		*/
		
    	PriorityQueue<int[]> minHeap = new PriorityQueue<>( (a,b) -> a[1]-b[1]);     //int[2] -> cityId, CityDegree
    	
    	int []cityDegrees = new int[n];
    	
    	for(int []road:roads) 
    	{
    		cityDegrees[road[0]]++;
    		cityDegrees[road[1]]++;
    	}
    	
    	for(int i=0; i<n; i++) 
    	{
    		minHeap.add(new int[] {i, cityDegrees[i]});
    	}    	    	
    	
    	int [] cityImportance = new int[n];
    	
    	for(int i=0; i<n; i++) 
    	{
    		int cityId = minHeap.poll()[0];
    		cityImportance[cityId] = i+1; 
    	}
    	
    	long sum = 0;
    	for(int []road:roads) 
    	{
    		sum += cityImportance[road[0]] + cityImportance[road[1]];  
    	}
    	
    	return sum;
    }
    
    public int maximumScore(int a, int b, int c) 
    {
    	/*
		  Tarih         : 19.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 1753. Maximum Score From Removing Stones
		  Problem Link  : https://leetcode.com/problems/maximum-score-from-removing-stones/description
		  Algoritma     : MaxHeap.
		  Diger         : 
		*/		
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (d,e) -> e-d );
    	
    	maxHeap.add(a);
    	maxHeap.add(b);
    	maxHeap.add(c);    	
    	
    	int score=0;
    	
    	while(maxHeap.size()>1) 
    	{
    		int elm1 = maxHeap.poll();
    		int elm2 = maxHeap.poll();
    		
    		elm1--;
    		elm2--;
    		
    		if(elm1>0) 
    			maxHeap.add(elm1);
    		if(elm2>0) 
    			maxHeap.add(elm2);
    		
    		score++;
    	}
    	
    	return score; 
    }
    
    public int[] topKFrequent(int[] nums, int k) 
    {
    	/*
		  Tarih         : 19.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 347. Top K Frequent Elements
		  Problem Link  : https://leetcode.com/problems/top-k-frequent-elements/description/
		  Algoritma     : maxHeap
		  Diger         : 
		*/
		    	
    	//create and fill freq map
    	HashMap<Integer, Integer> mapFreq = new HashMap<>();
    	
    	for(int num:nums) 
    	{
    		mapFreq.put(num, mapFreq.getOrDefault(num, 0)+1);
    	}
    	
    	//create and fill heap
    	PriorityQueue<int[]>  maxFreqHeap = new PriorityQueue<>((a,b) -> b[1]-a[1]);     //convert to maxHeap. int[] -> index0:num, index1=freq 
    	        	
    	for(Entry<Integer, Integer> ent : mapFreq.entrySet())
    		maxFreqHeap.add(new int[] {ent.getKey(), ent.getValue()});
    	
    	//create result array
    	int [] mostFreqNums = new int[k];
    	
    	for(int i=0; i<k; i++)
    		mostFreqNums[i] = maxFreqHeap.poll()[0];   //index0 is number 
    		
		return mostFreqNums;    	
    }
    
    class SeatManager 
    {
    	/*
		  Tarih         : 19.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 1845. Seat Reservation Manager
		  Problem Link  : https://leetcode.com/problems/seat-reservation-manager/description 
		  Algoritma     : MinHeap
		  Diger         : 
		*/
    
    	PriorityQueue<Integer> minHeap;
    	
        public SeatManager(int n) 
        {
        	minHeap = new PriorityQueue<>();   //default is minHeap
        	
        	for(int i=1; i<=n; i++)
        		minHeap.add(i);
        }
        
        public int reserve() 
        {
            return minHeap.poll();
        }
        
        public void unreserve(int seatNumber) 
        {
        	minHeap.add(seatNumber);
        }
    }
    
    public static int minStoneSum(int[] piles, int k) 
    {
    	/*
		  Tarih         : 19.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 1962. Remove Stones to Minimize the Total
		  Problem Link  : https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/
		  Algoritma     : 
		  Diger         : 
		*/
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
    	int remaining = 0;
    	
    	for(int stoneCount: piles) 
    	{
    		maxHeap.add(stoneCount);
    		remaining += stoneCount;
    	}
    	
    	for(int i=0; i<k && maxHeap.size()>0; i++) 
    	{
    		int stoneCount = maxHeap.poll();
    		int halfOfStoneCount = (int) Math.ceil(stoneCount/2d);    		
    		remaining += -1*stoneCount + halfOfStoneCount;
    		maxHeap.add(halfOfStoneCount);
    	}
    	
    	return remaining;
    }
           
    public static int maximumSum(int[] nums) 
    {    
    	/*
		  Tarih         : 19.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2342. Max Sum of a Pair With Equal Sum of Digits
		  Problem Link  : https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits 
		  Algoritma     : MaxHeap, minHeap
		  Diger         : 
		*/
    	
    	class DigitSum
    	{
    		int digitSum;
    		int freq;
    		PriorityQueue<Integer> numListHeap;
    		
    		public DigitSum(int digitSum) 
    		{
    			this.digitSum = digitSum;
    			freq = 1;
    			numListHeap = new PriorityQueue<>();     //for space optimization we only keep max two numbers
    		}
    		public void addNum(int num) 
    		{ 
    			numListHeap.add(num);
    			if(numListHeap.size()>2) 
                    numListHeap.poll(); 
    		}
    		public void incrementFreq() { freq++; }
    	}
    	
    	HashMap<Integer, DigitSum> mapFreq = new HashMap<>();   //digitSum, Frequency    	    	    	
    	
    	for(int num : nums) 
    	{
    		int digitSum = Utils.findDigitSum(num);
    		
    		DigitSum obj = mapFreq.get(digitSum);
    				
    		if(obj==null ) 
    		{
    			obj = new DigitSum(digitSum);	
    			mapFreq.put(digitSum, obj);
    		} 
    		else 
    		{    			
    			obj.incrementFreq();
    		}
    		obj.addNum(num);    	
    	}
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);    //convert to maxHeap
    	
    	for(Entry<Integer, DigitSum> ent : mapFreq.entrySet()) 
    	{    		
    		DigitSum obj = ent.getValue();
    		int freq = obj.freq;
    		
    		if(freq<2) continue;
    		
    		int maxTwoNumSum = obj.numListHeap.poll() + obj.numListHeap.poll();    
    		
    		maxHeap.add(maxTwoNumSum);
    	}
    	
    	return (maxHeap.size()>0) ? maxHeap.peek() : -1;
    	
    }
    
    public int kthSmallest(int[][] matrix, int k) 
    {    	
    	int rowCount = matrix.length;
    	int columnCount = matrix[0].length;
    
    	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    	
    	for(int i=0; i<rowCount; i++) 
    	{
    		for(int j=0; j<columnCount; j++) 
    		{
    			minHeap.add(matrix[i][j]);
    		}
    	}
    	
    	for(int i=0; i<k-1; i++) 
    	{
    		minHeap.poll();
    	}
    	    	
    	return minHeap.peek();    
    }
    
    public long maxKelements(int[] nums, int k) 
    {
    	/*
		  Tarih         : 20.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 2530. Maximal Score After Applying K Operations
		  Problem Link  : https://leetcode.com/problems/maximal-score-after-applying-k-operations/description
		  Algoritma     : maxHeap
		  Diger         : 
		*/
		

    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);   //convert to maxHeap
    	long score = 0;
    	
    	for(int i=0; i<nums.length;i++) 
    	{
    		maxHeap.add(nums[i]);    		
    	}
    	
    	for(int i=0; i<k; i++) 
    	{
    		int num = maxHeap.poll();
    		score+=num;
    		maxHeap.add((int) Math.ceil(num/3.0));
    	}
    	
    	return score;
    }

    public long findScore(int[] nums) 
    {
    	/*
		  Tarih         : 20.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2593. Find Score of an Array After Marking All Elements
		  Problem Link  : https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/description 
		  Algoritma     : 
		  Diger         : 
		*/

    	PriorityQueue<int[]> minHeap = new PriorityQueue<>( (a,b) ->                  //int[] ->{index, value}                    
    		{ 
    			int comp = Integer.compare(a[1],b[1]);
    			
    			if(comp==0)  return a[0]-b[0]; 
    			return comp;  
    		}
    	);    
    	
    	int numSize = nums.length;
    	
    	for(int i=0; i<numSize; i++) 
    	{
    		minHeap.add(new int[] {i, nums[i]});
    	}
    	
    	boolean []flagArr = new boolean[numSize];
    	long score = 0;
    	
    	while(minHeap.size()>0) 
    	{    		    		
    		int []idxAndNum = minHeap.poll();
    		int idx = idxAndNum[0];
    		int num = idxAndNum[1];
    	
    		if(flagArr[idx]==true) continue;
    		
    		score+=num;
    		
    		flagArr[idx] = true;
    		if(idx>0)  flagArr[idx-1] = true;
    		if(idx<numSize-1)  flagArr[idx+1] = true;
    	}
    	
    	return score;
    }

    public static int maxSumDistinctTriplet(int[] x, int[] y) 
    {
    	/*
		  Tarih         : 20.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 3572. Maximize Y-Sum by Picking a Triplet of Distinct X-Values
		  Problem Link  : https://leetcode.com/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues
		  Algoritma     : 
		  Diger         : 
		*/	
    	
    	// (x,y) pairs at same index 
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((arr1, arr2) -> arr2[1] - arr1[1]);  //convert to maxHeap. Compare y values
    	
    	for(int i=0; i<x.length; i++) 
    	{
    		maxHeap.add(new int[] {x[i], y[i]});
    	}
    	
    	int count =0 ;
    	int sum  = 0;
    	Set <Integer> xValuesSet = new HashSet<>();
    	
    	while(count<3 && maxHeap.size()>0) 
    	{
    		int []xy = maxHeap.poll();
    		
    		if(xValuesSet.contains(xy[0])) continue;
    		
    		xValuesSet.add(xy[0]);
    		sum += xy[1];
    		count++;    		
    	}
    	
    	return (count<3) ? -1 : sum;    	
    }

    public int matrixSum(int[][] nums) 
    {
    	/*
		  Tarih         : 20.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2679. Sum in a Matrix
		  Problem Link  : https://leetcode.com/problems/sum-in-a-matrix/description/
		  Algoritma     : maxHeap
		  Diger         : 
		*/
    	
    	int rowCount = nums.length;
    	int colCount = nums[0].length;
    	
    	PriorityQueue<Integer> [] maxHeapList = new PriorityQueue[rowCount];
    	
    	for(int i=0; i<rowCount; i++) 
    	{
    		maxHeapList[i] = new PriorityQueue<>((a,b) -> b-a);
    		
    		for(int j=0; j<colCount; j++) 
    		{
    			maxHeapList[i].add(nums[i][j]);
    		}
    	}
    	
    	int score  = 0;    	
    	
    	for(int i=0; i<colCount; i++) 
    	{
    		int max =0;    //max of column
	    	for(int j=0; j<rowCount; j++) 
	    	{
	    		int val = maxHeapList[j].poll();
	    		if(val>max) max = val;
	    	}
	    	score += max;
	    	
    	}
    	
    	return score;
    	
    }
    
    public List<String> topKFrequent(String[] words, int k) 
    {
    	/*
		  Tarih         : 21.11.2026
		  Durum         : Çözüldü.
		  Problem Adi   : 692. Top K Frequent Words
		  Problem Link  : https://leetcode.com/problems/top-k-frequent-words/description/
		  Algoritma     : MaxHeap.
		  Diger         : 
		*/
    	
    	Map<String, Integer> mapFreq = new HashMap<>();
    	
    	for(String word:words) 
    	{
    		mapFreq.put(word, mapFreq.getOrDefault(word, 0)+1);
    	}
    	
    	PriorityQueue<Object[]> maxHeap = new PriorityQueue<>((arr1,arr2) ->     //arr[0] -> Word, arr[1]->frequency
    		{
    			int comp = Integer.compare((int)arr2[1], (int)arr1[1]);
    			if(comp==0)
    				return ((String)arr1[0]).compareTo( (String)arr2[0]); 
    			return comp;
    		}
    	);
    	
    	for(Entry<String, Integer> ent : mapFreq.entrySet()) 
    	{
    		maxHeap.add(new Object[] {ent.getKey(), ent.getValue()});
    	}
    	
    	List<String> result = new ArrayList<>();
    	
    	for(int i=0; i<k; i++) 
    	{
    		result.add((String)maxHeap.poll()[0]);
    	}
    	
    	return result;
    }
    
    public long maxSum(int[][] grid, int[] limits, int k) 
    {
    	/*
		  Tarih         : 23.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 3462. Maximum Sum With at Most K Elements
		  Problem Link  : https://leetcode.com/problems/maximum-sum-with-at-most-k-elements/description
		  Algoritma     : MaxHeap
		  Diger         : 
		*/
		    	
    	int rowCount = grid.length;
    	int colCount = grid[0].length;
    	
    	PriorityQueue<Integer> []maxHeadArr = new PriorityQueue[rowCount];
    	
    	for(int i=0; i<rowCount; i++) 
    	{
    		maxHeadArr[i] = new PriorityQueue<>((a,b) -> b-a);          //converted to max array
    		
    		for(int j=0; j<colCount; j++) 
    		{
    			maxHeadArr[i].add(grid[i][j]);
    		}    		
    	}
    	    	
    	
    	PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a,b)->b-a);      //converted to max array
    	
    	for(int i=0; i<rowCount; i++) 
    	{
    		for(int j=0; j<colCount && j<limits[i]; j++) 
    		{
    			maxHeap2.add(maxHeadArr[i].poll());
    		}
    	}
    	
    	long sum =0;
    	for(int i=0; i<k && !maxHeap2.isEmpty(); i++)
    		sum += maxHeap2.poll();
    	
    	return sum;
    	
    }
    
   
    public int[] findDiagonalOrder(List<List<Integer>> nums) 
    {
    	/*
		  Tarih         : 23.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 1424. Diagonal Traverse II
		  Problem Link  : https://leetcode.com/problems/diagonal-traverse-ii/description/ 
		  Algoritma     : minHeap
		  Diger         : Önce satir ve sütun indexlerinin toplamý min olan önceliklendirilir, eðer aynýlarsa satir indexi büyük olan önceliklendirilir.
		*/		
    	
    	PriorityQueue<int[]> minHeap = new PriorityQueue<>((arr1, arr2)->      //i,j, num
	    	{
	    		int comp =  Integer.compare(arr1[0]+arr1[1], arr2[0]+arr2[1] );       //satir ve sütun indexlerinin toplamý küçük olan öncelikli olsun
	    		if(comp==0) return arr2[0] - arr1[0];                                 //toplamlar eþitse satir indexi daha büyük olan öncelikli olsun
	    		return comp;
	    	}
    	);
    	
    	int elmCount =0;
    	
    	for(int i=0; i<nums.size(); i++) 
    	{
    		for(int j=0; j<nums.get(i).size(); j++) 
    		{
    			minHeap.add(new int[] {i, j, nums.get(i).get(j)});
    			elmCount++;
    		}
    	}
    	
    	int [] result = new int[elmCount];
    	
    	for(int i=0; i<elmCount; i++) 
    	{
    		result[i] = minHeap.poll()[2];
    	}
    		 
    	return result;
    	
    }
    
    public static String reorganizeString(String s) 
    {
    	/*
		  Tarih         : 23.11.2026
		  Durum         : Çözüldü. (Bir çözümden yardým alýndý.)
		  Problem Adi   : 767. Reorganize String
		  Problem Link  : https://leetcode.com/problems/reorganize-string/description/ 
		  Algoritma     : 
		  Diger         : 
		*/		
    	
        PriorityQueue<Object[]> maxHeapFreq = new PriorityQueue<>( (arr1, arr2) ->
    		{
    			int comp = Integer.compare((int)arr2[1], (int)arr1[1]);
    			if(comp==0)
    				return (char)arr1[0]-(char)arr2[0];
    			return comp;
    		}
        );          
        
        int []freqCh = new int['z'-'a'+1];
        
        for(char ch:s.toCharArray())
        	freqCh[ch-'a']++;
        
        for(int i=0; i<freqCh.length; i++) 
        {
        	if(freqCh[i]==0) 
        		continue;
        	maxHeapFreq.add(new Object[] { (char)('a' + i), freqCh[i]} );
        }
        
        StringBuilder sb = new StringBuilder();
        Object [] prev = new Object[] {'.', 0};
        Object [] curr = null;
                       
        while( !maxHeapFreq.isEmpty() ) 
        {        	        	        
        	curr = maxHeapFreq.poll();        	
        	
        	char ch = (char)curr[0];        	
        	
        	sb.append(ch);        	        	
        	
        	if((int)prev[1]>0) 
        	{  
    			maxHeapFreq.add(prev);
        	}
        	
        	curr[1] = (int)curr[1] - 1;        	//frekans bir azaltýlýr.
        	        		
        	prev = curr;        	        	        	
        }        
        
        String resultStr = sb.toString();
        
        return resultStr.length()==s.length() ? resultStr  : "";        
    }
    
    class EventManager 
    {
    	/*
    	  Tarih         : 24.11.2026
    	  Durum         : Çözüldü.
    	  Problem Adi   : 3885. Design Event Manager
    	  Problem Link  : https://leetcode.com/problems/design-event-manager/description/ 
    	  Algoritma     : MaxHeap (lazy)
    	  Diger         : 
    	*/	
    	
    	PriorityQueue<int[]> maxEventHeap;               //eventId, priority
    	Map<Integer, Integer> mapEvent;                  //eventId, priority
    	
        public EventManager(int[][] events) 
        {
        	maxEventHeap = new PriorityQueue<>( (e1, e2) -> 
            	{
            		int comp = Integer.compare(e2[1], e1[1]);
            		if(comp==0)
            			return Integer.compare(e1[0], e2[0]);
            		return comp;
            	}
        	);
        	
        	mapEvent = new HashMap<>();    
        	
        	for(int []event : events) 
        	{    		
        		mapEvent.put(event[0], event[1]);
        		maxEventHeap.add(event);
        	}            
        }
        
        public void updatePriority(int eventId, int newPriority) 
        {    
        	mapEvent.put(eventId, newPriority);
        	maxEventHeap.add(new int[] {eventId, newPriority} );
        }
        
        public int pollHighest() 
        {
        	int idxOfHighestEvent = -1;
        	
        	while(!maxEventHeap.isEmpty()) 
        	{
        		int [] e1 = maxEventHeap.poll();
        		
        		if(!mapEvent.containsKey(e1[0])) 
        			continue;
        		
        		int validPriority = mapEvent.get(e1[0]);
        		
        		if(e1[1] == validPriority) 
        		{
        			idxOfHighestEvent = e1[0];
        			mapEvent.remove(e1[0]);
        			break;
        		}
        	}
        	
        	return idxOfHighestEvent;
        }
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) 
    {
    	/*
		  Tarih         : 24.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 658. Find K Closest Elements
		  Problem Link  : https://leetcode.com/problems/find-k-closest-elements/description
		  Algoritma     : 
		  Diger         : 
		*/		

    	PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> 
	    	{
	    		
	    		int comp = Integer.compare( Math.abs(x-a), Math.abs(x-b) );
	    		if(comp==0)
	    			return Integer.compare(a, b);
	    		
	    		return comp;
    		}
    	);
    	
    	for(int num : arr)
    		minHeap.add(num);
    	
    	List<Integer> ans = new ArrayList<>();
    	for(int i=0; i<k;i++)
    		ans.add(minHeap.poll());
    	
    	Collections.sort(ans);
    	
		return ans;        
    }
        
    class SmallestInfiniteSet 
    {
    	/*
		  Tarih         : 25.11.2026
		  Durum         : Çözüldü. 
		  Problem Adi   : 2336. Smallest Number in Infinite Set
		  Problem Link  : https://leetcode.com/problems/smallest-number-in-infinite-set/description
		  Algoritma     : MinHeap
		  Diger         : 
		*/		

    	PriorityQueue<Integer> minHeapRemovedAndAddedLater;
    	int smallestNum;

        public SmallestInfiniteSet() 
        {
        	minHeapRemovedAndAddedLater = new PriorityQueue<>();
        	smallestNum = 1;
        }
        
        public int popSmallest() 
        {        
        	int smallestNum2 = smallestNum;
        	        	        	
        	if(!minHeapRemovedAndAddedLater.isEmpty()) 
        		smallestNum2 = minHeapRemovedAndAddedLater.poll();        	
        	else        	
        		this.smallestNum++;
        	
    		return smallestNum2;            
        }
        
        public void addBack(int num) 
        {
        	if(!minHeapRemovedAndAddedLater.contains(num)  && smallestNum>num) 
        	{
        		minHeapRemovedAndAddedLater.add(num);   
        	}        	
        }
    }
    

    
    public static int[] rearrangeBarcodes(int[] barcodes) 
    {
    	
    	//henüz çözülmedi(25.11.2026)
    	PriorityQueue<int[]> maxFreqHeap = new PriorityQueue<>((arr1,arr2)->      //arr[] = {val, freq}
	    	{
	    		int comp = Integer.compare(arr2[1],arr1[1]);        //once frekanslar karsilastirilir
	    		if(comp==0)
	    			return Integer.compare(arr1[0],arr2[0]);              //frekanslar  ayný ise sayýlarýn kendisi karsilastirilir. Bu ikinci kisim olmasa da olurdu.
	    		return comp;
	    	}
    	);
    	
    	int [] freqCh = new int[10000+1];
    	
    	for(int num:barcodes)
    		freqCh[num]++;
    	
    	int barCount=0;
    	
    	for(int i=0; i<10000; i++) 
    	{
    		int freq = freqCh[i];
    		
    		if(freq==0) continue;
    		
    		maxFreqHeap.add(new int[] {i, freqCh[i]});    		
    		barCount += freqCh[i];
    	}
    	
    	int [] newBarcode = new int[barCount];
    		
    	int [] prev = maxFreqHeap.poll();
    	newBarcode[0] = prev[0];
    	prev[1]--;
    	
    	for(int i=1; i<barCount; i++) 
    	{    		    	
    		int []curr = maxFreqHeap.poll();
    		
    		newBarcode[i] = curr[0];
    		
    		if(prev[1]>0)
    			maxFreqHeap.add(prev);
    		
    		prev=curr;
    	}    	
    	
    	return newBarcode;
        
    }
    
    class NumberContainers 
    {
    	
    	HashMap<Integer, Integer> mapIdxNumber;    //index, number
    	HashMap<Integer,PriorityQueue<Integer>> mapNumberIdxHeap;

        public NumberContainers() 
        {
        	mapIdxNumber = new HashMap<>();     //index, number
        	mapNumberIdxHeap = new HashMap<>();
        }
        
        public void change(int index, int number) 
        {        	
        	mapIdxNumber.put(index, number);           //yoksa ekler, varsa replace yapar.
        }
        
        public int find(int number) 
        {
			return number;
            
        }
    }
    
    
    
    public int smallestChair(int[][] times, int targetFriend) 
    {
    	int minSeatPointer = 0;
    	PriorityQueue<Integer> minSeatHeap = new PriorityQueue<>();    //minHeap
		
    	return minSeatPointer;    	    	
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
    
    	/*
    	int [][]nums = new int[][]{{1,1,2,2,3,4,2,3},{3,8,7,8,7,5}, {9,2,2}};
		int [] k = {6,2,3}, x = {2,2,3};
		int index = 2;
		int []result = findXSum(nums[index], k[index], x[index]);
		System.out.println(Arrays.toString(result));
		*/
    
    	/*
    	String []arr = {"cccaaa", "loveleetcode", "2a554442f544asfasssffffasss"};
		String result = frequencySort(arr[2]);
		System.out.println(result);
		*/
    	
    	/*
    	int [][]arr = {{1,2,3,5}};
		int []k = {3};
		int index = 0;
		int [] result = kthSmallestPrimeFraction(arr[index], k[index]);
		System.out.println(Arrays.toString(result));
		*/
    	
    	/*
    	int [][] points = {{1,3},{-2,2}};
		int []k = {1};
		int index = 0;
		int[][] result = kClosest(points, k[index]);
		System.out.println(Arrays.toString(result));
		*/

    	/*
		String [] str = {"cczazcc","aaaaaab"};
		int []repeatLimit = {3,2};
		int index = 1;
		String result = repeatLimitedString(str[index],repeatLimit[index]);
		System.out.println(result);
		*/    	
    	
    	/*
    	int [][][]classes = {{{1,2},{3,5},{2,2}},{{2,4},{3,9},{4,5},{2,10}}};
		int []extraStudents = {2,4};
		int index = 1;
		double result = maxAverageRatio(classes[index], extraStudents[index]);
		System.out.println(result);
		*/
    	
    	/*
    	int[][] roads = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};
		int n = 5;
		int index = 0;
		double result = maximumImportance(n, roads);
		System.out.println(result);
    	*/
    	
    	/*
    	int[][] piles = {{5,4,9}};
		int k = 2;		
		int index = 0;
		double result = minStoneSum(piles[index], k);
		System.out.println(result);
		*/
    
    	/*
    	int[][] nums = {{18,43,36,13,7}, {10,12,19,14} };		
		int index = 1;
		double result = maximumSum(nums[index]);
		System.out.println(result);
		*/
    
    	/*
    	int [] x = {19,15,7,13 };
		int [] y = {13,11,13,11};
		int index = 1;		
		int result = maxSumDistinctTriplet(x,y);
		System.out.println(result);
		*/
    
    	/*
    	String []str = {"aaaaabbc","tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao"};
		int index = 1;		
		String result = reorganizeString(str[index]);
		System.out.println(result);
		*/
    
    	/*
    	int [][][] events = new int[][][]{ { {9,4}, {13,8}, {11,6} } , {{20,6},{13,2},{14,7},{17,2}} };
		EventManager eventManager = new  EventManager(events[1]);   // Initializes the manager with two events
		//eventManager.updatePriority(13,5); // return 7
		//int a = eventManager.pollHighest(); // return 4
		
		//[13,8],[13,1],[13,8],[],[],[20,5]
		eventManager.updatePriority(13,8);
		eventManager.updatePriority(13,1);
		eventManager.updatePriority(13,8);
		int a = eventManager.pollHighest();
		System.out.println(a);
		a = eventManager.pollHighest();
		System.out.println(a);
		eventManager.updatePriority(20,5);
		*/
    
    	/*
		int  [] arr = {2,4};
		int k=1;
		int x = 3;						
		int index = 1;		
		List<Integer> result = findClosestElements(arr, k, x);
		System.out.println(result);
		*/
    
    	/*
    	SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
		smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
		smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
		smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
		smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
		smallestInfiniteSet.addBack(1);    // 1 is added back to the set.		
		smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
		                                   // is the smallest number, and remove it from the set.
		smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
		smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
		*/
    }
        
	
	public static void main(String []args) 
	{		
		
		/*
		int  [][] barcodes = {{1,1,1,2,2,2}, {1,1,2}};					
		int index = 1;		
		int[] result = rearrangeBarcodes(barcodes[index]);
		System.out.println(Arrays.toString(result));
		*/
		
		
	}
}



