package cards;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class KingsGUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    KingsGUIPanels nP;
    KingsGUIPanels sP;
    KingsGUIPanels eP;
    KingsGUIPanels wP;
    KingsGUIPanels neP;
    KingsGUIPanels nwP;
    KingsGUIPanels seP;
    KingsGUIPanels swP;
    KingsGUIPanels deckP;
    KingsGUIPanels myHandP;
    KingsGUIPanels gameControlP;
    JPanel controlPanel;
    KingsGUIPanels botP;
    JButton btn;
    JButton startGame;
    JLabel numTurnsLabel;
    JLabel turnLabel;
    private KingsPlayer me; 
	private KingsBotPlayer bot;
	private KingsHand n;
	private KingsHand s;
	private KingsHand e;
	private KingsHand w;
	private KingsHand ne;
	private KingsHand nw;
	private KingsHand se;
	private KingsHand sw;
	private KingsDeck deck;
	int x, y, numTurns;
	boolean moveMake = false;
	boolean myMove = true;
	KingsGUIPanels[] cardTable = new KingsGUIPanels[12];

    public KingsGUI(KingsPlayer yo,KingsBotPlayer botBoy,KingsHand n,
    		KingsHand s,KingsHand e,KingsHand w,KingsDeck deck) {
    	super("Kings Card Table");
    	GridLayout experimentLayout = new GridLayout(3,4);
    	GridLayout ctrlPnl = new GridLayout(4,1);
    	setLayout(experimentLayout);
		setSize( 1500,830 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );

		controlPanel = new JPanel();
		btn = new JButton("End Turn");
		btn.addActionListener(this);
		startGame = new JButton("Start New Game");
		startGame.addActionListener(this);
		numTurnsLabel = new JLabel("0", JLabel.CENTER);
		turnLabel = new JLabel("Number of turns played: ", JLabel.CENTER);
        this.deck = deck;
        this.bot = botBoy;
        botP = new KingsGUIPanels(this.bot.getMyHand(),0,1);
        this.nw = new KingsHand("nw");

        nwP = new KingsGUIPanels(this.nw,-45,2);
        this.n = n;
        nP = new KingsGUIPanels(this.n,0,3);
        this.ne = new KingsHand("ne");

        neP = new KingsGUIPanels(this.ne,45,4);
        gameControlP = new KingsGUIPanels(new KingsHand("control"),0,5);
        controlPanel.setLayout(ctrlPnl);
        controlPanel.add(btn);
        controlPanel.add(turnLabel);
        controlPanel.add(numTurnsLabel);
        controlPanel.add(startGame);

        this.w = w;
        wP = new KingsGUIPanels(this.w,90,6);
        deckP = new KingsGUIPanels(this.deck,0,7);
        this.e = e;
        eP = new KingsGUIPanels(this.e,-90,8);
        this.me = yo;
        myHandP = new KingsGUIPanels(this.me.getMyHand(),0,9);
        this.sw = new KingsHand("sw");

        swP = new KingsGUIPanels(this.sw,45,10);
        this.s = s;
        sP = new KingsGUIPanels(this.s,0,11);
        this.se = new KingsHand("se");

        seP = new KingsGUIPanels(this.se,-45,12);
        cardTable[0] = this.botP;
        cardTable[1] = this.nwP;
        cardTable[2] = this.nP;
        cardTable[3] = this.neP;
        cardTable[4] = this.gameControlP;
        cardTable[5] = this.wP;
        cardTable[6] = this.deckP;
        cardTable[7] = this.eP;
        cardTable[8] = this.myHandP;
        cardTable[9] = this.swP;
        cardTable[10] = this.sP;
        cardTable[11] = this.seP;
        add(cardTable[0]);
        add(cardTable[1]);
        add(cardTable[2]);
        add(cardTable[3]);
        add(controlPanel);
        //add(cardTable[4]);
        add(cardTable[5]);
        add(cardTable[6]);
        add(cardTable[7]);
        add(cardTable[8]);
        add(cardTable[9]);
        add(cardTable[10]);
        add(cardTable[11]);
        cardTable[0].addMouseMotionListener(this);	
        cardTable[0].addMouseListener(this);
        cardTable[1].addMouseMotionListener(this);	
        cardTable[1].addMouseListener(this);
        cardTable[2].addMouseMotionListener(this);	
        cardTable[2].addMouseListener(this);
        cardTable[3].addMouseMotionListener(this);	
        cardTable[3].addMouseListener(this);
        cardTable[5].addMouseMotionListener(this);	
        cardTable[5].addMouseListener(this);
        cardTable[6].addMouseMotionListener(this);	
        cardTable[6].addMouseListener(this);
        cardTable[7].addMouseMotionListener(this);	
        cardTable[7].addMouseListener(this);
        cardTable[8].addMouseMotionListener(this);	
        cardTable[8].addMouseListener(this);
        cardTable[9].addMouseMotionListener(this);	
        cardTable[9].addMouseListener(this);
        cardTable[10].addMouseMotionListener(this);	
        cardTable[10].addMouseListener(this);
        cardTable[11].addMouseMotionListener(this);	
        cardTable[11].addMouseListener(this);
		setVisible(true);

    }

	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		x = event.getX(); 
		y = event.getY();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		Object source = event.getSource();
		if(source instanceof KingsGUIPanels){
		    KingsGUIPanels panelPressed = (KingsGUIPanels) source;
		    for(int i = 0; i < 12; ++i) {
		    	if(i == 0 || i == 4 || i == 6)
		    		continue;
		    	if(cardTable[i].getGrid() == panelPressed.getGrid()) {
		    		cardTable[i].setClicked(true);
		    		moveMake = true;
		    		cardTable[i].repaint();
		    	}
		    }
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		Object source = event.getSource();
		if(source instanceof KingsGUIPanels){
		    KingsGUIPanels panelPressed = (KingsGUIPanels) source;
		    cardTable[panelPressed.getGrid()-1].setClicked(false);
		    for(int i = 0; i < 12; ++i) {
		    	if(i == 0 || i == 4 || i == 6)
		    		continue;
		    	if(cardTable[i].isMouseIn() && cardTable[i].isValidMove()) {
		    		moveCards(panelPressed.getGrid()-1, i);
		    		cardTable[i].setValidMove(false);
		    		moveMake = false;
		    		cardTable[i].repaint();
		    	}
		    }
		    cardTable[panelPressed.getGrid()-1].repaint();
		}
	}

	

	@Override
	public void mouseEntered(MouseEvent event) {
		Object source = event.getSource();
		if(source instanceof KingsGUIPanels){
		    KingsGUIPanels panelPressed = (KingsGUIPanels) source;
		    if(panelPressed.getGrid() != 1 && panelPressed.getGrid() != 5 && panelPressed.getGrid() != 7) {
		    	cardTable[panelPressed.getGrid()-1].setMouseIn(true);
		    	if(moveMake) {
		    		for(int i = 0; i < 12; ++i) {
				    	if(i == 0 || i == 4 || i == 6)
				    		continue;
				    	else if(cardTable[i].isClicked() && cardTable[i].getGrid() != panelPressed.getGrid()) {
				    		if(cardTable[i].canDo(panelPressed))
				    			cardTable[panelPressed.getGrid()-1].setValidMove(true);
				    	}
		    		}
		    	}
		    	cardTable[panelPressed.getGrid()-1].repaint();
		    }
		}
	}

	@Override
	public void mouseExited(MouseEvent event) {
		Object source = event.getSource();
		if(source instanceof KingsGUIPanels){
		    KingsGUIPanels panelExited = (KingsGUIPanels) source;
		    cardTable[panelExited.getGrid()-1].setMouseIn(false);
	    	cardTable[panelExited.getGrid()-1].setValidMove(false);
	    	cardTable[panelExited.getGrid()-1].repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		if(event.getSource() == btn) {
			if(cardTable[8].getPanHand().size() == 0) {
				JOptionPane.showMessageDialog( this,"Booyah.","Winner = you",JOptionPane.INFORMATION_MESSAGE );
				return;
			}
			numTurns += 1;
			numTurnsLabel.setText(String.valueOf(numTurns));
			if(cardTable[6].getPanHand().size() > 0) {
				cardTable[6].getPanHand().deal(cardTable[8].getPanHand(), 1);
				cardTable[8].getPanHand().getCard(cardTable[8].getPanHand().size()-1).resetImageURL();
				cardTable[6].repaint();
				cardTable[8].repaint();
			}
			myMove = false;
			botMove();
			if(cardTable[0].getPanHand().size() == 0) {
				JOptionPane.showMessageDialog( this,"Boohoo.","Winner = machine",JOptionPane.INFORMATION_MESSAGE );
				return;
			}
			if(cardTable[6].getPanHand().size() > 0) {
				cardTable[6].getPanHand().deal(cardTable[0].getPanHand(), 1);
				cardTable[6].repaint();
				cardTable[0].repaint();
			}
			myMove = true;
			
		}
		if(event.getSource() == startGame) {
			setVisible(false);
			Kings newGame = new Kings();
			return;
		}
		
	}
	
	public void botMove() {
		if(cardTable[0].getPanHand().size() == 0)
			return;
		int canDoMoves = 0;
		int toIndex = -1;
		for(int i = 0; i < 12; ++i) { //Looks for moves within hand
	    	if(i == 0 || i == 4 || i == 6 || i == 8)
	    		continue;
	    	else if(cardTable[0].botCanDo(cardTable[i])) {
	    		toIndex = i;
	    		++canDoMoves;
	    	}
		}
		if(canDoMoves == 1) {
    		moveBotCards(0, toIndex);
    		
    		cardTable[0].repaint();
			cardTable[toIndex].repaint();
    	}
    	else if(canDoMoves > 1) {
    		moveBotCards(0, toIndex);
    		cardTable[0].repaint();
			cardTable[toIndex].repaint();
    		botMove();
    	}

		if(cardTable[0].getPanHand().size() == 0)
			return;
		int boardMoves = 0;
		int to = -1;
		int from = -1;
		for(int i = 0; i < 12; ++i) { //looks for moves with board stacks
	    	if(i == 0 || i == 4 || i == 6 || i == 8 || i == 1 || i == 3 || i == 9 || i == 11)
	    		continue;
	    	else  {
	    		for(int j = 0; j < 12; ++j) {
	    			if(j == 0 || j == 4 || j == 6 || j == 8)
	    	    		continue;
	    	    	else if(cardTable[i].botCanDo(cardTable[j])) {
	    				++boardMoves;
	    				to = j;
	    				from = i;
	    			}
	    		}
	    	}
		}
		if(boardMoves == 1) {
    		moveBotCards(from, to);
    		
    		cardTable[from].repaint();
			cardTable[to].repaint();
    	}
    	else if(boardMoves > 1) {
    		moveBotCards(from, to);
    		cardTable[from].repaint();
			cardTable[to].repaint();
    		botMove();
    	}
		
		if(cardTable[0].getPanHand().size() == 0)
			return;
		if(canDoMoves == 0 && boardMoves == 0)
			return;
		botMove();
	}
	
	private void moveCards(int fromIndex, int toIndex) {
		if(fromIndex == 2 || fromIndex == 5 || fromIndex == 7 || fromIndex == 10) {
			for(int j = cardTable[fromIndex].getPanHand().size()-1; j >= 0 ; --j) {
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(0));
			}
		}
		else if((fromIndex == 8 || fromIndex == 0) && cardTable[toIndex].getPanHand().size() == 0) {
			if(toIndex == 1 || toIndex == 3 || toIndex == 9 || toIndex == 11) {
				int numKings = 0;
				int kingIndex = 0;
				for(int i = 0;i < cardTable[fromIndex].getPanHand().size(); ++i) {
					if(cardTable[fromIndex].getPanHand().getCard(i).getRank() == 13) {
						++numKings;
						kingIndex = i;
					}
				}       
				if(numKings == 1)
					cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(kingIndex));
				else {
					kingIndex = askForCard(fromIndex);
					if(kingIndex == -1)
						return;
					if(cardTable[fromIndex].getPanHand().getCard(kingIndex).getRank() == 13) {
						cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(kingIndex));
					}
					else
						moveCards(fromIndex, toIndex);
				}
			}
			else{
				int cardIndex = askForCard(fromIndex);
				if(cardIndex == -1)
					return;
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(cardIndex));
			}
		}
		else if((fromIndex == 8 || fromIndex == 0) && cardTable[toIndex].getPanHand().size() != 0) {
			for(int i = 0;i < cardTable[fromIndex].getPanHand().size(); ++i) {
				if(cardTable[fromIndex].getPanHand().getCard(i).compareTo
						(cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size() - 1)) == 1) {
					cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(i));
					break;
				}
			} 
			/*
			int cardIndex = askForCard(fromIndex);
			if(cardIndex == -1)
				return;
			if(cardTable[fromIndex].getPanHand().getCard(cardIndex).compareTo
					(cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size() - 1)) == 1)
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(cardIndex));
			else
				moveCards(fromIndex, toIndex);
			*/
		}
	}
	
	private void moveBotCards(int fromIndex, int toIndex) {
		if(fromIndex == 2 || fromIndex == 5 || fromIndex == 7 || fromIndex == 10) {
			for(int j = cardTable[fromIndex].getPanHand().size()-1; j >= 0 ; --j) {
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(0));
				cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size()-1).resetImageURL();
			}
		}
		else if((fromIndex == 8 || fromIndex == 0) && cardTable[toIndex].getPanHand().size() == 0) {
			if(toIndex == 1 || toIndex == 3 || toIndex == 9 || toIndex == 11) {
				int numKings = 0;
				int kingIndex = 0;
				for(int i = 0;i < cardTable[fromIndex].getPanHand().size(); ++i) {
					if(cardTable[fromIndex].getPanHand().getCard(i).getRank() == 13) {
						++numKings;
						kingIndex = i;
					}
				}       
				if(numKings == 1) {
					cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(kingIndex));
					cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size()-1).resetImageURL();
				}
				else {
					//kingIndex = askForCard(fromIndex);
					if(kingIndex == -1)
						return;
					if(cardTable[fromIndex].getPanHand().getCard(kingIndex).getRank() == 13) {
						cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(kingIndex));
						cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size()-1).resetImageURL();
					}
					//else
						//moveCards(fromIndex, toIndex);
				}
			}
			else{
				int cardIndex = cardTable[fromIndex].getPanHand().size()-1;
				if(cardIndex == -1)
					return;
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(cardIndex));
				cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size()-1).resetImageURL();
			}
		}
		else if((fromIndex == 8 || fromIndex == 0) && cardTable[toIndex].getPanHand().size() != 0) {
			for(int i = 0;i < cardTable[fromIndex].getPanHand().size(); ++i) {
				if(cardTable[fromIndex].getPanHand().getCard(i).compareTo
						(cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size() - 1)) == 1) {
					cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(i));
					cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size()-1).resetImageURL();
					break;
				}
			} 
			/*
			int cardIndex = askForCard(fromIndex);
			if(cardIndex == -1)
				return;
			if(cardTable[fromIndex].getPanHand().getCard(cardIndex).compareTo
					(cardTable[toIndex].getPanHand().getCard(cardTable[toIndex].getPanHand().size() - 1)) == 1)
				cardTable[toIndex].getPanHand().addCard(cardTable[fromIndex].getPanHand().popCard(cardIndex));
			else
				moveCards(fromIndex, toIndex);
			*/
		}
	}

	private int askForCard(int fromIndex) {
		String[] myCards = cardNames(fromIndex);
		int cardIndex = 0;
		String cardName = (String) JOptionPane.showInputDialog(null, "\nPick a card to move: ", 
				"My Hand", JOptionPane.PLAIN_MESSAGE, null, myCards, myCards[0]);
		if(cardName == null)
			cardIndex = -1;
		for(int i = 0;i < myCards.length; ++i) {
			if(myCards[i].equals(cardName))
				cardIndex = i;
		}
		return cardIndex;
	}

	public String[] cardNames(int index) {
		String[] names = new String[cardTable[index].getPanHand().size()];
		for(int i = 0;i < cardTable[index].getPanHand().size(); ++i) {
			names[i] = cardTable[index].getPanHand().getCard(i).toString();
		}
		return names;
	}
	
}
