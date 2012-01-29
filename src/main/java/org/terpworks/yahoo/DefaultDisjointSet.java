package org.terpworks.yahoo;

import java.util.ArrayList;

/**
 * Disjoint Set, represented as an array
 * @author harsha
 *
 * @param <T>
 */
public class DefaultDisjointSet<T> implements DisjointSet<T> {

	private ArrayList<T> elements = new ArrayList<T>();
	
	private int[]  delegate;
	
	
	public DefaultDisjointSet(T[] elements) {
		
		for(T element : elements) {
			
			this.elements.add(element);
		}
		delegate = new int[elements.length];
		for(int i=0;i<elements.length;i++)
			delegate[i]=-1;
		
	}
	public void createSet(T element) {
		// TODO Auto-generated method stub
		
	}

	public T findSet(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void mergeSet(T parent, T child) {
		// TODO Auto-generated method stub
		
	}

}
