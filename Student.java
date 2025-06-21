package coursework.Elite;

class Student extends User {
    public Student(String userID, String userName){
        super(userID, userName);
    }
    @Override
    public void requestTransport(){
        System.out.println( "Student " + getUserID() + " has requested transport to a friend's birthday party " );
    }
}
