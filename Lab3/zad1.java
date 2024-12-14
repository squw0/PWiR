package Lab3;
import java.util.ArrayList;
import java.util.List;




class Buffer {

    private List<Integer> list = new ArrayList<>();
    private final int capacity = 10;

    public synchronized void produce(int number) throws InterruptedException{
        while(list.size()==capacity){
            System.out.println("Buffor jest peły czekamy");
            wait();
        }

        list.add(number);
        System.out.println("Wyprodukowano: "+number);
        notify();
    }

    public synchronized Integer consume() throws InterruptedException{
        while(list.isEmpty()){
            System.out.println("Buffor jest pusty czekamy");
            wait();
        }
        Integer number = list.remove(0);
        notify();
        return number;
    }
}

class Producer implements Runnable {
    private Buffer buffer;
    private int start;
    private int end;

    public Producer(Buffer buffer, int start, int end){
        this.buffer = buffer;
        this.end = end;
        this.start = start;
    }

    @Override
    public void run(){
        try{
            for (int i = start; i <= end ; i++) {
                buffer.produce(i);
                Thread.sleep(1000);
            }
        }
        catch (Exception e){}
    }
}

class Konsument implements Runnable {

    private Buffer buffer;
    public Konsument(Buffer buffer){
        this.buffer = buffer;
    }

    private boolean isPrime(int number){
        if(number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0 ) return false;
        }
        return true;
    }

    @Override
    public void run(){
        try {
            while (true) { 
                Integer number = buffer.consume();
                if(isPrime(number)){
                    System.out.println("Liczba jest pierwsza: "+number);
                    Thread.sleep(2000);
                }
                else{
                    System.out.println("Liczba nie jest pierwsza: "+number);
                    Thread.sleep(1000);
                }
                
            }
        } catch (Exception e) {
        }
    }
}
public class zad1 {
    public static void main(String[] args) {
        
        int start = 2;
        int end = 50;
        Buffer buffer = new Buffer();

        Thread producent = new Thread(new Producer(buffer, start, end));
        Thread konsument = new Thread(new Konsument(buffer));

        producent.start();
        konsument.start();
    }
}

