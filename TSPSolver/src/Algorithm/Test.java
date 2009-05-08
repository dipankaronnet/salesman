/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm;
import GrafVisualization.*;
import javax.swing.*;

/**
 *
 * @author Dorota
 */
public class Test {

   public static void main(String args[])
    {
        Canvas1 kanwa=new Canvas1();
        JFrame frame2=new JFrame("grafDescription");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.getContentPane().add(kanwa);
        frame2.pack();
        frame2.setVisible(true);
        /*Solver a=new Solver(6);
        a.branchAndBound();
        Costs root=(Costs)a.tree.getRoot();
        a.createTreeVisualization();
        a.completePath();
        a.printAnswer();*/

 /*       Costs a=new Costs(6);
        a.setDistances();
        a.setLowerBoundAndReduce();
        a.setEdgeToBranch();
        Edge krawedz=a.getEdgeToBranch();
        Costs b= new Costs(6,a,krawedz,false);
        b.showDistances();
        b.showPath();
        Costs c= new Costs(5,a,krawedz,true);
        System.out.println();
        c.showPath();
       c.setLowerBoundAndReduce();
       c.setEdgeToBranch();
       krawedz=c.getEdgeToBranch();
        c.showDistances();
        System.out.println("elo"+krawedz.getFrom()+" "+krawedz.getTo());
        Costs d= new Costs(4,c,krawedz,true);
        System.out.println();
        d.showDistances();
        d.showPath(); */
    }

}
