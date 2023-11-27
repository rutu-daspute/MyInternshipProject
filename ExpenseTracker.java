import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker extends JFrame {

    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextField moneyField; // New field for entering the money spent on different products
    private JTextArea expenseList;
    private List<Expense> expenses;

    public ExpenseTracker() {
        setTitle("Expense Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        descriptionField = new JTextField(15);
        amountField = new JTextField(15);
        categoryField = new JTextField(15);
        moneyField = new JTextField(15); // Initialize the new text field
        expenseList = new JTextArea(10, 40);
        expenseList.setEditable(false);
        expenses = new ArrayList<>();

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2)); // Increase the number of rows for the input panel
        inputPanel.add(new JLabel("Description: "));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount: "));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category: "));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Money Spent: "));
        inputPanel.add(moneyField); // Add the new text field to the input panel

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(expenseList), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addExpense() {
        String description = descriptionField.getText().trim();
        String amountText = amountField.getText().trim();
        String category = categoryField.getText().trim();
        String moneyText = moneyField.getText().trim(); // Retrieve the money spent

        if (!description.isEmpty() && !amountText.isEmpty() && !category.isEmpty() && !moneyText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                double money = Double.parseDouble(moneyText); // Parse the money spent
                Expense newExpense = new Expense(description, amount, category + " (Money Spent: $" + money + ")");
                expenses.add(newExpense);
                updateExpenseList();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount or money. Please enter valid numbers.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
        }
    }

    private void updateExpenseList() {
        expenseList.setText("");
        for (Expense expense : expenses) {
            expenseList.append(expense.toString() + "\n");
        }
    }

    private void clearFields() {
        descriptionField.setText("");
        amountField.setText("");
        categoryField.setText("");
        moneyField.setText(""); // Clear the money field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTracker();
            }
        });
    }
}

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("Description: %s, Amount: %.2f, Category: %s", description, amount, category);
    }
}

