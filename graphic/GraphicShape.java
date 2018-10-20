package graphic;

import javafx.scene.shape.Polygon;

/**
 *
 * @author Thømas
 */
public class GraphicShape extends Polygon {
    private final String id;
    
    public GraphicShape(String s) {
        super();
        id = s;
    }
    
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
    
}
