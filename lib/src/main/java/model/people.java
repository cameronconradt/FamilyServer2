package model;

import java.util.ArrayList;

/**
 * Created by camer on 3/10/2018.
 */

public class people extends Model {
    private ArrayList<Person> people = new ArrayList<>();

    public people(){}

    public Person[] getPeople() {
        return (Person[]) people.toArray();
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person){
        people.add(person);

    }
}
