package service;

import java.sql.SQLException;

import dao.Dao;
import dao.auth_tokenDao;
import dao.eventDao;
import dao.personDao;
import dao.userDao;
import model.Model;
import model.Person;
import model.User;
import model.event;
import model.loadRequest;

/**
 * Created by camer on 2/16/2018.
 */

public class loadService extends Service {


    public static Model serve(loadRequest request){
        userDao userDao = new userDao();
        personDao personDao = new personDao();
        eventDao eventDao = new eventDao();
        auth_tokenDao auth_tokenDao = new auth_tokenDao();
        if(request == null){
            return new Model("Request body was null");
        }
        User[] users = request.users;
        Person[] people = request.people;
        event[] events = request.events;

        if(users == null && people == null && events == null)
            return new Model("No data to input");
        Dao.createTables();
        try{
            userDao.addUsers(users);
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Could not add the users: " + e.getMessage());
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        try{
            personDao.addPeople(people);
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Could not add the people: " + e.getMessage());
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        try{
            eventDao.addEvents(events);
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Could not add the users: " + e.getMessage());
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        try{
            auth_tokenDao.addTokens(users);
        } catch(SQLException e){
            e.printStackTrace();
            return new Model("Could not add Tokens: " + e.getMessage());
        }
        return new Model("Successfully added to the database:\n" + users.length + " users\n" + people.length +
            " people\n" + events.length + " events\n");
    }
}
