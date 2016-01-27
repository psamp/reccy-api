package com.ficcy.api.core;

import java.util.ArrayList;

public class Ficlist {

	private long id;
	private String title;
	private String description;
	private boolean isPrivate;
	private ArrayList<Fic> fics;

	{
		fics = new ArrayList<Fic>();
	}

	public Ficlist(long id, String title, String description, boolean isPrivate, ArrayList<Fic> fics) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = isPrivate;
		this.fics = fics;
	}
	
	public Ficlist(long id, String title, String description, boolean ficlistIsPrivate, Fic fic) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = ficlistIsPrivate;
		this.fics.add(fic);
	}
	
	public boolean addFic(Fic fic) {
		return this.fics.add(fic);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFiclistIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public ArrayList<Fic> getFics() {
		return new ArrayList<Fic>(fics);
	}

	public void setFics(ArrayList<Fic> fics) {
		this.fics = fics;
	}
}
