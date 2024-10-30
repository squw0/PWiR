import java.util.Scanner;

public class StoperApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stoper stoper1 = new Stoper();
        Stoper stoper2 = new Stoper();

        boolean running = true;
        while (running) {
            System.out.println("Wybierz stoper (1 lub 2) lub 0 aby zakończyć: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                running = false;
                stoper1.zresetuj();
                stoper2.zresetuj();
            } else if (choice == 1 || choice == 2) {
                Stoper currentStoper = (choice == 1) ? stoper1 : stoper2;
                System.out.println("Wybierz akcję: 1-uruchom, 2-wstrzymaj, 3-wznow, 4-zresetuj");
                int action = scanner.nextInt();
                scanner.nextLine();
                
                switch (action) {
                    case 1:
                        currentStoper.uruchom();
                        break;
                    case 2:
                        currentStoper.wstrzymaj();
                        break;
                    case 3:
                        currentStoper.wznow();
                        break;
                    case 4:
                        currentStoper.zresetuj();
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
