package com.vbank.services;

import java.util.Scanner;

import com.vbank.dao.PlayerDao;
import com.vbank.dao.VbucksAccountDao;

public class VBankService {
    private static String wholeNumbersInsult = "\nVbucks are whole numbers, you noob! (1, 2, 3, 4, 5, 6, 7, 8, 9, 0)\nTry again!";
    private static String notEnoughVbucksInsult = "\nYou don't have enough Vbucks you noob!\nTry again!";

    static Scanner scanner = new Scanner(System.in);

    public static void depositVbucks(int playerId, int accountId) {
        int amount = -1;
        System.out.println("\nPlease enter a valid Vbucks amount to deposit\n");
        while (amount < 0) {
            if (!scanner.hasNextInt()) {
                System.out.println(wholeNumbersInsult);
                scanner.nextLine();
                continue;
            }
            amount = scanner.nextInt();
        }
        VbucksAccountDao.depositVbucks(amount, playerId, accountId);
    }

    public static void withdrawVbucks(int playerId, int accountId) {
        int amount = -1;
        System.out.println("\nPlease enter a valid Vbucks amount to withdraw\n");
        while (amount < 0) {
            if (!scanner.hasNextInt()) {
                System.out.println(wholeNumbersInsult);
                scanner.nextLine();
                continue;
            }
            amount = scanner.nextInt();
            boolean validAmount = VbucksAccountDao.checkVbucksAmount(amount, accountId);
            if (!validAmount) {
                System.out.println(notEnoughVbucksInsult);
                amount = -1;
                continue;
            }
        }
        VbucksAccountDao.withdrawVbucks(amount, playerId, accountId);
    }

    public static void giftVbucks(int playerId, int gifterAccount) {
        int amount = -1;
        System.out.println("\nPlease enter a valid Vbucks amount to gift\n");
        while (amount < 0) {
            if (!scanner.hasNextInt()) {
                System.out.println(wholeNumbersInsult);
                scanner.nextLine();
                continue;
            }
            amount = scanner.nextInt();
            boolean validAmount = VbucksAccountDao.checkVbucksAmount(amount, gifterAccount);
            if (!validAmount) {
                System.out.println(notEnoughVbucksInsult);
                amount = -1;
                continue;
            }
        }
        int giftedAccount = -1;
        System.out.println("\nPlease select a valid Vbucks account to receive your generous gift\n");
        while (giftedAccount < 0) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            giftedAccount = scanner.nextInt();
            boolean accountExists = VbucksAccountDao.checkVbucksAccountExists(giftedAccount);
            if (!accountExists) {
                System.out.println("\nThat account doesn't exist you noob!\nTry again!");
                scanner.nextLine();
                continue;
            }
        }
        VbucksAccountDao.giftVbucks(amount, gifterAccount, giftedAccount, playerId);
    }

    public static void createJointAccount(int accountId) {
        int jointAccount = -1;
        System.out.println("\nPlease enter a valid player account to link\n");
        while (jointAccount < 0) {
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid account, you noob! Try again!");
                scanner.nextLine();
                continue;
            }
            jointAccount = scanner.nextInt();
            boolean jointAccountExists = PlayerDao.checkPlayerExists(jointAccount);
            if (!jointAccountExists) {
                System.out.println("\nThat account doesn't exist, you noob! And no, I'm not gonna give you options to choose from! #securitythreats #noob\nTry again!!");
                scanner.nextLine();
                jointAccount = -1;
                continue;
            }
        }
        VbucksAccountDao.createJointAccount(accountId, jointAccount);
    }

    public static void closeAccount(int accountId) {
        String confirmClose = "";
        System.out.println("\nAre you sure you wanna close your account? (y/n)\nYour Vbucks will be returned to you (hehe)\n");
        while (confirmClose == "") {
            if (!scanner.hasNext()) {
                System.out.println("Yes or no you noob!");
                continue;
            }
            confirmClose = scanner.nextLine();
            if (confirmClose.equals("y")) {
                VbucksAccountDao.closeAccount(accountId);
            } else if (confirmClose.equals("n")) {
                break;
            } else {
                System.out.println("\nInvalid option you noob!\nTry again!");
                confirmClose = "";
            }
        }
    }
}