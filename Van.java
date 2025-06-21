package coursework.Elite;

class Van extends Vehicle{

    public Van(String regNumber, String vehicleType,  String shiftTime){
        super(regNumber, vehicleType,shiftTime);
    }

    @Override
    public void makeService(){
        System.out.println("Van  with regNumber | "+ getRegNumber()+ "  is out of service");
    }

    @Override
    public void trackVehicle(){
        System.out.println("Van  "+ getVehicleType() + " with  regNumber | "+getRegNumber()+"  Has been tracked");
    }

    @Override
    public void scheduleVehicle(){
        System.out.println("Van  with regNumber | "+ getRegNumber()+ " Cannot be Scheduled!");
    }
}