import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ATMGUI {
    private final User user = new User("user", "123");
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI().createLoginScreen());
    }

    private void createLoginScreen() {
        frame = new JFrame("ATM Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(4, 1));

        JTextField userIdField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        frame.add(new JLabel("User ID:"));
        frame.add(userIdField);
        frame.add(new JLabel("PIN:"));
        frame.add(pinField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String id = userIdField.getText();
            String pin = new String(pinField.getPassword());

            if (user.authenticate(id, pin)) {
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.setTitle("ATM Menu");
        frame.setLayout(new GridLayout(7, 1));

        JButton viewBalance = new JButton("View Balance");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton transfer = new JButton("Transfer");
        JButton viewTransactions = new JButton("Transaction History");
        JButton logout = new JButton("Logout");

        viewBalance.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Balance: $" + user.getBalance()));
        deposit.addActionListener(e -> handleDeposit());
        withdraw.addActionListener(e -> handleWithdraw());
        transfer.addActionListener(e -> handleTransfer());
        viewTransactions.addActionListener(e -> showTransactions());
        logout.addActionListener(e -> {
            frame.dispose();
            createLoginScreen();
        });

        frame.add(viewBalance);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(transfer);
        frame.add(viewTransactions);
        frame.add(logout);

        frame.revalidate();
        frame.repaint();
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountStr);
            user.deposit(amount);
            showSuccess("Deposit successful!");
        } catch (Exception e) {
            showError("Invalid amount!");
        }
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountStr);
            user.withdraw(amount);
            showSuccess("Withdraw successful!");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleTransfer() {
        String recipient = JOptionPane.showInputDialog("Enter recipient name:");
        String amountStr = JOptionPane.showInputDialog("Enter amount to transfer:");
        try {
            double amount = Double.parseDouble(amountStr);
            user.transfer(amount, recipient);
            showSuccess("Transfer successful!");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void showTransactions() {
        JTextArea area = new JTextArea(10, 30);
        for (Transaction t : user.getHistory().getTransactions()) {
            area.append(t.toString() + "\n");
        }
        area.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(area), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Success");
        dialog.getContentPane().setBackground(Color.GREEN);
        dialog.setVisible(true);
    }

    private void showError(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.getContentPane().setBackground(Color.RED);
        dialog.setVisible(true);
    }
}
