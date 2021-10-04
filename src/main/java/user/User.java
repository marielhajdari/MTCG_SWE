package user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String pass;
    private int elo;
    private int coins;

    public User(String username, String pass){
        this.username = username;
        this.pass = pass;
        elo = 100;
        coins = 20;
    }

    public User(String username, String pass, int elo, int coins) {
        this.username = username;
        this.pass = pass;
        this.elo = elo;
        this.coins = coins;
    }

    public void buyPackage(){}

    public void purchaseHistory(){}

    public void setTradeRequest(){}

    public void acceptTradeMarket(){}
}
