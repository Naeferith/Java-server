/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import javafx.application.Platform;
import javafx.scene.shape.Circle;
import network.ClientSession;
import org.w3c.dom.Node;

/**
 *
 * @author Thomas
 */
public class CircleInterface extends DrawableInterface {
    
    public CircleInterface(DrawableInterface di) {
        super(di);
    }
    
    @Override
    public void executeRequest(ClientSession cs) {
        System.out.println("Ceci est un circle");
        //Structure a dessiner
        String id = DrawableInterface.doc.getDocumentElement().getAttribute("id");
        Object temp = cs.retrieveShape(id);
        final Circle circle;
        boolean isUpdate = temp != null;
        
        if (temp != null) circle = (Circle) temp;
        else {
            circle = new Circle();
            circle.setId(id);
        }
        
        //Center
        final Node center = DrawableInterface.doc.getElementsByTagName("vertices").item(0).getFirstChild();
        circle.setCenterX(Double.parseDouble(center.getFirstChild().getTextContent()));
        circle.setCenterY(Double.parseDouble(center.getLastChild().getTextContent()));
        
        //Raduis
        circle.setRadius(Double.parseDouble(DrawableInterface.doc.getElementsByTagName("radius").item(0).getTextContent()));
        
        //Color
        circle.setFill(retrieveColor());
        
        Platform.runLater(new Runnable() {
            @Override public void run() {
                if (!isUpdate) cs.getGroup().getChildren().add(circle);
            }
        });
    }

    @Override
    public String getDescription() {
        return "circle";
    }
}
