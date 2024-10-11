// Car Class (inherits from Vehicle)
class Car extends Vehicle {
    private String fuelType;
    private double co2Emissions;

    public Car(String plateNumber, String maker, String model, Person owner, String fuelType, double co2Emissions) {
        super(plateNumber, maker, model, owner);
        this.fuelType = fuelType;
        this.co2Emissions = co2Emissions;
    }

    public double calculateTax() {
        switch (fuelType.toLowerCase()) {
            case "petrol":
                return co2Emissions * 1.4;
            case "diesel":
                return co2Emissions * 1.8;
            case "hybrid":
                return co2Emissions * 1.2;
            default:
                return 0;
        }
    }

    public String getVehicleDetails() {
        return String.format("Car - %s, CO2: %.2f g/km, Fuel: %s", super.getVehicleDetails(), co2Emissions, fuelType);
    }
}