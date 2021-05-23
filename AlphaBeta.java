package student_player;

import java.util.ArrayList;
import java.util.HashMap;

import boardgame.Board;
import boardgame.Move;

import pentago_twist.PentagoPlayer;
import pentago_twist.PentagoBoardState.Piece;
import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoMove;
import pentago_twist.PentagoCoord;


/** Alpha-Beta Pruning Minimax Search **/
public class AlphaBeta {
    private static final int CUTOFF = 1;

    // cloned board state
    public static Move minimaxDecision(PentagoBoardState pbs, int player_id) {
        // player_id = 0 -> WHITE, player_id = 1 -> BLACK
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        ArrayList<PentagoMove> moves = pbs.getAllLegalMoves();
        int best_move_value = Integer.MIN_VALUE;
        PentagoMove bestMove = null;

        for (PentagoMove successor : moves) {
            PentagoBoardState boardClone = (PentagoBoardState) pbs.clone();
            int value = alphaBeta(player_id, player_id, boardClone, successor, alpha, beta, CUTOFF);
            if (value > best_move_value) {
                best_move_value = value;
                bestMove = successor;
            }
        }

        return bestMove;
    }


    // The state of the board after a given move
    public static int alphaBeta(int player, int player_id, PentagoBoardState pbs, PentagoMove state, int alpha, int beta, int depth) {
        
        pbs.processMove(state);
        int win = isWinningMove(player, pbs, state);
        if ((win == 1 && player == player_id)) {
            // I won
            return Integer.MAX_VALUE;
        }
        if ((win == -1 && player == player_id)) {
            // other player won
            return Integer.MIN_VALUE;
        }
        if (win == 0) {
            //draw 
            return 0;
        }

        /// HEURISTIC USED
        int currValue = MyToolsAdvanced.getStreak(pbs, player, state.getMoveCoord());
        if (player != player_id) {
            currValue = currValue * -1;
        }
        
        ArrayList<PentagoMove> moves = pbs.getAllLegalMoves();
        if (depth == 0 || moves.isEmpty()) {
            return currValue;
        }

        // I was the MAX player, my successors are MIN players
        if (player == player_id) {
            for (PentagoMove successor : moves) {
                PentagoBoardState boardClone = (PentagoBoardState) pbs.clone();
                player = 1 - player;
                int value = alphaBeta(player, player_id, boardClone, successor, alpha, beta, depth-1);
                if (value > beta) {
                    return beta;
                }
                if (value > alpha) {
                    alpha = value;
                }
            }
            return alpha;
        }
        else 
        // I was the MIN player, my successors are MAX players
        {
            for (PentagoMove successor : moves) {
                PentagoBoardState boardClone = (PentagoBoardState) pbs.clone();
                player = 1 - player;
                int value = alphaBeta(player, player_id, boardClone, successor, alpha, beta, depth-1);
                if (value <= alpha) {
                    return alpha;
                }
                if (value < beta) {
                    beta = value;
                }
            }
            return beta;
        }
    }

    public static int isWinningMove(int player, PentagoBoardState pbs, PentagoMove move) {
        if (pbs.getWinner() == player) {
            return 1;
        }
        if (pbs.getWinner() == 1 - player) {
            return -1;
        }
        if (pbs.getWinner() == Board.DRAW) {
            return 0;
        }
        return 9;
    }

}