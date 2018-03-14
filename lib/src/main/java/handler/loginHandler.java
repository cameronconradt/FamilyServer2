package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import model.Model;
import model.loginRequest;
import model.loginResponse;
import service.loginService;

/**
 * Created by camer on 2/16/2018.
 */

public class loginHandler extends Handler {


    @Override
    public void handle(HttpExchange ex) throws IOException {
        Gson gson = new Gson();

        InputStreamReader in = new InputStreamReader(ex.getRequestBody());
        loginRequest request = gson.fromJson(in,loginRequest.class);
        in.close();

        Model response = loginService.serve(request);

        ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

        OutputStreamWriter out = new OutputStreamWriter(ex.getResponseBody());
        gson.toJson(response,out);
        out.close();
    }
}
