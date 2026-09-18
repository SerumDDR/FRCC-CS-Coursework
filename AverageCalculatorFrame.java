import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Creates a GUI application to calculate the average of three numbers.
 * It uses GridBagLayout for flexible component placement and implements
 * ActionListener to handle the "Calculate" button click.
 */
public class AverageCalculatorFrame extends JFrame implements ActionListener {

    // --- GUI Components ---
    private JLabel num1Label;
    private JLabel num2Label;
    private JLabel num3Label;
    private JLabel avgLabel;
    
    // Editable text fields for input
    private JTextField num1Field; 
    private JTextField num2Field; 
    private JTextField num3Field; 
    
    // Non-editable text field for output
    private JTextField avgField;
    
    private JButton calcButton;

    /**
     * Constructor sets up the frame, initializes all components, and 
     * organizes them using GridBagLayout and GridBagConstraints.
     */
    AverageCalculatorFrame() {
        // Used to specify GUI component layout constraints
        GridBagConstraints positionConst;

        // 1. Create a Frame (by extending JFrame)
        setTitle("Average Calculator");
        
        // --- Initialize Components (Labels and Fields) ---
        
        // Labels for the input fields
        num1Label = new JLabel("Number 1:");
        num2Label = new JLabel("Number 2:");
        num3Label = new JLabel("Number 3:");
        
        // Label for the output field
        avgLabel = new JLabel("Average:");

        // 3. Add 3 editable text fields (with labels)
        num1Field = new JTextField(10); // Width of 10 columns
        num1Field.setEditable(true);
        num1Field.setText("0"); // Initial value
        
        num2Field = new JTextField(10);
        num2Field.setEditable(true);
        num2Field.setText("0"); 
        
        num3Field = new JTextField(10);
        num3Field.setEditable(true);
        num3Field.setText("0");

        // 4. Add 1 non-editable field (with label)
        avgField = new JTextField(10);
        avgField.setEditable(false);
        
        // 5. Add a button labeled "Calculate"
        calcButton = new JButton("Calculate");
        
        // Set the current class to listen for button press events
        calcButton.addActionListener(this);

        // --- Layout Setup using GridBagLayout ---
        setLayout(new GridBagLayout());
        
        // Set up common padding for all components
        positionConst = new GridBagConstraints();
        // 10 pixels of padding around component
        positionConst.insets = new Insets(8, 8, 8, 8); 
        
        // Add components in a 2-column layout (Label in col 0, Field in col 1)

        // Row 0: Number 1
        positionConst.gridx = 0; // Column 0
        positionConst.gridy = 0; // Row 0
        add(num1Label, positionConst);

        positionConst.gridx = 1; // Column 1
        positionConst.gridy = 0; // Row 0
        add(num1Field, positionConst);
        
        // Row 1: Number 2
        positionConst.gridx = 0; // Column 0
        positionConst.gridy = 1; // Row 1
        add(num2Label, positionConst);

        positionConst.gridx = 1; // Column 1
        positionConst.gridy = 1; // Row 1
        add(num2Field, positionConst);
        
        // Row 2: Number 3
        positionConst.gridx = 0; // Column 0
        positionConst.gridy = 2; // Row 2
        add(num3Label, positionConst);

        positionConst.gridx = 1; // Column 1
        positionConst.gridy = 2; // Row 2
        add(num3Field, positionConst);

        // Row 3: Calculate Button (Spans 2 columns)
        positionConst.gridx = 0;    // Start at Column 0
        positionConst.gridy = 3;    // Row 3
        positionConst.gridwidth = 2; // Span 2 columns
        add(calcButton, positionConst);
        positionConst.gridwidth = 1; // Reset gridwidth for remaining components

        // Row 4: Average
        positionConst.gridx = 0; // Column 0
        positionConst.gridy = 4; // Row 4
        add(avgLabel, positionConst);

        positionConst.gridx = 1; // Column 1
        positionConst.gridy = 4; // Row 4
        add(avgField, positionConst);
    }

    /**
     * Method is automatically called when an event occurs, specifically 
     * when the "Calculate" button is pressed (Action Event).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            // Get user inputs as strings
            String input1 = num1Field.getText();
            String input2 = num2Field.getText();
            String input3 = num3Field.getText();
            
            // Convert from String to double for accurate average calculation
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);
            double number3 = Double.parseDouble(input3);
            
            // Calculate the average
            double average = (number1 + number2 + number3) / 3.0;

            // Display calculated average, formatted to 2 decimal places
            avgField.setText(String.format("%.2f", average));
            
        } catch (NumberFormatException e) {
            // Handle cases where the user enters non-numeric input
            avgField.setText("Invalid Input");
            // Print error to console for debugging
            System.err.println("Error: Non-numeric input detected. " + e.getMessage());
        }
    }

    /**
     * Creates an instance of the frame and makes it visible.
     */
    public static void main(String[] args) {
        // Create the frame and its components
        AverageCalculatorFrame myFrame = new AverageCalculatorFrame();

        // Set the program to exit when the window is closed
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Resize the window to fit the components perfectly
        myFrame.pack();
        
        // Make the window appear on screen
        myFrame.setVisible(true);
    }
}