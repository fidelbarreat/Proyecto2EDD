package Primitivas;

/**
 * @author Yasmin Hammoud
 */
public class Citizen {
    
    private String firstName;
    private String lastName;
    private int identity;

    public Citizen(String firstName, String lastName, int identity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identity = identity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
    
}
