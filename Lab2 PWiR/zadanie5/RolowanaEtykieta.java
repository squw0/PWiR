import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class RolowanaEtykieta extends JFrame {
    private String tekst;
    private String oryginalnyTekst;
    private int delay;
    private boolean jestWstrzymana = false;
    private Timer timer;
    private JLabel etykieta;
    private boolean kierunekPrawo;

    public RolowanaEtykieta(String tekst, int delay, boolean kierunekPrawo) {
        this.oryginalnyTekst = " ".repeat(20) + tekst + " ".repeat(20);
        this.tekst = this.oryginalnyTekst;
        this.delay = delay * 1000;
        this.kierunekPrawo = kierunekPrawo;
        setupUI();
    }

    private void setupUI() {
        etykieta = new JLabel(tekst, SwingConstants.CENTER);
        etykieta.setFont(new Font("Arial", Font.BOLD, 24));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.setLayout(new BorderLayout());
        this.add(etykieta, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void uruchom() {
        if (jestWstrzymana) {
            wznowTimer();
            jestWstrzymana = false;
            System.out.println("Rolowanie wznowione.");
        } else if (timer == null) {
            wznowTimer();
            System.out.println("Rolowanie uruchomione.");
        }
    }

    public void wstrzymaj() {
        if (timer != null && !jestWstrzymana) {
            timer.cancel();
            jestWstrzymana = true;
            System.out.println("Rolowanie wstrzymane.");
        }
    }

    public void zatrzymaj() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            jestWstrzymana = false;
            System.out.println("Rolowanie zatrzymane.");
        }
    }

    private void przesunTekst() {
        if (kierunekPrawo) {
            tekst = tekst.charAt(tekst.length() - 1) + tekst.substring(0, tekst.length() - 1);
        } else {
            tekst = tekst.substring(1) + tekst.charAt(0);
        }
        etykieta.setText(tekst);
    }

    private void wznowTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                przesunTekst();
            }
        }, 0, delay);
    }
}
