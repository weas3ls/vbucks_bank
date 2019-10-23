package com.vbank.models;

import java.util.Objects;

public class Player {
    private int playerId;
    private String firstName;
    private String username;

	public Player() {
	}

	public Player(int playerId, String firstName, String username) {
		this.playerId = playerId;
		this.firstName = firstName;
		this.username = username;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Player)) {
			return false;
		}
		Player player = (Player) o;
		return playerId == player.playerId && Objects.equals(firstName, player.firstName) && Objects.equals(username, player.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, firstName, username);
	}

	@Override
	public String toString() {
		return "{" +
			" playerId='" + getPlayerId() + "'" +
			", firstName='" + getFirstName() + "'" +
			", username='" + getUsername() + "'" +
			"}";
	}
}