
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int Id, int color, int x, int y) {
        super(Id, color, x, y);
        this.state = 0;
        String path = "/imgs/Pieces/";
        if(color == 0){
            path += "W";
        } else{
            path += "B";
        }
        path += "_Pawn.png";
        Sprite = getImage(path);
        Name = "Pawn";
    }

    public Pawn(Piece piece) {
        super(piece);
    }

    @Override
    public ArrayList<Integer[]> genMoves(Piece[][] board, boolean checking){

        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        Integer[] temp = new Integer[2];
        int offset = -1 * (int) Math.pow(-1, color);

        //move 1 space
        if(placeEmpty(board, x/100, (y/100)+offset)){
            temp[0] = x/100;
            temp[1] = (y/100)+offset;
            result.add(temp);
            temp = new Integer[2];
        }

        //move 2 space
        if(state == 0){
            if(placeEmpty(board, x/100, (y/100)+2*offset)){
                temp[0] = x/100;
                temp[1] = (y/100)+2*offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        //captures
        if(!placeEmpty(board, (x/100)-1, (y/100)+offset)) {
            if(inBoard(x/100-1, y/100+offset)){
            	if(board[y/100+offset][x/100-1].color != this.color) {
            		temp[0] = (x/100)-1;
                    temp[1] = (y/100)+offset;
                    result.add(temp);
                    temp = new Integer[2];
            	}
            }
        }
        if(!placeEmpty(board, (x/100)-1, y/100)){
            if(inBoard((x/100)-1, y/100)){
                if(board[y/100][x/100-1].state == 2){
                    addMove(result, (x/100)-1, (y/100)+offset);
                }
            }
        }

        if(!placeEmpty(board, (x/100)+1, (y/100)+offset)){
        	if(inBoard(x/100+1, y/100+offset)){
            	if(board[y/100+offset][x/100+1].color != this.color) {
            		temp[0] = (x/100)+1;
                    temp[1] = (y/100)+offset;
                    result.add(temp);
                    temp = new Integer[2];
            	}
            }
            
        }
        if(!placeEmpty(board, (x/100)+1, y/100)){
            if(inBoard((x/100)+1, y/100)){
                if(board[y/100][x/100+1].state == 2){
                    addMove(result, (x/100)+1, (y/100)+offset);
                }
            }
        }
        
        
        return result;
    }
    
}
