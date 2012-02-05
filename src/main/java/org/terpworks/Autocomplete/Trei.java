package org.terpworks.Autocomplete;

import java.util.ArrayList;
import java.util.LinkedList;



public class Trei {
	Node root;
	
	public Trei(){
		root=new Node();
	}

	private static class Node{
		char value;
		Node[] next = new Node[26];
		boolean endOfWordMarker;
		private Node(){
			value='0';
			endOfWordMarker=false;
			for(int i=0;i<26;i++){
				next[i]=null;
			} 
		}
	}
	
	
	
	public static void insert(Trei tree, String word){
		char[] characters=word.toLowerCase().toCharArray();
		int i =0;
		Node currNode=tree.root;
		while(currNode.next[characters[i]-97]!=null){
			currNode=currNode.next[characters[i]-97];
			i++;
		}
		while(i<characters.length){
			currNode.next[characters[i]-97]=new Node();
			currNode.next[characters[i]-97].value=characters[i];
			currNode=currNode.next[characters[i]-97];
			i++;
		}
		currNode.endOfWordMarker=true;
	}
	
	public static boolean find(Trei tree, String word){
		char[] characters=word.toLowerCase().toCharArray();
		Node currNode=tree.root;
		for(int i=0;i<characters.length;i++){
			if(currNode.next[characters[i]-97]==null){
				return false;
			}
			else{
				currNode=currNode.next[characters[i]-97];
			}
		}
		if(!currNode.endOfWordMarker) return false;
		return true;
	}
	
	public static void beginsWith(Trei tree, String str,int n){
		char[] characters=str.toLowerCase().toCharArray();
		Node currNode=tree.root;
		for(int i=0;i<characters.length;i++){
			if(currNode.next[characters[i]-97]==null){
				System.out.println("There is no word that begins with "+str);
			}
			else{
				currNode=currNode.next[characters[i]-97];
			}
		}
		ArrayList<String> wordList=new ArrayList<String>();
		if(currNode.endOfWordMarker) wordList.add(str);
		ArrayList<String> suffix=BFS(currNode,n);
		for(String s:suffix){
			wordList.add(str+s);
		}
		for(String s:wordList){
			System.out.println(s);
		}
	}
	
	private static ArrayList<String> BFS(Node treenode,int n){
		LinkedList<Node> queue = new LinkedList<Node>();
		LinkedList<StringBuilder> suffix=new LinkedList<StringBuilder>();
		ArrayList<String> wordList=new ArrayList<String>();
		queue.offer(treenode);
		//char[] data={treenode.value};
		String str=new String();
		suffix.offer(new StringBuilder(str));
		while(!queue.isEmpty()&& wordList.size()<n){
			Node node = queue.poll();
			StringBuilder prefix=suffix.poll();
			String prevStr=new String(prefix);
			for(int i =0;i<26;i++){
				if(node.next[i]!=null){
					queue.offer(node.next[i]);
					char[] nodeChar={node.next[i].value};
					str=prevStr+new String(nodeChar);
					suffix.offer(new StringBuilder(str));
					if(node.next[i].endOfWordMarker) wordList.add(str);
				}
			}
		}
		return wordList;
	}

	
	public static void main(String args[]){
		Trei tree = new Trei();
		Trei.insert(tree, "broth");
		Trei.insert(tree, "brother");
		Trei.insert(tree, "broil");
		Trei.insert(tree, "broke");
		Trei.insert(tree, "broken");
		Trei.insert(tree, "bromine");
		if(Trei.find(tree, "bro")){
			System.out.println("found");
		}
		else{
			System.out.println("not found");
		}
		Trei.beginsWith(tree, "bro", 4);
	}
	
	
}
