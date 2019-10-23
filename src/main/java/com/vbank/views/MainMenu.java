package com.vbank.views;

import com.vbank.util.ScannerUtil;


public class MainMenu implements View {

    public void printMenu() {
        System.out.println("1. LOGIN");
        System.out.println("2. CREATE PLAYER ACCOUNT");
        System.out.println("0. QUIT");
    }

    public View process() {
        printMenu();
        int selection = ScannerUtil.getInput(2);

        switch(selection) {
            case 0: return null;
            case 1: return new LoginView();
            case 2: return new CreatePlayerView();
            default: return null;
        }
    }
}