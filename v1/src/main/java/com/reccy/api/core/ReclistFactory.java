package com.reccy.api.core;

import java.util.ArrayList;

public class ReclistFactory {

	public static Reclist getInstance(String id, String title, String description, boolean isPrivate, ArrayList<Rec> recs) {
		
		return new Reclist(id, title, description, isPrivate, recs);
	}

	public static Reclist getInstance(String id, String title, String description, boolean isPrivate, Rec rec) {

		return new Reclist(id, title, description, isPrivate, rec);
	}

}
