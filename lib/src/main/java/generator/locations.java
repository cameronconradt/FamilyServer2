package generator;

import java.util.Random;

/**
 * Created by camer on 3/12/2018.
 */

public class locations {
    public locationHolder[] data;

    public locationHolder[] getData() {
        return data;
    }

    public void setData(locationHolder[] data) {
        this.data = data;
    }

    public locationHolder getLocation(){
        Random ran = new Random();
        return data[ran.nextInt(data.length)];
    }
}
