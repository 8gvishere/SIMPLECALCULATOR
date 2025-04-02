import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorUI {
    private JFrame frame;
    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean newInput = true;

    public CalculatorUI() { // Fixed constructor name
        frame = new JFrame("Smart Java Calculator");
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Buttons
        String[] buttonLabels = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "C", "+",
            "="
        };

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Segoe UI", Font.BOLD, 20));
            button.addActionListener(this::onButtonClick);
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void onButtonClick(ActionEvent e) {
        String input = ((JButton) e.getSource()).getText();

        if ("0123456789.".contains(input)) {
            if (newInput) {
                display.setText(input.equals(".") ? "0." : input);
                newInput = false;
            } else {
                if (input.equals(".") && display.getText().contains(".")) return;
                display.setText(display.getText() + input);
            }
        } else if ("+-*/".contains(input)) {
            firstNumber = Double.parseDouble(display.getText());
            operator = input;
            newInput = true;
        } else if (input.equals("=")) {
            try {
                double secondNumber = Double.parseDouble(display.getText());
                double result = calculate(firstNumber, secondNumber, operator);
                display.setText(Double.toString(result));
                newInput = true;
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (input.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            operator = "";
            newInput = true;
        }
    }

    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Divide by zero");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorUI::new);
        System.out.println("hello"); 
    }
}
