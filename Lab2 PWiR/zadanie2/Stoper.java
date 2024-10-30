import java.util.Timer;
import java.util.TimerTask;

public class Stoper {
    private double czas;
    private boolean jestUruchomiony;
    private boolean jestWstrzymany;
    private Timer timer;

    public Stoper() {
        this.czas = 0.0;
        this.jestUruchomiony = false;
        this.jestWstrzymany = false;
    }

    public void uruchom() {
        if (!jestUruchomiony || jestWstrzymany) {
            jestUruchomiony = true;
            jestWstrzymany = false;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    czas += 0.1;
                    System.out.printf("Czas: %.1f s%n", czas);
                }
            }, 0, 100);
            System.out.println("Stoper uruchomiony.");
        } else {
            System.out.println("Stoper już jest uruchomiony.");
        }
    }

    public void wstrzymaj() {
        if (jestUruchomiony && !jestWstrzymany) {
            jestWstrzymany = true;
            timer.cancel();
            System.out.println("Stoper wstrzymany.");
        }
    }

    public void wznow() {
        if (jestWstrzymany) {
            uruchom();
            System.out.println("Stoper wznowiony.");
        } else {
            System.out.println("Stoper nie jest wstrzymany, nie można wznowić.");
        }
    }

    public void zresetuj() {
        if (jestUruchomiony) {
            timer.cancel();
        }
        czas = 0.0;
        jestUruchomiony = false;
        jestWstrzymany = false;
        System.out.println("Stoper zresetowany.");
    }

    public double getCzas() {
        return czas;
    }
}
