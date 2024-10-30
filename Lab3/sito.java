package pwir.Lab3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// Przechowywanie liczb
public class sito {
    private  final int CAPACITY = 10;
    private List<Integer> list = new ArrayList<>();



// Produkowanie liczb począwszy od 2
// bufor pełny = ma stopować 
// przekazuje sito dalej do wątków 
    public synchronized void produce(int number) throws InterruptedException {}
    
    
    //list.add(number);


    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    synchronized (buffer) {
                        while (buffer.size() == CAPACITY) {// Sprawdzanie czy Buffor jest pełny
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





// // jeden reprezentuje liczbę pierwszą i zatrzymuje wszystkie podzielne przez liczbę pierwszą
    static class Consumer1 implements Runnable {
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


// drugi odbiera liczby niepodzielne jeżeli od początku go nie ma - jest tworzony
    static class Consumer2 implements Runnable {
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
