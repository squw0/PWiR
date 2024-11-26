import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

class Buffer {
    private double aValue = 0.0;
    private double bValue = 0.0;
    private boolean aUpdated = false;
    private boolean bUpdated = false; 

    public synchronized void produceA(double value) {
        aValue = value;
        aUpdated = true;
        notifyAll();
    }

    public synchronized void produceB(double value) {
        bValue = value;
        bUpdated = true;
        notifyAll();
    }

    public synchronized double[] consume() throws InterruptedException {
        while (!aUpdated || !bUpdated) {
            wait();
        }
        aUpdated = false;
        bUpdated = false;
        return new double[]{aValue, bValue};
    }
}

class GeneratorA implements Runnable {
    private final Buffer buffer;

    public GeneratorA(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                double randomValue = ThreadLocalRandom.current().nextDouble(0, 1);
                buffer.produceA(randomValue);
                Thread.sleep(ThreadLocalRandom.current().nextInt(10, 500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class GeneratorB implements Runnable {
    private final Buffer buffer;

    public GeneratorB(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                double randomValue = ThreadLocalRandom.current().nextDouble(0, 1);
                buffer.produceB(randomValue);
                Thread.sleep(ThreadLocalRandom.current().nextInt(10, 500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Konsument implements Runnable {
    private final Buffer buffer;
    private final int maxReads;
    private final AtomicBoolean stopFlag;

    public Konsument(Buffer buffer, int maxReads, AtomicBoolean stopFlag) {
        this.buffer = buffer;
        this.maxReads = maxReads;
        this.stopFlag = stopFlag;
    }

    @Override
    public void run() {
        double numerator = 0.0; 
        double denominator = 1.0;
        int reads = 0;

        try {
            while (reads < maxReads) {
                double[] values = buffer.consume();
                double a = values[0];
                double b = values[1];

                numerator += a;
                denominator *= b;

                if (Math.abs(denominator) < 1e-8) {
                    System.out.println("Mianownik bliski zera. Zatrzymuję obliczenia.");
                    stopFlag.set(true);
                    break;
                }

                System.out.printf("Odczyt %d: Aktualna wartość wyrażenia: %.8f%n", reads + 1, numerator / denominator);
                reads++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.printf("Końcowa wartość wyrażenia: %.8f%n", numerator / denominator);
            stopFlag.set(true);
        }
    }
}

public class Symulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj maksymalną liczbę odczytów: ");
        int maxReads = scanner.nextInt();

        Buffer buffer = new Buffer();
        AtomicBoolean stopFlag = new AtomicBoolean(false);

        Thread generatorA = new Thread(new GeneratorA(buffer));
        Thread generatorB = new Thread(new GeneratorB(buffer));
        Thread konsument = new Thread(new Konsument(buffer, maxReads, stopFlag));

        generatorA.start();
        generatorB.start();
        konsument.start();

        while (!stopFlag.get()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        generatorA.interrupt();
        generatorB.interrupt();
        konsument.interrupt();

        System.out.println("Program zakończył działanie.");
    }
}
