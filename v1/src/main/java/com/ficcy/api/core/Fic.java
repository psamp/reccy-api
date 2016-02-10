package com.ficcy.api.core;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.ficcy.api.constants.RATING;

public class Fic {

	private String id;
	private ArrayList<String> tags;
	private String summary;
	private String owner;
	@NotNull
	private String title;
	@NotNull
	private String author;
	@NotNull
	private String url;
	@NotNull
	private String fandom;
	@NotNull
	private RATING rating;

	{
		tags = new ArrayList<String>();
	}

	public Fic() {

	}

	public Fic(String title, String author, String url, String summary, String fandom, RATING rating) {
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
	}

	public Fic(String title, String author, String url, String summary, String fandom, RATING rating,
			ArrayList<String> tags) {
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
		this.tags = tags;
	}

	public Fic(String id, String title, String author, String url, String summary, String fandom, RATING rating) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
	}

	public Fic(String id, String title, String author, String url, String summary, String fandom, RATING rating,
			ArrayList<String> tags) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFandom() {
		return fandom;
	}

	public void setFandom(String fandom) {
		this.fandom = fandom;
	}

	public RATING getRating() {
		return rating;
	}

	public void setRating(RATING rating) {
		this.rating = rating;
	}

	public ArrayList<String> getTags() {
		return new ArrayList<String>(tags);
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
