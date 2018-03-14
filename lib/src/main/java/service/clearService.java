package service;

import dao.Dao;
import model.Model;

/**
 * Created by camer on 2/16/2018.
 */

public class clearService extends Service  {
    /**
     * Clears the database of all entries
     *
     */
    public static Model clear(){
        Dao.createTables();
        return new Model("Database cleared");
    }
}
