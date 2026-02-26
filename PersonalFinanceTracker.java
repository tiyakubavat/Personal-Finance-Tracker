import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonalFinanceTracker {

    static final String USERNAME = "admin";
    static final String PASSWORD = "1234";

    static double totalIncome = 0;
    static double totalExpense = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== PERSONAL FINANCE TRACKER =====");

        // Login System
        if (!login(sc)) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice = 0;

        do {
            System.out.println("\n1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addIncome(sc);
                        break;
                    case 2:
                        addExpense(sc);
                        break;
                    case 3:
                        viewReport();
                        break;
                    case 4:
                        System.out.println("Thank you for using the Finance Tracker!");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                sc.next(); // Clear wrong input
            }

        } while (choice != 4);

        sc.close();
    }

    // Login Method
    static boolean login(Scanner sc) {
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Username: ");
            String user = sc.next();
            System.out.print("Enter Password: ");
            String pass = sc.next();

            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login Successful!");
                return true;
            } else {
                System.out.println("Invalid credentials! Attempt " + i + " of 3");
            }
        }
        return false;
    }

    // Add Income
    static void addIncome(Scanner sc) {
        try {
            System.out.print("Enter income amount: ");
            double income = sc.nextDouble();

            if (income <= 0) {
                System.out.println("Income must be positive!");
                return;
            }

            totalIncome += income;
            saveToFile("Income: " + income);
            System.out.println("Income added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Enter numeric value.");
            sc.next();
        }
    }

    // Add Expense
    static void addExpense(Scanner sc) {
        try {
            System.out.print("Enter expense amount: ");
            double expense = sc.nextDouble();

            if (expense <= 0) {
                System.out.println("Expense must be positive!");
                return;
            }

            totalExpense += expense;
            saveToFile("Expense: " + expense);
            System.out.println("Expense added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Enter numeric value.");
            sc.next();
        }
    }

    // View Report
    static void viewReport() {
        System.out.println("\n===== FINANCIAL REPORT =====");
        System.out.println("Total Income  : " + totalIncome);
        System.out.println("Total Expense : " + totalExpense);
        System.out.println("Balance       : " + (totalIncome - totalExpense));
    }

    // Save Data to File
    static void saveToFile(String data) {
        try {
            FileWriter fw = new FileWriter("finance.txt", true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }
}