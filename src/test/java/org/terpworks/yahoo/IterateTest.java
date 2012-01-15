package org.terpworks.yahoo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.terpworks.yahoo.Iterate.Function;

public class IterateTest {

	@Test
	public void testIterate() {
		
		Iterator<Integer> results = Iterate.iterate(new Function<Integer>(){

			public Integer apply(Integer argument) {
				// TODO Auto-generated method stub
				return argument*argument;
			}
			
		}, 3);
		
		assertEquals(results.next(),(Integer) 3);
		assertEquals(results.next(),(Integer) 9);
		assertEquals(results.next(),(Integer)81);
		
		results.remove();
		assertEquals(results.next(),(Integer)81);
		
		results.remove();
		Exception t = null;
		try{ 
			results.remove();
		}catch(Exception e) {
			t = e;
		}
		
		assertNotNull(t);
		assertTrue(t instanceof IllegalStateException);
	}

	
}
