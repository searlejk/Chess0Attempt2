package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class PawnMoveCalc {
    private ChessBoard board;
    private ChessPosition pos;

    public PawnMoveCalc(ChessBoard board, ChessPosition pos) {
        this.board = board;
        this.pos = pos;
    }


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        ChessGame.TeamColor color = piece.getTeamColor();
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ArrayList<ChessPosition> PosList = this.colorMoves(color);

        for (ChessPosition pos : PosList){
            if (pos.getColumn()>8 | pos.getRow()>8 | pos.getColumn()<1 | pos.getRow()<1){
                continue;
            }
            ChessMove move = new ChessMove(myPosition,pos,null);
            if (board.getPiece(pos)!=null){
                if (board.getPiece(pos).getTeamColor()==color){
                    continue;
                }
                else{
                    if (row+1 == pos.getRow() && color == WHITE && col!=pos.getColumn()){
                        continue;
                    }
                    if (row+2 == pos.getRow() && color == WHITE){
                        if (board.getPiece(new ChessPosition(row+1,col))!=null){
                            continue;
                        }else{
                            moves.add(move);
                            continue;
                        }
                    }
                    if (row-1 == pos.getRow() && color == BLACK && col!=pos.getColumn()){
                        continue;
                    }
                    if (row-2 == pos.getRow() && color == BLACK){
                        if (board.getPiece(new ChessPosition(row-1,col))!=null){
                            continue;
                        }else{
                            moves.add(move);
                            continue;
                        }
                    }

                    moves.add(move);
                }
            }else{
                if (col==pos.getColumn()){
                    moves.add(move);
                }
                else {
                    continue;
                }
            }
        }

        return moves;
    }

    public ArrayList<ChessPosition> colorMoves(ChessGame.TeamColor color){
        ArrayList<ChessPosition> PosList = new ArrayList<>();
        int row = pos.getRow();
        int col = pos.getColumn();

        if (color== WHITE){

            PosList.add(new ChessPosition(row+1,col));
            PosList.add(new ChessPosition(row+1,col+1));
            PosList.add(new ChessPosition(row+1,col-1));
            PosList.add(new ChessPosition(row+2,col));
            return PosList;
        }
        else if (color== BLACK){

            PosList.add(new ChessPosition(row-1,col));
            PosList.add(new ChessPosition(row-1,col+1));
            PosList.add(new ChessPosition(row-1,col-1));
            PosList.add(new ChessPosition(row-2,col));
            return PosList;

        }
        return PosList;
    }
}
