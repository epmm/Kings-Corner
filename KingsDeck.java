package cards;
/**
 * A deck of playing cards.
 */
public class KingsDeck extends KingsHand {

    /**
     * Constructs a standard deck of 52 cards.
     */
	public KingsDeck(String label) {
        super(label);
        String suits = "cdhs";
        String cardset = "cardset-oxymoron";
        for (int suit = 0; suit <= 3; suit++) {
        	char c = suits.charAt(suit);
            for (int rank = 1; rank <= 13; rank++) {
            	String s = String.format("src/cards/%s/%02d%c.gif",cardset, rank, c);
                addCard(new KingsCard(rank, suit, s));
            }
        }
    }

}