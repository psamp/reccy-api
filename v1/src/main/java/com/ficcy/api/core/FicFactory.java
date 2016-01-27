package com.ficcy.api.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.ficcy.api.constants.RATING;

public class FicFactory {

	public static Fic getInstance(long id, String title, String author, String url, String summary, String fandom,
			RATING rating, ArrayList<String> relationships, ArrayList<String> tags) throws FiccyException {

		Fic rtn = null;

		try {
			rtn = new Fic(id, title, author, new URL(url), summary, fandom, rating, relationships, tags);
		} catch (MalformedURLException e) {
			throw new FiccyException(e);
		}

		return rtn;
	}

	public static Fic getInstance(long id, String title, String author, String url, String summary, String fandom,
			RATING rating) throws FiccyException {

		Fic rtn = null;

		try {
			rtn = new Fic(id, title, author, new URL(url), summary, fandom, rating);
		} catch (MalformedURLException e) {
			throw new FiccyException(e);
		}

		return rtn;
	}

}
