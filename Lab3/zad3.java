package Lab3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Buffor {

    // wazone filtry medianowe egzamin

    private List<Integer> listA = new ArrayList<>();
    private List<Integer> listB = new ArrayList<>();
    private final int capacityA= 10;
    private final int capacityB=10;

    public synchronized void produceA(int number) throws InterruptedException{
        while(listA.size()==capacityA){
            System.out.println("Buffor jest pełny czekamy");
            wait();
        }
        listA.add(number);
        //System.out.println("Dodano do buffor a: "+number);
        notify();
    }

    public synchronized void produceB(int number) throws InterruptedException{
        while(listB.size()==capacityB){
            System.out.println("Buffor jest pełny czekamy");
            wait();
        }
        listB.add(number);
        //System.out.println("Dodano do buffor b"+number);
        notify();
    }

    public synchronized Integer consumeA() throws InterruptedException{
        while(listA.isEmpty()){
            System.out.println("Buffor jest pusty czekamy");
            wait();
        }
        int number = listA.remove(0);
        notifyAll();
        return number;
    }

    public synchronized Integer consumeB() throws InterruptedException{
        while(listB.isEmpty()){
            System.out.println("Buffor jest pusty czekamy");
            wait();
        }
        int number = listB.remove(0);
        notifyAll();
        return number;
    }


}

class ProducentA implements Runnable {

    private Buffor buffor;
    //private int randomA;

    Random rand = new Random();

    public ProducentA(Buffor buffor){ // generuje A
        this.buffor=buffor;
        //this.randomA=randomA;
    }

    @Override
    public void run(){
        try {
            while (true) { 
                int number = rand.nextInt(2);
                buffor.produceA(number);
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }
}

class ProducentB implements Runnable { // generuje b

    private Buffor buffor;
    //private int randomB;
    Random rand = new Random();

    public ProducentB(Buffor buffor){
        this.buffor=buffor;
        //this.randomB=randomB;
    }

    @Override
    public void run(){
        try {
            while (true){
                int number = rand.nextInt(2);
                buffor.produceB(number);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {}
    }
}

class Konsument implements Runnable { // oblicza a/b

    private Buffor buffor;
    //private Array ProduceA;
    //private Array ProduceB;
    private int liczbaoperacji;
    
    public Konsument(Buffor buffor, int liczbaoperacji){
        this.buffor=buffor;
        //this.ProduceA=ProduceA;
        //this.ProduceB=ProduceB;
        this.liczbaoperacji=liczbaoperacji;
    }

    // private boolean wiekszeOdZero(int number){
    //     if(number!=0){
    //         return true;
    //     }
    //     else{
    //         return false;
    //     }
    // }

    // private int sumaA(int suma){
    //     int sumaA = 0;
    //     for(int i : ProduceA){
    //         sumaA+=i;
    //     }
    //     return sumaA;

    // }

    // private int sumaB(int suma){
    //     int sumaB = 0;
    //     for(int i : ProduceB){
    //         sumaB+=i;
    //     }
    //     return sumaB;

    // }

    // @Override
    // public void run(){
    //     try {
    //         //Integer numberA = buffor.consumeA(); 
    //         while (true) { 
    //             if(wiekszeOdZero(number)){
    //                 //return 
    //                 Thread.sleep(1000);
                    
    //             }
    //             else{
    //                 System.out.println("Nie można dzielić przez 0");
    //                 Thread.sleep(5000);
    //             }
    //         }
    //     } catch (Exception e) {
    //     }
    // }

    public void run(){
        try {
            for (int i = 0; i < liczbaoperacji; i++) {
                int a = buffor.consumeA();
                int b = buffor.consumeB();
                if(b==0){
                    System.out.println("Nie można dzielić przez 0");
                }
                else{
                    //System.out.println(a+a);
                    System.out.println("Dzielimy "+a+" / "+b+" = "+ (double) a / b);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}

public class zad3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe operacji: ");
        int liczbaoperacji = scanner.nextInt();

        Buffor buffor = new Buffor();

        Thread producenta = new Thread(new ProducentA(buffor));
        Thread producentb = new Thread(new ProducentB(buffor));
        Thread konsument = new Thread(new Konsument(buffor, liczbaoperacji));

        producenta.start();
        producentb.start();
        konsument.start();

    }
}


