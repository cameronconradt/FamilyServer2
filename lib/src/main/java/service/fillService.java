package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.eventDao;
import dao.personDao;
import dao.userDao;
import model.Model;
import model.Person;
import model.User;
import model.event;
import generator.generator;

/**
 * Created by camer on 2/16/2018.
 */

public class fillService extends Service {


    public static Model serve(String username, String gens){

        int generations = 0;
        try{
            generations = Integer.parseInt(gens);
            if(generations < 0)
                return new Model("Invalid generation value");
        } catch(NumberFormatException e) {
            return new Model("Invalid generation value");
        }

        userDao userDao = new userDao();
        personDao personDao = new personDao();
        eventDao eventDao = new eventDao();
        User user = null;
        Person userP = null;
        user = userDao.getUser(username);
        if(user == null)
            return new Model("Couldn't find the username " + username);
        try{
            personDao.removePerson(user.getPersonid());
        }
        catch(SQLException e){
            e.printStackTrace();
            return new Model("Couldn't delete the data for " + username);
        }

        return generateFamily(user,generations);

    }
    public static Model generateFamily(User user, int generations){
        generator generator = new generator();
        personDao personDao = new personDao();
        userDao userDao = new userDao();
        try {
            personDao.addUser(user);
            user = userDao.getUser(user.getUsername());
        }
        catch(SQLException e){
            System.err.println("User not added");
            e.printStackTrace();
        }

        generator.generateAll(user,generations);

        /*ArrayList<Person> people =  generatePeopleRecur(user,generations);
        ArrayList<event> events = generateEventsRecur(user, generations);

        int eventpos = 0;
        for( Person person : people){
            while(events.get(eventpos).getPerson_id() != null)
                eventpos++;
            events.get(eventpos).setPerson_id(person.getId());
            eventpos++;
            events.get(eventpos).setPerson_id(person.getId());
        }
        people = generateParents(people);

        for(Person person: people){
            try {
                personDao.addPerson(person);
            }
            catch (SQLException e){
                System.err.println("Person could not be added");
                e.printStackTrace();
            }
        }
        for(event event : events){
            try {
                eventDao.addEvent(event);
            }
            catch(SQLException e){
                System.err.println("Event not added");
                e.printStackTrace();
            }
        }*/
        return new Model("You filled " + user.getUsername() + " with " + generations + " generations");
    }/*
    private static ArrayList<Person> generatePeopleRecur(User user,int generations){
        if(generations == 0){
            return new ArrayList<Person>();
        }
        ArrayList<Person> toreturn = new ArrayList<>();
        toreturn.add(new Person(user));
        toreturn.add(new Person(user));
        toreturn.addAll(generatePeopleRecur(user,generations-1));
        toreturn.addAll(generatePeopleRecur(user,generations-1));
        return toreturn;
    }
    private static ArrayList<event> generateEventsRecur(User user, int generations){
        if(generations == 0){
            return new ArrayList<event>();
        }
        ArrayList<event> toreturn = new ArrayList<>();
        toreturn.add(new event(user));
        toreturn.add(new event(user));
        toreturn.add(new event(user));
        toreturn.add(new event(user));
        toreturn.addAll(generateEventsRecur(user,generations-1));
        toreturn.addAll(generateEventsRecur(user,generations-1));
        return toreturn;
    }
    private static ArrayList<Person> generateParents(ArrayList<Person> parentList){
        if(parentList.size() < 3)
            return new ArrayList<>();
        ArrayList<Person> toreturn = new ArrayList<>();
        parentList.get(0).setFather(parentList.get(1).getFirstName());
        parentList.get(0).setMother(parentList.get(2).getFirstName());
        toreturn.add(parentList.get(0));
        parentList.remove(0);
        toreturn.addAll(generateParents(parentList));
        return toreturn;
    }*/
}
