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
import java.awt.event.*;
import java.util.*;

public class Graf
{
    public DirectedSparseMultigraph<Vertex,Integer>gv;
    private Layout<Vertex,Integer>layout;
    private Vertex findRoot()
    {
            Collection<Vertex>vertexes=new ArrayList<Vertex>();
            vertexes=gv.getVertices();
            Vertex root=null;
            for(Iterator<Vertex>it=vertexes.iterator(); it.hasNext();)
            {
                Vertex a=it.next();
                if(gv.degree(a)==2)
                    root=a;
            }
            return root;
    }
    public void init()
    {
        gv=new DirectedSparseMultigraph<Vertex,Integer>();
    }
    private void setVertexPlacement(Vertex parent, Point2D parentPlacement, double wsp)
    {
       Collection<Vertex>children=new ArrayList<Vertex>();
        children=gv.getSuccessors(parent);
        int childrenNotLeaf=0;
        int childrenCounter=0;
        double x,y;
        for(Iterator<Vertex>it=children.iterator();it.hasNext();)
        {
            Vertex child=it.next();
            if(gv.getSuccessorCount(child)!=0)
            {
                childrenNotLeaf++;
            }
        }

        
        for(Iterator<Vertex>it=children.iterator();it.hasNext();)
        {
            Vertex child=it.next();
            y=parentPlacement.getY()+50;
            if(childrenCounter%2==0)
                x=parentPlacement.getX()-wsp*150;
            else
                x=parentPlacement.getX()+wsp*150;
            Point2D placement=new Point2D.Double(x, y);
            layout.setLocation(child, placement);
            if(childrenNotLeaf>1)
                setVertexPlacement(child,placement,0.5*wsp);
            else
                setVertexPlacement(child,placement,wsp);
            childrenCounter++;
        }

    }
    public void drawGraf()
    {
        SimpleGraphDraw sgv= new SimpleGraphDraw();
         layout= new CircleLayout(gv);
       VisualizationViewer<Vertex,Integer> vv =
        new VisualizationViewer<Vertex,Integer>(layout);
       vv.setPreferredSize(new Dimension(1500,1000));
       Vertex root=findRoot();
       Point2D rootPlacement=new Point2D.Double(500.0,50.0);
       layout.setLocation(root, rootPlacement);

       setVertexPlacement(root,rootPlacement,1);
    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
PluggableGraphMouse gm = new PluggableGraphMouse();
gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON1_MASK));
gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));
PickingGraphMousePlugin a=new PickingGraphMousePlugin();
gm.add(a);
vv.setGraphMouse(gm);

        JFrame frame=new JFrame("graf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F1A8FC4E-4E6F-8B9B-511D-A4349D12269B]
// </editor-fold>

