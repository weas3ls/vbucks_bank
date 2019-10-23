package com.vbank.views;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.vbank.dao.PlayerDao;
import com.vbank.models.Account;
import com.vbank.models.Player;
import com.vbank.services.PasswordHashing;
import com.vbank.services.VbucksScam;
import com.vbank.util.ScannerUtil;

public class LoginView extends PasswordHashing implements View {

    private static PlayerDao playerDao = new PlayerDao();

    @Override
    public View process() throws SQLException {
        Account account = login();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        if (Arrays.asList(2, 3, 5, 7).contains(randomNum)) {
            VbucksScam.willWeScam(randomNum);
        } else {
            if (account.getAccountId() == 0) {
                return new CreateVbucksAccountView(account.getAccountId(), account.getPlayerId());
            } else {
                return new PlayerMenu(account.getAccountId(), account.getPlayerId());
            }
        }
        return null;
    }

    public Account login() throws SQLException {
        Console console = System.console();

        System.out.println("\nEnter username");
        String username = console.readLine();

        while (true) {
            boolean usernameExists = playerDao.checkPlayerExists(username);
            if (!usernameExists) {
                System.out.println("\nWrong username you noob! Try again");
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
            passwordVerified = verifyPassword(password, hashedPassword, salt);
            if (passwordVerified) {
                break;
            }
            System.out.println("\nIncorrect password you noob! Try again");
        }
        Player player = playerDao.createPlayerClass(username);
        Account account = successfulLogin(player);
        return account;
    }

    public static Account successfulLogin(Player player) {
        System.out.println("Welcome " + player.getFirstName() + "\n");
        List<Integer> accounts = PlayerDao.printPlayerAccountInfo(player.getPlayerId());
        Account account = ScannerUtil.selectAccount(player, accounts);
        return account;
    }
}