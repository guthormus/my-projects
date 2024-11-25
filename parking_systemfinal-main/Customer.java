class CustomerMethods {
    public String CallingOperator(String platenumber){
        return "ok";
    }

}
public class Customer extends CustomerMethods{
    @Override
    public String CallingOperator(String platenumber){
        Operator operator=new Operator();
        operator.generateEntryID(platenumber);
        operator.assignedSlot(platenumber);
        return operator.entryTicket(platenumber);
    }
}