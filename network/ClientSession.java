package network;

import graphic.GraphicShape;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javaserver.*;


/**
 *
 * @author Thømas
 */
public class ClientSession extends Thread {
    private final Socket socket;
    private final int noConnexion;
    
    private Group SceneGroup = new Group();
    
    private DrawableInterface chain = null;
    
    private BufferedReader inputStream;
    
    public ClientSession(Socket s, ThreadGroup tg, int i) throws IOException {
        super(tg, "ClientSession");
        this.socket = s;
        this.noConnexion = i;
        
        this.inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        
        chain = new ShapeInterface(chain);
        chain = new CircleInterface(chain);
        chain = new ShapeGroupInterface(chain);
        
        Platform.runLater(new Runnable() {
            @Override public void run() {
                JavaServer.Root.getChildren().add(SceneGroup);
            }
        });
    }
    
    public Group getGroup() {
        return SceneGroup;
    }
    
    private ArrayList<Node> getAllNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(SceneGroup, nodes);
        return nodes;
    }
    
    private void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent) addAllDescendents((Parent)node, nodes);
        }
    }
    
    public GraphicShape initShape(String id) {
        for (javafx.scene.Node node : getAllNodes()) {
            if (node != null && node instanceof GraphicShape && ((GraphicShape)node).getID().equals(id)) return (GraphicShape)node;
        }
        return new GraphicShape(id);
    }
    
    @Override
    public void run() {
        String ligne; //ligne traitée dans les données envoyées
        try {
            while(!isInterrupted()) {
                ligne = inputStream.readLine(); //Data envoyé par le client (par ligne)
                System.out.println("Recieved data from client ["+noConnexion+"] : "+ligne);
                
                chain.interpretXML(ligne, this);
                
                sleep(5);
            }
        }
        catch(InterruptedException ie){/*FIN DU THREAD*/}
        catch(IOException ioe){}
    }
}
