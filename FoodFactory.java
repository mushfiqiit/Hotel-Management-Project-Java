import java.util.*;

public class FoodFactory {

    public void FoodFactory() { }

    public Food createFood(String foodType) {
        if(foodType=="Sandwich") {
            return new Sandwich();
        }
        else if(foodType=="Pasta") {
            return new Pasta();
        }

        else if(foodType=="Noodles") {
            return new Noodles();
        }

        else if(foodType=="Coke")
        {
            return new Coke();
        }
    }
}
