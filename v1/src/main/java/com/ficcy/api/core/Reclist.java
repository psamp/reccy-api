package com.ficcy.api.core;

import java.util.ArrayList;

public class Reclist {

	private long id;
	private String title;
	private String description;
	private boolean isPrivate;
	private ArrayList<Rec> recs;

	{
		recs = new ArrayList<Rec>();
	}

	public Reclist(long id, String title, String description, boolean isPrivate, ArrayList<Rec> recs) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = isPrivate;
		this.recs = recs;
	}
	
	public Reclist(long id, String title, String description, boolean isPrivate, Rec rec) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = isPrivate;
		this.recs.add(rec);
	}
	
	public boolean addRec(Rec rec) {
		return this.recs.add(rec);
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

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public ArrayList<Rec> getRecs() {
		return new ArrayList<Rec>(recs);
	}

	public void setRecs(ArrayList<Rec> recs) {
		this.recs = recs;
	}
}
