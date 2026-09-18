/*
    Name: Donnie Ranjel
    File Name: Employee.java
    Date: 09/18/2025
    Class: CSC 1060-503
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
    The Employee class represents an employee with a name, hire date, and hourly pay.
    It includes constructors, a toString method, a setter for hourly pay, and a
    mutator for giving a raise with specific rules (0%–20% allowed).
*/
public class Employee {

    // Data members
    private String name;
    private LocalDate hireDate;
    private double hourlyPay;

    // Default hourly pay value
    private static final double DEFAULT_PAY = 15.0;

    /*
        Default constructor.
        Initializes employee with default values.
    */
    public Employee() {
        this.name = "Unknown";
        this.hireDate = LocalDate.of(2020, 1, 1);
        this.hourlyPay = DEFAULT_PAY;
    }

    /*
        Constructor that takes an employee's name.
        Initializes name and sets other data members to default values.
    
    @param name The name of the employee.
    */
    public Employee(String name) {
        this(); // Call default constructor to set defaults
        this.name = name;
    }

    /*
        Sets the hourly pay for the employee.
    
        @param hourlyPay The new hourly pay to be set.
    */
    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    /*
        Mutator to give the employee a raise.
        The raise percentage must be between 0% and 20% (inclusive).
        If the raise is invalid, it prints an error message.
    
    @param percentage The raise percentage as a decimal (e.g., 0.10 for 10%).
    */
    public void giveRaise(double percentage) {
        if (percentage < 0 || percentage > 0.20) {
            System.out.println("Invalid raise percentage! Raise must be between 0 and 20%.");
        } else {
            this.hourlyPay += this.hourlyPay * percentage;
        }
    }

    /*
        Returns a formatted string representation of the Employee object.
    
    @return A string containing the employee's name, hire date, and hourly pay.
    */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDate = this.hireDate.format(formatter);
        return String.format("Employee Name: %s\nHire Date: %s\nHourly Pay: $%.2f",
                this.name, formattedDate, this.hourlyPay);
    }

    /*
        The main method to test the functionality of the Employee class.
    */
    public static void main(String[] args) {

        // Step 4: Create an object using the default constructor and output its information
        Employee emp1 = new Employee();
        System.out.println(emp1);

        // Create an object using the name constructor and output its information
        Employee emp2 = new Employee("John Doe");
        System.out.println(emp2);

        // Test the setter for hourly pay
        System.out.println("After setting hourly pay to 18.5:");
        emp2.setHourlyPay(18.5);
        System.out.println(emp2);

        // Test the mutator with a valid raise
        System.out.println("After giving a 10% raise:");
        emp2.giveRaise(0.10);
        System.out.println(emp2);

        // Test the mutator with an invalid raise
        System.out.println("After attempting a 25% raise:");
        emp2.giveRaise(0.25);
        System.out.println(emp2);
    }
}
