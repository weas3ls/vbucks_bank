package com.vbank.services;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.vbank.dao.PlayerDao;
import com.vbank.dao.VbucksScamDao;
import com.vbank.views.LoginView;
import com.vbank.views.MainMenu;

public class VbucksScam {
    static Scanner scanner = new Scanner(System.in);
    static PlayerDao playerDao = new PlayerDao();

    public static void willWeScam(int randomNum) throws SQLException {
        switch (randomNum) {
        case 1:
            break;
        case 2:
            scamTheNoob();
        case 3:
            scamTheNoob();
        case 4:
            break;
        case 5:
            scamTheNoob();
        case 6:
            break;
        case 7:
            scamTheNoob();
        case 8:
            break;
        case 9:
            break;
        case 10:
            break;
        default:
            break;
        }
    }

    public static void scamTheNoob() throws SQLException {
        String tbs = "";
        System.out.println("\nHello! Would you like to earn some free Vbucks?? (y/n)");
        while (tbs.equals("")) {
            if (!scanner.hasNext()) {
                System.out.println("Please choose! (y/n)");
                continue;
            }
            tbs = scanner.nextLine();
            if (tbs.equals("y")) {
                Console console = System.console();
                System.out.println("\nGreat! Please login so we can deliver the Vbucks to your account");
                System.out.println("\nEnter username");
                String username = console.readLine();
                while (true) {
                    boolean usernameExists = playerDao.checkPlayerExists(username);
                    if (!usernameExists) {
                        System.out.println("\nSorry! We can't seem to find your username\nPlease try again");
                        username = console.readLine();
                    } else {
                        break;
                    }
                }

                String hashedPassword = "";
                String salt = "";

                ResultSet resultSet = playerDao.getPlayerPassword(username);

                while (resultSet.next()) {
                    hashedPassword = resultSet.getString("password");
                    salt = resultSet.getString("password_salt");
                }

                boolean passwordVerified = false;
                while (!passwordVerified) {
                    System.out.println("\nEnter password");
                    char[] password = console.readPassword();
                    passwordVerified = LoginView.verifyPassword(password, hashedPassword, salt);
                    if (passwordVerified) {
                        break;
                    }
                    System.out.println("\nSorry, that doesn't seem to be the right password! Try again");
                }
                if (passwordVerified) {
                    VbucksScamDao.scamPlayer(username);
                    System.out.println("\n\nHa! You noob! You should know that Vbank will NEVER ask for your login information!\nMay you learn your lesson for the future. You will never see your Vbucks again!\n\n");
                    break;
                }
            } else {
                System.out.println("\n\nNice job on avoiding the scam! We've logged you out to protect your account\n");
                new MainMenu();
            }
        }
    }
}