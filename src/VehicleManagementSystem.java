import java.util.ArrayList;
import java.util.List;

public class VehicleManagementSystem {
    private List<Person> persons;
    private List<Vehicle> vehicles;

    public VehicleManagementSystem() {
        this.persons = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void deletePerson(String licenseNumber) {
        Person person = findPerson(licenseNumber);
        if (person != null) {
            persons.remove(person);
            // Transfer the vehicles back to the dealership if a person is deleted
            for (Vehicle vehicle : person.getVehicles()) {
                vehicle.setOwner(null); // Vehicle has no owner
                vehicles.add(vehicle);
            }
        } else {
            System.out.println("Person not found!");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle.getOwner() != null) {
            vehicle.getOwner().addVehicle(vehicle);
        }
        vehicles.add(vehicle);
    }

    public void deleteVehicle(String plateNumber) {
        Vehicle vehicle = findVehicle(plateNumber);
        if (vehicle != null) {
            if (vehicle.getOwner() != null) {
                vehicle.getOwner().removeVehicle(vehicle);
            }
            vehicles.remove(vehicle);
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    public void transferVehicle(String plateNumber, String transferType, String newOwnerLicense) {
        Vehicle vehicleToTransfer = findVehicle(plateNumber);

        if (vehicleToTransfer == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (transferType.equalsIgnoreCase("dealership")) {
            // Set owner to null to indicate the vehicle is back at the dealership
            vehicleToTransfer.setOwner(null);
            System.out.println("Vehicle transferred to dealership successfully!");
        } else if (transferType.equalsIgnoreCase("owner")) {
            Person newOwner = findPerson(newOwnerLicense);

            if (newOwner != null) {
                vehicleToTransfer.setOwner(newOwner); // Set the new owner
                newOwner.addVehicle(vehicleToTransfer); // Add vehicle to the new owner's list
                System.out.println("Vehicle transferred to new owner successfully!");
            } else {
                System.out.println("New owner not found.");
            }
        } else {
            System.out.println("Invalid transfer type. Transfer canceled.");
        }
    }

    public void updateVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateNumber().equalsIgnoreCase(updatedVehicle.getPlateNumber())) {
                vehicles.set(i, updatedVehicle);
                return;
            }
        }
    }

    public Person findPerson(String licenseNumber) {
        for (Person person : persons) {
            if (person.getLicenseNumber().equals(licenseNumber)) {
                return person;
            }
        }
        return null;
    }

    public Vehicle findVehicle(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public void generateTaxList() {
        for (Person person : persons) {
            System.out.println("Owner: " + person.getPersonDetails());
            double totalTax = 0;
            for (Vehicle vehicle : person.getVehicles()) {
                double tax = vehicle.calculateTax();
                System.out.println("    Vehicle: " + vehicle.getVehicleDetails() + " - Tax: " + tax);
                totalTax += tax;
            }
            System.out.println("Total tax for " + person.getName() + ": " + totalTax);
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
