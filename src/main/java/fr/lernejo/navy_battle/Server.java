package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.handlers.Ping;
import fr.lernejo.navy_battle.handlers.Start;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class Server {

    private final HttpServer server;
    private final Ping ping;
    private final Start start;

    public Server(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));

        ping = new Ping();
        start = new Start();


        server.createContext("/ping", ping);
        server.createContext("/api/game/start", (new Start()).Start(this));
        server.start();
    }
}