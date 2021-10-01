package marielhajdari.mtcg.userrelated;

import lombok.Builder;
import lombok.Data;

public class Monster extends Card{

    @Builder (builderMethodName = "MonsterBuilder")
    public Monster(String name, int damage, String elementType) {
        super(name, damage, elementType);
    }

}



