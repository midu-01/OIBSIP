import java.util.Scanner;

public class BankOperations {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;

    public BankOperations(User user) {
        this.user = user;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user.getHistory().getTransactions();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        user.withdraw(amount);
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        user.deposit(amount);
    }

    private void transfer() {
        System.out.print("Enter recipient name: ");
        scanner.nextLine(); // consume newline
        String recipient = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        user.transfer(amount, recipient);
    }
}
