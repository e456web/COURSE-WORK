
package coursework.Elite;

public abstract class Vehicle {
    final private String regNumber;
    final private String vehicleType;
    final private String shiftTime;


    public Vehicle(String regNumber, String vehicleType,  String shiftTime){
        this.regNumber=regNumber;
        this.vehicleType =vehicleType;
        this.shiftTime=shiftTime;
    }

    // accessing the private data

    public String getRegNumber(){
        return regNumber;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public String getShiftTime(){
        return shiftTime;
    }

    // Overriding interfaces


    public abstract void makeService();

    public abstract void trackVehicle();
    public abstract void scheduleVehicle();
}