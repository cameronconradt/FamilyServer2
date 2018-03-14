package model;

import java.lang.reflect.Field;

/**
 * Created by camer on 2/16/2018.
 */

public class Person extends Model  {
    private String id;
    private String user_id;
    private String descendant_id;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;

    /**
     *
     * @param data [id,descendant_id,user_id,firstName,lastName,gender,father,mother,spouse]
     */
    public Person(Object[] data){
        if(data.length == 5){
            descendant_id = (String) data[0];
            user_id = (String) data[1];
            firstName = (String) data[2];
            lastName = (String) data[3];
            gender = (String) data[4];

        }
        else if(data.length == 6) {
            id= (String) data[0];
            descendant_id = (String) data[1];
            user_id = (String) data[2];
            firstName = (String) data[3];
            lastName = (String) data[4];
            gender = (String) data[5];
        }
        else if(data.length == 8){
            descendant_id = (String) data[0];
            user_id = (String) data[1];
            firstName = (String) data[2];
            lastName = (String) data[3];
            gender = (String) data[4];
            father = (String) data[5];
            mother = (String) data[6];
            spouse = (String) data[7];
        }
        else if(data.length == 9){
            id= (String) data[0];
            descendant_id = (String) data[1];
            user_id = (String) data[2];
            firstName = (String) data[3];
            lastName = (String) data[4];
            gender = (String) data[5];
            father = (String) data[6];
            mother = (String) data[7];
            spouse = (String) data[8];
        }
        else throw new IllegalArgumentException();
    }

    public Person(User descendant, String gender){}

    public Person(User user){
        user_id=user.getId();
        firstName= user.getFirstName();
        lastName = user.getLastName();
        gender = user.getGender();
    }

    public String getData(){
        return new String("user_id = " + user_id + ", firstName = " + firstName + ", lastName = " + lastName + ", gender = " + gender + ", father = " + father + ", mother = " + mother + ", spouse = " + spouse);
    }

    public boolean isValid(){
        for (Field f: getClass().getDeclaredFields())
        {
            try {
                if (!f.equals( getClass().getDeclaredField("id")) && !f.equals( getClass().getDeclaredField("father"))
                        && !f.equals( getClass().getDeclaredField("mother"))
                        && !f.equals( getClass().getDeclaredField("spouse"))) {
                    if(f== null || f.equals(""))
                        return false;
                }
            }
            catch(NoSuchFieldException e){
                System.err.println("user.isvalid should never happen");
            }
            /*catch (IllegalAccessException e){
                System.err.println("user.isvalid should never happen");
            }*/
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescendant() {
        return descendant_id;
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

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setId(String id) {  this.id = id;  }

    public String getDescendant_id() {
        return descendant_id;
    }

    public void setDescendant_id(String descendant_id) {
        this.descendant_id = descendant_id;
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

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
}
