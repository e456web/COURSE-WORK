package coursework.Elite;

class TransportOfficer extends User {

    public TransportOfficer(String userID, String userName){
        super(userID, userName);
    }
    @Override
    public void requestTransport(){
        System.out.println( "TransportOfficer   "+  getUserID()+ " doesn't requested transport" );

    }

}