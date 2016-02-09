package com.ficcy.api.core;

import com.stormpath.sdk.account.Account;

public class User {
	private Account account;
	private long ID;
	
	public User(Account account, int ID) {
		this.account = account;
		this.ID = ID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getExternalID() {
		return (String) this.account.getCustomData().get("hashid");
	}
	
	public long getID() {
		return this.ID;
	}
}
