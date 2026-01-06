package blackjack.logic;

import java.util.*;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card c){ cards.add(c); }

    public int getValue(){
        int val = 0, aces = 0;
        for(Card c: cards){
            val += c.getRank().getValue();
            if(c.getRank() == Card.Rank.A) aces++;
        }
        while(val>21 && aces>0){ val -=10; aces--; }
        return val;
    }

    public List<Card> getCards(){ return cards; }
}
