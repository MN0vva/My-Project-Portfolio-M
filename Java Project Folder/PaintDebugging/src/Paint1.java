import java.util.Scanner;
import java.util.InputMismatchException;

public class Paint1 {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        double wallHeight = 0.0;
        double wallWidth = 0.0;
        double wallArea = 0.0;
        double gallonsPaintNeeded = 0.0;
        
        final double squareFeetPerGallons = 350.0;
        
        // Input validation for wall height
        do {
            try {
                System.out.println("Enter wall height (feet): ");
                wallHeight = scnr.nextDouble();
                if (wallHeight <= 0) {
                    System.out.println("Invalid input. Height must be a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scnr.next(); // Clear the invalid input
            }
        } while (wallHeight <= 0);

        // Input validation for wall width
        do {
            try {
                System.out.println("Enter wall width (feet): ");
                wallWidth = scnr.nextDouble(); // Fixed: assign to wallWidth, not wallHeight
                if (wallWidth <= 0) {
                    System.out.println("Invalid input. Width must be a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scnr.next(); // Clear the invalid input
            }
        } while (wallWidth <= 0);

        // Calculate and output wall area
        wallArea = wallHeight * wallWidth;
        System.out.printf("Wall area: %.2f square feet%n", wallArea);

        // Calculate and output the amount of paint (in gallons) needed to paint the wall
        gallonsPaintNeeded = wallArea / squareFeetPerGallons;
        // UPDATED: Changed format specifier to show all decimal places
        System.out.printf("Paint needed: %f gallons%n", gallonsPaintNeeded);

        scnr.close();
    }
}