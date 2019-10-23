package com.vbank.models;

import java.util.Objects;

public class Account {
    private int accountId;
    private int playerId;

    public Account(int accountId, int playerId) {
        this.accountId = accountId;
        this.playerId = playerId;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Account accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public Account playerId(int playerId) {
        this.playerId = playerId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return accountId == account.accountId && playerId == account.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, playerId);
    }

    @Override
    public String toString() {
        return "{" +
            " accountId='" + getAccountId() + "'" +
            ", playerId='" + getPlayerId() + "'" +
            "}";
    }
}