package model;

/**
 * Created by camer on 2/16/2018.
 */

public class Model {
    protected String message;

    public Model(){
        message = null;
    }
    public Model(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
