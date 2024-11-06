import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pet {
    // Attributes
    private String petType;
    private String petName;
    private int petAge;
    private double weight;
    private int daysStay;
    private boolean grooming;
    private double amountDue;

    // Constructor that initializes all attributes
    public Pet(String petType, String petName, int petAge, double weight, int daysStay, boolean grooming) {
        this.petType = petType;
        this.petName = petName;
        this.petAge = petAge;
        this.weight = weight;
        this.daysStay = daysStay;
        this.grooming = grooming;
        this.amountDue = 0.0;
    }

    // Accessors (getters)
    public String getPetType() { return petType; }
    public String getPetName() { return petName; }
    public int getPetAge() { return petAge; }
    public double getWeight() { return weight; }
    public int getDaysStay() { return daysStay; }
    public boolean isGrooming() { return grooming; }
    public double getAmountDue() { return amountDue; }

    // Mutators (setters)
    public void setPetType(String petType) { this.petType = petType; }
    public void setPetName(String petName) { this.petName = petName; }
    public void setPetAge(int petAge) { this.petAge = petAge; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setDaysStay(int daysStay) { this.daysStay = daysStay; }
    public void setGrooming(boolean grooming) { this.grooming = grooming; }
    public void setAmountDue(double amountDue) { this.amountDue = amountDue; }

    public static class PetBAG {
        private static int dogSpaces = 30, catSpaces = 12;
        private static List<Pet> pets = new ArrayList<>();

        public static int getDogSpaces() { return dogSpaces; }
        public static int getCatSpaces() { return catSpaces; }

        // Added setDogSpaces and setCatSpaces methods
        public static void setDogSpaces(int spaces) { dogSpaces = spaces; }
        public static void setCatSpaces(int spaces) { catSpaces = spaces; }

        public static String checkInPet(String petType, String petName, int petAge, double petWeight, int daysStay, boolean grooming) {
            if (petType.equalsIgnoreCase("Dog") && dogSpaces > 0) {
                dogSpaces--;
                pets.add(new Pet(petType, petName, petAge, petWeight, daysStay, daysStay >= 2 && grooming));
                return "Pet checked in successfully";
            } else if (petType.equalsIgnoreCase("Cat") && catSpaces > 0) {
                catSpaces--;
                pets.add(new Pet(petType, petName, petAge, petWeight, daysStay, false));
                return "Pet checked in successfully";
            } else {
                return petType.equalsIgnoreCase("Dog") || petType.equalsIgnoreCase("Cat") ? 
                       "No space available for " + petType.toLowerCase() + "s" : "Invalid pet type";
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nPet BAG Check-In System");
                System.out.println("1. Check in a pet");
                System.out.println("2. View available spaces");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice == 1) {
                    System.out.print("Enter pet type (Dog/Cat): ");
                    String petType = scanner.nextLine();
                    System.out.print("Enter pet name: ");
                    String petName = scanner.nextLine();
                    System.out.print("Enter pet age: ");
                    int petAge = scanner.nextInt();
                    System.out.print("Enter pet weight: ");
                    double petWeight = scanner.nextDouble();
                    System.out.print("Enter length of stay (days): ");
                    int daysStay = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    boolean grooming = false;
                    if (petType.equalsIgnoreCase("Dog") && daysStay >= 2) {
                        System.out.print("Grooming required? (Yes/No): ");
                        String groomingResponse = scanner.nextLine();
                        grooming = groomingResponse.equalsIgnoreCase("Yes");
                    }
                    
                    String result = checkInPet(petType, petName, petAge, petWeight, daysStay, grooming);
                    System.out.println(result);
                } else if (choice == 2) {
                    System.out.println("Available dog spaces: " + getDogSpaces());
                    System.out.println("Available cat spaces: " + getCatSpaces());
                } else if (choice == 3) {
                    System.out.println("Thank you for using Pet BAG. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
            
            scanner.close();
        }
    }
}
