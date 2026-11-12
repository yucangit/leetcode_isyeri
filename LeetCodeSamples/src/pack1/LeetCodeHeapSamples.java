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
	
	public static void main(String []args) 
	{
		System.out.println("");
		
		
		int []gifts = {25,64,9,4,100};
		int k = 4;
		
		pickGifts(gifts, k);
		
	}
}
