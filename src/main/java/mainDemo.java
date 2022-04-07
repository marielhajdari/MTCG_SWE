import java.io.IOException;
import java.net.ServerSocket;

public class mainDemo {
    public static ReadRequest reader = new ReadRequest();
    //public static Battlefield battle = new Battlefield();

    static ServerSocket _sSocket = null;
    static final int _port = 10001;

    public static void main(String[] args) throws IOException {
        System.out.println("srv: Starting server...");

        _sSocket = new ServerSocket(_port);
        System.out.println("srv: Server is running in port " + _port);

        /*
        Pool p = new Pool("pool_of_all_cards.txt");
        User me = new User("mariel", "112233");
        me.setTradeRequest();
        me.buyPackage(p);
        me.setTradeRequest();
        System.out.println();
        me.showPurchaseHistory();
         */
    }
}
