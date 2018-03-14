package handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import dao.eventDao;
import model.Model;
import model.event;
import model.events;
import service.eventService;

/**
 * Created by camer on 2/16/2018.
 */

public class eventHandler extends Handler {


    @Override
    public void handle(HttpExchange ex) throws IOException {

        try{
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

            String uri = ex.getRequestURI().toString();

            System.out.println(uri);

            String[] path = uri.split("/");
            String auth_token = ex.getRequestHeaders().get("Authorization").get(0);

            Gson gson = new Gson();
            String id = null;

            if (!(path.length < 2 || path.length > 3)) {
                if (path[path.length - 2].equals("event")) {
                    id = path[path.length-1];
                }
                else{
                        System.out.println("unidentified");
                }
            }

                Model event = eventService.serve(auth_token,id);

            ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

            OutputStreamWriter output = new OutputStreamWriter(ex.getResponseBody());
            gson.toJson(event,output);
            output.close();

        }
        catch(IOException e){
            ex.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            ex.getResponseBody().close();
            e.printStackTrace();
        }
    }

}
