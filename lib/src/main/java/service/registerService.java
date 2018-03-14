package service;

import java.sql.SQLException;

import dao.auth_tokenDao;
import dao.personDao;
import dao.userDao;
import model.Model;
import model.Person;
import model.User;
import model.auth_token;
import model.loginResponse;

/**
 * Created by camer on 2/16/2018.
 */

public class registerService extends Service {


    public static Model serve(User user){
        auth_tokenDao auth_tokenDao = new auth_tokenDao();
        if(user == null)
            return new Model("User blank");
        User find = null;
        userDao uDao = new userDao();
        personDao personDao = new personDao();
        find = uDao.getUser(user.getUsername());
        if(find != null){
            return new Model("Username already exists");
        }
        try{
            uDao.addUser(user);
            user = uDao.getUser(user.getUsername());
            Model temp = personDao.addUser(user);
            Person person = null;
            if(temp.getClass().equals(Person.class)){
                person = (Person) temp;
            }
            else{
                return temp;
            }
            user.setPersonid(person.getId());
            temp = uDao.updateUser(user);
            if(temp.getClass().equals(User.class))
                user = (User) temp;
            else{
                return temp;
            }
            auth_tokenDao.createAuth_Token(user.getId());
        }
        catch(SQLException e){
            System.err.println(" this-> " +e.getMessage());
            return new Model("Could not add the user");
        }

        fillService.generateFamily(user,4);
        auth_token token = null;
        auth_tokenDao aDao = new auth_tokenDao();
        try{
            find = uDao.getUser(user.getUsername());
            token = aDao.getWithName(user.getUsername());
        }
        catch(SQLException e){
            return new Model("failed retrieving user");
        }
        return new loginResponse(token.getId(),user.getUsername(),user.getPersonid());
    }
}
