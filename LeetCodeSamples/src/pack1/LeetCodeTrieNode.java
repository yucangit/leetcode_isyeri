package pack1;


class TrieNode1
{
    TrieNode1 []links;
    int R = 26;
    boolean isEnd;

    public TrieNode1()
    {
        links = new TrieNode1[R];
        isEnd = false;
    }
    public boolean containsKey(char ch)
    {
        return links[ch-'a']!=null;
    }
    public TrieNode1 get(char ch)
    {
        return links[ch-'a'];
    }
    public void put(char ch, TrieNode1 node)
    {
        links[ch-'a'] = node;
    }
    public void setEnd()
    {
        isEnd = true;
    }
    public boolean isEnd()
    {
        return isEnd;
    }

}
class Trie1 {
    TrieNode1 root;
    
    public Trie1() {
        root = new TrieNode1();
    }
    
    public void insert(String word) {
        TrieNode1 node = root;
        for(int i=0; i<word.length(); i++)
        {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) 
                node.put(ch, new TrieNode1());                                
            node = node.get(ch);
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode1 node = root;
        boolean result = false;
        for(int i=0; i<word.length(); i++)
        {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) 
            	 return false;                                
            node = node.get(ch);
        }
        return node!=null && node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode1 node = root;
        boolean result = false;
        for(int i=0; i<prefix.length(); i++)
        {
            char ch = prefix.charAt(i);
            if(!node.containsKey(ch)) 
                return false; //break;                                
            node = node.get(ch);
        }
        return node!=null;
    }
}

public class LeetCodeTrieNode {
	
	public static void main(String[] args) {
		
		/*
		Aþaðýdaki test case için yanlýþ çalýþýyor (7/16 testcase)
		["Trie","startsWith"]
		[[],["a"]]

		output   : [null,true]
		expected : [null,false]
		 */

		// Your Trie object will be instantiated and called as such:
		Trie1 obj = new Trie1();
		String word = "";
		String prefix="a";
		
		//obj.insert("leetcode");
		//boolean param_2 = obj.search("leet");
		//System.out.println("leetcode - search ( leet) : " +param_2+"");
		//param_2 = obj.search("leetcode");
		//System.out.println("search(" + "leet" +") : " +param_2+"");
		boolean param_3 = obj.startsWith("a");
		System.out.println("startsWith(" + "leet" +") : " +param_3+"");
		
		//System.out.println("startsWith(" + prefix +") : " +param_3+"");
	}
	
	
}
