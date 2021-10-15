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
            addHistory(inputPool.getCardPackage().get(i));
        }
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

    private void addHistory(Card inputCard){
        File file = new File("./userPurchases/" + username + "History.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        PrintWriter pr = null;
        try {
            // to append to file, you need to initialize FileWriter using below constructor
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            pr = new PrintWriter(br);
            pr.println(inputCard.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pr.close();
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void setTradeRequest(){
        if (userStack.getStack() == null){
            System.out.println("Your Stack is empty, therefore you cannot set a trade offer on the market!");
            System.out.println("Buy a card package, to be able to set a trade offer!");
        } else {

        }
    }

    public void possibleTradeCards(){
        int indexCards = 1;
        for (Card inStack: userStack.getStack()) {
            System.out.println("Card Number: " + indexCards + inStack);
            indexCards++;
        }
    }

    public void acceptTradeMarket(){}
}
