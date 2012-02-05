package org.terpworks.yahoo;

import java.util.BitSet;

public class Trie {

	
	private Node node = new Node();
	
	public void add(String entry) {
		
		node.addEntry(entry);
	}
	
	public boolean remove(String entry) {
		
		throw new UnsupportedOperationException();
	}
	public boolean  contains(String candidate) {
		
		return node.containsEntry(candidate);

	}
	
	/**
	 * This trie represents the 26 characters that appear in
	 * an English word. Since there are only 26 possibilities
	 * to represent, we can pack it into 32 bits.
	 *
	 *a -> 000...1
	 *b -> 0....10
	 *c -> 0...100
	 *
	 *etc. so that, given a mask, say 0...1110
	 *we know the arcs lead to subtries containing b,c, and d 
	 *
	 * @author harsha
	 *
	 */
	private static class Node  {
		
		private BitSet mask = new BitSet(32);
		Node[] nodes = new Node[0];
		private boolean terminating;
		
		public boolean isTerminating() {
			return terminating;
		}

		public void setTerminating(boolean terminating) {
			this.terminating = terminating;
		}

		public int setMask(char letter) {
			
			int bitIndex = (int)Character.toLowerCase(letter) - 97;
			mask.set(bitIndex);
			return bitIndex;
		}
		
		public boolean isSetMask(char letter) {
			
			return mask.get((int)Character.toLowerCase(letter) - 97);
		}
		
		public int computeIndexIntoArray(int bitMaskIndex) {
			
			int count = 0;
			for(int i=0;i<bitMaskIndex;i++) {
				
				if(mask.get(i)) ++count;
			}
			return count;
		}
		
		public boolean containsEntry(String entry) {
			
			if(entry == null || entry.length() == 0) {
				
				//if this is not a terminal node, then we have not fully matched stored string
				return isTerminating();
			}
			char firstChar = entry.charAt(0);
			String rest = entry.length() == 1 ? null : entry.substring(1);
			int bitMaskIndex = (int)Character.toLowerCase(firstChar) - 97;

			return isSetMask(firstChar) && nodes [ computeIndexIntoArray(bitMaskIndex)].containsEntry(rest);
		}
		public void addEntry(String entry) {
			
			if(entry == null || entry.length() == 0)  {
				
				//mark current node as terminating node and return
				setTerminating(true);
				return;
			}
			char firstChar = entry.charAt(0);
			String rest = entry.length() == 1 ? null : entry.substring(1);
			int bitMaskIndex = setMask(firstChar);
			int indexIntoArray = computeIndexIntoArray(bitMaskIndex);
			
			if(nodes.length < indexIntoArray + 1) {
				
				Node [] newNodes = new Node [indexIntoArray + 1];
				System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
				nodes = newNodes;
			}
			
			//check if a node is already present at this index, if so recurse on that node
			if(nodes [indexIntoArray] == null) {
				Node node = new Node();
				nodes[indexIntoArray] = node;

			}
			nodes [indexIntoArray].addEntry(rest);
		}
	}
}
