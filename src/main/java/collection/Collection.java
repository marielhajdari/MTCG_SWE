package collection;

import java.util.LinkedList;



public class Collection {
    LinkedList<Monster> ListOfMonsters = new LinkedList<Monster>();
    LinkedList<Spell> ListOfSpells = new LinkedList<Spell>();

    public Collection() {
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
    public void createPackage(){}

}
