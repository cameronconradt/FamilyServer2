package model;

import java.lang.reflect.Field;

import generator.generator;

/**
 * Created by camer on 2/16/2018.
 */

public class event extends Model  {
    private String date;
    private String type;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String id;
    private String person_id;
    private String user_id;



    public String getData(){
        return new String("date = " + date + ", type = " + type + ", latitude = " + Double.toString(latitude) + ", longitude = " + Double.toString(longitude) + ", country = " + country + ", city = " + city + ", person_id = " + person_id);
    }
    /**
     *
     * @param data string array [id(do not set),user_id,date,type,country,city,latitude,longitude,person_id]
     */
    public event(Object[] data){
        if(data.length == 8){
            user_id = (String) data[0];
            date = (String) data[1];
            type = (String) data[2];
            country = (String) data[3];
            city = (String) data[4];
            latitude = (Double) data[5];
            longitude = (Double) data[6];
            person_id = (String) data[7];
        }
        else if(data.length == 9){
            id = (String) data[0];
            user_id = (String) data[1];
            date = (String) data[2];
            type = (String) data[3];
            country = (String) data[4];
            city = (String) data[5];
            latitude = (Double) data[6];
            longitude = (Double) data[7];
            person_id = (String) data[8];
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
                System.err.println("event.isvalid should never happen");
            }
            catch (IllegalAccessException e){
                System.err.println("event.isvalid should never happen");
            }
        }
        return true;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCity(){ return city;}

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUser_id() {return user_id;}
    public void setUser_id(String id){user_id = id;}
    public String getPerson_id() {
        return person_id;
    }
    public void setPerson_id(String id){person_id = id;}


}
