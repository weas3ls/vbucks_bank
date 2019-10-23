package com.vbank.util;

import java.util.List;
import java.util.Scanner;

import com.vbank.models.Account;
import com.vbank.models.Player;

/**
 * Helper class to manage the Scanner instance and expose methods that will allow me to abstract the process of getting information from the user
 */

public class ScannerUtil {

    static Scanner scanner = new Scanner(System.in);

    public static int getInput(int max) {
        int input = -1;
        while (input < 0 || input > max) {
            System.out.println("Please make a selection between 0 and " + max);
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            input = scanner.nextInt();
        }
        return input;
    }

	public static String getStringInput() {
        String input = "";
        while (input.isEmpty()) {
            input = scanner.next();
            scanner.nextLine();
        }
        return input;
    }
    
    public static double getInput(double max) {
        double input = -1;
        while (input < 0 || input > max) {
            System.out.println("Please make a selection between 0 and " + max);
            if (!scanner.hasNextDouble()) {
                scanner.nextLine();
                continue;
            }
            input = scanner.nextDouble();
        }
        return input;
    }

    public static Account selectAccount(Player player, List<Integer> accounts) {
        int input = -1;
        System.out.println("Please select a valid Vbucks account " + accounts + " or press 0 to create new account");
        while (input < 0 || !(accounts.contains(input))) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            if (!(accounts.contains(input))) {
                System.out.println("Wrong account number you noob! Try again");
                continue;
            }
        }
        Account accountInstance = new Account(input, player.getPlayerId());
        return accountInstance;
    }
}