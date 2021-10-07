package pool;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Card {
    private String name;
    private int damage;
    private String elementType;

}
