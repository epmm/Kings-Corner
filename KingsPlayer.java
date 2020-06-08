package cards;

public class KingsPlayer {
	
	private boolean endTurn;
	private KingsHand myHand;
	private String myName;
	private int level;
	
	public KingsPlayer(String name, int lvl) {
		this.endTurn = false;
		this.myHand = new KingsHand(name);
		this.myName = name;
		this.level = lvl;
	}

	public boolean isEndTurn() {
		return endTurn;
	}

	public void setEndTurn(boolean endTurn) {
		this.endTurn = endTurn;
	}

	public KingsHand getMyHand() {
		return myHand;
	}

	public void setMyHand(KingsHand myHand) {
		this.myHand = myHand;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getLevel() {
		return level;
	}
	
           
}
