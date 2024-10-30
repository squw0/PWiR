import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class MigajacaEtykieta extends JFrame {
    private Color kolor1;
    private Color kolor2;
    private long czasMigania;
    private Timer timer;
    private boolean aktualnyKolorPierwszy = true;
    private boolean jestWstrzymana = false;
    private JLabel etykieta;

    public MigajacaEtykieta(Color kolor1, Color kolor2, long czasMigania) {
        this.kolor1 = kolor1;
        this.kolor2 = kolor2;
        this.czasMigania = czasMigania;
        setupUI();
    }

    private void setupUI() {
        etykieta = new JLabel("");
        etykieta.setOpaque(true);
        etykieta.setBackground(kolor1);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.add(etykieta, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void uruchom() {
        if (jestWstrzymana) {
            jestWstrzymana = false;
            wznowTimer();
        } else {
            if (timer == null) {
                wznowTimer();
                System.out.println("Miganie etykiety uruchomione.");
            }
        }
    }

    // public void wstrzymaj() {
    //     if (timer != null && !jestWstrzymana) {
    //         timer.cancel();
    //         jestWstrzymana = true;
    //         System.out.println("Miganie etykiety wstrzymane.");
    //     }
    // }

    public void zatrzymaj() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            jestWstrzymana = false;
            System.out.println("Miganie etykiety zatrzymane.");
        }
    }

    private void zmienKolor() {
        aktualnyKolorPierwszy = !aktualnyKolorPierwszy;
        etykieta.setBackground(aktualnyKolorPierwszy ? kolor1 : kolor2);
    }

    private void wznowTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                zmienKolor();
            }
        }, 0, czasMigania);
    }

    void wstrzymaj() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
