package cards;

import javax.swing.JOptionPane;

public class KingsBotPlayer extends KingsPlayer{

	public KingsBotPlayer(String name) {
		super(name, askLevel());
		
	}

	private static int askLevel() {
		int lvl = 0;
		int n = 0;	
		//n = JOptionPane.showConfirmDialog( null,"Would you like the bot to go easy on you?","Confirmation Dialog",JOptionPane.YES_NO_CANCEL_OPTION);	
		switch( n )
		{
			case 0 : lvl = 0; break;	
			case 1 : lvl = 1; break;
			case 2 : lvl = 1; break;
		}
		return lvl;
	}

}
