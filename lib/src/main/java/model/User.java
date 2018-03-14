package model;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by camer on 2/16/2018.
 */

public class User extends Model  {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personid;
    private String id;

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getData(){
        return new String("username = " + userName + ", password = " + password + ", email = " + email + ", firstName = " + firstName + ", lastName = " + lastName + ", gender = " + gender);
    }

    /**
     *
     * @param data array of Strings to be loaded into a new user.
     *             [username, password, email, firstName, lastName, gender, personid, id]
     */
    public User(String[] data){
        if(data.length == 7){
            userName = data[0];
            password = data[1];
            email = data[2];
            firstName = data[3];
            lastName = data[4];
            gender = data[5];
            personid = data[6];
        }
        else if(data.length == 8){
            userName = data[0];
            password = data[1];
            email = data[2];
            firstName = data[3];
            lastName = data[4];
            gender = data[5];
            personid = data[6];
            id= data[7];
        }
        else
            throw new IllegalArgumentException();
    }

    public boolean isValid(){
        for (Field f: getClass().getDeclaredFields())
        {
            try {
                if (f != getClass().getDeclaredField("id")) {
                    if(f.get(this) == null || f.get(this).equals(""))
                        return false;
                }
            }
            catch(NoSuchFieldException e){
                System.err.println("user.isvalid should never happen");
            }
            catch (IllegalAccessException e){
                System.err.println("user.isvalid should never happen");
            }
        }
        return true;
    }


}
