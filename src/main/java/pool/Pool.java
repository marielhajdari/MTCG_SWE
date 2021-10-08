package pool;

import java.util.LinkedList;
import java.util.Random;


public class Pool {
    private LinkedList<Monster> ListOfMonsters = new LinkedList<Monster>();
    private LinkedList<Spell> ListOfSpells = new LinkedList<Spell>();
    private LinkedList<Card> cardPackage = new LinkedList<Card>();

    public Pool() {
        for (int i = 0; i < 5; i++){
            ListOfMonsters.add(randomMonster());
        }
        for (int i = 0; i < 5; i++){
            ListOfSpells.add(randomSpell());
        }
    }

    private Spell randomSpell(){
        return Spell.SpellBuilder()
                .name("Spell"+String.valueOf(SpellType.Water))
                .damage(20)
                .elementType(String.valueOf(SpellType.Water))
                .build();
    }

    private Monster randomMonster(){
        return Monster.MonsterBuilder()
                .name(String.valueOf(MonsterType.Knight))
                .damage(40)
                .elementType(String.valueOf(SpellType.Fire))
                .build();
    }

    public void showCollection(){
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
        showCardPackage();
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
