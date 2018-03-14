package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.auth_tokenDao;
import dao.personDao;
import model.Model;
import model.Person;
import model.auth_token;
import model.people;

/**
 * Created by camer on 2/16/2018.
 */

public class personService extends Service {

    public static Model serve(String authToken, String person_id){
        auth_tokenDao aDao = new auth_tokenDao();
        auth_token token = null;
        try{
            token = aDao.getWithTokenId(authToken);
        }
        catch(SQLException e){
            return new Model("Invalid auth_token");
        }
        if(token == null)
            return new Model("Invalid auth_token");

        personDao pDao = new personDao();

        if(person_id != null){
            Person person = null;
            try{
                person = pDao.getPerson(person_id);
            }
            catch(SQLException e){
                return new Model("Person not retrievable");
            }
            if(!person.getUser_id().equals(token.getUserId()))
                return new Model("Auth code doesn't match user");
            return person;
        }
        else{
            people people = null;
            try{
                people = pDao.getAllPersons(token.getUserId());
            }
            catch(SQLException e){
                return new Model("Could not retrieve people");
            }
            return people;
        }
    }
}
