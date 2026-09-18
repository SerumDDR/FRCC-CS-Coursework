/*
    Name: Donnie Ranjel
    File Name: Employee.java
    Date: 10/09/2025
    Class: CSC 1060-503
*/

import java.time.LocalDate;

/*
    This program demonstrates how inheritance works in Java by creating a base class (Employee)
    and two subclasses (FullTimeEmployee and PartTimeEmployee).
    It shows how constructors, method overriding, and polymorphism are used in object-oriented design.
*/
public class Employee {
    // Step 1: Data Members (Accessible to Subclasses)
    protected String name;
    protected int id;
    protected LocalDate hireDate;

    // Step 2: Constructor to Initialize Common Employee Data
    public Employee(String name, int id, LocalDate hireDate) {
        this.name = name;
        this.id = id;
        this.hireDate = hireDate;
    }

    // Step 3: Display Basic Employee Information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Hire Date: " + hireDate);
    }

    // Step 4: Calculate Pay (Base method - overridden in subclasses)
    public double calculatePay() {
        return 0.0;
    }
}


// Step 5: FullTimeEmployee Subclass
class FullTimeEmployee extends Employee {
    private double salary;

    // Step 6: Constructor that calls the superclass constructor
    public FullTimeEmployee(String name, int id, LocalDate hireDate, double salary) {
        super(name, id, hireDate); // super = call to parent constructor
        this.salary = salary;
    }

    // Step 7: Override displayInfo to include salary details
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call base class version
        System.out.println("Salary: $" + String.format("%.2f", salary));
    }

    // Step 8: Override calculatePay for full-time employees
    @Override
    public double calculatePay() {
        return salary;
    }
}


// Step 9: PartTimeEmployee Subclass
class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    // Step 10: Constructor that calls the superclass constructor
    public PartTimeEmployee(String name, int id, LocalDate hireDate, double hourlyRate, int hoursWorked) {
        super(name, id, hireDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Step 11: Override displayInfo to include part-time details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly Rate: $" + String.format("%.2f", hourlyRate));
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Total Pay: $" + String.format("%.2f", (hourlyRate * hoursWorked)));
    }

    // Step 12: Override calculatePay for part-time employees
    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}


// Step 13: Main Class to Demonstrate Inheritance and Polymorphism
class PayrollDemo {
    public static void main(String[] args) {
        // Create a full-time employee
        FullTimeEmployee john = new FullTimeEmployee("John", 1001, LocalDate.of(2024, 1, 15), 5000.0);

        // Output full-time employee info and pay
        System.out.println("Full-Time Employee Information:");
        john.displayInfo();
        System.out.println("Pay: $" + String.format("%.2f", john.calculatePay()));
        System.out.println();

        // Create a part-time employee
        PartTimeEmployee bob = new PartTimeEmployee("Bob", 1002, LocalDate.of(2024, 5, 10), 20.0, 80);

        // Output part-time employee info and pay
        System.out.println("Part-Time Employee Information:");
        bob.displayInfo();
        System.out.println("Pay: $" + String.format("%.2f", bob.calculatePay()));
    }
}
