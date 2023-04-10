package com.bc;

import java.util.HashMap;
import java.util.Map;

public class Trie_ {
    private TrieNode root;

    public Trie_(){
        this.root=new TrieNode();
    }

    public void addStr(String target){
        TrieNode curNode=root;
        char curCharacter;
        for(int i=0;i<target.length();i++){
            curCharacter=target.charAt(i);
            if(!curNode.childIsContainsCharacter(curCharacter)){
                curNode.addChild(curCharacter);
            }
            curNode=curNode.getChildTrieNode(curCharacter);
        }
        curNode.setEnd(true);
    }

    public boolean searchStr(String target){
        TrieNode endNode=search(target);
        return endNode!=null&&endNode.isEnd();
    }

    public boolean startWith(String prefix){
        TrieNode endNode=search(prefix);
        return endNode!=null;
    }

    public TrieNode search(String target){
        TrieNode curNode=root;
        for(int i=0;i<target.length();i++){
            if(!curNode.childIsContainsCharacter(target.charAt(i)))
                return null;
            curNode=curNode.getChildTrieNode(target.charAt(i));
        }
        return curNode;
    }
}
class TrieNode{
    private Map<Character,TrieNode> child;
    private boolean isEnd;

    public TrieNode(){
        child=new HashMap<>();
        isEnd=false;
    }

    public void addChild(char target){
        TrieNode newChild=new TrieNode();
        child.put(target,newChild);
    }

    public boolean childIsContainsCharacter(char target){
        if(child.containsKey(target))
            return true;
        return false;
    }

    public TrieNode getChildTrieNode(char target){
        if(childIsContainsCharacter(target))
            return child.get(target);
        return null;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
