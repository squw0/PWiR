import java.awt.Color;
import java.util.Scanner;

public class MigajacaEtykietaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj pierwszy kolor (w formacie RGB, np. 255 0 0 dla czerwonego):");
        int r1 = scanner.nextInt();
        int g1 = scanner.nextInt();
        int b1 = scanner.nextInt();

        System.out.println("Podaj drugi kolor (w formacie RGB, np. 0 0 255 dla niebieskiego):");
        int r2 = scanner.nextInt();
        int g2 = scanner.nextInt();
        int b2 = scanner.nextInt();

        System.out.println("Podaj czas migania w milisekundach:");
        long czasMigania = scanner.nextLong();

        MigajacaEtykieta etykieta = new MigajacaEtykieta(new Color(r1, g1, b1), new Color(r2, g2, b2), czasMigania);

        boolean running = true;
        while (running) {
            System.out.println("Wpisz: 's' - start/wznowienie, 'p' - pauza, 'z' - zakończenie");

            String command = scanner.next().toLowerCase();
            switch (command) {
                case "s":
                    etykieta.uruchom();
                    break;
                // case "p":
                //     etykieta.wstrzymaj();
                //     break;
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
