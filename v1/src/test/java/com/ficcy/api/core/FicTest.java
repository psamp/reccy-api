package com.ficcy.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.ficcy.api.constants.RATING;

public class FicTest {
	private Fic first;
	private Fic second;
	private Random random = new Random();

	@Before
	public void setUp() {

		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<String> relationships = new ArrayList<String>();

		tags.add("one");
		tags.add("two");
		tags.add("three");

		relationships.add("Google/Browser");
		relationships.add("Yahoo & Bing");

		try {
			first = FicFactory.getInstance(random.nextLong(), "Lorem Ipsum", "lora", "https://www.google.com/",
					"This is literally google.com", "Alphabet Inc.", RATING.GENERAL, relationships, tags);

			second = FicFactory.getInstance(random.nextLong(), "The Star Wars", "jediKnight", "https://www.twitter.com/",
					"A star wars", "Star Wars", RATING.TEEN);

		} catch (FiccyException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetTitle() {
		assertEquals("Lorem Ipsum", first.getTitle());
		assertEquals("The Star Wars", second.getTitle());
	}
	
	@Test
	public void testGetAuthor() {
		assertEquals("lora", first.getAuthor());
		assertEquals("jediKnight", second.getAuthor());
	}
	
	@Test
	public void testGetUrl() {
		assertEquals("https://www.google.com/", first.getUrl().toString());
		assertEquals("https://www.twitter.com/", second.getUrl().toString());
	}
	
	@Test
	public void testGetSummary() {
		assertEquals("This is literally google.com", first.getSummary());
		assertEquals("A star wars", second.getSummary());
	}
	
	@Test
	public void testGetFandom() {
		assertEquals("Alphabet Inc.", first.getFandom());
		assertEquals("Star Wars", second.getFandom());
	}
	
	@Test
	public void testGetRating() {
		assertEquals(RATING.GENERAL, first.getRating());
		assertEquals(RATING.TEEN, second.getRating());
	}
	
	@Test
	public void testGetRelationships() {
		assertNotNull(first.getRelationships());
		assertNotNull(second.getRelationships());
		
		ArrayList<String> ship = first.getRelationships();
		
		for (String tag : ship) {
			assertNotNull(tag);
		}
	}
	
	@Test
	public void testGetTags() {
		assertNotNull(first.getTags());
		assertNotNull(second.getTags());
		
		ArrayList<String> tags = first.getTags();
		
		for (String tag : tags) {
			assertNotNull(tag);
		}
	}

}
