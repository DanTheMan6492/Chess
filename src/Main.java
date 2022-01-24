
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener, MouseListener, KeyListener{

    Color GREEN = new Color( 41, 176,  59);
    Color WHITE = new Color(255, 239, 216);

	public static int mouseX;
	public static int mouseY;
	public static int x;
	public static int y;
	public static int turn;
	public Piece dragging;
	public boolean play = true;

	public static Music Capture = new Music("Capture.wav", false);
	public static Music Castle  = new Music("Castle.wav" , false);
	public static Music Check   = new Music("Check.wav"  , false);
	public static Music Game    = new Music("Game.wav"   , false);
	public static Music Move    = new Music("Move.wav"   , false);

    public static Board board = new Board();

    public static void main(String[] args) {
		turn = 0;
        new Main();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        boolean flag = true;
		x = (mouseX-5) / 100;
		y = (mouseY-15) / 100;
		Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < 8; i++){

            
            for(int j = 0; j < 8; j++){
                if(flag){
                    g.setColor(WHITE);
                } else{
                    g.setColor(GREEN);
                }
                g.fillRect((j*100), (i*100), 100, 100);
                flag = !flag;
            }
            flag = !flag;
        }

		g.setColor(Color.WHITE);
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		g2.setComposite(alcom);
		g2.fillRect(x*100, y*100, 100, 100);

        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(alcom);



        board.paint(g);

		alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
		g2.setComposite(alcom);
		g.setColor(Color.gray);
		if(dragging != null){
			for(Integer[] elem : dragging.moves){
				g.fillOval(elem[0] * 100 + 35, elem[1] * 100 + 35, 30, 30);
			}
		}

		alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(alcom);
		if(dragging != null){
			dragging.paint(g);
		}

		flag = true;
		for(Piece[] row : board.board){
			for(Piece piece : row){
				if(piece != null){
					if(piece.color == turn%2){
						ArrayList<Integer[]> m = piece.genMoves(board.board, false);
						piece.pruneInvalid(m);
						if(m.size() > 0){
							flag = false;
							break;
						}
					}
				}
				
			}
		}

		alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
		g2.setComposite(alcom);
		g.setColor(Color.black);
		if(flag){
			g.fillRect(0, 0, 900, 900);
			g.setColor(Color.white);
			g.drawString("Check Mate!", 400, 400);
			if(play){
				Game.play();
				play = false;
			}
			switch(turn%2){
				case 0:
				g.drawString("Black Wins", 400, 500);
				break;
				case 1:
				g.drawString("White wins", 400, 500);
				break;
			}
		}
		
    }

    public Main() {
		JFrame f = new JFrame("Chess");
		f.setSize(new Dimension(815, 840));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent me)
			{
			  mouseX = me.getX();
			  mouseY = me.getY();
			}
		  });
		f.setVisible(true);
	}

    @Override
	public void mouseClicked(MouseEvent arg0) {
	
		if(dragging != null){
			dragging.release();
			
			dragging = null;
		} else if(board.board[y][x] != null){
			dragging = board.board[y][x];
			if(dragging.color == turn%2){
				dragging.pick();
			} else{
				dragging = null;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { 
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}