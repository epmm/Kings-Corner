package cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KingsGUIPanels extends JPanel{
	
	private int cardWidth = 50, cardHeight = 75;
	private KingsHand panHand;
	private int degRot;
	private int grid;
	private boolean mouseIn = false;
	private boolean clicked = false;
	private boolean validMove = false;
	JButton btn;
    JButton startGame;
    JLabel numTurnsLabel;
    JLabel turnLabel;
	

	public KingsGUIPanels(KingsHand hand, int deg, int grid) {
		//this.setBackground(Color.black);
		//this.setForeground(Color.black);
		this.panHand = hand;
		this.degRot = deg;
		this.grid = grid;
		//setTableSize(this.cardWidth, this.cardHeight);
    }

    public int getGrid() {
		return grid;
	}

	public void setGrid(int grid) {
		this.grid = grid;
	}

	/**
     * Sets the table size.
     * x and y are in units of card width/height.
     * Evan: this doesn't seem necessary anymore
     */
    public void setTableSize(double x, double y) {
        setSize((int) (x * cardWidth),
                (int) (y * cardHeight));
    }

    /**
     * Draws a card at the given coordinates.
     * x and y are in units of card width/height.
     */
    public void drawCard(Graphics g, int rank, int suit, double x, double y) {
    	if(!this.panHand.empty()) {
	        g.drawImage(this.panHand.getCard(0).getImage(),
	                    (int) (x * cardWidth),
	                    (int) (y * cardHeight),
	                    null);
    	}
    }
    
    public void drawCard(Graphics g, double x, double y, int deg, int xAdj, int yAdj, int i) {
    	if(!this.panHand.empty()) {
	    	Graphics2D g2d = (Graphics2D)g;
			double degreesToRotate = deg;
		    double locationX =this.panHand.getCard(i).getImage().getWidth() / 2;
		    double locationY = this.panHand.getCard(i).getImage().getHeight() / 2;
	
		    double diff = Math.abs(this.panHand.getCard(i).getImage().getWidth() - this.panHand.getCard(i).getImage().getHeight());
	
		    //To correct the set of origin point and the overflow
		    double rotationRequired = Math.toRadians(degreesToRotate);
		    double unitX = Math.abs(Math.cos(rotationRequired));
		    double unitY = Math.abs(Math.sin(rotationRequired));
	
		    double correctUx = unitX;
		    double correctUy = unitY;
	
		    //if the height is greater than the width, so you have to 'change' the axis to correct the overflow
		    if(this.panHand.getCard(i).getImage().getWidth() < this.panHand.getCard(i).getImage().getHeight()){
		        correctUx = unitY;
		        correctUy = unitX;
		    }
	
		    int posAffineTransformOpX = (int)(x * cardWidth)-(int)(locationX)-(int)(correctUx*diff)+xAdj;
		    int posAffineTransformOpY = (int)(y * cardHeight)-(int)(locationY)-(int)(correctUy*diff)+yAdj;
	
		    //translate the image center to same diff that dislocates the origin, to correct its point set
		    AffineTransform objTrans = new AffineTransform();
		    objTrans.translate(correctUx*diff, correctUy*diff);
		    objTrans.rotate(rotationRequired, locationX, locationY);
	
		    AffineTransformOp op = new AffineTransformOp(objTrans, AffineTransformOp.TYPE_BILINEAR);
	
		    // Drawing the rotated image at the required drawing locations
		    //g2d.drawImage(op.filter(buffImage, null), posAffineTransformOpX, posAffineTransformOpY, null);
	        g2d.drawImage(op.filter(this.panHand.getCard(i).getImage(), null), posAffineTransformOpX, 
	        			posAffineTransformOpY, null);
    	}
	}
    
    public void fillBG(Graphics g) {
    	//if(this.getGrid() == 5)
    		//return;
        super.paintComponent(g);
        if(isClicked()) {
        	g.setColor(Color.blue);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else if(isValidMove()) {
        	g.setColor(Color.cyan);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else {
        	g.setColor(Color.green);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Special method invoked when the Frame needs to be drawn.
     */
    public void paint(Graphics g) {
    	//if(this.getGrid() == 5)
    		//return;
        double x = 0;
        double y = 0;
        this.fillBG(g);
        for(int i = 0 ; i < this.panHand.size(); ++i) {
        	if(this.grid == 1) {
        		this.panHand.getCard(i).setImage("src/cards/cardset-oxymoron/back111.gif");
        		drawCard(g, 0.3*i, y, this.degRot, 50, 100, i);
        	}
        	else if(this.grid == 2)
        		drawCard(g, -0.27*i, -0.2*i, this.degRot, 310, 203, i);
        	else if(this.grid == 3)
        		drawCard(g, x, -0.23*i, this.degRot, 185, 213, i);
        	else if(this.grid == 4)
        		drawCard(g, 0.27*i, -0.2*i, this.degRot, 54, 203, i);
        	else if(this.grid == 6)
        		drawCard(g, -0.3*i, y, this.degRot, 320, 125, i);
        	else if(this.grid == 7) {
        		this.panHand.getCard(i).setImage("src/cards/cardset-oxymoron/back111.gif");
        		drawCard(g, x, y, this.degRot, 185, 125, i);
        	}
        	else if(this.grid == 8)
        		drawCard(g, 0.3*i, y, this.degRot, 50, 125, i);
        	else if(this.grid == 9)
        		drawCard(g, 0.3*i, y, this.degRot, 50, 100, i);
        	else if(this.grid == 10)
        		drawCard(g, -0.27*i, 0.2*i, this.degRot, 310, 58, i);
        	else if(this.grid == 11)
        		drawCard(g, x, 0.23*i, this.degRot, 185, 48, i);
        	else if(this.grid == 12)
        		drawCard(g, 0.27*i, 0.2*i, this.degRot, 54, 58, i);
        }
        	
    }

	public KingsHand getPanHand() {
		return panHand;
	}

	public void setPanHand(KingsHand panHand) {
		this.panHand = panHand;
	}

	public boolean isMouseIn() {
		return mouseIn;
	}

	public void setMouseIn(boolean mouseIn) {
		this.mouseIn = mouseIn;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean isValidMove() {
		return validMove;
	}

	public void setValidMove(boolean validMove) { 
		this.validMove = validMove;
	}

	public boolean canDo(KingsGUIPanels toPanel) {
		if(this.getPanHand().size() == 0)
			return false;
		if(this.getGrid() == 2 || this.getGrid() == 4 || this.getGrid() == 10 || this.getGrid() == 12)
			return false;
		if(toPanel.getGrid() == 2 || toPanel.getGrid() == 4 || toPanel.getGrid() == 10 || toPanel.getGrid() == 12) {
			if(this.getGrid() == 9 && toPanel.getPanHand().size() == 0) {
				if(this.getPanHand().toString().indexOf("King") > -1)
					return true;
			}
			else if(this.getGrid() == 9) {
				for(int j = this.getPanHand().size()-1; j >= 0 ; --j) {
	    			if(this.getPanHand().getCard(j).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    				return true;
				}
			}
			else if(this.getGrid() == 3 || this.getGrid() == 6 || this.getGrid() == 8 || this.getGrid() == 11) {
				if(toPanel.getPanHand().size() == 0)
					return (this.getPanHand().getCard(0).toString().indexOf("King") > -1);
				else 
					return (this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0);
					
			}
			else if(this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0){
	    		return true;
			}
		}
		if(toPanel.getGrid() == 3 || toPanel.getGrid() == 6 || toPanel.getGrid() == 8 || toPanel.getGrid() == 11) {
			if(toPanel.getPanHand().size() == 0)
				return true;
			else if(this.getGrid() == 9) {
				for(int j = this.getPanHand().size()-1; j >= 0 ; --j) {
	    			if(this.getPanHand().getCard(j).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    				return true;
				}
			}
			else if(this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    		return true;
		}
		return false;
	}
	
	public boolean botCanDo(KingsGUIPanels toPanel) {
		if(this.getPanHand().size() == 0)
			return false;
		if(this.getGrid() == 2 || this.getGrid() == 4 || this.getGrid() == 10 || this.getGrid() == 12)
			return false;
		if(toPanel.getGrid() == 2 || toPanel.getGrid() == 4 || toPanel.getGrid() == 10 || toPanel.getGrid() == 12) {
			if(this.getGrid() == 1 && toPanel.getPanHand().size() == 0) {
				if(this.getPanHand().toString().indexOf("King") > -1)
					return true;
			}
			else if(this.getGrid() == 1) {
				for(int j = this.getPanHand().size()-1; j >= 0 ; --j) {
	    			if(this.getPanHand().getCard(j).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    				return true;
				}
			}
			else if(this.getGrid() == 3 || this.getGrid() == 6 || this.getGrid() == 8 || this.getGrid() == 11) {
				if(toPanel.getPanHand().size() == 0)
					return (this.getPanHand().getCard(0).toString().indexOf("King") > -1);
				else 
					return (this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0);
					
			}
			else if(this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0){
	    		return true;
			}
		}
		if(toPanel.getGrid() == 3 || toPanel.getGrid() == 6 || toPanel.getGrid() == 8 || toPanel.getGrid() == 11) {
			if(toPanel.getPanHand().size() == 0)
				return true;
			else if(this.getGrid() == 1) {
				for(int j = this.getPanHand().size()-1; j >= 0 ; --j) {
	    			if(this.getPanHand().getCard(j).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    				return true;
				}
			}
			else if(this.getPanHand().getCard(0).compareTo(toPanel.getPanHand().getCard(toPanel.getPanHand().size()-1)) > 0)
	    		return true;
		}
		return false;
	}
	
}
