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

	public List<Integer> getRow(int rowIndex) {
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

	public static void main(String[] args) {
		
		generate(5);
		
		
		List<Integer> result = new LinkedList<>();
		result.add(1);
		result.clear();

	}

}
