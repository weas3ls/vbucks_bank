package com.vbank.models;

import java.time.LocalDate;
import java.util.Objects;

public class PlayerAccount {
	private int playerId;
	private int accountId;
	private String firstName;
	private String username;
	private String accountType;
	private int balance;
	private LocalDate date_created;
	private String status;


	public PlayerAccount() {
	}

	public PlayerAccount(int playerId, int accountId, String firstName, String username, String accountType, int balance, LocalDate date_created, String status) {
		this.playerId = playerId;
		this.accountId = accountId;
		this.firstName = firstName;
		this.username = username;
		this.accountType = accountType;
		this.balance = balance;
		this.date_created = date_created;
		this.status = status;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public LocalDate getDateCreated() {
		return this.date_created;
	}

	public void setDateCreated(LocalDate date_created) {
		this.date_created = date_created;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PlayerAccount)) {
			return false;
		}
		PlayerAccount playerAccount = (PlayerAccount) o;
		return playerId == playerAccount.playerId && accountId == playerAccount.accountId && Objects.equals(firstName, playerAccount.firstName) && Objects.equals(username, playerAccount.username) && Objects.equals(accountType, playerAccount.accountType) && balance == playerAccount.balance && Objects.equals(date_created, playerAccount.date_created) && Objects.equals(status, playerAccount.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, accountId, firstName, username, accountType, balance, date_created, status);
	}

	@Override
	public String toString() {
		return "{" +
			" playerId='" + getPlayerId() + "'" +
			", accountId='" + getAccountId() + "'" +
			", firstName='" + getFirstName() + "'" +
			", username='" + getUsername() + "'" +
			", accountType='" + getAccountType() + "'" +
			", balance='" + getBalance() + "'" +
			", date_created='" + getDateCreated() + "'" +
			", status='" + getStatus() + "'" +
			"}";
	}
	
}