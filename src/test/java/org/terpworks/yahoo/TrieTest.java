package org.terpworks.yahoo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class TrieTest {

	@Test
	public void testAdd() {
		
		Trie trie = new Trie();
		trie.add("hat");
			
		assertTrue(trie.contains("hat"));
		
		trie.add("hen");
		assertTrue(trie.contains("hat"));

		trie.add("hot");
		assertTrue(trie.contains("hen"));
		assertFalse(trie.contains("he"));
		assertFalse(trie.contains("hatt"));
		
		trie.add("aardvark");
		trie.add("abandon");
		trie.add("zygote");
		
		assertTrue(trie.contains("abandon"));

	}
	@Test
	public void testPerformance() throws Exception {
		
		File dictionary = new File("/usr/share/dict/words");
		BufferedReader reader = new BufferedReader(new FileReader(dictionary));
		String word;
		Trie trie = new Trie();
		while((word = reader.readLine())!=null) {
			
			trie.add(word);
		}
		
		reader.close();
		
		/**
		 * Check for positive matches
		 * aardvark, abandon, zymotic, zygote
		 */
		assertTrue(trie.contains("aardvark"));
		assertTrue(trie.contains("abandon"));
		assertTrue(trie.contains("zymotic"));
		assertTrue(trie.contains("zygote"));
		
		/**
		 * Check for negative matches
		 * 
		 */
		assertFalse(trie.contains("ardvark"));
		assertFalse(trie.contains("abandone"));
		assertFalse(trie.contains("zymotica"));
		assertFalse(trie.contains("zygoted"));
	}

}
