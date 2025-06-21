package coursework.Elite;

// the abstract super class to be extended by other subclasses (Student, Lecturer, TransportOfficer)

abstract class User {
    final private String userID;
    final private String userName;

    // Creating a Constructor that sets up a new user  with userID and userName.

    public User(String userID, String userName){
        this.userID =userID;
        this.userName =userName;
    }

    /*application of encapsulation.
    Accessing the private data through getter methods
    */


    public String getUserID(){
        return userID;

    }

    public String getUserName(){
        return userName;

    }

/*The method to be implemented by all the subclasses
subclasses must provide their own version of this method
    through @Overriding*/

    abstract void requestTransport();



}
