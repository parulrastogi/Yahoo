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
		
		/**
		 * If this is a new element not in the set, expand the array to add the new element.
		 * otherwise, this is a request to remove the element from membership in any set
		 */
		
		if(!index.containsKey(element)) {
			
			int len = delegate.length;
			int[] newDelegate = new int[len + 1];
			System.arraycopy(delegate, 0, newDelegate, 0, len);
			index.put(element, len);
			newDelegate[len] = -1;
			invertedIndex.put(len, element);
			delegate = newDelegate;
		}else {
			
			delegate[index.get(element)] = -1;
		}
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
