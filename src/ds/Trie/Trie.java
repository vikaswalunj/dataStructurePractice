package ds.Trie;

import java.util.HashMap;
import java.util.Map;
// trie implementation using hash map
public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;    
        }
    }
 
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
 
        if(t != null && t.isLeaf) 
            return true;
        else
            return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) 
            return false;
        else
            return true;
    }
 
    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
 
        return t;
    }
    
    public boolean dfsSearch(HashMap<Character, TrieNode> children, String word, int start) {
        if(start == word.length()){
            if(children.size()==0) 
                return true; 
            else
                return false;
        }
 
        char c = word.charAt(start);    
 
        if(children.containsKey(c)){
            if(start == word.length()-1 && children.get(c).isLeaf){
                return true;
            }
 
            return dfsSearch(children.get(c).children, word, start+1);
        }else if(c == '.'){
            boolean result = false;
            for(Map.Entry<Character, TrieNode> child: children.entrySet()){
                if(start == word.length()-1 && child.getValue().isLeaf){
                    return true;
                } 
 
                //if any path is true, set result to be true; 
                if(dfsSearch(child.getValue().children, word, start+1)){
                    result = true;
                }
            }
 
            return result;
        }else{
            return false;
        }
    }
}
