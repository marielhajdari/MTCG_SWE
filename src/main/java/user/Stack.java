package user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Stack {
    private int cardID;
    boolean isDeck;

    public void manageDeck(){}

}
