import java.util.*;


public abstract class Room {
    protected int bedSize;
    protected boolean ACAvailable;
    protected Customer customer;
    protected boolean freeBreakfastAvailable;
    protected double chargePerDay;
    private ArrayList<Food> food =new ArrayList<Food>();

    private String isAvailable(boolean availability) {
        if(availability) return "Yes";
        return "No"; 
    }


    public Room() {  }

    public void addFood(Food givenFood) {
        this.food.add(givenFood);
    }

    public void printFeatures() {
        System.out.println("Bed Size :" + bedSize +
        "\nAC : "+  isAvailable(ACAvailable) +
        "\nFree breakfast : "+ isAvailable(freeBreakfastAvailable) +
        "\nCharge per day:" + chargePerDay);
    }

    public void setCustomer(Customer customer) {
        this.customer=customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getChargePerDay() {
        return this.chargePerDay;
    }

    public ArrayList<Food> getFood() {
        return this.food;
    }
}
