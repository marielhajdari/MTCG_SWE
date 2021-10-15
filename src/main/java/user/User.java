package user;

import lombok.Builder;
import lombok.Data;
import pool.Card;
import pool.Pool;

import java.io.*;
import java.util.LinkedList;

@Data
public class User {
    private String username;
    private String pass;
    private int elo;
    private int coins;
    private Stack userStack = new Stack();

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

    public void buyPackage(Pool inputPool){
        createFile();
        inputPool.createCardPackage();
        for (int i = 0; i < 5 ; i++){
            insertIntoStack(inputPool.getCardPackage().get(i));
        }
        addHistory(inputPool.getCardPackage());
        coins-=5;
    }

    private void insertIntoStack(Card inputCard){
        userStack.getStack().add(inputCard);
    }

    private void createFile(){
        try {
            File myFile = new File("./userPurchases/" + username + "History.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void addHistory(LinkedList<Card> inputList){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./userPurchases/" + username + "History.txt")));
            writer.write(inputList.toString() + System.lineSeparator());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showPurchaseHistory(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("./userPurchases/" + username + "History.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setTradeRequest(){}

    public void acceptTradeMarket(){}
}
