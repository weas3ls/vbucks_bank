package com.vbank;

import java.sql.SQLException;

import com.vbank.views.MainMenu;
import com.vbank.views.View;

public final class App {
    public static void main(String[] args) throws SQLException {
        String fortnite_ascii = 
        "████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
        "████            ████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
        "████            ████      ██████            ████        ████████    ████      ██████████████████████████            ████\n" +
        "████            ██            ██              ██                    ████      ██                      ██            ████\n" +
        "████      ██████              ██                                    ████      ██                      ██            ████\n" +
        "████      ██████      ██      ██        ██      ████                  ██      ██                      ██        ████████\n" +
        "████      ██████      ██      ██        ██      ████      ████        ██      ██        ██        ██████        ████████\n" +
        "████          ██      ██                ██      ████      ████                ██        ████      ██████            ████\n" +
        "████          ██      ██                ██      ████      ████                ██        ████      ██████            ████\n" +
        "████          ██      ██                      ██████      ████                ██        ████      ██████            ████\n" +
        "████      ██████      ██                      ██████      ████                ██        ████      ██████        ████████\n" +
        "████      ██████      ██      ██              ██████      ████      ▓▓        ██        ████      ██████        ████████\n" +
        "████      ██████      ██      ██        ██    ██████      ████      ██        ██        ████      ██████        ████████\n" +
        "████      ████████            ██        ██      ████      ████      ████      ██        ████      ██████            ████\n" +
        "████      ████████          ████        ██      ████      ████      ████      ██        ████      ██████            ████\n" +
        "████      ██████████████████████████████████████████████████████████████████████        ████      ██████            ████\n" +
        "████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████";
        System.out.println(fortnite_ascii);
        View currentView = new MainMenu();
        while (currentView != null) {
            currentView = currentView.process();
        }
        System.out.println("Thank you for banking with VBUCKS!");
    }
}