package model;

/**
 * Created by camer on 3/10/2018.
 */

public class loginResponse extends Model {

    private String auth_token;
    private String username;
    private String personID;

    public loginResponse(String auth_token, String username, String personID) {
        this.auth_token = auth_token;
        this.username = username;
        this.personID = personID;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String toString() {
        StringBuffer output = new StringBuffer();
        output.append("{\n");
        if(this.getMessage()!= null){
            output.append("message:\"" + getMessage() + "\"\n");
        }
        else {
            output.append("authToken:\"" + auth_token + "\"\n");
            output.append("username:\"" + username + "\"\n");
            output.append("personId:\"" + personID + "\"\n");
        }
        output.append("}");
        return output.toString();
    }

    public boolean equals(Object o){
        if(o.getClass() != this.getClass()) {
            return false;
        }
        loginResponse temp = (loginResponse) o;
        if(this.message != null || temp.message != null) {
            if(this.message != null && temp.message != null) {
                if( !this.message.equals(temp.message) ) {
                    return false;
                }else {
                    return true;
                }
            }else {
                return false;
            }
        }
        if(this.auth_token.equals(temp.auth_token) && this.personID.equals(temp.personID)
                && this.username.equals(temp.username))
        {
            return true;
        }
        return false;
    }
}
