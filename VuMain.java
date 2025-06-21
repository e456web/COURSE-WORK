package coursework.Elite;

public class VuMain {
    public static void main(String [] args){
        //users
        Student  student =new Student( "VU01", "mugabi edward");
        Lecturer lecturer  =new Lecturer("VUL003", "eng. Alex", "ComputerScience");
        Driver driver=new Driver("VUD1", "Pius",  "L366u@pius");
        TransportOfficer transportofficer =new TransportOfficer("T023",  "Officer Kenneth" );

        student.requestTransport();
        lecturer.requestTransport();
        driver.requestTransport();
        transportofficer.requestTransport();

        //vehicle

        Bus coaster =new Bus("BTS008Y", "COASTER" ,"2:40 pm");
        Van myvan =new Van("UVX770", "WagonR" ,"6:00 am");
        //the overloaded methods:
        coaster.makeService();
        myvan.trackVehicle();
        coaster.scheduleVehicle();
        coaster.trackVehicle();
        coaster.makeService();
        myvan.scheduleVehicle();
    }
}
