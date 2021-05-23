package student_player;


import pentago_twist.PentagoBoardState.Piece;
import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoCoord;


public class MyToolsIntermediate {

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
            int empty = 0;
            int totalLine = 0;
            for (int y =0; y < board[0].length;y++) {
                if (playerColour == board[x][y]) {
                    streak += 1;
                    totalLine += 1;
                } else {
                    total += streak * streak;
                    
                    if (board[x][y] == Piece.EMPTY) {
                        empty += 1;
                    }

                    streak = 0;
                }
            }
            if (totalLine + empty == 6 && totalLine >= 4) {
                total += totalLine;
            }
        }
        return total;
    }


    public static int checkVerticalStreak(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        for (int y = 0; y < board[0].length;y++) {
            int streak = 0;
            int empty = 0;
            int totalLine = 0;
            for (int x=0; x < board.length;x++) {
                if (playerColour == board[x][y]) {
                    streak += 1;
                    totalLine += 1;
                } else {
                    total += streak * streak;
                    if (board[x][y] == Piece.EMPTY) {
                        empty += 1;
                    }
                    streak = 0;
                }
            }
            if (totalLine + empty == 6 && totalLine >= 4) {
                total += totalLine;
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
        int empty = 0;
        int totalLine = 0;
        for (int x = 0; x < board[0].length;x++) {
            int y = x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }

        }
                
        if (totalLine + empty == 6 && totalLine >= 4) {
            total += totalLine;
        }

        // One of the two smaller diagonals
        streak = 0;
        empty = 0;
        totalLine = 0;
        for (int x = 0; x < board[0].length-1;x++) {
            int y = x+1;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }

        }
        if (totalLine + empty == 5 && totalLine >= 4) {
            total += totalLine;
        }

        streak = 0;
        empty = 0;
        totalLine = 0;
        // The other small diagonal
        for (int x = 1; x < board[0].length;x++) {
            int y = x-1;
            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }
        }
        if (totalLine + empty == 5 && totalLine >= 4) {
            total += totalLine;
        }

        return total;
    }

    // Only need to check the three diagonals whose length >= 5
    public static int checkDiagonalStreak2(Piece[][] board, int player) {
        Piece playerColour = player == 0 ? Piece.WHITE : Piece.BLACK;
        
        int total = 0;

        // Biggest Diagonal
        int streak = 0;
        int empty = 0;
        int totalLine = 0;
        for (int x = board[0].length-1; x >=0;x--) {
            int y = board[0].length-1-x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }

        }
                
        if (totalLine + empty == 6 && totalLine >= 4) {
            total += totalLine;
        }

        // One of the two smaller diagonals
        streak = 0;
        empty = 0;
        totalLine = 0;
        for (int x = board[0].length-2; x >=0;x--) {
            int y = board.length-2-x;

            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }

        }
        if (totalLine + empty == 5 && totalLine >= 4) {
            total += totalLine;
        }

        streak = 0;
        empty = 0;
        totalLine = 0;
        // The other small diagonal
        for (int x = board[0].length-1; x >=1;x--) {
            int y = board[0].length-x;
            if (playerColour == board[x][y]) {
                streak += 1;
                totalLine += 1;
            } else {
                total += streak * streak;
                if (board[x][y] == Piece.EMPTY) {
                    empty += 1;
                }
                streak = 0;
            }
        }
        if (totalLine + empty == 5 && totalLine >= 4) {
            total += totalLine;
        }

        return total;
    }

}
