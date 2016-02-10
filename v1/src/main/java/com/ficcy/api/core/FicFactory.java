package com.ficcy.api.core;

import java.util.ArrayList;

import com.ficcy.api.constants.RATING;

public class FicFactory {

	public static Fic getInstance(String id, String title, String author, String url, String summary, String fandom,
			RATING rating, ArrayList<String> tags) {

		return new Fic(id, title, author, url, summary, fandom, rating, tags);
	}

	public static Fic getInstance(String id, String title, String author, String url, String summary, String fandom,
			RATING rating) {

		return new Fic(id, title, author, url, summary, fandom, rating);

	}

	public static Fic getInstance(String title, String author, String url, String summary, String fandom, RATING rating,
			ArrayList<String> tags) {

		return new Fic(title, author, url, summary, fandom, rating, tags);
	}

	public static Fic getInstance(String title, String author, String url, String summary, String fandom,
			RATING rating) {

		return new Fic(title, author, url, summary, fandom, rating);

	}

}
