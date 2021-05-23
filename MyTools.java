package student_player;

import java.util.function.UnaryOperator;

import boardgame.Move;

import pentago_twist.PentagoPlayer;
import pentago_twist.PentagoBoardState.Piece;
import pentago_twist.PentagoBoard;
import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoCoord;


public class MyTools {

    public static int getStreak(PentagoBoardState pbs, int player, PentagoCoord start) {
        int streaks = 0;
        Piece[][] board = pbs.getBoard();
        
        streaks += checkVerticalStreak(board, player);
        streaks += checkHorizontalStreak(board, player);
        streaks += checkDiagonalStreak(board, player);
        streaks += checkDiagonalStreak2(board, player);
        return streaks;
    }

    public static int checkHorizontalStreak(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        for (int x = 0; x < board.length;x++) {
            int streak = 0;
            int maxStreak = 0;
            int totalLine = 0;
            for (int y =0; y < board[0].length;y++) {
                if (playerColour == board[x][y]) {
                    streak += 1;
                    totalLine += 1;
                } else {
                    total += streak * streak;
                    streak = 0;
                }
            }
            if (totalLine >= 4 && maxStreak < 4) {
                // there was a gap in-between but it should still be equally valid
                total -= maxStreak * maxStreak;
                total += totalLine * totalLine;
            }
        }
        return total;
    }


    public static int checkVerticalStreak(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        for (int y = 0; y < board[0].length;y++) {
            int streak = 0;
            int maxStreak = 0;
            int totalLine = 0;
            for (int x=0; x < board.length;x++) {
                if (playerColour == board[x][y]) {
                    streak += 1;
                    totalLine += 1;
                } else {
                    total += streak * streak;
                    maxStreak = Math.max(maxStreak, streak);
                    streak = 0;
                }
            }
            if (totalLine >= 4 && maxStreak < 4) {
                // there was a gap in-between but it should still be equally valid
                total -= maxStreak * maxStreak;
                total += totalLine * totalLine;
            }
        }
        return total;
    }


    // Only need to check the three diagonals whose length >= 5
    public static int checkDiagonalStreak(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        // Biggest Diagonal
        int streak = 0;
        int maxStreak = 0;
        int totalLine = 0;
        for (int x = 0; x < board[0].length;x++) {
            int y = x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }

        }
                
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        // One of the two smaller diagonals
        streak = 0;
        maxStreak = 0;
        totalLine = 0;
        for (int x = 0; x < board[0].length-1;x++) {
            int y = x+1;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }

        }
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        streak = 0;
        maxStreak = 0;
        totalLine = 0;
        // The other small diagonal
        for (int x = 1; x < board[0].length;x++) {
            int y = x-1;
            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }
        }
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        return total;
    }

    // Only need to check the three diagonals whose length >= 5
    public static int checkDiagonalStreak2(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        // Biggest Diagonal
        int streak = 0;
        int maxStreak = 0;
        int totalLine = 0;
        for (int x = board[0].length-1; x >=0;x--) {
            int y = board[0].length-1-x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }

        }
                
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        // One of the two smaller diagonals
        streak = 0;
        maxStreak = 0;
        totalLine = 0;
        for (int x = board[0].length-2; x >=0;x--) {
            int y = board.length-2-x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }

        }
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        streak = 0;
        maxStreak = 0;
        totalLine = 0;
        // The other small diagonal
        for (int x = board[0].length-1; x >=1;x--) {
            int y = board[0].length-x;
            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                maxStreak = Math.max(maxStreak, streak);
                streak = 0;
            }
        }
        if (totalLine >= 4 && maxStreak < 4) {
            total -= maxStreak * maxStreak;
            total += totalLine * totalLine;
        }

        return total;
    }

}
