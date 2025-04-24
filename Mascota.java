public class Mascota {
    private String name;
    private int age;
    private String species;

    // Constructor
    public Mascota(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    // Method to display animal details
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Species: " + species);
    }

    // Main method for testing
    public static void main(String[] args) {
    }
}