package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class KingMoveCalc {
    private ChessBoard board;
    private ChessPosition pos;

    public KingMoveCalc(ChessBoard board, ChessPosition pos) {
        this.board = board;
        this.pos = pos;
    }


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        ChessGame.TeamColor color = piece.getTeamColor();
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ArrayList<ChessPosition> PosList = new ArrayList<>();
        PosList.add(new ChessPosition(row+1,col+1));
        PosList.add(new ChessPosition(row-1,col-1));
        PosList.add(new ChessPosition(row-1,col+1));
        PosList.add(new ChessPosition(row+1,col-1));
        PosList.add(new ChessPosition(row+1,col));
        PosList.add(new ChessPosition(row,col+1));
        PosList.add(new ChessPosition(row,col-1));
        PosList.add(new ChessPosition(row-1,col));

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
                    moves.add(move);
                    continue;
                }
            }else{
                moves.add(move);
                continue;
            }
        }

        return moves;
    }
}
