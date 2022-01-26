import java.util.ArrayList;

public class CPU {
    public static void nextMove(Piece[][] Board){
        double[][] moveInfo = MinMax(Board, 2, Main.turn);
        Main.board.board[(int) moveInfo[0][0]][(int)moveInfo[0][1]].move((int)moveInfo[1][0], (int)moveInfo[1][1]);;
        
    }

    public static double[][] MinMax(Piece[][] board, int depth, int turn){
    	if(depth == 0) {
    		double[][] result = {{-1,-1}, {-1, -2}, {Eval(board), 0}};
    		return result;
    	}
    	
    	double[][] result = new double[3][2];
    	
    		result[2][0] = 100 * Math.pow(-1, turn+1);
    		for(Piece[] row : board) {
    			for(Piece piece : row) {
    				if(piece != null) {
    					ArrayList<Integer[]> moves = piece.genMoves(board, false);
    					piece.pruneInvalid(moves);
    					for(Integer[] move : moves) {
    						Piece[][] futureBoard = piece.arraycopy(Main.board.board);
    		                futureBoard[move[1]][move[0]] = piece;
    		                futureBoard[piece.y/100][piece.x/100] = null;
    		                double[][] temp = MinMax(futureBoard, depth-1, turn+1);
    		                if(turn%2 == 0) {
    		                	if(temp[2][0] > result[2][0]) {
        		                	result[2][0] = temp[2][0];
        		                	result[0][0] = piece.x/100;
        		                	result[0][1] = piece.y/100;
        		                	result[1][0] = move[0];
        		                	result[1][1] = move[1];
        		                }
    		                } else {
    		                	if(temp[2][0] < result[2][0]) {
    		                		result[2][0] = temp[2][0];
        		                	result[0][0] = piece.x/100;
        		                	result[0][1] = piece.y/100;
        		                	result[1][0] = move[0];
        		                	result[1][1] = move[1];
        		                }
    		                }
    		                
    					}
    				}
    			}
    		}
    	
        return result;
    }
    
    public static double Eval(Piece[][] board) {
    	double Eval = 0;
    	for(Piece[] row : board) {
    		for(Piece piece : row) {
    			if(piece != null) {
    				switch(piece.Name) {
    				case "Pawn":
                        Eval += 1 * Math.pow(-1, piece.color);
                        break;
                        case "Rook":
                        Eval += 5 * Math.pow(-1, piece.color);
                        break;
                        case "Knight":
                        Eval += 3 * Math.pow(-1, piece.color);
                        break;
                        case "Bishop":
                        Eval += 3 * Math.pow(-1, piece.color);
                        break;
                        case "Queen":
                        Eval += 9 * Math.pow(-1, piece.color);
                        break;
    				}
    			}
    		}
    	}
    	return Eval;
    }


}
