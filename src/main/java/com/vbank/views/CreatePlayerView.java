package com.vbank.views;

import java.io.Console;

import com.vbank.dao.PlayerDao;
import com.vbank.models.Player;
import com.vbank.services.PasswordHashing;

public class CreatePlayerView extends PasswordHashing implements View {

    private PlayerDao playerDao = new PlayerDao();

    public int createPlayer() {
        Console console = System.console();

        System.out.println("\nEnter first name");
        String firstName = console.readLine();

        System.out.println("\nEnter username");
        String username = console.readLine();

        while (true) {
            boolean usernameExists = playerDao.checkPlayerExists(username);    
            if (usernameExists) {
                System.out.println("\nUsername's been taken, you noob!\nTry again!");
                username = console.readLine();
            } else {
                break;
            }
        }

        System.out.println("\nEnter password");
        char[] password = console.readPassword();

        String salt = generateSalt(512).get();
        String hashedPassword = hashPassword(password, salt).get();

        playerDao.createDBPlayer(firstName, username, hashedPassword, salt);
        Player player = playerDao.createPlayerClass(username);
        PlayerDao.printPlayerAccountInfo(player.getPlayerId());
        return player.getPlayerId();
    }

    @Override
    public View process() {
        int playerId = createPlayer();
        return new CreateVbucksAccountView(0, playerId);
    }   
}