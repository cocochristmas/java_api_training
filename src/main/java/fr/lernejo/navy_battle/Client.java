// package fr.lernejo.navy_battle;
package fr.lernejo.navy_battle;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;

import fr.lernejo.navy_battle.handlers.Fire;

import java.net.http.HttpResponse;

// import fr.lernejo.navy_battle.handlers.Ping;
// import fr.lernejo.navy_battle.handlers.Start;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;


public class Client {

    private final HttpClient client;
    private final HttpServer server;
    private final Fire fire;
    // private final Start start;

    public Client(int port, String serverURL) throws IOException {
        // server.setExecutor(Executors.newSingleThreadExecutor());

        client = HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_2)
                                .build();


        HttpRequest requetePost = HttpRequest.newBuilder()
                                    .uri(URI.create(serverURL + "/api/game/start"))
                                    .setHeader("Accept", "application/json")
                                    .setHeader("Content-Type", "application/json")
                                    .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}"))
                                    .build();
    
        client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString());

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
    
        fire = new Fire();
        server.createContext("/fire", fire);
        server.start();
    }
}