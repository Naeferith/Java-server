/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

import graphic.GraphicShape;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import network.ClientSession;
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
    public void executeRequest(ClientSession cs) {
        System.out.println("Ceci est une forme");
        //Structure a dessiner
        String id = DrawableInterface.doc.getDocumentElement().getAttribute("id");
        GraphicShape polygon = cs.initShape(id);
        boolean isUpdate = !polygon.getPoints().isEmpty();
        
        //Couleur
        polygon.setFill(new Color(
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("r").item(0).getTextContent()), 
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("g").item(0).getTextContent()), 
                Double.parseDouble(DrawableInterface.doc.getElementsByTagName("b").item(0).getTextContent()), 
                1));
        
        //Points
        final Node verticeNode = DrawableInterface.doc.getElementsByTagName("vertices").item(0);
        final NodeList vertices = verticeNode.getChildNodes();
        
        //Si des points ont déja été définis, c'est un update -> on reset les points.
        if (!polygon.getPoints().isEmpty()) polygon.getPoints().clear();
        
        for (int i = 0; i<vertices.getLength(); i++) {
            if(vertices.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element e = (Element) vertices.item(i);
                polygon.getPoints().add(new Double(e.getElementsByTagName("x").item(0).getTextContent()));
                polygon.getPoints().add(new Double(e.getElementsByTagName("y").item(0).getTextContent()));
            }				
        }
        Platform.runLater(new Runnable() {
            @Override public void run() {
                if (!isUpdate) cs.getGroup().getChildren().add(polygon);
            }
        });
        
    }

    @Override
    public String getDescription() {
        return "shape";
    }
    
}
