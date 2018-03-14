package handler;

/**
 * Created by camer on 2/16/2018.
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class defaultHandler extends Handler {


    @Override
    public void handle(HttpExchange ex) throws IOException {

        try{
            OutputStream responseBody = ex.getResponseBody();


            String uri = ex.getRequestURI().toString();

            String[] path = uri.split("/");

            String url = "web";

            Headers header = ex.getResponseHeaders();

            if(uri.equals("/") || uri.isEmpty()){
                url += "/index.html";
                header.set("Content-Type", "text/html");
            }
            else{
                if(path[path.length-1].split("[.]").length != 0){
                    String last = path[path.length-1].split("[.]")[path[path.length-1].split("[.]").length-1];
                    switch (last.toLowerCase()){
                        case "css" :
                            header.set("Content-Type", "text/css");
                            break;
                        case "jpg":
                        case "jpeg" :
                            header.set("Content-Type", "image/jpeg");
                            break;
                        case "html":
                            header.set("Content-Type", "text/html");
                            break;
                        default :
                            break;

                    }
                }
                url += uri;
            }
            //

            File file = new File(url);

            if(file.canRead() && file.exists()){
                ex.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            }
            else{
                file = new File("web/HTML/404.html");
                header.set("Content-Type", "text/html");
                ex.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            }

            Files.copy(file.toPath(),responseBody);

            responseBody.close();
        }
        catch(IOException e){
            OutputStream responseBody = ex.getResponseBody();
            File file = new File(".web/HTML/500.html");
            Headers header = ex.getResponseHeaders();
            header.set("Content-Type", "text/html");
            Files.copy(file.toPath(),responseBody);

            ex.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,0);
            ex.getResponseBody().close();

            e.printStackTrace();
        }
    }

}
