package model;

/**
 * Created by camer on 3/10/2018.
 */

public class loadRequest extends Model {
    public User[] users;
    public Person[] people;
    public event[] events;


    public loadRequest(User[] users, Person[] people, event[] events) {
        this.users = users;
        this.people = people;
        this.events = events;
    }
}
