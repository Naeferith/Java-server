package application;

import com.sun.org.apache.xerces.internal.dom.DeferredDocumentImpl;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


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
                
                //Interpretation de l'xml recu
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                StringBuilder xmlStringBuilder = new StringBuilder();
                xmlStringBuilder.append(ligne);
                ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
                DocumentBuilder builder = null;
                Document doc = null;
                
                try {
                    builder = factory.newDocumentBuilder();
                    doc = builder.parse(input);
                }
                catch (Exception e) { System.out.println("Erreur : XML corrompu");}
                
                //TODO do stuff
                //root elem doc.getDocumentElement() //tag name .getTagName()
                
                sleep(5);
            }
        }
        catch(InterruptedException ie){/*FIN DU THREAD*/}
        catch(IOException ioe){}
    }
}
