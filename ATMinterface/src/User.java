public class User {
    private final String userId;
    private final String pin;
    private double balance;
    private final TransactionHistory history;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 1000.0;
        this.history = new TransactionHistory();
    }

    public boolean authenticate(String inputId, String inputPin) {
        return userId.equals(inputId) && pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.addTransaction("Withdraw", amount, null);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        history.addTransaction("Deposit", amount, null);
    }

    public void transfer(double amount, String recipient) {
        if (amount <= balance) {
            balance -= amount;
            history.addTransaction("Transfer", amount, recipient);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public TransactionHistory getHistory() {
        return history;
    }
}
