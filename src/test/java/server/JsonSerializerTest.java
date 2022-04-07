package server;

import cards.Card;
import enums.elements;
import enums.monsters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {
    @Test
    public void testCardSerialization() throws IOException {
        //Arrange
        JsonSerializer jsonSerializer=new JsonSerializer();
        BufferedReader in= Mockito.mock(BufferedReader.class);
        Mockito.when(in.readLine()).thenReturn("{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}",
                "{\"Id\":\"e85e3976-7c86-4d06-9a80-641c2019a79f\", \"Name\":\"WaterSpell\", \"Damage\": 20.0}");
        //Act
        Card monsterCard=jsonSerializer.convertCardToObject(in.readLine());
        Card spellCard=jsonSerializer.convertCardToObject(in.readLine());
        //Assert
        Assertions.assertEquals(monsters.Goblin,monsterCard.getMonsterType());
        Assertions.assertEquals(elements.Water,monsterCard.getAttribute());
        Assertions.assertEquals(10,monsterCard.getDamage());
        Assertions.assertEquals(elements.Water,spellCard.getAttribute());
        Assertions.assertEquals(20,spellCard.getDamage());
    }

    @Test
    public  void testUserSerialization() throws IOException {
        //Arrange
        JsonSerializer jsonSerializer=new JsonSerializer();
        BufferedReader in= Mockito.mock(BufferedReader.class);
        Mockito.when(in.readLine()).thenReturn("{\"Username\":\"admin\",\"Password\":\"istrator\"}");
        //Act
        User testUser=jsonSerializer.convertUserToObject(in.readLine());
        //Assert
        Assertions.assertEquals("admin",testUser.getUsername());
        Assertions.assertEquals("istrator",testUser.getPassword());
    }
}