package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHandlerTest {
    @Test
    public void testUserCreation() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        //Arrange
        DatabaseHandler dbHandler=new DatabaseHandler();
        String username="test";
        String password="test";
        String actual="";
        //Act
        dbHandler.createUser(username,password);
        String selectStatement="Select \"username\" from \"MonsterCardGame\".\"user\" ";
        Statement stmt=dbHandler.getConnection().createStatement();
        ResultSet resultSet=stmt.executeQuery(selectStatement);
        while (resultSet.next()){
            actual=resultSet.getString("username");
        }
        String cleanUpStatement="delete from \"MonsterCardGame\".\"user\" where \"username\"=\'test\'";
        stmt.executeUpdate(cleanUpStatement);
        //Assert
        Assertions.assertEquals("test",actual);
    }

}