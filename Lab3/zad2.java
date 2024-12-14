package Lab3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Buffor {

    private List<Integer> list = new ArrayList<>();

    public synchronized void produce(int number){
        System.out.println("W liscie znajduje sie : "+list.size());
        if(number != 0){
            list.add(number);
            System.out.println("Dodano: "+number);
        }
        else{
            System.out.println("Wyprodukowano 0: "+ list);
            list.clear();
        }
        
        notifyAll();
    }

    public synchronized Integer consume() throws InterruptedException {
        while (list.isEmpty()) {
            wait();
        }
        Integer number =  list.remove(0);
        notifyAll();
        return number;
    }
}

class Producent implements Runnable{

    private Buffor buffor;
    private int min;
    private int max;
    Random rand = new Random();

    public Producent(Buffor buffor, int min, int max){
        this.buffor = buffor;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run(){
        try {
            while (true) { 
                int random = rand.nextInt(10);
                buffor.produce(random);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
        }
    }
}

class Konsumer implements Runnable{

    private Buffor buffor;

    public Konsumer(Buffor buffor){
        this.buffor = buffor;
    }

    private boolean notEndsWithZero(int number){
        if(number != 0){
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void run(){
    }
}

public class zad2 {
    public static void main(String[] args) {
        int min = 0;
        int max = 9;
        Buffor buffor = new Buffor();

        Thread producent = new Thread(new Producent(buffor, min, max));
        Thread konsumer = new Thread(new Konsumer(buffor));

        producent.start();
        konsumer.start();

    }
}
