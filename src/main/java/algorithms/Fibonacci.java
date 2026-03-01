package algorithms;

public class Fibonacci {

    public static long iterativo(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long t = a + b;
            a = b;
            b = t;
        }
        return b;
    }

    // Sin memoización (intencional, para observar el costo O(2^n))
    public static long recursivo(int n) {
        if (n <= 1) return n;
        return recursivo(n - 1) + recursivo(n - 2);
    }
}
