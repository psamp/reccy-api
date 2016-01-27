package com.ficcy.api.core;

import java.util.ArrayList;

public class FiclistFactory {

	public static Ficlist getInstance(long id, String title, String description, boolean isPrivate, ArrayList<Fic> fics) {
		
		return new Ficlist(id, title, description, isPrivate, fics);
	}

	public static Ficlist getInstance(long id, String title, String description, boolean isPrivate, Fic fic) {

		return new Ficlist(id, title, description, isPrivate, fic);
	}

}
