package com.vbank;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import com.vbank.dao.VbucksAccountDao;
import com.vbank.models.Player;
import com.vbank.models.PlayerAccount;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    VbucksAccountDao vbucksAccountDao = Mockito.mock(VbucksAccountDao.class);
    static Player player1 = new Player();
    static Player player2 = new Player();
    static PlayerAccount playerAccount1 = new PlayerAccount();
    static PlayerAccount playerAccount2 = new PlayerAccount();

    @Before
    public void setup() {
        player1.setPlayerId(100);
        player1.setFirstName("Andy");
        player1.setUsername("nardDog");

        player1.setPlayerId(200);
        player1.setFirstName("Kevin");
        player1.setUsername("cookies");

        playerAccount1.setAccountId(100);
        playerAccount1.setPlayerId(100);
        playerAccount1.setAccountType("Checking");
        playerAccount1.setBalance(0);
        playerAccount1.setDateCreated(LocalDate.now());
        playerAccount1.setFirstName("Andy");
        playerAccount1.setUsername("nardDog");
        playerAccount1.setStatus("Open");

        playerAccount2.setAccountId(200);
        playerAccount2.setPlayerId(200);
        playerAccount2.setAccountType("Savings");
        playerAccount2.setBalance(199990);
        playerAccount2.setDateCreated(LocalDate.now());
        playerAccount2.setFirstName("Kevin");
        playerAccount2.setUsername("cookies");
        playerAccount2.setStatus("Open");
    }

    @Test
    public void testDepositVbucks() {
        int deposit = 5500;

        playerAccount1.setBalance(deposit);

        Mockito.verify(vbucksAccountDao).depositVbucks(deposit, playerAccount1.getPlayerId(), playerAccount1.getAccountId());

        assertEquals("New balance should be 5500V", 5500, playerAccount1.getBalance());
    }

    @Test
    public void testWithdrawVbucks() {
        int withdrawal = 500;

        playerAccount2.setBalance(playerAccount2.getBalance() - withdrawal);

        Mockito.verify(vbucksAccountDao).withdrawVbucks(withdrawal, playerAccount2.getPlayerId(), playerAccount2.getAccountId());

        assertEquals("New balance should be 199490V", 199490, playerAccount2.getBalance());
    }

    @Test
    public void testWithdrawVbucksFail() {
        int withdrawal = 100000;

        Mockito.when(vbucksAccountDao.checkVbucksAmount(withdrawal, playerAccount1.getAccountId())).thenReturn(false);

        Mockito.verify(vbucksAccountDao).withdrawVbucks(withdrawal, playerAccount1.getPlayerId(), playerAccount1.getAccountId());

        assertEquals("Should be false", false, false);
    }

    @Test
    public void testGiftVbucks() {
        int gift = 500;

        playerAccount1.setBalance(playerAccount1.getBalance() - gift);
        playerAccount2.setBalance(playerAccount2.getBalance() + gift);

        Mockito.verify(vbucksAccountDao).giftVbucks(gift, playerAccount1.getAccountId(), playerAccount2.getAccountId(), playerAccount1.getPlayerId());

        assertEquals("Your new balance should be 200490V", 200490, playerAccount2.getBalance());
    }
}
