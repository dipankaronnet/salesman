/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafVisualization;
import java.lang.Object.*;
import javax.swing.JFrame;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.samples.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.*;
import edu.uci.ics.jung.visualization.decorators.*;
import edu.uci.ics.jung.visualization.renderers.Renderer;
//import org.apache.commons.collections15.*;
//import java.awt.Dimension;
import java.awt.geom.*;
import javax.swing.text.Position;
import Algorithm.*;
import java.awt.Dimension;
import java.awt.Component.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Dorota
 */
public class MyPickingGraphMousePlugin<Object,Integer> extends PickingGraphMousePlugin {

//public ArrayList<Vertex>vertexes;
public ArrayList<VertexLocalization>vertexes;
public Vertex vertex;
public MyPickingGraphMousePlugin()
{
    vertexes=new ArrayList<VertexLocalization>();
}
public void set(Vertex v, Point2D vPlacement)
{
   VertexLocalization a=new VertexLocalization(v.getDistances(), v.getDistancesSize(), v.getLowerBound(), vPlacement);
   vertexes.add(a);
}

@Override
   public  void mouseClicked(MouseEvent e)
{
  /* double x=e.getX();
   double y=e.getY();
   System.out.println(x);
   for(int i=0; i<vertexes.size(); ++i)
   {
       VertexLocalization a;
       a=vertexes.get(i);
       //System.out.println(a.getLocalization().getX());
       if(a.getLocalization().getX()==x && a.getLocalization().getY()==y)
       {
           System.out.println(a.getLowerBound());
       }
   }
   


        //System.out.println(vertex.getLowerBound());
        //VertexVisualization avis=new VertexVisualization(vertex);
        //avis.setVisible(true);
        JFrame frame=new JFrame("wierzcholek");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/
}
/*public Object getVertex()
{
    return vertex;
}*/
}
