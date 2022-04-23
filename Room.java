public abstract class Room {
    protected int bedSize;
    protected boolean ACAvailable;
    protected Customer customer;
    protected boolean freeBreakfastAvailable;
    protected int chargePerDay;

    private String isAvailable(boolean availability) {
        if(availability) return "Yes";
        return "No"; 
    }


    public Room() {  }

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
}
