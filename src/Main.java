

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static Search_Engine SE = new Search_Engine();

    public static int menu() {
        System.out.println("1. Term Retrieval.");
        System.out.println("2. Boolean Retrieval.");
        System.out.println("3. Ranked Retrieval.");
        System.out.println("4. Indexed Documents.");
        System.out.println("5. Indexed Tokens.");
        System.out.println("6. Exit.");

        System.out.print("Enter Input: ");
        int choice = input.nextInt();
        return choice;
    }

    public static void printBoolean(boolean[] result) {
        Term t = new Term("", result);
        System.out.println(t);
    }

    public static void Retrieval_Term() {
        int choice1;
        System.out.println("------------------- Retrieval Term -------------------");

        System.out.println("1- Index");
        System.out.println("2- Inverted Index");
        System.out.println("3- Inverted Index using BST");
        System.out.println("4- Inverted Index using AVL");
        System.out.print("Enter your Input: ");
        choice1 = input.nextInt();

        System.out.print("Enter Term: ");
        String str = input.next();

        System.out.print("Result doc IDs: ");
        printBoolean(SE.Term_Retrieval(str.trim().toLowerCase(), choice1));
        System.out.println("\n");
    }

    public static void Boolean_Retrieval_menu() {
        System.out.println("------------------- Boolean Retrieval -------------------");
        System.out.println("1. Index");
        System.out.println("2. Inverted Index");
        System.out.println("3. Inverted Index using BST");
        System.out.println("4. Inverted Index using AVL");
        System.out.print("Enter your choice: ");
        int choice1 = input.nextInt();

        System.out.print("Enter boolean term (AND / OR): ");
        input.nextLine(); // Consume the leftover newline
        String str = input.nextLine();

        System.out.println("Query: " + str);

        System.out.print("Result doc IDs: ");
        printBoolean(SE.Boolean_Retrieval(str.trim().toUpperCase(), choice1));
        System.out.println("\n");
    }

    public static void Ranked_Retrieval_menu() {
        System.out.println("--------- Ranked Retrieval ---------");
        System.out.println("1- Index");
        System.out.println("2- Inverted Index");
        System.out.println("3- Inverted Index using BST");
        System.out.println("4- Inverted Index using AVL");
        System.out.print("Enter your Input: ");
        int choice1 = input.nextInt();

        System.out.print("Enter term: ");
        input.nextLine(); // Consume the leftover newline
        String str = input.nextLine();

        System.out.println("Query: " + str);
        System.out.println("DocID\tScore");
        switch (choice1) {
            case 1:
                System.out.println("Use ranked index list");
                SE.Ranked_Index(str);
                break;
            case 2:
                System.out.println("Use ranked inverted index list");
                SE.Ranked_RetrievalInvertedIndex(str);
                break;
            case 3:
                System.out.println("Use ranked BST");
                SE.Ranked_RetrievalBST(str);
                break;
            case 4:
                System.out.println("Use ranked AVL");
                SE.Ranked_RetrievalAVL(str);
                break;
            default:
                System.out.println("Wrong choice");
        }
        System.out.println("\n");
    }

    public static void Indexed_Documents_menu() {
        System.out.println("--------- Indexed Documents --------- ");
        System.out.println("Indexed Documents:");
        SE.Indexed_Documents();
        System.out.println("");
    }

    public static void Indexed_Tokens_menu() {
        System.out.println("--------- Indexed Tokens --------- ");
        System.out.println("Tokens and Vocabulary:");
        SE.Indexed_Tokens();
        System.out.println("");
    }

    public static void main(String[] args) {

        // Load data without printing tokens/vocab counts
        SE.LoadData("C:\\Users\\User\\eclipse-workspace\\ccis2123\\stop.txt",
                "C:\\Users\\User\\eclipse-workspace\\ccis2123\\dataset.csv");

        int choice;

        do {
            choice = menu();
            switch (choice) {
                // Term Retrieval
                case 1:
                    Retrieval_Term();
                    break;

                // Boolean Retrieval
                case 2:
                    Boolean_Retrieval_menu();
                    break;

                // Ranked Retrieval
                case 3:
                    Ranked_Retrieval_menu();
                    break;

                // Indexed Documents
                case 4:
                    Indexed_Documents_menu();
                    break;

                // Indexed Tokens
                case 5:
                    Indexed_Tokens_menu();
                    break;

                case 6:
                    System.out.println("See you later!");
                    break;

                default:
                    System.out.println("invalid choice, try again!");
            }
        } while (choice != 6);
    }
}
