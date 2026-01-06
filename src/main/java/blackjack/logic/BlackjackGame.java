package blackjack.logic;

public class BlackjackGame {
    private final Deck deck;
    private final Hand playerHand;
    private final Hand dealerHand;
    private boolean playerTurn = true;

    public BlackjackGame(){
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();

        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());
    }

    public Hand getPlayerHand(){ return playerHand; }
    public Hand getDealerHand(){ return dealerHand; }
    public Deck getDeck(){ return deck; }

    public boolean isPlayerTurn(){ return playerTurn; }

    public void playerHit(){ playerHand.add(deck.draw()); }

    public void playerStand(){
        playerTurn = false;
        while(dealerHand.getValue() < 17){
            dealerHand.add(deck.draw());
        }
    }

    public String getResult(){
        int p = playerHand.getValue();
        int d = dealerHand.getValue();

        if(p>21) return "Player busts! Dealer wins";
        if(d>21) return "Dealer busts! Player wins";
        if(!playerTurn){
            if(p>d) return "Player wins";
            if(d>p) return "Dealer wins";
            return "Draw";
        }
        return "Game ongoing";
    }
}
