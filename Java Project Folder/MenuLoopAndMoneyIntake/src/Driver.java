import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	 // Added a new ArrayList to store Monkey objects
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        initializeDogList();
        initializeMonkeyList();

        // Main menu loop 
        do {
            displayMenu();
            input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "1":
                    intakeNewDog(scanner);
                    break;
                case "2":
                    intakeNewMonkey(scanner);
                    break;
                case "3":
                    reserveAnimal(scanner);
                    break;
                case "4":
                    printAnimals("dog");
                    break;
                case "5":
                    printAnimals("monkey");
                    break;
                case "6":
                    printAnimals("available");
                    break;
                case "q":
                    System.out.println("Quitting application...");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (!input.equalsIgnoreCase("q"));

        scanner.close();
    }

    // This method prints the menu options (Unchanged)
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }

    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
        // You can add test monkeys here if needed
    }

    // Complete intakeNewDog
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return;
            }
        }

        // Get dog details
        System.out.println("Enter the dog's breed:");
        String breed = scanner.nextLine();
        System.out.println("Enter the dog's gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter the dog's age:");
        String age = scanner.nextLine();
        System.out.println("Enter the dog's weight:");
        String weight = scanner.nextLine();
        System.out.println("Enter the acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("Enter the acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("Enter the training status:");
        String trainingStatus = scanner.nextLine();

        // Create and add the new dog
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, false, "");
        dogList.add(newDog);
        System.out.println("New dog added successfully.");
    }

    // Complete intakeNewMonkey
    public static void intakeNewMonkey(Scanner scanner) {
        System.out.println("What is the monkey's name?");
        String name = scanner.nextLine();
        for(Monkey monkey: monkeyList) {
            if(monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis monkey is already in our system\n\n");
                return;
            }
        }

        System.out.println("Enter the monkey's species:");
        String species = scanner.nextLine();
        String[] validSpecies = {"Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel monkey", "Tamarin"};
        boolean isValidSpecies = false;
        for (String validSpecie : validSpecies) {
            if (species.equalsIgnoreCase(validSpecie)) {
                isValidSpecies = true;
                break;
            }
        }
        if (!isValidSpecies) {
            System.out.println("Invalid species. Monkey intake cancelled.");
            return;
        }

        // Get monkey details
        System.out.println("Enter the monkey's gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter the monkey's age:");
        String age = scanner.nextLine();
        System.out.println("Enter the monkey's weight:");
        String weight = scanner.nextLine();
        System.out.println("Enter the acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("Enter the acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("Enter the training status:");
        String trainingStatus = scanner.nextLine();
        System.out.println("Enter the tail length:");
        String tailLength = scanner.nextLine();
        System.out.println("Enter the height:");
        String height = scanner.nextLine();
        System.out.println("Enter the body length:");
        String bodyLength = scanner.nextLine();

        // Create and add the new monkey
        Monkey newMonkey = new Monkey(name, species, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, false, "", tailLength, height, bodyLength);
        monkeyList.add(newMonkey);
        System.out.println("New monkey added successfully.");
    }

    // Complete reserveAnimal
    public static void reserveAnimal(Scanner scanner) {
        System.out.println("Enter animal type (dog/monkey):");
        String animalType = scanner.nextLine().toLowerCase();
        System.out.println("Enter the country of service:");
        String country = scanner.nextLine();

        if (animalType.equals("dog")) {
            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") &&
                    dog.getInServiceLocation().equalsIgnoreCase(country) &&
                    !dog.getReserved()) {
                    dog.setReserved(true);
                    System.out.println(dog.getName() + " has been reserved.");
                    return;
                }
            }
        } else if (animalType.equals("monkey")) {
            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") &&
                    monkey.getInServiceLocation().equalsIgnoreCase(country) &&
                    !monkey.getReserved()) {
                    monkey.setReserved(true);
                    System.out.println(monkey.getName() + " has been reserved.");
                    return;
                }
            }
        } else {
            System.out.println("Invalid animal type.");
            return;
        }
        System.out.println("No available " + animalType + " found in " + country);
    }

    // Complete printAnimals
    public static void printAnimals(String listType) {
        switch (listType.toLowerCase()) {
            case "dog":
                System.out.println("Dogs:");
                for (Dog dog : dogList) {
                    System.out.println(dog.getName() + " | " + dog.getTrainingStatus() + " | " 
                                       + dog.getAcquisitionLocation() + " | Reserved: " + dog.getReserved());
                }
                break;
            case "monkey":
                System.out.println("Monkeys:");
                for (Monkey monkey : monkeyList) {
                    System.out.println(monkey.getName() + " | " + monkey.getTrainingStatus() + " | " 
                                       + monkey.getAcquisitionLocation() + " | Reserved: " + monkey.getReserved());
                }
                break;
            case "available":
                System.out.println("Available Animals:");
                for (Dog dog : dogList) {
                    if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                        System.out.println("Dog: " + dog.getName() + " | " + dog.getTrainingStatus() + " | " 
                                           + dog.getAcquisitionLocation() + " | Reserved: " + dog.getReserved());
                    }
                }
                for (Monkey monkey : monkeyList) {
                    if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                        System.out.println("Monkey: " + monkey.getName() + " | " + monkey.getTrainingStatus() + " | " 
                                           + monkey.getAcquisitionLocation() + " | Reserved: " + monkey.getReserved());
                    }
                }
                break;
            default:
                System.out.println("Invalid list type");
        }
    }
}
