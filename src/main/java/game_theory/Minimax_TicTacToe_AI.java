package game_theory;

public class Minimax_TicTacToe_AI {
    static char player = 'x';
    static char opponent = 'o';

    static class Move {
        int row;
        int col;

        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // This function returns true if there are moves
    // remaining on the board. It returns false if
    // there are no moves left to play.
    static Boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return true;
                }
            }
        }

        return false;
    }

    // This is evaluation function
    static int evaluate(char [][] board) {

        // Checking for rows for X or 0 victory.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == player) {
                    return 10;
                } else if (board[row][0] == opponent) {
                    return -10;
                }
            }
        }

        // Checking for Columns for X or 0 victory.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == player) {
                    return 10;
                } else if (board[0][col] == opponent) {
                    return -10;
                }
            }
        }

        // Checking for Diagonals for X or 0 victory
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == player) {
                return 10;
            } else if (board[0][0] == opponent) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == player) {
                return 10;
            } else if (board[0][2] == opponent) {
                return -10;
            }
        }
        return 0;
    }

    // This is the the minimax Function
    // It considers all the possible ways the game can go and returns the value of the board
    static int minimax(char [][] board, int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his evaluated score
        if (score == 10) {
            return score - depth; // small optimization
        }

        // If Minimizer has won the game
        // return his evaluated score
        if (score == -10) {
            return score + depth; // small optimization
        }

        // If there are no more moves and no winner then it is a tie.
        if (isMovesLeft(board) == false) {
            return 0;
        }

        // If this Maximizer's move
        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = player;
                        best = Math.max(best, minimax(board, depth+1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }

            return best;
        } else {
            // If this minimizer's move
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = opponent;
                        best = Math.min(best, minimax(board, depth+1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove(char [][] board) {
        int bestVal = Integer.MIN_VALUE;

        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == '_') {
                    board[i][j] = player;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '_';

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return bestMove;
    }

    public static void main(String[] args) {
        char [][] board = {{'x', 'o', 'x'},
                            {'o', 'o', 'x'},
                            {'_', '_', '_'}};

        Move bestMove = findBestMove(board);

        System.out.printf("The Optimal Move is : \n");
        System.out.printf("ROW: %d COL: %d\n\n",
                bestMove.row, bestMove.col );
    }
}
