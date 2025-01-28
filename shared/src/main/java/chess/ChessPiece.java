package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private PieceType type;
    private ChessGame.TeamColor color;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.color = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        PieceType type = board.getPiece(myPosition).getPieceType();
        Collection<ChessMove> moves = new ArrayList<>();

        if (type==PieceType.BISHOP){
            BishopMoveCalc B_calc = new BishopMoveCalc(board,myPosition);
            moves = B_calc.pieceMoves(board,myPosition);
            return moves;
        }
        if (type==PieceType.ROOK){
            RookMoveCalc R_calc = new RookMoveCalc(board,myPosition);
            moves = R_calc.pieceMoves(board,myPosition);
            return moves;
        }
        if (type==PieceType.QUEEN){
            RookMoveCalc R_calc = new RookMoveCalc(board,myPosition);
            moves = R_calc.pieceMoves(board,myPosition);
            BishopMoveCalc B_calc = new BishopMoveCalc(board,myPosition);
            for (ChessMove move : B_calc.pieceMoves(board,myPosition)){
                moves.add(move);
            }
            return moves;

        }
        if (type==PieceType.KING){
            KingMoveCalc K_calc = new KingMoveCalc(board,myPosition);
            moves = K_calc.pieceMoves(board,myPosition);
            return moves;
        }
        if (type==PieceType.KNIGHT){
            KnightMoveCalc Knight_calc = new KnightMoveCalc(board,myPosition);
            moves = Knight_calc.pieceMoves(board,myPosition);
            return moves;
        }
        if (type==PieceType.PAWN){
            PawnMoveCalc P_calc = new PawnMoveCalc(board,myPosition);
            moves = P_calc.pieceMoves(board,myPosition);
            return moves;
        }


        return new ArrayList<>();
    }
}
