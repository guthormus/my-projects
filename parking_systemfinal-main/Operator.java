import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.Arrays;


 class operatorMethods{
    public String generateEntryID(String plateNumber){
        return "don't use this class";
    }
     public int assignedSlot(String carPlateNumber){

         return 0;
     }
     public  int freeSpot(String id){
         return 0;
     }
     public double calculateParkingDurationHours(String id){
        return 0.0;
     }
     public double calculateParkingFee(String id){
        return 0.0;
     }
     public String entryTicket(String plateNumber){
        return "don't use this class";
     }
     public  String printExitTicket(String providedEntryID){
        return "don't use this class";
     }
     public  List<Integer> freeSpots(){
        return Arrays.asList(0,0);
     }
}

public class Operator extends operatorMethods {

    private String entryID;
    private LocalDateTime entryDateTime;

    private  String carPlateNumber;
    private double parkingFeePerHour = 2.5;

    @Override
    public String generateEntryID(String plateNumber) {

            entryID = String.valueOf((int) (Math.random() * 100001));
            this.carPlateNumber = plateNumber;


            return database_handle.insertCustomerData(entryID, carPlateNumber);

    }
    @Override
    public int assignedSlot(String carPlateNumber){

        if(database_handle.cancelSlot(entryID)==1){
            return 0;
        }
        this.carPlateNumber = carPlateNumber;
        if(database_handle.fullSpots()==1){
            return 0;
        }
      int slot =  database_handle.assignSlot(entryID);
      database_handle.assignSlotToCustomer(slot,entryID);

      // file.writeFile("slot",slot);
        return slot;
    }

    @Override
    public  int freeSpot(String id){
        database_handle.freeSpot(id);
        return 1;
    }



    /*public String printEntryTicket(String platenumber) {
        this.carPlateNumber= platenumber;
        System.out.println("==== Entry Ticket ====");
        System.out.println("Entry ID: " + entryID);
        System.out.println("Vehicle Number: " + carPlateNumber);
        System.out.println("Entry Time: " + entryDateTime);
        System.out.println("Slot Number: "+ availableSlot);
        System.out.println("=====================");
    }*/
    @Override
    public double calculateParkingDurationHours(String id) {
        this.entryID=id;
        entryDateTime = database_handle.getDate(id).toLocalDateTime();
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(entryDateTime, exitTime);
        database_handle.setExitDate(entryID, Timestamp.valueOf(exitTime));
        return duration.getSeconds();
    }
    @Override
    public double calculateParkingFee(String id) {
        this.entryID=id;
        double hours = calculateParkingDurationHours(entryID);
        double fees = hours* parkingFeePerHour;
        database_handle.setFees(entryID,fees);
        return fees;

    }


/*
    public void printExitTicket(String providedEntryID) {
        
        if (providedEntryID==entryID) {
            calculateParkingDurationHours();
            double parkingFee = calculateParkingFee();

            // Simulate printing an exit ticket with duration and fee details
            System.out.println("==== Exit Ticket ====");
            System.out.println("Entry ID: " + entryID);
            System.out.println("Vehicle Number: " + carPlateNumber);
            System.out.println("Parked Time: " + calculateParkingDurationHours() + " hours");
            System.out.println("Parking Fee: $" + parkingFee);
            System.out.println("=====================");
        } else {
            System.out.println("Invalid Entry ID. Please provide the correct entry ID.");
        }
       // database_handle.printEntryTicket(providedEntryID);
    }
*/
    @Override
     public String entryTicket(String plateNumber){
         /*
         int counter = 0;
         if(database_handle.fullSpots()==1){
             counter++;
             if(counter>1){
                 return "no freeSpots";
             }
         }

          */
        this.carPlateNumber = plateNumber;

        return database_handle.setEntryTicket(entryID);
    }
    @Override
   public  String printExitTicket(String providedEntryID){
        return database_handle.getCustomerData(providedEntryID);
        /*if (providedEntryID==entryID) {
            calculateParkingDurationHours(providedEntryID);
            double parkingFee = (double)calculateParkingFee(providedEntryID);
            String output = "{\n\"Entry ID\": \""+entryID+"\",\n\"Car Plate Number\": \""+carPlateNumber+"\",\n\"Duration Hours\": \"" + calculateParkingDurationHours(entryID)+ "\",\n\"Parking Fee\": \"$" + parkingFee + "\"}";
            //database_handle.updateStatus(entryID,"Exited");
            //database_handle.setPaymentStatus(entryID,"Unpaid");
            return output;
            } else {
                return "Invalid Entry ID.";
                }
                //return database_handle.printExitTicket(providedEntryID);*/
                }


    public  List<Integer> freeSpots(){
        return database_handle.getFreeSpots();
        /*
        database_handle database= new database_handle();
        void availableSlot = String.valueOf(database.retrieveData("spots"));
        System.out.println("Availble Spots: " +availableSlot);

         */
    }




}
