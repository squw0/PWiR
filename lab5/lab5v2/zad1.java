import java.io.*;
import java.util.*;

public class zad1 {
    static class Question {
        String question;
        List<String> answers;
        char correctAnswer;

        public Question(String question, List<String> answers, char correctAnswer) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(question).append("\n");
            for (int i = 0; i < answers.size(); i++) {
                sb.append((char) ('A' + i)).append(". ").append(answers.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        List<Question> test = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("_______Wybierz opcje_______");
            System.out.println("1. Wczytaj test z pliku");
            System.out.println("2. Przeprowadź test");
            System.out.println("3. Edytuj test (dodaj/usuń pytania)");
            System.out.println("4. Zapisz test do pliku");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    test = loadTest("test.txt");
                    break;
                case 2:
                    conductTest(test);
                    break;
                case 3:
                    editTest(test, scanner);
                    break;
                case 4:
                    saveTest(test, "test.txt");
                    break;
                case 5:
                    System.out.println("Koniec programu.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public static List<Question> loadTest(String filename) {
        List<Question> test = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String question = line;
                List<String> answers = Arrays.asList(reader.readLine().split("; "));
                char correctAnswer = reader.readLine().charAt(0);
                test.add(new Question(question, answers, correctAnswer));
                reader.readLine();
            }
            System.out.println("Test wczytano pomyślnie.");
        } catch (IOException e) {
            System.out.println("Błąd wczytywania testu: " + e.getMessage());
        }
        return test;
    }

    public static void saveTest(List<Question> test, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Question q : test) {
                writer.write(q.question + "\n");
                writer.write(String.join("; ", q.answers) + "\n");
                writer.write(q.correctAnswer + "\n");
                writer.write("\n");
            }
            System.out.println("Test zapisano pomyślnie.");
        } catch (IOException e) {
            System.out.println("Błąd zapisywania testu: " + e.getMessage());
        }
    }

    public static void conductTest(List<Question> test) {
        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;

        for (Question q : test) {
            System.out.println(q);
            System.out.print("Podaj odpowiedź (A, B, C): ");
            char answer = scanner.next().toUpperCase().charAt(0);
            if (answer == q.correctAnswer) {
                correctAnswers++;
            }
        }

        int percentage = (int) ((double) correctAnswers / test.size() * 100);
        System.out.println("Twój wynik: " + percentage + "%");
        if (percentage >= 90) {
            System.out.println("Ocena: bardzo dobra");
        } else if (percentage >= 79) {
            System.out.println("Ocena: plus dobra");
        } else if (percentage >= 68) {
            System.out.println("Ocena: dobra");
        } else if (percentage >= 57) {
            System.out.println("Ocena: plus dostateczna");
        } else {
            System.out.println("Ocena: niedostateczna");
        }
    }

    public static void editTest(List<Question> test, Scanner scanner) {
        System.out.println("=== EDYCJA TESTU ===");
        System.out.println("1. Dodaj pytanie");
        System.out.println("2. Usuń pytanie");
        System.out.print("Wybierz opcję: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Podaj pytanie: ");
            String question = scanner.nextLine();
            List<String> answers = new ArrayList<>();
            for (char c = 'A'; c <= 'C'; c++) {
                System.out.print("Podaj odpowiedź " + c + ": ");
                answers.add(scanner.nextLine());
            }
            System.out.print("Podaj poprawną odpowiedź (A, B, C): ");
            char correctAnswer = scanner.nextLine().toUpperCase().charAt(0);
            test.add(new Question(question, answers, correctAnswer));
        } else if (choice == 2) {
            System.out.println("Podaj numer pytania do usunięcia (1-" + test.size() + "): ");
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < test.size()) {
                test.remove(index);
                System.out.println("Pytanie usunięto.");
            } else {
                System.out.println("Nieprawidłowy numer pytania.");
            }
        } else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }
}
