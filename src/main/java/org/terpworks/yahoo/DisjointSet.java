package org.terpworks.yahoo;
/**
 * Disjoint Set Data Structure using path compression
 * 
 * @author harsha
 *
 * @param <T>
 */
public interface DisjointSet<T> {

	/**
	 * Creates a set with one element in it
	 * @param element
	 */
	public void createSet(T element);
	/**
	 * Returns a representative element of the set containing the given element, 
	 * or null if the element is not present.
	 * @param element
	 * @return
	 */
	public T findSet(T element);
	
	public void mergeSet(T parent, T child);
}
