package pack1;

import java.util.Queue;
import java.util.Deque;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

//Definition for a binary tree node.
class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(){}
	
	public TreeNode(int val) 
	{
		this.val = val;
	}
	
	public TreeNode(int val, TreeNode left, TreeNode right) 
	{
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
}

//Definition for a Node.
class TreeNode2 {
	public int val;
	public TreeNode2 left;
	public TreeNode2 right;
	public TreeNode2 next;
	
	public TreeNode2() {}
	 
	public TreeNode2(int _val) {
		val = _val;
	}
	
	public TreeNode2(int _val, TreeNode2 _left, TreeNode2 _right, TreeNode2 _next) {
	    val = _val;
	    left = _left;
	    right = _right;
	    next = _next;
	}
}


class FindElements
{
	Set<Integer> set;

	public FindElements(TreeNode root) 
	{
	    //Yontem : level order traversing
		
		Queue<TreeNode> q = new LinkedList<>();
		set = new HashSet<>();
		TreeNode curr, left, right;		
		
		root.val=0;
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();  
				int val = curr.val;
				set.add(val);
				
				left = curr.left;
				right = curr.right;
						
				if(left!=null)  { 
					left.val =2*val+1;  q.add(left); 
				}
				if(right!=null) { 
					right.val=2*val+2;  q.add(right); 
				}
			}
		}	
	 }
	
	public boolean find(int target) 
	{
		return set.contains(target);
    }
}	


public class LeetCodeTreeSolutions {
	
	//Bu fonksiyonda hata var mi?(06.02.2026) . (Dogru gibi. 13.02.2026)
	public static TreeNode arrayToTree(Integer []arr) 
	{	
		//int sayac = 1;
		//System.out.println("sayac : " + sayac);
		//sayac++;
		//if(sayac>1) return null;
		
		//Bu fonksiyonda hata var mï¿½?(06.02.2026)
		int size = arr.length;
		if(size==0)  return null;
		
		Queue <TreeNode> q = new LinkedList<TreeNode>();
		
		TreeNode rootNode = null;
		rootNode = new TreeNode(arr[0]);
		//rootNode = null;
		q.add(rootNode);
		
		TreeNode node =null;
		
		for(int i=1; i<size; ) 
		{
			node = q.poll();
			Integer leftVal  = arr[i++];
			Integer rightVal = (i<size)?arr[i++]:null;
			
			if(leftVal!=null) 
			{
				TreeNode newNode = new TreeNode(leftVal);
				q.add(newNode);
				node.left = newNode;				
			}
			if(rightVal!=null) 
			{
				TreeNode newNode = new TreeNode(rightVal);
				q.add(newNode);
				node.right = newNode;				
			}
		} 
		
		return rootNode;		
	}
	
	public static boolean isCycleExists(TreeNode root) 
	{
		Queue<TreeNode> q = new LinkedList<>();		
		Set<TreeNode> set = new HashSet<>();
		TreeNode curr, left, right;
		boolean isDublicateFound = false;
		
		if(root!=null)  q.add(root);
		
		while(!q.isEmpty() && !isDublicateFound) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				
				if(set.contains(curr)) {isDublicateFound=true; break;}
				
				
				left= curr.left;
				right= curr.right;
				
				if(left!=null)   q.add(left);
				if(right!=null)  q.add(right);
			}
		}
		
		return isDublicateFound;
	}
	
	public static List<Integer> getAllQueueValues(Queue<TreeNode> q) 
	{
		List<Integer> result = new LinkedList<>();
		//int size = q.size();
		
		for(TreeNode node : q) 
		{
			Integer val = (node!=null)?node.val:null;
			result.add(val);
		}
		
		return result;
	}
	
	public static boolean isLeaf(TreeNode node) 
	{
		return node.left==null && node.right==null;
	}
	
	public static void printListContent(List<TreeNode> list, boolean yatay)
	{				
		int size = list.size();
		
		if(list==null) 
			System.out.print("Boï¿½ dizi!");
		
		for(int i=0; i<list.size(); i++) 
		{
			Integer val = list.get(i).val;
			
			if( yatay == true) 
			{
				System.out.print(val);
				if(i<size-1) System.out.print(", ");
			}
			else 
			{
				System.out.print(val);
				if(i<size-1) System.out.println(", ");
			}
		}									
	}
		
	public static List<TreeNode> inOrderTraversal(TreeNode root)
	{
		//bu fonksiyon dogru calismiyor gibi(11.02.2026).
		
		Deque<TreeNode> st  = new LinkedList<>();      //deque class faster than stack class 
		Set<TreeNode> processed = new HashSet<>();     //left and right childs added to stack
		List<TreeNode>  result = new LinkedList<>();
		
		TreeNode curr, left, right;
		
		if(root!=null)   st.addLast(root);
		
		while(!st.isEmpty()) 
		{
			curr  = st.pollLast();
			
			if(isLeaf(curr))  
			{ 
				result.add(curr); 
				continue; 
			}
			else if(processed.contains(curr))  
				result.add(curr);
			else
			{
				left  = curr.left;
				right = curr.right;
				
				if(right!=null) 
					st.addLast(right);
				st.addLast(curr);
				if(left!=null) 
					st.addLast(left);
					
				processed.add(curr);
			}											
		}
		
		return result;
		
	}
	
	public static List<TreeNode> preOrderTraversal(TreeNode root)
	{
		Deque<TreeNode> st = new LinkedList<>();        //deque is faster than stack
		Set<TreeNode> visitedNodes = new HashSet<>();   //left and right childs inserted onto stack
		List<TreeNode> result = new LinkedList<>();     //pre ordered result list
		TreeNode left,right, curr;
				
		st.addLast(root);
		
		while(!st.isEmpty()) 
		{
			curr = st.pollLast();
			
			if( visitedNodes.contains(curr)) 
				result.add(curr);
			else 
			{
				left  = curr.left;
				right = curr.right;
				
				if(right!=null) 
					st.addLast(right);
				if(left!=null) 
					st.addLast(left);
				
				visitedNodes.add(curr);
				st.addLast(curr);
			}
		}
		
		for(int i=0; i<result.size(); i++)
			System.out.println(result.get(i).val + ",");
		
		return result;
	}
	
	public static List<TreeNode> inOrderTraversalRec(TreeNode root)
	{
		
		return null;		
	}
		
	public static int findLevelCount(TreeNode root) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode node = null, left=null, right=null;
		int level = 1;
		
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			node = q.poll();
			left = node.left;
			right = node.right;
			
			if(left==null && right==null) continue; 

			level++;
			
			if(left!=null)    q.add(left);
			if(right!=null)   q.add(right);			
		}
		
		return level;			
	}
	
	public static TreeNode subtreeWithAllDeepest(TreeNode root) 
	{			
		//Problem      : 865. Smallest Subtree with all the Deepest Nodes
		//Problem link : https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/?envType=daily-question&envId=2026-01-09
		//Durum        : Yapildi(16.01.2026).
	
		if(root.left==null && root.right==null) return root;
		
		Map<TreeNode,Integer>  map1 = new HashMap<>();     //node, level
		Map<TreeNode,TreeNode> map2 = new HashMap<>();     //node, parentNode
				
		Queue <TreeNode> q = new LinkedList<>();           //for level based traversing, to find level and parent of each Node.		
		TreeNode currNode=null, left=null, right=null;
		
		int maxLevel=1, level=1;
		
		map2.put(root,null);
		q.add(root);		
		
		while( !q.isEmpty())      //find node's level and parentNode, and put to them to map1 and map2 
		{
			int qSize = q.size();
			
			if( level > maxLevel ) maxLevel= level;
			
			for(int i=0; i<qSize; i++)
			{
				currNode = q.poll();
				left = currNode.left;
				right = currNode.right;
				
				map1.put(currNode, level);
				
				if(left!=null ) 
				{
					q.add(left);				
					map2.put(left, currNode);
				}
				if(right!=null ) 
				{
					q.add(right);				
					map2.put(right, currNode);
				}
			}
			level++;
		}
			
		
		Set<TreeNode> deepestNodesSet = new HashSet<>();
		
		for(Map.Entry<TreeNode, Integer> entry:map1.entrySet()) 
		{			
			if(entry.getValue()==maxLevel) 
			{		
				deepestNodesSet.add(entry.getKey());
			}
		}
						
		Set<TreeNode> parentNodesSet = new HashSet<>();
		Set<TreeNode> tempParentNodesSet = new HashSet<>();
		
		for(TreeNode node:deepestNodesSet) 
		{
			parentNodesSet.add(map2.get(node));
		}
		
		for(int i=0; i<maxLevel-2 && parentNodesSet.size()!=1; i++) 
		{	
			tempParentNodesSet = new HashSet<TreeNode>(parentNodesSet);
			parentNodesSet.clear();		
			
			for(TreeNode node:tempParentNodesSet) 
			{
				parentNodesSet.add(map2.get(node));
			}									
		}
		
		TreeNode smallestSubTree = (TreeNode)( ( deepestNodesSet.size()==1) ? deepestNodesSet.toArray()[0]: parentNodesSet.toArray()[0]);
		
		return smallestSubTree; 
										
	}
	
	public static int rangeSumBST(TreeNode root, int low, int high) 
	{
		int sum = 0;
		
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode node = root, left=null, right=null;
		
		q.add(node);
		
		while( !q.isEmpty() ) 
		{
			node = q.poll();
			int val = node.val;
			
			if(val>=low && val<=high) sum += val;
			
			left  = node.left;
			right = node.right;
					
			if(left!=null  && val>low)  q.add(left);
			if(right!=null && val<high) q.add(right);
			
		}
						
		return sum;
	} 
	
	public static TreeNode evaluteExp(TreeNode n1, TreeNode n2, TreeNode op) 
	{
		int val1= n1.val;
		int val2= n2.val;
		int opVal  = op.val;
		
		return new TreeNode ( (opVal==2)? val1|val2 : val1&val2 );				
	}
	
	public static boolean evaluateTree1_iterative(TreeNode root) 
	{
		Deque<TreeNode> st = new LinkedList<>();
		TreeNode curr=null, left=null, right=null;
		boolean result=false;
		
		st.addLast(root);
		
		while(!st.isEmpty()) 
		{
			curr = st.peekLast();
			left = curr.left;
			right = curr.right;
			
			if(curr.val>1)           //operators(AND=3,OR=2)
			{
				if(left.val<2 && right.val<2) {
					st.pollLast();
					TreeNode newNode = evaluteExp(left, right, curr);
					st.addLast(newNode);
				}
				else
				{
					st.addLast(left);
					st.addLast(right);
				}				
			}
						
			else //operand(True=1, False=0)
			{
				TreeNode n1 = st.pollLast();				
				
				if(st.isEmpty()) {
					result = (curr.val==1)?true:false;					
				}
				else {
					TreeNode n2 = st.pollLast();
					TreeNode op = st.pollLast();
					
					TreeNode newNode = evaluteExp(n1, n2, op);												
					st.addLast(newNode);
				}
			}			
		}
		
		return result;
	}
	
	public static boolean evaluateTree1_iterative2(TreeNode root) 
	{
		Deque<TreeNode> st = new LinkedList<>();
		Set<TreeNode> visitedNodesSet = new HashSet<>();
		TreeNode curr=null, left=null, right=null;
		boolean result=false;
		
		st.addLast(root);
		
		while(!st.isEmpty()) 
		{
			curr = st.peekLast();
			left = curr.left;
			right = curr.right;
			
			if(curr.val>1)           //operators(AND=3,OR=2)
			{
				if(left.val<2 && right.val<2) {
					st.pollLast();
					TreeNode newNode = evaluteExp(left, right, curr);
					st.addLast(newNode);
				}
				else
				{
					st.addLast(left);
					st.addLast(right);
				}				
			}
						
			else //operand(True=1, False=0)
			{
				TreeNode n1 = st.pollLast();				
				
				if(st.isEmpty()) {
					result = (curr.val==1)?true:false;					
				}
				else {
					TreeNode n2 = st.pollLast();
					TreeNode op = st.pollLast();
					
					TreeNode newNode = evaluteExp(n1, n2, op);												
					st.addLast(newNode);
				}
			}			
		}
		
		return result;
	}
	
	public static boolean evaluateTree2(TreeNode root) 
	{
		//operators(AND=3,OR=2)
		//operands(True=1, False=0)
		
		int val = root.val;
		TreeNode left = root.left;
		TreeNode right = root.right;
		boolean result=false;
		
		if( val==1 )      result = true;
		else if( val==0 ) result = false;
		else if( val==2 ) result = evaluateTree2(left) | evaluateTree2(right) ; 
		else if( val==3 ) result = evaluateTree2(left) & evaluateTree2(right) ;
											
		return result;
	}

	public static TreeNode searchBST(TreeNode root, int val) 
	{		
		TreeNode curr = root;				
		
		while(curr!=null && curr.val!=val) 
		{
			if(val<curr.val)  curr = curr.left;
			else              curr = curr.right;
			
		}
		
		return curr;        
    }
	
	public static int maxDepth(TreeNode root) 
	{
		int depth = 0;
		Queue <TreeNode> q = new LinkedList<>();
		TreeNode curr = null, left=null, right=null;
		
		if(root!=null)  q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			for(int i=0; i<size;i++) 
			{
				curr = q.poll();
                left = curr.left;
                right = curr.right;
				if(left!=null) q.add(left);
				if(right!=null) q.add(right);
			}
			depth++;
		}
		
		return depth;
	}

	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) 
	{
		TreeNode result=null;
		Queue <TreeNode> q1 = new LinkedList<>();
		Queue <TreeNode> q2 = new LinkedList<>();
		
		return result;
	}
	
	public static List<Double> averageOfLevels(TreeNode root) 
	{
		Queue <TreeNode> q = new LinkedList<>();
		TreeNode curr=null, left=null, right=null;
		List<Double> listAvg = new LinkedList<>();
		
		int levelNodecount =0;
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			levelNodecount = q.size();
			double sum=0;
			for(int i=0; i<levelNodecount; i++) 
			{
				curr = q.poll();
				sum += curr.val;
				left = curr.left;
				right = right.right;
				
				if(left!=null)  q.add(left);
				if(right!=null) q.add(right);
			}
			listAvg.add(sum/levelNodecount); 
		}
		return listAvg;
	         
	}

	public static boolean isUnivalTree(TreeNode root) 
    {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode curr=null, left=null, right=null ;

        q.add(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size;i++)
            {
                curr = q.poll();
                set.add(curr.val);
                left = curr.left;
                right = curr.right;
                if(left!=null) q.add(left);
                if(right!=null) q.add(right);
            }
        }

        return set.size()==1;
        
    }
	
	public static int countNodes(TreeNode root) 
	{ 
		//Karmasikligin O(n)'den kucuk olmasi isteniyor.
		//Yapilamadi(23.01.2026)
		int count = 0;
		int level=0;
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr=null, left=null, right=null;
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			curr  = q.poll();
			left  = curr.left;
			right = curr.right;
			
			if(left!=null || right!=null) 
				count += Math.pow(2,level);
			level++;
				
			for(int i=0; i<size-1; i++) 
			{
				
			}
			
		}
		
		
		return count;
        
    }
		
	public static boolean leafSimilar(TreeNode root1, TreeNode root2) 
	{
		
		List<TreeNode> root1InOrder= inOrderTraversal(root1);				
		List<TreeNode> root2InOrder= inOrderTraversal(root2);	
		
		List<TreeNode> leafNodes1= new LinkedList<>();
		List<TreeNode> leafNodes2= new LinkedList<>();
		
		TreeNode curr=null;
		
		for(int i=0; i<root1InOrder.size(); i++) {
			curr = root1InOrder.get(i);
			if(curr.left==null && curr.right==null)  leafNodes1.add(curr);
		}
		
		for(int i=0; i<root2InOrder.size(); i++) {
			curr = root2InOrder.get(i);
			if(curr.left==null && curr.right==null)  leafNodes2.add(curr);
		}
		
		boolean result = true;
		
		if(leafNodes1.size()!=leafNodes2.size()) result = false;
		else 
		{
			int size = leafNodes1.size();
			for(int i=0; i<size;i ++) 
			{
				if(leafNodes1.get(i).val!=leafNodes2.get(i).val) 
				{ 
					result = false; 
					break;
				}
			}
		}
		
		return result;
	
	}

	public static int findTilt(TreeNode root) 
	{
		Deque<TreeNode>       st  = new LinkedList<>();
		Map<TreeNode,Integer[]> map = new HashMap<>();             //Node, is childs processed, sum of child node vals, difference of child node vals 
		TreeNode curr=null, left=null, right=null;
		int globalTiltSum=0, diff=0;
		Integer leftVal=0, rightVal=0;
		
		if(root!=null) 
		{
			st.addLast(root);
			map.put(root, new Integer[]{0,null,null});
		}
		
		
		while(!st.isEmpty())               //in order traversal 
		{
			curr  = st.pollLast();
			left  = curr.left;
			right = curr.right;
			
			
			if(left==null && right==null)      //leaf 
			{
				//st.addLast(curr);
				map.put(curr, new Integer[] {1, 0 + curr.val+ 0,0-0});	
				continue;
			}   
			
			else if(map.getOrDefault(curr, new Integer[] {0,null,null})[0]==0) 
			{
			    //map.put(curr, new Integer[] {1,null, null});				
			    st.addLast(curr);
				if(right!=null) {
					st.addLast(right);				
					//map.put(right, new Integer[] {0,null,null});
				}								
				
				if(left!=null) {
					st.addLast(left);				
					//map.put(left, new Integer[] {0,null,null});
				}
				map.put(curr, new Integer[] {1,null, null});				
			}
			else 
			{
				
				Integer []arr = new Integer[3];
				arr[0] = 1;
				
				if(left==null) leftVal=0;
				else if(left!=null && map.get(left)[1]!=null) leftVal = map.get(left)[1];
				else leftVal = null;
				
				//leftVal  = (left==null || (left!=null  && map.get(left)[1]!=null))?map.getOrDefault(left, new Integer[] {0,null,null})[1]:null;
				
				//rightVal = (right!=null && map.get(right)[1]!=null)?0:0;
				
				if(right==null) rightVal=0;
				else if(right!=null && map.get(right)[1]!=null) rightVal = map.get(right)[1];
				else rightVal = null;
				
				if(leftVal==null || rightVal==null ) {
					
				}
				else {
					arr[1] = leftVal + curr.val + rightVal;
					arr[2] = Math.abs(rightVal - leftVal);	
					map.put(curr, arr);
					globalTiltSum += arr[2]; 
				}
				//curr.val= arr[2];								
			}
		}		
		
		return globalTiltSum;
	}

	public static int getMinimumDifference(TreeNode root) 
	{
		List<TreeNode> list = inOrderTraversal(root);
		
		int size = list.size();
		int min =  Integer.MAX_VALUE; 
		
		for(int i=0; i<size-1;i++) 
		{
			min = Math.min(min, list.get(i+1).val - list.get(i).val );
		}
		
		return min;
	}
		
	public static boolean hasPathSum(TreeNode root, int targetSum) 
	{
		if(root==null) return false;
		
		Map<TreeNode,Integer>  mapSums    = new HashMap<>();      //sum of values root to the node. 
		Map<TreeNode,TreeNode> mapParents = new HashMap<>();      //node, parentNode
		Set<TreeNode> processedNodes = new HashSet<>();           //is left and right nodes pushed to stack 
		Deque<TreeNode> st = new LinkedList<>();                  //Deque is faster than stack 		
		TreeNode curr, left, right, parent;		
		boolean result = false;
		
		st.addLast(root);
		mapParents.put(root, null);
		
		while(!st.isEmpty()) 
		{
			curr  = st.peekLast();
			left  = curr.left;
			right = curr.right; 
			parent = mapParents.get(curr);
			
			int val = curr.val;
			int parentSumVal = mapSums.getOrDefault(parent,0);
			int sumVal = val + parentSumVal;
			
			if(isLeaf(curr)) 
			{
				st.removeLast();
				if(sumVal==targetSum) { result=true; break;}
				processedNodes.add(curr);
				mapSums.put(curr, sumVal);
				continue;
			}
			if(!processedNodes.contains(curr)) 
			{
				if(right!=null) {st.addLast(right); mapParents.put(right,curr);}
				if(left!=null) {st.addLast(left); mapParents.put(left,curr);}
				processedNodes.add(curr);
				
				mapSums.put(curr, sumVal);
			}
			else 
			{
				st.removeLast();
			}						
		}			
		
		return result;
	}

	public static int findSecondMinimumValue(TreeNode root) 
    {        
        if(root.left==null)   return -1;                  
        
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode curr, left, right;
        
        int val1 = root.val;
        Integer val2 = null;
        
        q.add(root);
        
        while(!q.isEmpty()) 
        {
        	int size = q.size();            	        	
        	
        	for(int i=0; i<size; i++) 
        	{
        		curr = q.poll();
        		int currVal = curr.val;
        		
        		left = curr.left;
        		right = curr.right;
        		
        		if(left!=null)  q.add(left);
        		if(right!=null) q.add(right);
        		
        		if( currVal >val1 ) 
        		{
        			if(val2==null)        val2 = currVal;
        			else if(currVal<val2) val2 = currVal;
        		}
        	}
        }
                       
        return (val2==null)?-1:val2;        
    }
	
	public static TreeNode invertTree(TreeNode root) 
	{
		/*
		    problem : Change left and right nodes of each node			
			link    : https://leetcode.com/problems/invert-binary-tree/description/?envType=problem-list-v2&envId=binary-tree
			Date    : 04.02.2026
			Status  : Solved		 
		*/
		
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr, left, right, temp;
		
		if(root!=null)
			q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			for(int i=0;i<size;i++) 
			{
				curr  = q.poll();
				left  = curr.left;
				right = curr.right;
								
				if(isLeaf(curr)) continue;
				
				if(left!=null)   q.add(left);
				if(right!=null)  q.add(right);								
				
				temp=left;
				curr.left = right;
				curr.right = temp;							
			}
		}
		
		return root;		       
    }
	
	public static boolean isSymmetric(TreeNode root) 
	{		
		/*
		    Problem     : Symmetric Tree 
		              		Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).			
			link        : https://leetcode.com/problems/symmetric-tree/?envType=problem-list-v2&envId=binary-tree
			Constraints : The number of nodes in the tree is in the range [1, 1000].
			Date    	: 05.02.2026
			Status  	: Solved
		*/
		
		Deque<TreeNode> dqCurrLevel = new LinkedList<>();
		Deque<TreeNode> dqNextLevel = new LinkedList<>();		
		Set<TreeNode> set =new HashSet<>();
		
		TreeNode curr1,curr2;		
		boolean result=true;
		
		dqCurrLevel.addFirst(root.left);
		dqCurrLevel.addLast(root.right);

		while(!dqCurrLevel.isEmpty() && result==true) 
		{
			int size = dqCurrLevel.size();			
						
			if(size%2==1) { result=false; break;}
									
			for(int i=0; i<size/2; i++) 
			{				
				curr1 = dqCurrLevel.pollFirst();
				curr2 = dqCurrLevel.pollLast();			
				
				if(curr1==null && curr2==null)      
				{ 
					dqNextLevel.addFirst(null); dqNextLevel.addFirst(null);  
					dqNextLevel.addLast(null);  dqNextLevel.addLast(null); 
					set.add(null);             //child nodes are also null.
				}
				else if(curr1!=null && curr2==null) { 
					result=false;  
					break;  
				}
				else if(curr1==null && curr2!=null) { 
					result=false;  
					break;  
				}
				else if(curr1.val!=curr2.val)       { 
					result=false;  					
					break;  
				}
				else                                
				{ 
					dqNextLevel.addFirst(curr1.right); dqNextLevel.addFirst(curr1.left);  
					dqNextLevel.addLast(curr2.left);   dqNextLevel.addLast(curr2.right);  
					set.add(curr1.right);
					set.add(curr1.left);
					set.add(curr2.right);
					set.add(curr2.left);
				}
			}
			if(set.size()==1 && set.toArray()[0]==null) 
				break;    //exit from while loop. All child nodes are null.
			
			set.clear();						
			
			dqCurrLevel = new LinkedList<TreeNode>(dqNextLevel);
			dqNextLevel.clear();
		}
		
		return result;
    }
	
	//bu fonksiyonda bir problem var gibi(06.02.2026)
	public static List<Integer> treeToList(TreeNode root)
	{
		
		/*
		  06.02.2026
		  Bu fonksiyonda bir problem var.
		  Olusan hata : java.lang.OutOfMemoryError: Java heap space
		  while dongusunun 25.iterasyonunda  
		  		q1.size=33.554.432, list1.size=67.108.863, set.size=2   oluyor.
		  
		  Kullanilan parametre: 
		  Integer [][] arr = {
				{1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2},
				{1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2}
		};
		*/
		
		Queue<TreeNode> q1 = new LinkedList<>();		
		Set<TreeNode>  set = new HashSet<>();
		List<Integer> list1 = new LinkedList<>();	
		TreeNode curr, left, right;									
		boolean result = true;
		
		if(root!=null)   q1.add(root);
		
		//System.out.println("treeToList fonksiyonu icerisinde");
		int j=0;
		
		while(!q1.isEmpty() && result==true) 
		{
			int size = q1.size();
			
			for(int i=0; i<size; i++) 
			{
				curr  = q1.poll();				
				
				if(curr==null) 
				{ 
					q1.add(null);  q1.add(null);  set.add(null);  list1.add(null); 
				}
				else
				{
					list1.add(curr.val);
					left  = curr.left;
					right = curr.right;
					q1.add(left);  
					q1.add(right);
					set.add(left);
					set.add(right);
				}								
			}
			//System.out.println("treeToList fonksiyonu icerisinde j=" + j + ", q1.size="+size + ", list1.size=" + list1.size() + ", set.size=" + set.size());
			
			if(set.size()==1 && set.toArray()[0]==null) 
				result=false;
			set.clear();
			j++;
		}				
		
		return list1;		
	}
	
	public static boolean isSameList(List<Integer> list1, List<Integer> list2) 
	{
		boolean result = true;														
		
		if(list1.size()!=list2.size()) 
			result = false;
		else 
		{
			for(int i=0; i<list1.size();i++) 
			{
				Integer val1 = list1.get(i);
				Integer val2 = list2.get(i);
				if(val1==null && val2==null) continue;
				else if(val1==null && val2!=null) {
					result=false;  break; 
				}
				else if(val1!=null && val2==null) {
					result=false;  break; 
				}
				else if( val1.intValue() != val2.intValue()) 
				{ 
					result=false;  break; 
				}
			}
		}
		
		return result;		
    }
		
	public static boolean isSameTree(TreeNode p, TreeNode q) 
	{
		boolean result = true;											
		
		List<Integer> list1 = treeToList(p);
		List<Integer> list2 = treeToList(q);								
		
		if(list1.size()!=list2.size()) result = false;
		else 
		{
			for(int i=0; i<list1.size();i++) 
			{
				Integer val1 = list1.get(i);
				Integer val2 = list2.get(i);
				if(val1==null && val2==null) continue;
				else if(val1==null && val2!=null) {
					result=false;  break; 
				}
				else if(val1!=null && val2==null) {
					result=false;  break; 
				}
				else if( val1.intValue() != val2.intValue()) 
				{ 
					result=false;  break; 
				}
			}
		}
		
		return result;		
    }	
	
	public static boolean isSameTree2(TreeNode p, TreeNode q)     //recursive version
	{
		if( (p==null && q!=null) || (p!=null && q==null) ) 
			return false;
		if(p==null && q==null) return true;
		if(p.val==q.val && isLeaf(p) && isLeaf(q)) 
			return true;
		if(p.val!=q.val) 
			return false;		
		return p.val==q.val && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);  
	} 
	
	public static boolean isSubtree(TreeNode root, TreeNode subRoot) 
	{
		boolean result = false;
		
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr, left, right;
		
		System.out.println("TreeToList oncesi");
		//List<Integer> list1 = null;
		List<Integer> list2 = treeToList(subRoot);
		
		q.add(root);		
		int j=0;
		
		System.out.println("while dongusunue baslanacak!");
				
		while( !q.isEmpty() && result==false ) 
		{
			System.out.println("j=" + j);
			
			int size = q.size();
			for(int i=0; i<size; i++) 
			{
				System.out.println("i=" + i);
				curr  = q.poll();
				left  = curr.left;
				right = curr.right;
				
				List<Integer>list1 = treeToList(curr);
				
				if(left!=null)  q.add(left);
				if(right!=null) q.add(right);
				
				if(isSameList(list1, list2)) { 
					result=true;  break; 
				}
				
				long heapFreeSize = Runtime.getRuntime().freeMemory();
				System.out.println("i : " + i +", heapFreeSize : " + heapFreeSize/1024/1024 + " MB");   //in MB
			}
			long heapFreeSize = Runtime.getRuntime().freeMemory();
			System.out.println("j : " + (j++) +", heapFreeSize : " + heapFreeSize/1024/1024 + " MB");   //in MB
		}
		
		return result;		
    }
	
	public static boolean isSubtree2(TreeNode p, TreeNode q)   //recursive version 
	{
		Deque<TreeNode> deq = new LinkedList<>();
		TreeNode curr, left, right;
		boolean result = false;
		
		deq.addLast(p);
		
		while( !deq.isEmpty() ) 
		{
			curr  = deq.pollLast();
			left  = curr.left;
			right = curr.right;
			
			if(isSameTree2(curr, q)) {
				result = true;
				break;
			}
			
			if(right!=null) 
				deq.addLast(right);
			if(left!=null)  
				deq.addLast(left);			
		}
		
		return result;
	}
	
	public static int deepestLeavesSum(TreeNode root) 
	{				
		Queue<TreeNode> q = new LinkedList<>();		
		TreeNode curr, left, right;
		int levelSum=0 ;		
		
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			levelSum=0;
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				
				levelSum+=curr.val;
				
				left = curr.left;
				right = curr.right;
				
				if(left!=null)  q.add(left);
				if(right!=null) q.add(right);
			}						
		}

		return levelSum; 
    }

	public static int averageOfSubtree(TreeNode root) 
	{	
		/*
			Cozum tarihi : 10.02.2026
			Sonuc        : Basarili.
		*/
		Deque<TreeNode> st = new LinkedList<>();
		Map<TreeNode, Integer> mapSums = new HashMap<>();     //sum of currNode and subnodes val
		Map<TreeNode, Integer> mapCounts = new HashMap<>();   //count of subnodes + 1
		Set<TreeNode> processed = new HashSet<>();
		TreeNode curr, left, right;
		int result = 0;                                       //number of node whose value is same with all sub nodes and curr node  
				
		st.addLast(root);
		
		while(!st.isEmpty()) 
		{
			curr = st.peekLast();
			int val = curr.val;
			
			if(isLeaf(curr)) 
			{
				mapSums.put(curr, val);
				mapCounts.put(curr, 1);
				result++;
				st.pollLast();
				continue;
			}
			
			left  = curr.left;
			right = curr.right;
			
			if(!processed.contains(curr)) 
			{
				if(right!=null) st.addLast(right);
				if(left!=null) st.addLast(left);
				processed.add(curr);
			}
			
			else 
			{
				st.pollLast();
				int sum = val;
				int nodesCount = 1;
				
				if(left!=null)  { sum+=mapSums.get(left);   nodesCount+=mapCounts.get(left);}
				if(right!=null) { sum+=mapSums.get(right);  nodesCount+=mapCounts.get(right);}
				
				mapSums.put(curr, sum);
				mapCounts.put(curr, nodesCount);
				
				int avg = sum/nodesCount;
				if(avg==val) result++;			
			}			
		}
				
		return result;
	}

	public static TreeNode reverseOddLevels(TreeNode root) 
	{
		Deque<TreeNode> q = new LinkedList<>();
		List<TreeNode> list = new LinkedList<>();
		TreeNode curr, left, right;
		
		q.addLast(root);
		boolean isOddLevel = false;
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			list.clear();
			
			for(int i=0; i<size;i++) 
			{
				curr = q.poll();
				left = curr.left;
				right = curr.right;
				
				if(left!=null)  {list.add(left);    q.add(left);   }
				if(right!=null) {list.add(right);   q.add(right);  }
			}
			
			if(!isOddLevel && !list.isEmpty()) 
			{
				for(int i=0; i<size; i++) 
				{
					int temp = list.get(i).val;
					list.get(i).val = list.get(2*size-i-1).val;
					list.get(2*size-i-1).val = temp;
				}
			}
			
			isOddLevel=!isOddLevel;
		}
		
		
		return root;
    }
	
	public static TreeNode createBinaryTree(int[][] descriptions) 
	{
		//descriptions[i] = [parenti, childi, isLefti] indicates that parent_i is the parent of child_i in a binary tree of unique values. Furthermore,
		//descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
		Map<Integer, TreeNode> map = new HashMap<>();     //value, node
		Set<TreeNode> parents = new HashSet<>();
		Set<TreeNode> childs = new HashSet<>();
		
		TreeNode root=null, parent, child;
		
		for(int[] desc : descriptions) 
		{			
			if(desc[0]==4) {
				System.out.println("asdf");
			}
			if(map.containsKey(desc[0])) { parent = map.get(desc[0]); }
			else 
			{ 
				parent = new TreeNode(desc[0]);  
				map.put(desc[0], parent);
				//parents.add(parent);				
			}
			parents.add(parent);
			
			if(map.containsKey(desc[1])) { child = map.get(desc[1]); }
			else 
			{ 
				child = new TreeNode(desc[1]);    
				map.put(desc[1], child);
				//childs.add(child);
			}
			childs.add(child);
			
			if(desc[2]==1) parent.left = child;
			else           parent.right = child;		
		}
		
		for(TreeNode node : parents )    //parent child 
		{			
			if(!childs.contains(node)) 
			{ 
				root = node; 
				break;
			}
		}
		
		return root;
    }
	
	public static TreeNode lcaDeepestLeaves(TreeNode root) 
	{
		/*
		Tarih             : 11.02.2026
		Problem           : Lowest Common Ancestor of Deepest Leaves
		Problem Aciklama  : Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
		Link              : https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/?envType=problem-list-v2&envId=binary-tree
		Cozum Algoritmasý : BFS ile deepest node'lar ve her bir node'a ait parent node belirlenir.
		                    Deepest node'lar ortak parent bulunmaya calisilir. 
		*/
		
		Queue <TreeNode> q = new LinkedList<>();
		Map<TreeNode, TreeNode> map = new HashMap<>();   //node, parent
		List<TreeNode> deepestNodes=null;
		TreeNode curr, left, right, parent, result = null; 
		Set<TreeNode> parents = new HashSet<>();
		
		if(root!=null)   q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			deepestNodes = new LinkedList<>(q);
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				left  = curr.left;
				right = curr.right;
				if(left!=null)  {q.add(left);  map.put(left, curr);   }
				if(right!=null) {q.add(right); map.put(right, curr);  }
			}						
		}
		
		int size = deepestNodes.size();
		
		if(size==0)   return null;
		if(size == 1) 
			return (TreeNode) deepestNodes.toArray()[0];
		
		//for(int i=0; i<size; i++)
		boolean parentFound = false;
		
		while(!parentFound) 
		{
			parents.clear();
			for( TreeNode curr1: deepestNodes )
			{
				parent = map.get(curr1);
				parents.add(parent);		
			}
			if(parents.size()==1) 
			{ 
				result = (TreeNode) parents.toArray()[0]; 
				break;
			}
			else 
			{
				deepestNodes.clear();
				deepestNodes = new LinkedList<>(parents);
			}
		}
		
		
		return result;
	}

	public static TreeNode removeLeafNodes(TreeNode root, int target) 
	{
		/*
			Tarih             : 11.02.2026
			Problem           : Delete Leaves With a Given Value
			Problem Aciklama  : Given a binary tree root and an integer target, delete all the leaf nodes with value target.
			Link              : https://leetcode.com/problems/delete-leaves-with-a-given-value/description/?envType=problem-list-v2&envId=binary-tree
			Cozum Algoritmasi : BFS ile aï¿½aï¿½ ï¿½zerinde seviye bazlý ilerlenir. o anki node bir leaf node ise ve degeri de uyumlu ise parent node'un ilgili child nodu null yapilir.
			                    Deepest node'lar ortak parent bulunmaya calisilir. 
			Durum             : Yapildi.
		*/
	 		
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, TreeNode> map  = new HashMap<>();   //node, parent Node
		TreeNode curr, left, right, parent;
		boolean nodeDeleted = true;
		
		//q.add(root);				
		
		while(nodeDeleted) 
		{
			nodeDeleted = false;
		
			if(root!=null)	q.add(root);
			
			while(!q.isEmpty()) 
			{
				int size = q.size();
				for(int i=0; i<size; i++) 
				{
					curr = q.poll();				
					
					if(isLeaf(curr) && curr.val==target) 
					{
						parent = map.get(curr);
						
						if(parent==null) {   //root node
							curr=null;
							root = null;
						}
						else if(parent.left==curr)        
							parent.left  = null;
						else if(parent.right==curr)  
							parent.right = null;
						
						nodeDeleted = true;
					}
					else 
					{
						left  = curr.left;
						right = curr.right;
						
						if(left!=null)  { q.add(left);   map.put(left, curr);  }
						if(right!=null) { q.add(right);  map.put(right, curr); }
					}
				}
			}
		}
		
		return root;
				
    }

    public static int kthSmallest(TreeNode root, int k) 
    {
    	//int result=-1;
    	List<TreeNode> inOrderList = inOrderTraversal(root);
    	    	    	
    	return inOrderList.get(k-1).val;        
    }
	
    public static int goodNodes(TreeNode root) 
    {
    	/*
		Tarih             : 11.02.2026
		Problem           : Count Good Nodes in Binary Tree
		Problem Aciklama  : Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
						    Return the number of good nodes in the binary tree.
		Link              : https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/?envType=problem-list-v2&envId=binary-tree
		Cozum Algoritmasý :  
		Durum             : Yapildi.
	    */
    	Queue<TreeNode> q = new LinkedList<>();
    	Map<TreeNode, Integer> map = new HashMap<>();
    	Set<TreeNode> goodNodes = new HashSet<>();
    	TreeNode curr, left, right;
    	
    	q.add(root);
    	goodNodes.add(root);
    	map.put(root, root.val);    	
    	
    	while(!q.isEmpty()) 
    	{
    		curr  = q.poll();
    		int maxValOnPath = map.get(curr);
    		
    		left  = curr.left;
    		right = curr.right;
    		
    		if(left!=null) 
    		{
    			q.add(left);
    			if(left.val>=maxValOnPath)  
    				goodNodes.add(left);    	
    			map.put(left, Math.max(maxValOnPath, left.val));
    		}
    		if(right!=null) 
    		{
    			q.add(right);
    			if(right.val>=maxValOnPath)  
    				goodNodes.add(right);    	
    			map.put(right, Math.max(maxValOnPath, right.val));
    		}
    		
    	}
    	
    	return goodNodes.size();        
    }
    
    public static List<List<Integer>> levelOrder(TreeNode root) 
    {
    	/*
		Tarih             : 12.02.2026
		Problem           : Binary Tree Level Order Traversal
		Problem Aciklama  : Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).						    
		Link              : https://leetcode.com/problems/binary-tree-level-order-traversal/?envType=problem-list-v2&envId=binary-tree
		Cozum Algoritmasý : Kuyruk veri yapýsý kullanýlarak seviye bazlý dolasma(BFS) yapýlýr.
		Durum             : ï¿½ï¿½zï¿½ldï¿½.
	    */
    	
    	List<List<Integer>> result = new LinkedList<>();
    	Queue <TreeNode> q = new LinkedList<>();
    	TreeNode curr, left, right;
    	
    	if(root!=null)   q.add(root);
    	
    	while(!q.isEmpty()) 
    	{
    		int size = q.size();
    		List<Integer> levelItems = new LinkedList<>();
    		
    		for(int i=0; i<size; i++) 
    		{
    			curr  = q.poll();
    			int val = curr.val;
    			levelItems.add(val);
    			
    			left  = curr.left;
    			right = curr.right;
    			
    			if(left!=null)   q.add(left);
    			if(right!=null)  q.add(right);
    		}
    		result.add(levelItems);
    	}
    	
    	return result;
    	    	    	
    }
    
    public static int findBottomLeftValue(TreeNode root) 
    {    	
    	/*
		Tarih             : 12.02.2026
		Problem           : Find Bottom Left Tree Value
		Problem Aciklama  : Given the root of a binary tree, return the leftmost value in the last row of the tree.						    
		Link              : https://leetcode.com/problems/find-bottom-left-tree-value/description/?envType=problem-list-v2&envId=binary-tree
		cozum Algoritmasý : Kuyruk veri yapýsý kullanýlarak seviye bazlý dolasma(BFS) yapýlýr.
		Durum             : cozuldu.
	    */
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	TreeNode curr, left, right, leftmostNode=null;
    	    	
    	q.add(root);
    	
    	while(!q.isEmpty()) 
    	{
    		int size = q.size();
    		leftmostNode = q.peek();
    		
    		for(int i=0; i<size; i++) 
    		{
    			curr  = q.poll();
    			left  = curr.left;
    			right = curr.right;
    			
    			if(left!=null)   q.add(left);
    			if(right!=null)  q.add(right);
    		}
    	}
    	
    	return leftmostNode.val;
    }

    public static List<Integer> rightSideView(TreeNode root) 
    {
    	/*
		Tarih             : 12.02.2026
		Problem           : Binary Tree Right Side View
		Problem Aï¿½ï¿½klama  : Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.						    
		Link              : https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=problem-list-v2&envId=binary-tree
		ï¿½ï¿½zï¿½m Algoritmasï¿½ : Kuyruk veri yapï¿½sï¿½ kullanï¿½larak seviye bazlï¿½ dolaï¿½ma(BFS) yapï¿½lï¿½r. Her seviye iï¿½in kuyruï¿½un en son elemanï¿½ alï¿½nï¿½r.
		Durum             : ï¿½ï¿½zï¿½ldï¿½.
	    */
    	
    	Deque<TreeNode> q = new LinkedList<>();
    	List<Integer> resultList = new LinkedList<>();
    	TreeNode curr, left, right;
    	
    	if(root!=null) q.add(root);
    	
    	while(!q.isEmpty()) 
    	{
    		int size = q.size();
    		resultList.add(q.peekLast().val);
    		
    		for(int i=0; i<size; i++) 
    		{
    			curr  = q.pollFirst();
    			left  = curr.left;
    			right = curr.right;
    			
    			if(left!=null)   q.addLast(left);
    			if(right!=null)  q.addLast(right);
    		}
    	}
    	return resultList;
    	
    }
    
    public static List<List<Integer>> levelOrderBottom(TreeNode root) 
    {
    	Queue<TreeNode> q = new LinkedList<>();    	
    	List<List<Integer>> result = new LinkedList<>();
    	TreeNode curr, left, right;
    	
    	if(root!=null)   q.add(root);
    	
    	while(!q.isEmpty()) 
    	{
    		int size = q.size();
    		List<Integer> levelItems = new LinkedList<>();
    		
    		for(int i=0; i<size;i++) 
    		{
    			curr = q.poll();
    			levelItems.add(curr.val);
    			
    			left = curr.left;
    			right = curr.right;
    			
    			if(left!=null)  q.add(left);
    			if(right!=null) q.add(right);    			
    		}
    		result.add(0,levelItems);    		
    	}
    	
    	return result;
    }
    
    public static boolean isEvenOddTree(TreeNode root) 
    {
    	/*
		Tarih             : 12.02.2026
		Problem           : Even Odd Tree
		Problem Aï¿½ï¿½klama  : 						    
		Link              : https://leetcode.com/problems/even-odd-tree/description/?envType=problem-list-v2&envId=binary-tree
		cozum Algoritmasi : Kuyruk veri yapisi kullanilarak seviye bazli dolasma(BFS) yapilir. 
		Durum             : cozuldu.
	    */
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	TreeNode curr, left, right;    	
    	boolean result = true;
    	int level =0;
    		
    	if(root!=null)   q.add(root);
    	
    	while(!q.isEmpty() && result) 
    	{
    		int size = q.size();    		    		
    		long prevVal;
    		
    		if(level%2==0) prevVal = q.peek().val - 2;
    		else           prevVal = q.peek().val + 2;    			    		    		
    		
    		for(int i=0; i<size; i++) 
    		{
    			curr  = q.poll();
    			int currVal = curr.val;
    			
    			left  = curr.left;
    			right = curr.right;    
    			
    			if(left!=null)   q.add(left);
    			if(right!=null)  q.add(right);
    			    			
    			
    			if(level%2==0)    //For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
    			{
    				if( currVal%2==0 || currVal<=prevVal ) { result=false; break;}
    			}
    			
    			else if(level%2==1)    //For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
    			{
    				if( currVal%2==1 || currVal>=prevVal ) { result=false; break;}
    			}
    			
    			prevVal = currVal;
    			
    		}
    		
    		level++;
    	}
    	
    	return result;
        
    }

	public static List<Integer> largestValues(TreeNode root) 
	{
        Queue <TreeNode> q = new LinkedList<>();        
        TreeNode curr, left, right;
        List<Integer> resultList = new LinkedList<>();
        int max;
        
        if(root!=null) q.add(root);
        
        while(!q.isEmpty()) 
        {
        	int size= q.size();
        	max = q.peek().val;
        	
        	for(int i=0; i<size; i++) 
        	{
        		curr  = q.poll();
        		left  = curr.left;
        		right = curr.right;
        		
        		max = Math.max(max, curr.val);
        		
        		if(left!=null)   q.add(left);
        		if(right!=null)  q.add(right);
        	}
        	resultList.add(max);
        }
        
        return resultList;
        
    }

	public static long kthLargestLevelSum(TreeNode root, int k) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr, left, right;
		List<Long> sumVals = new ArrayList<>();
				
		q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			long sum = 0;
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				left = curr.left;
				right = curr.right;
				
				if(left!=null)   q.add(left);
				if(right!=null)  q.add(right);
				
				sum += curr.val;
			}
			sumVals.add(sum);			
		}
		
		long[] result = sumVals.stream().mapToLong(l -> l).toArray();
		
		Arrays.sort(result);
		int size = result.length;
		
		return (k<=result.length)?result[size-1-(k-1)]:-1;
        
    }	

	public static TreeNode2 connect(TreeNode2 root) 
	{
		/*
			13.02.2026
			Bu fonksiyonun orjinalinde "TreeNode2" yerine "Node" bulunuyor.
			Ancak bu problem bir Tree sorusu oldugu icin bu sekilde guncellendi.
		*/
		Queue<TreeNode2> q = new LinkedList<>();
		TreeNode2 curr, left, right;
		
		if(root!=null)   q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				if(i+1<size) curr.next = q.peek();
				
				left = curr.left;
				right = curr.right;
				
				if(left!=null)  q.add(left);
				if(right!=null) q.add(right);
			}
		}
		
		return root;
	}	
	
	public static String smallestFromLeaf(TreeNode root) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, String> mapPath   = new HashMap<>();      //node, root to node path
		Map<TreeNode, TreeNode> mapParent = new HashMap<>();    //node, parent node
		TreeNode curr, left, right, parent;
		TreeNode dummyParent=new TreeNode(-1);   //for eliminating extra checks
		
		String minPath = null;		
		
		if(root!=null) {
			q.add(root);
			mapParent.put(root, dummyParent);
		}		
		
		mapPath.put(dummyParent, "");
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				int val = curr.val;
				String str = (char) ('a'+val) + "";
				
				parent = mapParent.get(curr);
				String path = str + mapPath.get(parent);
				mapPath.put(curr, path);
								
				if( isLeaf(curr) ) {
					
					if(minPath==null) 
						minPath = path;
					else if( path.compareTo(minPath) < 0 ) 				
						minPath = path;
				}
				
				left  = curr.left;
				right = curr.right;
				if(left!=null)  { q.add(left);   mapParent.put(left, curr);  }
				if(right!=null) { q.add(right);  mapParent.put(right, curr); }
			}			
		}
		
		return minPath;							
	}
	
	public static int widthOfBinaryTree(TreeNode root) 
	{
		/*
		Critical point is choosing type of variable for node index.
		Node index is related with depth:
		
			Level Index	node count
			0	  1 node
			1	  2 nodes		
			n	  2^n nodes
			
		Some test cases have a depth bigger than 64.

		Example following test case's depth is 68. At level 68 first non-null node's index is 442721857769029238783, and last non-null node's index is 442721857769029238784.

		When depth is bigger than 32, int type is not enough and when bigger than 64 long type is not enough.
		So we must use BigInteger type for Node index.
		
		Test case with height of 68:  {0,0,0,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null}		
		*/
		
		Queue<TreeNode> q = new LinkedList<>();		
		Map<TreeNode, String>  mapNodePath = new HashMap<>();   //node, path from root to node
		TreeNode curr, left, right;		
		int maxWidth=0;		
		int level = 0;
		
		if(root!=null) q.add(root);
				
		mapNodePath.put(root,"1");
		
		while( !q.isEmpty() ) 
		{
			int size = q.size();
			int width =0;
			//long startIdx=Integer.MAX_VALUE;
			//long endIdx = 0;
			BigInteger startIdx = null;
			BigInteger endIdx = BigInteger.ZERO;			
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();																				
				left  = curr.left;
				right = curr.right;
								
				String currPathBinary = mapNodePath.get(curr);
				BigInteger currNodeIdx = new BigInteger(currPathBinary, 2);	
				
				if(left!=null)   
				{ 
					q.add(left);    
					mapNodePath.put(left, currPathBinary+"0");   
				}
				if(right!=null)  
				{ 
					q.add(right);     
					mapNodePath.put(right, currPathBinary+"1");  
				}
								
				if(startIdx==null)
					startIdx = currNodeIdx;			
				
				//if(endIdx<currNodeIdx)
				if(endIdx.compareTo(currNodeIdx)<0 )
				{   
					endIdx = currNodeIdx;
				}								
			}
			
			//width = (int) (endIdx-startIdx + 1);
			width = endIdx.subtract(startIdx).add(BigInteger.ONE).intValue();
			maxWidth = Math.max(maxWidth, width);		
			
			System.out.println("level : " + level++ + ", q.size:" + size + ", width:" + width + ", startIdx:" + startIdx + ", endIdx:" + endIdx );			
		}
        
		return maxWidth;
    }
	
	public static int sumNumbers(TreeNode root) 
	{
		//date : 14.02.2026
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, Integer> mapPath = new HashMap<>();
		TreeNode curr, left, right;
		int result=0;
		
		if(root!=null)  {
			q.add(root);		
			mapPath.put(root, root.val);
		}
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				
				int sumVal = mapPath.get(curr); 
				
				if(isLeaf(curr))  { result += sumVal;  System.out.println(sumVal);} 
				else 
				{															
					left  = curr.left;
					right = curr.right;
					
					if(left!=null)  { q.add(left);   mapPath.put(left,  sumVal*10+left.val); }
					if(right!=null) { q.add(right);  mapPath.put(right, sumVal*10+right.val); }
				}
			}
		}
		return result;
    }
	
	public static List<List<Integer>>  pathSum(TreeNode root,  int targetSum) 
	{
		//date : 14.02.2026
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, List<Integer>> mapPath = new HashMap<>();
		Map<TreeNode, Integer>       mapPathSum = new HashMap<>();
		TreeNode curr, left, right;
		
		List<List<Integer>>  ans= new LinkedList<>();
		List<Integer> pathItems = new ArrayList<>();
		
		if(root!=null)  
		{
			q.add(root);	
			pathItems.add(root.val);
			mapPath.put(root, pathItems);
			mapPathSum.put(root, root.val);
		}
				
		while(!q.isEmpty()) 
		{
			int size = q.size();
			//pathItems = new ArrayList<>();
			
			for(int i=0; i<size; i++) 
			{
				curr = q.poll();
				
				pathItems = mapPath.get(curr);
				int pathSum = mapPathSum.get(curr);
				
				if(isLeaf(curr) && pathSum==targetSum)  
				{ 
					ans.add(pathItems);
					System.out.println(pathItems);
				} 
				else 
				{															
					left  = curr.left;
					right = curr.right;
					
					if(left!=null)  { 
						int val = left.val; 
						q.add(left);    
						List<Integer> pathItemsLeft = new ArrayList<>(pathItems);
						pathItemsLeft.add(val);						
						mapPath.put(left, pathItemsLeft);
						mapPathSum.put(left, pathSum + val); 						
					}
					if(right!=null) { 
						int val = right.val; 
						q.add(right);   
						List<Integer> pathItemsRight = new ArrayList<>(pathItems);
						pathItemsRight.add(val);						
						mapPath.put(right, pathItemsRight);
						mapPathSum.put(right, pathSum + val); 
					}
				}
			}
		}
		
		return ans;
    }
	
	public static TreeNode createBSTfromInOrderTreeNodes(List<TreeNode> root) 
	{
		//oluþturulacak (15.02.2026)
		return null;
	}
	
	public static TreeNode deleteNode(TreeNode root, int key) 
	{
		//Eksik, henüz tamamlanmadý.
		//15.02.2026
		TreeNode curr=root, left, right, parent;
		TreeNode dummyRoot = new TreeNode(-1);
		boolean isFound = false;
		
		dummyRoot.left = root;
		parent = dummyRoot;
		
		while(!isFound && curr!=null) 
		{
			int val = curr.val;
			left  = curr.left;
			right = curr.right;
			
			if(val == key) 
			{
				List<TreeNode> inOrderList = inOrderTraversal(curr);
				//bst from sortedArray
				
				if(curr == parent.left) 
				{
					if(right!=null)       parent.left = right; 
					else if(left!=null)   parent.left = left;
				}
				else 
				{
					if(right!=null)       parent.right = right; 
					else if(left!=null)   parent.right = left;
				}
				
				curr.left = null;
				curr.right= null;
				
				isFound=true;
				break;
				
			}
			else if(val>key)  { curr=curr.left;   parent = curr;  }
			else if(val<key)  { curr=curr.right;  parent = curr;  }
		}		
		
		return dummyRoot.left;
	}

	public static boolean isCompleteTree(TreeNode root) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr, left, right;
		boolean isComplete = true;		
		int currLevelNotNullNodeCount=0;
		int currLevelMaxNodeCount = 1;		
		int level = 0;
		boolean isNextLevelExists = true;
			
		if(root!=null) {
			q.add(root);
			currLevelNotNullNodeCount=1;
		}
		
		while(!q.isEmpty() && isComplete && isNextLevelExists) 
		{
			int size = q.size();			
			TreeNode prevNode=new TreeNode(-1);
			currLevelNotNullNodeCount=0;
			isNextLevelExists=false;
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				if(curr!=null) 
				{
					left  = curr.left;
					right = curr.right;
					currLevelNotNullNodeCount++;
				}
				else 
				{ 
					left=null;
					right=null;
				}

				q.add(left); 
				q.add(right);
				
				if(left!=null || right!=null) 
					isNextLevelExists=true;
				
				if( prevNode==null && curr!=null) 
				{
					isComplete=false; 
					break;
				} 
				prevNode = curr;										
			}
			
			if(isNextLevelExists && currLevelNotNullNodeCount<currLevelMaxNodeCount) 
			{
				isComplete=false; 
				break;
			} 
				
			System.out.println("level : " + level++);

			currLevelMaxNodeCount*=2;
		}
		
		return isComplete;
	}
	
	public static TreeNode addOneRow(TreeNode root, int val, int depth) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode curr, left, right;
		int level=0;         //At definition of problem root is at level 1						
		
		TreeNode dummyRoot = new TreeNode(val);      //To eliminate "dept==1" control
		dummyRoot.left = root;            
				
		q.add(dummyRoot);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				left  = curr.left;
				right = curr.right;
				
				if(left!=null)    q.add(left);
				if(right!=null)   q.add(right);
				
				if(level+1==depth)   //   || (level==depth && isRowAdded==false)) 
				{
					TreeNode node1 = new TreeNode(val);
					node1.left = left;
					curr.left = node1;

					TreeNode node2 = new TreeNode(val);
					node2.right = right;																
					curr.right = node2;
					
					//isRowAdded=true;
				}
			}
			
			level++;
		}
        
		return dummyRoot.left;
    }
	
	public static int sumEvenGrandparent(TreeNode root) 
	{
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, TreeNode> map = new HashMap<>();   //node, parent node
		TreeNode curr, left, right;
		int level =0, sum=0;
		
		if(root!=null)   q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				left  = curr.left;
				right = curr.right;
				
				if(left!=null)  {  q.add(left);   map.put(left, curr);  }
				if(right!=null) {  q.add(right);  map.put(right, curr); }
				
				if(level>=2) 
				{
					TreeNode parent = map.get(curr);
					TreeNode grandParent = map.get(parent);
					if(grandParent.val%2==0) sum+=curr.val;
				}
				
			}
			level++;
		}
		return sum;
		
	}
		
	public static TreeNode bstToGst(TreeNode root) 
	{
		List<TreeNode> inOrderList = inOrderTraversal(root);
		
		Map<TreeNode, Integer> map = new HashMap<>();   //node, sum value 
		int size= inOrderList.size();
		int cumSum=0;
		
		for(int i=size-1; i>=0; i--) 
		{
			TreeNode curr = inOrderList.get(i);
			cumSum += curr.val;
			map.put(curr, cumSum);
		}
		
		for(int i=size-1; i>=0; i--) 
		{
			TreeNode curr = inOrderList.get(i);
			curr.val = map.get(curr);			
		}
		
		return root;
		
	}
		
	public static TreeNode replaceValueInTree(TreeNode root) 
	{
		//Yapýldý(17.02.2026)
		Queue<TreeNode> q = new LinkedList<>();
		Map<TreeNode, TreeNode> map = new HashMap<>();    //node, brother of the key node
		List<TreeNode> levelNodes = new ArrayList<>();
		TreeNode curr, left, right;		
		int level =0;
		
		if(root!=null)  q.add(root);
		
		while(!q.isEmpty()) 
		{
			int size = q.size();
			int levelSum=0;			
			
			for(int i=0; i<size; i++) 
			{
				curr  = q.poll();
				levelNodes.add(curr);
				
				levelSum+=curr.val;
				
				left  = curr.left;
				right = curr.right;
				
				if(left!=null && right!=null) { map.put(left, right);  map.put(right,left); }
				
				if(left!=null)   q.add(left);
				if(right!=null)  q.add(right);								
			}
			
			System.out.println("level : " + level + ", levelSum:" + levelSum);
													
			for(int i=0; i<size; i++) 
			{					
				curr = levelNodes.get(i);
				
				TreeNode bro =  map.get(curr);
				
				if(bro==null) 
				{
					curr.val = levelSum - curr.val; 
				}
				else 
				{
					int currBroSum = curr.val + bro.val;
					curr.val = levelSum - currBroSum;
					bro.val  = levelSum - currBroSum;
					i++;                                        //to eliminate second process, which takes us to wrong result ,for two brothers. 
				}
			}
			
			
			levelNodes.clear();
			level++;
		}
		
		return root;
		
	}
	
	public static void testCases() 
	{
		/*
		Integer [][] arr = { {5,4,9,1,10,null,7}, {3,1,2},{1},{1,2}};		
		int index = 3;
		TreeNode root = arrayToTree(arr[index]);		
		TreeNode result = replaceValueInTree(root);		
		System.out.println(treeToList(result));	
		*/
		
		/*
		Integer [][] arr = { {6,7,8,2,7,1,3,9,null,1,4,null,null,null,5}, {1}};		
		int index = 1;
		TreeNode root = arrayToTree(arr[index]);		
		int result = sumEvenGrandparent(root);		
		System.out.println(result);	
		*/
		
		/*
		Integer [][] arr = { {6,7,8,2,7,1,3,9,null,1,4,null,null,null,5}, {1}};		
		int index = 1;
		TreeNode root = arrayToTree(arr[index]);		
		int result = sumEvenGrandparent(root);		
		System.out.println(result);	
		*/
		
		/*
		Integer [][] arr = { {4,2,6,3,1,5}, {4,2,null,3,1}, {4,2,6,3,1,5}, {1} };		
		int index = 2;
		int[] val = {1,1,1};
		int[] depth = {2,3,1};		
		TreeNode root = arrayToTree(arr[index]);		
		TreeNode result = addOneRow(root, val[index], depth[index]);		
		System.out.println(treeToList(result));
		*/
		
		/*
		Integer [][] arr = { {1,2,3,4,5,6}, {1,2,3,4,5,6,7,8,null,10}, {1,null,2}, {2} ,{1,2,3,4,5,6,7,8,9,10,11,12,13,null,null,15} };		
		int index = 1;
		TreeNode root = arrayToTree(arr[index]);		
		boolean result = isCompleteTree(root);						
		System.out.println("sonuc : " + result);
		*/
		
		/*
		Integer [][] arr = { {5,4,8,11,null,13,4,7,2,null,null,5,1}, {1,2,3}, {1,2} };
		int [] targetSum = {22, 5 , 0} ;
		int index = 0;
		TreeNode root = arrayToTree(arr[index]);		
		List<List<Integer>> result = pathSum(root, targetSum[index] );						
		System.out.println("sonuc : " + result);
		*/
		
		/*
		Integer [][] arr = { {1,2,3}, {4,9,0,5,1}, {5} };		
		TreeNode root = arrayToTree(arr[2]);
		int result = sumNumbers(root);						
		System.out.println("sonuc : " + result);
		*/
		
		/*
		Integer [][] arr = { {1,3,2,5,3,null,9},
							 {1,3,2,5,null,null,9,6,null,7},
							 {1,3,2,5},
							 //{0,  0,0,  null,0,0,null,    null,0,0,null,  null,0,0,null,      null,0,0,null,  null,0,0,null,  null,0,0,null,  null,0,0,null  },
				             {-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,3,53,-81,33,4,null,-51,-44,-60,11,null,null,null,null,78,null,-35,-64,26,-81,-31,27,60,74,null,null,8,-38,47,12,-24,null,-59,-49,-11,-51,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,-4,null,null,null,null,null,null,null,3,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,-38,-31,-85,-31,-96,null,null,-18,67,34,72,null,-17,-77,null,56,-65,-88,-53,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,72,-27,null,null,null,null,null,null,-67,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,-93,null,null,null,98},
							 {0,0,0,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null}};		
		TreeNode root = arrayToTree(arr[4]);
		int result = widthOfBinaryTree(root);						
		System.out.println("sonuc : " + result);
		*/
		
		/*
		Integer [][] arr = { {0,1,2,3,4,3,4},{25,1,3,1,3,0,2},{2,2,1,null,1,0,null,0},{5,25}};		
		TreeNode root = arrayToTree(arr[3]);		
		String result = smallestFromLeaf(root);						
		System.out.println("sonuc : " + result);	
		*/
		
		/*
		Integer [][] arr = { {1,10,4,3,null,7,9,12,8,6,null,null,2},{5,4,2,3,3,7},{5,9,1,3,5,7},{2,12,8,5,9,null,null,18,16}};		
		TreeNode root = arrayToTree(arr[3]);		
		boolean result = isEvenOddTree(root);						
		System.out.println(result);
		*/
		
		/*
		Integer [][] arr = { {5,8,9,2,1,3,7,4,6},{1,2,null,3}};
		int [] k = {2,1};
		TreeNode root = arrayToTree(arr[0]);		
		long result = kthLargestLevelSum(root,k[0]);						
		System.out.println(result);
		*/
		
		/*
		Integer [][] arr = { {3,1,4,3,null,1,5},{3,3,null,4,2},{1}};		
		TreeNode root = arrayToTree(arr[0]);
		List<TreeNode> inOrderList = inOrderTraversal(root);
		*/
		
		/*
		Integer [][] arr = { {3,1,4,null,2},{5,3,6,2,4,null,null,1},{2},{}};
		int []k = {1,3};
		TreeNode root = arrayToTree(arr[0]);
		List<TreeNode> inOrderList = inOrderTraversal(root);
		int result = kthSmallest(root, k[1]);						
		System.out.println(result);
		*/
		
		/*
		Integer [][] arr = { {1,2,3,2,null,2,4},{1,3,3,3,2},{1,2,null,2,null,2},{1},{2},{}};
		int []target = {2,3,2,1};
		TreeNode root = arrayToTree(arr[5]);
		TreeNode result = removeLeafNodes(root,target[3]);
		*/
		
		/*
		Integer [][] arr = { {3,5,1,6,2,0,8,null,null,7,4},{1},{0,1,3,null,2} };
		TreeNode root = arrayToTree(arr[2]);
		TreeNode result = lcaDeepestLeaves(root);		
		System.out.println(treeToList(result));
		*/
		
		/*
		int [][] arr1 = { {2,3,5,8,13,21,34},{7,13,11},{0,1,2,0,0,0,0,1,1,1,1,2,2,2,2},{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} };
		int [][] arr2 = {{1,2,1},{2,3,0},{3,4,1} };
		int [][] arr3 = {{85,74,0},{38,82,0},{39,70,0},{82,85,0},{74,13,0},{13,39,0}};
		//TreeNode root = arrayToTree(arr[3]);
		TreeNode result = createBinaryTree(arr3);		
		System.out.println(treeToList(result));
		*/
		
		/*
		Integer [][] arr = { {2,3,5,8,13,21,34},{7,13,11},{0,1,2,0,0,0,0,1,1,1,1,2,2,2,2},{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} };
		TreeNode root = arrayToTree(arr[3]);
		TreeNode result = reverseOddLevels(root);		
		System.out.println(treeToList(result));
		*/
		
		/*
		Integer [][] arr = { {-1,null,-1}, {-1,-1,-1,-1,-1} };
		Integer[][] target = {{1,2},{1,3,5}};
		TreeNode root = arrayToTree(arr[1]);
		
		FindElements obj = new FindElements(root);
		boolean result = obj.find(target[1][0]);		
		
		System.out.println(result);
		*/
		
		/*
		Integer [][] arr = { {1,2,3,4,5,null,6,7,null,null,null,null,8},{1,null,3},{1,2,3,4},{},{1,1},{1},{1,2,3}, {1,2} };
		TreeNode root = arrayToTree(arr[5]);
		int result = deepestLeavesSum(root);		
		System.out.println(result);
		*/
		
		/*
		Integer [][] arr = {{1,2,3},{1,2,3},{},
				{5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929},
				{5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929}
		};
		TreeNode root1 = arrayToTree(arr[3]);
		TreeNode root2 = arrayToTree(arr[4]);
		boolean result = isSameTree(root1,root2);
		*/
		
		/*
		
		Integer [][] arr = {{1,2,2,3,4,4,3},{1,2,2,null,3,null,3},{1}};
		TreeNode root = arrayToTree(arr[2]);
		boolean result = isSymmetric(root);
		*/
		
		/*
		Integer [][] arr = {{5,4,8,11,null,13,4,7,3,null,null,null,1}, {1,2,3},{},{1,-2,-3,1,3,-2,null,-1}};
		TreeNode root = arrayToTree(arr[3]);
		boolean result = hasPathSum(root,2);
		*/
		
		/*
		Integer [][] arr = {{1,2,3}, {4,2,9,3,5,null,7}};
		TreeNode root = arrayToTree(arr[1]);
		int a = findTilt(root);		
		System.out.println(a);
		*/
		
		/*
 		Integer [][] arr = {   //Tree1, Tree2
						{3,5,1,6,2,9,8,null,null,7,4},{3,5,1,6,7,4,2,null,null,null,null,null,null,9,8} ,
						{1,2,3}, {1,3,2}
				   };
		TreeNode root1= arrayToTree(arr[0]);
		List<TreeNode> root1InOrder= inOrderTraversal(root1);
		System.out.println("");
		
		TreeNode root2= arrayToTree(arr[1]);
		List<TreeNode> root2InOrder= inOrderTraversal(root2);
		System.out.println("");
		System.out.println( leafSimilar(root1,root2) );
		*/
		
		/*
	  	Integer [][] arr = {{1,2,3}, {4,2,9,3,5,null,7},{4,2,6,1,3}};
		TreeNode root = arrayToTree(arr[2]);
		List<TreeNode> list = preOrderTraversal(root);
		*/
		
		/*
		Integer [][] arr = {{1,1,1,1,1,null,1}};
		TreeNode root = arrayToTree(arr[0]);
		boolean result1= isUnivalTree(root);
		System.out.println(result1);
		*/
		
		/*
		Integer [][] arr = {{10,5,15,3,7,null,18}};    //seviye seviye, once 1.seviyedeki node'lar sonra 2.seviyedekiler ...		
		TreeNode root = arrayToTree(arr[0]);		
		int result= rangeSumBST(root,7,15);
		*/
		
		/*
		//subtreeWithAllDeepest()
		Integer [][] arr = {{3,5,1,6,2,0,8,null,null,7,4,null,null,null,null},{1},{0,1,3,null,2},{3,5,1,6,2,0,8,null,null,7,4,null,9,10,null},{1}};    //seviye seviye, once 1.seviyedeki node'lar sonra 2.seviyedikiler ...		
		TreeNode root = arrayToTree(arr[2]);		
		TreeNode subTree= subtreeWithAllDeepest(root);
		*/		
	}
	
	public static void main(String [] args) 
	{
		/*
		//Integer [] arr = {1,2,3,4,5,6,null};

		//operators(AND=3,OR=2), operands(True=1, False=0)
		//                 {{OR,True,And,null,null,False,True}, {True} }
		Integer [][] arr = {{3,1,3,null,null,0,1},              
							{1} ,
							{3,3,2,0,0,3,2,null,null,null,null,1,3,1,1,null,null,2,1,null,null,null,null,1,1,null,null,null,null,null,null}
						   };    //seviye seviye, once 1.seviyedeki node'lar sonra 2.seviyedekiler ...
		
		TreeNode root = arrayToTree(arr[2]);
		
		boolean result1= false, result2= false;
		result1 = evaluateTree1_iterative(root);
		result2= evaluateTree2(root);
				
		//System.out.println(result1);
		System.out.println(result2);
		*/		
	
		//[5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929]

		//q:
	    //[5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929]							
		
		/*
		Bu fonksiyon hatali.
		Integer [][] arr = {{3,4,5,1,2},{4,1,2},{},
				{1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2},
				{1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2}
		};
		TreeNode root1 = arrayToTree(arr[3]);
		TreeNode root2 = arrayToTree(arr[4]);
		boolean result = isSubtree(root1,root2);
	    */	 				
		/*
		Integer [][] arr = {{1,2,3},{1,2,3,4},{},{1,1},{1},{1,2,3}, {1,2}, 
				{5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929},
				{5,-685,2970,-755,-462,2770,3620,null,-714,-649,434,373,2913,3337,4201,null,null,null,-497,-400,-95,313,1620,2838,null,3228,3574,3874,4262,-589,-484,null,-225,-183,null,240,null,1357,2342,null,null,2988,3262,3434,null,3658,3885,null,null,-622,null,null,null,-381,null,null,null,150,null,1237,1538,2235,2593,null,3072,null,null,3349,3490,null,3760,null,4187,null,null,null,-325,74,null,701,1246,1439,1583,2136,2249,2553,2661,3053,3174,null,3384,3465,null,3686,3855,3971,null,null,null,null,null,527,913,null,1283,null,null,null,null,1945,2219,null,null,2370,null,null,2694,3007,null,3109,null,null,null,null,null,null,null,null,null,null,4058,519,624,712,972,null,null,1736,1954,null,null,null,2467,2692,null,null,null,null,3115,null,4100,-203,null,null,null,null,725,null,1227,1670,1745,1952,2030,2427,null,null,null,null,null,null,null,null,null,null,817,1056,null,null,null,null,1852,null,null,null,2098,null,null,null,null,null,1133,1834,1929}
		};
		TreeNode root1 = arrayToTree(arr[5]);
		TreeNode root2 = arrayToTree(arr[6]);
		boolean result = isSubtree2(root1,root2);
		*/
		//System.out.println(result);								
				
		
		Integer [][] arr = { {5,4,9,1,10,null,7}, {3,1,2},{1},{1,2}};		
		int index = 3;
		TreeNode root = arrayToTree(arr[index]);		
		TreeNode result = replaceValueInTree(root);		
		System.out.println(treeToList(result));		
		
		

	}
}
