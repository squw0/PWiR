import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 10;
    public synchronized void produce(int number) throws InterruptedException {
        while (queue.size() == CAPACITY) { 
            System.out.println("Bufor pełny, producent czeka");
            wait();
        }

        queue.add(number);
        System.out.println("Wyprodukowano: " + number);
        notify();
    }
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) { 
            System.out.println("Bufor pusty, konsument czeka");
            wait(); 
        }

        int number = queue.poll();
        notify(); 
        return number;
    }
}

class Generator implements Runnable {
    private final Buffer buffer;
    public Generator(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
                buffer.produce(randomNum);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Lacznik implements Runnable {
    private final Buffer buffer;
    public Lacznik(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            StringBuilder currentNumber = new StringBuilder();

            while (true) {
                int number = buffer.consume();

                if (number == 0) {
                    if (currentNumber.length() > 0) {
                        System.out.println("Utworzono liczbę: " + currentNumber.append(0));
                        currentNumber.setLength(0);
                    }
                } else {
                    if (!(currentNumber.length() == 0 && number == 0)) {
                        currentNumber.append(number);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class zakZerem {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread generatorThread = new Thread(new Generator(buffer));
        Thread lacznikThread = new Thread(new Lacznik(buffer));
        generatorThread.start();
        lacznikThread.start();
    }
}
