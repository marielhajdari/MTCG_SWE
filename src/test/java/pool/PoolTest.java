package pool;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class PoolTest {

    @Test
    void showPoolTest() {
        Pool p = new Pool("pool_of_all_cards.txt");
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
        Pool p = new Pool("pool_of_all_cards.txt");
        p.createCardPackage();
        int length = p.getCardPackage().size();

        assertTrue(length == 5, "Wrong card package size!");
    }

    @Test
    void emptyCardPackageTest() {
        Pool p = new Pool("pool_of_all_cards.txt");
        p.createCardPackage();
        p.emptyCardPackage();
        int size = p.getCardPackage().size();

        assertTrue(size == 0, "Can not empty Package!");
    }

    @Test
    void showCardPackageTest() {
        Pool p = new Pool("pool_of_all_cards.txt");
        p.createCardPackage();
        int length = p.getCardPackage().size();
        p.showCardPackage();

        assertTrue(length == 5, "Can not show package!");
    }

    @Test
    void returnElementTypeShouldBeFireTest(){
        String input = "Fire";
        Pool p = new Pool("pool_of_all_cards.txt");
        String shouldBeFire = p.returnElementType(input);

        assertTrue( shouldBeFire.equals("Fire"), "Element type should be fire!");
    }

    @Test
    void returnElementTypeShouldBeWaterTest(){
        String input = "Water";
        Pool p = new Pool("pool_of_all_cards.txt");
        String shouldBeWater = p.returnElementType(input);

        assertTrue( shouldBeWater.equals("Water"), "Element type should be water!");
    }

    @Test
    void returnElementTypeShouldBeNormalTest(){
        String input = "Normal";
        Pool p = new Pool("pool_of_all_cards.txt");
        String shouldBeNormal = p.returnElementType(input);

        assertTrue( shouldBeNormal.equals("Normal"), "Element type should be normal!");
    }

    @Test
    void returnElementTypeShouldBeNoneTest(){
        String input = "Fir";
        Pool p = new Pool("pool_of_all_cards.txt");
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
    /*
    @Test
    public void bufferedReaderCannotFileTest() throws IOException{
        Pool p = new Pool("wrongName.txt");
        assertThrows(IOException.class, () -> new Pool("wrongName.txt"));
    }
    */

    @Test
    void nameShouldBeKnight(){
        String nameKnight = MonsterType.Knight.toString();
        Pool p = new Pool("pool_of_all_cards.txt");
        //System.out.println(p.getListOfMonsters().get(0));
        Monster name = p.getListOfMonsters().get(0);
        String cardName = name.getName();

        assertTrue(cardName.equals(nameKnight), "Name should be Knight.");
    }

    @Test
    void getListOfMonstersTest(){
        Pool p = new Pool("pool_of_all_cards.txt");
        Monster[] monsterL = p.getListOfMonsters().toArray(new Monster[0]);

        assertTrue(p.getListOfMonsters().size() == monsterL.length, "Error in getListOfMonsters method!");
    }

    @Test
    void getListOfSpellsTest(){
        Pool p = new Pool("pool_of_all_cards.txt");
        Spell[] spellL = p.getListOfSpells().toArray(new Spell[0]);

        assertTrue(p.getListOfSpells().size() == spellL.length, "Error in getListOfSpells method!");
    }

    @Test
    void getCardPackageTest(){
        Pool p = new Pool("pool_of_all_cards.txt");
        Card[] cardPack = p.getCardPackage().toArray(new Card[0]);

        assertTrue(p.getCardPackage().size() == cardPack.length, "Error in getCardPackage method!");
    }

    @Test
    void cardInsTest(){ // card instanz test
        Card c = Card.builder()
                .name("Knight")
                .elementType("Water")
                .damage(70)
                .build();
        assertTrue(c.getName().equals(MonsterType.Knight.toString())
                && c.getDamage() == 70
                && c.getElementType().equals(SpellType.Water.toString()), "Card Instanz fehler!");
    }

    @Mock Pool p;

    /*
    @Test(expected = FileNotFoundException.class)
    void catchIOExceptionTest() throws FileNotFoundException{
        p = new Pool("wrongName.txt");
    }
    */
}