package pool;


public class PoolDemo {
    public static void main(String[] args) {
        Pool c = new Pool();
        c.showPool();
        System.out.println("Hello");
        System.out.println("");
        c.createCardPackage();

        System.out.println("2");
        c.createCardPackage();
        System.out.println("3");
        c.createCardPackage();

        System.out.println();
    }
}
