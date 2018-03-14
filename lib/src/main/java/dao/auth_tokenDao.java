package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import model.auth_token;

/**
 * Created by camer on 2/16/2018.
 */

public class auth_tokenDao extends Dao {
    /**
     *
     * @param id ID of auth_token to remove
     */
    public void removeAuth_Token(String id) throws SQLException{
        Connection connect = Dao.connect();
        if(connect == null){
            throw new NullPointerException();
        }

        Statement state = connect.createStatement();
        state.executeUpdate("delete from auth_tokens where id=\"" + id + "\";");

        try{
            connect.close();
        }
        catch(SQLException e){
            System.err.println("Couldn't close connection");
            e.printStackTrace();
        }

    }

    public String addTokens(User[] users)throws SQLException{
        Connection connect = connect();
        if(connect == null) {
            throw new NullPointerException();
        }
        PreparedStatement state = connect.prepareStatement("insert into auth_tokens values(?,?);");

        for(User user : users){
            if(user.isValid()) {
                state.setString(2, user.getId());
                state.addBatch();
            }
        }

        return "tokens added to users";
    }

    /**
     * Creates a new unique auth token
     *@param user_id ID of user to attach auth_token to
     */
    public void createAuth_Token(String user_id) throws SQLException{
        Connection connection = connect();
        if(connection == null) {
            throw new NullPointerException();
        }

        PreparedStatement prep = connection.prepareStatement("insert into auth_tokens values(?, ?);");

        prep.setString(1, user_id);
        prep.addBatch();

        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
        try {
        connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't close the connection!");
            e.printStackTrace();
        }


    }



    public auth_token getWithTokenId(String id) throws SQLException{
        Connection connection = connect();
        if(connection == null) {
            throw new NullPointerException();
        }
        Statement statement;
        ResultSet rs = null;
        auth_token token = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from auth_tokens where id=\""+ id + "\";");

        } catch (SQLException e) {
            System.err.println("The attempt to get the user info failed!");
            e.printStackTrace();
        }

        token = new auth_token(rs.getString(1),rs.getString(2));

        try{
            connection.close();
        }
        catch(SQLException e){
            System.err.print("Couldn't close connection");
            e.printStackTrace();
        }
        return token;
    }

    public auth_token getWithUserId(String id) throws SQLException{
        Connection connection = connect();
        if(connection == null) {
            throw new NullPointerException();
        }
        Statement statement;
        ResultSet rs = null;
        auth_token token = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from auth_tokens where user_id=\""+ id + "\";");

        } catch (SQLException e) {
            System.err.println("The attempt to get the user info failed!");
            e.printStackTrace();
        }

        token = new auth_token(rs.getString(1),rs.getString(2));

        try{
            connection.close();
        }
        catch(SQLException e){
            System.err.print("Couldn't close connection");
            e.printStackTrace();
        }
        return token;
    }

    public auth_token getWithName(String username)throws SQLException{
        userDao userDao = new userDao();
        Connection connection = connect();
        if(connection == null) {
            throw new NullPointerException();
        }
        Statement statement;
        ResultSet rs = null;
        auth_token token = null;
        User user = null;

        try {
            user = userDao.getUser(username);
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from auth_tokens where user_id=\""+ user.getId() + "\";");

        } catch (SQLException e) {
            System.err.println("The attempt to get the user info failed!");
            e.printStackTrace();
        }

        token = new auth_token(rs.getString(1),rs.getString(2));

        try{
            connection.close();
        }
        catch(SQLException e){
            System.err.print("Couldn't close connection");
            e.printStackTrace();
        }
        return token;
    }
}
