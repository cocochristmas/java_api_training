package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CallHandler implements HttpHandler {
    private final String s = "OK";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, s.length());
        try (OutputStream os = exchange.getResponseBody()) {
            System.err.println(exchange.getProtocol());
            os.write(s.getBytes());
        }
    }
}