package server;

/**
 * Created by camer on 3/5/2018.
 */

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import dao.Dao;
import handler.*;


    public class server
    {

        private static final int MAX_WAITING_CONNECTIONS = 12;

        public static void main(String[] args)
        {
            String portNumber = args[0];
            new server().run(portNumber);
        }

        private void run(String portNumber)
        {
            HttpServer server;
            System.out.println("Initializing HTTP Server");

            try
            {
                server = HttpServer.create(
                        new InetSocketAddress(Integer.parseInt(portNumber)),
                        MAX_WAITING_CONNECTIONS);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
            // Indicate that we are using the default "executor".
            // This line is necessary, but its function is unimportant for our purposes.
            server.setExecutor(null);

            //clear
            server.createContext("/clear", new clearHandler());

            //load
            server.createContext("/load", new loadHandler());

            //fill
            server.createContext("/fill", new fillHandler());

            //login
            server.createContext("/user/register", new registerHandler());

            //login
            server.createContext("/user/login", new loginHandler());

            //event
            server.createContext("/event", new eventHandler());

            //person
            server.createContext("/person", new personHandler());

            //do last
            server.createContext("/", new defaultHandler());


            System.out.println("Starting server");

            server.start();

            System.out.println("Server started");
        }
    }
