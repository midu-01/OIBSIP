public class Transaction {
    String type;
    double amount;
    String recipient;

    public Transaction(String type, double amount, String recipient) {
        this.type = type;
        this.amount = amount;
        this.recipient = recipient;
    }

    public String toString() {
        if (recipient != null) {
            return type + ": $" + amount + " to " + recipient;
        }
        return type + ": $" + amount;
    }
}
