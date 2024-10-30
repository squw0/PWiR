import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ZegarApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zegar zegar = new Zegar();
        Timer timer = new Timer();
        boolean isRunning = false;

        boolean running = true;
        while (running) {
            System.out.println("Wybierz akcję: 1-nastaw, 2-wypisz, 3-zmień format (12/24), 4-tykniecie, 5-uruchom, 6-wstrzymaj, 7-zatrzymaj, 0-zakończ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    running = false;
                    if (isRunning) {
                        timer.cancel();
                    }
                    System.out.println("Zakończono program.");
                    break;
                case 1:
                    System.out.println("Podaj godzinę:");
                    int godzina = scanner.nextInt();
                    System.out.println("Podaj minutę:");
                    int minuta = scanner.nextInt();
                    System.out.println("Podaj sekundę:");
                    int sekunda = scanner.nextInt();
                    zegar.nastaw(godzina, minuta, sekunda);
                    break;
                case 2:
                    System.out.println("Aktualny czas: " + zegar.wypisz());
                    break;
                case 3:
                    System.out.println("Wybierz format: 1-12-godzinny, 2-24-godzinny");
                    int format = scanner.nextInt();
                    zegar.format(format == 1);
                    System.out.println("Nowy czas w wybranym formacie: " + zegar.wypisz());
                    break;
                case 4:
                    zegar.tykniecie();
                    break;
                case 5:
                    if (!isRunning) {
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                zegar.tykniecie();
                            }
                        }, 0, 1000); // Aktualizacja co 1 sekunda
                        isRunning = true;
                        System.out.println("Zegar uruchomiony.");
                    } else {
                        System.out.println("Zegar już działa.");
                    }
                    break;
                case 6:
                    if (isRunning) {
                        timer.cancel();
                        isRunning = false;
                        System.out.println("Zegar wstrzymany.");
                    } else {
                        System.out.println("Zegar nie jest uruchomiony.");
                    }
                    break;
                case 7:
                    if (isRunning) {
                        timer.cancel();
                        isRunning = false;
                    }
                    timer = new Timer(); // Nowy timer po zatrzymaniu
                    System.out.println("Zegar zatrzymany.");
                    break;
                default:
                    System.out.println("Niepoprawny wybór.");
                    break;
            }
        }

        scanner.close();
    }
}
