import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



class NotAvailable extends Exception
{
    @Override
    public String toString()
    {
        return "Not Available !";
    }
}



class write implements Runnable
{
    Hotel hotel_ob;
    write(Hotel hotel_ob)
    {
        this.hotel_ob=hotel_ob;
    }
    @Override
    public void run() {
          try{
        FileOutputStream fileOutputStream=new FileOutputStream("backup");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(hotel_ob);
        }
        catch(Exception e)
        {
            System.out.println("Error in writing "+e);
        }         
        
    }
    
}

public class Main {
    public static void main(String[] args)
    {
        Hotel paradiseHotel=Hotel.getinstance();

        try
        {   
        Thread t=new Thread(new write(paradiseHotel));
        t.start();
        }  
        catch(Exception e)
        {
            System.out.println("Not a valid input");
        }

    }
}
