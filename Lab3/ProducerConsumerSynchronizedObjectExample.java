package pwir.Lab3;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerSynchronizedObjectExample {
    private static final int BUFFER_SIZE = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    synchronized (buffer) {
                        while (buffer.size() == BUFFER_SIZE) {// Sprawdzanie czy Buffor jest pełny
                            System.out.println("Buffer jest pełny. Konsument przechodzi w stan WAITING...");
                            buffer.wait(); // metoda wait() na obiekcie sychronizowanym zmienia stan wątku na WAITING
                        }

                        buffer.add(i);
                        System.out.println("Producnet dodał:  " + i);

                        buffer.notifyAll(); // Powiadom konsumenta Jeśli Konsument ma stan WAITING zmień go na RUNNABLE
                    }
                    Thread.sleep(1000); // Symulacja czasu produkcji
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) { // Sprawdzanie czy Buffor jest pusty
                            System.out.println("Buffer jest pusty. Konsument przechodzi w stan WAITING...");
                            buffer.wait(); // metoda wait() na obiekcie sychronizowanym zmienia stan wątku na WAITING
                        }

                        int item = buffer.poll();
                        System.out.println("Kosument pobrał: " + item);

                        buffer.notifyAll(); // Powiadom producenta. Jeśli Producnet ma stan WAITING zmień go na RUNNABLE
                    }
                    Thread.sleep(2000); // Symulacja czasu konsumpcji
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
