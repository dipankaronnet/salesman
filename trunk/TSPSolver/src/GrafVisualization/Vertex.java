/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafVisualization;
import java.awt.geom.*;

/**
 *
 * @author Dorota
 */
public class Vertex {
    private String description;
    private Point2D placement;
    public void setPlacement(int x, int y)
    {
        placement=new Point2D.Double(x, y);
    }
    public Point2D getPlacement()
    {
        return placement;
    }

    public void setDescription(String a)
    {
        description=a;
    }
    public String getDescription()
    {
        return description;
    }
    @Override
    public String toString()
    {
        return description;
    }


}
