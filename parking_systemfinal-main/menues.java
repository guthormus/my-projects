import java.util.Scanner;


public class menues{


    public static void homeMenue() {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("===== Parking System Menu =====");
                System.out.println("1. Customer");
                System.out.println("2. Operator");
                System.out.println("3. Admin");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        customerMenu();
                        break;
                    case 2:
                        OperatorMenu();
                        break;
                    case 3:
                        adminMenu();
                        break;
                    case 4:
                        System.out.println("Exiting the Parking System. Goodbye!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred");
            homeMenue();
        }
    }
    public static void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        //Customer customer = new Customer("");

        System.out.println("Customer Menu");
        System.out.println("1. Print Entry Ticket");
        System.out.println("2. Print Exit Ticket");
        System.out.println("3. Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        Operator operator=new Operator();
        Customer customer = new Customer();
        switch (choice) {
            case 1:
                System.out.print("Enter vehicle plate number: ");
                String plateNumber = scanner.nextLine();
                System.out.println(customer.CallingOperator(plateNumber));

/*
              operator.generateEntryID(plateNumber);
              operator.assignedSlot(plateNumber);
                System.out.println(operator.entryTicket(plateNumber));

 */


                //operator.recordEntryTime();
                //operator.assignedSlot(plateNumber);
               // System.out.println(database_handle.getCustomerData(plateNumber));

                //customer.setVehicleNumber(plateNumber);
                //customer.park(1);
                break;
            case 2:
                System.out.print("Enter entry ID: ");
                String providedEntryID = scanner.nextLine();
                
                operator.calculateParkingDurationHours(providedEntryID);
                operator.calculateParkingFee(providedEntryID);
                System.out.println(operator.printExitTicket(providedEntryID));
                operator.freeSpot(providedEntryID);
               // operator.freeSpot();
                //customer.printExitTicket(providedEntryID);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                break;
        }
    }
    public static void OperatorMenu() {
        Operator entryOperator = new Operator();
        Scanner scanner = new Scanner(System.in);
        boolean backToMain = false;

        while (!backToMain) {
            System.out.print("Enter Operator Username: ");

            String username = scanner.nextLine();
            System.out.print("Enter Operator Password: ");
            String pass = scanner.nextLine();
            int check = database_handle.checkOperator(username,pass);
            if (check==1) {
                System.out.println("Login successful!");
                operatorActions(entryOperator);
                backToMain = true;
            } else {
                System.out.println("Invalid credentials.");
                    homeMenue();
            }
        }
    }

    public static void operatorActions(Operator entryOperator) {
        Scanner scanner = new Scanner(System.in);
        boolean backToLogin = false;

        while (!backToLogin) {
            System.out.println("===== Entry Station Operator Menu =====");
            System.out.println("1. Display Free Slots");
            System.out.println("2. Back to Login");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            Operator operator = new Operator();
            switch (choice) {
                case 1:
                    System.out.println(operator.freeSpots());
                    break;
                case 2:
                    backToLogin = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean backToMain = false;

        System.out.println("Admin Menu");
        System.out.println("1. Log in");
        System.out.println("2. Sign UP");
        System.out.println("3. Back");
        System.out.print("Choose an option: ");

        int choice1 = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        //Admin adminLogin;
        Admin adminSignup;
        switch (choice1) {
            case 1:
                //adminLogin = new Admin();
                if(file.adminCheck()){
                    adminActions();
                }
                else System.out.println("Access Denied");
                break;
            case 2:
                Scanner adminScanner = new Scanner(System.in);
                System.out.print("Enter Username: ");
                String username = adminScanner.nextLine();
                System.out.print("Enter Password: ");
                String pass = adminScanner.nextLine();
                adminSignup = new Admin(username, pass);
                adminMenu();
                break;
            case 3:
                backToMain = true;
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                break;
        }


    }

   public static void adminActions(){
        try {
            Scanner scanner = new Scanner(System.in);
            boolean backToMain = false;
            while (!backToMain) {
                System.out.println("===== Admin Menu =====");
                System.out.println("1. Add Slots");
                System.out.println("2. View Total Slots");
                System.out.println("3. Reset username and password");
                System.out.println("4. Add customer");
                System.out.println("5. Add Operator");
                System.out.println("6. Update Customer ID");
                System.out.println("7. Update Operator Name");
                System.out.println("8. Update Operator Pass");
                System.out.println("9. Update operator shift");
                System.out.println("10. Delete Customer");
                System.out.println("11. Delete Operator");
                System.out.println("12. View Parked Cars");
                System.out.println("13. View shifts Report");
                System.out.println("14. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice2 = scanner.nextInt();
                Admin admin=new Admin();
                switch (choice2) {
                    case 1:
                        System.out.println(admin.addSpots());
                        break;
                    case 2:
                        System.out.println(admin.viewTotalSpots());
                        break;
                    case 3:
                    /*Scanner adminScanner = new Scanner(System.in);
                    System.out.print("Enter new Username: ");
                    String username = adminScanner.nextLine();
                    System.out.print("Enter new Password: ");
                    String pass = adminScanner.nextLine();*/
                        System.out.println(admin.setUsernameAndPass());
                        //adminMenu();
                        break;
                    case 4:
                        Scanner adminScanner1 = new Scanner(System.in);
                        System.out.print("Enter Customer Id: ");
                        String ID = adminScanner1.nextLine();
                        System.out.print("Enter Customer PlateNummber: ");
                        String platenumber = adminScanner1.nextLine();
                        System.out.println(admin.addCustomer(ID, platenumber));
                        break;
                    case 5:
                        Scanner adminScanner2 = new Scanner(System.in);
                        System.out.print("Enter Operator Username: ");
                        String username = adminScanner2.nextLine();
                        System.out.print("Enter Operator Password: ");
                        String pass = adminScanner2.nextLine();
                        System.out.print("Enter Operator Shift No: ");
                        int shift = adminScanner2.nextInt();
                        System.out.println(admin.addOperator(username, pass, shift));
                        break;
                    case 6:
                        Scanner adminScanner3 = new Scanner(System.in);
                        System.out.print("Enter Old Customer ID: ");
                        String oldID = adminScanner3.nextLine();
                        System.out.print("Enter new Customer ID: ");
                        String newID = adminScanner3.nextLine();
                        System.out.println(admin.updateUser(oldID, newID));
                        break;
                    case 7:
                        Scanner adminScanner4 = new Scanner(System.in);
                        System.out.print("Enter Old Username: ");
                        String oldOpName = adminScanner4.nextLine();
                        System.out.print("Enter new Username: ");
                        String newOpName = adminScanner4.nextLine();
                        System.out.println(admin.updateOperatorName(oldOpName, newOpName));
                        break;
                    case 8:
                        Scanner adminScanner5 = new Scanner(System.in);
                        System.out.print("Enter Old Password: ");
                        String oldOpPass = adminScanner5.nextLine();
                        System.out.print("Enter new Password: ");
                        String newOpPass = adminScanner5.nextLine();
                        System.out.println(admin.updateOperatorPass(oldOpPass, newOpPass));
                        break;
                    case 9:
                        Scanner adminScanner6 = new Scanner(System.in);
                        System.out.print("Enter operatorUsername: ");
                        String operatorUsername= adminScanner6.nextLine();
                        System.out.println(admin.updateShift(operatorUsername));
                        break;
                    case 10:
                        Scanner adminScanner7 = new Scanner(System.in);
                        System.out.print("Enter Customer ID: ");
                        String CustomerID = adminScanner7.nextLine();
                        System.out.println(admin.deleteCustomer(CustomerID));
                        break;
                    case 11:
                        Scanner adminScanner8 = new Scanner(System.in);
                        System.out.print("Enter Operator name: ");
                        String OpID = adminScanner8.nextLine();
                        System.out.println(admin.deleteOperator(OpID));
                        break;
                    case 12:
                        System.out.println(admin.parkedCar());
                        break;
                    case 13:
                        System.out.println(admin.carReports());
                        break;
                    case 14:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                        break;
                }
            }
        }catch (Exception e) {
            System.out.println("An unexpected error occurred");
            adminActions();
        }


    }
}

