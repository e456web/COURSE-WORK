package coursework.Elite;

public class Driver  extends User{
    final  private String licenseNumber;
    public Driver(String userID, String userName, String licenseNumber){
        super(userID, userName);
        this.licenseNumber =licenseNumber;

    }
    public String getLicenseNumber(){
        return licenseNumber;
    }

    @Override
    public void requestTransport(){
        System.out.println("Driver  "+ getUserID()+"  has requested Transport: |  " +"Licence:" +licenseNumber );
    }


}
