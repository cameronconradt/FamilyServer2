package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import model.Model;
import model.loadRequest;
import service.loadService;

/**
 * Created by camer on 2/16/2018.
 */

public class loadHandler extends Handler {

    @Override
    public void handle(HttpExchange ex) throws IOException {

        Gson gson = new Gson();
        InputStreamReader inputStreamReader = new InputStreamReader(ex.getRequestBody());
        loadRequest request = gson.fromJson(inputStreamReader,loadRequest.class);
        inputStreamReader.close();

        Model response = loadService.serve(request);

        ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

        OutputStreamWriter output = new OutputStreamWriter(ex.getResponseBody());
        gson.toJson(response,output);
        output.close();
    }
}
