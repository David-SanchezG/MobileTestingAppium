package automation.helpers.domain;

public class User{
    public final String username;
    public final String password;

    public User(String name, String password){
        this.password = password;
        this.username = name;
    }
}
