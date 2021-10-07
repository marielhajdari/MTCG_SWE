package pool;

import lombok.Builder;

public class Spell extends Card{
    @Builder (builderMethodName = "SpellBuilder")
    public Spell(String name, int damage, String elementType) {
        super(name, damage, elementType);
    }
}
