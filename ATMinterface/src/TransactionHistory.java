import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private final List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(String type, double amount, String recipient) {
        transactions.add(new Transaction(type, amount, recipient));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
