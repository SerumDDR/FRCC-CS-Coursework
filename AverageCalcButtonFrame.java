/*
    Name: Donnie Ranjel
    File Name: AverageCalcButtonFrame.java
    Date: 11/5/2025
    Class: CSC 1060-503
*/

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
    Creates a JFrame that allows the user to enter three numbers
    and click a button to calculate their average.
*/

public class AverageCalcButtonFrame extends JFrame implements ActionListener {

    // Labels for each input and output field
    private JLabel num1Label;
    private JLabel num2Label;
    private JLabel num3Label;
    private JLabel avgLabel;

    // Text fields for input and output
    private JTextField num1Field;
    private JTextField num2Field;
    private JTextField num3Field;
    private JTextField avgField;

    //  Button to trigger average calculation
    private JButton calcButton;

    /*
        Constructor sets up all components and layouts the UI
        using a GridBagLayout.
    */
    public AverageCalcButtonFrame() {

        // Set the window title
        setTitle("Average Calculator");

        // Initialize labels
        num1Label = new JLabel("Number 1:");
        num2Label = new JLabel("Number 2:");
        num3Label = new JLabel("Number 3:");
        avgLabel  = new JLabel("Average:");

        // Initialize text fields (3 editable)
        num1Field = new JTextField(15);
        num2Field = new JTextField(15);
        num3Field = new JTextField(15);

        // Default values help students test the program immediately
        num1Field.setText("0");
        num2Field.setText("0");
        num3Field.setText("0");

        // Output field (non-editable)
        avgField = new JTextField(15);
        avgField.setEditable(false);

        // Initialize button and register listener
        calcButton = new JButton("Calculate");
        calcButton.addActionListener(this);

        // Layout Section
        setLayout(new GridBagLayout());
        GridBagConstraints positionConst = new GridBagConstraints();

        // Padding for every component
        positionConst.insets = new Insets(10, 10, 10, 10);

        // Row 0: Number 1 Label
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        add(num1Label, positionConst);

        // Row 0: Number 1 Field
        positionConst.gridx = 1;
        add(num1Field, positionConst);

        // Row 1: Number 2
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        add(num2Label, positionConst);

        positionConst.gridx = 1;
        add(num2Field, positionConst);

        // Row 2: Number 3
        positionConst.gridx = 0;
        positionConst.gridy = 2;
        add(num3Label, positionConst);

        positionConst.gridx = 1;
        add(num3Field, positionConst);

        // Row 3: Average
        positionConst.gridx = 0;
        positionConst.gridy = 3;
        add(avgLabel, positionConst);

        positionConst.gridx = 1;
        add(avgField, positionConst);

        // Row 4: Calculate Button
        positionConst.gridx = 0;
        positionConst.gridy = 4;
        add(calcButton, positionConst);
    }

    /*
        Method runs automatically when the "Calculate" button is pressed.
        It reads the three numbers, computes the average, and displays it.
    */
    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            // Read values from text fields as double
            double n1 = Double.parseDouble(num1Field.getText());
            double n2 = Double.parseDouble(num2Field.getText());
            double n3 = Double.parseDouble(num3Field.getText());

            // Compute average
            double average = (n1 + n2 + n3) / 3.0;

            // Display in output field, formatted to two decimals
            avgField.setText(String.format("%.2f", average));

        } catch (NumberFormatException e) {
            // Catch invalid input (e.g., letters)
            avgField.setText("Invalid Input");
        }
    }

    
    // Main method creates the window and displays it.

    public static void main(String[] args) {

        AverageCalcButtonFrame myFrame = new AverageCalcButtonFrame();

        // Close program when window closes
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fit frame to components
        myFrame.pack();

        // Display the window
        myFrame.setVisible(true);
    }
}
