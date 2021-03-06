package stack;

import cards.Card;
import cards.MonsterCard;
import cards.SpellCard;
import server.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> deck=new ArrayList(4);
    public void createCards(int userID, String payload, DatabaseHandler dbHandler) throws SQLException {
        if (deck.size()==4){
            System.out.println("Deck already full!");
            return;
        }
        String regex="(?<=\"), (?=\")";
        String cards[]=payload.split(regex);
        if (cards.length<4){
            System.out.println("Not enough cards");
            return;
        }
        cards[0]=cards[0].replace("[","");
        cards[cards.length-1]=cards[cards.length-1].replace("]","");
        String selectSql="SELECT cardserialid, cardmonster, carddamage, cardname" +
                " FROM \"MonsterCardGame\".\"card\" as c " +
                "join \"MonsterCardGame\".\"package\" as p " +
                "on c.\"packageid\"=p.\"packageid\" " +
                "join \"MonsterCardGame\".\"user\" as u " +
                "on u.\"userid\"=p.\"userid\" "+
                " where cardserialid=? AND u.\"userid\"="+userID;
        for (int i=0;i<cards.length;i++){
            cards[i]=cards[i].replace("\"","");
            PreparedStatement preparedStatement=dbHandler.getConnection().prepareStatement(selectSql);
            preparedStatement.setString(1,cards[i]);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int cardDmg=resultSet.getInt("carddamage");
                String cardName=resultSet.getString("cardname");
                String monsterType=resultSet.getString("cardmonster");
                String cardSerialID=resultSet.getString("cardserialid");
                if(monsterType.compareTo("NONE")==0){
                    deck.add(new SpellCard(cardName,cardDmg,cardSerialID));
                }
                else {
                    deck.add(new MonsterCard(cardName,cardDmg,cardSerialID));
                }

            }
        }
    }

    public void autoGenerateCards(DatabaseHandler dbHandler,int userID) throws SQLException {
        String selectSql="SELECT cardserialid, cardmonster, carddamage, cardname" +
                " FROM \"MonsterCardGame\".\"card\" as c " +
                "join \"MonsterCardGame\".\"package\" as p " +
                "on c.\"packageid\"=p.\"packageid\" " +
                "join \"MonsterCardGame\".\"user\" as u " +
                "on u.\"userid\"=p.\"userid\" "+
                " where u.\"userid\"=? LIMIT 4";
        PreparedStatement preparedStatement=dbHandler.getConnection().prepareStatement(selectSql);
        preparedStatement.setInt(1,userID);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()) {
            int cardDmg = resultSet.getInt("carddamage");
            String cardName = resultSet.getString("cardname");
            String monsterType = resultSet.getString("cardmonster");
            String cardSerialID = resultSet.getString("cardserialid");
            if (monsterType.compareTo("NONE") == 0) {
                deck.add(new SpellCard(cardName, cardDmg, cardSerialID));
            } else {
                deck.add(new MonsterCard(cardName, cardDmg, cardSerialID));
            }
        }
    }

    public void printDeck(){
        for (int i=0;i<deck.size();i++){
            System.out.println("Card number "+i+" Name: "+deck.get(i).getName()+" Damage: "+deck.get(i).getDamage()
                    +" Attribute: "+deck.get(i).getAttribute()+" MonsterType: "+deck.get(i).getMonsterType());
        }
    }

    public Card getCard(int index){
        Card card;
        if (deck.get(index)==null){
            System.out.println("Logical error");
        }
        card = deck.get(index);
        return card;
    }

    public void addCard(Card card){
        deck.add(card);
    }

    public void removeCard(Card card){
        deck.remove(card);
    }

    public boolean isEmpty(){
        if (deck.isEmpty()){
            return true;
        }
        return false;
    }

    public int getLength(){
        return deck.size();
    }

}
