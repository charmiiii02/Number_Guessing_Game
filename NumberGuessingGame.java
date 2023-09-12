import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JButton playAgainButton;

    private Random rand = new Random();
    private int randNum;
    private int guess;
    private int tries;
    
    public NumberGuessingGame() {
        frame = new JFrame("Number Guessing Game");
        panel = new JPanel();
        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        playAgainButton = new JButton("Play Again");

        randNum = rand.nextInt(100) + 1; // Generate a random number between 1 and 100
        tries = 0;

        // Set up action listeners
        guessButton.addActionListener(new GuessButtonListener());
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(resultLabel);
        panel.add(playAgainButton);

        // Configure the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                guess = Integer.parseInt(guessField.getText());
                tries++;

                if (guess == randNum) {
                    resultLabel.setText("Awesome! You guessed the number in " + tries + " tries.");
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                } else if (guess > randNum) {
                    resultLabel.setText("Your guess is too high. Try again.");
                } else {
                    resultLabel.setText("Your guess is too low. Try again.");
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter a number.");
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            randNum = rand.nextInt(100) + 1;
            guessField.setText("");
            resultLabel.setText("");
            tries = 0;
            guessButton.setEnabled(true);
            playAgainButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}

