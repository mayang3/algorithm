package game_theory;

public class Minimax_Alpha_Beta_Pruning {
    static int MAX = 1000;
    static int MIN = -1000;

    static int minimax(int depth, int nodeIndex, boolean maximizingPlayer, int[] values, int alpha, int beta) {
        // base case
        // leaf node is reached
        if (depth == 3) {
            return values[nodeIndex];
        }

        int best;

        if (maximizingPlayer) {
            best = MIN;

            // Recur for left and right children
            for (int i = 0; i < 2; i++) {
                int val = minimax(depth + 1, nodeIndex * 2 + i, false, values, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                if (beta <= alpha) {
                    break;
                }
            }

        } else {
            best = MAX;

            // Recur for left and right children
            for (int i = 0; i < 2; i++) {
                int val = minimax(depth + 1, nodeIndex * 2 + i, true, values, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                if (beta <= alpha) {
                    break;
                }
            }

        }

        return best;
    }

    public static void main(String[] args) {
        int values[] = {3, 5, 6, 9, 1, 2, 0, -1};
        System.out.println("The optimal value is : " +
                minimax(0, 0, true, values, MIN, MAX));
    }
}
