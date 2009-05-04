/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm;

/**
 *
 * @author Dorota
 */
public class Test {

    public static void main(String args[])
    {
        Solver a=new Solver(6);
        a.branchAndBound();
        Costs root=(Costs)a.tree.getRoot();
       // a.showTree(root);
                a.completePath();
        a.printAnswer();
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
