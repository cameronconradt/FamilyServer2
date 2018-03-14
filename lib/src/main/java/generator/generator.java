package generator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import dao.eventDao;
import dao.personDao;
import model.Model;
import model.Person;
import model.User;
import model.event;


/**
 * Created by camer on 3/12/2018.
 */

public class generator {

    nameHolder fnames = getNames("json/fnames.json");
    nameHolder mnames = getNames("json/mnames.json");
    nameHolder snames = getNames("json/snames.json");
    locations locations = getLocations("json/locations.json");
    int totgenerations = 0;


    public Model[] generateAll(User descendant, int generations) {
        totgenerations = generations;
        return (Model[]) generatePeople(descendant,1).toArray();
    }

    private String generateEvents(Person person, int generation){
        if(generation >= totgenerations)
            return null;
        ArrayList<event> toreturn = new ArrayList<>();
        Random random = new Random();

        int year = 1950 + (generation * 21) + random.nextInt(5);
        locationHolder location = locations.getLocation();

        Object[] data = {person.getUser_id(),Integer.toString(year),"birth",location.country,location.city,Double.parseDouble(location.latitude),
                            Double.parseDouble(location.longitude),person.getId()};
        toreturn.add(new event(data));

        data = new Object[] {person.getUser_id(),Integer.toString(year + 21),"marriage",location.country,location.city,Double.parseDouble(location.latitude),
                Double.parseDouble(location.longitude),person.getId()};
        toreturn.add(new event(data));

        data = new Object[] {person.getUser_id(),Integer.toString(year + 5 + random.nextInt(74)),"death",location.country,location.city,Double.parseDouble(location.latitude),
                Double.parseDouble(location.longitude),person.getId()};
        toreturn.add(new event(data));

        eventDao eventDao = new eventDao();
        try{
            eventDao.addEvents((event[]) toreturn.toArray());
        }
        catch (SQLException e){
            System.err.println("events could not be added");
        }

        return "Events added for " + person.getFirstName() + " " + person.getLastName();

    }

    private ArrayList<Model> generatePeople(User descendant, int generation){
        if(generation >= totgenerations)
            return new ArrayList<>();
        ArrayList<Model> husbandParents = generatePeople(descendant,generation+1);
        ArrayList<Model> wifeParents = generatePeople(descendant,generation+1);
        String[] data = {descendant.getId(),descendant.getPersonid(),mnames.getName(),snames.getName(),"m"};
        Person husband = new Person(data);

        System.out.println("GeneratePeople");
        data = new String[] {descendant.getId(),descendant.getPersonid(),fnames.getName(),snames.getName(),"f"};
        System.out.println("GeneratePeople2");
        Person wife = new Person(data);
        personDao personDao = new personDao();
        for(int i = 0; i < husbandParents.size(); i++){
            System.out.println("GeneratePeople4");
            Person temp = (Person) husbandParents.get(i);
            Person tempW = (Person) wifeParents.get(i);
            if(i == 0){
                husband.setFather(temp.getId());
                wife.setFather(tempW.getId());
            }
            else{
                husband.setMother(temp.getId());
                wife.setMother(tempW.getId());
            }
        }

        System.out.println("GeneratePeople3");
        Model temp;
        try {
            temp = personDao.addPerson(husband);
            if(temp.getClass().equals(Person.class))
                husband = (Person) temp;
            else
                return new ArrayList<Model>();
        }
        catch(SQLException e){
            System.err.println("Person not added");
        }
        try {
            temp = personDao.addPerson(wife);
            if(temp.getClass().equals(Person.class))
                wife = (Person) temp;
            else
                return new ArrayList<Model>();
        }
        catch(SQLException e){
            System.err.println("Person not added");
        }

        husband.setSpouse(wife.getId());
        wife.setSpouse(husband.getId());
        ArrayList<Model> couple = new ArrayList<>();
        try {
            personDao.updatePersonSpouse(husband);
            personDao.updatePersonSpouse(wife);
        }
        catch (SQLException e){
            System.err.println("Husband/Wife pair not updated");
            return null;
        }
        couple.add(husband);
        couple.add(wife);
        generateEvents(husband,generation);
        generateEvents(wife,generation);
        return couple;
    }

    private nameHolder getNames(String filePath){
        Gson gson = new Gson();
        nameHolder toReturn = new nameHolder();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            toReturn = gson.fromJson(reader, nameHolder.class);
            reader.close();
        }
        catch (IOException e){
            System.err.println("generator getNames error");
        }
        return toReturn;
    }

    private locations getLocations(String filePath){
        locations temp = null;
        Gson gson = new Gson();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            temp = gson.fromJson(reader, locations.class);
            reader.close();
        }
        catch (IOException e){
            System.err.println("generator getNames error");
        }
        return temp;
    }
}
