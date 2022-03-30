package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        if (args.length == 1) {
            launcher.launchServer(args[0]);
        } else if (args.length == 2) {
                launcher.launchClient(args[0], args[1]);
        } 
        else {
            System.err.println("[ERROR] The usage is java -jar fr.lernejo.navy_battle.Launcher [port] ([id])");
                // launcher.launchServer("8384");
        }
    }

    public Launcher() {
        // Constructor for the Server and Client launchers
    }

    public void launchServer(String portString) {
        int port;
        try {
            port = Integer.parseInt(portString);
            try {
                new Server(port);
                System.out.println("The server is ready and running...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Invalid port : " + portString);
        }
    }

    public void launchClient(String portString, String serverURL) {
        int port;
        try {
            port = Integer.parseInt(portString);
            try {
                new Client(port, serverURL);
                System.out.println("The Client is ready");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Invalid port : " + portString);
        }
    }
}