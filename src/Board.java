
import java.awt.Graphics;

public class Board {
    
    Piece[][] board;

    public Board(){

        board = new Piece[8][8];

        //add pawns
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(i, 1, i, 1);
        }

        for(int i = 0; i < 8; i++){
            board[6][i] = new Pawn(i, 0, i, 6);
        }

        //add rest of pieces
        board[0][0] = new Rook  (0, 1, 0, 0);
        board[0][1] = new Knight(0, 1, 1, 0);
        board[0][2] = new Bishop(0, 1, 2, 0);
        board[0][3] = new Queen (0, 1, 3, 0);
        board[0][4] = new King  (0, 1, 4, 0);
        board[0][5] = new Bishop(1, 1, 5, 0);
        board[0][6] = new Knight(1, 1, 6, 0);
        board[0][7] = new Rook  (1, 1, 7, 0);

        board[7][0] = new Rook  (0, 0, 0, 7);
        board[7][1] = new Knight(0, 0, 1, 7);
        board[7][2] = new Bishop(0, 0, 2, 7);
        board[7][3] = new Queen (0, 0, 3, 7);
        board[7][4] = new King  (0, 0, 4, 7);
        board[7][5] = new Bishop(1, 0, 5, 7);
        board[7][6] = new Knight(1, 0, 6, 7);
        board[7][7] = new Rook  (1, 0, 7, 7);
    }

    public void paint(Graphics g){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] != null){
                    board[i][j].paint(g);
                }
            }
        }
    }

}
