package com.vbank.views;

import com.vbank.services.VBankService;
import com.vbank.util.ScannerUtil;

public class PlayerMenu implements View {
    private int accountId;
    private int playerId;

    // private PlayerDao playerDao = new PlayerDao();

    public PlayerMenu(int accountId, int playerId) {
        super();
        this.accountId = accountId;
        this.playerId = playerId;
    }

    private static void printMenu(int accountId) {
        System.out.println("----- PLAYER MENU [ACCT "+accountId+"] -----");
        System.out.println("1. DEPOSIT VBUCKS");
        System.out.println("2. WITHDRAW VBUCKS");
        System.out.println("3. GIFT VBUCKS");
        System.out.println("4. ADD PLAYER TO ACCOUNT (CREATE JOINT ACCOUNT)");
        System.out.println("5. CLOSE ACCOUNT");
        System.out.println("0. LOGOUT");
    }
    
	@Override
	public View process() {
        if (accountId == 0) {
            System.out.println("Can't manipulate a nonexistent account you noob!\nTry again!");
            return null;
        }
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
    
    // private void createPlayer() {
    //     System.out.println("Enter first name: ");
    //     String firstName = ScannerUtil.getStringInput();
        
    //     System.out.println("Enter salary: ");
    //     BigDecimal salary = new BigDecimal(ScannerUtil.getInput(10000.0));

    //     System.out.println("Enter team id: ");
    //     int teamId = ScannerUtil.getInput(6);
        
    //     Player user = new Player(0, firstName, null, salary, teamId);
    // }

    // public void viewAllPlayers() {
    //     // call dao method to get user
    //     List<Player> users = userDao.getAllPlayers();
    //     // print users
    //     printPlayerList(users);
    // }

    // public void getPlayersByFirstName() {
    //     System.out.println("Type user name: ");
    //     String firstName = ScannerUtil.getStringInput();
    //     List<Player> users = userDao.getPlayersByFirstName(firstName);
    //     printPlayerList(users);
    // }

    // private void printPlayerList(List<Player> users) {
    //     System.out.println("--------------------------- Players ---------------------------");
    //     System.out.println("| id |   first name   |  salary  |  start date  |  team_id  |");
    //     for (Player user : users) {
    //         System.out.printf("| %2d | %-14s | %8s | %12s | %9d |%n", user.getId(), user.getFirstName(), user.getSalary(), user.getStartDate(), user.getTeamId());
    //     }
    //     System.out.println("-------------------------------------------------------------");
    // }
}