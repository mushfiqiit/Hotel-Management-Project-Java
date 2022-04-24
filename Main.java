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
