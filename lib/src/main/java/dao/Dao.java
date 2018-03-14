package dao;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Model;
import model.Person;
import model.User;
import model.auth_token;
import model.event;

/**
 * Created by camer on 2/16/2018.
 */

public class Dao {
    public static Connection connect(){
        Connection connect = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connect;
    }
    /**
     * clears the Database
     */
        public void clear(){createTables();}

        public static void createTables(){
            Connection connection = connect();
            Statement state = null;
            try{
                state = connection.createStatement();
            }
            catch(SQLException e){
                System.out.println("Could not get statement" + e.getMessage());
                return;
            }

            //create users table
            try{
                state.executeUpdate("drop table if exists users");
                state.executeUpdate("create table users" +
                        "(" +
                        "username varchar(255) not null," +
                        "password varchar(255) not null," +
                        "email varchar(255) not null," +
                        "firstName varchar(255) not null," +
                        "lastName varchar(255) not null," +
                        "gender char(1) not null," +
                        "personid integer," +
                        "id integer not null primary key autoincrement" +
                        ");");
            }
            catch(SQLException e){
                System.out.println("Could not create table users" + e.getMessage());
                return;
            }
            //create people table
            try{
                state.executeUpdate("drop table if exists people;");
                state.executeUpdate("create table people\n" +
                        "(\n" +

                        "\tid integer not null primary key autoincrement,\n" +
                        "\tdescendant_id integer not null,\n" +
                        "\tuser_id integer not null,\n" +
                        "\tfirstName varchar(255) not null,\n" +
                        "\tlastName varchar(255) not null,\n" +
                        "\tgender char(1) not null,\n" +
                        "\tfather varchar(255),\n" +
                        "\tmother varchar(255),\n" +
                        "\tspouse varchar(255),\t\n" +
                        "\tFOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,\n" +
                        "\tFOREIGN KEY (descendant_id) REFERENCES people(id) ON DELETE CASCADE\n" +
                        ");");
            }
            catch(SQLException e){
                System.out.println("Could not create table people" + e.getMessage());
                return;
            }
            //create auth_tokens table
            try{
                state.executeUpdate("drop table if exists auth_tokens;");
                state.executeUpdate("create table auth_tokens\n" +
                        "(\n" +
                        "\tuser_id integer not null,\n" +
                        "\tid integer not null primary key autoincrement, \n" +
                        "\tFOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE\n" +
                        ");");
            }
            catch(SQLException e){
                System.out.println("Could not create table auth_tokens" + e.getMessage());
                return;
            }
            //create Events table
            try{
                state.executeUpdate("drop table if exists events");
                state.executeUpdate("create table events\n" +
                        "(\n" +
                        "\tuser_id integer not null,\n" +
                        "\tdate varchar(255) not null,\n" +
                        "\ttype varchar(255) not null,\n" +
                        "\tcountry varchar(255) not null,\n" +
                        "\tcity varchar(255) not null,\n" +
                        "\tid integer not null primary key autoincrement,\n" +
                        "\tlatitude double not null,\n" +
                        "\tlongitude double not null,\n" +
                        "person_id integer not null," +
                        "\tFOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,\n" +
                        "FOREIGN KEY (person_id) REFERENCES people(id) ON DELETE CASCADE" +
                        ");");
            }
            catch (SQLException e){
                System.out.println("Could not create table events" + e.getMessage());
            }


        }




}
