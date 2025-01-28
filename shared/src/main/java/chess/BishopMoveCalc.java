package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class BishopMoveCalc {
    private ChessBoard board;
    private ChessPosition pos;

    public BishopMoveCalc(ChessBoard board, ChessPosition pos) {
        this.board = board;
        this.pos = pos;
    }


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        ChessGame.TeamColor color = piece.getTeamColor();
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        int inc1 = 1;
        int inc2 = 1;

        int checkRow = row+inc1;
        int checkCol = col+inc1;

        boolean loop = true;
        boolean reset = false;


        while (loop){

            ///  if move is on the board
            if (checkRow >=1 && checkRow <= 8 && checkCol >=1 && checkCol <= 8 && !reset){
                ChessMove move = new ChessMove(pos,new ChessPosition(checkRow,checkCol),null);
                if (board.getPiece(new ChessPosition(checkRow,checkCol))!=null){
                    if (board.getPiece(new ChessPosition(checkRow,checkCol)).getTeamColor() == color){
                        reset = true;
                    } else {
                        moves.add(move);
//                        int[] bothIncs = this.incUpdate(inc1,inc2);
//                        inc1 += bothIncs[0];
//                        inc2 += bothIncs[1];
//                        checkRow = row+inc1;
//                        checkCol = col+inc2;
                        reset = true;
                    }

                }else{
                    moves.add(move);
                    int[] bothIncs = this.incUpdate(inc1,inc2);
                    inc1 += bothIncs[0];
                    inc2 += bothIncs[1];
                    checkRow = row+inc1;
                    checkCol = col+inc2;
                }
            }
            else{
                /// reset incs code goes here
                int[] bothIncs = this.incReset(inc1,inc2);
                inc1 = bothIncs[0];
                inc2 = bothIncs[1];
                checkRow = row+inc1;
                checkCol = col+inc2;

                reset = false;
                if (inc1==0 && inc2==0){
                    break;
                }

            }


        }


        return moves;
    }

    private int[] incUpdate(int inc1,int inc2){
        if (inc1>0 & inc2>0){
            return new int[]{1,1};
        }
        if (inc1>0 & inc2<0){
            return new int[]{1,-1};
        }
        if (inc1<0 & inc2>0){
            return new int[]{-1,1};
        }
        if (inc1<0 & inc2<0){
            return new int[]{-1,-1};
        }
        System.out.print("You broke incUpdate in Bishop");
        return new int[]{9,9};
    }

    private int[] incReset(int inc1,int inc2){
        if (inc1>0 & inc2>0){
            return new int[]{1,-1};
        }
        if (inc1>0 & inc2<0){
            return new int[]{-1,1};
        }
        if (inc1<0 & inc2>0){
            return new int[]{-1,-1};
        }
        if (inc1<0 & inc2<0){
            return new int[]{0,0};
        }
        System.out.print("You broke incReset in Bishop");
        return new int[]{9,9};
    }

}
