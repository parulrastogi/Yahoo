package org.terpworks.yahoo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DefaultDisjointSetTest {

	@Test
	public void testFindSet() {
		
		DefaultDisjointSet<Integer> set = new DefaultDisjointSet<Integer>(new Integer[] { 1 , 2 , 3 , 4});
		Integer representative = set.findSet(3);
		assertEquals(representative, (Integer)3);
		
		//now merge 2 and 4
		set.mergeSet(2, 4);
		
		//now, 4's representative element should be 2
		representative = set.findSet(4);
		assertEquals(representative, (Integer)2);

		//now merge 1 and 4
		set.mergeSet(1, 4);
		
		//now 2's representative element must be 1
		representative = set.findSet(2);
		assertEquals(representative, (Integer)1);

	}
	
	@Test
	public void testCreateSet() {
		
		DefaultDisjointSet<Integer> set = new DefaultDisjointSet<Integer>(new Integer[] { 1 , 2 , 3 , 4});
		Integer representative = set.findSet(3);
		assertEquals(representative, (Integer)3);
		
		//now merge 2 and 4
		set.mergeSet(2, 4);
		
		//now create a set out of 4
		
		set.createSet(4);
		representative = set.findSet(4);
		assertEquals(representative, (Integer)4);
		
		set.createSet(5);
		set.mergeSet(2, 5);
		set.mergeSet(1,5);
		representative = set.findSet(5);
		assertEquals(representative, (Integer)1);
		
	}
	
	@Test
	public void testPerformance() {
		
		int SIZE = 1000000;
		Integer [] elements = new Integer[SIZE];
		for(int i=0;i<SIZE;i++)
			elements[i]=i;
		DefaultDisjointSet<Integer> set = new DefaultDisjointSet<Integer>(elements);
		
		long start = System.currentTimeMillis();
		for(int n=1;n<100000;n++)
			set.mergeSet(2*n, 4*n);
		
		System.out.println(System.currentTimeMillis() - start);
	}
}
