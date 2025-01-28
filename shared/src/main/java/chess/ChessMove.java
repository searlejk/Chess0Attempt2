package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    private ChessPosition start;
    private ChessPosition end;
    private ChessPiece.PieceType promo;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.start = startPosition;
        this.end = endPosition;
        this.promo = promotionPiece;
    }

    @Override
    public String toString() {
        return "Move: "+start+" to "+end+".";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start,this.end,this.promo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!=this.getClass()){
            return false;
        }
        if (obj==null){
            return false;
        }
        if (obj==this){
            return true;
        }
        ChessMove temp = (ChessMove)obj;
        boolean test1 = this.getStartPosition().getRow()==temp.getStartPosition().getRow();
        boolean test2 = this.getStartPosition().getColumn()==temp.getStartPosition().getColumn();
        boolean test3 = this.getEndPosition().getRow()==temp.getEndPosition().getRow();
        boolean test4 = this.getEndPosition().getColumn()==temp.getEndPosition().getColumn();
        boolean test5 = this.getPromotionPiece()==temp.getPromotionPiece();

        if (test1 && test2 && test3 && test4 && test5){
            return true;
        }
        return false;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.start;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.end;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promo;
    }
}
