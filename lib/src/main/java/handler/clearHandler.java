package handler;

import com.sun.net.httpserver.HttpExchange;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import model.Model;
import model.clearResult;
import service.clearService;

/**
 * Created by camer on 2/16/2018.
 */

public class clearHandler extends Handler {
    static int count = 0;
    /**
     * Clears the database of all entries
     *
     */
    @Override
    public void handle(HttpExchange ex)throws IOException{
        System.out.println("clear handle" + count);
        count++;

        try {
            switch (ex.getRequestMethod().toLowerCase()) {
                case "post":
                    System.out.println("is post");
                    break;
                case "get":
                    System.out.println("is get");
                    break;
                default:
                    System.out.println("unidentified");
                    break;
            }
            OutputStream responseBody = ex.getResponseBody();
            OutputStreamWriter outBody = new OutputStreamWriter(responseBody);

            String uri = ex.getRequestURI().toString();

            System.out.println(uri);

            String[] path = uri.split("/");

            boolean good_path = true;

            if (path[path.length - 1].equals("clear")) {
                System.out.println("trying to clear");
            } else {
                good_path = false;
                System.out.println("unidentified");
            }
            if (path.length != 2) {
                good_path = false;
            }

            if (!good_path) {
                ex.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            } else {
                Model clearResult = new clearService().clear();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                String json = gson.toJson(clearResult);

                ex.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                outBody.write(json);
            }
        outBody.close();

        }
        catch(IOException e){
            ex.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,0);
            ex.getResponseBody().close();
            e.printStackTrace();
        }

    }
}
