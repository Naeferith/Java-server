package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 *
 * @author Thømas
 */
public class ClientSession extends Thread {
    private final Socket socket;
    private final int noConnexion;
    
    private BufferedReader inputStream;
    //TODO variable d output (fenetre, texte, je sais pas)
    
    public ClientSession(Socket s, ThreadGroup tg, int i) throws IOException {
        super(tg, "ClientSession");
        this.socket = s;
        this.noConnexion = i;
        
        this.inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }
    
    @Override
    public void run() {
        String ligne; //ligne traitée dans les données envoyées
        try {
            while(!isInterrupted()) {
                ligne = inputStream.readLine(); //Data envoyé par le client (par ligne)
                System.out.println("Recieved data from client ["+noConnexion+"] : "+ligne);
                //TODO do stuff
                sleep(5);
            }
        }
        catch(InterruptedException ie){/*FIN DU THREAD*/}
        catch(IOException ioe){}
    }
}
