package app;

import algorithms.*;
import benchmark.TimeBenchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final Random RNG = new Random(123);

    public static void main(String[] args) throws Exception {
        File out = new File("data/results.csv");
        out.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out, false))) {
            bw.write("Algoritmo,Tamano,TiempoPromedioNs%n".formatted());

            runFactorial(bw);
            runFibonacci(bw);
            runLinearSearch(bw);
            runBubbleSort(bw);
        }

        System.out.println("OK -> Resultados exportados a: " + out.getPath());
        System.out.println("Siguiente paso: abrir data/results.csv en Excel y graficar.");
    }

    private static void runFactorial(BufferedWriter bw) throws Exception {
        int[] ns = {1000, 5000, 10000, 20000, 40000};

        for (int n : ns) {
            int safeN = Math.min(n, 20);

            long tIter = TimeBenchmark.medirPromedioNs(() -> Factorial.iterativo(safeN));
            long tRec  = TimeBenchmark.medirPromedioNs(() -> Factorial.recursivo(safeN));

            writeRow(bw, "Factorial_Iterativo", n, tIter);
            writeRow(bw, "Factorial_Recursivo", n, tRec);
        }
    }

    private static void runFibonacci(BufferedWriter bw) throws Exception {
        int[] ns = {5, 10, 15, 20, 25, 30, 35};

        for (int n : ns) {
            long tIter = TimeBenchmark.medirPromedioNs(() -> Fibonacci.iterativo(n));
            long tRec  = TimeBenchmark.medirPromedioNs(() -> Fibonacci.recursivo(n));

            writeRow(bw, "Fibonacci_Iterativo", n, tIter);
            writeRow(bw, "Fibonacci_Recursivo", n, tRec);
        }
    }

    private static void runLinearSearch(BufferedWriter bw) throws Exception {
        int[] sizes = {1000, 5000, 10000, 50000, 100000};

        for (int n : sizes) {
            int[] arr = randomArray(n);
            int target = arr[n / 2];

            long tIter = TimeBenchmark.medirPromedioNs(() -> LinearSearch.iterativo(arr, target));
            long tRec  = TimeBenchmark.medirPromedioNs(() -> LinearSearch.recursivo(arr, target, 0));

            writeRow(bw, "BusquedaLineal_Iterativo", n, tIter);
            writeRow(bw, "BusquedaLineal_Recursivo", n, tRec);
        }
    }

    private static void runBubbleSort(BufferedWriter bw) throws Exception {
        int[] sizes = {200, 400, 800, 1200, 1600};

        for (int n : sizes) {
            int[] base = randomArray(n);

            long tIter = TimeBenchmark.medirPromedioNs(() -> {
                int[] copy = Arrays.copyOf(base, base.length);
                BubbleSort.iterativo(copy);
            });

            long tRec = TimeBenchmark.medirPromedioNs(() -> {
                int[] copy = Arrays.copyOf(base, base.length);
                BubbleSort.recursivo(copy, copy.length);
            });

            writeRow(bw, "Burbuja_Iterativo", n, tIter);
            writeRow(bw, "Burbuja_Recursivo", n, tRec);
        }
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RNG.nextInt(n);
        return a;
    }

    private static void writeRow(BufferedWriter bw, String algoritmo, int tamano, long tiempoNs) throws Exception {
        bw.write(String.format("%s,%d,%d%n", algoritmo, tamano, tiempoNs));
    }
}
