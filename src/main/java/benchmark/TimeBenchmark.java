package benchmark;

public class TimeBenchmark {

    // Promedio de 5 ejecuciones (no incluye inicialización en la medición)
    public static long medirPromedioNs(Runnable algoritmo) {
        long total = 0;
        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();
            algoritmo.run();
            long fin = System.nanoTime();
            total += (fin - inicio);
        }
        return total / 5;
    }
}
