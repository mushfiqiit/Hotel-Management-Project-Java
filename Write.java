import java.io.*;

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