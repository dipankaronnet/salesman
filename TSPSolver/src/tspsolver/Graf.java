/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tspsolver;

/**
 *
 * @author Dorota
 */
import java.lang.Object;
import edu.uci.ics.jung.graph.AbstractGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

/* klasa AbstractGraf - abstrakcyjna implementuje interfejs Graph
  trzeba dopisać implementacje bądź jakie jej metod abstrakcyjnych i można próbować
 tworzyć jakiś graf
 <> w tych nawiasach to są genericsy - mówią kompilatorowi jakiego typu jest kolekcja
  - u nas że Graf ma wierzchołki Integer i krawędzie String*/
public class Graf<Integer,String> extends AbstractGraph<Integer,String>
{
    Integer a;
    boolean isSource(Integer v, String e)
    {
        return false;
    }
    boolean isDest(Integer v, String e)
    {
        return false;
    }
    Integer getDest(String e)
    {
      return null;
    }
    public Integer getSource(String e)
    {
        return null;
    }
    public Integer getSuccessors(Integer v)
    {
        return null;
    }
  Pair<Integer> getEndpoints(String edge)
   {
        Pair<Integer> para = new Pair<Integer>(4,5);
        return para;
   }
  boolean addEdge(String edge, Pair<? extends Integer> endpoints, EdgeType edgeType)
   {
       return true;
   }
  //TODO doimplementwac inne metody abstrakcyjne
}
