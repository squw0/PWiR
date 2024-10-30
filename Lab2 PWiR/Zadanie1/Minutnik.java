import java.util.Timer;
import java.util.TimerTask;

public class Minutnik {
    private double czasWSekundach;
    private double aktualnyCzas;
    private boolean jestUruchomiony;
    private boolean jestWstrzymany;
    private Timer timer;
    
    public Minutnik(double czasWSekundach) {
        this.czasWSekundach = czasWSekundach;
        this.aktualnyCzas = czasWSekundach;
        this.jestUruchomiony = false;
        this.jestWstrzymany = false;
    }

    public void ustawCzas(double czasWSekundach) {
        this.czasWSekundach = czasWSekundach;
        this.aktualnyCzas = czasWSekundach;
        System.out.println("Czas ustawiony na: " + czasWSekundach + " sekund.");
    }

    public void uruchom() {
        if (!jestUruchomiony || jestWstrzymany) {
            jestUruchomiony = true;
            jestWstrzymany = false;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (aktualnyCzas > 0) {
                        aktualnyCzas -= 0.1;
                        System.out.printf("Pozostały czas: %.1f sekund%n", aktualnyCzas);
                    } else {
                        zatrzymaj();
                        System.out.println("Minutnik zakończył odliczanie.");
                    }
                }
            }, 0, 100);
            System.out.println("Minutnik uruchomiony.");
        }
    }

    public void wstrzymaj() {
        if (jestUruchomiony && !jestWstrzymany) {
            jestWstrzymany = true;
            timer.cancel();
            System.out.println("Minutnik wstrzymany.");
        }
    }

    public void wznow() {
        if (jestWstrzymany) {
            uruchom();
            System.out.println("Minutnik wznowiony.");
        }
    }

    public void zatrzymaj() {
        if (jestUruchomiony) {
            timer.cancel();
            jestUruchomiony = false;
            System.out.println("Minutnik zatrzymany.");
        }
    }

    public void ponownieUruchom() {
        zatrzymaj();
        aktualnyCzas = czasWSekundach;
        uruchom();
        System.out.println("Minutnik ponownie uruchomiony.");
    }
}
