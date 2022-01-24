import java.util.ArrayList;

public class King extends Piece{
    public King(int Id, int color, int x, int y) {
        super(Id, color, x, y);
        this.state = 0;
        String path = "/imgs/Pieces/";
        if(color == 0){
            path += "W";
        } else{
            path += "B";
        }
        path += "_King.png";
        Sprite = getImage(path);
        Name = "King";
    }

    public King(Piece piece) {
        super(piece);
    }

    @Override
    public ArrayList<Integer[]> genMoves(Piece[][] board, boolean checking){
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        int x = this.x / 100;
        int y = this.y / 100;

        int xOffset = 1;
        int yOffset = 1;
        for(int i = 0; i < 8; i++){
            if(inBoard(x+xOffset, y+yOffset)){
                if(placeEmpty(board, x+xOffset, y+yOffset)){
                        addMove(result, x+xOffset, y+yOffset);
                } else if(board[y+yOffset][x+xOffset].color != this.color){
                    addMove(result, x+xOffset, y+yOffset);
                }
            }
            switch(i/2){
                case 0:
                    xOffset--;
                    break;
                case 1:
                    yOffset--;
                    break;
                case 2:
                    xOffset++;
                    break;
                case 3:
                    yOffset++;
                    break;
            }
        }

        if(state == 0){
            if(board[y][0] != null){
                boolean flag = true;
                for(int i = 1; i < 4; i++){
                    if(board[y][i] != null){
                        flag = false;
                        break;
                    }
                }
                if(flag && board[y][0].state == 0){
                    addMove(result, x-2, y);
                }
            }

            if(board[y][7] != null){
                boolean flag = true;
                for(int i = 5; i < 7; i++){
                    if(board[y][i] != null){
                        flag = false;
                        break;
                    }
                }
                if(flag && board[y][7].state == 0){
                    addMove(result, x+2, y);
                }
            }
        }
        
        if(checking){
            pruneInvalid(moves);
        }

        return result;
    }
}
