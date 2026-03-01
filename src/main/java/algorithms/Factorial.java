package algorithms;

public class Factorial {

    public static long iterativo(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }

    public static long recursivo(int n) {
        if (n <= 1) return 1;
        return n * recursivo(n - 1);
    }
}
