package network;

import java.io.IOException;
import java.net.*;

/**
 * détermine l'adresse IP de la machine locale et l'affiche sur la console
 * */
public class Server extends Thread {
    private int noConnexion;
    
    public Server() {
        noConnexion = 0;
    }
    
    @Override
    public void run() {
        try {
            //Initialisation du serveur et ses coodrdonnées locales (adresse/port)
            ServerSocket server = new ServerSocket(9003);
            InetAddress serverIP = InetAddress.getLocalHost();
            int localPort = server.getLocalPort();

            System.out.println("Server is set up at ["+serverIP.getHostAddress()+":"+localPort+"]");

            ThreadGroup group = new ThreadGroup("clientSockets");
            

            while(true) { //attente infinie du serveur sur le port
                Socket nouveauClientSocket;
                ClientSession nouveauClientThread;

                nouveauClientSocket = server.accept(); // attente de connexion de la part d'un nouveau client
                ++noConnexion; // la connexion a eu lieu et un socket a été créé : nouveauClientSocket
                System.out.println("Connexion réussie n° : "+noConnexion);

                /* à présent création d'un thread pour gérer les transactions avec le nouvau client en parallèle 
                 * avec les autres clients déjà connectés et avec l'attente perpétuelle du servur*/

                nouveauClientThread = new ClientSession(nouveauClientSocket, group, noConnexion); 
                nouveauClientThread.start();
            }
        }
        catch (IOException e) {}
    }
}
