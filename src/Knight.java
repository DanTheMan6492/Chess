import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int Id, int color, int x, int y) {
        super(Id, color, x, y);
        this.state = 0;
        String path = "/imgs/Pieces/";
        if(color == 0){
            path += "W";
        } else{
            path += "B";
        }
        path += "_Knight.png";
        Sprite = getImage(path);
        Name = "Knight";
    }
    

    public Knight(Piece piece) {
        super(piece);
    }


    @Override
    public ArrayList<Integer[]> genMoves(Piece[][] board, boolean checking){
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        int x = this.x / 100;
        int y = this.y / 100;

        int xOffset = 2;
        int yOffset = 1;
        for(int i = 0; i < 8; i++){
            if(inBoard(x+xOffset, y+yOffset)){
                if(placeEmpty(board, x+xOffset, y+yOffset) || board[y+yOffset][x+xOffset].color != this.color){
                    addMove(result, x+xOffset, y+yOffset);
                }
            }
            switch(i%2){
                case 0:
                    xOffset *= -1;
                    break;
                case 1:
                    yOffset *= -1;
                    break;
            }
            if(i == 3){
                xOffset = 1;
                yOffset = 2;
            }
        }
        if(checking){
            pruneInvalid(moves);
        }

        return result;
    }
}
