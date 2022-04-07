package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHasherTest {
    @Test
    public void testHashGenerator() throws InvalidKeySpecException, NoSuchAlgorithmException {
        //Arrange
        PasswordHasher passwordHasher=new PasswordHasher();
        String password="istraitor";
        //Act
        String hash=passwordHasher.generateStrongPasswordHash(password);
        boolean check=passwordHasher.validatePassword("istraitor",hash);
        //Assert
        Assertions.assertEquals(true,check);
    }
}