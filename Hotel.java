import javax.management.InstanceAlreadyExistsException;
import javax.management.remote.rmi.RMIConnectionImpl_Stub;

import java.util.*;


public class Hotel {
    private static Hotel instance;
    RoomFactory roomFactory=new RoomFactory();
    private static List<Room> rooms=new ArrayList<Room>();
    private static List<String> roomTypes=new ArrayList<String>();
    private static HashMap<String, Integer> roomIndexMap = new HashMap<String, Integer>();
    private static List<Integer> startIndexes=new ArrayList<Integer>();
    private static List<Integer> endIndexes=new ArrayList<Integer>();

    


    

    private Hotel() {  
    startIndexes.add(0);
    startIndexes.add(10);
    startIndexes.add(30);
    startIndexes.add(40);

    endIndexes.add(10);
    endIndexes.add(30);
    endIndexes.add(40);
    endIndexes.add(60);

    roomTypes.add("LuxuryDoubleRoom");
    roomTypes.add("DeluxeDoubleRoom");
    roomTypes.add("LuxurySingleRoom");
    roomTypes.add("DeluxeSingleRoom");
    
    roomIndexMap.put(roomTypes.get(0), 0);
    roomIndexMap.put(roomTypes.get(1), 10);
    roomIndexMap.put(roomTypes.get(2), 30);
    roomIndexMap.put(roomTypes.get(3), 40);


        for(int i=0, j=0;i<60;i++) {
            if(i==10 || i==30 || i==40) j++;
//Factory Pattern
            Room roomToAdd=RoomFactory.createRoom(roomTypes.get(j));
            rooms.add(roomToAdd);
        }
     }
// Singleton pattern
    public static Hotel getinstance()
    {
        if(instance==null)
        {
            instance=new Hotel();
        }
        return instance;
    }


    static holder hotel_ob=new holder();
    static Scanner scanner = new Scanner(System.in);
    static void CustomerDetails(int i,int roomNumber)
    {
        Customer customer=new Customer();
        customer.takeInput();
        rooms.get(roomNumber).setCustomer(customer);
    }


    public static void reception()
    {
        Scanner scanner = new Scanner(System.in);
        int choice, choice2;
        char wish;
        x:
        do{

            System.out.println("\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
            choice = scanner.nextInt();
            switch(choice){
                case 1: System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n");
                        choice2 = scanner.nextInt();
                        Hotel.features(choice2);
                    break;
                case 2:System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
                         choice2 = scanner.nextInt();
                         Hotel.availability(choice2);
                    break;
                case 3:System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
                         choice2 = scanner.nextInt();
                         Hotel.bookroom(choice2);                     
                    break;
                case 4:
                     System.out.print("Room Number -");
                         choice2 = scanner.nextInt();
                         if(choice2>60)
                             System.out.println("Room doesn't exist");
                         else if(choice2>40)
                             Hotel.order(choice2-41,4);
                         else if(choice2>30)
                             Hotel.order(choice2-31,3);
                         else if(choice2>10)
                             Hotel.order(choice2-11,2);
                         else if(choice2>0)
                             Hotel.order(choice2-1,1);
                         else
                             System.out.println("Room doesn't exist");
                         break;
                case 5:                 
                    System.out.print("Room Number -");
                         choice2 = scanner.nextInt();
                         if(choice2>60)
                             System.out.println("Room doesn't exist");
                         else if(choice2>40)
                             Hotel.deallocate(choice2-41,4);
                         else if(choice2>30)
                             Hotel.deallocate(choice2-31,3);
                         else if(choice2>10)
                             Hotel.deallocate(choice2-11,2);
                         else if(choice2>0)
                             Hotel.deallocate(choice2-1,1);
                         else
                             System.out.println("Room doesn't exist");
                         break;
                case 6:break x;
                    
            }
               
                System.out.println("\nContinue : (y/n)");
                wish=scanner.next().charAt(0); 
                if(!(wish=='y'||wish=='Y'||wish=='n'||wish=='N'))
                {
                    System.out.println("Invalid Option");
                    System.out.println("\nContinue : (y/n)");
                    wish=scanner.next().charAt(0); 
                }
                
            }while(wish=='y'||wish=='Y'); 
    }

    static void order(int rn,int rtype)
    {
        int i,q;
        char wish;
         try{
             System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
        do
        {
            i = scanner.nextInt();
            System.out.print("Quantity- ");
            q=scanner.nextInt();
           
              switch(rtype){
            case 1: hotel_ob.luxury_doublerrom[rn].food.add(new Food(i,q));
                break;
            case 2: hotel_ob.deluxe_doublerrom[rn].food.add(new Food(i,q));
                break;
            case 3: hotel_ob.luxury_singleerrom[rn].food.add(new Food(i,q));
                break;
            case 4: hotel_ob.deluxe_singleerrom[rn].food.add(new Food(i,q));
                break;                                                 
        }
              System.out.println("Do you want to order anything else ? (y/n)");
              wish=scanner.next().charAt(0); 
        }while(wish=='y'||wish=='Y');  
        }
         catch(NullPointerException e)
            {
                System.out.println("\nRoom not booked");
            }
         catch(Exception e)
         {
             System.out.println("Cannot be done");
         }
    }

    static void deallocate(int rn,int rtype)
    {
        int j;
        char w;
        switch (rtype) {
            case 1:               
                if(hotel_ob.luxury_doublerrom[rn]!=null)
                    System.out.println("Room used by "+hotel_ob.luxury_doublerrom[rn].name);                
                else 
                {    
                    System.out.println("Empty Already");
                        return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                 w=scanner.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.luxury_doublerrom[rn]=null;
                    System.out.println("Deallocated succesfully");
                }
                
                break;
            case 2:
                if(hotel_ob.deluxe_doublerrom[rn]!=null)
                    System.out.println("Room used by "+hotel_ob.deluxe_doublerrom[rn].name);                
                else 
                {    
                    System.out.println("Empty Already");
                        return;
                }
                System.out.println(" Do you want to checkout ?(y/n)");
                 w=scanner.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.deluxe_doublerrom[rn]=null;
                    System.out.println("Deallocated succesfully");
                }
                 
                break;
            case 3:
                if(hotel_ob.luxury_singleerrom[rn]!=null)
                    System.out.println("Room used by "+hotel_ob.luxury_singleerrom[rn].name);                
                else 
                 {    
                    System.out.println("Empty Already");
                        return;
                }
                System.out.println(" Do you want to checkout ? (y/n)");
                w=scanner.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.luxury_singleerrom[rn]=null;
                    System.out.println("Deallocated succesfully");
                }
                
                break;
            case 4:
                if(hotel_ob.deluxe_singleerrom[rn]!=null)
                    System.out.println("Room used by "+hotel_ob.deluxe_singleerrom[rn].name);                
                else 
                 {    
                    System.out.println("Empty Already");
                        return;
                }
                System.out.println(" Do you want to checkout ? (y/n)");
                 w=scanner.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.deluxe_singleerrom[rn]=null;
                    System.out.println("Deallocated succesfully");
                }
                break;
            default:
                System.out.println("\nEnter valid option : ");
                break;
        }
    }


    static void bill(int rn,int rtype)
    {
        double amount=0;
        String list[]={"Sandwich","Pasta","Noodles","Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");
               
        switch(rtype)
        {
            case 1:
                amount+=4000;
                    System.out.println("\nRoom Charge - "+4000);
                    System.out.println("\n===============");
                    System.out.println("Food Charges:- ");
                    System.out.println("===============");
                     System.out.println("Item   Quantity    Price");
                    System.out.println("-------------------------");
                    for(Food obb:hotel_ob.luxury_doublerrom[rn].food)
                    {
                        amount+=obb.price;
                        String format = "%-10s%-10s%-10s%n";
                        System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                    }
                    
                break;
            case 2:amount+=3000;
                    System.out.println("Room Charge - "+3000);
                    System.out.println("\nFood Charges:- ");
                    System.out.println("===============");
                     System.out.println("Item   Quantity    Price");
                    System.out.println("-------------------------");
                    for(Food obb:hotel_ob.deluxe_doublerrom[rn].food)
                    {
                        amount+=obb.price;
                        String format = "%-10s%-10s%-10s%n";
                        System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                    }
                break;
            case 3:amount+=2200;
                    System.out.println("Room Charge - "+2200);
                    System.out.println("\nFood Charges:- ");
                    System.out.println("===============");
                    System.out.println("Item   Quantity    Price");
                    System.out.println("-------------------------");
                    for(Food obb:hotel_ob.luxury_singleerrom[rn].food)
                    {
                        amount+=obb.price;
                        String format = "%-10s%-10s%-10s%n";
                        System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                    }
                break;
            case 4:amount+=1200;
                    System.out.println("Room Charge - "+1200);
                    System.out.println("\nFood Charges:- ");
                    System.out.println("===============");
                    System.out.println("Item   Quantity    Price");
                    System.out.println("-------------------------");
                    for(Food obb: hotel_ob.deluxe_singleerrom[rn].food)
                    {
                        amount+=obb.price;
                        String format = "%-10s%-10s%-10s%n";
                        System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                    }
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- "+amount);
    }


    static void availability(int i)
    {
      int j,count=0;
      int startIndex=startIndexes.get(i-1);
      int endIndex=startIndexes.get(i-1);

      for(j=startIndex;j<endIndex;j++)
      if(rooms.get(j).getCustomer()==null) count++;


    System.out.println("Number of rooms available : "+count);
    }


    static void features(int i)
    {
        Room roomToShow=rooms.get(roomIndexMap.get(roomTypes.get(i-1)));
        roomToShow.printFeatures();
        }
    }


    static void bookroom(int i)
    {
        int j;
        int roomNumber;
        System.out.println("\nChoose room number from : ");
        switch (i) {
            case 1:
                for(j=0;j<hotel_ob.luxury_doublerrom.length;j++)
                {
                    if(hotel_ob.luxury_doublerrom[j]==null)
                    {
                        System.out.print(j+1+",");
                    }
                }
                System.out.print("\nEnter room number: ");
                try{
                roomNumber=scanner.nextInt();
                roomNumber--;
                if(hotel_ob.luxury_doublerrom[roomNumber]!=null)
                    throw new NotAvailable();
                CustomerDetails(i,roomNumber);
                }
                catch(Exception e)
                {
                    System.out.println("Invalid Option");
                    return;
                }
                break;
            case 2:
                 for(j=0;j<hotel_ob.deluxe_doublerrom.length;j++)
                {
                    if(hotel_ob.deluxe_doublerrom[j]==null)
                    {
                        System.out.print(j+11+",");
                    }
                }
                System.out.print("\nEnter room number: ");
                try{
                roomNumber=scanner.nextInt();
                roomNumber=roomNumber-11;
                if(hotel_ob.deluxe_doublerrom[roomNumber]!=null)
                    throw new NotAvailable();
                CustomerDetails(i,roomNumber);
                }
                catch(Exception e)
                {
                    System.out.println("Invalid Option");
                    return;
                }
                break;
            case 3:
                  for(j=0;j<hotel_ob.luxury_singleerrom.length;j++)
                {
                    if(hotel_ob.luxury_singleerrom[j]==null)
                    {
                        System.out.print(j+31+",");
                    }
                }
                System.out.print("\nEnter room number: ");
                try{
                roomNumber=scanner.nextInt();
                roomNumber=roomNumber-31;
                if(hotel_ob.luxury_singleerrom[roomNumber]!=null)
                    throw new NotAvailable();
                CustomerDetails(i,roomNumber);
                }
                catch(Exception e)
                {
                    System.out.println("Invalid Option");
                    return;
                }
                break;
            case 4:
                  for(j=0;j<hotel_ob.deluxe_singleerrom.length;j++)
                {
                    if(hotel_ob.deluxe_singleerrom[j]==null)
                    {
                        System.out.print(j+41+",");
                    }
                }
                System.out.print("\nEnter room number: ");
                try{
                roomNumber=scanner.nextInt();
                roomNumber=roomNumber-41;
                if(hotel_ob.deluxe_singleerrom[roomNumber]!=null)
                    throw new NotAvailable();
                CustomerDetails(i,roomNumber);
                }
                catch(Exception e)
                {
                   System.out.println("Invalid Option");
                    return;
                }
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
        System.out.println("Room Booked");
    }

}
