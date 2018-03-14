package service;


import java.sql.SQLException;

import dao.auth_tokenDao;
import dao.eventDao;
import model.Model;
import model.auth_token;
import model.event;
import model.events;

/**
 * Created by camer on 2/16/2018.
 */

public class eventService extends Service {

    public static Model serve(String auth_token, String id){
        auth_tokenDao authDao = new auth_tokenDao();
        auth_token token =null;
        try{
            token = authDao.getWithTokenId(auth_token);
        }
        catch(SQLException e){
            return new Model("Incorrect auth_code");
        }
        if(token == null)
            return new Model("Incorrect auth_code");

        eventDao eventDao = new eventDao();

        if(id != null){
            event event = null;
            try{
                event = eventDao.getEvent(id);
            }
            catch(SQLException e){
                return new Model("Could not retrieve event/eventID does not exist");
            }
            if(!event.getUser_id().equals(token.getUserId())){
                return new Model("auth_code does not match owner of event");
            }
            return event;
        }
        else{
            events events = null;
            try{
                events = eventDao.getAllEvents(token.getId());
            }
            catch(SQLException e){
                e.printStackTrace();
                return new Model("Could not retrieve events");
            }
            return events;
        }
    }
}
