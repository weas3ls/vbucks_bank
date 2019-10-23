package com.vbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vbank.models.Player;
import com.vbank.models.PlayerAccount;
import com.vbank.util.ConnectionUtil;


public class PlayerDao {
    public void createDBPlayer(String firstName, String username, String hashedPassword, String salt) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "insert into player (first_name, username, password, password_salt) values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, firstName);
            statement.setString(2, username);
            statement.setString(3, hashedPassword);
            statement.setString(4, salt);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPlayerExists(int playerId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select count(*) from player where id = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.getInt("count") > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPlayerExists(String username) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select count(*) from player where username = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.getInt("count") > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getPlayerPassword(String username) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select password, password_salt from player where username = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> printPlayerAccountInfo(int playerId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select vbucks_account.id, player.first_name, vbucks_account_type.account_type, vbucks_account.balance, vbucks_account.date_created, vbucks_account.status, player.username from player_vbucks_account inner join player on player_vbucks_account.player_id = player.id inner join vbucks_account on vbucks_account.id =  player_vbucks_account.account_id inner join vbucks_account_type on vbucks_account.account_type = vbucks_account_type.id where player.id = (?) and vbucks_account.status = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, playerId);
            statement.setString(2, "Open");
            ResultSet resultSet = statement.executeQuery();

            List<PlayerAccount> players = new ArrayList<>();
            List<Integer> accounts = new ArrayList<>();
            while (resultSet.next()) {
                int accountId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String accountType = resultSet.getString("account_type");
                String status = resultSet.getString("status");
                int balance = resultSet.getInt("balance");
                LocalDate dateCreated = resultSet.getDate("date_created").toLocalDate();
                PlayerAccount playerAccount = new PlayerAccount(playerId, accountId, firstName, username, accountType, balance, dateCreated, status);
                players.add(playerAccount);
                accounts.add(accountId);
            }
            System.out.println("----------------------------------- ACCOUNT INFO -----------------------------------");
            System.out.println("| ID |   FIRST NAME   |    BALANCE    |  DATE CREATED  |  ACCOUNT TYPE  |  STATUS  |");
            for (PlayerAccount playerAccount : players) {
                System.out.printf("| %2s | %-14s | %12sV | %14s | %-14s | %-8s |%n", playerAccount.getAccountId(), playerAccount.getFirstName(), playerAccount.getBalance(), playerAccount.getDateCreated(), playerAccount.getAccountType(), playerAccount.getStatus());
            }
            System.out.println("------------------------------------------------------------------------------------");
            return accounts;
        } catch (SQLException e) {
            return null;
        }
    }

    public Player createPlayerClass(String username) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select id, first_name, username from player where username = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                username = resultSet.getString("username");

                Player player = new Player(id, firstName, username);
                return player;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}