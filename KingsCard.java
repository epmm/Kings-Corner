package cards;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class KingsCard {

    public static final String[] RANKS = {
        null, "Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};

    public static final String[] SUITS = {
        "Clubs", "Diamonds", "Hearts", "Spades"};

    private final int rank;
    private final int suit;
	private final int color; //COLOR CODE IS 0 = Black, 1 = Red
    private BufferedImage image;

    public KingsCard(int rank, int suit, String url) {
        this.rank = rank;
        this.suit = suit;
        this.color = setColor(suit);
        Image holder = new ImageIcon(url).getImage();
        this.image = toBufferedImage(holder);
    }
    
    public void setImage(String url) {
    	Image holder = new ImageIcon(url).getImage();
        this.image = toBufferedImage(holder);
	}
    
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    private int setColor(int suitNum) {
		if(suitNum == 0 || suitNum == 3)
			return 0;
		if(suitNum == 1 || suitNum == 2)
			return 1;
		return 0;
	}

	/**
     * Returns a negative integer if
     * non-playable  move. Returns positive integer
     * if it is a playable move.
     */
    public int compareTo(KingsCard that) {
        if (this.color != that.color) {
        	if (this.rank == that.rank-1) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * Returns true if the given card has the same
     * rank AND same suit; otherwise returns false.
     * DOESN"T SEEM NECESSARY - EM
     */
    public boolean equals(KingsCard that) {
        return this.rank == that.rank
            && this.suit == that.suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }
    
    public int getColor() {
        return this.color;
    }

    /**
     * Returns the card's index in a sorted deck of 52 cards.
     */
    public int position() {
        return this.suit * 13 + this.rank - 1;
    }

    /**
     * Returns a string representation of the card.
     */
    public String toString() {
        return RANKS[this.rank] + " of " + SUITS[this.suit];
    }

	public BufferedImage getImage() {
		return image;
	}

	public void resetImageURL() {
		String suits = "cdhs";
	    String cardset = "cardset-oxymoron";
	    char c = suits.charAt(this.getSuit());
	    String s = String.format("src/cards/%s/%02d%c.gif",cardset, this.getRank(), c);
        this.setImage(s);
	}
	
}

