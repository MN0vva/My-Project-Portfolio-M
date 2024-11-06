public class Dog {
    // Attributes
    private int dogSpaceAvailable;
    private double dogWeight;
    private boolean dogGrooming;

    // Constructor
    public Dog(int spaceAvailable, double weight, boolean dogGrooming) {
        this.dogSpaceAvailable = spaceAvailable;
        this.dogWeight = weight;
        this.dogGrooming = dogGrooming;
    }

    // Accessors (getters)
    public int getDogSpaceAvailable() {
        return this.dogSpaceAvailable;
    }

    public double getDogWeight() {
        return this.dogWeight;
    }

    public boolean getDogGrooming() {
        return this.dogGrooming;
    }

    // Mutators (setters)
    public void setDogSpaceAvailable(int spaceAvailable) {
        this.dogSpaceAvailable = spaceAvailable;
    }

    public void setDogWeight(double weight) {
        this.dogWeight = weight;
    }

    public void setDogGrooming(boolean dogGrooming) {
        this.dogGrooming = dogGrooming;
    }

    // Main Method to test the Dog class
    public static void main(String[] args) {
        // Create Dog Object
        Dog myDog = new Dog(10, 20.5, true);

        // Print Dog details
        System.out.println("Dog Space Available: " + myDog.getDogSpaceAvailable());
        System.out.println("Dog Weight: " + myDog.getDogWeight());
        System.out.println("Dog Grooming: " + myDog.getDogGrooming());
    }
}