package cards;

public class KingsHand extends KingsCardCollection {

    /**
     * Constructs an empty hand.
     */
    public KingsHand(String label) {
        super(label);
    }

    /**
     * Prints the label and cards.
     */
    public void display() {
        System.out.println(getLabel() + ": ");
        for (int i = 0; i < size(); i++) {
            System.out.println(getCard(i));
        }
        System.out.println();
    }

	public boolean canDo(KingsHand toHand) {
		
		return false;
	}

	public String toString() {
		return super.toString();
	}
}
