package cards;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KingsCardTable extends Canvas implements ActionListener, MouseListener, MouseMotionListener{

    private Image[][] images;
    private BufferedImage[][] buffImages;
    private int cardWidth, cardHeight, cardWidthB, cardHeightB;
    private Image[] n;
    private BufferedImage[] buffN;
    private Image[] s;
    private BufferedImage[] buffS;
    private Image[] e;
    private BufferedImage[] buffE;
    private Image[] w;
    private BufferedImage[] buffW;
    private Image[] ne;
    private BufferedImage[] buffNE;
    private Image[] nw;
    private BufferedImage[] buffNW;
    private Image[] se;
    private BufferedImage[] buffSE;
    private Image[] sw;
    private BufferedImage[] buffSW;
    private Image[] tableDeck;
    private BufferedImage[] buffTableDeck;
    private Image[] myHand;
    private BufferedImage[] buffMyHand;
    private Image[] botHand;
    private BufferedImage[] buffBotHand;
    

    public KingsCardTable(String cardset) {
        setBackground(new Color(0x088A4B));

        images = new Image[14][4];
        buffImages = new BufferedImage[14][4];
        buffN = new BufferedImage[7];
        buffS = new BufferedImage[7];
        buffE = new BufferedImage[7];
        buffW = new BufferedImage[7];
        buffNE = new BufferedImage[7];
        buffNW = new BufferedImage[7];
        buffSE = new BufferedImage[7];
        buffSW = new BufferedImage[7];
        buffTableDeck = new BufferedImage[1];
        Image cardBack = new ImageIcon("src/cards/back111.gif").getImage();
        buffTableDeck[0] = toBufferedImage(cardBack);
        String suits = "cdhs";

        for (int suit = 0; suit <= 3; suit++) {
            char c = suits.charAt(suit);

            for (int rank = 1; rank <= 13; rank++) {
                String s = String.format("src/cards/%s/%02d%c.gif",
                                         cardset, rank, c);
                images[rank][suit] = new ImageIcon(s).getImage();
                buffImages[rank][suit] = toBufferedImage(images[rank][suit]);
                if(suit == 0 && rank <= 7)
                	buffN[rank-1] = buffImages[rank][suit];
                if(suit == 0 && rank > 7)
                	buffS[rank-8] = buffImages[rank][suit];
                if(suit == 1 && rank <= 7)
                	buffE[rank-1] = buffImages[rank][suit];
                if(suit == 1 && rank > 7)
                	buffW[rank-8] = buffImages[rank][suit];
                if(suit == 2 && rank <= 7)
                	buffNE[rank-1] = buffImages[rank][suit];
                if(suit == 2 && rank > 7)
                	buffNW[rank-8] = buffImages[rank][suit];
                if(suit == 3 && rank <= 7)
                	buffSE[rank-1] = buffImages[rank][suit];
                if(suit == 3 && rank > 7)
                	buffSW[rank-8] = buffImages[rank][suit];
            }
        }

        // get the width and height of the cards and set the size of
        // the frame accordingly
        cardWidth = images[1][1].getWidth(null);
        cardHeight = images[1][1].getHeight(null);
        cardWidthB = buffImages[1][1].getWidth(null);
        cardHeightB = buffImages[1][1].getHeight(null);
        System.out.println(String.valueOf(cardWidth));
        System.out.println(String.valueOf(cardHeight));
        // set the size temporarily to get the insets
        setTableSize(20, 9);
    }

    /**
     * Sets the table size.
     * x and y are in units of card width/height.
     */
    public void setTableSize(double x, double y) {
        setSize((int) (x * cardWidthB),
                (int) (y * cardHeightB));
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
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
    
    public void drawDeck(Graphics g) {
    	int x = 713;
    	int y = 377;
        Image image = buffTableDeck[0];
        g.drawImage(image,x,y,null);
    }
	    
	public void drawCard(Graphics g,int rank, int suit, double x, double y, int deg, int xAdj, int yAdj) {
		BufferedImage buffImage = buffImages[rank][suit];
		Graphics2D g2d = (Graphics2D)g;
		double degreesToRotate = deg;
	    double locationX =buffImage.getWidth() / 2;
	    double locationY = buffImage.getHeight() / 2;

	    double diff = Math.abs(buffImage.getWidth() - buffImage.getHeight());

	    //To correct the set of origin point and the overflow
	    double rotationRequired = Math.toRadians(degreesToRotate);
	    double unitX = Math.abs(Math.cos(rotationRequired));
	    double unitY = Math.abs(Math.sin(rotationRequired));

	    double correctUx = unitX;
	    double correctUy = unitY;

	    //if the height is greater than the width, so you have to 'change' the axis to correct the overflow
	    if(buffImage.getWidth() < buffImage.getHeight()){
	        correctUx = unitY;
	        correctUy = unitX;
	    }

	    int posAffineTransformOpX = (int)(x * cardWidthB)-(int)(locationX)-(int)(correctUx*diff)+xAdj;
	    int posAffineTransformOpY = (int)(y * cardHeightB)-(int)(locationY)-(int)(correctUy*diff)+yAdj;

	    //translate the image center to same diff that dislocates the origin, to correct its point set
	    AffineTransform objTrans = new AffineTransform();
	    objTrans.translate(correctUx*diff, correctUy*diff);
	    objTrans.rotate(rotationRequired, locationX, locationY);

	    AffineTransformOp op = new AffineTransformOp(objTrans, AffineTransformOp.TYPE_BILINEAR);

	    // Drawing the rotated image at the required drawing locations
	    //g2d.drawImage(op.filter(buffImage, null), posAffineTransformOpX, posAffineTransformOpY, null);
	    if(suit == 0 && rank <= 7)
        	g2d.drawImage(op.filter(buffN[rank-1], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 0 && rank > 7)
        	g2d.drawImage(op.filter(buffS[rank-8], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 1 && rank <= 7)
        	g2d.drawImage(op.filter(buffE[rank-1], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 1 && rank > 7)
        	g2d.drawImage(op.filter(buffW[rank-8], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 2 && rank <= 7)
        	g2d.drawImage(op.filter(buffNE[rank-1], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 2 && rank > 7)
        	g2d.drawImage(op.filter(buffNW[rank-8], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 3 && rank <= 7)
        	g2d.drawImage(op.filter(buffSE[rank-1], null), posAffineTransformOpX, posAffineTransformOpY, null);
        if(suit == 3 && rank > 7)
        	g2d.drawImage(op.filter(buffSW[rank-8], null), posAffineTransformOpX, posAffineTransformOpY, null);
	}

    /**
     * Special method invoked when the Frame needs to be drawn.
     */
    public void paint(Graphics g) {
    	drawDeck(g);
    	int deg = 180; int xAdj = 300; int yAdj = 150; int i = 0;
        for (int rank = 1; rank <= 13; rank++) {
            for (int suit = 0; suit <= 3; suit++) {
                double x = rank  ; //- 1 + suit / 5.0
                double y = suit /2.0 ;
                if(suit == 0 && rank <= 7) {
                	x = 0; y = -0.33*i; deg = 180;  xAdj = 750;  yAdj = 325;
                }
                if(suit == 0 && rank > 7) {
                	x = 0; y = 0.33*(i-7); deg = 0;  xAdj = 750;  yAdj = 525;
                }
                if(suit == 1 && rank <= 7) {
                	x = 0.33*i; y = 0; deg = 90;  xAdj = 850;  yAdj = 425;
                }
                if(suit == 1 && rank > 7) {
                	x = -0.33*i; y = 0; deg = -90;  xAdj = 800;  yAdj = 425;
                }
                if(suit == 2 && rank <= 7) {
                	x = 0.3*i; y = -0.2*i; deg = 45;  xAdj = 850;  yAdj = 325;
                }
                if(suit == 2 && rank > 7) {
                	x = -0.3*(i-7); y = -0.2*(i-7); deg = -45;  xAdj = 650;  yAdj = 325;
                }
                if(suit == 3 && rank <= 7) {
                	x = 0.3*i; y = 0.2*i; deg = -45;  xAdj = 850;  yAdj = 525;
                }
                if(suit == 3 && rank > 7) {
                	x = -0.3*(i-7); y = 0.2*(i-7); deg = 45;  xAdj = 650;  yAdj = 525;
                }
                drawCard(g, rank, suit, x, y, deg, xAdj, yAdj);
            }
            ++i;
        }
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
        // make the frame
        JFrame frame = new JFrame("Card Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(null);
        //frame.set

        // add the CardTable
        String cardset = "cardset-oxymoron";
        Canvas canvas = new KingsCardTable(cardset);
        frame.getContentPane().add(canvas);
        /*JPanel nPan = new JPanel();
        nPan.setBackground(new Color(0,0,0,125));
        nPan.setLocation(713,300);
        nPan.setSize(75,400);
        //frame.add(nPan);
        JPanel sPan = new JPanel();
        JPanel ePan = new JPanel();
        JPanel wPan = new JPanel();
        JPanel nePan = new JPanel();
        JPanel nwPan = new JPanel();
        JPanel sePan = new JPanel();
        JPanel swPan = new JPanel();
        JPanel myPan = new JPanel();
        JPanel deckPan = new JPanel();
        */

        // show the frame
        frame.pack();
        frame.setVisible(true);
    }

}
