import java.util.Scanner;

public class Paint2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        double wallHeight = 0.0;
        double wallWidth = 0.0;
        double wallArea = 0.0;
        double gallonsPaintNeeded = 0.0;
        double cansNeeded;

        final double squareFeetPerGallons = 350.0;
        final double gallonsPerCan = 1.0;

        // Prompt user to input wall's height
        System.out.println("Enter wall height (feet): ");
        wallHeight = scnr.nextDouble();

        // Prompt user to input wall's width
        System.out.println("Enter wall width (feet): ");
        wallWidth = scnr.nextDouble();

        // Calculate and output wall area
        wallArea = wallHeight * wallWidth;
        // FIXED: Removed concatenation and used printf for formatting
        System.out.printf("Wall area: %.1f square feet\n", wallArea);

        // Calculate and output the amount of paint in gallons needed to paint the wall
        gallonsPaintNeeded = wallArea / squareFeetPerGallons;
        // FIXED: Used printf for formatting to limit decimal places
        System.out.printf("Paint needed: %.4f gallons\n", gallonsPaintNeeded);

        // Calculate and output the number of paint cans needed to paint the wall,
        // rounded up to nearest integer
        // ADDED: Calculation for cans needed
        cansNeeded = Math.ceil(gallonsPaintNeeded / gallonsPerCan);
        // ADDED: Output for cans needed
        System.out.printf("Cans needed: %d can(s)\n", (int)cansNeeded);

        // ADDED: Close the scanner to prevent resource leak
        scnr.close();
    }
}
