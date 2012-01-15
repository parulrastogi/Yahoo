package org.terpworks.yahoo;

import java.util.Collection;

/**
 * Provides a Lazy Stream resulting from the iterative application of a function to an argument
 * (x, f(x), f(f(x)), ...)
 * @author Parul
 *
 */
public class Iterate {

	
	public <T> Collection<T> iterate(Function<T> function, T argument) {
		
		throw new UnsupportedOperationException();
	}
	
	public static interface Function<T> {
		
		public T apply(T argument) ;
	}
}
