
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class zad1 {
    public static void main(String[] args) {
        List<String> odpowiedzi = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter writer = new FileWriter("test.txt");

            // Pytanie 1
            System.out.println("\nPytanie 1");
            System.out.println("1. Ile nóg ma pies? \tA.1 \tB.2 \tC.4");
            System.out.println("Podaj odpowiedź: ");
    
            writer.write("1. Ile nóg ma pies? \tA.1 \tB.2 \tC.4");
            writer.append("\nOdpowiedź Użytkownika: "+scanner.next());
            writer.append("\nPoprawna Odpowiedź: A\n");

            

            // Pytanie 2
            System.out.println("\nPytanie 2");
            System.out.println("2. Ile nóg ma kot? \tA.1 \tB.2 \tC.4");
            System.out.println("Podaj odpowiedź: ");


            writer.write("\n2. Ile nóg ma kot? \tA.1 \tB.2 \tC.4");
            writer.append("\nOdpowiedź Użytkownika: "+scanner.next());
            writer.append("\nPoprawna Odpowiedź: A\n");


            // Pytanie 3
            System.out.println("\nPytanie 3");
            System.out.println("3. Ile nóg ma człowiek? \tA.1 \tB.2 \tC.4");
            System.out.println("Podaj odpowiedź: ");
            

            writer.write("\n3. Ile nóg ma człowiek? \tA.1 \tB.2 \tC.4");
            writer.append("\nOdpowiedź Użytkownika: "+scanner.next());
            writer.append("\nPoprawna Odpowiedź: A\n");


            // Pytanie 4
            System.out.println("\nPytanie 4");
            System.out.println("4. Ile nóg ma krowa? \tA.1 \tB.2 \tC.4");
            System.out.println("Podaj odpowiedź: ");
            

            writer.write("\n4. Ile nóg ma krowa? \tA.1 \tB.2 \tC.4");
            writer.append("\nOdpowiedź Użytkownika: "+scanner.next());
            writer.append("\nPoprawna Odpowiedź: A\n");
            writer.close();
        } 
        catch (Exception e) {
        }



        try {
            FileReader reader = new FileReader("test.txt");
            int data = reader.read();
            while(data != -1){
                System.out.println((char)data);
                data = reader.read();
            }
            reader.close();
        } catch (Exception e) {
        }
    }
}