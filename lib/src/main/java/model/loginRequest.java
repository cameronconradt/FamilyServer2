package model;

/**
 * Created by camer on 3/10/2018.
 */

public class loginRequest extends Model {

    public String userName;
    public String password;

    public loginRequest(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public String toString(){
        return ("username: ") + userName + ", password: " + password;
    }

    public boolean isValid(){
        if(userName == null || password == null || userName.equals("") || password.equals(""))
            return false;
        return true;
    }
}
