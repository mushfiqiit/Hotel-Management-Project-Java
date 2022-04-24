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
    private static List<String> menuMessages=new ArrayList<String>();

    Hotel() {  

    menuMessages.add("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n")
    menuMessages.add("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");


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

    private static void showMenu() {
        System.out.println("\nEnter your choice :\n1.Display room details\n"+
        "2.Display room availability \n"+
        "3.Book\n4.Order food\n"+
        "5.Checkout\n"+
        "6.Exit\n");
    }

    private static void showMenuMessage() {
        System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n");
    }

    static Scanner scanner = new Scanner(System.in);

    static void CustomerDetails(int roomNumber)
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
            showMenu();
            choice = scanner.nextInt();

            if(choice>=1 && choice<=3) {
                    showMenuMessage();
                }

                else if(choice>=4 && choice<=5) {
                    System.out.print("Room Number -");
                }
                choice2 = scanner.nextInt();

            switch(choice){
                case 1:
                        Hotel.features(choice2);
                    break;
                case 2:
                         Hotel.availability(choice2);
                    break;
                case 3:
                         Hotel.bookroom(choice2);                     
                    break;
                case 4:
                         if(choice2>60 || choice2<=0)
                             System.out.println("Room doesn't exist");
                         else
                             order(choice2);
                case 5: if(choice2>60 || choice2<=0)
                             System.out.println("Room doesn't exist");
                         else
                             deallocate(choice2);
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

    static void order(int roomNumber)
    {
        int i,q;
        List<String> foodList=new ArrayList<>();
        foodList.add("Sandwich");
        foodList.add("Pasta");
        foodList.add("Noodles");
        foodList.add("Coke");
        FoodFactory foodFactory=new FoodFactory();
        char wish;
         try{
             System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
        do
        {
            i = scanner.nextInt();
            System.out.print("Quantity- ");
            q=scanner.nextInt();
            Food orderedFood=foodFactory.createFood(foodList.get(i-1));
            rooms.get(roomNumber).addFood(orderedFood);

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

    static void deallocate(int roomNumber)
    {
        int j;
        char w;
        if(rooms.get(roomNumber).getCustomer()!=null) {
            System.out.println("Room used by "+ rooms.get(roomNumber).getCustomer().getName());
        }
        else 
        {    
            System.out.println("Empty Already");
                return;
        }
        System.out.println("Do you want to checkout ?(y/n)");
         w=scanner.next().charAt(0);
        if(w=='y'||w=='Y')
        {
            bill(roomNumber,rtype);
            rooms.get(roomNumber).setCustomer(null);
            System.out.println("Deallocated succesfully");
        }

    }

    static void printBill() {
        System.out.println("\nRoom Charge - "+4000);
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
    }

    static void bill(int roomNumber)
    {
        double amount=0;
        String list[]={"Sandwich","Pasta","Noodles","Coke"};

        amount+=rooms.get(roomNumber).getChargePerDay();

        for(Food footItem:rooms.get(roomNumber).getFood()) {
            amount+=footItem.price;
            String format = "%-10s%-10s%-10s%n";
            System.out.printf(format,list[footItem.itemno-1],footItem.quantity,footItem.price );
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

        for(j=startIndexes.get(i-1);j<endIndexes.get(i-1);j++) {
            if(rooms.get(j).getCustomer()!=null) {
                System.out.print(j+1+",");
            }
        }
        System.out.print("\nEnter room number: ");

        try{
            roomNumber=scanner.nextInt();
            roomNumber--;
            if(rooms.get(roomNumber).getCustomer()!=null)
                System.out.println("Not Available");
            CustomerDetails(roomNumber);
            }
            catch(Exception e)
            {
                System.out.println("Invalid Option");
                return;
            }

        System.out.println("Room Booked");
    }

}
