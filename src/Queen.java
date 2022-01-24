import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int Id, int color, int x, int y) {
        super(Id, color, x, y);
        this.state = 0;
        String path = "/imgs/Pieces/";
        if(color == 0){
            path += "W";
        } else{
            path += "B";
        }
        path += "_Queen.png";
        Sprite = getImage(path);
        Name = "Queen";
    }

    public Queen(Piece piece) {
        super(piece);
    }

    @Override
    public ArrayList<Integer[]> genMoves(Piece[][] board, boolean checking){
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        Integer[] temp = new Integer[2];
        int x = this.x / 100;
        int y = this.y / 100;

        int offset = 1;
        while(placeEmpty(board, x, y+offset)){
            temp[0] = x;
            temp[1] = y + offset;
            result.add(temp);
            temp = new Integer[2];

            offset++;
        }
        if(y+offset <= 7){
            if(board[y+offset][x].color != this.color){
                temp[0] = x;
                temp[1] = y + offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = -1;
        while(placeEmpty(board, x, y+offset)){
            temp[0] = x;
            temp[1] = y + offset;
            result.add(temp);
            temp = new Integer[2];

            offset--;
        }
        if(y+offset >= 0){
            if(board[y+offset][x].color != this.color){
                temp[0] = x;
                temp[1] = y + offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = 1;
        while(placeEmpty(board, x+offset, y)){
            temp[0] = x+offset;
            temp[1] = y;
            result.add(temp);
            temp = new Integer[2];

            offset++;
        }
        if(x+offset <= 7){
            if(board[y][x+offset].color != this.color){
                temp[0] = x+offset;
                temp[1] = y;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = -1;
        while(placeEmpty(board, x+offset, y)){
            temp[0] = x+offset;
            temp[1] = y;
            result.add(temp);
            temp = new Integer[2];

            offset--;
        }
        if(x+offset >= 0){
            if(board[y][x+offset].color != this.color){
                temp[0] = x+offset;
                temp[1] = y;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = 1;
        while(placeEmpty(board, x+offset, y+offset)){
            temp[0] = x + offset;
            temp[1] = y + offset;
            result.add(temp);
            temp = new Integer[2];

            offset++;
        }
        if(y+offset <= 7 && x+offset <= 7){
            if(board[y+offset][x+offset].color != this.color){
                temp[0] = x + offset;
                temp[1] = y + offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = -1;
        while(placeEmpty(board, x+offset, y+offset)){
            temp[0] = x + offset;
            temp[1] = y + offset;
            result.add(temp);
            temp = new Integer[2];

            offset--;
        }
        if(y+offset >= 0 && x+offset >= 0){
            if(board[y+offset][x+offset].color != this.color){
                temp[0] = x + offset;
                temp[1] = y + offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = 1;
        while(placeEmpty(board, x+offset, y-offset)){
            temp[0] = x + offset;
            temp[1] = y - offset;
            result.add(temp);
            temp = new Integer[2];

            offset++;
        }
        if(y-offset >= 0 && x+offset <= 7){
            if(board[y-offset][x+offset].color != this.color){
                temp[0] = x + offset;
                temp[1] = y - offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }

        offset = -1;
        while(placeEmpty(board, x+offset, y-offset)){
            temp[0] = x + offset;
            temp[1] = y - offset;
            result.add(temp);
            temp = new Integer[2];

            offset--;
        }
        if(y-offset <= 7 && x+offset >= 0){
            if(board[y-offset][x+offset].color != this.color){
                temp[0] = x + offset;
                temp[1] = y - offset;
                result.add(temp);
                temp = new Integer[2];
            }
        }
        
        if(checking){
            pruneInvalid(moves);
        }
        
        return result;
    }
}
