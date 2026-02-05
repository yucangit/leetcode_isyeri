package pack1;

import java.util.Queue;
import java.util.Deque;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
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


public class LeetCodeTree {
	
	public static TreeNode arrayToTree(Integer []arr) 
	{	
		int size = arr.length;
		if(size==0)  return null;
		
		Queue <TreeNode> q = new LinkedList<TreeNode>();
		
		TreeNode rootNode = new TreeNode(arr[0]);
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
	
	public static List<TreeNode> inOrderTraversal(TreeNode root) 
	{
		if(root==null) return null;
				
		Deque<TreeNode> st = new LinkedList<>();				
		TreeNode curr=null, left=null, right=null;
		List <TreeNode> listInOrder = new LinkedList<>();
		
		Map<TreeNode, Integer> map = new HashMap<>();
		
		st.addLast(root);
		map.put(root,0);     //left and right child nodes not visited
		
		while(!st.isEmpty()) 
		{
			curr = st.pollLast();
			if(map.getOrDefault(curr, 0)==1)  {
				listInOrder.add(curr);
				System.out.print(curr.val + ",");
				continue;
			}
			
			left= curr.left;
			right= curr.right;
			
			if(right!=null)  st.addLast(right);
			st.addLast(curr);
			if(left!=null)  st.addLast(left);
			map.put(curr, 1);						
		}
		
		return listInOrder;
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
		//Durum        : Çözüldü(16.01.2026).
	
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
		//Karmaþýklýðýn O(n)'den küçük olmasý isteniyor.
		//Yapýlamadý(23.01.2026)
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
	
	public static boolean isLeaf(TreeNode node) 
	{
		return node.left==null && node.right==null;
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
	
	
	
	public static void testCases() 
	{
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
		Integer [][] arr = {{10,5,15,3,7,null,18}};    //seviye seviye, önce 1.seviyedeki node'lar sonra 2.seviyedekiler ...		
		TreeNode root = arrayToTree(arr[0]);		
		int result= rangeSumBST(root,7,15);
		*/
		
		/*
		//subtreeWithAllDeepest()
		Integer [][] arr = {{3,5,1,6,2,0,8,null,null,7,4,null,null,null,null},{1},{0,1,3,null,2},{3,5,1,6,2,0,8,null,null,7,4,null,9,10,null},{1}};    //seviye seviye, önce 1.seviyedeki node'lar sonra 2.seviyediler ...		
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
						   };    //seviye seviye, önce 1.seviyedeki node'lar sonra 2.seviyedekiler ...
		
		TreeNode root = arrayToTree(arr[2]);
		
		boolean result1= false, result2= false;
		result1 = evaluateTree1_iterative(root);
		result2= evaluateTree2(root);
		
		
		//System.out.println(result1);
		System.out.println(result2);
		*/
		

		
		Integer [][] arr = {{1,2,2,3,4,4,3},{1,2,2,null,3,null,3},{1}};
		TreeNode root = arrayToTree(arr[2]);
		boolean result = isSymmetric(root);
		
		
		
		
		System.out.println(result);												
		
				
	}
}
