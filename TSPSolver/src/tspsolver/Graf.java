package tspsolver;

import edu.uci.ics.jung.graph.AbstractGraph; 
import edu.uci.ics.jung.graph.util.EdgeType; 
import edu.uci.ics.jung.graph.util.Pair; 

/**
 *  klasa AbstractGraf - abstrakcyjna implementuje interfejs Graph
 *    trzeba dopisać implementacje bądź jakie jej metod abstrakcyjnych i można próbować
 *   tworzyć jakiś graf
 *   <> w tych nawiasach to są genericsy - mówią kompilatorowi jakiego typu jest kolekcja
 *    - u nas że Graf ma wierzchołki Integer i krawędzie String
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F1A8FC4E-4E6F-8B9B-511D-A4349D12269B]
// </editor-fold> 
public class Graf < Integer, String > extends AbstractGraph<String> {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0F8FC050-88F8-00C7-7707-F7010E441AAF]
    // </editor-fold> 
    Pair getEndpoints (String edge) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.48908A9E-34D7-6DB8-8D24-D35768DFDE98]
    // </editor-fold> 
    boolean addEdge (String edge, Pair endpoints, EdgeType edgeType) {
        return true;
    }

}

