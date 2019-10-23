package com.vbank.views;

import com.vbank.dao.VbucksAccountDao;
import com.vbank.util.ScannerUtil;


public class CreateVbucksAccountView implements View {
    private int accountId;
    private int playerId;

    public CreateVbucksAccountView(int accountId, int playerId) {
        super();
        this.accountId = accountId;
        this.playerId = playerId;
    }

    private void printMenu() {
        System.out.println("----- ACCOUNT TYPE -----");
        System.out.println("1. CHECKING");
        System.out.println("2. SAVINGS");
        System.out.println("0. BACK");
    }

    @Override
    public View process() {
        System.out.println("What kind of account would you like to create?");

        printMenu();
        int selection = ScannerUtil.getInput(2);

        switch (selection) {
            case 1:
                accountId = VbucksAccountDao.createVbucksAccount(playerId, "Checking");
                System.out.println("Account created successfully!\n");
                return new PlayerMenu(accountId, playerId);
            case 2:
                accountId = VbucksAccountDao.createVbucksAccount(playerId, "Saving");
                System.out.println("Account created successfully!\n");
                return new PlayerMenu(accountId, playerId);
            case 0:
                return new PlayerMenu(accountId, playerId);
            default:
                return null;
        }
    }
}