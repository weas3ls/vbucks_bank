package com.vbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vbank.util.ConnectionUtil;

public class VbucksAccountDao {

    public static int createVbucksAccount(int playerId, String accountType) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select createVbucksAccount((?), (?))";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, playerId);
            statement.setString(2, accountType);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("createVbucksAccount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void depositVbucks(int amount, int playerId, int accountId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select depositVbucks((?), (?))";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, amount);
            statement.setLong(2, accountId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getBoolean("depositVbucks")) {
                    System.out.println("\nVbucks deposited successfully\n");
                    PlayerDao.printPlayerAccountInfo(playerId);
                } else {
                    System.out.println("\nYour account is closed you noob! Open it first!\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkVbucksAmount(int amount, int accountId) {
        boolean validAmount = false;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select balance from vbucks_account where id = (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, accountId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                if (amount <= balance) {
                    validAmount = true;
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validAmount;
    }

    public static void withdrawVbucks(int amount, int playerId, int accountId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select withdrawVbucks((?), (?))";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, amount);
            statement.setLong(2, accountId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getBoolean("withdrawVbucks")) {
                    System.out.println("\nVbucks withdrawn successfully\n");
                    PlayerDao.printPlayerAccountInfo(playerId);
                } else {
                    System.out.println("\nYour account is closed you noob!\n");
                    PlayerDao.printPlayerAccountInfo(playerId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void giftVbucks(int amount, int gifterAccount, int giftedAccount, int playerId) {
        withdrawVbucks(amount, playerId, gifterAccount);
        depositVbucks(amount, playerId, giftedAccount);
        System.out.println("\nGift successful! Thank you for your generosity, you noob\n");
    }

    public static boolean checkVbucksAccountExists(int accountId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select count(*) from player_vbucks_account where id = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, accountId);
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

    public static void createJointAccount(int accountId, int jointPlayerId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "insert into player_vbucks_account (player_id, account_id) values ((?), (?))";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, jointPlayerId);
            statement.setLong(2, accountId);

            int result = statement.executeUpdate();

            if (result == 1) {
                System.out.println("\nCongrats! You've added another player to this account, you noob!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAccount(int accountId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "update vbucks_account set status = 'Closed' where id = (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, accountId);

            int result = statement.executeUpdate();

            if (result == 1) {
                System.out.println("\nYou've closed your account, you noob! Goodbye!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}