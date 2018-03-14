package dao;

import model.event;
import model.events;

import java.awt.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by camer on 2/16/2018.
 */

public class eventDao extends Dao {


    public static String addEvent(event event)throws SQLException{
        Connection connect = Dao.connect();
        if(connect == null){
            throw new NullPointerException();
        }
        if(!event.isValid())
            return "Event invalid";

        PreparedStatement state = connect.prepareStatement("insert into events values(?,?,?,?,?,?,?,?,?");
        //id(do not set),user_id,date,type,country,city,latitude,longitude,person_id

        state.setString(2,event.getUser_id());
        state.setString(3,event.getDate());
        state.setString(4,event.getType());
        state.setString(5,event.getCountry());
        state.setString(6,event.getCity());
        state.setString(7,Double.toString(event.getLatitude()));
        state.setString(8,Double.toString(event.getLongitude()));
        state.setString(9,event.getPerson_id());
        state.addBatch();

        connect.setAutoCommit(false);
        state.executeBatch();
        connect.setAutoCommit(true);

        try{
            connect.close();
        }catch(SQLException e){
            System.err.println("Connection not closed");
            e.printStackTrace();
        }

        return "Event added to table";
    }

    public String addEvents(event[] events) throws SQLException{
        for(event event : events){
            try{
                addEvent(event);
            }
            catch (SQLException e){
                System.err.println("Event not added " + event.getData());
            }
        }
        return "Events added";
    }

    /**
     *
     * @param id unique id to remove
     */
    public void removeEvent(String id)throws SQLException{

        Connection connect = Dao.connect();
        if(connect == null){
            throw new NullPointerException();
        }

        Statement state = connect.createStatement();
        state.executeUpdate("delete from events where id=\"" + id + "\";");

        try{
            connect.close();
        }
        catch (SQLException e){
            System.err.println("Couldn't close connection");
            e.printStackTrace();
        }

    }

    /**
     *
     * @param id event ID
     * @return return event associated with the id
     */
    public event getEvent(String id) throws SQLException {

        Connection connect = Dao.connect();
        if (connect == null) {
            throw new NullPointerException();
        }

        Statement state;
        ResultSet result = null;
        try {
            state = connect.createStatement();
            result = state.executeQuery("select * from events where id=\"" + id + "\";");
        } catch (SQLException e) {
            System.err.println("Event not retrieved");
            e.printStackTrace();
        }
        ArrayList<String> data = new ArrayList<>();
        //id(do not set),user_id,date,type,country,city,latitude,longitude,person_id
        for (int i = 1; i < 10; i++) {
            data.add(result.getString(i));
        }

        try{
            connect.close();
        }
        catch(SQLException e){
            System.err.println("Connection not closed");
            e.printStackTrace();
        }

        return new event(data.toArray());

    }

    /**
     *
     * @param user_id Root Person ID
     * @return all events associated with root id
     */
    public events getAllEvents(String user_id) throws SQLException{

        Connection connect = Dao.connect();
        if (connect == null) {
            throw new NullPointerException();
        }

        Statement state;
        ResultSet result = null;
        try {
            state = connect.createStatement();
            result = state.executeQuery("select * from events where user_id=\"" + user_id + "\";");
        } catch (SQLException e) {
            System.err.println("Event not retrieved");
            e.printStackTrace();
        }
        events toReturn = new events();
        while(result.next()) {
            ArrayList<String> data = new ArrayList<>();
            //id(do not set),user_id,date,type,country,city,latitude,longitude,person_id
            for (int i = 1; i < 10; i++) {
                data.add(result.getString(i));
            }
            toReturn.addEvent(new event(data.toArray()));
        }
        try{
            connect.close();
        }
        catch(SQLException e){
            System.err.println("Connection not closed");
            e.printStackTrace();
        }
        return toReturn;
       }



}
