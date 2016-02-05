package com.ficcy.api.core;

import java.util.ArrayList;

import com.stormpath.sdk.account.Account;

public class User {
	private Account account;
	private ArrayList<Fic> fics;
	private ArrayList<Ficlist> ficlists;
	
	{
		//todo: populate lists from dao when instance loads
	}
	
	public User(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<Fic> getFics() {
		return new ArrayList<Fic>(fics);
	}

	private void setFics(ArrayList<Fic> fics) {
		this.fics = fics;
	}

	public ArrayList<Ficlist> getFiclists() {
		return new ArrayList<Ficlist>(ficlists);
	}

	private void setFiclists(ArrayList<Ficlist> ficlists) {
		this.ficlists = ficlists;
	}
}
