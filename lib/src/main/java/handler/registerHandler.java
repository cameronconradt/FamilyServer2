package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import model.Model;
import model.User;
import model.loginResponse;
import service.registerService;

/**
 * Created by camer on 2/16/2018.
 */

public class  registerHandler extends Handler {

    @Override
    public void handle(HttpExchange ex) throws IOException {
        Gson gson = new Gson();
        InputStreamReader in = new InputStreamReader(ex.getRequestBody());
        User user = gson.fromJson(in,User.class);
        in.close();

        Model response = registerService.serve(user);

        ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

        OutputStreamWriter out = new OutputStreamWriter(ex.getResponseBody());
        gson.toJson(response,out);
        out.close();
    }
}
