import java.util.Scanner;

public class RolowanaEtykietaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj tekst do rolowania:");
        String tekst = scanner.nextLine();

        System.out.println("Podaj szybkość rolowania (w sekundach):");
        int delay = scanner.nextInt();

        System.out.println("Wybierz kierunek: 1 - prawo, 2 - lewo");
        int kierunek = scanner.nextInt();
        boolean kierunekPrawo = kierunek == 1;

        RolowanaEtykieta etykieta = new RolowanaEtykieta(tekst, delay, kierunekPrawo);

        boolean running = true;
        while (running) {
            System.out.println("Wpisz: 's' - start/wznowienie, 'p' - pauza, 'z' - zakończenie");

            String command = scanner.next().toLowerCase();
            switch (command) {
                case "s":
                    etykieta.uruchom();
                    break;
                case "p":
                    etykieta.wstrzymaj();
                    break;
                case "z":
                    etykieta.zatrzymaj();
                    running = false;
                    break;
                default:
                    System.out.println("Niepoprawny znak. Spróbuj ponownie.");
                    break;
            }
        }

        scanner.close();
    }
}
