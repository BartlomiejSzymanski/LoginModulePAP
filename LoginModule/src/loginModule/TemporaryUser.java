package loginModule;

public class TemporaryUser {
    String name;
    String surname;
    String password;
    String username; 
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    TemporaryUser(String name, String surname, String username, String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
}
