/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafVisualization;
import java.awt.geom.*;

/**
 * wierzcholek tlyko nie w tym logicznym drzewie costów tlyko w wizualizacji
 * posiada połozenie opis itd
 * @author Dorota
 */
public class Vertex{
    int id;
    private String description;
    private Point2D placement;
    Integer [][]distances;
    int lowerBound;
    int distancesSize;
    public Vertex(Integer[][]tab,int tabSize, int lb)
    {
        distancesSize=tabSize;
        distances=new Integer[tabSize][];
        for(int i=0; i<tabSize; ++i)
            distances[i]=new Integer[tabSize];

        for(int i=0; i<tabSize; ++i)
            for(int j=0; j<tabSize; ++j)
                distances[i][j]=tab[i][j];
        lowerBound=lb;

    }
    public Integer[][]getDistances()
    {
        return distances;
    }
    public int getLowerBound()
    {
        return lowerBound;
    }
    public int getDistancesSize()
    {
        return distancesSize;
    }
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
   public int getId()
   {
       return id;
   }
   public void setId(int i)
   {
       id=i;
   }


}
