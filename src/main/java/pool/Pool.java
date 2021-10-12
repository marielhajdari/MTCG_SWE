package pool;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Getter
public class Pool {
    private LinkedList<Monster> ListOfMonsters = new LinkedList<Monster>();
    private LinkedList<Spell> ListOfSpells = new LinkedList<Spell>();
    private LinkedList<Card> cardPackage = new LinkedList<Card>();

    public Pool(String inputFile) {
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                addToPool(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        /*for (int i = 0; i < 5; i++){
            ListOfMonsters.add(randomMonster());
        }
        for (int i = 0; i < 5; i++){
            ListOfSpells.add(randomSpell());
        }*/
    }

    private void addToPool(String inputLine){
        if (inputLine != null){
            String[] result = inputLine.split(";");
            if (result[0].equals("M")){
                //System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3] + "!");
                ListOfMonsters.add(Monster.MonsterBuilder()
                        .name(result[2])
                        .damage(parseInt(result[3]))
                        .elementType(returnElementType(result[1]))
                        .build());
            } else {
                //System.out.println(result[0] + " " + result[1] + " " + result[2] + "?");
                ListOfSpells.add(Spell.SpellBuilder()
                        .name("Spell" + result[1])
                        .damage(parseInt(result[2]))
                        .elementType(returnElementType(result[1]))
                        .build());
            }
        }
    }

    public String returnElementType(String inputLine){
        switch (inputLine){
            case "Fire" : return SpellType.Fire.toString();
            case "Water" : return SpellType.Water.toString();
            case "Normal" : return SpellType.Normal.toString();
        }
        return "None";
    }

    public void showPool(){
        for (int i = 0; i < ListOfSpells.size(); i++){
            System.out.println(ListOfSpells.get(i));
        }
        for (int i = 0; i < ListOfMonsters.size(); i++){
            System.out.println(ListOfMonsters.get(i));
        }
    }

    public void createCardPackage(){
        Random rand = new Random();
        for (int i = 0; i < 5; i++){
            if (rand.nextInt(2) == 0){
                cardPackage.add(ListOfMonsters.get(rand.nextInt(ListOfMonsters.size())));
            }else {
                cardPackage.add(ListOfSpells.get(rand.nextInt(ListOfSpells.size())));
            }
        }
    }

    public void emptyCardPackage(){
        cardPackage.clear();
    }

    public void showCardPackage(){
        for (int i = 0; i < cardPackage.size() ; i++){
            System.out.println(cardPackage.get(i));
        }
        emptyCardPackage();
    }
}
