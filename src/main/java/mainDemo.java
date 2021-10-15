import pool.Pool;
import user.User;

public class mainDemo {
    public static void main(String[] args) {
        Pool p = new Pool("pool_of_all_cards.txt");
        User me = new User("mariel", "112233");
        me.setTradeRequest();
        me.buyPackage(p);
        me.setTradeRequest();

    }
}
