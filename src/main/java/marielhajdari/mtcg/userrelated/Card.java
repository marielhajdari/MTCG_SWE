package marielhajdari.mtcg.userrelated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    private String name;
    private int damage;
    private String elementType;

}
