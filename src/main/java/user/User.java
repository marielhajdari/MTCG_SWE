package user;

import lombok.Builder;
import lombok.Data;
import pool.Card;
import pool.Pool;

import java.io.*;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

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
        if (userStack.getStack().isEmpty()){
            System.out.println("Your Stack is empty, therefore you cannot set a trade offer on the market!");
            System.out.println("Buy a card package, to be able to set a trade offer!");
        } else {
            System.out.println("######################################################");
            possibleTradeCards();
            System.out.println("######################################################");
            Scanner cardToTrade = new Scanner(System.in);
            System.out.println("Type the number of the cards you want to trade: ");
            int cardNumber = cardToTrade.nextInt();

            // Here should be a connection to the server,
            // in order to save this card in the market(which will show on server)
            // till server is complete a printLine will suffice
            System.out.println("Card: " + userStack.getStack().get(cardNumber-1) + "is set on trade market");
        }
    }

    public void possibleTradeCards(){
        int indexCards = 1;
        for (Card inStack: userStack.getStack()) {
            System.out.println("Card Number: " + indexCards + "\t" + inStack);
            indexCards++;
        }
    }

    public void acceptTradeMarket(){}
}
