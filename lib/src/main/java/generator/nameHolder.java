package generator;

import java.util.Random;

/**
 * Created by camer on 3/12/2018.
 */

public class nameHolder {
    String[] data;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getName(){
        Random ran = new Random();
        return data[ran.nextInt(data.length)];
    }
}
