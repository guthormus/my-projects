/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2_project;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import static pl2_project.fileHandling.appendFile;
import static pl2_project.fileHandling.readFile;
import static pl2_project.fileHandling.writeFile;

/**
 *
 * @author Mohamed Hassan
 */
public class Mains {

  //methods 
    public static void implementDelete(String RoleName,Admin u) throws IOException{
            Scanner input = new Scanner(System.in);
            System.out.println(readFile(RoleName));
            System.out.println("enter id of admin you want to delete");
            byte ID = input.nextByte();
            u.deleteUserById(ID + "", RoleName);
            System.out.println("delete Successfully");
    }
    
    public static void implementUpdate(String RoleName,Admin u) throws IOException{
        Scanner input = new Scanner(System.in);
        Person p = new Person(){};
        System.out.println(readFile(RoleName));
              System.out.println("enter the id of user you want to update");
              int ID = input.nextInt();
              p.objectifyPerson(ID, RoleName, p);
              System.out.println("what do you want to update?");
              System.out.println("enter 1->'name' or 2->'email' or 3->'password' or 4->phone");
              byte n23 = input.nextByte();
              switch (n23) {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    String newName = input.next();
                    p.name = newName;
            }
                case 2 -> {
                    System.out.print("Enter new email: ");
                    String newEmail = input.next();
                    p.email = newEmail;
            }
                case 3 -> {
                    System.out.print("Enter new password: ");
                    String newPass = input.next();
                    p.password = newPass;
            }
                case 4 -> {
                    System.out.print("Enter new phone: ");
                    String newPhone = input.next();
                    p.phone = newPhone;
            }
                default -> System.out.println("Invalid input");
              }
              u.updateUserById(ID + "", p);
              System.out.println("Updated Successfully");
    }



  //Customer Main
  public static void CustomerMain() throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your Email : ");
    String email = input.next();
    System.out.println("Enter your Password : ");
    String pass = input.next();
    if (-1 != Customer.login(email, pass)) {
      String s = readFile("Customer");
      String[] c = s.split("\n");
      int idx = 0;
      while (!c[idx].startsWith(Customer.login(email, pass) + "")) {
        idx++;
      }
      int id = parseInt(c[idx].split(",")[0]);
      String name = c[idx].split(",")[1];
      String phone = c[idx].split(",")[4];
      Customer u = new Customer(name, email, pass, phone, "Customer");
      System.out.println("enter 1 to book , 2 to manageBooking , 3 to contact with pm , 4 to check state , 5 to update your information , 6 to view the request's respond:");
      byte choiseU = input.nextByte();
        switch (choiseU) {
            case 1 ->                 {
                    System.out.println("event name : ");
                    String eventName = input.next();
                    System.out.println("event date: ");
                    String eventDate = input.next();
                    System.out.println("event Place: ");
                    String eventPlace = input.next();
                    System.out.println("number of chairs : ");
                    int eventNOC = input.nextInt();
                    System.out.println("number of Tables : ");
                    int eventNOT = input.nextInt();
                    Event e2 = new Event(eventName, eventDate, eventPlace, eventNOC, eventNOT);
                    u.book(e2, id);
                    System.out.println(readFile("Customer" + id + "_book"));
                }
            case 2 ->                 {
                    Event e2 = new Event();
                    u.objectifyEvent("Customer" + id + "_book", e2);
                    if (e2.state == -1) {
                        System.out.println("What do you want to update?");
                        System.out.println("enter 'name' or 'date' or 'place' or 'numberOfChairs' or 'numberOfTables'");
                        String oldValue = input.next();
                        System.out.println("enter the new value ");
                        String newValue = input.next();
                       u.manageBooking(e2, oldValue, newValue, id);
                        System.out.println(readFile("Customer" + id + "_book"));
                    } else {
                        System.out.println("Can't update any data after approval");
                    }                          }
            case 3 -> {
                System.out.println(readFile("Chat_" + id));
                System.out.println("Enter The Message: ");
                String message = input.next();
                u.contactPM(message, id);
            }
            case 4 ->                 {
                    Event e2 = new Event();
                    System.out.println(u.checkState(e2, id));
                }
            case 5 -> {
                Admin a = new Admin("mo", "Admin@gmail", "ksdjfk", "021565262", "Admin");
                System.out.println("your data :");
                System.out.println(u.toString());
                System.out.println("what do you want to update?");
                System.out.println("enter 1->'name' or 2->'email' or 3->'password' or 4->phone");
                byte n = input.nextByte();
                switch (n) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        String newName = input.next();
                        u.name = newName;
                    }
                    case 2 -> {
                        System.out.print("Enter new email: ");
                        String newEmail = input.next();
                        u.email = newEmail;
                    }
                    case 3 -> {
                        System.out.print("Enter new password: ");
                        String newPass = input.next();
                        u.password = newPass;
                    }
                    case 4 -> {
                        System.out.print("Enter new phone: ");
                        String newPhone = input.next();
                        u.phone = newPhone;
                    }
                    default -> System.out.println("Invalid input");
                }       a.updateUserById(id + "", u);
                System.out.println(u.toString());
            }
            case 6 -> System.out.println(u.viewRespond(id));
            default -> {
            }
        }

    } else {
      System.out.println("Error:Invalid Email or Password");
    }
  }


  // Admin Main
  public static void AdminMain() throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your Email : ");
    String email = input.next();
    System.out.println("Enter your Password : ");
    String pass = input.next();
    if (-1 != Admin.login(email, pass)) {
      String s = readFile("Admin");
      String[] c = s.split("\n");
      int idx = 0;
      while (!c[idx].startsWith(Admin.login(email, pass) + "")) {
        idx++;
      }
      String name = c[idx].split(",")[2];
      String phone = c[idx].split(",")[4];
      Admin u = new Admin(name, email, pass, phone, "SP");
      // to complete
      System.out.println("enter 1 to view users, 2 to add user, 3 to update user, 4 to delete user: ");
      byte choice = input.nextByte();
      switch (choice) {
        case 1 -> {
            System.out.println("select user role: 1 for Admin, 2 for customer, 3 for pm, 4 for sp:");
            byte choice1 = input.nextByte();
            switch (choice1) {
                case 1 -> System.out.println(readFile("Admin"));
                case 2 -> System.out.println(readFile("Customer"));
                case 3 -> System.out.println(readFile("PM"));
                case 4 -> System.out.println(readFile("SP"));
                default -> System.out.println("Invalid input");
            } }
        case 2 -> {
            String userId = new String();
            File file = new File("currentUserId.txt");
            if (file.exists()) {
                userId = readFile("currentUserId");
            }else{
                writeFile("currentUserId","1");
                userId = "1";
            }
            System.out.println("select user role to Add: 1 for Admin, 2 for customer, 3 for pm, 4 for sp:");
            byte choice2 = input.nextByte();
            System.out.println("enter name");
            String namee = input.next();
            System.out.println("enter email");
            String emaill = input.next();
            System.out.println("enter password");
            String password = input.next();
            System.out.println("enter phone");
            String phonee = input.next();
            switch (choice2) {
                case 1 -> {
                    Admin a = new Admin(namee, emaill, password, phonee, "Admin");
                    u.addUser(a,userId);
                    writeFile("currentUserId",(parseInt(userId)+1)+"");
                    System.out.println("Added Successfully");
              }
                case 2 -> {
                    Customer c2 = new Customer(namee, emaill, password, phonee, "Customer");
                    u.addUser(c2,userId);
                    System.out.println("Added Successfully");
                    writeFile("currentUserId",(parseInt(userId)+1)+"");
              }
                case 3 -> {
                    ProjectManager Pm = new ProjectManager(namee, emaill, password, phonee, "PM");
                    u.addUser(Pm,userId);
                    System.out.println("Added Successfully");
                    writeFile("currentUserId",(parseInt(userId)+1)+"");
              }
                case 4 -> {
                    SP Sp = new SP(namee, emaill, password, phonee, "SP");
                    u.addUser(Sp,userId);
                    System.out.println("Added Successfully");
                    writeFile("currentUserId",(parseInt(userId)+1)+"");
              }
                default -> System.out.println("Invalid input");
            } }

        case 3 -> {
            System.out.println("enter 1 to update admin , 2 to update customer , 3 to update sp , 4 to update pm:");
            byte choice3 = input.nextByte();
            switch (choice3) {
                case 1 -> implementUpdate("Admin",u);
                case 2 -> implementUpdate("Customer",u);
                case 3 -> implementUpdate("SP",u);
                case 4 -> implementUpdate("PM",u);
                default -> System.out.println("Invalid input");
            } }


        case 4 -> {
            System.out.println("enter 1 to delete admin , 2 to delete customer , 3 to delete pm , 4 to delete sp");
            byte choice4 = input.nextByte();
            switch (choice4) {
                case 1 -> implementDelete("Admin",u);
                case 2 -> implementDelete("Customer",u);
                case 3 -> implementDelete("PM",u);
                case 4 -> implementDelete("SP",u);
                default -> System.out.println("invalid input");
            } }
      }
    } else {
      System.out.println("Invalid Email or Password!");
    }
  }

  
  // SP Main
  public static void SpMain() throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your Email : ");
    String email = input.next();
    System.out.println("Enter your Password : ");
    String pass = input.next();
    if (-1 != SP.login(email, pass)) {
      String s = readFile("SP");
      String[] c = s.split("\n");
      int idx = 0;
      while (!c[idx].startsWith(SP.login(email, pass) + "")) {
        idx++;
      }
      String name = c[idx].split(",")[2];
      String phone = c[idx].split(",")[4];
      SP u = new SP(name, email, pass, phone, "SP");
      System.out.println("Enter 1 To update State , 2 to set Price:");
      byte choice = input.nextByte();
      switch (choice) {
        case 1 -> {
            if(readFile("PM_Resends").trim().equals("")){
            System.out.println("The PM Resends file is Empty");
            }else{
            System.out.println("{ "+readFile("PM_Resends")+" }");
            System.out.println("Enter the Id : ");
            int Id0 = input.nextInt();
            System.out.println("----------------\n"+readFile("Manage"+Id0+"SP")+"\n----------------");
            System.out.println("Enter 1 to approve , 2 to disapprove");
            int State = input.nextInt();
            System.out.println(u.updateState(Id0, State));
            }
            }
        case 2 -> {
            Event ee = new Event();
            if(readFile("PM_Resends").trim().equals("")){
                System.out.println("The PM Resends file is Empty");
            }else{
            System.out.println("{ "+readFile("PM_Resends")+" }");
            System.out.println("Enter the Id : ");
            int Id00 = input.nextInt();
            System.out.println(u.SetPrice(ee, Id00));
             }
            }
        default -> System.out.println("Invalid input");
      }

    } else {
      System.out.println("Invalid Email or Password!");
    }

  }

  
  
  // PM Main
  public static void PmMain() throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your Email : ");
    String email = input.next();
    System.out.println("Enter your Password : ");
    String pass = input.next();
    if (-1 != ProjectManager.login(email, pass)) {
      String s = readFile("PM");
      String[] c = s.split("\n");
      int idx = 0;
      while (!c[idx].startsWith(ProjectManager.login(email, pass) + "")) {
        idx++;
      }
      String name = c[idx].split(",")[2];
      String phone = c[idx].split(",")[4];
      ProjectManager u = new ProjectManager(name, email, pass, phone, "PM");

      System.out.println("Enter 1 to contact with customer , 2 to view customer request and send it to SP , 3 to send bill to customer file");

      byte choice = input.nextByte();
      
      switch (choice) {
        case 1 ->{
            System.out.println(readFile("Customer"));
            System.out.println("Enter the ID of customer");
            int Id = input.nextInt();
            System.out.println(readFile("Chat_"+Id));
            System.out.println("Enter your messege");
            String message = input.next();
            u.contactCustomer(Id, message);
            }

        case 2 -> {
            if(readFile("CustomersBooking").trim().equals("")){
                System.out.println("there's no events booked");
            }else{
            System.out.println("{ "+readFile("CustomersBooking")+" }");
            System.out.println("Enter the Id of customer");
            int Id0 = input.nextInt();
            System.out.println(u.receiveRequest(Id0));
            System.out.println("\nThe Request has sent to SP");
            writeFile("PM_Resends","CustomerID : "+Id0);
            }
        }
            
        case 3 -> {
            System.out.println("{ "+readFile("CustomersBooking")+" }");
            System.out.println("Enter Id:");
            int Id11 = input.nextInt();
            Event e22 = new Event();
            File file = new File("Manage"+Id11+"SP.txt");
            if (file.exists()) {
                u.objectifyEventPm("Manage" + Id11 + "SP", e22);
                switch (e22.state) {
                case 1 -> {
                    u.showBill(e22,Id11);
                    System.out.println("Bill has been added to customer file Successfully");
                }
                case -1 -> System.out.println("The request is in waiting ");
                case 0 -> {System.out.println("The request is rejected");
                String f = readFile("Manage" + Id11 + "SP");
                writeFile("Customer" + Id11 + "_book", f);
                appendFile("Customer" + Id11 + "_book", "\nThe Request has been Rejected :(");}
                default -> {}
                 }
            } else {
                System.out.println("You should send it to SP first");
            }
            
             }
        
        default -> System.out.println("invalid input");
      }
    } else {
      System.out.println("Invalid Email or Password!");
    }
  }

}
