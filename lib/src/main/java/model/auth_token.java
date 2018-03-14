package model;

/**
 * Created by camer on 2/16/2018.
 */

public class auth_token extends Model {
    private String id;
    private String user_id;

    public String getId() {
        return id;
    }

    public String getUserId() { return user_id;}

    /**
     *
     * @param token Token to store
     */
    public auth_token(String token, String user_id){this.id = token; this.user_id = user_id;}

    public String getData(){
        return new String("user_id = " + user_id);
    }


}
