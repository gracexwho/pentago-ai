package student_player;

import java.util.ArrayList;
import java.util.Random;

import boardgame.Move;

import pentago_twist.PentagoPlayer;
import pentago_twist.PentagoBoardState.Piece;
import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoMove;


/** A player file submitted by a student. */
public class StudentPlayer extends PentagoPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("STUDENT_NUMBER");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(PentagoBoardState boardState) {
        // You probably will make separate functions in MyTools.
        // For example, maybe you'll need to load some pre-processed best opening
        // strategies...
        Piece[][] board = boardState.getBoard();

        int player_id = boardState.getTurnPlayer();

        if (boardState.getTurnNumber() == 0) {
            // First Move
            return openingMove(boardState, board, player_id);
        }
        
        Move myMove = AlphaBeta.minimaxDecision(boardState, player_id);

        // Return your move to be processed by the server.
        return myMove;
    }

    public Move openingMove(PentagoBoardState boardState, Piece[][] board, int player_id) {
        // public PentagoMove(int x, int y, int aSwap, int bSwap, int playerId)
        ArrayList<PentagoMove> openingMoves = new ArrayList<PentagoMove>();
        
        if (board[1][1] == Piece.EMPTY) {
            openingMoves.add(new PentagoMove(1, 1, 0, 0, player_id));
        } 
        if (board[1][4] == Piece.EMPTY) {
            openingMoves.add(new PentagoMove(1, 4, 0, 0, player_id));
        } 
        if (board[4][1] == Piece.EMPTY) {
            openingMoves.add(new PentagoMove(4, 1, 0, 0, player_id));
        } 
        if (board[4][4] == Piece.EMPTY) {
            openingMoves.add(new PentagoMove(4, 4, 0, 0, player_id));
        } 

        Random rand = new Random();
        return openingMoves.get(rand.nextInt(openingMoves.size()));
    }


}