package com.ficcy.api.core;

import java.util.ArrayList;

import com.ficcy.api.constants.RATING;

public class RecFactory {

	public static Rec getInstance(String id, String title, String author, String url, String summary, String about,
			RATING rating, ArrayList<String> tags) {

		return new Rec(id, title, author, url, summary, about, rating, tags);
	}

	public static Rec getInstance(String id, String title, String author, String url, String summary, String about,
			RATING rating) {

		return new Rec(id, title, author, url, summary, about, rating);

	}

	public static Rec getInstance(String title, String author, String url, String summary, String about, RATING rating,
			ArrayList<String> tags) {

		return new Rec(title, author, url, summary, about, rating, tags);
	}

	public static Rec getInstance(String title, String author, String url, String summary, String about,
			RATING rating) {

		return new Rec(title, author, url, summary, about, rating);

	}

}
