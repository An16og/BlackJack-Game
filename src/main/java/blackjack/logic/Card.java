package blackjack.logic;

public class Card {
    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        J(10), Q(10), K(10), A(11);

        private final int value;
        Rank(int value){ this.value = value; }
        public int getValue(){ return value; }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){ return suit; }
    public Rank getRank(){ return rank; }
    public String getImageName() {
        String rankStr;
        switch(rank) {
            case TWO: rankStr = "2"; break;
            case THREE: rankStr = "3"; break;
            case FOUR: rankStr = "4"; break;
            case FIVE: rankStr = "5"; break;
            case SIX: rankStr = "6"; break;
            case SEVEN: rankStr = "7"; break;
            case EIGHT: rankStr = "8"; break;
            case NINE: rankStr = "9"; break;
            case TEN: rankStr = "10"; break;
            default: rankStr = rank.toString().toLowerCase(); break; // JACK, QUEEN, KING, ACE
        }
        return suit.toString().toLowerCase() + "_" + rankStr + ".png";
    }


}
