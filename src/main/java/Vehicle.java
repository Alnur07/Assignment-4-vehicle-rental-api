package kz.alnur.vehicle_rental_api;
public class Vehicle {
    private int id;
    private String model;
    private double pricePerDay;

    public Vehicle() { } // нужно для JSON (Jackson)

    public Vehicle(int id, String model, double pricePerDay) {
        this.id = id;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public Vehicle(String model, double pricePerDay) {
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public int getId() { return id; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }

    public void setId(int id) { this.id = id; }
    public void setModel(String model) { this.model = model; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
}
