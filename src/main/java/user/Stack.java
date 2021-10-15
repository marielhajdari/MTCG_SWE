package user;

import lombok.Builder;
import lombok.Data;
import pool.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class Stack {
    private LinkedList<Card> stack = new LinkedList<>();

    public Stack(){
        // do nothing
    }

    public void manageDeck(){}
}
