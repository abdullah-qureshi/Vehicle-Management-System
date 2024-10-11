import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VehicleManagementSystem vms = new VehicleManagementSystem();
        Scanner scanner = new Scanner(System.in);
        String licenseNumber;
        String plateNumber;

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Add Person
                    System.out.print("Enter license number: ");
                    licenseNumber = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    vms.addPerson(new Person(licenseNumber, name, surname, address));
                    System.out.println("Person added successfully!");
                    break;

                case 2: // Edit Person
                    System.out.print("Enter license number of the person to edit: ");
                    licenseNumber = scanner.nextLine();
                    Person personToEdit = vms.findPerson(licenseNumber);
                    if (personToEdit != null) {
                        System.out.println("What would you like to change?");
                        System.out.println("1. Name");
                        System.out.println("2. Surname");
                        System.out.println("3. Address");
                        int editChoice = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        switch (editChoice) {
                            case 1:
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                personToEdit.setName(newName);
                                break;
                            case 2:
                                System.out.print("Enter new surname: ");
                                String newSurname = scanner.nextLine();
                                personToEdit.setSurname(newSurname);
                                break;
                            case 3:
                                System.out.print("Enter new address: ");
                                String newAddress = scanner.nextLine();
                                personToEdit.setAddress(newAddress);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        System.out.println("Person details updated!");
                    } else {
                        System.out.println("Person not found.");
                    }
                    break;

                case 3: // Delete Person
                    System.out.print("Enter license number of the person to delete: ");
                    licenseNumber = scanner.nextLine();
                    vms.deletePerson(licenseNumber);
                    System.out.println("Person deleted.");
                    break;

                case 4: // Add Vehicle (can be with or without an owner)
                    System.out.print("Enter plate number: ");
                    plateNumber = scanner.nextLine();
                    System.out.print("Enter maker: ");
                    String maker = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter vehicle type (car/motorcycle): ");
                    String type = scanner.nextLine();

                    Person owner = null;
                    System.out.print("Does the vehicle have an owner? (yes/no): ");
                    String hasOwner = scanner.nextLine();
                    if (hasOwner.equalsIgnoreCase("yes")) {
                        System.out.print("Enter owner's license number: ");
                        licenseNumber = scanner.nextLine();
                        owner = vms.findPerson(licenseNumber);
                        if (owner == null) {
                            System.out.println("Owner not found. Adding vehicle to dealership.");
                        }
                    }

                    if (type.equalsIgnoreCase("car")) {
                        System.out.print("Enter fuel type (petrol/diesel/hybrid): ");
                        String fuelType = scanner.nextLine();
                        System.out.print("Enter CO2 emissions: ");
                        double co2Emissions = scanner.nextDouble();
                        scanner.nextLine();
                        vms.addVehicle(new Car(plateNumber, maker, model, owner, fuelType, co2Emissions));
                        System.out.println("Car added successfully!");

                    } else if (type.equalsIgnoreCase("motorcycle")) {
                        System.out.print("Enter engine displacement: ");
                        double engineDisplacement = scanner.nextDouble();
                        scanner.nextLine();
                        vms.addVehicle(new Motorcycle(plateNumber, maker, model, owner, engineDisplacement));
                        System.out.println("Motorcycle added successfully!");
                    } else {
                        System.out.println("Invalid vehicle type.");
                    }
                    break;

                case 5: // Edit Vehicle
                    System.out.print("Enter plate number of the vehicle to edit: ");
                    plateNumber = scanner.nextLine();
                    Vehicle vehicleToEdit = vms.findVehicle(plateNumber);
                    if (vehicleToEdit != null) {
                        // Initialize default values with current vehicle details
                        maker = vehicleToEdit.getMaker();
                        model = vehicleToEdit.getModel();

                        // Ask for new maker
                        System.out.print("Current maker: " + maker + ". Enter new maker (or press Enter to keep): ");
                        String newMaker = scanner.nextLine();
                        if (!newMaker.isEmpty()) {
                            maker = newMaker; // Update maker only if user provided a new value
                        }

                        // Ask for new model
                        System.out.print("Current model: " + model + ". Enter new model (or press Enter to keep): ");
                        String newModel = scanner.nextLine();
                        if (!newModel.isEmpty()) {
                            model = newModel; // Update model only if user provided a new value
                        }

                        // Update the vehicle details
                        vehicleToEdit.setMaker(maker);
                        vehicleToEdit.setModel(model);

                        // Update vehicle in the management system
                        vms.updateVehicle(vehicleToEdit);
                        System.out.println("Vehicle details updated.");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;


                case 6: // Delete Vehicle
                    System.out.print("Enter plate number of the vehicle to delete: ");
                    plateNumber = scanner.nextLine();
                    vms.deleteVehicle(plateNumber);
                    System.out.println("Vehicle deleted.");
                    break;

                case 7: // Transfer Vehicle
                    System.out.print("Enter vehicle plate number: ");
                    plateNumber = scanner.nextLine();

                    // Ask for transfer type using numeric options
                    System.out.println("Transfer to:");
                    System.out.println("1. Dealership");
                    System.out.println("2. New Owner");
                    System.out.print("Enter your choice (1 or 2): ");
                    int transferChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    String newOwnerLicense = null;

                    if (transferChoice == 2) {
                        System.out.print("Enter new owner's license number: ");
                        newOwnerLicense = scanner.nextLine();
                    }

                    String transferType = (transferChoice == 1) ? "dealership" : "owner";

                    vms.transferVehicle(plateNumber, transferType, newOwnerLicense);
                    break;

                case 8: // Generate Tax List
                    System.out.println("Tax List:");
                    vms.generateTaxList();
                    break;

                case 9: // Search Person
                    System.out.print("Enter license number of the person to search: ");
                    licenseNumber = scanner.nextLine();
                    Person foundPerson = vms.findPerson(licenseNumber);
                    if (foundPerson != null) {
                        System.out.println("Person found: " + foundPerson.getPersonDetails());
                    } else {
                        System.out.println("Person not found.");
                    }
                    break;

                case 10: // Search Vehicle
                    System.out.print("Enter plate number of the vehicle to search: ");
                    plateNumber = scanner.nextLine();
                    Vehicle foundVehicle = vms.findVehicle(plateNumber);
                    if (foundVehicle != null) {
                        System.out.println("Vehicle found: " + foundVehicle);
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;

                case 11: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nVehicle Management System Menu:");
        System.out.println("1. Add Person");
        System.out.println("2. Edit Person");
        System.out.println("3. Delete Person");
        System.out.println("4. Add Vehicle");
        System.out.println("5. Edit Vehicle");
        System.out.println("6. Delete Vehicle");
        System.out.println("7. Transfer Vehicle");
        System.out.println("8. Generate Tax List");
        System.out.println("9. Search Person");
        System.out.println("10. Search Vehicle");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }
}
