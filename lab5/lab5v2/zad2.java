


import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class zad2 {
    static class Question {
        String question;
        List<String> answers;
        char correctAnswer;

        public Question(String question, List<String> answers, char correctAnswer) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> test = new ArrayList<>();
        Gson gson = new Gson();

        while (true) {
            System.out.println("=== MENU ===");
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
                    test = loadTest("test.json", gson);
                    break;
                case 2:
                    conductTest(test);
                    break;
                case 3:
                    editTest(test, scanner);
                    break;
                case 4:
                    saveTest(test, "test.json", gson);
                    break;
                case 5:
                    System.out.println("Koniec programu.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public static List<Question> loadTest(String filename, Gson gson) {
        try (Reader reader = new FileReader(filename)) {
            Type listType = new TypeToken<List<Question>>() {}.getType();
            List<Question> test = gson.fromJson(reader, listType);
            System.out.println("Test wczytano pomyślnie.");
            return test;
        } catch (IOException e) {
            System.out.println("Błąd wczytywania testu: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveTest(List<Question> test, String filename, Gson gson) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(test, writer);
            System.out.println("Test zapisano pomyślnie.");
        } catch (IOException e) {
            System.out.println("Błąd zapisywania testu: " + e.getMessage());
        }
    }

    public static void conductTest(List<Question> test) {
        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;

        for (Question q : test) {
            System.out.println(q.question);
            for (int i = 0; i < q.answers.size(); i++) {
                System.out.println((char) ('A' + i) + ". " + q.answers.get(i));
            }
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
