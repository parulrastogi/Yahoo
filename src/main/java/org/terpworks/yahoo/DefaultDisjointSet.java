package org.terpworks.yahoo;

import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint Set, represented as an array
 * @author harsha
 *
 * @param <T>
 */
public class DefaultDisjointSet<T> implements DisjointSet<T> {

	private Map<T, Integer> index = new HashMap<T, Integer>();
	private Map<Integer, T> invertedIndex = new HashMap<Integer, T>();
	
	private int[]  delegate;
	
	
	public DefaultDisjointSet(T[] elements) {
		
		
		delegate = new int[elements.length];
		for(int i=0;i<elements.length;i++){
			
			delegate[i]=-1;
			index.put(elements[i], i);
			invertedIndex.put(i,elements[i]);
		}
		
	}
	public void createSet(T element) {
		// TODO Auto-generated method stub
		
	}

	public T findSet(T element) {
		
		if(!index.containsKey(element)) return null;
		int arrayIndex = index.get(element);
		int originalArrayIndex = arrayIndex;
		
		int originalParentIndex = delegate[arrayIndex];
		int parentIndex = originalParentIndex;

		while(parentIndex != -1) {
			arrayIndex = parentIndex;
			parentIndex = delegate[arrayIndex];
		}
		
		if(originalParentIndex != -1)
			delegate[originalArrayIndex] = arrayIndex;
		return invertedIndex.get(arrayIndex) ;
	}

	public void  mergeSet(T representativeOfFirstSet, T representativeOfSecondSet) {
		// TODO Auto-generated method stub
		if(!index.containsKey(representativeOfFirstSet) 
				|| !index.containsKey(representativeOfSecondSet))
			return;
		
		int firstIndex = index.get(findSet(representativeOfFirstSet));
		int secondIndex = index.get(findSet(representativeOfSecondSet));
		
		if(firstIndex <  secondIndex) {
			
			delegate[secondIndex] = firstIndex;
		} else {
			
			delegate[firstIndex] = secondIndex;
		}
		
	}

}
