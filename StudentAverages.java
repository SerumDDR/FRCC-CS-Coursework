/*
    Name: Donnie Ranjel
    File Name: StudentAverages.java
    Date: 09/25/2025
    Class: CSC 1060-503
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentAverages {
    public static void main(String[] args) throws IOException {
        // 1. Read the student data from students.txt
        Scanner inFile = new Scanner(new File("students.txt"));
        PrintWriter outFile = new PrintWriter("averages.txt");

        while (inFile.hasNextLine()) {
            String line = inFile.nextLine().trim();
            if (line.isEmpty()) continue;

            Scanner lineScan = new Scanner(line);
            String name = lineScan.next();

            double sum = 0.0;
            int count = 0;
            while (lineScan.hasNextDouble()) {
                sum += lineScan.nextDouble();
                count++;
            }
            lineScan.close();

            // 2. Calculate the average grade for each student
            double avg = (count == 0) ? 0.0 : sum / count;

            // 3. Write the processed data to averages.txt
            outFile.printf("%s %.2f%n", name, avg);
            
            // 4. Print a summary of the results to the console
            System.out.printf("Student: %s | Average: %.2f%n", name, avg);
        }

        inFile.close();
        outFile.close();

        System.out.println("Processing complete. Results written to averages.txt");
    }
}