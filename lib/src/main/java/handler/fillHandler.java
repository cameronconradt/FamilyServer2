package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.InvalidPathException;

import model.Model;
import service.fillService;

/**
 * Created by camer on 2/16/2018.
 */

public class fillHandler extends Handler {
    private static final String DEFAULT_GENERATIONS = "4";

    @Override
    public void handle(HttpExchange ex) throws IOException {
        URI uri = ex.getRequestURI();
        String[] path = uri.getPath().split("/");

        String userName = null;
        String generations = null;
        if(path.length == 3){
            userName = path[2];
            generations = DEFAULT_GENERATIONS;
        }
        else if(path.length == 4){
            userName = path[2];
            generations = path[3];
        }
        else{
            throw new InvalidPathException(path.toString(),"Invalid Path" );
        }

        Model response = fillService.serve(userName,generations);

        ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

        OutputStreamWriter output = new OutputStreamWriter(ex.getResponseBody());
        Gson gson = new Gson();
        gson.toJson(response, output);
        output.close();

    }
}
