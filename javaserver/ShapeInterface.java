/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

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
        
        final Element racine = DrawableInterface.doc.getDocumentElement();
        final NodeList racineNoeuds = racine.getChildNodes();
        
        for (int i = 0; i<racineNoeuds.getLength(); i++) {
            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Node personne = racineNoeuds.item(i);
                System.out.println(personne.getNodeName());
            }				
        }

        
        Polygon polygon = new Polygon();
    }

    @Override
    public String getDescription() {
        return "shape";
    }
    
}
