import java.io.Serializable;
import java.util.*;

public abstract class Food implements Serializable {
    protected int quantity;   
    protected float price;
    
    public Food(int quantity)
    {
        this.quantity=quantity;
    }

    public Food()
    {
        
    }

    public float getPrice() {
        return this.price;
    }

    public abstract void setPrice();
}