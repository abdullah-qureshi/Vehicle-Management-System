public class Motorcycle extends Vehicle {
    private double engineDisplacement; // in cc

    public Motorcycle(String plateNumber, String maker, String model, Person owner, double engineDisplacement) {
        super(plateNumber, maker, model, owner);
        this.engineDisplacement = engineDisplacement;
    }

    public double calculateTax() {
        return engineDisplacement * 0.1;
    }

    public String getVehicleDetails() {
        return super.getVehicleDetails() + " [Engine Displacement: " + engineDisplacement + "cc]";
    }
}
