/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.w3c.dom.*;

/**
 *
 * @author Thomas
 */
public class ShapeInterface extends DrawableInterface {

    public ShapeInterface(DrawableInterface di) {
        super(di);
    }
    
    @Override
    public void executeRequest() {
        System.out.println("Ceci est une forme");

        //Structure a dessiner
        Polygon polygon = new Polygon();
        
        //Couleur
        polygon.setFill(new Color(
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("r").item(0).getTextContent()), 
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("g").item(0).getTextContent()), 
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("b").item(0).getTextContent()), 
                1));
        
        //Points
        final Node verticeNode = DrawableInterface.doc.getElementsByTagName("vertices").item(0);
        final NodeList vertices = verticeNode.getChildNodes();
        
        for (int i = 0; i<vertices.getLength(); i++) {
            if(vertices.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element e = (Element) vertices.item(i);
                polygon.getPoints().add(new Double(e.getElementsByTagName("x").item(0).getTextContent()));
                polygon.getPoints().add(new Double(e.getElementsByTagName("y").item(0).getTextContent()));
            }				
        }
        Platform.runLater(new Runnable() {
            @Override public void run() {
                JavaServer.Root.getChildren().add(polygon);
            }
        });
        
    }

    @Override
    public String getDescription() {
        return "shape";
    }
    
}
