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
        Costs a=new Costs(6);
        a.setDistances();
        a.setLowerBoundAndReduce();
        System.out.println(a.getLowerBound());
        a.showDistances();
        a.setEdgeToBranch();

    }

}
