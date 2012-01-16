package org.terpworks.yahoo;

import java.util.Iterator;

/**
 * Provides a Lazy Stream resulting from the iterative application of a function to an argument
 * (x, f(x), f(f(x)), ...)
 * @author Parul
 *
 */
public class Iterate {

	
	public static <T> Iterator<T> iterate(Function<T> function, T argument) {
		
		return new LazyIterator<T>(function, argument);
	}
	
	public static interface Function<T> {
		
		public T apply(T argument) ;
	}
	/**
	 * Iterator that lazily processes the stream to generate successive applications of the function to a given argument.
	 * Each call to next results in the application of the function f, to the result of the previous call.
	 * @author harsha
	 *
	 * @param <T>
	 */
	private static class LazyIterator<T> implements Iterator<T> {

		private Function<T> function;
		
		private T prevArg;
		
		private T prevPrevArg ;
		
		public LazyIterator(Function<T> function, T prevArg) {
			super();
			this.function = function;
			this.prevArg = prevArg;
		}

		public boolean hasNext() {
			return true;
		}

		public T next() {
			
			prevPrevArg = prevArg;
			prevArg = function.apply(prevArg);
			return prevPrevArg;
		}

		public void remove() {
			
			if(prevPrevArg == null) {
				
				throw new IllegalStateException();
			}
			prevArg = prevPrevArg;
			prevPrevArg = null;
		}
		
		
	}
}
