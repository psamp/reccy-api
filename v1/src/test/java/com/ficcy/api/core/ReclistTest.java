package com.ficcy.api.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.ficcy.api.constants.RATING;

public class ReclistTest {

	private Reclist first;
	private Reclist second;
	private Random random = new Random();
	private ArrayList<Rec> recs = new ArrayList<Rec>();
	private ArrayList<String> tags = new ArrayList<String>();

	@Before
	public void setUp() throws ReccyException {

		tags.add("fluff");
		tags.add("hurt comfort");
		tags.add("Blah/Blah");
		tags.add("Who & Who");

		recs.add(RecFactory.getInstance("" + random.nextLong(), "Hmmm Hmm", "anAuthor", "http://piiw.co.uk/urevleg",
				"Lorem ipsum dolor si amet", "A fandom", RATING.GENERAL));
		recs.add(RecFactory.getInstance("" + random.nextLong(), "A Story", "author", "http://potihvut.org/dusimdu",
				"A story about something", "Whatever", RATING.MATURE, tags));

		first = ReclistFactory.getInstance(random.nextLong(), "A list of fics", "This is my list of fics.", true, recs);
		second = ReclistFactory.getInstance(random.nextLong(), "Another list of fics",
				"This is someone else's list of fics.", true,
				RecFactory.getInstance("" + random.nextLong(), "A Story", "author", "http://odages.com/fa",
					
						
						"A story about something", "Whatever", RATING.MATURE, tags));
	}

	@Test
	public void testThatReclistIsNotEmpty() {
		assertTrue(first.getRecs().size() > 0);
		assertTrue(second.getRecs().size() > 0);
	}

}
