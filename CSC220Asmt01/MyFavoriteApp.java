package projecthelloworld;

import java.util.Scanner;

/**
 * Class originally named "CoffeeShopAccount" as part of
 * a CSC 210 assignment. The name has been changed to comply
 * with the assessment guideline specified under (B)(2).
 * 
 * For the original project, see:
 * https://github.com/mosguinz-csc210-03/CSC210Asmt05/blob/main/src/CoffeeShopAccount.java
 */
public class MyFavoriteApp {

    private static final Scanner scan = new Scanner(System.in);
    private static double balance = 1.0 / 0;  // Infinity as default
    private static String pin;

    public static void main(String[] args) {
        loop();
    }

    /**
     * Run the program's execution loop.
     */
    public static void loop() {
        initialSetup();

        while (true) {
            switch (promptAction()) {
                case 1:
                    displayBalance();
                    break;
                case 2:
                    addBalance();
                    break;
                case 3:
                    spendBalance();
                    break;
                case 4:
                    setPin();
                    break;
                case 5:
                    System.out.println("\nExiting... Goodbye!");
                    return;
            }

            System.out.println("\nPlease enter your PIN before proceeding.");
            if (!promptPin().equals(pin)) {
                System.out.println("Incorrect PIN.");
                System.out.println("If you have forgotten your PIN. Please restart the program to reset it.");
                System.out.println("The program will now exit.");
                return;
            }
        }
    }

    /**
     * Run the initial setup prompts.
     */
    private static void initialSetup() {
        System.out.println("Welcome! Let's set up a new four-digit PIN.");
        pin = promptPin();
        System.out.println("Thank you! Your PIN Has been recorded");
    }

    /**
     * Prompt the user for a menu option.
     *
     * @return Returns an `int` that corresponds to the menu option.
     */
    private static int promptAction() {
        int action;
        while (true) {
            System.out.println("\nChoose a number from the following menu:");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Spend money");
            System.out.println("4. Change 4-digit PIN number");
            System.out.println("5. Exit");
            System.out.print(">>> ");
            action = scan.nextInt();
            if (action < 1 || action > 5) {
                System.out.println("\nInvalid selection. Please enter a valid option.");
                continue;
            }
            System.out.println();
            return action;
        }
    }

    /**
     * Prompt the user to enter a PIN. Performs pattern validation to make sure
     * that it is of an expected format (four digit number).
     * <p>
     * Note that it does not check if the pin is correct.
     *
     * @return the given PIN in a valid format.
     */
    private static String promptPin() {
        String input;
        while (true) {
            System.out.print("Enter PIN: ");
            input = scan.next();
            if (!input.matches("^\\d{4}$")) {
                System.out.println("Invalid PIN format. Please enter a four digit PIN.");
                continue;
            }
            return input;
        }
    }

    /**
     * Set the PIN.
     */
    private static void setPin() {
        System.out.println("Let's set your new PIN number.");
        pin = promptPin();
        System.out.println("Your PIN number has been updated.");
    }

    /**
     * Display the balance. Runs after every balance-related operations.
     * <p>
     * This method will perform checks to see if the initial balance has been
     * set by the user. If the balance hasn't been set (default = Infinity),
     * then it will prompt the user to set one before continuing.
     */
    private static void displayBalance() {
        if (balance == 1.0 / 0) setBalance();
        System.out.printf("Your balance is $%.2f%n", balance);
    }

    /**
     * Set the balance. Only for initial setup. Prevents operations on balance
     * when one hasn't been defined.
     */
    private static void setBalance() {
        System.out.println("You haven't set your balance yet.");
        System.out.print("Let's set your starting balance: $");
        balance = scan.nextDouble();
    }

    /**
     * Spend the balance.
     */
    private static void spendBalance() {
        double delta;
        while (true) {
            displayBalance();
            System.out.print("Enter your spending amount: $");
            delta = scan.nextDouble();
            if (delta > balance) {
                System.out.println("You have insufficient funds.");
                continue;
            }
            break;
        }
        balance -= delta;
        System.out.printf("You are spending: $%.2f%n", delta);
        System.out.printf("Your new balance is: $%.2f%n", balance);
    }

    /**
     * Add to the balance.
     */
    private static void addBalance() {
        System.out.print("Enter your deposit amount: $");
        double delta = scan.nextDouble();
        displayBalance();
        balance += delta;
        System.out.printf("You are depositing: $%.2f%n", delta);
        System.out.printf("Your new balance is: $%.2f%n", balance);
    }
    
}
