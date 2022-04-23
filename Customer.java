import java.util.*;

public class Customer {
    private String name, contact, gender;
   public Customer() {  } 
   static Scanner scanner = new Scanner(System.in);
   
   public void takeInput() {
    System.out.print("\nEnter customer name: ");
    name = scanner.next();
    System.out.print("Enter contact number: ");
    contact=scanner.next();
    System.out.print("Enter gender: ");
    gender = scanner.next();
   }
}
