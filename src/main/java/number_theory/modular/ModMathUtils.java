package number_theory.modular;

/**
 * @author neo82
 */
public class ModMathUtils {
    static int MOD = (int) 1e9 + 7;
    static final int MAX = 1000001;
    static long[] fact = new long[MAX];
    static long[] factInv = new long[MAX];

    public static long multiply(long a, long b) {
        return (a * b) % MOD;
    }

    public static void initFact() {
        fact[0] = factInv[0] = 1;

        for (int i = 1; i < MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
            factInv[i] = inv(fact[i]);
        }
    }

    public static long modExp(long base, long power) {
        if (power == 0) {
            return 1;
        }

        long cur = modExp(base, power / 2);
        cur = (cur * cur) % MOD;

        if (power == 1) {
            cur = (cur * base) % MOD;
        }

        return cur;
    }

    // modular inverse
    public static long inv(long base) {
        return modExp(base, MOD - 2);
    }

    public static long binomialCoefficient(int n, int k) {
        if (k > n) return 0;
        if (n < 0) return 0;
        if (k < 0) return 0;

        return fact[n] * factInv[k] % MOD * factInv[n - k] % MOD;
    }
}
