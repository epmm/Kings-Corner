package cards;

public class Kings {
	
	private KingsPlayer me; 
	private KingsBotPlayer bot;
	private KingsHand n;
	private KingsHand s;
	private KingsHand e;
	private KingsHand w;

	public Kings() {
		KingsDeck deck = new KingsDeck("The Deck");
		deck.shuffle();
		me = new KingsPlayer("me", 3);
		bot = new KingsBotPlayer("bot");
		n = new KingsHand("n");
		s = new KingsHand("s");
		e = new KingsHand("e");
		w = new KingsHand("w");
		int handSize = 7;
        deck.deal(me.getMyHand(), handSize);
        deck.deal(bot.getMyHand(), handSize);
        deck.deal(n, 1);
        deck.deal(s, 1);
        deck.deal(e, 1);
        deck.deal(w, 1);
        KingsGUI gui = new KingsGUI(me, bot, n, s, e, w, deck);
	}
	
	public static void main(String...strings) {
		Kings game = new Kings();
	}
	
}
