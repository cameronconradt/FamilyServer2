package service;

import java.sql.SQLException;

import dao.auth_tokenDao;
import dao.userDao;
import model.Model;
import model.User;
import model.auth_token;
import model.loginRequest;
import model.loginResponse;

/**
 * Created by camer on 2/16/2018.
 */

public class loginService extends Service {


    public static Model serve(loginRequest request){
        if(!request.isValid()){
            return new Model("Request invalid, username and password required");
        }
        userDao userDao = new userDao();
        User user = null;

        auth_tokenDao authDao = new auth_tokenDao();
        auth_token token = null;

        try{
            user = userDao.getUser(request.userName);
            token = authDao.getWithName(request.userName);
        }
        catch(SQLException e){
            return new Model("Username does not exist");
        }
        if(!user.getPassword().equals(request.password)){
            return new Model("Passwords do not match");
        }
        if(token != null){
            return new loginResponse(token.getId(),user.getUsername(),user.getPersonid());
        }
        return null;
    }

}
