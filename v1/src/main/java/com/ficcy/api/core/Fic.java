package com.ficcy.api.core;

import java.net.URL;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.ficcy.api.constants.RATING;

public class Fic {
	
	private long id;
	private ArrayList<String> tags;
	@NotNull
	private String title;
	@NotNull
	private String author;
	@NotNull
	private URL url;
	@NotNull
	private String summary;
	@NotNull
	private String fandom;
	@NotNull
	private RATING rating;
	
	{
		tags = new ArrayList<String>();
	}
	
	public Fic() {
		
	}
	
	public Fic(String title, String author, URL url, String summary, String fandom, RATING rating) {
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
	}
	
	public Fic(String title, String author, URL url, String summary, String fandom, RATING rating, ArrayList<String> tags) {
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
		this.tags = tags;
	}

	public Fic(long id, String title, String author, URL url, String summary, String fandom, RATING rating) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.summary = summary;
		this.fandom = fandom;
		this.rating = rating;
	}

	public Fic(long id, String title, String author, URL url, String summary, String fandom, RATING rating, ArrayList<String> tags) {
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

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
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

	public long getId() {
		return id;
	}
}
