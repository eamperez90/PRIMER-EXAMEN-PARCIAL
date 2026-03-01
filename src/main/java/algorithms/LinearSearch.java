package algorithms;

public class LinearSearch {

    public static int iterativo(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }
        return -1;
    }

    public static int recursivo(int[] arr, int x, int i) {
        if (i >= arr.length) return -1;
        if (arr[i] == x) return i;
        return recursivo(arr, x, i + 1);
    }
}
