package user;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import pool.Card;
import pool.Pool;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    // ##### Test @Data - Begin #####
    @Test
    void getSetUsernameTest(){
        User me = new User("mariel", "111111");
        me.setUsername("mariel23");
        String expectedUsr = "mariel23";

        assertTrue(me.getUsername().equals(expectedUsr), "Setter/Getter error in username variable!");
    }

    @Test
    void getSetPasswordTest(){
        User me = new User("mariel", "111111");
        me.setPass("222222");
        String expectedPass = "222222";

        assertTrue(me.getPass().equals(expectedPass), "Setter/Getter error in password variable!");
    }

    @Test
    void eloShouldBe100(){
        User me = new User("mariel", "111111");
        int expectedElo = me.getElo();

        assertTrue(expectedElo == 100, "Setter/Getter error in elo variable!");
    }

    @Test
    void coinsShouldBe20(){
        User me = new User("mariel", "111111");
        int expectedCoins = me.getCoins();

        assertTrue(expectedCoins == 20, "Setter/Getter error in elo variable!");
    }
    // ##### Test @Data - End #####

    @Test
    void buyPackageTest(){
        Pool p = new Pool("pool_of_all_cards.txt");
        User me = new User("mariel", "111111");

        me.buyPackage(p);

        assertTrue(p.getCardPackage().toString().equals(me.getUserStack().getStack().toString())
                && me.getCoins() == 15, "Error");
    }
}