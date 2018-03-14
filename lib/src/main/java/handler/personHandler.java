package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.InvalidPathException;

import model.Model;
import service.personService;

/**
 * Created by camer on 2/16/2018.
 */

public class personHandler extends Handler {


    @Override
    public void handle(HttpExchange ex) throws IOException {
        Gson gson = new Gson();
        URI uri = ex.getRequestURI();
        String path = uri.getPath();

        String[] parts = path.split("/");
        String id = null;
        if(parts.length == 3){
            id = parts[2];
        }
        else {
            throw new InvalidPathException(path, "Invalid Path");
        }
        String auth_token = ex.getRequestHeaders().getFirst("Authorization");

        Model response = personService.serve(auth_token,id);

        ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

        OutputStreamWriter out = new OutputStreamWriter(ex.getResponseBody());
        gson.toJson(response,out);
        out.close();
    }
}
