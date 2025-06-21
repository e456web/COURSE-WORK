package coursework.Elite;

class Lecturer extends User {
    final private String Department;
    public Lecturer(String userID, String userName, String Department ){
        super(userID,userName);
        this.Department=Department;

    }
    @Override
    public void requestTransport(){
        System.out.println("Lecturer  " +getUserID() + "  Under  "  +Department+  "   has requested transport ");
    }

}
