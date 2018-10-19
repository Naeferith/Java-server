/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

/**
 *
 * @author Thomas
 */
public class CircleInterface extends DrawableInterface {
    
    public CircleInterface(DrawableInterface di) {
        super(di);
    }
    
    @Override
    public void executeRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return "circle";
    }
}
