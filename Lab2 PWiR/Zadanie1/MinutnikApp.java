import java.util.Scanner;

public class MinutnikApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Minutnik minutnik1 = new Minutnik(40.0); 
        Minutnik minutnik2 = new Minutnik(40.0); 

        boolean running = true;
        while (running) {
            System.out.println("Wybierz minutnik (1 lub 2) lub 0 aby zakończyć: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                running = false;
                minutnik1.zatrzymaj();
                minutnik2.zatrzymaj();
            } else if (choice == 1 || choice == 2) {
                Minutnik currentMinutnik = (choice == 1) ? minutnik1 : minutnik2;

                System.out.println("Wybierz akcję: 1-uruchom, 2-wstrzymaj, 3-wznow, 4-zatrzymaj, 5-ponownie uruchom, 6-ustaw czas");
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        currentMinutnik.uruchom();
                        break;
                    case 2:
                        currentMinutnik.wstrzymaj();
                        break;
                    case 3:
                        currentMinutnik.wznow();
                        break;
                    case 4:
                        currentMinutnik.zatrzymaj();
                        break;
                    case 5:
                        currentMinutnik.ponownieUruchom();
                        break;
                    case 6:
                        System.out.println("Podaj nowy czas w sekundach:");
                        double newTime = scanner.nextDouble();
                        scanner.nextLine();
                        currentMinutnik.ustawCzas(newTime);
                        break;
                    default:
                        System.out.println("Niepoprawny wybór.");
                        break;
                }
            } else {
                System.out.println("Niepoprawny wybór.");
            }
        }
        scanner.close();
    }
}
