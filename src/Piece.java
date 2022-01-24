
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Piece {
    public int color;
    public int state;
    public Image Sprite;
    public AffineTransform tx;
    public boolean dragged;
    public int x;
    public int y;
    public int X;
    public int Y;
    public String Name;
    public ArrayList<Integer[]> moves;
    public int lastMoved;

    public Piece(int Id, int color, int x, int y){
        dragged = false;
        this.color = color;

        this.x = 100*x;
        this.y = 100*y;
        X=this.x;
        Y=this.y;
        state = 0;

        tx = AffineTransform.getTranslateInstance(x, y);
        tx.scale(0.1, 0.1);
    }
    public Piece(Piece alt){
        this.color = alt.color;
        this.state = alt.state;
        this.x = alt.x;
        this.y = alt.y;
        this.Name = alt.Name;
        this.tx = alt.tx;
        this.x = alt.x;
        this.Y = alt.y;
        this.lastMoved = alt.lastMoved;
        this.dragged = alt.dragged;
    }

    public ArrayList<Integer[]> genMoves(Piece[][] board, boolean checking){
        return null;
    }
    private void update(){
        if(state == 2){
            if(lastMoved + 2 < Main.turn){
                state = 1;
            }
        }
        if(dragged){
            x = Main.mouseX-60;
            y = Main.mouseY-70;
        }
        tx.setToTranslation(x, y);
        tx.scale(0.1, 0.1);
        
        
    }

    public void pick(){
        moves = genMoves(Main.board.board, true);
        pruneInvalid(moves);
        X = x;
        Y = y;
        dragged = true;
    }

    public void move(int x, int y){
        int type = 0;
        this.x = x;
        this.y = y;
        Integer[] temp = {x, y};
        if(isInList(moves, temp)){
            if(Main.board.board[y/100][x/100] != null){
                type = 1;
            }
            Main.board.board[y/100][x/100] = Main.board.board[Y/100][X/100];
            Main.board.board[Y/100][X/100] = null;
            state = 1;
            if(this.Name.equals("King")){
                System.out.println(X/100 - x/100);
                if(X/100 - x/100 == 2){
                    System.out.println(Main.board.board[Y/100][0] == null);
                    System.out.println((x/100)+1);
                    Main.board.board[Y/100][(x/100)+1] = Main.board.board[Y/100][0];
                    Main.board.board[Y/100][(x/100)+1].x = x+100;
                    Main.board.board[Y/100][0] = null;
                    type = 2;
                } else if(X/100 - x/100 == -2){
                    Main.board.board[Y/100][(x/100)-1] = Main.board.board[Y/100][7];
                    Main.board.board[Y/100][(x/100)-1].x = x-100;
                    Main.board.board[Y/100][7] = null;
                    type = 2;
                }
            }
            if(this.Name.equals("Pawn")){
                int offset = (int) Math.pow(-1, color);
                if(inBoard(x/100, y/100+offset)){
                    if(Main.board.board[y/100+offset][x/100] != null){
                        if(Main.board.board[y/100+offset][x/100].state == 2){
                            Main.board.board[y/100+offset][x/100] = null;
                        }
                    }
                }
                if(color == 0){
                    if(y/100 < Y/100 - 1){
                        state = 2;
                    }
                } else{
                    if(y/100 > Y/100 + 1){
                        state = 2;
                    }
                }
            }
            lastMoved = Main.turn;
            Main.turn++;
        } else{
            x = X;
            y = Y;
        }
        switch(type){
            case 0:
            Main.Move.play();
            break;
            case 1:
            Main.Capture.play();
            break;
            case 2:
            Main.Castle.play();
            break;
        }
    }
    public void release(){
        int type = 0;
        x = 100*Main.x;
        y = 100*Main.y;
        Integer[] temp = {x/100, y/100};
        if(isInList(moves, temp)){
        	type = 3;
            if(Main.board.board[y/100][x/100] != null){
                type = 1;
            }
            Main.board.board[y/100][x/100] = Main.board.board[Y/100][X/100];
            Main.board.board[Y/100][X/100] = null;
            state = 1;
            if(this.Name.equals("King")){
                System.out.println(X/100 - x/100);
                if(X/100 - x/100 == 2){
                    System.out.println(Main.board.board[Y/100][0] == null);
                    System.out.println((x/100)+1);
                    Main.board.board[Y/100][(x/100)+1] = Main.board.board[Y/100][0];
                    Main.board.board[Y/100][(x/100)+1].x = x+100;
                    Main.board.board[Y/100][0] = null;
                    type = 2;
                } else if(X/100 - x/100 == -2){
                    Main.board.board[Y/100][(x/100)-1] = Main.board.board[Y/100][7];
                    Main.board.board[Y/100][(x/100)-1].x = x-100;
                    Main.board.board[Y/100][7] = null;
                    type = 2;
                }
            }
            if(this.Name.equals("Pawn")){
                int offset = (int) Math.pow(-1, color);
                if(inBoard(x/100, y/100+offset)){
                    if(Main.board.board[y/100+offset][x/100] != null){
                        if(Main.board.board[y/100+offset][x/100].state == 2){
                            Main.board.board[y/100+offset][x/100] = null;
                        }
                    }
                }
                if(color == 0){
                    if(y/100 < Y/100 - 1){
                        state = 2;
                    }
                } else{
                    if(y/100 > Y/100 + 1){
                        state = 2;
                    }
                }
                if(y == 0 || y == 700) {
                	dragged = false;
                	Main.board.board[y/100][x/100] = new Queen(this);
                }
            }
            lastMoved = Main.turn;
            Main.turn++;
        } else{
            x = X;
            y = Y;
        }
        switch(type){
            case 3:
            Main.Move.play();
            break;
            case 1:
            Main.Capture.play();
            break;
            case 2:
            Main.Castle.play();
            break;
        }
        dragged = false;
    }

    protected Image getImage(String path) {

		Image tempImage = null;
		try {
			URL imageURL = Piece.class.getResource(path);
			tempImage    = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {e.printStackTrace();}
		return tempImage;
	}

    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(Sprite, tx, null);
	}

    public boolean placeEmpty(Piece[][] board, int x, int y){
        if(!inBoard(x, y)){
            return false;
        }
        return (board[y][x] == null);
    }

    public static boolean isInList( ArrayList<Integer[]> list, Integer[] candidate){
        if(list == null){
            return false;
        }
        boolean flag = true;;
        for(Integer[] item : list){
            for(int i = 0; i < item.length; i++){
                if(item[i] != candidate[i]){
                    flag= false;
                }
            }
            if(flag){
                return true;
            }
            flag = true;
        }
        return false;
    }

    public boolean inBoard(int x, int y){
        if(x < 0 || x > 7){
            return false;
        }
        if(y < 0 || y > 7){
            return false;
        }
        return true;
    }

    public void addMove(ArrayList<Integer[]> moves, int x, int y){
        Integer[] temp = new Integer[2];
        temp[0] = x;
        temp[1] = y;
        moves.add(temp);
    }

    public void pruneInvalid(ArrayList<Integer[]> moves){
        if(moves != null){
            for(int i = 0; i < moves.size(); i++){
                Piece[][] futureBoard = arraycopy(Main.board.board);
                futureBoard[moves.get(i)[1]][moves.get(i)[0]] = this;
                futureBoard[this.y/100][this.x/100] = null;
    
                Integer[] kingCoords = new Integer[2];

                for(int x = 0; x < 8; x++){
                    for(int y = 0; y < 8; y++){
                        Piece piece = futureBoard[y][x];
                        if(piece != null){
                            if(piece.Name.equals("King") && piece.color == this.color){
                                kingCoords[0] = x;
                                kingCoords[1] = y;
                                break;
                            }
                        }
                    }
                }

    
                boolean flag = false;
                for(Piece[] row : futureBoard){
                    for(Piece piece : row){
                        if(piece != null){
                                ArrayList<Integer[]> m = piece.genMoves(futureBoard, false);
                                if(isInList(m, kingCoords)){
                                    moves.remove(i);
                                    i--;
                                    flag = true;
                                    break;
                            }
                        }
                    }
                    if(flag){
                        break;
                    }
                }
            }
        }
        
        
    }

    public Piece[][] arraycopy(Piece[][] arr){
        Piece[][] result = new Piece[arr.length][arr[0].length];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                if(arr[i][j] != null){
                    switch(arr[i][j].Name){
                        case "Pawn":
                        result[i][j] = new Pawn(arr[i][j]);
                        break;
                        case "Rook":
                        result[i][j] = new Rook(arr[i][j]);
                        break;
                        case "Knight":
                        result[i][j] = new Knight(arr[i][j]);
                        break;
                        case "Bishop":
                        result[i][j] = new Bishop(arr[i][j]);
                        break;
                        case "Queen":
                        result[i][j] = new Queen(arr[i][j]);
                        break;
                        case "King":
                        result[i][j] = new King(arr[i][j]);
                        break;
                    }
                }
            }
        }
        return result;
    }

}
