package fr.lernejo.navy_battle.handlers;


import org.assertj.core.internal.bytebuddy.utility.RandomString;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.Server;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class Start  {
    public HttpHandler Start(Server server) {
        return new HttpHandler() {
            public void SendResponse(HttpExchange exchange) throws IOException {
                RandomString randomString = new RandomString(10);
                JSONObject response = new JSONObject();
                response.put("id", randomString.nextString());
                response.put("url", "http://" + exchange.getLocalAddress().getHostName() + ":" + exchange.getLocalAddress().getPort());
                response.put("message", "Salut tdb");
                
                String r = response.toString();
                exchange.sendResponseHeaders(202, r.length());
                System.out.println(r);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(r.getBytes());
                }
            }

            public void handle(HttpExchange exchange) throws IOException {
                if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                    JSONObject json;
                    json = new JSONObject(new String(exchange.getRequestBody().readAllBytes()));
                    this.SendResponse(exchange);
                    try{
                        System.out.println("id: " + json.getString("id"));
                        System.out.println("url: " + json.getString("url"));
                        System.out.println("message: " + json.getString("message"));
                    } catch(Exception e) {
                        System.out.println("error in JSON !!");
                    }
                }
                else{
                    exchange.sendResponseHeaders(404,0);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(' ');
                    }
                }
            }
        };

    
    }
}
