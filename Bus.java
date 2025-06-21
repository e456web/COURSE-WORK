
package coursework.Elite;

class Bus extends Vehicle{
    public Bus(String regNumber, String vehicleType,  String shiftTime){
        super(regNumber, vehicleType,shiftTime);
    }

    @Override
    public void makeService(){
        System.out.println("The bus  with regNumber | "+ getRegNumber() + "  is in service");
    }

    @Override
    public void trackVehicle(){
        System.out.println("The bus  with regNumber | " +getRegNumber()+ "  Cannot be tracked!!");
    }

    @Override
    public void scheduleVehicle(){
        System.out.println("The vehicle "+  getVehicleType()+" will be scheduled at | "  +getShiftTime());
    }


}
