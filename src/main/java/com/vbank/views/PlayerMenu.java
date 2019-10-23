package com.vbank.views;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.vbank.services.VBankService;
import com.vbank.services.VbucksScam;
import com.vbank.util.ScannerUtil;

public class PlayerMenu implements View {
    private int accountId;
    private int playerId;

    public PlayerMenu(int accountId, int playerId) {
        super();
        this.accountId = accountId;
        this.playerId = playerId;
    }

    private static void printMenu(int accountId) {
        System.out.println("----- PLAYER MENU [ACCT " + accountId + "] -----");
        System.out.println("1. DEPOSIT VBUCKS");
        System.out.println("2. WITHDRAW VBUCKS");
        System.out.println("3. GIFT VBUCKS");
        System.out.println("4. ADD PLAYER TO ACCOUNT (CREATE JOINT ACCOUNT)");
        System.out.println("5. CLOSE ACCOUNT");
        System.out.println("0. LOGOUT");
    }

    @Override
    public View process() throws SQLException {
        if (accountId == 0) {
            System.out.println("\nCan't manipulate a nonexistent account you noob!\nTry again!");
            return null;
        }
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        if (Arrays.asList(2, 3, 5, 7).contains(randomNum)) {
            VbucksScam.willWeScam(randomNum);
        } else {
            printMenu(accountId);
            int selection = ScannerUtil.getInput(5);

            switch (selection) {
            case 0:
                return null;
            case 1:
                VBankService.depositVbucks(playerId, accountId);
                return this;
            case 2:
                VBankService.withdrawVbucks(playerId, accountId);
                return this;
            case 3:
                VBankService.giftVbucks(playerId, accountId);
                return this;
            case 4:
                VBankService.createJointAccount(accountId);
                return this;
            case 5:
                VBankService.closeAccount(accountId);
            default:
                return null;
            }
        }
        return null;
    }
}