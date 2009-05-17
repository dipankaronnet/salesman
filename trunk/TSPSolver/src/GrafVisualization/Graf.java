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
import Algorithm.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * wizualicja dzewa gui
 * @author Dorota
 */
public class Graf
{
    public DirectedSparseMultigraph<Vertex,Integer>gv;
   public Layout<Vertex,Integer>layout;
    public VisualizationViewer<Vertex,Integer>vv;
   private MyPickingGraphMousePlugin<Vertex,Integer> mouse;
   int vertexId=0;
   int vertexCounter=0;
   /**
    * jezlei jakis wierzcholek ma 2 krawedzie to root
    * @return
    */
    public Vertex findRoot()
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

    /**
     * przechodzi po drzewie rekurencyjnie i na podstawie wsp rodzica znajdujemy
     * zdzieciom miejsce
     * TU MOZE BYC BŁAD TEZ ZE LEWE DZIECKO PO PRAWEJ STRONIE  trzeba sietemu przyjrzec
     * @param parent
     * @param parentPlacement
     * @param wsp
     */
    private void setVertexPlacement(Vertex parent, Point2D parentPlacement, double wsp)
    {
       Collection<Vertex>children=new ArrayList<Vertex>();

       ArrayList<Vertex>childrenList=new ArrayList<Vertex>();

       //zwraca nastepników w dowolnej kolejnosci dlatego w drzewie w dowonej kolejnosci
       //trzeba je przesorotwac z mniejszym lb pierwsze zrobie to jutro
        children=gv.getSuccessors(parent);
         for(Iterator<Vertex>its=children.iterator();its.hasNext();)
         {
             Vertex el=its.next();
             childrenList.add(el);
         }
        //jezeli peirwsze dziecko ma mniejsze lb to usuwamy i wrzucamy na 
        // koniec w przeciwnym wypadku nicn ie robimy
       Iterator <Vertex>its=childrenList.iterator();
       if(its.hasNext())
       {
           Vertex child1=its.next();
           Vertex child2=its.next();
           if(child1.getLowerBound()>child2.getLowerBound())
           {
             Vertex ble=childrenList.remove(0);
             childrenList.add(0, child2);
             childrenList.add(1,ble);
           }
       }
        mouse.set(parent, parentPlacement);
        int childrenNotLeaf=0;
        int childrenCounter=0;
        double x,y;
        for(Iterator<Vertex>it=childrenList.iterator();it.hasNext();)
        {
            Vertex child=it.next();
            System.out.println(child.getLowerBound());
            if(gv.getSuccessorCount(child)!=0)
            {
                childrenNotLeaf++;
            }
        }

        
        for(Iterator<Vertex>it=childrenList.iterator();it.hasNext();)
        {
            Vertex child=it.next();
            y=parentPlacement.getY()+50;
            if(childrenCounter%2==0)
                x=parentPlacement.getX()-wsp*200;
            else
                x=parentPlacement.getX()+wsp*200;
            Point2D placement=new Point2D.Double(x, y);
            layout.setLocation(child, placement);
            //System.out.println(child.getId()+" "+child.getDescription());
            if(childrenNotLeaf>1)
                setVertexPlacement(child,placement,0.5*wsp);
            else
                setVertexPlacement(child,placement,wsp);
            childrenCounter++;
        }

    }
    /**
     * nadaje wierzcholkowi w wizualicji nr takzeby mzona bylo skojarzyc ztabelka
     * dodaja do opisu
     * @param root
     */
    public void setId(Vertex root)
    {
        root.setId(vertexId);
        ++vertexId;
        String olddesc=root.getDescription();
        String newdesc=Integer.toString(root.getId())+". "+olddesc;
        root.setDescription(newdesc);
        Collection<Vertex>children=new ArrayList<Vertex>();
        children=gv.getSuccessors(root);
        int childCounter=0;
         for(Iterator<Vertex>it=children.iterator();it.hasNext();)
        {
            Vertex child=it.next();
            setId(child);
            ++childCounter;

        }



    }
    /**
     *  gui poki dziala to wolałabym nie wnikac jakeis myszki klikanie wyswietlenie całosci
     * na formatce na pewno nie ma nic wspolnego z rozmieszczeniem wierzchołków i ich
     * pozycja na formatce jakeies głupoty z myszka itd itp
     */
    public void drawGraf()
    {
 //       SimpleGraphDraw sgv= new SimpleGraphDraw();
         layout= new CircleLayout(gv);
 vv =   new VisualizationViewer<Vertex,Integer>(layout);
       vv.setPreferredSize(new Dimension(1500,1000));
       Vertex root=findRoot();
       MyGraphMouseListener mouseListener=new MyGraphMouseListener();
       vv.addGraphMouseListener(mouseListener);
       Point2D rootPlacement=new Point2D.Double(500.0,50.0);
        mouse=new MyPickingGraphMousePlugin<Vertex,Integer>();
       layout.setLocation(root, rootPlacement);
        setVertexPlacement(root,rootPlacement,1);
        vertexId=0;
        setId(root);
        vertexCounter=vertexId;
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        PluggableGraphMouse gm=new PluggableGraphMouse();
        gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON1_MASK));
        gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));
        vv.setGraphMouse(gm);
        gm.add(mouse);
        MouseEvent e=new MouseEvent(vv, 0, 1,1, 0, 0, 0, true);
        //mouseListener.graphClicked(e, e)

        mouse.mouseClicked(e);
        JFrame frame=new JFrame("graf");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
      /*  Canvas1 kanwa=new Canvas1();
        JFrame frame2=new JFrame("grafDescription");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.getContentPane().add(kanwa);
        frame2.pack();
        frame2.setVisible(true);*/
    }

}
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F1A8FC4E-4E6F-8B9B-511D-A4349D12269B]
// </editor-fold>

