package blackjack.logic;

import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck(){
        for(Card.Suit s: Card.Suit.values()){
            for(Card.Rank r: Card.Rank.values()){
                cards.add(new Card(s, r));
            }
        }
        shuffle();
    }

    public void shuffle(){ Collections.shuffle(cards); }

    public Card draw(){ return cards.remove(cards.size()-1); }
}
