package com.vbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vbank.util.ConnectionUtil;
import com.vbank.views.MainMenu;

public class VbucksScamDao {
    public static boolean scamPlayer(String username) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "select scamPlayer((?))";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                new MainMenu();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}