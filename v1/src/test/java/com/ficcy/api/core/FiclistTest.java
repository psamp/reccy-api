package com.ficcy.api.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.ficcy.api.constants.RATING;

public class FiclistTest {

	private Ficlist first;
	private Ficlist second;
	private Random random = new Random();
	private ArrayList<Fic> fics = new ArrayList<Fic>();
	private ArrayList<String> tags = new ArrayList<String>();

	@Before
	public void setUp() throws FiccyException {

		tags.add("fluff");
		tags.add("hurt comfort");
		tags.add("Blah/Blah");
		tags.add("Who & Who");

		fics.add(FicFactory.getInstance(random.nextLong(), "Hmmm Hmm", "anAuthor", "http://piiw.co.uk/urevleg",
				"Lorem ipsum dolor si amet", "A fandom", RATING.GENERAL));
		fics.add(FicFactory.getInstance(random.nextLong(), "A Story", "author", "http://potihvut.org/dusimdu",
				"A story about something", "Whatever", RATING.MATURE, tags));

		first = FiclistFactory.getInstance(random.nextLong(), "A list of fics", "This is my list of fics.", true, fics);
		second = FiclistFactory.getInstance(random.nextLong(), "Another list of fics",
				"This is someone else's list of fics.", true,
				FicFactory.getInstance(random.nextLong(), "A Story", "author", "http://odages.com/fa",
					
						
						"A story about something", "Whatever", RATING.MATURE, tags));
	}

	@Test
	public void testThatFiclistIsNotEmpty() {
		assertTrue(first.getFics().size() > 0);
		assertTrue(second.getFics().size() > 0);
	}

}
