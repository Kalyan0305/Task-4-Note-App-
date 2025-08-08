import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Write New Note");
            System.out.println("2. View Notes");
            System.out.println("3. Append Note");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    writeNewNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    appendNote(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Notes App.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void writeNewNote(Scanner scanner) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            System.out.println("Enter your note (end with 'exit'): ");
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void appendNote(Scanner scanner) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, true);
            System.out.println("Enter note to append (end with 'exit'): ");
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
            System.out.println("Note appended.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            System.out.println("\n--- Saved Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No notes found.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
