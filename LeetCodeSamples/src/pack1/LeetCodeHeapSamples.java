package pack1;

import java.util.Arrays;
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
    }
	
	public static void main(String []args) 
	{
		System.out.println("");
		
		int []nums = new int[]{1,5,0,3,5};
		minimumOperations(nums);
		
	}
}
