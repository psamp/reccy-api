package com.ficcy.api.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Reclist implements Serializable {
	
	private static final long serialVersionUID = 2749953926740113769L;
	private ArrayList<Rec> recs;
	private String id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private boolean isPrivate;

	{
		recs = new ArrayList<Rec>();
	}

	public Reclist() {
		
	}
	
	public Reclist(String id, String title, String description, boolean isPrivate, ArrayList<Rec> recs) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = isPrivate;
		this.recs = recs;
	}

	public Reclist(String id, String title, String description, boolean isPrivate, Rec rec) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isPrivate = isPrivate;
		this.recs.add(rec);
	}

	public boolean addRec(Rec rec) {
		return this.recs.add(rec);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<Rec> getRecs() {
		return new ArrayList<Rec>(recs);
	}

	public void setRecs(ArrayList<Rec> recs) {
		this.recs = recs;
	}
}
