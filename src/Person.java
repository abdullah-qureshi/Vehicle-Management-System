import java.util.ArrayList;
import java.util.List;

class Person {
    private String licenseNumber;
    private String name;
    private String surname;
    private String address;
    private List<Vehicle> vehicles = new ArrayList<>();

    // Constructor
    public Person(String licenseNumber, String name, String surname, String address) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    // Getters
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public double getTotalTax() {
        double totalTax = 0;
        for (Vehicle vehicle : vehicles) {
            totalTax += vehicle.calculateTax();
        }
        return totalTax;
    }

    public String getPersonDetails() {
        return String.format("License: %s, Name: %s, Address: %s", licenseNumber, getName(), address);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
