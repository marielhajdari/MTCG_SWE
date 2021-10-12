package pool;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class PoolTest {

    @Test
    void showPoolTest() {
        Pool p = new Pool();
        p.showPool();
        int size = p.getListOfMonsters().size() + p.getListOfSpells().size();
        int countLinesPool = 0;
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("pool_of_all_cards.txt"));
            String line = reader.readLine();
            while (line != null) {
                countLinesPool++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        assertTrue(size == countLinesPool, "Bad input in pool!");
    }

    @Test
    void createCardPackageTest() {
        Pool p = new Pool();
        p.createCardPackage();
        int length = p.getCardPackage().size();

        assertTrue(length == 5, "Wrong card package size!");
    }

    @Test
    void emptyCardPackageTest() {
        Pool p = new Pool();
        p.createCardPackage();
        p.emptyCardPackage();
        int size = p.getCardPackage().size();

        assertTrue(size == 0, "Can not empty Package!");
    }

    @Test
    void showCardPackageTest() {
        Pool p = new Pool();
        p.createCardPackage();
        int length = p.getCardPackage().size();
        p.showCardPackage();

        assertTrue(length == 5, "Can not show package!");
    }

    @Test
    void returnElementTypeShouldBeFireTest(){
        String input = "Fire";
        Pool p = new Pool();
        String shouldBeFire = p.returnElementType(input);

        assertTrue( shouldBeFire.equals("Fire"), "Element type should be fire!");
    }

    @Test
    void returnElementTypeShouldBeWaterTest(){
        String input = "Water";
        Pool p = new Pool();
        String shouldBeWater = p.returnElementType(input);

        assertTrue( shouldBeWater.equals("Water"), "Element type should be water!");
    }

    @Test
    void returnElementTypeShouldBeNormalTest(){
        String input = "Normal";
        Pool p = new Pool();
        String shouldBeNormal = p.returnElementType(input);

        assertTrue( shouldBeNormal.equals("Normal"), "Element type should be normal!");
    }

    @Test
    void returnElementTypeShouldBeNoneTest(){
        String input = "Fir";
        Pool p = new Pool();
        String shouldBeNone = p.returnElementType(input);

        assertTrue( shouldBeNone.equals("None"), "Element type should be None!");
    }

    @Test
    void bufferedReaderTest(){
        String input = "M;Fire;Knight;50;";
        String readFile;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("pool_of_all_cards.txt"));
            readFile = reader.readLine();

            assertEquals(readFile, input, "File can not be read!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bufferedReaderCannotFileTest(){

    }
}