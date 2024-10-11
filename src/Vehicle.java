// Vehicle Class (abstract)
abstract class Vehicle {
    private String plateNumber;
    private String maker;
    private String model;
    private Person owner;

    public Vehicle(String plateNumber, String maker, String model, Person owner) {
        this.plateNumber = plateNumber;
        this.maker = maker;
        this.model = model;
        this.owner = owner;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public Person getOwner() {
        return owner;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public abstract double calculateTax();

    public String getVehicleDetails() {
        return String.format("Plate: %s, Maker: %s, Model: %s, Owner: %s",
                plateNumber, maker, model, owner != null ? owner.getName() : "No Owner");
    }
}




