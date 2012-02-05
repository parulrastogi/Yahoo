package org.terpworks.yahoo;

/**
 * Inspired by Phil Bagwell's Ideal Hash Trees paper.
 * An Array Mapped Trie implementation that uses an Integer 
 * bit mask to quickly identify the sub arcs that are represented
 * by a given node. The count pop instruction then gives a fast 
 * index into an array containing the specific sub arc of interest.
 * 
 * @author harsha
 *
 */
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
		
		private int mask = 0;
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
			mask|= (1<< bitIndex);
			return bitIndex;
		}
		
		public boolean isSetMask(char letter) {
			
			int bitIndex = (int)Character.toLowerCase(letter) - 97;
			return (mask & (1 << bitIndex)) != 0;
		}
		
		public int computeIndexIntoArray(int bitMaskIndex) {
			
			int shiftRightCount = 31 - bitMaskIndex;
			return Integer.bitCount(mask << shiftRightCount);
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
