import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NumberGuessingUI extends JFrame {
    private int numberToGuess;
    private int guessesLeft = 7;
    private final int maxNumber = 100;

    public NumberGuessingUI() {
        // Setup random number
        numberToGuess = (int) (Math.random() * maxNumber) + 1;

        // UI Setup
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));

        JLabel promptLabel = new JLabel("Guess a number between 1 and " + maxNumber, SwingConstants.CENTER);
        JTextField guessInput = new JTextField();
        JLabel resultLabel = new JLabel("You have " + guessesLeft + " guesses left.", SwingConstants.CENTER);
        JButton submitButton = new JButton("Submit Guess");

        panel.add(promptLabel);
        panel.add(guessInput);
        panel.add(submitButton);
        panel.add(resultLabel);

        add(panel);

        // Button Action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = guessInput.getText();
                try {
                    int userGuess = Integer.parseInt(text);
                    guessesLeft--;

                    if (userGuess == numberToGuess) {
                        panel.setBackground(Color.GREEN);
                        resultLabel.setText("Correct! You win!");
                        guessInput.setEditable(false);
                        submitButton.setEnabled(false);
                    } else {
                        panel.setBackground(Color.RED);
                        String hint = userGuess > numberToGuess ? "Too High!" : "Too Low!";
                        resultLabel.setText(hint + " Guesses left: " + guessesLeft);
                        if (guessesLeft == 0) {
                            resultLabel.setText("You Lose! The number was " + numberToGuess);
                            guessInput.setEditable(false);
                            submitButton.setEnabled(false);
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGuessingUI();
    }
}
